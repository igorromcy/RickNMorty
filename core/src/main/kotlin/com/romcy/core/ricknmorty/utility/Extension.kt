package com.romcy.core.ricknmorty.utility

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException

internal fun Throwable.isConnectionException(): Boolean =
    this is ConnectException || this is UnknownHostException || this is ConnectionShutdownException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T,
): Result<T, ResultError.NetworkError> {
    return withContext(dispatcher) {
        try {
            mapResponse(apiCall())
        } catch (exception: Exception) {
            if (exception is HttpException) {
                mapHttpExceptionToResultError(
                    exception.response()?.errorBody(),
                    exception.code(),
                    exception.message()
                )
            } else {
                mapGenericExceptionToResultError(exception)
            }
        }
    }
}

private fun <T> mapResponse(response: T): Result<T, ResultError.NetworkError> {
    return if (response !is Response<*>) {
        Result.Success(response)
    } else {
        if (response.isSuccessful) {
            Result.Success(response)
        } else {
            mapHttpExceptionToResultError(
                response.errorBody(),
                response.code(),
                response.message()
            )
        }
    }
}

private object MoshiAdapterApiCall {
    val moshiAdapter: JsonAdapter<ErrorBodyResponse> by lazy {
        Moshi.Builder().build().adapter(ErrorBodyResponse::class.java)
    }
}

private fun mapHttpExceptionToResultError(
    errorBody: ResponseBody?,
    statusCode: Int,
    message: String,
) = try {
    val errorBodyString = errorBody?.string()

    val errorBodyResponse = if (shouldDeserialize(errorBodyString, statusCode)) {
        deserializeErrorBodyResponse(errorBodyString)
    } else null

    Result.Error(
        ResultError.NetworkError(
            httpCode = statusCode,
            httpMessage = message,
            serverCode = errorBodyResponse?.code,
            serverMessage = errorBodyResponse?.message,
            localizedMessage = errorBodyResponse?.localizedMessage,
            expectedAction = errorBodyResponse?.expectedAction?.action,
            isConnectionError = false
        )
    )
} catch (exception: Exception) {
    mapGenericExceptionToResultError(exception)
}

private fun deserializeErrorBodyResponse(errorBody: String?): ErrorBodyResponse? = try {
    MoshiAdapterApiCall.moshiAdapter.fromJson(errorBody!!)
} catch (exception: Exception) {
    null
}

private fun shouldDeserialize(
    errorBody: String?,
    statusCode: Int,
) = !errorBody.isNullOrEmpty() &&
        statusCode != HttpURLConnection.HTTP_FORBIDDEN &&
        statusCode !in HttpURLConnection.HTTP_INTERNAL_ERROR..600

private fun mapGenericExceptionToResultError(
    exception: Exception,
) = Result.Error(
    ResultError.NetworkError(
        exceptionTitle = exception::class.simpleName,
        exceptionMessage = exception.message,
        isConnectionError = exception.isConnectionException()
    )
)

package com.romcy.core.ricknmorty.utility

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class HttpCode(
    val httpCode: Int,
    val body: ErrorBodyResponse? = null,
) {
    open class On400(val errorBody: ErrorBodyResponse) : HttpCode(400, errorBody) // Bad Request
    open class On401(val errorBody: ErrorBodyResponse) : HttpCode(401, errorBody) // Unauthorized
    open class On403(val errorBody: ErrorBodyResponse) : HttpCode(403, errorBody) // Forbidden
    open class On404(val errorBody: ErrorBodyResponse) : HttpCode(404, errorBody) // not found
    open class On408(val errorBody: ErrorBodyResponse) : HttpCode(408, errorBody) // request Timeout
    open class On412(val errorBody: ErrorBodyResponse) : HttpCode(412, errorBody) // Precondition Failed
    open class On429(val errorBody: ErrorBodyResponse) : HttpCode(429, errorBody) // Too many requests

    open class On500(
        val errorBody: ErrorBodyResponse,
    ) : HttpCode(
        500,
        errorBody
    ) // Internal Server Error

    open class On501(val errorBody: ErrorBodyResponse) : HttpCode(501, errorBody) // Not Implemented
    open class On502(val errorBody: ErrorBodyResponse) : HttpCode(502, errorBody) // Bad Gateway
    open class On503(val errorBody: ErrorBodyResponse) : HttpCode(503, errorBody) // Service Unavailable
    open class On504(val errorBody: ErrorBodyResponse) : HttpCode(504, errorBody) // Gateway Timeout

    open class OnV3Error(
        val errorBody: ErrorBodyResponse,
    ) : HttpCode(errorBody.code?.toInt() ?: -1, errorBody) // All V3 errors with response 200-ok.

    open class OnV3ParseError : HttpCode(-1) // A V3 error that could not be parsed

    open class NetworkError(
        val errorBody: ErrorBodyResponse? = null,
    ) : HttpCode(999, errorBody) // Network problem

    open class Unknown(
        httpErrorCode: Int,
        val errorBody: ErrorBodyResponse,
    ) : HttpCode(httpErrorCode, errorBody)

    open class Socket(
        val errorBody: ErrorBodyResponse? = null,
    ) : HttpCode(900, errorBody)

    open class SocketTimeout(
        val errorBody: ErrorBodyResponse? = null,
    ) : HttpCode(901, errorBody)

    open class MalformedJson(
        val errorBody: ErrorBodyResponse? = null,
    ) : HttpCode(902, errorBody)

    open class EndOfFile(
        val errorBody: ErrorBodyResponse? = null,
    ) : HttpCode(903, errorBody)

    open class InterruptedOperation(
        val errorBody: ErrorBodyResponse? = null,
    ) : HttpCode(904, errorBody)

    open class IllegalState(
        val errorBody: ErrorBodyResponse? = null,
    ) : HttpCode(905, errorBody)

    open class JsonParsing(
        val errorBody: ErrorBodyResponse? = null,
    ) : HttpCode(906, errorBody)
}

class SimpleNetworkException(
    val httpCode: Int,
    val causeMessage: String? = null,
    rootCause: Throwable? = null,
) : Exception(causeMessage, rootCause)

class NetworkException(
    val code: HttpCode,
    val causeName: String? = null,
    val causeMessage: String? = null,
    rootCause: Throwable? = null,
    val isErrorRetryable: Boolean = false,
    val retryAfterSeconds: Int? = null,
) : Exception(causeMessage, rootCause), BBXError {

    companion object {
        fun newInstance(
            code: Int,
            errorBody: ErrorBodyResponse,
            rootCause: Throwable? = null,
            isErrorRetryable: Boolean = false,
            retryAfterSeconds: Int? = null,
            causeName: String? = null,
            causeMessage: String? = null,
        ): NetworkException {
            val httpCode = when (code) {
                400 -> HttpCode.On400(errorBody)
                401 -> HttpCode.On401(errorBody)
                403 -> HttpCode.On403(errorBody)
                404 -> HttpCode.On404(errorBody)
                408 -> HttpCode.On408(errorBody)
                412 -> HttpCode.On412(errorBody)
                429 -> HttpCode.On429(errorBody)
                500 -> HttpCode.On500(errorBody)
                501 -> HttpCode.On501(errorBody)
                502 -> HttpCode.On502(errorBody)
                503 -> HttpCode.On503(errorBody)
                504 -> HttpCode.On504(errorBody)
                999 -> HttpCode.NetworkError(errorBody)
                else -> HttpCode.Unknown(code, errorBody)
            }
            return NetworkException(
                code = httpCode,
                rootCause = rootCause,
                isErrorRetryable = isErrorRetryable,
                retryAfterSeconds = retryAfterSeconds,
                causeName = causeName,
                causeMessage = causeMessage,
            )
        }
    }

    fun isConnectionError() = cause?.isConnectionException() == true

    fun isNetworkError() = this.code is HttpCode.NetworkError

    override val errorDomain = if (code !is HttpCode.Unknown) {
        "RequestInvalidCode.${this.code.httpCode}"
    } else {
        causeName
    }

    override val errorDescription = if (code !is HttpCode.Unknown) {
        "Code: ${this.code.body?.code} Message: ${this.code.body?.message}"
    } else {
        causeMessage
    }
}

@JsonClass(generateAdapter = true)
data class ErrorBodyResponse(
    @field:Json(name = "code") val code: String? = null,
    @field:Json(name = "message") val message: String? = null,
    @field:Json(name = "localized_message") val localizedMessage: String? = null,
    @field:Json(name = "expected_action") val expectedAction: ExpectedActionResponse? = null,
    @field:Json(name = "details") val details: List<String?>? = null,
    @field:Json(name = "key") val key: String? = null,
)

@JsonClass(generateAdapter = true)
data class ExpectedActionResponse(@field:Json(name = "action") val action: String? = null)

inline fun NetworkException.is400(action: (ErrorBodyResponse) -> Unit) {
    if (this.code is HttpCode.On400) {
        action(this.code.errorBody)
    }
}

inline fun NetworkException.is401(action: (ErrorBodyResponse) -> Unit) {
    if (this.code is HttpCode.On401) {
        action(this.code.errorBody)
    }
}

inline fun NetworkException.is404(action: (ErrorBodyResponse) -> Unit) {
    if (this.code is HttpCode.On404) {
        action(this.code.errorBody)
    }
}

inline fun NetworkException.is429(action: (ErrorBodyResponse) -> Unit) {
    if (this.code is HttpCode.On429) {
        action(this.code.errorBody)
    }
}

fun NetworkException.is500(action: ((ErrorBodyResponse) -> Unit?)? = null): Boolean {
    return if (this.code is HttpCode.On500) {
        action?.invoke(this.code.errorBody)
        true
    } else false
}

fun NetworkException.is501(action: ((ErrorBodyResponse) -> Unit)? = null): Boolean {
    return if (this.code is HttpCode.On501) {
        action?.invoke(this.code.errorBody)
        true
    } else false
}

fun NetworkException.is502(action: ((ErrorBodyResponse) -> Unit)? = null): Boolean {
    return if (this.code is HttpCode.On502) {
        action?.invoke(this.code.errorBody)
        true
    } else false
}

fun NetworkException.is503(action: ((ErrorBodyResponse) -> Unit)? = null): Boolean {
    return if (this.code is HttpCode.On503) {
        action?.invoke(this.code.errorBody)
        true
    } else false
}

fun NetworkException.is504(action: ((ErrorBodyResponse) -> Unit?)? = null): Boolean {
    return if (this.code is HttpCode.On504) {
        action?.invoke(this.code.errorBody)
        true
    } else false
}

inline fun NetworkException.is403(action: (ErrorBodyResponse) -> Unit) {
    if (this.code is HttpCode.On403) {
        action(this.code.errorBody)
    }
}

inline fun NetworkException.is408(action: (ErrorBodyResponse) -> Unit) {
    if (this.code is HttpCode.On408) {
        action(this.code.errorBody)
    }
}

inline fun NetworkException.isUnknown(action: (ErrorBodyResponse) -> Unit) {
    if (this.code is HttpCode.Unknown) {
        action(this.code.errorBody)
    }
}

inline fun NetworkException.NetworkError(action: (ErrorBodyResponse?) -> Unit) {
    if (this.code is HttpCode.NetworkError) {
        action(this.code.errorBody)
    }
}

fun NetworkException.toResultNetworkError(): ResultError.NetworkError {
    return ResultError.NetworkError(
        httpCode = code.httpCode,
        serverCode = code.body?.code,
        serverMessage = code.body?.message,
        exceptionTitle = causeName,
        exceptionMessage = causeMessage,
        localizedMessage = code.body?.localizedMessage,
        isConnectionError = isConnectionError(),
    )
}

fun ResultError.NetworkError.toNetworkException(): NetworkException =
    NetworkException.newInstance(
        code = httpCode,
        errorBody = ErrorBodyResponse(
            code = serverCode,
            message = serverMessage,
            localizedMessage = localizedMessage,
        ),
        causeName = exceptionTitle,
        causeMessage = exceptionMessage,
    )

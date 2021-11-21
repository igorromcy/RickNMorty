package com.romcy.location.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.romcy.core.ricknmorty.base.exhaustive
import com.romcy.core.ricknmorty.dependencies.module.FragmentKey
import com.romcy.location.presentation.adapter.LocationAdapter
import com.romcy.core.ricknmorty.dependencies.Injectable
import com.romcy.location.R
import com.romcy.location.databinding.LocationFragmentBinding
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

@ContributesMultibinding(AppScope::class, Fragment::class)
@FragmentKey(LocationFragment::class)
class LocationFragment @Inject constructor(
    private val viewModelProviderFactory: ViewModelProvider.Factory,
) : Fragment(R.layout.location_fragment), Injectable {

    private val binding by viewBinding(LocationFragmentBinding::bind)
    private val viewModel by viewModels<LocationViewModel> { viewModelProviderFactory }
    private val locationAdapter: LocationAdapter by lazy { LocationAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.list.adapter = locationAdapter
    }

    private fun observeState() {
        with(viewModel.viewState) {
            observePageState()
            observeListState()
        }
    }

    private fun LocationViewState.observeListState() {
        locations.observe(viewLifecycleOwner) {
            locationAdapter.submitList(it)
            locationAdapter.notifyDataSetChanged()
        }
    }

    private fun LocationViewState.observePageState() {
        pageState.observe(viewLifecycleOwner) { pageState ->
            when (pageState) {
                LocationPageState.Content -> {
                    binding.list.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                LocationPageState.ContentRefreshing -> {
                    binding.list.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                LocationPageState.Empty -> {
                    binding.list.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                LocationPageState.Init -> {
                }
            }.exhaustive
        }
    }
}
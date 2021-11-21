package com.romcy.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.romcy.characters.impl.R
import com.romcy.characters.impl.databinding.CharactersFragmentBinding
import com.romcy.core.ricknmorty.base.exhaustive
import com.romcy.core.ricknmorty.dependencies.Injectable
import com.romcy.core.ricknmorty.dependencies.module.FragmentKey
import com.romcy.characters.presentation.adapter.CharactersAdapter
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

@ContributesMultibinding(AppScope::class, Fragment::class)
@FragmentKey(CharactersFragment::class)
class CharactersFragment @Inject constructor(
    private val viewModelProviderFactory: ViewModelProvider.Factory,
) : Fragment(R.layout.characters_fragment), Injectable {

    private val binding by viewBinding(CharactersFragmentBinding::bind)
    private val viewModel by viewModels<CharactersViewModel> { viewModelProviderFactory }
    private val charactersAdapter: CharactersAdapter by lazy { CharactersAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.list.adapter = charactersAdapter
    }

    private fun observeState() {
        with(viewModel.viewState) {
            observePageState()
            observeListState()
        }
    }

    private fun CharactersViewState.observeListState() {
        characters.observe(viewLifecycleOwner) {
            charactersAdapter.submitList(it)
            charactersAdapter.notifyDataSetChanged()
        }
    }

    private fun CharactersViewState.observePageState() {
        pageState.observe(viewLifecycleOwner) { pageState ->
            when (pageState) {
                CharactersPageState.Content -> {
                    binding.list.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                CharactersPageState.ContentRefreshing -> {
                    binding.list.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                CharactersPageState.Empty -> {
                    binding.list.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
                CharactersPageState.Init -> {
                }
            }.exhaustive
        }
    }

}
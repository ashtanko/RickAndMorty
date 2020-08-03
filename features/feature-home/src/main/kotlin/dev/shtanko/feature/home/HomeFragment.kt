package dev.shtanko.feature.home

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.shtanko.common.ui.base.BaseViewModelFragment
import dev.shtanko.common.ui.extensions.observe
import dev.shtanko.core.App
import dev.shtanko.core.navigation.AppNavigation
import dev.shtanko.feature.home.adapter.CharacterAdapter
import dev.shtanko.feature.home.adapter.CharactersListAdapterState
import dev.shtanko.feature.home.databinding.FragmentHomeBinding
import dev.shtanko.feature.home.di.HomeComponent
import dev.shtanko.feature.home.model.CharacterModel
import timber.log.Timber
import javax.inject.Inject

class HomeFragment :
    BaseViewModelFragment<FragmentHomeBinding, HomeViewModel>(layoutId = R.layout.fragment_home) {

    @Inject
    lateinit var navigation: AppNavigation

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.observeFilters()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.data, ::onViewDataChange)
        observe(viewModel.event, ::onViewEvent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filter -> {
                navigation.fromHomeToFilter()
            }
        }
        return true
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.contentCharactersList.apply {
            adapter = characterAdapter
        }
    }

    override fun inject() {
        HomeComponent.Initializer.init(
            (requireContext().applicationContext as App).getAppComponent(),
            this
        ).inject(this)
    }

    private fun onViewDataChange(viewData: PagedList<CharacterModel>) {
        characterAdapter.submitList(viewData)
        with(viewBinding.contentCharactersList.charactersRecyclerView) {
            val layoutManager =
                (layoutManager as LinearLayoutManager)
            val position = layoutManager.findFirstCompletelyVisibleItemPosition()
            if (position != RecyclerView.NO_POSITION) {
                scrollToPosition(position)
            }
        }
    }

    private fun onViewStateChange(viewState: HomeViewState) {
        when (viewState) {
            is HomeViewState.Loaded ->
                characterAdapter.submitState(CharactersListAdapterState.Added)
            is HomeViewState.AddLoading ->
                characterAdapter.submitState(CharactersListAdapterState.AddLoading)
            is HomeViewState.AddError ->
                characterAdapter.submitState(CharactersListAdapterState.AddError)
            is HomeViewState.NoMoreElements ->
                characterAdapter.submitState(CharactersListAdapterState.NoMore)
        }
    }

    private fun onViewEvent(viewEvent: HomeViewEvent) {
        when (viewEvent) {
            is HomeViewEvent.OpenCharacterDetail -> {
                navigation.fromHomeToDetails(viewEvent.id, viewEvent.title)
            }
        }
    }
}

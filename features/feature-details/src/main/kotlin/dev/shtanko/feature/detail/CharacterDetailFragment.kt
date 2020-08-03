package dev.shtanko.feature.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import dev.shtanko.common.ui.base.BaseViewModelFragment
import dev.shtanko.common.ui.extensions.observe
import dev.shtanko.common.ui.views.ProgressBarDialog
import dev.shtanko.core.App
import dev.shtanko.core.navigation.AppNavigation
import dev.shtanko.feature.detail.databinding.FragmentCharacterDetailBinding
import dev.shtanko.feature.detail.di.component.CharacterDetailComponent
import javax.inject.Inject

class CharacterDetailFragment :
    BaseViewModelFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(
        layoutId = R.layout.fragment_character_detail
    ) {

    @Inject
    lateinit var navigation: AppNavigation

    @Inject
    lateinit var progressDialog: ProgressBarDialog

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        viewModel.loadCharacterDetail(args.id)
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
    }

    override fun inject() {
        CharacterDetailComponent.Initializer
            .init((requireContext().applicationContext as App).getAppComponent(), this)
            .inject(this)
    }

    private fun onViewStateChange(viewState: CharacterDetailViewState) {
        when (viewState) {
            is CharacterDetailViewState.Loading -> {
                progressDialog.show(R.string.character_detail_dialog_loading_text)
            }
            is CharacterDetailViewState.Loaded -> {
                progressDialog.dismiss()
            }
            is CharacterDetailViewState.Error -> {
                progressDialog.dismissWithErrorMessage(R.string.character_detail_dialog_error_text)
            }
            is CharacterDetailViewState.Dismiss -> {
                navigation.navigateBack()
            }
        }
    }
}

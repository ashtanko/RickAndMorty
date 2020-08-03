package dev.shtanko.feature.filter

import dev.shtanko.common.ui.viewmodel.ViewCommand

sealed class FilterCommand : ViewCommand {
    object NavigateBack : FilterCommand()
}

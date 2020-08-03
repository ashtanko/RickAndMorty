package dev.shtanko.feature.filter.di.component

import dagger.Component
import dev.shtanko.common.ui.di.ViewModelModule
import dev.shtanko.core.di.ApplicationProvider
import dev.shtanko.feature.filter.FilterFragment
import dev.shtanko.feature.filter.di.modules.FilterModule
import dev.shtanko.feature.filter.di.scope.FilterScope

@Component(
    dependencies = [
        ApplicationProvider::class
    ],
    modules = [ViewModelModule::class, FilterModule::class]
)
@FilterScope
interface FilterComponent {

    fun inject(fragment: FilterFragment)

    class Initializer private constructor() {
        companion object {
            fun init(
                applicationProvider: ApplicationProvider,
                fragment: FilterFragment
            ): FilterComponent {
                return DaggerFilterComponent.builder().filterModule(FilterModule(fragment))
                    .applicationProvider(applicationProvider).build()
            }
        }
    }
}

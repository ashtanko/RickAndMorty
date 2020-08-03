package dev.shtanko.feature.home.di

import dagger.Component
import dev.shtanko.common.ui.di.ViewModelModule
import dev.shtanko.core.di.ApplicationProvider
import dev.shtanko.core.di.scope.FragmentScope
import dev.shtanko.feature.home.HomeFragment

@FragmentScope
@Component(
    dependencies = [ApplicationProvider::class],
    modules = [ViewModelModule::class, HomeModule::class]
)
interface HomeComponent {

    fun inject(fragment: HomeFragment)

    class Initializer private constructor() {
        companion object {
            fun init(
                applicationProvider: ApplicationProvider,
                homeFragment: HomeFragment
            ): HomeComponent {
                return DaggerHomeComponent.builder().applicationProvider(applicationProvider)
                    .homeModule(
                        HomeModule(homeFragment)
                    )
                    .build()
            }
        }
    }
}

package dev.shtanko.feature.detail.di.component

import dagger.Component
import dev.shtanko.core.di.ApplicationProvider
import dev.shtanko.core.di.scope.FragmentScope
import dev.shtanko.feature.detail.CharacterDetailFragment
import dev.shtanko.feature.detail.di.modules.CharacterDetailViewModule

@FragmentScope
@Component(
    dependencies = [ApplicationProvider::class],
    modules = [CharacterDetailViewModule::class]
)
interface CharacterDetailComponent {
    fun inject(fragment: CharacterDetailFragment)

    class Initializer private constructor() {
        companion object {
            fun init(
                applicationProvider: ApplicationProvider,
                fragment: CharacterDetailFragment
            ): CharacterDetailComponent {
                return DaggerCharacterDetailComponent.builder()
                    .applicationProvider(applicationProvider).characterDetailViewModule(
                    CharacterDetailViewModule(fragment)
                ).build()
            }
        }
    }
}

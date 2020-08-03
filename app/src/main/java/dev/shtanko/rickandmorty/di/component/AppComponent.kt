package dev.shtanko.rickandmorty.di.component

import dagger.Component
import dev.shtanko.core.di.ApplicationProvider
import dev.shtanko.core.di.ToolsProvider
import dev.shtanko.core.di.UseCaseProvider
import dev.shtanko.data.di.component.UseCaseComponent
import dev.shtanko.rickandmorty.MainActivity
import dev.shtanko.rickandmorty.RickAndMorty
import javax.inject.Singleton

@Component(
    dependencies = [
        ToolsProvider::class, UseCaseProvider::class
    ]
)
@Singleton
interface AppComponent : ApplicationProvider {

    fun inject(activity: MainActivity)

    class Initializer private constructor() {
        companion object {
            fun init(app: RickAndMorty): AppComponent {
                val toolsProvider = ToolsComponent.Initializer.init(app)
                val useCaseProvider = UseCaseComponent.Initializer.init(toolsProvider)
                return DaggerAppComponent.builder()
                    .toolsProvider(toolsProvider)
                    .useCaseProvider(useCaseProvider)
                    .build()
            }
        }
    }
}

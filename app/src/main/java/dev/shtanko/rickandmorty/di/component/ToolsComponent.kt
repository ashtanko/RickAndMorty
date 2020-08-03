package dev.shtanko.rickandmorty.di.component

import dagger.BindsInstance
import dagger.Component
import dev.shtanko.core.App
import dev.shtanko.core.di.ToolsProvider
import dev.shtanko.rickandmorty.di.module.NavigationModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class]
)
interface ToolsComponent : ToolsProvider {

    @Component.Builder
    interface Builder {
        fun build(): ToolsComponent

        @BindsInstance
        fun app(app: App): Builder
    }

    class Initializer private constructor() {
        companion object {
            fun init(app: App): ToolsProvider =
                DaggerToolsComponent.builder()
                    .app(app)
                    .build()
        }
    }
}

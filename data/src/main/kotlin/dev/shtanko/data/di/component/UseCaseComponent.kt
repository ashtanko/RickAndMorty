package dev.shtanko.data.di.component

import dagger.Component
import dev.shtanko.core.di.ToolsProvider
import dev.shtanko.core.di.UseCaseProvider
import dev.shtanko.data.di.modules.DataModule
import dev.shtanko.data.di.modules.DataSourceModule
import dev.shtanko.data.di.modules.RepositoryModule
import dev.shtanko.data.di.modules.UseCaseModule
import dev.shtanko.network.di.ApiProvider
import dev.shtanko.network.di.component.NetworkComponent
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ApiProvider::class, ToolsProvider::class],
    modules = [
        UseCaseModule::class,
        RepositoryModule::class,
        DataSourceModule::class,
        DataModule::class
    ]
)
interface UseCaseComponent : UseCaseProvider {
    class Initializer private constructor() {
        companion object {
            fun init(toolsProvider: ToolsProvider): UseCaseProvider {
                val networkComponent = NetworkComponent.Initializer.init(
                    toolsProvider.provideContext().getApplicationContext()
                )
                return DaggerUseCaseComponent.builder()
                    .apiProvider(networkComponent)
                    .toolsProvider(
                        toolsProvider
                    ).build()
            }
        }
    }
}

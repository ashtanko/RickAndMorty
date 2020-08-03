package dev.shtanko.network.di.component

import android.content.Context
import dagger.Component
import dev.shtanko.network.di.ApiProvider
import dev.shtanko.network.di.modules.ApiModule
import dev.shtanko.network.di.modules.NetworkModule
import dev.shtanko.network.di.modules.ServiceModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, ServiceModule::class, ApiModule::class]
)
interface NetworkComponent : ApiProvider {
    class Initializer private constructor() {
        companion object {
            fun init(context: Context): ApiProvider {
                return DaggerNetworkComponent.builder().apiModule(ApiModule(context))
                    .networkModule(NetworkModule(context)).serviceModule(ServiceModule()).build()
            }
        }
    }
}

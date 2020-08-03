package dev.shtanko.network.di.modules

import dagger.Module
import dagger.multibindings.Multibinds
import dev.shtanko.network.di.qualifier.NetworkInterceptor
import okhttp3.Interceptor

@Module
abstract class InterceptorModule {
    @NetworkInterceptor
    @Multibinds
    internal abstract fun provideNetworkInterceptors(): Set<Interceptor>

    @Multibinds
    internal abstract fun provideInterceptors(): Set<Interceptor>
}

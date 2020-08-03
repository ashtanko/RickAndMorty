package dev.shtanko.network.interceptor

import android.content.Context
import dev.shtanko.network.extensions.isOnline
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ForceCacheInterceptor @Inject constructor(private val context: Context) : Interceptor {

    companion object {
        private const val CACHE_MAX_AGE_MINUTES = 15
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_MAX_AGE_MINUTES, TimeUnit.MINUTES)
            .build()

        val builder = chain.request().newBuilder()

        builder.cacheControl(cacheControl)

        if (!context.isOnline()) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }

        return chain.proceed(builder.build())
    }
}

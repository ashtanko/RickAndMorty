package dev.shtanko.network.di.modules

import android.content.Context
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.IntoSet
import dev.shtanko.network.BuildConfig
import dev.shtanko.network.di.qualifier.NetworkInterceptor
import dev.shtanko.network.extensions.delegatingCallFactory
import dev.shtanko.network.interceptor.ForceCacheInterceptor
import dev.shtanko.network.interceptor.RetryInterceptor
import dev.shtanko.network.interceptor.getHttpLoggingInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [InterceptorModule::class])
class NetworkModule(private val context: Context) {

    companion object {
        private const val HTTP_LOG_TAG = "OkHttp"
        private const val KB = 1024L
        private const val MB = KB * 1024L
        private const val GSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val CACHE_SIZE: Long = 50L * MB // 50 MiB
        private const val CONNECTION_TIMEOUT = 120L
        private const val READ_TIMEOUT = 60L
        private const val WRITE_TIMEOUT = 60L
    }

    /**
     * [HttpLoggingInterceptor] provider
     */
    @Provides
    @Singleton
    fun provideLoggingLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    /**
     *
     */
    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    internal fun provideLoggingInterceptor(level: HttpLoggingInterceptor.Level): Interceptor =
        getHttpLoggingInterceptor(
            level
        ) { message ->
            Timber.tag(HTTP_LOG_TAG).v(message)
        }

    /**
     * Gson provider
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setPrettyPrinting()
            .setDateFormat(GSON_DATE_FORMAT)
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideConvertersFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideCacheDir(): File {
        return File(context.applicationContext.cacheDir, "httpCache")
    }

    @Provides
    @Singleton
    fun provideCache(cacheDir: File): Cache {

        assertHttpCallThread()

        return Cache(
            cacheDir,
            CACHE_SIZE
        )
    }

    @Provides
    @Reusable
    @IntoSet
    fun provideForceCacheInterceptor(): Interceptor =
        ForceCacheInterceptor(context)

    @Provides
    @Reusable
    @IntoSet
    fun provideRetryInterceptor(): Interceptor =
        RetryInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        interceptors: Set<@JvmSuppressWildcards Interceptor>,
        @NetworkInterceptor networkInterceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {

        assertHttpCallThread()

        val builder = OkHttpClient().newBuilder()

        builder
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .cache(cache)
            .retryOnConnectionFailure(true)

        builder.networkInterceptors()
            .addAll(networkInterceptors)

        builder.interceptors()
            .addAll(interceptors)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        client: Lazy<OkHttpClient>,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .delegatingCallFactory(client)

    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder
            .baseUrl(BuildConfig.RICK_AND_MORTY_API_BASE_URL)
            .build()
    }

    private fun assertHttpCallThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw IllegalStateException("HTTP client initialized on main thread.")
        }
    }
}

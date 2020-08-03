package dev.shtanko.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor

inline fun getHttpLoggingInterceptor(
    level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE,
    crossinline logger: (String) -> Unit
): HttpLoggingInterceptor {
    return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            logger(message)
        }
    }).also { it.level = level }
}

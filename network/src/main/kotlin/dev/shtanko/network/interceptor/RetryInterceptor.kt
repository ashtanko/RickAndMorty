package dev.shtanko.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class RetryInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val MAX_RETRY_COUNT = 10
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        var response = chain.proceed(request)

        var tryCount = 0

        while (!response.isSuccessful && tryCount < MAX_RETRY_COUNT) {
            tryCount++
            response = chain.proceed(request)
        }

        return response
    }
}

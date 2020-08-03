package dev.shtanko.network.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

@SuppressLint("MissingPermission") // warn network permission provided in higher level
@JvmName("isOnline")
@Suppress("DEPRECATION")
fun Context.isOnline(): Boolean {
    return try {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected ?: false
    } catch (expected: Exception) {
        expected.printStackTrace()
        false
    }
}

package dev.shtanko.core

import android.content.Context
import dev.shtanko.core.di.ApplicationProvider

interface App {
    fun getApplicationContext(): Context

    fun getAppComponent(): ApplicationProvider
}

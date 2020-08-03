package dev.shtanko.rickandmorty

import android.app.Application
import dev.shtanko.core.App
import dev.shtanko.core.di.ApplicationProvider
import dev.shtanko.rickandmorty.di.component.AppComponent
import timber.log.Timber

class RickAndMorty : Application(), App {

    private val appComponent: AppComponent by lazy {
        AppComponent.Initializer.init(this@RickAndMorty)
    }

    companion object {
        lateinit var applicationComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initDI()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDI() {
        applicationComponent = appComponent
    }

    override fun getAppComponent(): ApplicationProvider {
        return appComponent
    }
}

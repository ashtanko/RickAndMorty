package dev.shtanko.network.di

import com.google.gson.Gson
import dev.shtanko.network.api.CharactersApi

interface ApiProvider {

    fun provideCharactersApi(): CharactersApi

    fun provideGson(): Gson
}

import Dependencies.CORE_KTX
import Dependencies.GSON
import Dependencies.OKHTTP
import Dependencies.OKHTTP_LOGGING_INTERCEPTOR
import Dependencies.RETROFIT
import Dependencies.RETROFIT_CONVERTER
import Dependencies.RETROFIT_RX_JAVA_ADAPTER
import Dependencies.ROOM_COMPILER
import Dependencies.RX_ANDROID
import Dependencies.RX_JAVA
import extensions.buildConfigStringField

plugins {
    id("common.android-library")
}

android {
    buildTypes.forEach {
        it.buildConfigStringField(
            "RICK_AND_MORTY_API_BASE_URL",
            "https://rickandmortyapi.com/api/"
        )
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(CORE_KTX)
    implementation(RETROFIT)
    implementation(OKHTTP)
    implementation(OKHTTP_LOGGING_INTERCEPTOR)
    implementation(RETROFIT_CONVERTER)
    implementation(RETROFIT_RX_JAVA_ADAPTER)
    implementation(GSON)
    implementation(RX_JAVA)
    implementation(RX_ANDROID)

    kapt(ROOM_COMPILER)
}

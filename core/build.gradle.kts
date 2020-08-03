import Dependencies.CORE_KTX
import Dependencies.GSON
import Dependencies.LIFECYCLE_EXTENSIONS
import Dependencies.NAVIGATION_FRAGMENT
import Dependencies.OKHTTP
import Dependencies.OKHTTP_LOGGING_INTERCEPTOR
import Dependencies.RETROFIT
import Dependencies.RETROFIT_CONVERTER
import Dependencies.RETROFIT_RX_JAVA_ADAPTER
import Dependencies.ROOM_COMPILER
import Dependencies.ROOM_KTX
import Dependencies.ROOM_RX_JAVA
import Dependencies.RX_ANDROID
import Dependencies.RX_JAVA

plugins {
    id("common.android-library")
}

dependencies {
    api(project(":domain"))
    implementation(ROOM_KTX)
    implementation(ROOM_RX_JAVA)
    implementation(LIFECYCLE_EXTENSIONS)
    implementation(CORE_KTX)
    implementation(RETROFIT)
    implementation(OKHTTP)
    implementation(NAVIGATION_FRAGMENT)
    implementation(OKHTTP_LOGGING_INTERCEPTOR)
    implementation(RETROFIT_CONVERTER)
    implementation(RETROFIT_RX_JAVA_ADAPTER)
    implementation(GSON)
    implementation(RX_JAVA)
    implementation(RX_ANDROID)

    kapt(ROOM_COMPILER)
}

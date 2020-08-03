import Dependencies.CORE_KTX
import Dependencies.DAGGER
import Dependencies.GLIDE
import Dependencies.GLIDE_COMPILER
import Dependencies.LIFECYCLE_EXTENSIONS
import Dependencies.MATERIAL
import Dependencies.NAVIGATION_FRAGMENT
import Dependencies.PAGING
import Dependencies.RECYCLE_VIEW
import Dependencies.RX_ANDROID
import Dependencies.RX_JAVA

plugins {
    id("common.android-library")
}

dependencies {
    implementation(LIFECYCLE_EXTENSIONS)
    implementation(project(":domain"))
    implementation(CORE_KTX)
    api(MATERIAL)
    implementation(RECYCLE_VIEW)
    implementation(NAVIGATION_FRAGMENT)
    implementation(RX_JAVA)
    implementation(RX_ANDROID)
    implementation(DAGGER)
    implementation(PAGING)
    api(GLIDE)
    kapt(GLIDE_COMPILER)
}

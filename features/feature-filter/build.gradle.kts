import Dependencies.CORE_KTX
import Dependencies.FRAGMENT_KTX
import Dependencies.LIFECYCLE_EXTENSIONS
import Dependencies.MATERIAL
import Dependencies.RX_ANDROID
import Dependencies.RX_JAVA

plugins {
    id("common.android-library")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":common:ui"))
    implementation(LIFECYCLE_EXTENSIONS)
    implementation(FRAGMENT_KTX)
    implementation(CORE_KTX)
    implementation(MATERIAL)
    implementation(RX_JAVA)
    implementation(RX_ANDROID)
}

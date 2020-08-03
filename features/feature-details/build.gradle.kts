import Dependencies.CORE_KTX
import Dependencies.LIFECYCLE_EXTENSIONS
import Dependencies.MATERIAL
import Dependencies.RX_JAVA

plugins {
    id("common.android-library")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":common:ui"))
    implementation(LIFECYCLE_EXTENSIONS)
    implementation(CORE_KTX)
    implementation(RX_JAVA)
    implementation(MATERIAL)
}

import Dependencies.CORE_KTX
import Dependencies.LIFECYCLE_EXTENSIONS
import Dependencies.LIVEDATA_KTX
import Dependencies.PAGING
import Dependencies.PAGING_RX
import Dependencies.RECYCLE_VIEW
import Dependencies.RX_ANDROID
import Dependencies.RX_JAVA
import Dependencies.SWIPE_REFRESH_LAYOUT

plugins {
    id("common.android-library")
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":common:ui"))
    implementation(LIFECYCLE_EXTENSIONS)
    implementation(LIVEDATA_KTX)
    implementation(CORE_KTX)
    implementation(RECYCLE_VIEW)
    implementation(RX_JAVA)
    implementation(RX_ANDROID)
    implementation(PAGING)
    implementation(PAGING_RX)
    implementation(SWIPE_REFRESH_LAYOUT)
}

import Dependencies.GSON
import Dependencies.RX_ANDROID
import Dependencies.RX_JAVA

plugins {
    id("common.android-library")
}


dependencies {
    implementation(project(":network"))
    api(project(":domain"))
    implementation(project(":core"))
    implementation(GSON)
    implementation(RX_JAVA)
    implementation(RX_ANDROID)
}

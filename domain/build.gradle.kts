import Dependencies.RX_JAVA
import TestDependencies.ASSERTJ
import TestDependencies.JUNIT
import TestDependencies.MOCKITO

plugins {
    id("common.kotlin-library")
}

dependencies {
    implementation(RX_JAVA)
    //implementation(RX_ANDROID)
    //implementation(DAGGER)

    testImplementation(JUNIT)
    testImplementation(MOCKITO)
    testImplementation(ASSERTJ)
}

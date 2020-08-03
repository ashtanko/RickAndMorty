package common

import AndroidBuildConfig
import Dependencies.CONSTRAIN_LAYOUT
import Dependencies.DAGGER
import Dependencies.DAGGER_COMPILER
import Dependencies.KOTLIN
import Dependencies.NAVIGATION_FRAGMENT
import Dependencies.NAVIGATION_UI
import Dependencies.TIMBER
import extensions.addTestsDependencies
import extensions.implementation
import extensions.kapt

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(AndroidBuildConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AndroidBuildConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidBuildConfig.TARGET_SDK_VERSION)

        testInstrumentationRunner = AndroidBuildConfig.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = AndroidBuildConfig.DATA_BINDING
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    lintOptions {
        lintConfig = rootProject.file("config/lint-config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(KOTLIN)
    implementation(DAGGER)
    implementation(TIMBER)
    implementation(CONSTRAIN_LAYOUT)
    implementation(NAVIGATION_UI)
    implementation(NAVIGATION_FRAGMENT)

    kapt(DAGGER_COMPILER)
    addTestsDependencies()
}

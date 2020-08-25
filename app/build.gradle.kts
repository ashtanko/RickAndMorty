import AndroidBuildConfig.BUILD_TOOLS_VERSION
import AndroidBuildConfig.COMPILE_SDK_VERSION
import AndroidBuildConfig.MIN_SDK_VERSION
import AndroidBuildConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
import AndroidBuildConfig.TARGET_SDK_VERSION
import AndroidBuildConfig.TEST_INSTRUMENTATION_RUNNER
import AndroidBuildConfig.VERSION_CODE
import AndroidBuildConfig.VERSION_NAME
import Dependencies.APPCOMPAT
import Dependencies.CONSTRAIN_LAYOUT
import Dependencies.DAGGER
import Dependencies.DAGGER_COMPILER
import Dependencies.KOTLIN
import Dependencies.MATERIAL
import Dependencies.NAVIGATION_FRAGMENT
import Dependencies.NAVIGATION_UI
import Dependencies.TIMBER
import extensions.addTestsDependencies
import extensions.getLocalProperty

plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_NAVIGATION_SAFE_ARGS)
}

android {
    compileSdkVersion(COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = "dev.shtanko.rickandmorty"
        buildToolsVersion(BUILD_TOOLS_VERSION)
        targetSdkVersion(TARGET_SDK_VERSION)
        minSdkVersion(MIN_SDK_VERSION)
        versionCode = VERSION_CODE
        versionName = VERSION_NAME

        vectorDrawables.useSupportLibrary = SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
    }

    signingConfigs {
        create(BuildType.RELEASE) {
            keyAlias = getLocalProperty("signing.key.alias")
            keyPassword = getLocalProperty("signing.key.password")
            storeFile = file(getLocalProperty("signing.store.file"))
            storePassword = getLocalProperty("signing.store.password")
        }
    }

    buildFeatures {
        dataBinding = AndroidBuildConfig.DATA_BINDING
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            signingConfig = signingConfigs.getByName(name)

            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lintOptions {
        isAbortOnError = false
    }
}

dependencies {
    implementation(KOTLIN)
    implementation(APPCOMPAT)
    implementation(MATERIAL)
    implementation(CONSTRAIN_LAYOUT)
    implementation(NAVIGATION_FRAGMENT)
    implementation(NAVIGATION_UI)
    implementation(TIMBER)
    implementation(DAGGER)
    kapt(DAGGER_COMPILER)

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":features:feature-filter"))
    implementation(project(":features:feature-home"))
    implementation(project(":features:feature-details"))

    addTestsDependencies()
}

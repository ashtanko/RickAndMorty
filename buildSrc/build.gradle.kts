plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.android.tools.build:gradle:4.0.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.10.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-formatting:1.10.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
    implementation("com.pinterest:ktlint:0.36.0")
}

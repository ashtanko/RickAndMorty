import Dependencies.LINT_API
import Dependencies.LINT_CHECKS

plugins {
    id("common.android-library")
}

dependencies {
    compileOnly(LINT_API)
    compileOnly(LINT_CHECKS)
}

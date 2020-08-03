package plugin

import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

apply<DetektPlugin>()

configure<DetektExtension> {
    input = project.files("src/main/kotlin")
    config = files("$rootDir/config/detekt.yml")
    reports {
        xml {
            enabled = true
            destination = project.file("build/reports/detekt/report.xml")
        }
        html {
            enabled = true
            destination = project.file("build/reports/detekt/report.html")
        }
    }
}

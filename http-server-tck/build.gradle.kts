import org.checkerframework.gradle.plugin.CheckerFrameworkExtension

plugins {
    id("io.micronaut.build.internal.convention-library")
    id("org.checkerframework")
}

repositories {
    mavenLocal()
}

configure<CheckerFrameworkExtension> {
    checkers = listOf(
        "org.checkerframework.checker.tainting.TaintingChecker",
    )
    skipCheckerFramework = true
}
dependencies {
    annotationProcessor(projects.injectJava)

    annotationProcessor(platform(libs.test.boms.micronaut.validation))
    annotationProcessor(libs.micronaut.validation.processor) {
        exclude(group = "io.micronaut")
    }
    annotationProcessor(projects.httpValidation)

    compileOnly(platform(libs.test.boms.micronaut.validation))
    compileOnly(libs.micronaut.validation) {
        exclude(group = "io.micronaut")
    }
    implementation(projects.runtime)
    implementation(projects.jacksonDatabind)
    implementation(projects.inject)
    implementation(projects.management)
    add("compileOnly", "org.checkerframework:checker-qual:3.37.1-SNAPSHOT")
    add("checkerFramework", "org.checkerframework:checker:3.37.1-SNAPSHOT")
    api(projects.httpTck)
    api(projects.httpServer)
    api(projects.httpClientCore)
    api(libs.junit.jupiter.api)
    api(libs.junit.jupiter.params)
    api(libs.managed.reactor)
}
micronautBuild {
    binaryCompatibility {
        enabled.set(false)
    }
}

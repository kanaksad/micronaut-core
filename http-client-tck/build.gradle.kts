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
    annotationProcessor(project(":inject-java"))
    api(libs.junit.jupiter)
    api(projects.httpTck)
    implementation(libs.managed.reactor)
    add("compileOnly", "org.checkerframework:checker-qual:3.37.1-SNAPSHOT")
    add("checkerFramework", "org.checkerframework:checker:3.37.1-SNAPSHOT")
    implementation(project(":context"))
    implementation(project(":http-server-netty"))
    implementation(project(":http-client-core"))
}
tasks.named<Test>("test") {
    useJUnitPlatform()
}

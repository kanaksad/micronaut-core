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

micronautBuild {
    core {
        usesMicronautTestSpock()
    }
}

dependencies {
    annotationProcessor(projects.injectJava)
    api(projects.httpClientCore)
    compileOnly(projects.httpClient)
    implementation(libs.managed.reactor)
    add("compileOnly", "org.checkerframework:checker-qual:3.37.1-SNAPSHOT")
    add("checkerFramework", "org.checkerframework:checker:3.37.1-SNAPSHOT")
    testImplementation(projects.jacksonDatabind)
    testImplementation(projects.httpServerNetty)
    testImplementation(libs.bcpkix)
    testImplementation(libs.testcontainers.spock)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    // systemProperty("jdk.httpclient.HttpClient.log", "all") // Uncomment to enable logging
}

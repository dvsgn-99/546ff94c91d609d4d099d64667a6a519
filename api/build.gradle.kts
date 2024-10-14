import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks {
    named<BootJar>("bootJar") {
        enabled = true
    }
    named<Jar>("jar") {
        enabled = false
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":infra"))

    implementation(Dependencies.SPRING_BOOT_WEBFLUX)
    implementation(Dependencies.KOTLIN_LIB)
}
object Versions {
    const val KOTLIN_VERSION = "2.0.20"
    const val KSP_VERSION = "2.0.20-1.0.24"
    const val SPRINGBOOT_VERSION = "3.1.4"
    const val SPRING_DEPENDENCY_MANAGEMENT_VERSION = "1.0.15.RELEASE"
}

object Dependencies {
    // spring
    const val SPRING_BOOT = "org.springframework.boot"
    const val SPRING_BOOT_WEBFLUX = "org.springframework.boot:spring-boot-starter-webflux"
    const val SPRING_TX = "org.springframework:spring-tx"
    const val SPRING_BOOT_AOP = "org.springframework.boot:spring-boot-starter-aop"
    const val SPRING_DEPENDENCY_MANAGEMENT = "io.spring.dependency-management"
    const val SPRING_BOOT_DATA_MONGO = "org.springframework.boot:spring-boot-starter-data-mongodb"

    // kotlin
    const val KOTLIN_PLUGIN_SPRING = "org.jetbrains.kotlin.plugin.spring"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
    const val KOTLIN_LIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.20"
    const val KSP = "com.google.devtools.ksp"
    const val KOTLIN_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
    const val KOTLIN_COROUTINES_REACTOR = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor"

    // objectMapper
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2"

    // apache commons-text
    const val APACHE_COMMONS_TEXT = "org.apache.commons:commons-text:1.10.0"

    // test
    const val KOTEST_RUNNER = "io.kotest:kotest-runner-junit5:5.6.2"
    const val MOCKK = "io.mockk:mockk:1.9.3"
    const val SPRING_STARTER_TEST = "org.springframework.boot:spring-boot-starter-test"
    const val KOTEST_ASSERTIONS = "io.kotest:kotest-assertions-core:5.6.2"
    const val KOTEST_EXTENSIONS = "io.kotest.extensions:kotest-extensions-spring:1.1.3"
}
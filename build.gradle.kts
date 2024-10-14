import org.springframework.boot.gradle.tasks.bundling.BootJar

val javaVersion = JavaVersion.VERSION_17.toString()

plugins {
    base
    kotlin("jvm") version Versions.KOTLIN_VERSION
    kotlin("plugin.spring") version Versions.KOTLIN_VERSION
    id(Dependencies.KSP) version Versions.KSP_VERSION
    id(Dependencies.SPRING_BOOT) version Versions.SPRINGBOOT_VERSION apply false
    id(Dependencies.SPRING_DEPENDENCY_MANAGEMENT) version Versions.SPRING_DEPENDENCY_MANAGEMENT_VERSION apply false
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }
}


//tasks.getByName("bootJar") {
//    enabled = false
//}

//tasks.getByName("jar") {
//    enabled = true
//}

//tasks.jar {
//    archiveFileName.set("app.jar")
//}

allprojects {
    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "me.dvsgn"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = Dependencies.SPRING_BOOT)
    apply(plugin = Dependencies.SPRING_DEPENDENCY_MANAGEMENT)
    apply(plugin = Dependencies.KOTLIN_JVM)
    apply(plugin = Dependencies.KOTLIN_PLUGIN_SPRING)
    apply(plugin = Dependencies.KSP)

    kotlin {
        jvmToolchain(17)
        compilerOptions {
            apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
        }
    }


    tasks.getByName<Jar>("jar") {
        enabled = true
    }

    tasks.getByName<BootJar>("bootJar") {
        enabled = false
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")

        implementation(Dependencies.SPRING_BOOT_AOP)
        implementation(Dependencies.JACKSON)
        implementation(Dependencies.KOTLIN_REFLECT)
        implementation(Dependencies.KOTLIN_COROUTINES)
        implementation(Dependencies.KOTLIN_COROUTINES_REACTOR)

        //test
        testImplementation(Dependencies.MOCKK)
        testImplementation(Dependencies.KOTEST_RUNNER)
        testImplementation(Dependencies.KOTEST_ASSERTIONS)
        testImplementation(Dependencies.KOTEST_EXTENSIONS)
        testImplementation(Dependencies.SPRING_STARTER_TEST)

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.104.Final:osx-aarch_64")
    }
}
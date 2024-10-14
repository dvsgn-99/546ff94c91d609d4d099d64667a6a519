pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

rootProject.children.forEach {
    it.apply {
        buildFileName = "build.gradle.kts"
    }
}

rootProject.name = "ed036d77c1924e546338f9c9c4d209b8"

include("api")
include("domain")
include("common")
include("infra")

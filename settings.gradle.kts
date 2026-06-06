pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    plugins {
        kotlin("jvm").version("2.2.20")
        kotlin("plugin.compose").version("2.2.20")
        id("org.jetbrains.compose").version("1.11.1")
    }
}

rootProject.name = "sdm_portable"

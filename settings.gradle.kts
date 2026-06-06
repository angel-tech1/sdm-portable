pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    plugins {
        kotlin("jvm").version("2.1.0")
        kotlin("plugin.compose").version("2.1.0")
        id("org.jetbrains.compose").version("1.7.3")
    }
}

rootProject.name = "sdm_portable"

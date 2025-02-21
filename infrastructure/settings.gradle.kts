pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "Infrastructure"
include(":app")
include(":library")

// Clean arch module
include(":adapter")
project(":adapter").projectDir = File(rootProject.projectDir, "../adapter/library")
include(":domain")
project(":domain").projectDir = File(rootProject.projectDir, "../domain/library")

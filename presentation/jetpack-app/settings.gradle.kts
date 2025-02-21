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
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://repository.liferay.com/nexus/content/repositories/public/")  }
//        maven { setUrl("https://jcenter.bintray.com/") }
    }

}

rootProject.name = "HelloWorld"
include(":app")

// Clean arch module
include(":domain")
project(":domain").projectDir = File(rootProject.projectDir, "../../domain/library")

include(":infrastructure")
project(":infrastructure").projectDir = File(rootProject.projectDir, "../../infrastructure/library")

include(":adapter")
project(":adapter").projectDir = File(rootProject.projectDir, "../../adapter/library")

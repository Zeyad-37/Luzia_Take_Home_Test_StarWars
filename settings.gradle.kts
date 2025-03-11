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
    }
}

rootProject.name = "Luzia Star Wars"
include(":app")
include(":domain")
include(":data")
include(":testBase")
include(":architecture")
include(":sharedUI")
include(":planetList")
include(":planetDetail")
include(":planetSharedPresentation")

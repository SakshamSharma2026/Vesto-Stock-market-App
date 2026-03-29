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
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Vesto"
include(":app")
include(":utilites")
include(":feature:stock:data")
include(":feature:stock:domain")
include(":feature:stock:ui")
include(":feature:news:data")
include(":core:common")
include(":core:feature_api")
include(":core:network")
include(":core:database")
include(":core:database")
include(":feature:news:ui")
include(":feature:news:domain")
include(":feature:profile:ui")
include(":feature:auth:data")
include(":feature:auth:domain")
include(":feature:auth:ui")

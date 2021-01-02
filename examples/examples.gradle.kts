plugins {
    // Kotlin
    kotlin("jvm")
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("manifests")
        }
    }
}

dependencies {
    val rootProjectName = rootProject.name

    /***********************
     * Implementation
     ***********************/

    // Genitor
    implementation(project(":$rootProjectName-dsl"))
    implementation(project(":$rootProjectName-core-resources"))

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("script-runtime"))
}

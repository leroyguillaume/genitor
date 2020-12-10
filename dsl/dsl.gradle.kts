plugins {
    // Kotlin
    kotlin("jvm")
}

dependencies {
    val rootProjectName = rootProject.name

    /***********************
     * Implementation
     ***********************/

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    /***********************
     * API
     ***********************/

    // Genitor
    api(project(":$rootProjectName-core"))
}

plugins {
    // Kotlin
    kotlin("jvm")
}

dependencies {
    val rootProjectName = rootProject.name

    /***********************
     * Implementation
     ***********************/

    // Genitor
    implementation(project(":$rootProjectName-core"))

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
}

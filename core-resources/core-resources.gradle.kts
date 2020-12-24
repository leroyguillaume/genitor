plugins {
    // Kotlin
    kotlin("jvm")
}

dependencies {
    val resourcesProjectsGroup = "${rootProject.name}-resources"

    /***********************
     * API
     ***********************/

    // Genitor
    api(project(":$resourcesProjectsGroup-file"))
}

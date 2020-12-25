plugins {
    // Kotlin
    kotlin("jvm")
}

dependencies {
    val rootProjectName = rootProject.name

    /***********************
     * Implementation
     ***********************/

    // Commons
    implementation("commons-codec:commons-codec:1.15")

    // Genitor
    implementation(platform(project(":$rootProjectName-bom")))
    implementation(project(":$rootProjectName-core"))
    implementation(project(":$rootProjectName-dto"))

    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}

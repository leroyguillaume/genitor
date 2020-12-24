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
    implementation(project(":$rootProjectName-dsl"))

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // SLF4J
    implementation("org.slf4j:slf4j-api:1.7.30")
}

plugins {
    // Kotlin
    kotlin("jvm")
}

dependencies {
    val rootProjectName = rootProject.name
    val junitVersion = "5.7.0"

    /***********************
     * Implementation
     ***********************/

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // KtsRunner
    implementation("de.swirtz:ktsRunner:0.0.9")

    /***********************
     * API
     ***********************/

    // Genitor
    api(project(":$rootProjectName-core"))

    /***********************
     * Test implementation
     ***********************/

    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")

    /***********************
     * Test runtime
     ***********************/

    // JUnit
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

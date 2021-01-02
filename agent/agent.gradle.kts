plugins {
    // Gradle
    application
    distribution

    // Kotlin
    kotlin("jvm")
    kotlin("plugin.spring")

    // Spring
    id("org.springframework.boot")
}

application {
    mainClassName = "tech.genitor.agent.GenitorAgentKt"
}

dependencies {
    val rootProjectName = rootProject.name

    /***********************
     * Implementation
     ***********************/

    // Genitor
    implementation(platform(project(":$rootProjectName-bom")))
    implementation(project(":$rootProjectName-core"))
    implementation(project(":$rootProjectName-dto"))
    implementation(project(":$rootProjectName-commons-beans"))

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")

    /***********************
     * Runtime
     ***********************/

    // Genitor
    runtimeOnly(project(":$rootProjectName-core-resources"))
}

tasks {
    jar {
        enabled = true
    }
}

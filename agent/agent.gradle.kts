plugins {
    // Gradle
    application
    distribution

    // Kotlin
    kotlin("jvm")
    kotlin("plugin.spring")

    // Spring
    id("org.springframework.boot")
    id("io.spring.dependency-management")
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
    implementation(project(":$rootProjectName-core"))
    implementation(project(":$rootProjectName-dto"))
    implementation(project(":$rootProjectName-commons-config"))

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka:2.6.3")

    /***********************
     * Runtime
     ***********************/

    // Genitor
    runtimeOnly(project(":$rootProjectName-core-resources"))

    /***********************
     * Annotation processor
     ***********************/

    // Spring
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks {
    jar {
        enabled = true
    }
}

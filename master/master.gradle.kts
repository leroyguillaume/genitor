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
    mainClassName = "tech.genitor.server.GenitorServerKt"
}

dependencies {
    val rootProjectName = rootProject.name

    /***********************
     * Implementation
     ***********************/

    // Genitor
    implementation(project(":$rootProjectName-core"))

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.kafka:spring-kafka:2.6.3")

    /***********************
     * Runtime
     ***********************/

    // Liquibase
    runtimeOnly("org.liquibase:liquibase-core")

    // PostgreSQL
    runtimeOnly("org.postgresql:postgresql")

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

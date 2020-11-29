import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    // Kotlin
    kotlin("jvm")
    kotlin("plugin.spring")

    // Spring
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    /***********************
     * Implementation
     ***********************/

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka:2.6.3")

    /***********************
     * Annotation processor
     ***********************/

    // Spring
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

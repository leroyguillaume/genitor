import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    val kotlinVersion = "1.4.10"

    // Kotlin
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false

    // Spring
    id("org.springframework.boot") version "2.3.5.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.10.RELEASE" apply false
}

allprojects {
    group = "tech.genitor"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }

    tasks.withType<BootRun> {
        workingDir(rootProject.projectDir)
        args("--spring.profiles.active=dev")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks {
    wrapper {
        gradleVersion = "6.6"
    }
}

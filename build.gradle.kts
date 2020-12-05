import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
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
    val rootGroup = "tech.genitor"
    group = if (projectDir.parentFile == rootProject.projectDir) {
        rootGroup
    } else {
        "$rootGroup.${projectDir.parentFile.name}"
    }
    version = "0.1.0"

    repositories {
        mavenCentral()
    }

    tasks.withType<BootJar> {
        archiveClassifier.set("fatjar")
    }

    tasks.withType<BootRun> {
        workingDir(rootProject.projectDir)
        args("--spring.profiles.active=dev")
    }

    tasks.withType<Jar> {
        manifest {
            attributes(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    tasks.withType<Tar> {
        compression = Compression.GZIP
        archiveExtension.set("tar.gz")
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

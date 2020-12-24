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
    mainClassName = "tech.genitor.master.GenitorMasterKt"
}

dependencies {
    val rootProjectName = rootProject.name

    /***********************
     * Implementation
     ***********************/

    // Genitor
    implementation(project(":$rootProjectName-core"))
    implementation(project(":$rootProjectName-dto"))
    implementation(project(":$rootProjectName-dsl"))
    implementation(project(":$rootProjectName-commons-config"))

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // KtsRunner
    implementation("de.swirtz:ktsRunner:0.0.9")

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.kafka:spring-kafka:2.6.3")

    /***********************
     * Runtime
     ***********************/

    // Kotlin
    runtimeOnly(kotlin("reflect"))

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
    create<Exec>("buildDockerImage") {
        dependsOn("copyDistTarToDockerDir")

        val rootProjectName = rootProject.name
        val target = project.name.removePrefix("$rootProjectName-")
        val tag = "$rootProjectName/$target"

        executable("docker")
        args(
            "build",
            "-t", tag,
            "-t", "$tag:$version",
            "--build-arg", "version=$version",
            "docker/genitor"
        )
        workingDir(rootProject.projectDir)
    }

    create<Copy>("copyDistTarToDockerDir") {
        dependsOn("distTar")

        from("$buildDir/distributions/${project.name}-$version.tar.gz")
        into("${rootProject.projectDir}/docker/genitor")
    }

    jar {
        enabled = true
    }
}

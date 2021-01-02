plugins {
    // Gradle
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    val rootProjectName = rootProject.name

    // Spring
    api(platform("org.springframework.boot:spring-boot-dependencies:2.3.5.RELEASE"))

    constraints {
        // Genitor
        api(project(":$rootProjectName-core"))
        api(project(":$rootProjectName-dto"))
        api(project(":$rootProjectName-dsl"))

        // Spring
        api("org.springframework.kafka:spring-kafka:2.6.3")
    }
}

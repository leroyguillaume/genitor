plugins {
    kotlin("jvm") version "1.4.10" apply false
}

allprojects {
    group = "tech.genitor"
    version = "0.1.0"

    repositories {
        mavenCentral()
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

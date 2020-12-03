fun includeModule(name: String) {
    val projectName = "${rootProject.name}-$name"
    include(projectName)
    val project = project(":$projectName")
    project.projectDir = File(name)
    project.buildFileName = "$name.gradle.kts"
}

rootProject.name = "genitor"

arrayOf("core", "dto", "dsl", "master", "agent").forEach { includeModule(it) }

import tech.genitor.dsl.catalog
import tech.genitor.resources.file.symlink
import java.nio.file.Paths

catalog {
    node("agent-01") {
        symlink(
            path = Paths.get("/tmp/test"),
            target = Paths.get("/etc/hosts")
        ).whenSuccess(
            symlink(
                path = Paths.get("/tmp/test2"),
                target = Paths.get("/tmp/test")
            )
        )
    }
}

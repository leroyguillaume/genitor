import tech.genitor.dsl.catalog

catalog {
    group("monitoring") {
        val influxdb01 = node("influxdb-01") {

        }
        val grafana01 = node("grafana-01") {

        }

        add(influxdb01, grafana01)
    }
}

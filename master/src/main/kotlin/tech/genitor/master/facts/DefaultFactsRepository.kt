package tech.genitor.master.facts

import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import tech.genitor.core.FactsRepository
import tech.genitor.master.FactsColumnName
import tech.genitor.master.FactsTableName
import tech.genitor.master.HostnameColumnName
import tech.genitor.master.LastModificationDateColumnName

/**
 * Default implementation of facts repository.
 *
 * @param jdbcTemplate JDBC template.
 */
@Repository
class DefaultFactsRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : FactsRepository {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultFactsRepository::class.java)
    }

    override fun save(hostname: String, factsJson: String) {
        jdbcTemplate.update(
            """
            INSERT INTO $FactsTableName ($HostnameColumnName, $FactsColumnName)
            VALUES (:hostname, :facts::jsonb)
            ON CONFLICT ($HostnameColumnName)
            DO UPDATE SET $FactsColumnName = :facts::jsonb, $LastModificationDateColumnName = now()
            """,
            MapSqlParameterSource(mapOf("hostname" to hostname, "facts" to factsJson))
        )
        Logger.info("Facts of node '$hostname' updated")
    }
}

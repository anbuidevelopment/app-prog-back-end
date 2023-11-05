package com.apps.prog.appprosbackend.api.features.lms.params_config

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParamConfigRepository : JpaRepository<ParamConfig, Long> {
    fun findAllByTableNameAndColumnName(tableName: String, columnName: String): List<ParamConfig>

}
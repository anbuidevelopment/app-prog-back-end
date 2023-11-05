package com.apps.prog.appprosbackend.api.features.lms.params_config

import org.springframework.stereotype.Service

@Service
class ParamConfigService(private val paramConfigRepository: ParamConfigRepository) {
    fun findAllStatusForTask(): List<ParamConfig> {
        return paramConfigRepository.findAllByTableNameAndColumnName(tableName = "app.lms.Tasks", columnName = "Status")
    }
}
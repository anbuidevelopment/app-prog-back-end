package com.apps.prog.appprosbackend.api.features.lms.params_config

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/paramConfig")
class ParamConfigController(private val service: ParamConfigService) {
    @GetMapping
    fun findAllStatusForTask() = service.findAllStatusForTask()
}
package com.apps.prog.appprosbackend.api.features.posize

import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
import java.net.URLDecoder

@RestController
@RequestMapping("/api/v1/po/detail")
class PoSizeController(private val service: PoSizeService) {
    @GetMapping
    fun findAllByOrderNo(@Param("orderNo") orderNo: String): List<PoSize> {
        val data = service.findAllByOrderNo(orderNo)
        return data.distinctBy { it.size }
    }
}
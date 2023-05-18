package com.apps.prog.appprosbackend.api.features.a1af1plan

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/plan")
class A1AF1PlanController(private val service: A1AF1PlanService) {
    @GetMapping
    fun findAll(
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("rowsPerPage", defaultValue = "10") rowsPerPage: Int,
        @RequestParam("pono", defaultValue = "") pono: String,
        @RequestParam("fromDate", defaultValue = "") fromDate: String,
        @RequestParam("toDate", defaultValue = "") toDate: String,
        @RequestParam("line", defaultValue = "") line: String,
    ) = service.getListA1AF1Plan(
        page = page,
        rowsPerPage = rowsPerPage,
        pono = pono,
        fromDate = fromDate,
        toDate = toDate,
        line = line
    )

    @GetMapping("/in-out-decoration")
    fun findPoDetail(@RequestParam("factory") factory: String, @RequestParam("pono") pono: String) =
        service.findPoDetail(factory = factory, pono)
}
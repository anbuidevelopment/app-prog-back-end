package com.apps.prog.appprosbackend.api.features.lms.problem.controller

import com.apps.prog.appprosbackend.api.features.lms.problem.model.request.ProblemRequest
import com.apps.prog.appprosbackend.api.features.lms.problem.service.ProblemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/prb")
class ProblemTicketController(private val service: ProblemService) {
    @PostMapping("/save")
    suspend fun saveProblem(@RequestBody rq: ProblemRequest) = service.saveForm(rq)

    @GetMapping("/reasons")
    fun fetchReason() = service.fetchReason()

    @GetMapping("/solutions")
    fun fetchSolution() = service.fetchSolution()

    @GetMapping("/problems")
    fun fetchProblem(@RequestParam startDate: String, @RequestParam endDate: String) =
        service.findProblemTicketsByDateRange(startDate, endDate)
}
package com.apps.prog.appprosbackend.api.features.a1af1plan

import com.apps.prog.appprosbackend.api.common.ApiResponse
import com.apps.prog.appprosbackend.api.common.DataListPage
import com.apps.prog.appprosbackend.api.common.Resource
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class A1AF1PlanService(private val repository: com.apps.prog.appprosbackend.api.features.a1af1plan.A1AF1PlanRepository) {
    fun getListA1AF1Plan(
        page: Int = 0,
        rowsPerPage: Int = 10,
        pono: String = "",
        fromDate: String = "",
        toDate: String = ""
    ): Resource<Any> {
        try {
            val pageRequest = PageRequest.of(page, rowsPerPage)
            val dataMapToPage = if (pono.isBlank() && (fromDate.isNotBlank() && toDate.isNotBlank())) {
                repository.findAllByShipmentDateBetween(fromDate, toDate, pageRequest)
            } else if (pono.isNotBlank()) {
                repository.findAllByPonoContainingIgnoreCase(pono, pageRequest)
            } else {
                repository.findAll(pageRequest)
            }
            return Resource.Success(
                DataListPage(
                    totalElements = dataMapToPage.totalElements,
                    totalPages = dataMapToPage.totalPages,
                    dataList = dataMapToPage.content
                )
            )
        } catch (e: ResponseStatusException) {
            return Resource.Error(ApiResponse<Any>(HttpStatus.BAD_REQUEST.value(), e.localizedMessage))
        }
    }

//    fun findInOutDecoration(factory: String) = repository.findInOutDecoration(factory)



}

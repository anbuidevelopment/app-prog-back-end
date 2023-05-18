package com.apps.prog.appprosbackend.api.features.a1af1plan

import jakarta.persistence.ConstructorResult
import jakarta.persistence.EntityManagerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface A1AF1PlanRepository : JpaRepository<A1AF1Plan, Long> {
    fun findAllByPonoContainingIgnoreCaseAndShipmentDateBetween(
        pono: String,
        fromDate: String,
        toDate: String,
        page: Pageable
    ): Page<A1AF1Plan>

    fun findAllByShipmentDateBetween(fromDate: String, toDate: String, page: Pageable): Page<A1AF1Plan>

    fun findAllByLineContainingIgnoreCaseAndShipmentDateBetween(
        line: String,
        fromDate: String,
        toDate: String,
        page: Pageable
    ): Page<A1AF1Plan>

    fun findAllByLineContainingIgnoreCaseAndPonoContainingIgnoreCaseAndShipmentDateBetween(
        line: String,
        pono: String,
        fromDate: String,
        toDate: String,
        page: Pageable
    ): Page<A1AF1Plan>
}
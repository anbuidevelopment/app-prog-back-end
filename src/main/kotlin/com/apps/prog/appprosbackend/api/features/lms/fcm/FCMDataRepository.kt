package com.apps.prog.appprosbackend.api.features.lms.fcm

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FCMDataRepository : JpaRepository<FCMDataEntity, Long> {
    fun findAllByStaffCodeAndCreatedDateBetweenOrderByIsRead(
        staffCode: String,
        fromDate: String,
        toDate: String,
        page: Pageable
    ): Page<FCMDataEntity>

    fun countAllByStaffCodeAndIsRead(staffCode: String, isRead: Boolean = false): Int


    fun findAllByStaffCodeAndIsRead(staffCode: String, isRead: Boolean = false): List<FCMDataEntity>

    @Modifying
    @Query("UPDATE EscalationPolicyData es SET es.isRead = true WHERE es.escalationPolicyDataId = :esId")
    fun markAsRead(esId: Long)

    fun findAllByStaffCodeAndHasSolution(staffCode: String, hasSolution: Boolean = true): List<FCMDataEntity>

    @Modifying
    @Query("UPDATE EscalationPolicyData es SET es.hasSolution = true WHERE es.escalationPolicyDataId = :esId")
    fun markHasSolution(esId: Long)
}
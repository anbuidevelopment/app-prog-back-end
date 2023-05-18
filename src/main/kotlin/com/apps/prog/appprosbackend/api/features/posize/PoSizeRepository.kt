package com.apps.prog.appprosbackend.api.features.posize

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PoSizeRepository : JpaRepository<PoSize, Int> {
    fun findAllByOrderNo(orderNo: String): List<PoSize>
}
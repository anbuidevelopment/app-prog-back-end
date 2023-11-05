package com.apps.prog.appprosbackend.api.features.lms.checkList

import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.Checklist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CheckListRepository : JpaRepository<Checklist, Int> {
    fun findByType(type: Int): Optional<Checklist>
}
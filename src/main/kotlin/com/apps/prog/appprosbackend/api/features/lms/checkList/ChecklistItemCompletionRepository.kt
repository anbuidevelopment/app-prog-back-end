package com.apps.prog.appprosbackend.api.features.lms.checkList

import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.ChecklistItem
import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.ChecklistItemCompletion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ChecklistItemCompletionRepository : JpaRepository<ChecklistItemCompletion, Long> {
//    @Query(
//        """
//        SELECT c
//        FROM ChecklistItemCompletion c
//        WHERE
//        FUNCTION('MOD', FUNCTION('DATEPART', 'WEEK', c.completionDate), FUNCTION('MONTH', c.completionDate)) = :weekOfMonth
//        AND FUNCTION('MONTH', c.completionDate) = :month
//    """
//    )
//    fun findByWeekOfMonthAndMonth(
//        @Param("weekOfMonth") weekOfMonth: Int,
//        @Param("month") month: Int
//    ): List<ChecklistItemCompletion>

    fun findByChecklistItemIdAndCompletionDateBetween(
        id: Long,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): ChecklistItemCompletion?
}
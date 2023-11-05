package com.apps.prog.appprosbackend.api.features.lms.checkList

import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.ChecklistItem
import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.ChecklistItemCompletion
import com.apps.prog.appprosbackend.api.features.lms.checkList.model.ChecklistResponse
import org.springframework.data.jpa.repository.JpaRepository
;
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CheckListItemRepository : JpaRepository<ChecklistItem, Int> {
    @Query(
        """ SELECT NEW com.apps.prog.appprosbackend.api.features.lms.checkList.model.ChecklistResponse(
                '',
            c.checklistId,
            c.checklistItemId,
            c.title,
            c.description,
            comp.completionDate
     )
        FROM ChecklistItem c
        LEFT JOIN ChecklistItemCompletion comp ON c.checklistItemId = comp.checklistItemId
            AND comp.completionDate BETWEEN :startDate AND :endDate
        WHERE c.checklistId = :checklistId
    """
    )
    fun findCompletedItemsForDate(
        @Param("startDate") startDate: LocalDateTime,
        @Param("endDate") endDate: LocalDateTime,
        @Param("checklistId") checkListId: Int
    ): List<ChecklistResponse>
}
//
//    @Query(
//        """
//         SELECT c.ChecklistId, c.ChecklistItemId, c.Title, c.Description, comp.CompletionDate
//            FROM lms.ChecklistItems c
//            LEFT JOIN lms.ChecklistItemCompletions comp ON c.ChecklistItemId = comp.ChecklistItemId
//       WHERE (comp.CompletionDate IS NULL
//              OR (DATEPART(WEEK, comp.CompletionDate) % MONTH(comp.CompletionDate)) = :weekOfMonth
//              AND MONTH(comp.CompletionDate) = :month
//              AND YEAR(comp.CompletionDate) = :year)
//              AND c.ChecklistId = :checklistId
//    """, nativeQuery = true
//    )
//    fun findCompletedItemsForWeekOfMonthAndMonth(
//        @Param("weekOfMonth") weekOfMonth: Int,
//        @Param("month") month: Int,
//        @Param("year") year: Int,
//        @Param("checklistId") checklistId: Long
//    ): List<Array<Any>>
//
//    @Query(
//        """
//        SELECT NEW com.apps.prog.appprosbackend.api.features.lms.checkList.model.ChecklistResponse(
//                '',
//            c.checklistId,
//            c.checklistItemId,
//            c.title,
//            c.description,
//            comp.completionDate
//     )
//        FROM ChecklistItem c
//        LEFT JOIN ChecklistItemCompletion comp ON c.checklistItemId = comp.checklistItemId
//        WHERE
//           (comp.completionDate IS NULL OR
//            FUNCTION('YEAR', comp.completionDate) = :year
//            AND FUNCTION('MONTH', comp.completionDate) = :month)
//            AND c.checklistId = :checklistId
//    """
//    )
//    fun findCompletedItemsForMonthAndYear(
//        @Param("month") month: Int,
//        @Param("year") year: Int,
//        @Param("checklistId") checkListId: Int
//    ): List<ChecklistResponse>
//}

//  @Query("""
//        SELECT c, comp
//        FROM ChecklistItemCompletion comp
//        JOIN ChecklistItem c ON comp.checklistItemId = c.checklistItemId
//        WHERE
//            FUNCTION('MOD', FUNCTION('DATEPART', 'WEEK', comp.completionDate), FUNCTION('MONTH', comp.completionDate)) = :weekOfMonth
//            AND FUNCTION('MONTH', comp.completionDate) = :month
//    """)
//  fun findCompletedItemsForWeekOfMonthAndMonth(
//    @Param("weekOfMonth") weekOfMonth: Int,
//    @Param("month") month: Int
//  ): List<Pair<ChecklistItem, ChecklistItemCompletion>>

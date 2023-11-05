package com.apps.prog.appprosbackend.api.features.lms.checkList

import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.Checklist
import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.ChecklistItemCompletion
import com.apps.prog.appprosbackend.api.features.lms.checkList.model.ChecklistRequest
import com.apps.prog.appprosbackend.api.features.lms.checkList.model.ChecklistResponse
import com.apps.prog.appprosbackend.api.features.lms.task_manager.TaskService
import com.apps.prog.appprosbackend.utils.formatToString
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*


@Service
class CheckListItemService(
    private val itemRepository: CheckListItemRepository,
    private val checkListRepository: CheckListRepository,
    private val itemCompletionRepository: ChecklistItemCompletionRepository
) {
    private val logger = LoggerFactory.getLogger(CheckListItemService::class.java)
    fun checkToComplete(completes: List<ChecklistItemCompletion>) {
        val listTemp = arrayListOf<ChecklistItemCompletion>()
        completes.forEach {
            val current = LocalDateTime.now()
            val startDate = LocalDateTime.of(current.year, current.month, current.dayOfMonth, 0, 0, 0)
            val endDate = LocalDateTime.of(current.year, current.month, current.dayOfMonth, 23, 59, 0)
            val item = itemCompletionRepository.findByChecklistItemIdAndCompletionDateBetween(
                it.checklistItemId,
                startDate,
                endDate
            )
            logger.info("s {}", item)
            if (item == null) listTemp.add(it.copy(completionDate = current))
        }
        itemCompletionRepository.saveAll(listTemp)
    }

    fun getAllCheckListItemsForChecklist(rq: ChecklistRequest): List<ChecklistResponse> {
        val checkList = checkListRepository.findByType(rq.type)
            .orElseThrow { RuntimeException("CheckListId with ID ${rq.type} not found") }

        return when (ChecklistType.fromInt(checkList.type.toInt())) {
            ChecklistType.DAILY -> getDailyChecklist(rq.day, rq.month, rq.year, checkList)
            ChecklistType.WEEK_OF_MONTH -> getWeekOfMonthChecklist(rq.weekOfMonth, rq.month, rq.year, checkList)
            ChecklistType.MONTHLY -> getMonthChecklist(rq.month, rq.year, checkList)
        }
    }

    private fun getDailyChecklist(day: Int, month: Int, year: Int, checklist: Checklist): List<ChecklistResponse> {
        val startDate = LocalDateTime.of(year, month, day, 0, 0, 0)
        val endDate = LocalDateTime.of(year, month, day, 23, 59, 0)
        return itemRepository.findCompletedItemsForDate(startDate, endDate, checklist.checklistId)
            .map {
                it.copy(
                    checklistDesc = checklist.description,
                    completionDate = (it.completionDate as LocalDateTime?)?.formatToString()
                )
            }
    }

    private fun getWeekOfMonthChecklist(
        weekOfMonth: Int,
        month: Int,
        year: Int,
        checklist: Checklist
    ): List<ChecklistResponse> {
        val (startDate, endDate) = getDateRangeForWeekOfMonth(year, month, weekOfMonth)
        return itemRepository.findCompletedItemsForDate(
            startDate.atTime(0, 0),
            endDate.atTime(23, 59),
            checklist.checklistId
        ).map {
            it.copy(
                checklistDesc = checklist.description,
                completionDate = (it.completionDate as LocalDateTime?)?.formatToString()
            )
        }.toList()
    }

    private fun getMonthChecklist(month: Int, year: Int, checklist: Checklist): List<ChecklistResponse> {
        val (startDate, endDate) = getDateRangeForMonth(year, month)

        return itemRepository.findCompletedItemsForDate(
            startDate.atTime(0, 0),
            endDate.atTime(23, 59),
            checklist.checklistId
        )
            .map {
                it.copy(
                    checklistDesc = checklist.description,
                    completionDate = (it.completionDate as LocalDateTime?)?.formatToString()

                )
            }.toList()
    }

    private fun getDateRangeForWeekOfMonth(year: Int, month: Int, weekOfMonth: Int): Pair<LocalDate, LocalDate> {
        val firstDayOfMonth = LocalDate.of(year, month, 1)
        val firstDayOfWeek = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
        val desiredWeekStart = firstDayOfWeek.plusWeeks((weekOfMonth - 1).toLong())
        val desiredWeekEnd = desiredWeekStart.plusDays(6) // Assuming a week has 7 days

        return Pair(desiredWeekStart, desiredWeekEnd)
    }

    private fun getDateRangeForMonth(year: Int, month: Int): Pair<LocalDate, LocalDate> {
        val yearMonth = YearMonth.of(year, month)
        val firstDayOfMonth = yearMonth.atDay(1)
        val lastDayOfMonth = yearMonth.atEndOfMonth()

        return Pair(firstDayOfMonth, lastDayOfMonth)
    }

    private enum class ChecklistType(val value: Int) {
        DAILY(1),
        WEEK_OF_MONTH(2),
        MONTHLY(3);

        companion object {
            fun fromInt(value: Int): ChecklistType = values().find { it.value == value }
                ?: throw IllegalArgumentException("Invalid ChecklistType value: $value")
        }
    }
}

//        val date = LocalDate.of(2023, 8, 27)
//        val weekField = WeekFields.of(Locale.getDefault())
//        val weekNumber = date.get(weekField.weekOfMonth())
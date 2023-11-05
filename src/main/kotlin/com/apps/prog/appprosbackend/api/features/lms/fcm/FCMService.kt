package com.apps.prog.appprosbackend.api.features.lms.fcm

import com.apps.prog.appprosbackend.api.exception.AppException
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Service
class FCMService(private val repository: FCMDataRepository) {

    fun getNotifies(): MutableList<FCMDataEntity> = repository.findAll()

    @Throws(FirebaseMessagingException::class)
    @Async
    fun sendNotification(token: String, title: String, content: String) {
        val notification: Notification = Notification
            .builder()
            .setTitle(title)
            .setBody(content)
            .build()
        val message: Message = Message
            .builder()
            .setToken(token)
            .setNotification(notification)
            .build()
        FirebaseMessaging.getInstance().send(message)
    }

    @Transactional
    @Throws(AppException::class)
    fun markAsRead(esId: Long) {
        repository.markAsRead(esId)
    }
    @Transactional
    @Throws(AppException::class)
    fun markHasSolution(esId: Long) {
        repository.markHasSolution(esId)
    }
    @Throws(AppException::class)
    fun countNotificationNotRead(staffCode: String): Int {
        return repository.countAllByStaffCodeAndIsRead(staffCode = staffCode, false)
    }

    fun findAllByStaffCodeAndIsRead(staffCode: String) = repository.findAllByStaffCodeAndIsRead(staffCode)
    fun findAllByStaffCodeAndHasSolution(staffCode: String) = repository.findAllByStaffCodeAndHasSolution(staffCode)

    @Throws(AppException::class)
    fun getFcmDataByAccountId(
        staffCode: String,
        page: Int = 0,
        rowsPerPage: Int = 50,
        fromDate: String,
        toDate: String
    ): FcmDataResponse {
        val pageRequest = PageRequest.of(page, rowsPerPage)
        var fromDateRequest = defaultFromDate()
        var toDateRequest = defaultToDate()

        if (fromDate.isNotEmpty() && toDate.isNotEmpty()) {
            fromDateRequest = convertDateToDateTimeString(fromDate, LocalTime.MIDNIGHT)
            toDateRequest = convertDateToDateTimeString(toDate, LocalTime.of(23, 59, 59))
        }

        val data =
            repository.findAllByStaffCodeAndCreatedDateBetweenOrderByIsRead(staffCode, fromDateRequest, toDateRequest, pageRequest)

        return FcmDataResponse(
            pageIndex = data.pageable.pageNumber,
            rowsPerPage = data.pageable.pageSize,
            totalPage = data.totalPages,
            totalCount = data.totalElements,
            items = data.content
        )
    }

    private fun formatDate(date: LocalDateTime, pattern: String = "yyyy-MM-dd HH:mm"): String {
        return date.format(DateTimeFormatter.ofPattern(pattern))
    }

    private fun defaultFromDate(): String {
        val currentDate = LocalDate.now()
        return formatDate(LocalDateTime.of(currentDate.minusDays(7), LocalTime.MIDNIGHT))
    }

    private fun defaultToDate(): String {
        return formatDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59)))
    }

    private fun convertDateToDateTimeString(dateString: String, localTime: LocalTime): String {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, dateFormatter)
        return formatDate(LocalDateTime.of(date, localTime))
    }
}
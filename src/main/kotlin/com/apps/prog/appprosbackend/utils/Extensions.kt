package com.apps.prog.appprosbackend.utils

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun MessageSource.getMsg(localizeMsg: String, args: Array<Any?>? = emptyArray()): String {
    val locale = LocaleContextHolder.getLocale()
    return this.getMessage(localizeMsg, args, locale)
}


fun LocalDateTime.formatToString(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}

fun String.parseDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): LocalDate? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        LocalDate.parse(this, formatter)
    } catch (e: DateTimeParseException) {
        null
    }
}

fun LocalDate.parseString(pattern: String = "yyyy-MM-dd HH:mm:ss"): String? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return this.format(formatter)
    } catch (e: Exception) {
        null
    }
}
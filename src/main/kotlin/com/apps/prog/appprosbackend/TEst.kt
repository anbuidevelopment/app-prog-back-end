//package com.apps.prog.appprosbackend
//
//import java.time.DayOfWeek
//import java.time.LocalDate
//import java.time.LocalDateTime
//import java.time.LocalTime
//import java.time.format.DateTimeFormatter
//import java.time.temporal.TemporalAdjusters
//
//fun main() {
//    val (start, end) = getDateRangeForWeekOfMonth(2023,10, 1)
//    val fromDateRequest = convertDateToDateTimeString("2023-10-08", LocalTime.MIDNIGHT)
//    val toDateRequest = convertDateToDateTimeString("2023-10-09", LocalTime.of(23, 59, 59))
//    print("hello world ${start} \n ${end} \n ${fromDateRequest} - ${toDateRequest}")
//}
//
//fun getDateRangeForWeekOfMonth(year: Int, month: Int, weekOfMonth: Int): Pair<LocalDate, LocalDate> {
//    val firstDayOfMonth = LocalDate.of(year, month, 1)
//    val firstDayOfWeek = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
//    val desiredWeekStart = firstDayOfWeek.plusWeeks((weekOfMonth - 1).toLong())
//    val desiredWeekEnd = desiredWeekStart.plusDays(6) // Assuming a week has 7 days
//
//    return Pair(desiredWeekStart, desiredWeekEnd)
//}
//
//private fun formatDate(date: LocalDateTime, pattern: String = "yyyy-MM-dd HH:mm"): String {
//    return date.format(DateTimeFormatter.ofPattern(pattern))
//}
//
// fun convertDateToDateTimeString(dateString: String, localTime: LocalTime): String {
//    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//    val date = LocalDate.parse(dateString, dateFormatter)
//    return formatDate(LocalDateTime.of(date, localTime))
//}
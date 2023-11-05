package com.apps.prog.appprosbackend.api.features.lms.fcm

import org.checkerframework.checker.units.qual.t
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("api/v1/notify")
class FCMController(private val fcmService: FCMService) {
    val token =
        "dupu-bAbSpGdVa_cC8xNco:APA91bFFxCSR-FGrEDH_CirL-akhJfn4TWaLuKb-blADpT0qJOupvd2fDzyxwLiLszo0uLb9mJgueFDOWEHMRYxESUNsE2vBPEBbBhsi-V2eCwDCbs6s3FBI_ziGsK5VXmShT3Rwi0F7"

    //    @Scheduled(cron = "*/5 * * * * *")
    @PostMapping("/send-msg")
    fun sendMessage() = fcmService.sendNotification(token, "test", "test")

    @GetMapping
    fun getAllByStaffCode(
        @RequestParam("staffCode") staffCode: String,
        @RequestParam("page") page: Int,
        @RequestParam("fromDate", defaultValue = "") fromDate: String,
        @RequestParam("toDate", defaultValue = "") toDate: String,
    ) = fcmService.getFcmDataByAccountId(staffCode = staffCode, fromDate = fromDate, toDate = toDate, page = page)

    @GetMapping("/staff/all")
    fun findAllByStaffCode(@RequestParam("staffCode") staffCode: String) =
        fcmService.findAllByStaffCodeAndIsRead(staffCode)

    @GetMapping("/staff/hasSolution/all")
    fun findAllHasSolutionByStaffCode(@RequestParam("staffCode") staffCode: String) =
        fcmService.findAllByStaffCodeAndHasSolution(staffCode)

    @GetMapping("/badges")
    fun getBadge(@RequestParam("staffCode") staffCode: String) = fcmService.countNotificationNotRead(staffCode)

    @PutMapping("/{esId}/markAsRead")
    fun markAsRead(@PathVariable("esId") esId: Long) = fcmService.markAsRead(esId)

    @PutMapping("/{esId}/markHasSolution")
    fun markHasSolution(@PathVariable("esId") esId: Long) = fcmService.markHasSolution(esId)
}
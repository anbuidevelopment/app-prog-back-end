package com.apps.prog.appprosbackend.api.features.lms.fcm

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import java.time.Duration
import java.util.concurrent.Executors

//@Configuration
//@EnableScheduling
//class SchedulerConfiguration : SchedulingConfigurer {
//
//    @Autowired
//    private lateinit var fcmService: FCMService
//
//    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
//        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(1))
//
//        val dataNotifies = fcmService.getNotifies()
//        dataNotifies.forEach {
//            taskRegistrar.addFixedRateTask({ fcmService.sendNotification(registrationToken, it.staffId, it.content) }, Duration.ofMinutes(it.timeRepeat.toLong()))
//        }
//        println("Connected")
//    }
//
//    private val registrationToken = "f3BOpgySTd-edF0Q5WsDLQ:APA91bEEfSomMNJZxeiyzZe17gi_bplmNJIUrSLnWjAQv6sQanFc0b_6-iIXdIppeWeEWE0D6xTIG-iZtOibH3dylVI1TjA-4LWjYaKIMbQzsPWt8hDEgg1KGkppbJggFup1TJ-GcBwx"
//}
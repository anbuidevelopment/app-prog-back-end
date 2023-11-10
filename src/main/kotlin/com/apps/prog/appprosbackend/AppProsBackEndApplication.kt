package com.apps.prog.appprosbackend

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate
import java.io.FileInputStream
import java.io.IOException


@SpringBootApplication
@EnableScheduling
class AppProsBackEndApplication

fun main(args: Array<String>) {
    runApplication<AppProsBackEndApplication>(*args)
}


@Configuration
@EnableAsync
class FireBaseConfig {
    @Autowired
    lateinit var resourceLoader: ResourceLoader

    @Bean
    @Throws(IOException::class)
    fun createFireBaseApp(): FirebaseApp {
        val resource = resourceLoader.getResource("classpath:lms-allianceone-prod-firebase-adminsdk-vskd8-2b4771b298.json")
        val inputStream = resource.inputStream

        val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build()
        return FirebaseApp.initializeApp(options)
    }
}
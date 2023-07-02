package com.apps.prog.appprosbackend.config

import com.apps.prog.appprosbackend.utils.JsonMessageSource
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LanguageConfig {
    @Bean
    fun messageSource(): MessageSource {
        return JsonMessageSource()
    }
}
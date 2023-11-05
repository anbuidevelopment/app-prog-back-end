package com.apps.prog.appprosbackend.config

import com.apps.prog.appprosbackend.utils.CustomLocaleResolver
import com.apps.prog.appprosbackend.utils.JsonMessageSource
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver

@Configuration
class LanguageConfig {
    @Bean
    fun messageSource(): MessageSource {
        return JsonMessageSource()
    }

    @Bean
    fun locale(): AcceptHeaderLocaleResolver {
        return CustomLocaleResolver()
    }



}
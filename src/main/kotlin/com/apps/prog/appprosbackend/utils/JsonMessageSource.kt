package com.apps.prog.appprosbackend.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.support.AbstractMessageSource
import org.springframework.lang.Nullable
import java.text.MessageFormat
import java.util.*

class JsonMessageSource : AbstractMessageSource() {

    override fun resolveCode(code: String, locale: Locale): MessageFormat? {
        val resourceName = "msg_${locale.language}.json"
        val jsonFile = this.javaClass.classLoader.getResourceAsStream(resourceName)

        val messages: Map<String, String> =
            ObjectMapper().readValue(jsonFile, object : TypeReference<Map<String, String>>() {})

        val message = messages[code]
        return if (message != null) MessageFormat(message, locale) else null
    }

    override fun resolveArguments(args: Array<out Any>?, locale: Locale): Array<out Any> {
        // Convert any non-string arguments to strings before resolving
        return args?.map { it.toString() }?.toTypedArray() ?: emptyArray()
    }
}
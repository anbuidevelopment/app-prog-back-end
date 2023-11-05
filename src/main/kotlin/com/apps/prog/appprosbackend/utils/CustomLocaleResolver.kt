package com.apps.prog.appprosbackend.utils

import jakarta.servlet.http.HttpServletRequest
import org.springframework.util.StringUtils
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

class CustomLocaleResolver : AcceptHeaderLocaleResolver() {
    override fun resolveLocale(request: HttpServletRequest): Locale {
        if (request.getHeader("Accept-Language").isNullOrEmpty()) {
            return Locale("vi", "VN")
        }

        return Locale(request.getHeader("Accept-Language"), "VN")
    }
}
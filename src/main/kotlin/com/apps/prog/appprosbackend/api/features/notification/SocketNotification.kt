package com.apps.prog.appprosbackend.api.features.notification

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.socket.WebSocketSession

@Controller
class WebSocketController {
    private val users = mutableMapOf<String, WebSocketSession>()
}
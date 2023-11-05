package com.apps.prog.appprosbackend.api.exception

import org.springframework.dao.EmptyResultDataAccessException

class AppAuthException(message: String) : RuntimeException(message)
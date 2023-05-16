package com.apps.prog.appprosbackend.api.features.authentication

import com.apps.prog.appprosbackend.api.common.ApiResponse
import org.apache.coyote.Response
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(private val userService: UserService) {
    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/authenticate")
    fun signIn(@RequestBody body: LoginRequestBody): ResponseEntity<Any> {
        return try {
            val userDto = userService.findUserByUsernameAndPassword(body.username, body.password)
            ResponseEntity.ok(userDto)
        } catch (em: EmptyResultDataAccessException) {
            logger.error(em.localizedMessage)

            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApiResponse(
                    status = HttpStatus.UNAUTHORIZED.value(),
                    message = "The user not found",
                    data = null
                )
            )
        } catch (e: Exception) {
            logger.error(e.localizedMessage)

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse(
                    status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    message = e.message.toString(),
                    data = null
                )
            )
        }
    }

    @GetMapping
    fun getUsers(@RequestParam(name = "dept") dept: String): ResponseEntity<Any> {
        return try {
            val data = userService.findAllUserByDepartment(department = dept)
            ResponseEntity.ok(data)
        } catch (e: Exception) {
            logger.error(e.localizedMessage)

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse(
                    status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    message = e.message.toString(),
                    data = null
                )
            )
        }
    }
}
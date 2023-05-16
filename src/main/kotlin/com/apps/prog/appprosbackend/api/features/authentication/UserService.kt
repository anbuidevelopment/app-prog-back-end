package com.apps.prog.appprosbackend.api.features.authentication

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findUserByUsernameAndPassword(username: String, password: String): User? {
        return userRepository.findUserByUsernameAndPassword(username, password)
    }

    fun findAllUserByDepartment(department: String): List<UserDto> {
        return userRepository.findAllByDepartment(department)
    }
}
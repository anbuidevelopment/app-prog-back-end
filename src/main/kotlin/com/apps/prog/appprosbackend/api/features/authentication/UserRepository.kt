package com.apps.prog.appprosbackend.api.features.authentication

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findUserByUsernameAndPassword(username: String, password: String): User

    @Query("SELECT NEW com.apps.prog.appprosbackend.api.features.authentication.UserDto(u.id, u.fullName) FROM lms_users u where u.department = :department")
    fun findAllByDepartment(@Param("department") department: String): List<UserDto>
}
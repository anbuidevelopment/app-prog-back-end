package com.apps.prog.appprosbackend.api.features.authentication

import com.apps.prog.appprosbackend.api.features.authentication.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, Int> {
    fun findByAccountCode(username: String): Account?
    fun findAllByAccountIdIn(ids: List<Int>): List<Account>
    fun findAllByDepartment(department: String): List<Account>

    @Modifying
    @Query("UPDATE Account a SET a.fcmToken = :newFcmToken WHERE a.accountId = :accountId")
    fun updateAccountFCM(accountId: Long, newFcmToken: String)


//    @Query("SELECT NEW com.apps.prog.appprosbackend.api.features.authentication.model.dto.UserDto(u.id, u.fullName) FROM lms_users u where u.department = :department")
}
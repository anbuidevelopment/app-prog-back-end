package com.apps.prog.appprosbackend.api.features.authentication.model

import com.apps.prog.appprosbackend.api.features.authentication.entity.Account
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

data class AuthRequest(
    val username: String,
    val password: String
)


data class AccountRequest(
    val accountId: Int,
    val accountCode: String,
    val accountName: String,
    val password: String,
    val accountType: Short? = null,
    val status: Short? = null,
    val country: String?,
    val department: String? = null,
    val email: String? = null,
    val fcmToken: String? = null,
    val token: String? = null,
    val gender: Short? = null,
    val isActive: Boolean = false,
    val createdBy: Int? = null,
    val createdDate: String,
    val updatedBy: Int? = null,
    val updatedDate: String,
) {
   fun toAccount() = Account(
    accountId = this.accountId,
    accountCode = this.accountCode,
    accountName = this.accountName,
    password = this.password.toByteArray(),
    accountType = this.accountType,
    status = this.status,
    country = this.country,
    department = this.department,
    email = this.email,
    token = this.token,
    gender = this.gender,
    isActive = this.isActive,
    createdBy = this.createdBy,
    createdDate = this.createdDate,
    updatedBy = this.updatedBy,
    updatedDate = this.updatedDate,
    )
}
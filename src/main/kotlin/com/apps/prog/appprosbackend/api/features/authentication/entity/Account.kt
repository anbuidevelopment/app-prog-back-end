package com.apps.prog.appprosbackend.api.features.authentication.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "Account")
@Table(name = "Account", schema = "app")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountId")
    val accountId: Int,

    @Column(name = "AccountCode", nullable = false)
    val accountCode: String,

    @Column(name = "AccountName", nullable = false)
    val accountName: String,

    @Lob
    @JsonIgnore
    @Column(name = "Password")
    val password: ByteArray,

    @Column(name = "AccountType")
    val accountType: Short? = null,

    @Column(name = "Status")
    val status: Short? = null,

    @Column(name = "Country")
    val country: String?,

    @Column(name = "Department")
    var department: String? = null,

    @Column(name = "Email")
    var email: String? = null,

    @Column(name = "FCMToken")
    var fcmToken: String? = null,

    @Column(name = "Token")
    var token: String? = null,

    @Column(name = "Gender")
    var gender: Short? = null,

    @Column(name = "IsActive", nullable = false)
    var isActive: Boolean = false,

    @Column(name = "CreatedBy", nullable = false)
    var createdBy: Int? = null,

    @Column(name = "CreatedDate", nullable = false)
    var createdDate: String,

    @Column(name = "UpdatedBy", nullable = false)
    var updatedBy: Int? = null,

    @Column(name = "UpdatedDate", nullable = false)
    var updatedDate: String,
)
package com.apps.prog.appprosbackend.api.features.posize

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "sahasm")
data class PoSize(
    @Id
    @Column(name = "SizeID")
    val sizeId: Int,

    @Column(name = "Sizx")
    val size: String,

    @Column(name = "OrderNo")
    val orderNo: String
)

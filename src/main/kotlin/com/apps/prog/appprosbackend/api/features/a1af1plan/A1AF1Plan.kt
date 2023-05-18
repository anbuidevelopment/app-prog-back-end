package com.apps.prog.appprosbackend.api.features.a1af1plan

import jakarta.persistence.*
import org.hibernate.annotations.JoinFormula

@Entity
@Table(name = "A1AF1Plan")
data class A1AF1Plan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PONO")
    val pono: String,
    @Column(name = "Line")
    val line: String,
    @Column(name = "Sub_No")
    val job: String,
    @Column(name = "Job_No")
    val orderNo: String,
    @Column(name = "Style")
    val style: String,
    @Column(name = "Type")
    val type: String,
    @Column(name = "Color")
    val color: String,
    @Column(name = "Season")
    val season: String,
    @Column(name = "Order_Qty")
    val orderQty: Long,
    @Column(name = "DecorAction")
    val decoration: String,
    @Column(name = "Shipment_Date")
    val shipmentDate: String,
)

package com.apps.prog.appprosbackend.api.features.spmstempoutput1

import jakarta.persistence.*

@Entity
@Table(name = "A1AF1Plan")
data class SPMSTempOutput1(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PO_NO")
    val pono: String,
    @Column(name = "JOB_NO")
    val job: String,
    @Column(name = "COLOR1")
    val colorCode: String,
    @Column(name = "COLOR")
    val colorText: String,
    @Column(name = "A1PIN")
    val a1pin: String,
    @Column(name = "A1POUT")
    val a1out: String,
    @Column(name = "BONIN")
    val bonIn: String,
    @Column(name = "BONOUT")
    val bonOut: String,
    @Column(name = "HEAT")
    val heatIn: String,
    @Column(name = "OutputHT")
    val heatOut: String,
    @Column(name = "EMB")
    val embIn: String,
    @Column(name = "OutputEMB")
    val embOut: String,
    @Column(name = "PP")
    val ppIn: String,
    @Column(name = "OutputPP")
    val ppOut: String,
    @Column(name = "SPMKIn")
    val supperMarketIn: String,
    @Column(name = "SPMKOut")
    val supperMarketOut: String,
    @Column(name = "Shipment_Date")
    val shipmentDate: String,
)


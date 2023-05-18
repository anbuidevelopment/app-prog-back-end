package com.apps.prog.appprosbackend.api.features.a1af1plan

import java.sql.Timestamp

data class PoDetailDto(
    val line: String,
    val jobNo: String,
    val so: String,
    val styleNo: String,
    val poNo: String,
    val color: String,
    val type: String,
    val orderQty: Double,
    val a1pIn: Int,
    val a1pOut: Int,
    val bonIn: Int,
    val bonOut: Int,
    val heat: Int,
    val outputHT: Int,
    val emb: Int,
    val outputEMB: Int,
    val pp: Int,
    val outputPP: Int,
    val supperMarketIn: Double,
    val supperMarketOut: Double,
    val startSewing: Any,
    val endSewing: Any,
    val shipmentDate: Any,
    val inSew: Int
)

package com.apps.prog.appprosbackend.api.features.lms.fcm

data class FcmDataResponse(
    val pageIndex: Int,
    val rowsPerPage: Int,
    val totalCount: Long,
    val totalPage: Int,
    val items: List<FCMDataEntity>
)

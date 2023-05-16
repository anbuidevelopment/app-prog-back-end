package com.apps.prog.appprosbackend.api.common

data class DataListPage<T>(
    val totalElements: Long,
    val totalPages: Int,
    val dataList: List<T>
)

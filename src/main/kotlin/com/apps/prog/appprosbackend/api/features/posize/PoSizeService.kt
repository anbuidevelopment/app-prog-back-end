package com.apps.prog.appprosbackend.api.features.posize

import org.springframework.stereotype.Service

@Service
class PoSizeService(private val poSizeRepository: PoSizeRepository) {

    fun findAllByOrderNo(orderNo: String) = poSizeRepository.findAllByOrderNo(orderNo)
}
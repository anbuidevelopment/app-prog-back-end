package com.apps.prog.appprosbackend.api.features.a1af1plan

import com.apps.prog.appprosbackend.api.common.DataListPage
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.IOException
import java.io.Reader
import java.sql.Clob
import java.sql.Date
import java.sql.SQLException
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class A1AF1PlanService(private val repository: com.apps.prog.appprosbackend.api.features.a1af1plan.A1AF1PlanRepository) {
    @Autowired
    lateinit var entityManagerFactory: EntityManagerFactory

    fun getListA1AF1Plan(
        page: Int = 0,
        rowsPerPage: Int = 10,
        pono: String = "",
        fromDate: String = "",
        toDate: String = "",
        line: String = "",
    ): ResponseEntity<Any> {
        val pageRequest = PageRequest.of(page, rowsPerPage)
        val dataMapToPage = when {
            pono.isBlank() && line.isBlank() -> repository.findAllByShipmentDateBetween(fromDate, toDate, pageRequest)
            pono.isNotBlank() && line.isBlank() -> repository.findAllByPonoContainingIgnoreCaseAndShipmentDateBetween(
                pono,
                fromDate,
                toDate,
                pageRequest
            )

            pono.isBlank() && line.isNotBlank() -> repository.findAllByLineContainingIgnoreCaseAndPonoContainingIgnoreCaseAndShipmentDateBetween(
                line,
                pono,
                fromDate,
                toDate,
                pageRequest
            )

            else -> repository.findAllByLineContainingIgnoreCaseAndShipmentDateBetween(
                line,
                fromDate,
                toDate,
                pageRequest
            )
        }

        val dataList = dataMapToPage.content.distinctBy { it.pono }

        val dataListPage = DataListPage(
            totalElements = dataMapToPage.totalElements,
            totalPages = dataMapToPage.totalPages,
            dataList = dataList
        )

        return ResponseEntity.ok(dataListPage)

    }

    fun findPoDetail(factory: String, pono: String): ResponseEntity<Any> {
        val entityManager = entityManagerFactory.createEntityManager()
        val query = entityManager.createNativeQuery(Constants.poDetailQuery)
        query.setParameter(1, pono)
        query.setParameter(2, factory)
        @Suppress("UNCHECKED_CAST")
        val data = query.resultList.map { row ->
            val columns = row as Array<Any>
            PoDetailDto(
                line = columns[0] as String,
                jobNo = columns[1] as String,
                so = columns[2] as String,
                styleNo = columns[3] as String,
                poNo = columns[4] as String,
                color = columns[5] as String,
                type = columns[6] as String,
                orderQty = (columns[7] as Double).toDouble(),
                a1pIn = (columns[8] as Int).toInt(),
                a1pOut = (columns[9] as Int).toInt(),
                bonIn = (columns[10] as Int).toInt(),
                bonOut = (columns[11] as Int).toInt(),
                heat = (columns[12] as Int).toInt(),
                outputHT = (columns[13] as Int).toInt(),
                emb = (columns[14] as Int).toInt(),
                outputEMB = (columns[15] as Int).toInt(),
                pp = (columns[16] as Int).toInt(),
                outputPP = (columns[17] as Int).toInt(),
                supperMarketIn = (columns[18] as Double).toDouble(),
                supperMarketOut = (columns[19] as Double).toDouble(),
                startSewing = columns[20] as Timestamp,
                endSewing = columns[21] as Timestamp,
                shipmentDate = columns[22] as Timestamp,
                inSew = (columns[23] as Int).toInt()
            )
        }

        return ResponseEntity.ok(data.map {
            it.copy(
                startSewing = convertLongToTime(it.startSewing as Timestamp),
                endSewing = convertLongToTime(it.endSewing as Timestamp),
                shipmentDate = convertLongToTime(it.shipmentDate as Timestamp)
            )
        })
    }
}

fun convertLongToTime(time: Timestamp): String {
    val localDateTime = time.toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return localDateTime.format(formatter)
}



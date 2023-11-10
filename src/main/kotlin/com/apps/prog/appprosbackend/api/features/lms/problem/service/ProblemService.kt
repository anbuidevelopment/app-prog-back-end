package com.apps.prog.appprosbackend.api.features.lms.problem.service

import com.apps.prog.appprosbackend.api.exception.AppException
import com.apps.prog.appprosbackend.api.features.lms.problem.model.*
import com.apps.prog.appprosbackend.api.features.lms.problem.model.request.ProblemRequest
import com.apps.prog.appprosbackend.api.features.lms.problem.model.request.ProblemTicketDTO
import com.apps.prog.appprosbackend.api.features.lms.problem.repository.*
import com.apps.prog.appprosbackend.api.features.lms.task_manager.TaskService
import com.apps.prog.appprosbackend.config.WebClientConfig
import com.apps.prog.appprosbackend.utils.ErrorConstants
import com.apps.prog.appprosbackend.utils.formatToString
import com.apps.prog.appprosbackend.utils.getMsg
import com.apps.prog.appprosbackend.utils.parseDate
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.reactive.awaitLast
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ProblemService(
    private val reasonResp: ReasonResp,
    private val solutionResp: SolutionResp,
    private val problemTicketResp: ProblemTicketResp,
    private val problemTicketReasonResp: ProblemTicketReasonResp,
    private val problemTicketSolutionResp: ProblemTicketSolutionResp,
    private val messageSource: MessageSource,
    private val webClientConfig: WebClientConfig,
) {
    private val logger = LoggerFactory.getLogger(TaskService::class.java)

    fun findProblemTicketsByDateRange(startDate: String, endDate: String): List<ProblemTicketDTO> {
        val startDateLocal = startDate.parseDate()
        val endDateLocal = endDate.parseDate()
        if (startDateLocal != null && endDateLocal != null) {
            val list = problemTicketResp.findProblemTicketsByDateRange(startDateLocal, endDateLocal)
            val arrayOfArrays = Gson().fromJson(Gson().toJson(list), Array<Array<Any>>::class.java)
            return arrayOfArrays.map { array ->
                ProblemTicketDTO(
                    problemTicketId = (array[0] as Double).toLong(),
                    problemTicketCode = array[1].toString(),
                    departmentCode = array[2].toString(),
                    problemTicketTitle = array[3].toString(),
                    problemTicketContent = array[4].toString(),
                    problemTicketStatus = array[5].toString(),
                    createDate = convertDateToFormattedString(array[6].toString()),
                    startTime = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    endTime = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    reason = array[9].toString(),
                    solution = array[10].toString()
                )
            }
        }
        return emptyList();
    }

    fun convertDateToFormattedString(dateString: String): String {
        val inputFormat = SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }

    suspend fun saveForm(rq: ProblemRequest) {
        try {
            logger.info("S {}", rq)
            runBlocking {
                val reasonId = async { getReasonId(rq) }
                val solutionId = async { getSolutionId(rq) }
                val problemId = async { getProblemTicketId(rq) }

                problemTicketReasonResp.save(
                    ProblemTicketReasonModel(
                        problemTicketId = problemId.await(),
                        reasonId = reasonId.await(),
                        createdBy = rq.createdBy,
                    )
                )

                problemTicketSolutionResp.save(
                    ProblemTicketSolutionModel(
                        problemTicketId = problemId.await(),
                        solutionId = solutionId.await(),
                        createdBy = rq.createdBy
                    )
                )

            }

            val apiResponse = webClientConfig.webClient()
                .put()
                .uri("/api/v1/notify/${rq.esId}/markHasSolution")
                .retrieve()
                .toBodilessEntity()
                .awaitLast()
            if (apiResponse.statusCode.isError) {
                logger.error("HTTP PUT request failed with status code: ${apiResponse.statusCode}")
            }
        } catch (ex: Exception) {
            logger.error("Error in saveForm: ${ex.message}", ex)
            throw AppException(message = messageSource.getMsg(ErrorConstants.ERROR_PROBLEM_SUBMIT))
        }
    }

    fun fetchReason(): List<ReasonModel> = reasonResp.findAll()
    fun fetchSolution(): List<SolutionModel> = solutionResp.findAll()
    private suspend fun getProblemTicketId(rq: ProblemRequest): Long {
        val problem = problemTicketResp.save(
            ProblemTicketModel(
                departmentCode = rq.departmentCode,
                problemTicketCode = rq.problemTicketCode,
                problemTicketTitle = rq.problemTicketTitle,
                problemTicketContent = rq.problemTicketContent,
                problemTicketStatus = rq.problemTicketStatus,
                updatedBy = rq.createdBy,
                createdBy = rq.createdBy,
                startTime = LocalDateTime.now().formatToString(),
            )
        )
        return problem.problemTicketId!!
    }

    private suspend fun getSolutionId(rq: ProblemRequest): Long {
        val solution = solutionResp.findById(rq.solutionId).orElse(null)
        if (solution == null) {
            val solutionRequest = solutionResp.save(
                SolutionModel(
                    departmentType = rq.departmentType,
                    solutionCode = rq.solutionCode,
                    solutionDescription = rq.solutionDesc,
                    solutionType = rq.type,
                    createdBy = rq.createdBy,
                    updatedBy = rq.createdBy
                )
            )
            return solutionRequest.solutionId!!
        }
        return solution.solutionId!!
    }

    private suspend fun getReasonId(rq: ProblemRequest): Long {
        val reason = reasonResp.findById(rq.reasonId).orElse(null)
        if (reason == null) {
            val reasonRequest = reasonResp.save(
                ReasonModel(
                    departmentType = rq.departmentType,
                    reasonCode = rq.reasonCode,
                    reasonDescription = rq.reasonDesc,
                    reasonType = rq.type,
                    createdBy = rq.createdBy,
                    updatedBy = rq.createdBy
                ),
            )
            return reasonRequest.reasonId!!
        }

        return reason.reasonId!!
    }
}
package com.apps.prog.appprosbackend.api.features.lms.problem.repository;

import com.apps.prog.appprosbackend.api.features.lms.problem.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ProblemTicketSolutionResp : JpaRepository<ProblemTicketSolutionModel, Long> {

}

@Repository
interface ProblemTicketReasonResp : JpaRepository<ProblemTicketReasonModel, Long> {

}

@Repository
interface ProblemTicketResp : JpaRepository<ProblemTicketModel, Long> {
    @Query(value = """
        SELECT 
            pb.ProblemTicketId,
            pb.ProblemTicketCode,
            pb.DepartmentCode,
            pb.ProblemTicketTitle,
            pb.ProblemTicketContent,
            pb.ProblemTicketStatus,
            pb.CreatedDate,
            pb.StartTime,
            pb.EndTime,
            CAST(ISNULL(STUFF((SELECT ', ' + r.ReasonDescription
                               FROM prb.ProblemTicketReason pr WITH(NOLOCK)
                               INNER JOIN prb.Reason r WITH(NOLOCK) ON pr.ReasonId = r.ReasonId
                               WHERE pb.ProblemTicketId = pr.ProblemTicketId
                               FOR XML PATH('')), 1, 2, ''), '') AS NVARCHAR(500)) AS Reason,
            CAST(ISNULL(STUFF((SELECT ', ' + s.SolutionDescription
                               FROM prb.ProblemTicketSolution ps WITH(NOLOCK)
                               INNER JOIN prb.Solution s WITH(NOLOCK) ON ps.SolutionId = s.SolutionId
                               WHERE pb.ProblemTicketId = ps.ProblemTicketId
                               FOR XML PATH('')), 1, 2, ''), '') AS NVARCHAR(500)) AS Solution
        FROM prb.ProblemTicket pb WITH(NOLOCK)
        WHERE pb.CreatedDate >= :startDate AND pb.CreatedDate < :endDate
    """, nativeQuery = true)
    fun findProblemTicketsByDateRange(@Param("startDate") startDate: LocalDate, @Param("endDate") endDate: LocalDate): List<Any>



}

@Repository
interface SolutionResp : JpaRepository<SolutionModel, Long> {}

@Repository
interface ReasonResp : JpaRepository<ReasonModel, Long> {}
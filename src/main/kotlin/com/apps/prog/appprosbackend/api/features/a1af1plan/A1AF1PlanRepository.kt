package com.apps.prog.appprosbackend.api.features.a1af1plan

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface A1AF1PlanRepository : JpaRepository<com.apps.prog.appprosbackend.api.features.a1af1plan.A1AF1Plan, Long> {
    fun findAllByPonoContainingIgnoreCase(pono: String, page: Pageable): Page<com.apps.prog.appprosbackend.api.features.a1af1plan.A1AF1Plan>

    fun findAllByShipmentDateBetween(fromDate: String, toDate: String, page: Pageable): Page<com.apps.prog.appprosbackend.api.features.a1af1plan.A1AF1Plan>

//    @Query(
//        value = "select distinct a.Line,a.Sub_No  JOB_NO,a.Job_No  SO,\n" +
//                "a.Style STYLE_NO,a.PONO PO_NO,a.Color,a.Type,a.Order_Qty ,isnull(b.A1PIN,0) A1PIN,isnull(b.A1POUT,0) A1POUT,\n" +
//                "isnull(b.BONIN,0) BONIN,isnull(b.BONOUT,0) BONOUT,isnull(b.HEAT,0) HEAT,isnull(b.OutputHT,0) OutputHT,\n" +
//                "isnull(b.EMB,0) EMB,isnull(b.OutputEMB,0) OutputEMB,isnull(b.PP,0) PP,isnull(b.OutputPP,0) OutputPP,isnull(b.SPMKIn,0) SPMKIn,isnull(b.SPMKOut,0) SPMKOut,a.Start_Sewing,a.End_Sewing,a.Shipment_Date,b.insew\n" +
//                " from A1AF1Plan a\n" +
//                " INNER JOIN SPMSTempOutput1 b ON a.Sub_No = b.JOB_NO\n" +
//                " where a.Sub_No=b.JOB_NO and a.PONO like '%'+b.PO_NO and SUBSTRING(a.Line,1,2)='F2' \n" +
//                " and a.End_Sewing between getdate()-3 and getdate()+7 ", nativeQuery = true
//    )
}
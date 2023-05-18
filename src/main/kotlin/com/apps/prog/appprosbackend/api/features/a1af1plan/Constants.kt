package com.apps.prog.appprosbackend.api.features.a1af1plan

object Constants {

    const val poDetailQuery =  "SELECT DISTINCT\n" +
            "    a.Line,\n" +
            "    a.Sub_No AS JOB_NO,\n" +
            "    a.Job_No AS SO,\n" +
            "    a.Style AS STYLE_NO,\n" +
            "    a.PONO AS PO_NO,\n" +
            "    a.Color,\n" +
            "    a.Type,\n" +
            "    a.Order_Qty,\n" +
            "    ISNULL(b.A1PIN, 0) AS A1PIN,\n" +
            "    ISNULL(b.A1POUT, 0) AS A1POUT,\n" +
            "    ISNULL(b.BONIN, 0) AS BONIN,\n" +
            "    ISNULL(b.BONOUT, 0) AS BONOUT,\n" +
            "    ISNULL(b.HEAT, 0) AS HEAT,\n" +
            "    ISNULL(b.OutputHT, 0) AS OutputHT,\n" +
            "    ISNULL(b.EMB, 0) AS EMB,\n" +
            "    ISNULL(b.OutputEMB, 0) AS OutputEMB,\n" +
            "    ISNULL(b.PP, 0) AS PP,\n" +
            "    ISNULL(b.OutputPP, 0) AS OutputPP,\n" +
            "    ISNULL(b.SPMKIn, 0) AS SPMKIn,\n" +
            "    ISNULL(b.SPMKOut, 0) AS SPMKOut,\n" +
            "    a.Start_Sewing,\n" +
            "    a.End_Sewing,\n" +
            "    a.Shipment_Date,\n" +
            "    b.insew\n" +
            "FROM\n" +
            "    A1AF1Plan a\n" +
            "    INNER JOIN SPMSTempOutput1 b ON a.Sub_No = b.JOB_NO\n" +
            "WHERE\n" +
            "    a.Sub_No = b.JOB_NO\n" +
            "    AND a.PONO = ?1   \n"+
            "    AND a.PONO LIKE '%' + b.PO_NO\n" +
            "    AND SUBSTRING(a.Line, 1, 2) = ?2 \n" +
            "    AND a.End_Sewing BETWEEN DATEADD(DAY, -3, GETDATE()) AND DATEADD(DAY, 7, GETDATE())\n"
}
package com.apps.prog.appprosbackend.api.features.task_manager_modifer

object Constants {

    const val queryAllTasks =
        " SELECT DISTINCT nc.task_id, nc.due_date, nc.start_time, nc.end_time, nc.is_completed\n" +
                "        FROM lms_normal_check_ins nc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = nc.task_id\n" +
                "        WHERE ta.assigned_to_user_id = ?1 \n" +
                "        UNION ALL\n" +
                "        SELECT dc.task_id, dc.check_in_date AS due_date, NULL AS start_time, NULL AS end_time, dc.is_completed\n" +
                "        FROM lms_daily_check_ins dc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = dc.task_id\n" +
                "        WHERE ta.assigned_to_user_id = ?1  \n" +
                "        UNION ALL\n" +
                "        SELECT wc.task_id, wc.check_in_week AS due_date, NULL AS start_time, NULL AS end_time, wc.is_completed\n" +
                "        FROM lms_weekly_check_ins wc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = wc.task_id\n" +
                "        WHERE ta.assigned_to_user_id = ?1 \n" +
                "        UNION ALL\n" +
                "        SELECT mc.task_id, mc.check_in_month AS due_date, NULL AS start_time, NULL AS end_time, mc.is_completed\n" +
                "        FROM lms_monthly_check_ins mc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = mc.task_id\n" +
                "        WHERE ta.assigned_to_user_id = ?1 "


    const val queryAllTasksOfStaff =
        " SELECT DISTINCT nc.task_id, nc.due_date, nc.start_time, nc.end_time, nc.is_completed\n" +
                "        FROM lms_normal_check_ins nc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = nc.task_id\n" +
                "        WHERE ta.assigned_by_user_id = ?1 \n"


    const val queryTasksByUserAndDate =
        " SELECT nc.task_id, nc.due_date, nc.start_time, nc.end_time, nc.is_completed\n" +
                "        FROM lms_normal_check_ins nc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = nc.task_id\n" +
                "        WHERE ta.assigned_to_user_id = ?1  AND nc.due_date = ?2 \n" +
                "        UNION ALL\n" +
                "        SELECT dc.task_id, dc.check_in_date AS due_date, NULL AS start_time, NULL AS end_time, dc.is_completed\n" +
                "        FROM lms_daily_check_ins dc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = dc.task_id\n" +
                "        WHERE ta.assigned_to_user_id = ?1   AND dc.check_in_date = ?2\n" +
                "        UNION ALL\n" +
                "        SELECT wc.task_id, wc.check_in_week AS due_date, NULL AS start_time, NULL AS end_time, wc.is_completed\n" +
                "        FROM lms_weekly_check_ins wc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = wc.task_id\n" +
                "        WHERE ta.assigned_to_user_id = ?1 AND wc.check_in_week = ?2 \n" +
                "        UNION ALL\n" +
                "        SELECT mc.task_id, mc.check_in_month AS due_date, NULL AS start_time, NULL AS end_time, mc.is_completed\n" +
                "        FROM lms_monthly_check_ins mc\n" +
                "        INNER JOIN lms_task_assignment ta ON ta.task_id = mc.task_id\n" +
                "        WHERE ta.assigned_to_user_id = ?1  AND mc.check_in_month = ?2"

}

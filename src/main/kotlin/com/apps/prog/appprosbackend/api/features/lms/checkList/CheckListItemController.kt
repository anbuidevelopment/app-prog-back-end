package com.apps.prog.appprosbackend.api.features.lms.checkList

import com.apps.prog.appprosbackend.api.features.lms.checkList.entity.ChecklistItemCompletion
import com.apps.prog.appprosbackend.api.features.lms.checkList.model.ChecklistRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/checklistItem")
class CheckListItemController(private val service: CheckListItemService) {
    @PostMapping
    fun getCheckListItems(@RequestBody body: ChecklistRequest) = service.getAllCheckListItemsForChecklist(body)

    @PostMapping("/markComplete")
    fun checkToComplete(@RequestBody body: List<ChecklistItemCompletion>) = service.checkToComplete(body)
}
package com.apps.prog.appprosbackend.api.features.authentication

import com.apps.prog.appprosbackend.api.features.authentication.entity.Account
import com.apps.prog.appprosbackend.api.features.authentication.model.AccountIdRequest
import com.apps.prog.appprosbackend.api.features.authentication.model.AccountRequest
import com.apps.prog.appprosbackend.api.features.authentication.model.AuthRequest
import com.apps.prog.appprosbackend.api.features.authentication.model.ChangePasswordRequest
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class AccountController(private val accountService: AccountService) {
    @PostMapping("/authenticate")
    fun signIn(@RequestBody body: AuthRequest) =
        accountService.findUserByUsernameAndPassword(body.username, body.password)

    @PutMapping("/{accountId}/{fcmToken}")
    fun updateAccountFcm(@PathVariable("accountId") accountId: Long, @PathVariable("fcmToken") fcmToken: String) =
        accountService.updateAccountFCMToken(accountId, fcmToken)

    @GetMapping("/atDepartment")
    fun findAllByDepartment(@RequestParam("department") department: String) =
        accountService.findAllByDepartment(department)

    @PostMapping
    fun findAllByAccountIdIn(@RequestBody accountIdRequest: AccountIdRequest) =
        accountService.findAllByAccountIdIn(accountIdRequest.accountIds)

    @GetMapping
    fun findAccountById(@RequestParam("accountId") accountId: Int) = accountService.findById(accountId)

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody account: AccountRequest) = accountService.createAccount(account)

    @PutMapping("/change-password")
    fun changePassword(@RequestBody changePasswordRequest: ChangePasswordRequest) =
        accountService.changePassword(changePasswordRequest)

}
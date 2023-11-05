package com.apps.prog.appprosbackend.api.features.authentication

import com.apps.prog.appprosbackend.api.exception.AppAuthException
import com.apps.prog.appprosbackend.api.exception.AppException
import com.apps.prog.appprosbackend.api.features.authentication.entity.Account
import com.apps.prog.appprosbackend.api.features.authentication.model.AccountRequest
import com.apps.prog.appprosbackend.api.features.authentication.model.ChangePasswordRequest
import com.apps.prog.appprosbackend.utils.ErrorConstants
import com.apps.prog.appprosbackend.utils.getMsg
import org.mindrot.jbcrypt.BCrypt
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val messageSource: MessageSource,
) {

    @Transactional
    fun updateAccountFCMToken(accountId: Long, newFCMToken: String) {
        accountRepository.updateAccountFCM(accountId, newFCMToken)
    }

    fun findAllByAccountIdIn(ids: List<Int>) = accountRepository.findAllByAccountIdIn(ids)
    fun findAllByDepartment(department: String) = accountRepository.findAllByDepartment(department)
    fun findUserByUsernameAndPassword(username: String, password: String): Account {
        val account = accountRepository.findByAccountCode(username)
        if (account != null) {
            val passwordInDb: ByteArray = account.password
            val isAuthenticated = BCrypt.checkpw(password, String(passwordInDb))
            if (isAuthenticated) return account
        }
        throw AppAuthException(message = messageSource.getMsg(ErrorConstants.ERROR_AUTHENTICATE))
    }

    fun createAccount(rq: AccountRequest): Int {
        val hashPassword = BCrypt.hashpw(String(rq.password.toByteArray()), BCrypt.gensalt())
        val createdAccount = accountRepository.save(rq.toAccount().copy(password = hashPassword.toByteArray()))
        return createdAccount.accountId;
    }

    @Throws(AppException::class)
    fun changePassword(rq: ChangePasswordRequest) {
        val account = accountRepository.findById(rq.id).orElseThrow {
            AppException(
                message = messageSource.getMsg(
                    ErrorConstants.ERROR_USER_NOT_FOUND,
                    arrayOf(rq.id)
                )
            )
        }
        val storedPassword = account.password
        if (BCrypt.checkpw(rq.oldPassword.trim(), String(storedPassword))) {
            val newHashedPassword = BCrypt.hashpw(rq.newPassword.trim(), BCrypt.gensalt())
            accountRepository.save(account.copy(password = newHashedPassword.toByteArray()))
        } else {
            throw AppException(message = messageSource.getMsg(ErrorConstants.ERROR_CHANGE_PASSWORD))
        }
    }

    fun findById(accountId: Int) = accountRepository.findById(accountId)
}

package br.com.lucascosta.study_kotlin.service.impl

import br.com.lucascosta.study_kotlin.entity.Account
import br.com.lucascosta.study_kotlin.repository.AccountRepository
import br.com.lucascosta.study_kotlin.service.AccountService
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.util.*

@Service
class AccountServiceImpl(private val repository: AccountRepository) : AccountService {

    override fun create(account: Account): Account {
        Assert.hasLength(account.name, "[name] cannot be empty!")
        Assert.isTrue(account.name.length >= 5, "[name] must be at least 5 characters long!")

        Assert.hasLength(account.document, "[document] cannot be empty!")
        Assert.isTrue(account.document.length >= 11, "[document] must be at least 11 characters long!")

        Assert.hasLength(account.phone, "[phone] cannot be empty!")
        Assert.isTrue(account.phone.length >= 11, "[phone] must be at least 11 characters long!")

        return repository.save(account)
    }

    override fun getAll(): List<Account> {
        return repository.findAll()
    }

    override fun getById(id: Long): Optional<Account> {
        return repository.findById(id)
    }

    override fun update(id: Long, account: Account): Optional<Account> {
        val optional = getById(id)
        if (optional.isEmpty) Optional.empty<Account>()

        return optional.map {
            val accountToUpdate = it.copy(
                name = account.name,
                document = account.document,
                phone = account.phone
            )
            repository.save(accountToUpdate)
        }
    }

    override fun delete(id: Long) {
        getById(id).map {
            repository.delete(it)
        }.orElseThrow { throw RuntimeException("Id: $id, not found") }
    }
}
package br.com.lucascosta.study_kotlin.controller

import br.com.lucascosta.study_kotlin.entity.Account
import br.com.lucascosta.study_kotlin.repository.AccountRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(val repository: AccountRepository) {

    @PostMapping
    fun create(@RequestBody account: Account): ResponseEntity<Account> = ResponseEntity.ok(repository.save(account))

    @GetMapping
    fun getAll() = ResponseEntity.ok(repository.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Account> =
        repository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody account: Account): ResponseEntity<Account> =
        repository.findById(id).map {
            val accountToUpdate = it.copy(
                name = account.name,
                document = account.document,
                phone = account.phone
            )
            ResponseEntity.ok(repository.save(accountToUpdate))
        }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> =
        repository.findById(id).map {
            repository.delete(it)
            ResponseEntity<Unit>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
}
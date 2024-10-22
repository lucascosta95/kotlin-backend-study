package br.com.lucascosta.study_kotlin.repository

import br.com.lucascosta.study_kotlin.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>
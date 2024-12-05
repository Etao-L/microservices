package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


}

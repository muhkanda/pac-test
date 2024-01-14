package com.kanda.pac.tiga.service;

import com.kanda.pac.tiga.domain.Account;
import com.kanda.pac.tiga.repository.AccountRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Dependent
public class AccountService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

}

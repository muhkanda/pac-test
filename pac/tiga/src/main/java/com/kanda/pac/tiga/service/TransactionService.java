package com.kanda.pac.tiga.service;

import com.kanda.pac.tiga.domain.Account;
import com.kanda.pac.tiga.domain.Transaction;
import com.kanda.pac.tiga.repository.AccountRepository;
import com.kanda.pac.tiga.repository.TransactionRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Dependent
public class TransactionService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TransactionRepository transactionRepository;
    @Inject
    private AccountRepository accountRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public void tarikTunai(Account account, BigDecimal amount) {
        BigDecimal saldoAwal = account.getBalance();
        BigDecimal saldoAkhir = saldoAwal.subtract(amount);
        account.setBalance(saldoAkhir);
        accountRepository.updateAccount(account);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionTimestamp(new Date());
        transaction.setType("TARIK TUNAI");
        transaction.setDebitCredit("DEBIT");
        transaction.setNoRekening(account.getAccountId());
        transactionRepository.saveLog(transaction);
    }

    public void setorTunai(Account account, BigDecimal amount) {
        BigDecimal saldoAwal = account.getBalance();
        BigDecimal saldoAkhir = saldoAwal.add(amount);
        account.setBalance(saldoAkhir);
        accountRepository.updateAccount(account);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionTimestamp(new Date());
        transaction.setType("SETOR TUNAI");
        transaction.setDebitCredit("CREDIT");
        transaction.setNoRekening(account.getAccountId());
        transactionRepository.saveLog(transaction);
    }

    public void transfer(Account account, BigDecimal amount, String beneficiaryId) {
        Account beneficiary = accountRepository.find(beneficiaryId);
        BigDecimal saldoAwalBeneficiary = beneficiary.getBalance();
        BigDecimal saldoAkhirBeneficiary = saldoAwalBeneficiary.add(amount);
        beneficiary.setBalance(saldoAkhirBeneficiary);
        accountRepository.updateAccount(beneficiary);
        BigDecimal saldoAwalSource = account.getBalance();
        BigDecimal saldoAkhirSource = saldoAwalSource.subtract(amount);
        account.setBalance(saldoAkhirSource);
        accountRepository.updateAccount(account);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setTransactionTimestamp(new Date());
        transaction.setType("TRANSFER");
        transaction.setDebitCredit("DEBIT");
        transaction.setNoRekening(account.getAccountId());
        transactionRepository.saveLog(transaction);
        Transaction trx = new Transaction();
        trx.setAmount(amount);
        trx.setTransactionDate(new Date());
        trx.setTransactionTimestamp(new Date());
        trx.setType("TRANSFER");
        trx.setDebitCredit("CREDIT");
        trx.setNoRekening(beneficiary.getAccountId());
        transactionRepository.saveLog(trx);
    }

}

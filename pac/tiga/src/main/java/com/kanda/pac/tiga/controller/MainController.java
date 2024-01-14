package com.kanda.pac.tiga.controller;

import com.kanda.pac.tiga.domain.Account;
import com.kanda.pac.tiga.domain.Transaction;
import com.kanda.pac.tiga.service.AccountService;
import com.kanda.pac.tiga.service.TransactionService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class MainController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @Inject
    private AccountService accountService;
    @Inject
    private TransactionService transactionService;

    private List<Account> accounts;
    private List<Transaction> transactions;
    private List<Account> beneficiaryAccounts;
    private Account selectedAccount;
    private String beneficiaryAccountId;
    private BigDecimal amount;

    @PostConstruct
    public void init() {
        getData();
    }

    private void getData() {
        accounts = accountService.findAll();
        transactions = transactionService.findAll();
        beneficiaryAccounts = new ArrayList<>();
    }

    public void tarikTunai(Account account) {
        LOG.debug("--- tarikTunai: {} ---", account.getAccountId());
        selectedAccount = account;
        amount = BigDecimal.ZERO;
        PrimeFaces.current().executeScript("PF('dlgTarik').show()");
    }

    public void doTarikTunai() {
        LOG.debug("--- doTarikTunai ---");
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            addMessage(FacesMessage.SEVERITY_WARN, "Warning", "Harap masukan jumlah tarik tunai");
        } else {
            if (selectedAccount.getBalance().compareTo(amount) < 0) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Kesalahan", "Saldo Anda Kurang");
            } else {
                transactionService.tarikTunai(selectedAccount, amount);
                addMessage(FacesMessage.SEVERITY_INFO, "Sukses", "Tarik Tunai Berhasil");
                getData();
            }
        }
    }

    public void setorTunai(Account account) {
        LOG.debug("--- setorTunai: {} ---", account.getAccountId());
        selectedAccount = account;
        amount = BigDecimal.ZERO;
        PrimeFaces.current().executeScript("PF('dlgSetor').show()");
    }

    public void doSetorTunai() {
        LOG.debug("--- doSetorTunai ---");
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            addMessage(FacesMessage.SEVERITY_WARN, "Warning", "Harap masukan jumlah setor tunai");
        } else {
            transactionService.setorTunai(selectedAccount, amount);
            addMessage(FacesMessage.SEVERITY_INFO, "Sukses", "Setor Tunai Berhasil");
            getData();
        }
    }

    public void transfer(Account account) {
        LOG.debug("--- transfer ---");
        beneficiaryAccounts.clear();
        selectedAccount = account;
        amount = BigDecimal.ZERO;
        for (Account acc : accounts) {
            if (!account.getAccountId().equalsIgnoreCase(acc.getAccountId())) {
                beneficiaryAccounts.add(acc);
            }
        }
        PrimeFaces.current().executeScript("PF('dlgTransfer').show()");
    }

    public void doTransfer() {
        LOG.debug("--- doTransfer ---");
        LOG.debug("--- beneficiary account: {} ---", beneficiaryAccountId);
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            addMessage(FacesMessage.SEVERITY_WARN, "Warning", "Harap masukan jumlah transfer");
        } else {
            transactionService.transfer(selectedAccount, amount, beneficiaryAccountId);
            addMessage(FacesMessage.SEVERITY_INFO, "Sukses", "Transfer Berhasil");
            getData();
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Account> getBeneficiaryAccounts() {
        return beneficiaryAccounts;
    }

    public void setBeneficiaryAccounts(List<Account> beneficiaryAccounts) {
        this.beneficiaryAccounts = beneficiaryAccounts;
    }

    public String getBeneficiaryAccountId() {
        return beneficiaryAccountId;
    }

    public void setBeneficiaryAccountId(String beneficiaryAccountId) {
        this.beneficiaryAccountId = beneficiaryAccountId;
    }
}

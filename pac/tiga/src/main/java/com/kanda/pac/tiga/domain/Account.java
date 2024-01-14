package com.kanda.pac.tiga.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "Account")
@Entity
@NamedQueries({
        @NamedQuery(name = "Account.findById",
                query = "SELECT c FROM Account c WHERE c.accountId = :accountId"),
        @NamedQuery(name = "Account.findAll",
                query = "SELECT a FROM Account a ORDER BY a.accountId ASC"),
        @NamedQuery(name = "Account.updateAmountById",
                query = "UPDATE Account c SET c.balance = :newbalance WHERE c.accountId = :accountId")
})
public class Account implements Serializable {

    @Id
    @Column(length = 32, nullable = false)
    private String accountId; // Primary Key
    @Column(length = 64)
    private String name;
    @Column(name = "balance")
    private BigDecimal balance;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

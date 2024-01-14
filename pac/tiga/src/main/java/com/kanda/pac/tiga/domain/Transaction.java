package com.kanda.pac.tiga.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Table(name = "Transaction")
@Entity
@NamedQueries({
        @NamedQuery(name = "Transaction.findAll",
                query = "SELECT a FROM Transaction a ORDER BY a.transactionTimestamp DESC")
})
public class Transaction implements Serializable {

    @Id
    @Column(length = 32, nullable = false)
    private String id;
    @Column(length = 32, nullable = false)
    private String type;
    @Column(length = 32, nullable = false)
    private String debitCredit;

    @Column(length = 32, nullable = false)
    private String noRekening;
    private BigDecimal amount;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    private Date transactionDate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Jakarta")
    private Date transactionTimestamp;

    public Transaction() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(Date transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }
}

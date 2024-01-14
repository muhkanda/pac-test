package com.kanda.pac.tiga.repository;

import com.kanda.pac.tiga.domain.Transaction;
import com.kanda.pac.tiga.infrastructure.JpaRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.List;

@Dependent
public class TransactionRepository extends JpaRepository<Transaction, String> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(TransactionRepository.class);

    @Inject
    private EntityManager entityManager;

    public TransactionRepository() {
        super(Transaction.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Transaction> findAll() {
        LOG.debug("--- TransactionRepository findAll ---");
        TypedQuery<Transaction> query = entityManager.createNamedQuery("Transaction.findAll", Transaction.class);
        return query.getResultList();
    }

    @Transactional
    public void saveLog(Transaction transaction) {
        entityManager.persist(transaction);
    }

}

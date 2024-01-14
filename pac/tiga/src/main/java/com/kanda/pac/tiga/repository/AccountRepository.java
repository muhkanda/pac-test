package com.kanda.pac.tiga.repository;

import com.kanda.pac.tiga.domain.Account;
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
public class AccountRepository extends JpaRepository<Account, String> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(AccountRepository.class);

    @Inject
    private EntityManager entityManager;

    public AccountRepository() {
        super(Account.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Account> findAll() {
        LOG.debug("--- AccountRepository findAll ---");
        TypedQuery<Account> query = entityManager.createNamedQuery("Account.findAll", Account.class);
        return query.getResultList();
    }

    @Transactional
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }


}

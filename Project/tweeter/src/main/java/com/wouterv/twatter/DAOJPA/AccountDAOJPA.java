package com.wouterv.twatter.DAOJPA;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.DaoFacade;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
@Stateless
@JPA
public class AccountDAOJPA extends DaoFacade<Account> implements IAccountDAO {

    @PersistenceContext
    EntityManager em;

    public AccountDAOJPA() {
        super(Account.class);
    }

    @Override
    public Account findByUserName(String userName) {
        Query q = em.createQuery("SELECT a FROM Account a where a.userName = :userName");
        q.setParameter("userName", userName);
        return (Account) q.getSingleResult();
    }

    @Override
    public List<Account> search(String name) {
        Query q = em.createQuery("SELECT a FROM Account a where a.firstName like :name or a.lastName like :name");
        q.setParameter("name", "%"+name+"%");
        List<Account> userList = q.getResultList();
        return userList;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}

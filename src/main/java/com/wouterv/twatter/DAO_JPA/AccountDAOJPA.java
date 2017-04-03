package com.wouterv.twatter.DAO_JPA;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.DaoFacade;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Type;

import javax.ejb.Stateless;
import javax.persistence.*;
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
        Account account;
        try {
            Query q = em.createNamedQuery("accountdao.findByUserName");
            q.setParameter("userName", userName);
            account = (Account) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return account;

    }

    @Override
    public List<Account> search(String name) {
        name = name.replace(" ", "%");
        name = "%" + name + "%";
        Query q = em.createNamedQuery("accountdao.search");
        q.setParameter("name", name);
        List<Account> userList = q.getResultList();
        return userList;
    }

    @Override
    public List<Account> getFollowing(int Id) {
        Query q = em.createNamedQuery("accountdao.getFollowing");
        q.setParameter("id", Id);
        List<Account> userList = q.getResultList();
        return userList;
    }

    @Override
    public boolean addRole(Type role, int id) {
        try {
            Account account = findById(id);
            account.addGroup(role);
            em.persist(account);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeRole(Type role, int id) {
        try {
            Account account = findById(id);
            account.removeGroup(role);
            em.persist(account);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}

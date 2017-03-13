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
@NamedQueries(
        {@NamedQuery(name = "accountdao.findByUserName", query = "SELECT a FROM Account a where a.userName = :userName"),
                @NamedQuery(name = "accountdao.search", query = "SELECT a FROM Account a where a.firstName like :name or a.lastName like :name"),
                @NamedQuery(name = "accountdao.getFollowing", query = "SELECT a FROM Account a where :id in (select f.Id from a.following f)")}
)
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
//        Query q = em.createNamedQuery("findByUserName");
        Query q = em.createQuery("SELECT a FROM Account a where a.userName = :userName");
        q.setParameter("userName", userName);
        return (Account) q.getSingleResult();
    }

    @Override
    public List<Account> search(String name) {
        name = name.replace(" ", "%");
        name = "%" + name + "%";
        Query q = em.createQuery("SELECT a FROM Account a where a.firstName like :name  or a.userName like :name");
        q.setParameter("name", name);
        List<Account> userList = q.getResultList();
        return userList;
    }

    @Override
    public List<Account> getFollowing(int Id) {
        Query q = em.createQuery("SELECT a FROM Account a where :id in (select f.Id from a.following f)");
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
        }catch (Exception e){
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
        }catch (Exception e){
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

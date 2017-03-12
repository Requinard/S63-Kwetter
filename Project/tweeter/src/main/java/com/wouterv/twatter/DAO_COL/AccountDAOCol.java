package com.wouterv.twatter.DAO_COL;

import com.wouterv.twatter.DAO.DaoFacade;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */

@Stateless
@Default
public class AccountDAOCol extends DaoFacade<Account> implements IAccountDAO {

    CopyOnWriteArrayList<Account> accounts = new CopyOnWriteArrayList<>();

    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    @Override
    public void create(Account account) {accounts.add(account);
    }

    @Override
    public void remove(Account account) {
        accounts.remove(account);
    }

    @Override
    public ArrayList<Account> getAll() {
        return new ArrayList(accounts);
    }

    @Override
    public Account findById(int id) {
        for (Account a : accounts){
            if(a.getId() == (int)id){
                return a;
            }
        }
        return null;
    }


    @PostConstruct
    public void init(){
        System.out.println("StudentDaoColl");
    }


    public AccountDAOCol() {
        super(Account.class);
    }

    @Override
    public Account findByUserName(String name) {
        for (Account account : accounts) {
            if ((account.getFirstName()+account.getLastName()).contains(name)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> search(String name) {
        ArrayList acc = new ArrayList();
        for (Account account : accounts) {
            if ((account.getFirstName()+account.getLastName()).contains(name)) {
                acc.add(account);
            }
        }
        return acc;
    }

    @Override
    public List<Account> getFollowing(int Id) {
        Account user = findById(Id);
        ArrayList acc = new ArrayList();
        for (Account account : accounts) {
            if (account.getFollowing().contains(user)) {
                acc.add(account);
            }
        }
        return acc;
    }
}



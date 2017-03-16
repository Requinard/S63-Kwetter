package com.wouterv.twatter.DAO_COL;

import com.wouterv.twatter.DAO.DaoFacade;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Type;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
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
    int Id = 0;
    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    @Override
    public void create(Account account) {
        account.setId(Id);
        accounts.add(account);
        Id++;

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
        for (Account a : accounts) {
            if (a.getId() == (int) id) {
                return a;
            }
        }
        return null;
    }

    public AccountDAOCol() {
        super(Account.class);
    }

    @Override
    public Account findByUserName(String name) {
        for (Account account : accounts) {
            if (account.getUserName() == name) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> search(String name) {
        ArrayList acc = new ArrayList();
        for (Account account : accounts) {
            if ((account.getFirstName() + " " + account.getLastName() + " " + account.getUserName()).contains(name)) {
                acc.add(account);
            }
        }
        return acc;
    }

    @Override
    public boolean addRole(Type role, int Id) {
        try {
            Account a = findById(Id);
            a.addGroup(role);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeRole(Type role, int Id) {
        try {
            Account a = findById(Id);
            a.removeGroup(role);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
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

    @Override
    public void edit(Account entity) {
        for (int i=0;i<accounts.size();i++){
            if(accounts.get(i).getId() == entity.getId()){
                accounts.set(i,entity);
                break;
            }
        }
    }

    public void setAccounts(CopyOnWriteArrayList<Account> accounts) {
        this.accounts = accounts;
    }
    public void setId(int id){Id = id;}
}



package com.wouterv.twatter.Service;

import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Stateless
public class AccountService {

    @Inject
    @JPA
    IAccountDAO accountDAO;

    public Account getAccount(int userId) {
        Account account = accountDAO.findById(userId);
        return account;
    }

    public Account getAccountByUsername(String userName) {
        Account account = accountDAO.findByUserName(userName);
        return account;
    }
    public List<Account> search( String userName) {
        List<Account> accounts = accountDAO.search(userName);
        return accounts;
    }

    public boolean login(String username, String password) throws LoginException {//TODO : remove the userId and use JAAS
        throw new NotImplementedException();
    }
}
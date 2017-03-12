package com.wouterv.twatter.Service;

import com.sun.security.auth.callback.TextCallbackHandler;
import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.Models.Account;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
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

    public List<Account> search(String userName) {
        List<Account> accounts = accountDAO.search(userName);
        return accounts;
    }

    public Account create(String username, String email, String bio, String firstName, String lastName) {
        Account account = new Account(username,email,bio,firstName,lastName);
        accountDAO.create(account);
        return account;
    }
    public boolean follow(int toFollowId, int loggedInId){
        try {
            Account toFollow = accountDAO.findById(toFollowId);
            Account loggedIn = accountDAO.findById(loggedInId);
            loggedIn.addFollowing(toFollow);
            accountDAO.edit(loggedIn);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Account> followers(int id) {
        return accountDAO.getFollowing(id);
    }
//Todo : make custom login or use oath or something
//    public boolean login(String username, String password) {
//        try{
//            LoginContext lc =new LoginContext("Test", new TextCallbackHandler());
//            lc.login();
//        }catch (Exception e){
//
//        }
//
//        String a = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
//    }

//    public boolean login(String username, String password) throws LoginException {//TODO : remove the userId and use JAAS
//        throw new NotImplementedException();
//    }
}
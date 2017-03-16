package com.wouterv.twatter.Service;

import com.sun.security.auth.callback.TextCallbackHandler;
import com.wouterv.twatter.Annotations.JPA;
import com.wouterv.twatter.DAO.IAccountDAO;
import com.wouterv.twatter.DAO.ITypeDAO;
import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Models.Type;
import jdk.nashorn.internal.runtime.ECMAException;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static java.awt.SystemColor.text;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Stateless
public class AccountService {

    @Inject
    @JPA
    IAccountDAO accountDAO;
    @Inject
    @JPA
    ITypeDAO typeDAO;

    public List<Account> getAllAccounts() {
        return accountDAO.getAll();
    }

    public Account findByID(int userId) {
        Account account = accountDAO.findById(userId);
        return account;
    }

    public Account findByUsername(String userName) {
        Account account = accountDAO.findByUserName(userName);
        return account;
    }

    public List<Account> search(String userName) {
        List<Account> accounts = accountDAO.search(userName);
        return accounts;
    }

    public Account create(String username, String email, String bio, String firstName, String lastName,String password) {
        //TODO : Make a salted implementation
        Account account = new Account(username, email, bio, firstName, lastName);
        account.setPassword(hashPassword(password));
        accountDAO.create(account);
        return account;
    }

    public boolean follow(int toFollowId, int loggedInId) {
        try {
            Account toFollow = accountDAO.findById(toFollowId);
            Account loggedIn = accountDAO.findById(loggedInId);
            loggedIn.addFollowing(toFollow);
            accountDAO.edit(loggedIn);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean unFollow(int toUnfollowId, int loggedInId) {
        try {
            Account toUnfollow = accountDAO.findById(toUnfollowId);
            Account loggedIn = accountDAO.findById(loggedInId);
            loggedIn.removeFollowing(toUnfollow);
            accountDAO.edit(loggedIn);
        } catch (Exception e) {
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
    public boolean remove(int id) {
        try {
            Account account = accountDAO.findById(id);
            accountDAO.remove(account);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void setTypeDAO(ITypeDAO typeDAO) {
        this.typeDAO = typeDAO;
    }

    public boolean addRole(String role, int id) {
        try {
            Type type = typeDAO.findOrCreate(role);
            accountDAO.addRole(type, id);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public boolean removeRole(String role, int id) {
        try {
            Type type = typeDAO.findOrCreate(role);
            accountDAO.removeRole(type, id);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public boolean edit(int id,String username,String email,String bio,String firstName,String  lastName){
        try {
            Account account = accountDAO.findById(id);
            account.setUserName(username);
            account.setEmail(email);
            account.setBio(bio);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            accountDAO.edit(account);
            return true;
        }catch (Exception e ){
            return false;
        }
    }
    public boolean editPassword(int id,String currentPass, String newPass){
        Account account;
        try {
            account = findByID(id);
            if(account.getPassword()!=hashPassword(currentPass)) {
                return false;
            }
            account.setPassword(hashPassword(newPass));
            accountDAO.edit(account);
            return true;
        }catch (Exception e ){
            return false;
        }
    }

    public String hashPassword(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
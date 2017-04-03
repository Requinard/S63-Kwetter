package com.wouterv.twatter.Beans;

import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Service.AccountService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Wouter Vanmulken on 21-3-2017.
 */
@Named
@SessionScoped
public class AccountsBean implements Serializable {
    @Inject
    AccountService accountService;
    Account loggedIn = null;

    public Account getLoggedIn() {
        if (loggedIn != null) {
            return loggedIn;
        }
        loggedIn = accountService.findByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        return loggedIn;
    }

    public Account getByUserName(String userName) {
        Account account = null;
        try {
            account = accountService.findByUsername(userName);
        } catch (Exception e) {
            System.out.println("Could not find user by username :" + userName);
        }
        return account;
    }

    public List<Account> getAll() {
        return accountService.getAllAccounts();
    }

    public void removeUser() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public List<Account> getFollowing(Account selectedAccount) {
        return accountService.followers(selectedAccount.getId());
    }

    public Account Follow(Account toFollow) {
//        try {
            accountService.followToggle(toFollow, loggedIn);
//        } catch (Exception e) {
//            System.out.println("Could not follow or unfollow user:" + toFollow.getUserName() + " for loggedIn user :" + loggedIn.getUserName());
//            return false;
//        }
//        return true;
//        return "/app/userprofile?user=" + toFollow.getUserName();
        return toFollow;
    }

    public boolean isFollowing(Account toFollow) {
        if(loggedIn == null){//TODO : fix that get loggedIn isn't being called
            getLoggedIn();
        }
        return loggedIn.getFollowing().contains(toFollow) || loggedIn.getUserName() == toFollow.getUserName();
    }
    public List<Account> search(String searchQuery){
        return accountService.search(searchQuery);
    }
    public void searchRedirect(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String query = params.get("searchF:searchContent");

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            response.sendRedirect("accounts.xhtml?query="+query);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.wouterv.twatter.Beans;

import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Service.AccountService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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
        if (loggedIn != null){
            return loggedIn;
        }
        loggedIn = accountService.findByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        return loggedIn;
    }

    public List<Account> getAll() {
        return accountService.getAllAccounts();
    }

    public void removeUser() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }


}

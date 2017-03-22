package com.wouterv.twatter.Beans;

import com.wouterv.twatter.Models.Account;
import com.wouterv.twatter.Service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.Servlet;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 21-3-2017.
 */
@Named
@RequestScoped
public class AccountsBean {
    @Inject
    AccountService accountService;

    public List<Account> getAll(){
        return accountService.getAllAccounts();
    }
}

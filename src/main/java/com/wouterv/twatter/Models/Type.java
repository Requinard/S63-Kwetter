package com.wouterv.twatter.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 11-3-2017.
 */
@Entity
public class Type implements Serializable {
    @Id
    private String groupName;

    @ManyToMany
    @JoinTable(name = "account_type",
                joinColumns = @JoinColumn(name = "groupName", referencedColumnName = "groupName"),
                inverseJoinColumns = @JoinColumn(name = "userName",referencedColumnName = "userName"))
    private List<Account> accounts;

    public Type(){}
    public Type(String name){this.groupName = name;}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
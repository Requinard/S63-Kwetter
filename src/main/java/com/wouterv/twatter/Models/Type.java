package com.wouterv.twatter.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 11-3-2017.
 */
@Entity
@XmlRootElement
public class Type implements Serializable {
    @Id
    private String groupName;

    @ManyToMany
    @JoinTable(name = "account_type",
            joinColumns = @JoinColumn(name = "groupName", referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "userName", referencedColumnName = "userName"))
    @JsonBackReference
    private List<Account> accounts;

    public Type() {}

    public Type(String name) {
        this.groupName = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
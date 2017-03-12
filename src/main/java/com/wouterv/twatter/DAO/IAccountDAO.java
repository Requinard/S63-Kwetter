package com.wouterv.twatter.DAO;

import com.wouterv.twatter.Models.Account;

import java.util.List;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */

public interface IAccountDAO extends IKwetterDAO<Account>{
    Account findByUserName(String name);
    List<Account> search(String name);
    List<Account> getFollowing(int Id);
}

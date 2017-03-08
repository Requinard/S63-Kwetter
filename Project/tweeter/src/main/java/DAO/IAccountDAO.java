package DAO;

import Models.Account;

import java.util.ArrayList;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */

public interface IAccountDAO extends IKwetterDAO<Account>{
    Account findByName(String name);
}

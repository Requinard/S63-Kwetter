package com.wouterv.twatter.DAO;

import com.wouterv.twatter.Models.Type;

/**
 * Created by Wouter Vanmulken on 12-3-2017.
 */
public interface ITypeDAO {
    Type findOrCreate(String name);
}

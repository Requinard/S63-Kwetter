package com.wouterv.twatter.DAO_COL;

import com.wouterv.twatter.DAO.ITypeDAO;
import com.wouterv.twatter.Models.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 12-3-2017.
 */
public class TypeDAOCol implements ITypeDAO {
    List<Type> typeList = new ArrayList<>();
    @Override
    public Type findOrCreate(String name) {
        for (Type t : typeList){
            if(t.getGroupName() ==name){
                return t;
            }
        }
        Type type = new Type(name);
        typeList.add(type);
        return type;
    }

}

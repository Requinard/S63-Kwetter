package com.wouterv.twatter.DAO.DAO;

import com.wouterv.twatter.Models.Hashtag;

/**
 * Created by Wouter Vanmulken on 2-4-2017.
 */
public interface IHashtagDAO extends IKwetterDAO<Hashtag> {
    public Hashtag findOrCreate(String name);
}

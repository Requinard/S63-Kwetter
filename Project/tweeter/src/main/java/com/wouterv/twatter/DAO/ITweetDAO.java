package com.wouterv.twatter.DAO;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
import com.wouterv.twatter.Models.Tweet;

import java.util.List;

public interface ITweetDAO extends IKwetterDAO<Tweet> {
    List<Tweet> getPersonalTweets();
}

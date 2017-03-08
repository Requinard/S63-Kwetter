package DAOCol;

import DAO.ITweetDAO;
import Models.Tweet;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */
@Stateless
@Default
public class TweetDAOCol implements ITweetDAO{
    CopyOnWriteArrayList<Tweet> tweets = new CopyOnWriteArrayList<>();

    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    @Override
    public void create(Tweet tweet) {
        tweets.add(tweet);
    }

    @Override
    public void remove(Tweet tweet) {
        tweets.remove(tweet);
    }

    @Override
    public void edit(Tweet entity) {
        for (int i =0;i<tweets.size();i++) {
            if(tweets.get(i).getId() == entity.getId()){
                tweets.set(i,entity);
                continue;
            }
        }
    }

    @Override
    public ArrayList<Tweet> getAll() {
        return new ArrayList(tweets);
    }

    @Override
    public Tweet findById(Object id) {
        throw new NotImplementedException();
//        return null;//Todo : make getAll
    }
}

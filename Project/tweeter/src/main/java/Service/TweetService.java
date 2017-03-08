package Service;

import Annotations.JPA;
import DAO.ITweetDAO;
import Models.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */

@Stateless
@Path("/tweets")
public class TweetService {

    @Inject
    @JPA
    ITweetDAO tweetDAO;

    @GET
    @Path("/tweets")
    @Produces("application/json")
    public List<Tweet> getTweets() {
        List<Tweet> tweets = tweetDAO.getAll();
        return tweets;
    }
//    @POST
//    @Path("/tweet")
//    @Produces("application/json")
//    public List<Tweet> postTweets(HttpRequest request) {
//        List<Tweet> tweets = tweetDAO.getAll();
//        return tweets;
//    }
}

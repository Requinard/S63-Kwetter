package com.wouterv.twatter.Batch;

import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.AccountService;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("TweetProcessor")
public class TweetProcessor implements ItemProcessor {

    @Inject
    AccountService accountService;

    @Override
    public Object processItem(Object item) throws Exception {
         
      InputTweet inputtweet = (InputTweet) item;
      
      Tweet tweet= new Tweet();
      tweet.setContent(inputtweet.Content);
      tweet.setPostAccount(accountService.findByID(inputtweet.UserId));
      tweet.setDate(inputtweet.Date);

      return tweet;
    }
  
}

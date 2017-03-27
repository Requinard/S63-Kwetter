package com.wouterv.twatter.Batch;

import com.wouterv.twatter.Models.Tweet;
import com.wouterv.twatter.Service.AccountService;
import com.wouterv.twatter.Service.TweetService;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("TweetWriter")
public class TweetWriter implements ItemWriter {

    @Inject
    private TweetService service;
    @Inject
    private AccountService accountService;


    @Override
    public void close() throws Exception {
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {

        for (Object item : items) {
            Tweet t = (Tweet) item;
            service.create(t.getContent(),t.getPostAccount().getId());
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }

    @Override
    public void open(Serializable checkpoint) throws Exception {
    }

}

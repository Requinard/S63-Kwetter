package com.wouterv.twatter.Batch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;

@Dependent
@Named("TweetReader")
public class TweetReader implements ItemReader {

    @Inject
    private JobContext jobContext;

    private String fileName;

    //    private JsonParser parser;
    private JSONParser parser;
    private JSONArray array;
    private Iterator iterator;
    private Checkpoint checkpoint;

    private boolean start;


    @Override
    public void open(Serializable srlzbl) throws Exception {
        if (checkpoint == null) {
            this.checkpoint = new Checkpoint();
        } else {
            this.checkpoint = (Checkpoint) checkpoint;
        }

        fileName = jobContext.getProperties().getProperty("input_file");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream iStream = classLoader.getResourceAsStream(fileName);

        JSONParser jsonParser = new JSONParser();
        JSONObject obj = (JSONObject) jsonParser.parse(new BufferedReader(new InputStreamReader(iStream, "UTF-8")));

        array = (JSONArray) obj.get("Tweets");
        iterator = array.iterator();

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public Object readItem() throws Exception {
        InputTweet inputTweet = new InputTweet();

        if (iterator.hasNext()) {
            JSONObject currentItem = (JSONObject) iterator.next();
            inputTweet.Content = (String) currentItem.get("Content");
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH);
            inputTweet.Date = format.parse((String) currentItem.get("Date"));
            inputTweet.UserId = ((Long)currentItem.get("UserId")).intValue();
        }else {return null;}
        return inputTweet;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return new Checkpoint();
    }

}

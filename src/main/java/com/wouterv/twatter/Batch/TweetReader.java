package com.wouterv.twatter.Batch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;
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
//        try {
        JSONObject obj = (JSONObject) jsonParser.parse(new BufferedReader(new InputStreamReader(iStream, "UTF-8")));
//
//            JSONObject jsonObject = (JSONObject) obj;
//            System.out.println(jsonObject);
//
//            String name = (String) jsonObject.get("name");
//            System.out.println(name);
//
//            long age = (Long) jsonObject.get("age");
//            System.out.println(age);

        // loop array
        array = (JSONArray) obj.get("Tweets");
        iterator = array.iterator();
//        } catch (Exception e){
//        }


//        parser = Json.createParser(iStream);
//
//        start = false;
//        for (long i = 0; i < this.checkpoint.getCount(); ++i) {
//            JsonParser.Event event = parser.next();
//            if (event == JsonParser.Event.START_ARRAY) {
//                start = true;
//            }
//        }
    }

    @Override
    public void close() throws Exception {
//        parser.close();

    }

    @Override
    public Object readItem() throws Exception {
//        boolean itemFound = false;
//        InputTweet item = new InputTweet();
//
//        System.out.println("Read Item");
//
//
//        while (!itemFound && parser.hasNext()) {
//            JsonParser.Event event = parser.next();
//            checkpoint.eventHappened();
//
//            switch (event) {
//                case START_ARRAY:
//                    start = true;
//                    break;
//                case VALUE_STRING:
//                    if (start == true) {
//                        System.out.println("aaaa : "+parser.getString());
//
//                        if (item.Content == null) {
//                            System.out.println("aaaa-content : "+parser.getString());
//                            item.Content = parser.getString();
//                        }
//                        else if (item.Date == null) {
//                            System.out.println("aaaa-date : "+parser.getString());
//                            DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
//                            item.Date = format.parse(parser.getString());
//                        }
//                    }
//                    break;
//                case VALUE_NUMBER:
//                    if(start== true){
//                         if (item.UserId == -1) {
//                             System.out.println("aaaa-id : "+parser.getString());
//                            item.UserId = parser.getInt();
//                            itemFound = true;
//                        }
//                    }
//                    break;
//                case END_ARRAY:
//                    item = null;
//                    break;
//            }
//        }
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

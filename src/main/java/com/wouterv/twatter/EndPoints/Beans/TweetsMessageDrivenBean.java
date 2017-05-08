package com.wouterv.twatter.EndPoints.Beans;

/**
 * Created by Wouter Vanmulken on 6-5-2017.
 */

import com.wouterv.twatter.Service.TweetService;
import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "queue.kwetter"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class TweetsMessageDrivenBean implements MessageListener {

    @Inject
    TweetService tweetService;

    @Context
    UriInfo uriInfo;

    @Override
    public void onMessage(Message message) {
        System.out.println("message received");
        try {
            String content = ((TextMessage)message).getText();
            String[] split = content.split(",");
            tweetService.create(split[1],Integer.parseInt(split[0]));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
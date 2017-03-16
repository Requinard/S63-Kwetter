package com.wouterv.twatter.Models;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Wouter Vanmulken on 15-3-2017.
 */
@Entity
@XmlRootElement
public class Hashtag extends TweeterModel{
    private String name;

    public Hashtag() {}
    public Hashtag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

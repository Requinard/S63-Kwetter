package com.wouterv.twatter.Models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Wouter Vanmulken on 8-3-2017.
 */


@MappedSuperclass
public class TweeterModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

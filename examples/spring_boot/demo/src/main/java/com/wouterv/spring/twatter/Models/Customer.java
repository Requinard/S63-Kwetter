package com.wouterv.spring.twatter.Models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wouter Vanmulken on 1-3-2017.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;
    private String lastname;

    public Customer(long l, String format) {
        firstname = String.valueOf(l);
        lastname = format;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}


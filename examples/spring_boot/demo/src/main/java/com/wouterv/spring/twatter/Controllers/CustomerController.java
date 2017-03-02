package com.wouterv.spring.twatter.Controllers;

import com.wouterv.spring.twatter.Models.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Wouter Vanmulken on 1-3-2017.
 */
@RestController
@RequestMapping("Customers")
public class CustomerController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    //    @RequestMapping("/greeting")
//    public Customer greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }
    @RequestMapping("/customer")
    public List<Customer> customer(@RequestParam(value = "name", defaultValue = "World") String name) {

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1,"a"));
        customers.add(new Customer(2,"b"));
        customers.add(new Customer(3,"c"));


        return customers;
    }
}

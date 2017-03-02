package Controllers;

/**
 * Created by Wouter Vanmulken on 22-2-2017.
 */

import Models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


//@Path("/message")

public class TestController {

//    @PersistenceContext(name = "TweeterPU")
//    EntityManager em;

    @GET
    @Path("test3")
    @Produces("application/json")
    public Response printMessage2() {
        return Response.status(200).entity("result mofo").build();
    }
//
//
//
//    @GET
//    @Path("test/{param}")
//    public Response printMessage(@PathParam("param") String msg) {
//        em.getTransaction().begin();
//        User test = new User("first_test","last_test");
//        em.persist(test);
//        em.getTransaction().commit();
//        String result = "Restful example : " + msg;
//
//        Query q = em.createQuery("SELECT u FROM User u");
//        List<User> userList = q.getResultList();
//        for(User user : userList){result += user;}
//
//        return Response.status(200).entity(result).build();
//
//    }
//    @GET
//    @Path("/user/{param}")
//    public Response getUsers(@PathParam("param") String msg) {
//        List<User> users = new ArrayList(5);
//        users.add(new User("Wouter", "Vanmulken"));
//        users.add(new User("Test", "Gebruiker"));
//        users.add(new User("Hello", "World"));
//        users.add(new User("U wot", msg));
//        users.add(new User("Foo", "Bar"));
//        return Response.ok().entity(users).build();
//    }
}
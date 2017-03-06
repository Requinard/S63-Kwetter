package Controllers;

/**
 * Created by Wouter Vanmulken on 22-2-2017.
 */

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Stateless
//@Path("/test")
public class TestController {

    @Inject
    ITestDingetje testDingetje;

//    @PersistenceContext(name = "TweeterPU")
//    EntityManager em;
//    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TweeterPU");

    @GET
    @Path("test")
    @Produces("application/json")
    public String printMessage2() {
        return  testDingetje.getStuff();
//        return "result";
    }


    //    @GET
//    @GET
//    @Path("test3")
//    @Produces("application/json")
//    public Response printMessage2() {
//        return Response.status(200).entity("result mofo").build();
//    }
//
//
//
//    @GET
//    @Path("test2")
//    @Produces("application/json")
//
//    public Response printMessage() {
//        System.out.println("Hello motherfucking world");
////        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//        Account test = new Account("first_test", "last_test");
//        em.persist(test);
//        em.getTransaction().commit();
//        String result = "Restful example : ";
//
//        Query q = em.createQuery("SELECT u FROM Account u");
//        List<Account> userList = q.getResultList();
//        for (Account user : userList) {
//            result += user;
//        }
//        return Response.status(200).entity(result).build();
////        return Response.status(200).entity(null).build();
//
//    }
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
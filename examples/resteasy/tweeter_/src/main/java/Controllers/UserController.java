package Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Wouter Vanmulken on 2-3-2017.
 */
@Path("Users")
public class UserController {
    @GET
    @Path("user")
    public String GetUser(){
        return "ussssseeeeeeeeeerrr";
    }
}

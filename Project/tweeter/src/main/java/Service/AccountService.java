package Service;

import Controllers.ITestDingetje;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Stateless
@Path("/accountservice")
public class AccountService {
    @Inject
    ITestDingetje TestDingetje;

    @GET
    @Path("/test")
    public String getTest() {
        String test = "not null";
        if(TestDingetje == null){
            test = "null";
        }else{
            test = TestDingetje.getStuff();
        }
        return test;
    }

}

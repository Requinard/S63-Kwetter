package Rest;

import com.wouterv.twatter.Models.Account;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Wouter Vanmulken on 13-3-2017.
 */
public class AccountControllerTest {
    Client client;
    WebTarget root;
    static final String PATH = "/tweeter/api/accounts/";
    static final String BASEURL = "http://localhost:8080" + PATH;

    public AccountControllerTest() {
    }

    @Before
    public void setUp() {
        this.client = ClientBuilder.newClient();
        this.root = this.client.target(BASEURL);
    }

    @Test
    public void crud() {
        String mediaType = MediaType.APPLICATION_JSON;
        Account account = new Account("username", "email", "bio", "firstname", "lastname");

        final Entity<Account> entity = Entity.entity(account, mediaType);
        Account accResult = this.root.request().post(entity, Account.class);
        assertThat(accResult, is(account));



        //
//        String mediaType = MediaType.APPLICATION_JSON;
//        Student leider = new Student("Leider", 100, "Java");
//        Student volger = new Student("Volger", 100, "Java");
//        StudentDTO studentDTO = new StudentDTO(leider, volger);
//
//        final Entity<StudentDTO> entity = Entity.entity(studentDTO, mediaType);
//        Student studResult = this.root.request().post(entity, Student.class);
//        System.out.println("NAME " + studResult.getName());
//
//
//          assertThat(studResult, is(leider));
//
//

      /*
        final String mediaType = MediaType.APPLICATION_JSON;
        final Entity<Student> entity = Entity.entity(student, mediaType);
        Response response = this.root.request().post(entity, Response.class);
        Student studResult = response.readEntity(Student.class);
        assertThat(response.getStatus(), is(200));
        assertThat(studResult, is(student));

        response = this.root.path(student.getName()).request(mediaType).delete(Response.class);
        assertThat(response.getStatus(), is(204));
        */
    }

}

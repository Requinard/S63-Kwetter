package Models;

//import javax.persistence.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by wouter on 18-2-2017.
 */
@Entity
@XmlRootElement
public class Account implements Serializable{

    @Id
    private String Id;
    private String FirstName;
    private String LastName;

    public Account() {
    }

    public Account(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

//    @javax.persistence.Id
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @Override
    public String toString() {
        return this.FirstName + " " + this.LastName;
    }
}

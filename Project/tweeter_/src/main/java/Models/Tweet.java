package Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Wouter Vanmulken on 6-3-2017.
 */
@Entity
@XmlRootElement
public class Tweet implements Serializable{


    private String Message;
    private Account PostAccount;
    private Tag Tag;

    private String Id;

    @javax.persistence.Id
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}

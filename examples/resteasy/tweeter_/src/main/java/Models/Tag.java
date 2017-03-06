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
public class Tag implements Serializable{
    private String Id;

    @Id
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }
}

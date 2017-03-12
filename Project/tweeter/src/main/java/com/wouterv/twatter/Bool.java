package com.wouterv.twatter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Wouter Vanmulken on 10-3-2017.
 */
@XmlRootElement
public class Bool implements Serializable {
    private boolean value;

    public Bool() {
    }

    public Bool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
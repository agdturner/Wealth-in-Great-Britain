package uk.ac.leeds.ccg.andyt.projects.ukhi.core;

import java.io.Serializable;

/**
 * @author Andy Turner
 */
public abstract class UKHI_Object implements Serializable {

    public transient UKHI_Environment env;
    
    protected UKHI_Object() {
    }

    public UKHI_Object(UKHI_Environment env) {
        this.env = env;
    }
}

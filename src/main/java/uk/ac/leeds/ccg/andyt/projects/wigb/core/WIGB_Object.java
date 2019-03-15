package uk.ac.leeds.ccg.andyt.projects.wigb.core;

import java.io.Serializable;

/**
 * @author Andy Turner
 */
public abstract class WIGB_Object implements Serializable {

    public transient UKHI_Environment env;
    
    protected WIGB_Object() {
    }

    public WIGB_Object(UKHI_Environment env) {
        this.env = env;
    }
}

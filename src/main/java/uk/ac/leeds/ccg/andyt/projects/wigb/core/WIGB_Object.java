package uk.ac.leeds.ccg.andyt.projects.wigb.core;

import java.io.Serializable;

/**
 * @author Andy Turner
 */
public abstract class WIGB_Object implements Serializable {

    /**
     * A reference to LR_Environment
     */
    public transient WIGB_Environment Env;

//    public LR_Object(){
//        Env = new LR_Environment();
//    }
    protected WIGB_Object() {
    }

    public WIGB_Object(WIGB_Environment env) {
        Env = env;
    }
}

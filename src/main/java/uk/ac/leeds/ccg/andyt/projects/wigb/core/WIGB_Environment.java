package uk.ac.leeds.ccg.andyt.projects.wigb.core;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_Environment;
import uk.ac.leeds.ccg.andyt.generic.data.Generic_Interval_long1;
import uk.ac.leeds.ccg.andyt.generic.data.Generic_UKPostcode_Handler;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;

/**
 *
 * @author geoagdt
 */
public class WIGB_Environment extends WIGB_OutOfMemoryErrorHandler
        implements Serializable {

    public transient Generic_Environment ge;

    public transient WIGB_Strings Strings;
    public transient WIGB_Files Files;
    public transient Generic_UKPostcode_Handler PostcodeHandler;

    public transient static final String EOL = System.getProperty("line.separator");

    public WIGB_Environment() {
        Strings = new WIGB_Strings();
        Files = new WIGB_Files(Strings, Strings.getS_data());
        ge = new Generic_Environment(Files, Strings);
        PostcodeHandler = new Generic_UKPostcode_Handler();
        File f;
        f = Files.getEnvDataFile();
        if (f.exists()) {
            System.out.println("Loading cache...");
            WIGB_Environment cache;
            cache = (WIGB_Environment) Generic_StaticIO.readObject(f);
            System.out.println("Loaded cache.");
            
        } else {
            
        }
    }

    

}

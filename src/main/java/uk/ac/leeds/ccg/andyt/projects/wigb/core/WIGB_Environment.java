package uk.ac.leeds.ccg.andyt.projects.wigb.core;

import java.io.File;
import java.io.Serializable;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_Environment;
import uk.ac.leeds.ccg.andyt.generic.data.Generic_UKPostcode_Handler;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Data;
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
    
    /**
     * Data.
     */
    public WIGB_WaAS_Data data;

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
            data = (WIGB_WaAS_Data) Generic_StaticIO.readObject(f);
            System.out.println("Loaded cache.");
        } else {
            data = new WIGB_WaAS_Data();
        }
    }

    /**
     * A method to try to ensure there is enough memory to continue.
     *
     * @return
     */
    @Override
    public boolean checkAndMaybeFreeMemory() {
        while (getTotalFreeMemory() < Memory_Threshold) {
            if (!swapDataAny()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean swapDataAny(boolean handleOutOfMemoryError) {
        try {
            boolean result = swapDataAny();
            checkAndMaybeFreeMemory();
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                clearMemoryReserve();
                boolean result = swapDataAny(HOOMEF);
                initMemoryReserve();
                return result;
            } else {
                throw e;
            }
        }
    }
    
    /**
     * Currently this just tries to swap a SHBE collection.
     *
     * @return
     */
    @Override
    public boolean swapDataAny() {
        boolean r;
        r = clearSomeCache();
        if (r) {
            return r;
        } else {
            System.out.println("No SHBE data to clear. Do some coding to try "
                    + "to arrange to clear something else if needs be!!!");
            return r;
        }
    }

    public boolean clearSomeCache() {
        return data.clearSomeCache();
    }

    //public boolean clearSomeCacheExcept(byte wave, short CASEW1) {
    public boolean clearSomeCacheExcept(byte wave, byte personCollectionIDs, short CASEW1) {
        return data.clearSomeCacheExcept(wave, personCollectionIDs, CASEW1);
    }
}

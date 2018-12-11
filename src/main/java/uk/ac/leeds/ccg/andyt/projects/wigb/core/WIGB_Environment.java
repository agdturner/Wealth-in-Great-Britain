package uk.ac.leeds.ccg.andyt.projects.wigb.core;

import java.io.File;
import java.io.Serializable;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_Environment;
import uk.ac.leeds.ccg.andyt.data.postcode.Generic_UKPostcode_Handler;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;
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
    
    /**
     * Data.
     */
    public WIGB_WaAS_Data data;

    public transient static final String EOL = System.getProperty("line.separator");

    public WIGB_Environment() {
        //Memory_Threshold = 3000000000L;
        Strings = new WIGB_Strings();
        Files = new WIGB_Files(Strings, Strings.s_data);
        ge = new Generic_Environment(Files, Strings);
        File f;
        f = Files.getEnvDataFile();
        if (f.exists()) {
            loadData();
            data.Files = Files;
            data.Files.Strings = Strings;
            data.Strings = Strings;
        } else {
            data = new WIGB_WaAS_Data(Files, Strings);
        }
    }

    /**
     * A method to try to ensure there is enough memory to continue.
     *
     * @return
     */
    @Override
    public boolean checkAndMaybeFreeMemory() {
        System.gc();
        while (getTotalFreeMemory() < Memory_Threshold) {
//            int clear = clearAllData();
//            if (clear == 0) {
//                return false;
//            }
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
     * Currently this just tries to swap WaAS data.
     *
     * @return
     */
    @Override
    public boolean swapDataAny() {
        boolean r;
        r = clearSomeData();
        if (r) {
            return r;
        } else {
            System.out.println("No WaAS data to clear. Do some coding to try "
                    + "to arrange to clear something else if needs be. If the "
                    + "program fails then try providing more memory...");
            return r;
        }
    }

    public boolean clearSomeData() {
        return data.clearSomeData();
    }

    public int clearAllData() {
        int r;
        r = data.clearAllData();
        return r;
    }
    
    public void cacheData() {
        File f;
        f = Files.getEnvDataFile();
        System.out.println("<cache data>");
        Generic_IO.writeObject(data, f);
        System.out.println("</cache data>");
    }

    public final void loadData() {
        File f;
        f = Files.getEnvDataFile();
        System.out.println("<load data>");
        data = (WIGB_WaAS_Data) Generic_IO.readObject(f);
        System.out.println("<load data>");
    }
}

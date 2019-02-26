package uk.ac.leeds.ccg.andyt.projects.wigb.core;

import java.io.File;
import java.io.Serializable;
import java.util.logging.Level;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_Environment;
import uk.ac.leeds.ccg.andyt.generic.data.waas.core.WaAS_Environment;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Data;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;

/**
 *
 * @author geoagdt
 */
public class WIGB_Environment extends WIGB_OutOfMemoryErrorHandler
        implements Serializable {

    public transient WIGB_Files files;
    public transient Generic_Environment ge;
    public transient WaAS_Environment we;
    
    /**
     * Data.
     */
    public WaAS_Data data;

    public transient static final String EOL = System.getProperty("line.separator");

    public WIGB_Environment() {
        //Memory_Threshold = 3000000000L;
        files = new WIGB_Files();
        Generic_Environment ge = new Generic_Environment(files, Level.FINE, 1);
        we = new WaAS_Environment(ge);
        File f;
        f = files.getEnvDataFile();
        if (f.exists()) {
            loadWaASData();
        } else {
            data = new WaAS_Data(we);
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
        f = files.getEnvDataFile();
        System.out.println("<cache data>");
        Generic_IO.writeObject(data, f);
        System.out.println("</cache data>");
    }

    public final void loadWaASData() {
        File f;
        f = we.files.getEnvDataFile();
        System.out.println("<load data>");
        data = (WaAS_Data) Generic_IO.readObject(f);
        System.out.println("<load data>");
    }
}

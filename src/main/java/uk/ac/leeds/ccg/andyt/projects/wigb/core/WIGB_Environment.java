package uk.ac.leeds.ccg.andyt.projects.wigb.core;

import java.io.File;
import java.io.Serializable;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_Environment;
import uk.ac.leeds.ccg.andyt.generic.data.Generic_UKPostcode_Handler;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Data;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.process.WIGB_Main_Process;

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
        //Memory_Threshold = 3000000000L;
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
            data = new WIGB_WaAS_Data(this);
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
//            int clear = clearAllCache();
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
            System.out.println("No WaAS data to clear. Do some coding to try "
                    + "to arrange to clear something else if needs be. If the "
                    + "program fails then try providing more memory...");
            return r;
        }
    }

    public boolean clearSomeCache() {
        return data.clearSomeCache();
    }

    public int clearAllCache() {
        System.out.println("<clearAllCache>");
        int r;
        r = data.clearAllCache();
        System.out.println("</clearAllCache>");
        return r;
    }
    
    /**
     *
     * @param collectionID the value of collectionID
     * @param o the value of o
     */
    public void storeCacheSubsetCollection(short collectionID, Object o) {
        File dir;
        dir = Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, "WaAS_" + collectionID + "." + Strings.S_dat);
        storeCache(cf, o);
    }

    /**
     *
     * @param collectionID the value of collectionID
     * @return 
     */
    public Object loadCacheSubsetCollection(short collectionID) {
        Object r;
        File dir;
        dir = Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, "WaAS_" + collectionID + "." + Strings.S_dat);
        r = loadCache(cf);
        return r;
    }

    /**
     *
     * @param cf the value of cf
     * @return
     */
    protected Object loadCache(File cf) {
        Object r;
        System.out.println("<Loading Collection from cache file " + cf + ">");
        r = Generic_StaticIO.readObject(cf);
        System.out.println("</Loading Collection from cache file " + cf + ">");
        return r;
    }

    /**
     *
     * @param cf the value of cf
     * @param o the value of o
     */
    protected void storeCache(File cf, Object o) {
        System.out.println("<Storing Collection in cache file " + cf + ">");
        Generic_StaticIO.writeObject(o, cf);
        System.out.println("</Storing Collection in cache file " + cf + ">");
    }

    public void storeCache() {
        File f;
        f = Files.getEnvDataFile();
        System.out.println("Storing cache...");
        Generic_StaticIO.writeObject(data, f);
        System.out.println("Stored cache.");
    }

}

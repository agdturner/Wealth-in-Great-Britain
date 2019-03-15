package uk.ac.leeds.ccg.andyt.projects.wigb.core;

import java.io.File;
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
public class UKHI_Environment extends WIGB_OutOfMemoryErrorHandler  {

    public transient WIGB_Files files;
    public transient Generic_Environment ge;
    public transient WaAS_Environment we;
    
    public transient final int logID;
    
    public final WaAS_Data data;
    public transient final byte NWAVES;
    public transient final byte W1;
    public transient final byte W2;
    public transient final byte W3;
    public transient final byte W4;
    public transient final byte W5;

    public transient static final String EOL = System.getProperty("line.separator");

    public UKHI_Environment() {
        //Memory_Threshold = 3000000000L;
        files = new WIGB_Files();
        ge = new Generic_Environment(files, Level.FINE, 1);
        we = new WaAS_Environment(ge);
        File f = files.getEnvDataFile();
        if (f.exists()) {
            data = (WaAS_Data) Generic_IO.readObject(we.files.getEnvDataFile());
        } else {
            data = new WaAS_Data(we);
        }
        NWAVES = WaAS_Data.NWAVES;
        W1 = WaAS_Data.W1;
        W2 = WaAS_Data.W2;
        W3 = WaAS_Data.W3;
        W4 = WaAS_Data.W4;
        W5 = WaAS_Data.W5;
        logID = ge.initLog(UKHI_Strings.s_UKHI);
    }

    /**
     * @return See {@link WaAS_Environment#checkAndMaybeFreeMemory()}.
     */
    @Override
    public boolean checkAndMaybeFreeMemory() {
        return we.checkAndMaybeFreeMemory();
    }

    /**
     * @param hoome IFF true then {@link java.lang.OutOfMemoryError} is handled.
     * @return See {@link WaAS_Environment#swapDataAny()}.
     */
    @Override
    public boolean swapDataAny(boolean hoome) {
        return we.swapDataAny(hoome);
    }

    /**
     * @return See {@link WaAS_Environment#swapDataAny()}.
     */
    @Override
    public boolean swapDataAny() {
        return we.swapDataAny();
    }

    /**
     * @return See {@link WaAS_Environment#cacheData()}.
     */
    public boolean clearSomeData() {
        return data.clearSomeData();
    }

    /**
     * @return See {@link WaAS_Environment#clearAllData()}.
     */
    public int clearAllData() {
        return we.clearAllData();
    }
    
    /**
     * See {@link WaAS_Environment#cacheData()}.
     */
    public void cacheData() {
        we.cacheData();
    }

    /**
     * For convenience. See
     * {@link Generic_Environment#logStartTag(java.lang.String, int)}.
     *
     * @param s The tag name.
     */
    public final void logStartTag(String s) {
        ge.logStartTag(s, logID);
    }
    
    /**
     * For convenience. See
     * {@link Generic_Environment#log(java.lang.String, int)}.
     * @param s The message to be logged.
     */
    public void log(String s) {
        ge.log(s, logID);
    }
    
    /**
     * For convenience. See
     * {@link Generic_Environment#logEndTag(java.lang.String, int)}.
     *
     * @param s The tag name.
     */
    public final void logEndTag(String s) {
        ge.logEndTag(s, logID);
    }
}

/*
 * Copyright 2018 geoagdt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.andyt.projects.wigb.data.waas;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.process.WIGB_Main_Process;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_Data implements Serializable {

    public transient WIGB_Files Files;
    public transient WIGB_Strings Strings;

    /**
     * Stores the number of waves in the WaAS
     */
    public static final byte NWAVES = 5;
    public static final byte W1 = 1;
    public static final byte W2 = 2;
    public static final byte W3 = 3;
    public static final byte W4 = 4;
    public static final byte W5 = 5;

    /**
     * The main WaAS data store. Keys are Collection IDs.
     */
    public HashMap<Short, WIGB_WaAS_Collection> data;

    /**
     * Looks up from a CASEW1 to the Collection ID for where the
     * WIGB_WaAS_Collection_Record is for the CASEW1.
     */
    public HashMap<Short, Short> lookup;

    public WIGB_WaAS_Collection getCollection(short collectionID) {
        WIGB_WaAS_Collection r;
        r = data.get(collectionID);
        if (r == null) {
            r = (WIGB_WaAS_Collection) loadSubsetCollection(collectionID);
            data.put(collectionID, r);
        }
        return r;
    }

    public void clearCollection(short cID) {
        WIGB_WaAS_Collection c;
        data.put(cID, null);
    }

    public WIGB_WaAS_Data(WIGB_Files Files, WIGB_Strings Strings) {
        this.Files = Files;
        this.Strings = Strings;
        data = new HashMap<>();
        lookup = new HashMap<>();
    }

    public boolean clearSomeData() {
        Iterator<Short> ite;
        ite = data.keySet().iterator();
        short cID;
        while (ite.hasNext()) {
            cID = ite.next();
            WIGB_WaAS_Collection c;
            c = data.get(cID);
            cacheSubsetCollection(cID, c);
            data.put(cID, null);
            return true;
        }
        return false;
    }

    public int clearAllData() {
        int r;
        r = 0;
        Iterator<Short> ite;
        ite = data.keySet().iterator();
        short cID;
        while (ite.hasNext()) {
            cID = ite.next();
            WIGB_WaAS_Collection c;
            c = data.get(cID);
            cacheSubsetCollection(cID, c);
            data.put(cID, null);
            r++;
        }
        return r;
    }

    /**
     *
     * @param cID the value of collectionID
     * @param o the value of o
     */
    public void cacheSubsetCollection(short cID, Object o) {
        File f;
        f = new File(Files.getGeneratedWaASSubsetsDir(),
                "WaAS_" + cID + "." + Strings.S_dat);
        cache(f, o);
    }

    /**
     *
     * @param cID the value of collectionID
     * @return
     */
    public Object loadSubsetCollection(short cID) {
        Object r;
        File f;
        f = new File(Files.getGeneratedWaASSubsetsDir(),
                "WaAS_" + cID + "." + Strings.S_dat);
        r = load(f);
        return r;
    }

    /**
     *
     * @param f the File to load Object result from.
     * @return
     */
    protected Object load(File f) {
        Object r;
        WIGB_Main_Process.log1("<load File " + f + ">");
        r = Generic_IO.readObject(f);
        WIGB_Main_Process.log1("</load File " + f + ">");
        return r;
    }

    /**
     *
     * @param f the value of cf
     * @param o the value of o
     */
    protected void cache(File f, Object o) {
        WIGB_Main_Process.log1("<cache File " + f + ">");
        Generic_IO.writeObject(o, f);
        WIGB_Main_Process.log1("</cache File " + f + ">");
    }

}

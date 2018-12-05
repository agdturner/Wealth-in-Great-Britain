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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_Data extends WIGB_Object {

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
        if (data.containsKey(collectionID)) {
            r = data.get(collectionID);
        } else {
            r = (WIGB_WaAS_Collection) Env.loadSubsetCollection(collectionID);
            data.put(collectionID, r);
        }
        return r;
    }
    
    public void clearCollection(short collectionID) {
        WIGB_WaAS_Collection c;
        c = data.get(collectionID);
        c = null;
    }

    public WIGB_WaAS_Data(WIGB_Environment env) {
        super(env);
        data = new HashMap<>();
        lookup = new HashMap<>();
    }

    public boolean clearSomeData() {
        Iterator<Short> ite;
        ite = data.keySet().iterator();
        short collectionID;
        while (ite.hasNext()) {
            collectionID = ite.next();
            WIGB_WaAS_Collection c;
            c = data.get(collectionID);
            Env.cacheSubsetCollection(collectionID, c);
            c = null;
            return true;
        }
        return false;
    }

    public int clearAllData() {
        int r;
        r = 0;
        Iterator<Short> ite;
        ite = data.keySet().iterator();
        short collectionID;
        while (ite.hasNext()) {
            collectionID = ite.next();
            WIGB_WaAS_Collection c;
            c = data.get(collectionID);
            Env.cacheSubsetCollection(collectionID, c);
            c = null;
            r++;
        }
        return r;
    }

}

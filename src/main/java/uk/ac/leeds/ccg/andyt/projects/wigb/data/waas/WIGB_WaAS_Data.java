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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave1_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave2_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave3_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave4_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave5_PERSON_Record;

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
     * Keys are CASEW1 values are PersonCollectionIDs.
     */
    public HashMap<Short, Short> personLookupW1;
    /**
     * Keys are CASEW2 values are PersonCollectionIDs.
     */
    public HashMap<Short, Short> personLookupW2;
    /**
     * Keys are CASEW3 values are PersonCollectionIDs.
     */
    public HashMap<Short, Short> personLookupW3;
    /**
     * Keys are CASEW4 values are PersonCollectionIDs.
     */
    public HashMap<Short, Short> personLookupW4;
    /**
     * Keys are CASEW5 values are PersonCollectionIDs.
     */
    public HashMap<Short, Short> personLookupW5;

    /**
     * The main WaAS data store. Keys are Collection IDs.
     */
    public HashMap<Short, WIGB_WaAS_Collection> data;

    /**
     * Looks up from a CASEW1 to the Collection ID for where the
     * WIGB_WaAS_Collection_Record is for the CASEW1.
     */
    public HashMap<Short, Short> lookup;

    /**
     * A set of CASEW1 indicating what WIGB_WaAS_Collections in data have
     * changed.
     */
    public HashSet<Short> hasChanged;

    public WIGB_WaAS_Collection getCollection(short CASEW1) {
        WIGB_WaAS_Collection r;
        short collectionID;
        collectionID = lookup.get(CASEW1);
        if (data.containsKey(collectionID)) {
            r = data.get(collectionID);
        } else {
            r = (WIGB_WaAS_Collection) Env.loadCacheSubsetCollection(collectionID);
            data.put(collectionID, r);
        }
        return r;
    }

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW1 and
     * values are lists of WIGB_WaAS_Wave1_PERSON_Record.
     */
    public HashMap<Short, HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>>> personDataW1;

    /**
     * Indicates if there is some personDataW1 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW1;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW2 and
     * Values are lists of WIGB_WaAS_Wave2_PERSON_Record.
     */
    public HashMap<Short, HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>>> personDataW2;

    /**
     * Indicates if there is some personDataW2 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW2;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW3 and
     * Values are lists of WIGB_WaAS_Wave3_PERSON_Record.
     */
    public HashMap<Short, HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>>> personDataW3;

    /**
     * Indicates if there is some personDataW3 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW3;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW4 and
     * Values are lists of WIGB_WaAS_Wave4_PERSON_Record.
     */
    public HashMap<Short, HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>>> personDataW4;

    /**
     * Indicates if there is some personDataW4 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW4;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW5 and
     * Values are lists of WIGB_WaAS_Wave5_PERSON_Record.
     */
    public HashMap<Short, HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>>> personDataW5;

    /**
     * Indicates if there is some personDataW5 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW5;

//    public WIGB_WaAS_Data() {
//        data = new HashMap<>();
//    }
    public WIGB_WaAS_Data(WIGB_Environment env) {
        super(env);
        hasPersonDataW1 = false;
        hasPersonDataW2 = false;
        hasPersonDataW3 = false;
        hasPersonDataW4 = false;
        hasPersonDataW5 = false;
        data = new HashMap<>();
        lookup = new HashMap<>();
        hasChanged = new HashSet<>();
    }

    public HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> getPersonCollectionW1(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW1) {
        HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> r;
        short pID;
        pID = personLookupW1.get(CASEW1);
        //System.out.println("Person Collection ID " + pID);
        if (personDataW1.containsKey(pID)) {
            r = personDataW1.get(pID);
        } else {
            r = (HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>>) personHandler.loadCacheSubsetCollection(pID, W1);
            personDataW1.put(pID, r);
        }
        hasPersonDataW1 = true;
        return r;
    }

    public HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> getPersonCollectionW2(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW2) {
        HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> r;
        short pID;
        pID = personLookupW2.get(CASEW2);
        //System.out.println("Person Collection ID " + pID);
        if (personDataW2.containsKey(pID)) {
            r = personDataW2.get(pID);
        } else {
            r = (HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>>) personHandler.loadCacheSubsetCollection(pID, W2);
            personDataW2.put(pID, r);
        }
        hasPersonDataW2 = true;
        return r;
    }

    public HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> getPersonCollectionW3(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW3) {
        HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> r;
        short pID;
        pID = personLookupW3.get(CASEW3);
        //System.out.println("Person Collection ID " + pID);
        if (personDataW3.containsKey(pID)) {
            r = personDataW3.get(pID);
        } else {
            r = (HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>>) personHandler.loadCacheSubsetCollection(pID, W3);
            personDataW3.put(pID, r);
        }
        hasPersonDataW3 = true;
        return r;
    }

    public HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> getPersonCollectionW4(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW4) {
        HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> r;
        short pID;
        pID = personLookupW4.get(CASEW4);
        //System.out.println("Person Collection ID " + pID);
        if (personDataW4.containsKey(pID)) {
            r = personDataW4.get(pID);
        } else {
            r = (HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>>) personHandler.loadCacheSubsetCollection(pID, W4);
            personDataW4.put(pID, r);
        }
        hasPersonDataW4 = true;
        return r;
    }

    public HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> getPersonCollectionW5(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW5) {
        HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> r;
        short pID;
        pID = personLookupW5.get(CASEW5);
        //System.out.println("Person Collection ID " + pID);
        if (personDataW5.containsKey(pID)) {
            r = personDataW5.get(pID);
        } else {
            r = (HashMap<WIGB_WaAS_ID, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>>) personHandler.loadCacheSubsetCollection(pID, W5);
            personDataW5.put(pID, r);
        }
        hasPersonDataW5 = true;
        return r;
    }

    public boolean clearSomeCache() {
        if (hasPersonDataW1) {
            int n;
            n = personDataW1.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW1.containsKey(pcID)) {
                    personDataW1.remove(pcID);
                    System.out.println("<Set to null PersonDataW1 with "
                        + "Collection ID " + pcID + "/>");
                    return true;
                }
            }
            hasPersonDataW1 = false;
        }
        if (hasPersonDataW2) {
            int n;
            n = personDataW2.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW2.containsKey(pcID)) {
                    personDataW2.remove(pcID);
                    System.out.println("<Set to null PersonDataW2 with "
                        + "Collection ID " + pcID + "/>");
                    return true;
                }
            }
            hasPersonDataW2 = false;
        }
        if (hasPersonDataW3) {
            int n;
            n = personDataW3.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW3.containsKey(pcID)) {
                    personDataW3.remove(pcID);
                    System.out.println("<Set to null PersonDataW3 with "
                        + "Collection ID " + pcID + "/>");
                    return true;
                }
            }
            hasPersonDataW3 = false;
        }
        if (hasPersonDataW4) {
            int n;
            n = personDataW4.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW4.containsKey(pcID)) {
                    personDataW4.remove(pcID);
                    System.out.println("<Set to null PersonDataW4 with "
                        + "Collection ID " + pcID + "/>");
                    return true;
                }
            }
            hasPersonDataW4 = false;
        }
        if (hasPersonDataW5) {
            int n;
            n = personDataW5.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW5.containsKey(pcID)) {
                    personDataW5.remove(pcID);
                    System.out.println("<Set to null PersonDataW5 with "
                        + "Collection ID " + pcID + "/>");
                    return true;
                }
            }
            hasPersonDataW5 = false;
        }
        Iterator<Short> ite;
        ite = data.keySet().iterator();
        short collectionID;
        while (ite.hasNext()) {
            collectionID = ite.next();
            if (hasChanged.contains(collectionID)) {
                Env.storeCacheSubsetCollection(collectionID, data.get(collectionID));
                hasChanged.remove(collectionID);
            }
            data.remove(collectionID);
            return true;
        }
        return false;
    }

    public int clearAllCache() {
        int r;
        r = 0;
        if (hasPersonDataW1) {
            int n;
            n = personDataW1.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW1.containsKey(pcID)) {
                    personDataW1.remove(pcID);
                    System.out.println("<Set to null PersonDataW1 with "
                        + "Collection ID " + pcID + "/>");
                    r++;
                }
            }
            hasPersonDataW1 = false;
        }
        if (hasPersonDataW2) {
            int n;
            n = personDataW2.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW2.containsKey(pcID)) {
                    personDataW2.remove(pcID);
                    System.out.println("<Set to null PersonDataW2 with "
                        + "Collection ID " + pcID + "/>");
                    r++;
                }
            }
            hasPersonDataW2 = false;
        }
        if (hasPersonDataW3) {
            int n;
            n = personDataW3.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW3.containsKey(pcID)) {
                    personDataW3.remove(pcID);
                    System.out.println("<Set to null PersonDataW3 with "
                        + "Collection ID " + pcID + "/>");
                    r++;
                }
            }
            hasPersonDataW3 = false;
        }
        if (hasPersonDataW4) {
            int n;
            n = personDataW4.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW4.containsKey(pcID)) {
                    personDataW4.remove(pcID);
                    System.out.println("<Set to null PersonDataW4 with "
                        + "Collection ID " + pcID + "/>");
                    r++;
                }
            }
            hasPersonDataW4 = false;
        }
        if (hasPersonDataW5) {
            int n;
            n = personDataW5.size();
            for (short pcID = 0; pcID < n; pcID++) {
                if (personDataW5.containsKey(pcID)) {
                    personDataW5.remove(pcID);
                    System.out.println("<Set to null PersonDataW5 with "
                        + "Collection ID " + pcID + "/>");
                    r++;
                }
            }
            hasPersonDataW5 = false;
        }
        Iterator<Short> ite;
        ite = data.keySet().iterator();
        short collectionID;
        while (ite.hasNext()) {
            collectionID = ite.next();
            if (hasChanged.contains(collectionID)) {
                Env.storeCacheSubsetCollection(collectionID, data.get(collectionID));
                hasChanged.remove(collectionID);
            }
            data.remove(collectionID);
            r++;
        }
        return r;
    }

}

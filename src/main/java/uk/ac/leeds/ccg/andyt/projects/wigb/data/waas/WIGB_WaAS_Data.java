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
import java.util.Iterator;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave1_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave2_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave3_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave4_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave5_PERSON_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_Data {

    /**
     * Stores the number of waves in the WaAS
     */
    public static final byte nwaves = 5;
    public static final byte w1 = 1;
    public static final byte w2 = 2;
    public static final byte w3 = 3;
    public static final byte w4 = 4;
    public static final byte w5 = 5;

    public HashMap<Short, Byte> personLookupW1;
    public HashMap<Short, Byte> personLookupW2;
    public HashMap<Short, Byte> personLookupW3;
    public HashMap<Short, Byte> personLookupW4;
    public HashMap<Short, Byte> personLookupW5;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW1 and
     * Values are lists of WIGB_WaAS_Wave1_PERSON_Record.
     */
    public HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>>> personDataW1;

    /**
     * Indicates if there is some personDataW1 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW1;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW2 and
     * Values are lists of WIGB_WaAS_Wave2_PERSON_Record.
     */
    public HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>>> personDataW2;

    /**
     * Indicates if there is some personDataW2 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW2;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW3 and
     * Values are lists of WIGB_WaAS_Wave3_PERSON_Record.
     */
    public HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>>> personDataW3;

    /**
     * Indicates if there is some personDataW3 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW3;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW4 and
     * Values are lists of WIGB_WaAS_Wave4_PERSON_Record.
     */
    public HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>>> personDataW4;

    /**
     * Indicates if there is some personDataW4 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW4;

    /**
     * Keys are personCollectionIDs, values are Maps where keys are CASEW5 and
     * Values are lists of WIGB_WaAS_Wave5_PERSON_Record.
     */
    public HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>>> personDataW5;

    /**
     * Indicates if there is some personDataW5 that can be deleted from memory
     * to free up space.
     */
    boolean hasPersonDataW5;

    public WIGB_WaAS_Data() {

    }

    public HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> getPersonCollectionW1(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW1) {
        HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> r;
        byte b;
        b = personLookupW1.get(CASEW1);
        System.out.println("Person Collection ID " + b);
        if (personDataW1.containsKey(b)) {
            r = personDataW1.get(b);
        } else {
            r = (HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>>) personHandler.loadCacheSubsetCollection(b, w1);
            personDataW1.put(b, r);
        }
        return r;
    }

    public HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> getPersonCollectionW2(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW2) {
        HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> r;
        byte b;
        b = personLookupW2.get(CASEW2);
        System.out.println("Person Collection ID " + b);
        if (personDataW2.containsKey(b)) {
            r = personDataW2.get(b);
        } else {
            r = (HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>>) personHandler.loadCacheSubsetCollection(b, w2);
            personDataW2.put(b, r);
        }
        return r;
    }

    public HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> getPersonCollectionW3(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW3) {
        HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> r;
        byte b;
        b = personLookupW3.get(CASEW3);
        System.out.println("Person Collection ID " + b);
        if (personDataW3.containsKey(b)) {
            r = personDataW3.get(b);
        } else {
            r = (HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>>) personHandler.loadCacheSubsetCollection(b, w3);
            personDataW3.put(b, r);
        }
        return r;
    }

    public HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> getPersonCollectionW4(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW4) {
        HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> r;
        byte b;
        b = personLookupW4.get(CASEW4);
        System.out.println("Person Collection ID " + b);
        if (personDataW4.containsKey(b)) {
            r = personDataW4.get(b);
        } else {
            r = (HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>>) personHandler.loadCacheSubsetCollection(b, w4);
            personDataW4.put(b, r);
        }
        return r;
    }

    public HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> getPersonCollectionW5(
            WIGB_WaAS_PERSON_Handler personHandler, short CASEW5) {
        HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> r;
        byte b;
        b = personLookupW5.get(CASEW5);
        System.out.println("Person Collection ID " + b);
        if (personDataW5.containsKey(b)) {
            r = personDataW5.get(b);
        } else {
            r = (HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>>) personHandler.loadCacheSubsetCollection(b, w5);
            personDataW5.put(b, r);
        }
        return r;
    }

    public boolean clearSomeCache() {
        if (hasPersonDataW1) {
            for (byte pcID = 0; pcID < personDataW1.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> wData;
                wData = personDataW1.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave1_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        return true;
                    }
                }
            }
        }
        if (hasPersonDataW2) {
            for (byte pcID = 0; pcID < personDataW2.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> wData;
                wData = personDataW2.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave2_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        return true;
                    }
                }
            }
        }
        if (hasPersonDataW3) {
            for (byte pcID = 0; pcID < personDataW3.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> wData;
                wData = personDataW3.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave3_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        return true;
                    }
                }
            }
        }
        if (hasPersonDataW4) {
            for (byte pcID = 0; pcID < personDataW4.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> wData;
                wData = personDataW4.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave4_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        return true;
                    }
                }
            }
        }
        if (hasPersonDataW5) {
            for (byte pcID = 0; pcID < personDataW5.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> wData;
                wData = personDataW5.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave5_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int clearAllCache() {
        int r;
        r = 0;
        if (hasPersonDataW1) {
            for (byte pcID = 0; pcID < personDataW1.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> wData;
                wData = personDataW1.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave1_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        r++;
                    }
                }
            }
        }
        if (hasPersonDataW2) {
            for (byte pcID = 0; pcID < personDataW2.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> wData;
                wData = personDataW2.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave2_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        r++;
                    }
                }
            }
        }
        if (hasPersonDataW3) {
            for (byte pcID = 0; pcID < personDataW3.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> wData;
                wData = personDataW3.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave3_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        r++;
                    }
                }
            }
        }
        if (hasPersonDataW4) {
            for (byte pcID = 0; pcID < personDataW4.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> wData;
                wData = personDataW4.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave4_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        r++;
                    }
                }
            }
        }
        if (hasPersonDataW5) {
            for (byte pcID = 0; pcID < personDataW5.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> wData;
                wData = personDataW5.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave5_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        cData = null;
                        r++;
                    }
                }
            }
        }
        return r;
    }

    public int clearAllCacheExcept(byte wave, byte personCollectionIDs, short CASEID) {
        int r;
        r = 0;
        byte thisWave;
        if (hasPersonDataW1) {
            thisWave = 1;
            for (byte pcID = 0; pcID < personDataW1.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> wData;
                wData = personDataW1.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave1_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                r++;
                            }
                        } else {
                            cData = null;
                            r++;
                        }
                    }
                }
            }
        }
        if (hasPersonDataW2) {
            thisWave = 2;
            for (byte pcID = 0; pcID < personDataW2.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> wData;
                wData = personDataW2.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave2_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                r++;
                            }
                        } else {
                            cData = null;
                            r++;
                        }
                    }
                }
            }
        }
        if (hasPersonDataW3) {
            thisWave = 3;
            for (byte pcID = 0; pcID < personDataW3.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> wData;
                wData = personDataW3.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave3_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                r++;
                            }
                        } else {
                            cData = null;
                            r++;
                        }
                    }
                }
            }
        }
        if (hasPersonDataW4) {
            thisWave = 4;
            for (byte pcID = 0; pcID < personDataW4.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> wData;
                wData = personDataW4.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave4_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                r++;
                            }
                        } else {
                            cData = null;
                            r++;
                        }
                    }
                }
            }
        }
        if (hasPersonDataW5) {
            thisWave = 5;
            for (byte pcID = 0; pcID < personDataW5.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> wData;
                wData = personDataW5.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave5_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                r++;
                            }
                        } else {
                            cData = null;
                            r++;
                        }
                    }
                }
            }
        }
        return r;
    }

    public boolean clearSomeCacheExcept(byte wave, byte personCollectionIDs, short CASEID) {
        byte thisWave;
        if (hasPersonDataW1) {
            thisWave = 1;
            for (byte pcID = 0; pcID < personDataW1.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> wData;
                wData = personDataW1.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave1_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                return true;
                            }
                        } else {
                            cData = null;
                            return true;
                        }
                    }
                }
            }
        }
        if (hasPersonDataW2) {
            thisWave = 2;
            for (byte pcID = 0; pcID < personDataW2.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> wData;
                wData = personDataW2.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave2_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                return true;
                            }
                        } else {
                            cData = null;
                            return true;
                        }
                    }
                }
            }
        }
        if (hasPersonDataW3) {
            thisWave = 3;
            for (byte pcID = 0; pcID < personDataW3.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> wData;
                wData = personDataW3.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave3_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                return true;
                            }
                        } else {
                            cData = null;
                            return true;
                        }
                    }
                }
            }
        }
        if (hasPersonDataW4) {
            thisWave = 4;
            for (byte pcID = 0; pcID < personDataW4.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> wData;
                wData = personDataW4.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave4_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                return true;
                            }
                        } else {
                            cData = null;
                            return true;
                        }
                    }
                }
            }
        }
        if (hasPersonDataW5) {
            thisWave = 5;
            for (byte pcID = 0; pcID < personDataW5.size(); pcID++) {
                HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> wData;
                wData = personDataW5.get(pcID);
                Iterator<Short> ite;
                ite = wData.keySet().iterator();
                while (ite.hasNext()) {
                    short k;
                    k = ite.next();
                    ArrayList<WIGB_WaAS_Wave5_PERSON_Record> cData;
                    cData = wData.get(k);
                    if (cData != null) {
                        if (thisWave == wave) {
                            if (personCollectionIDs != pcID && k != CASEID) {
                                cData = null;
                                return true;
                            }
                        } else {
                            cData = null;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

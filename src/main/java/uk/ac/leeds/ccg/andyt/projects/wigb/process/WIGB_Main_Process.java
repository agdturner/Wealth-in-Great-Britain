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
package uk.ac.leeds.ccg.andyt.projects.wigb.process;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.generic.utilities.Generic_Collections;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_HHOLD_Handler;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_PERSON_Handler;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_PERSON_ID;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave2_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave3_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave4_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave1_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave2_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave3_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave4_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave5_PERSON_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_Main_Process extends WIGB_Object {

    // For convenience
    public WIGB_Strings Strings;
    public WIGB_Files Files;

    public WIGB_Main_Process(WIGB_Environment env) {
        super(env);
        Strings = Env.Strings;
        Files = Env.Files;
    }

    public static void main(String[] args) {
        WIGB_Main_Process p;
        p = new WIGB_Main_Process(new WIGB_Environment());
        p.Files.setDataDirectory(new File(System.getProperty("user.dir"), "data"));
        // Main switches
        //p.doJavaCodeGeneration = true;
        p.doLoadDataIntoCaches = true; // rename/reuse just left here for convenience...
        p.run();
    }

    public void run() {

        if (doJavaCodeGeneration) {
            runJavaCodeGeneration();
        }

        File indir;
        indir = Files.getWaASInputDir();

        File generateddir;
        generateddir = Files.getGeneratedWaASDirectory();

        Object[] data;

        File cf;
        cf = new File(generateddir, "cache." + Env.Strings.S_dat);
        if (cf.exists()) {
            data = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            data = getHouseholdsThatHaveSameMembersThroughoutWaves(indir);
            Generic_StaticIO.writeObject(data, cf);
        }

    }

    /**
     * Method for running JavaCodeGeneration
     */
    public void runJavaCodeGeneration() {
        WIGB_JavaCodeGenerator p;
        p = new WIGB_JavaCodeGenerator(new WIGB_Environment());
        p.Files.setDataDirectory(new File(System.getProperty("user.dir"), "data"));
        String type;
        type = "hhold";
        p.run(type);
        type = "person";
        p.run(type);
    }

    public Object[] getHouseholdsThatHaveSameMembersThroughoutWaves(
            File indir) {

        Object[] result;
        result = new Object[18]; // Change

        /**
         * Step 1: Load household data into cache and memory.
         */
        WIGB_HHOLD_Handler hholdHandler;
        hholdHandler = new WIGB_HHOLD_Handler(Env, indir);
        Object[] hholdData;
        hholdData = hholdHandler.load();

        /**
         * Step 2: Unpack hholdData. hholdData is an Object[] of length 2. r[0]
         * is a HashMap with Integer keys which are the CASE id for the wave and
         * the values are WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>. r[1] is an array
         * of HashSets where: For Wave 5; r[1][0] is a list of CASEW5 values,
         * r[1][1] is a list of CASEW4 values, r[1][2] is a list of CASEW3
         * values, r[1][3] is a list of CASEW2 values, r[1][4] is a list of
         * CASEW1 values. For Wave 4; r[1][0] is a list of CASEW4 values,
         * r[1][1] is a list of CASEW3 values, r[1][2] is a list of CASEW2
         * values, r[1][3] is a list of CASEW1 values. For Wave 3; r[1][0] is a
         * list of CASEW3 values, r[1][1] is a list of CASEW2 values, r[1][2] is
         * a list of CASEW1 values. For Wave 2; r[1][0] is a list of CASEW2
         * values, r[1][1] is a list of CASEW1 values. For Wave 1: r[1][0] is a
         * list of CASEW1 values.
         */
        HashMap<Integer, HashSet<Integer>[]> iDLists;
        iDLists = new HashMap<>();
        // W1
        Object[] hholdW1;
        hholdW1 = (Object[]) hholdData[0];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW1recs;
        hholdW1recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW1[0];
        HashSet<Integer>[] iDListsW1;
        iDListsW1 = (HashSet<Integer>[]) hholdW1[1];
        iDLists.put(1, iDListsW1);
        // W2
        Object[] hholdW2;
        hholdW2 = (Object[]) hholdData[1];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW2recs;
        hholdW2recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW2[0];
        HashSet<Integer>[] iDListsW2;
        iDListsW2 = (HashSet<Integer>[]) hholdW2[1];
        iDLists.put(2, iDListsW2);
        // W3
        Object[] hholdW3;
        hholdW3 = (Object[]) hholdData[2];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW3recs;
        hholdW3recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW3[0];
        HashSet<Integer>[] iDListsW3;
        iDListsW3 = (HashSet<Integer>[]) hholdW3[1];
        iDLists.put(3, iDListsW3);
        // W4
        Object[] hholdW4;
        hholdW4 = (Object[]) hholdData[3];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW4recs;
        hholdW4recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW4[0];
        HashSet<Integer>[] iDListsW4;
        iDListsW4 = (HashSet<Integer>[]) hholdW4[1];
        iDLists.put(4, iDListsW4);
        // W5
        Object[] hholdW5;
        hholdW5 = (Object[]) hholdData[4];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW5recs;
        hholdW5recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW5[0];
        HashSet<Integer>[] iDListsW5;
        iDListsW5 = (HashSet<Integer>[]) hholdW5[1];
        iDLists.put(5, iDListsW5);

        /**
         * Step 3: Print out the Number of Households in each wave.
         */
        for (int wave = 5; wave > 0; wave--) {
            for (int i = 0; i < wave; i++) {
                if (i == 0) {
                    System.out.println("" + iDLists.get(wave)[i].size()
                            + "\tNumber of HHOLD IDs in Wave " + wave);
                } else {
                    System.out.println("" + iDLists.get(wave)[i].size()
                            + "\tNumber of HHOLD IDs from Wave " + (wave - i)
                            + " in Wave " + wave);
                }
            }
        }

        /**
         * Step 4: Get IDs of all hholds in waves going back from Wave 5 to Wave
         * 1; and, report the number of Households that are in all 5 waves
         * unchanged.
         */
        Iterator<Integer> ite;
        Integer CASEW5;
        Integer CASEW4;
        Integer CASEW3;
        Integer CASEW2;
        Integer CASEW1;
        /**
         * Get IDs of all hholds in waves 5, 4 and 3.
         */
        HashSet<Integer> tCASEW3IDsInCASEW4;
        tCASEW3IDsInCASEW4 = new HashSet<>();
        // Initialise the iterator to go through the list of all hhold IDs from 
        // Wave 5 that are in Wave 4.
        ite = iDListsW5[1].iterator();
        while (ite.hasNext()) {
            CASEW4 = ite.next();
            WIGB_Wave4_HHOLD_Record r;
            r = (WIGB_Wave4_HHOLD_Record) hholdW4recs.get(CASEW4);
            if (r != null) {
                CASEW3 = r.getCASEW3();
                if (CASEW3 != null) {
                    if (iDListsW3[0].contains(CASEW3)) {
                        tCASEW3IDsInCASEW4.add(CASEW3);
                    } else {
                        System.out.println("Warning Wave 4 CASEW3 " + CASEW3
                                + " is reported to exist, but no such ID is in "
                                + "the input!");
                    }
                }
            }
        }
        System.out.println("" + tCASEW3IDsInCASEW4.size()
                + "\tNumber of HHOLDs in Waves 5, 4 and 3");
        /**
         * Get IDs of all hholds in waves 5, 4, 3 and 2.
         */
        HashSet<Integer> tCASEW2IDsInCASEW3;
        tCASEW2IDsInCASEW3 = new HashSet<>();
        // Initialise the iterator to go through the list of all hhold IDs that 
        // are in Waves 5, Wave 4 and Wave 3.
        ite = tCASEW3IDsInCASEW4.iterator();
        while (ite.hasNext()) {
            CASEW3 = ite.next();
            WIGB_Wave3_HHOLD_Record r;
            r = (WIGB_Wave3_HHOLD_Record) hholdW3recs.get(CASEW3);
            if (r != null) {
                CASEW2 = r.getCASEW2();
                if (CASEW2 != null) {
                    if (iDListsW2[0].contains(CASEW2)) {
                        tCASEW2IDsInCASEW3.add(CASEW2);
                    } else {
                        System.out.println("Warning Wave 3 CASEW2 " + CASEW2
                                + " is reported to exist, but no such ID is in "
                                + "the input!");
                    }
                }
            }
        }
        System.out.println("" + tCASEW2IDsInCASEW3.size()
                + "\tNumber of HHOLDs in Waves 5, 4, 3 and 2");
        // Get IDs of all hholds in waves 5, 4, 3, 2 and 1.
        HashSet<Integer> tCASEW1IDsInCASEW2;
        tCASEW1IDsInCASEW2 = new HashSet<>();
        ite = tCASEW2IDsInCASEW3.iterator();
        while (ite.hasNext()) {
            CASEW2 = ite.next();
            WIGB_Wave2_HHOLD_Record r;
            r = (WIGB_Wave2_HHOLD_Record) hholdW2recs.get(CASEW2);
            if (r != null) {
                CASEW1 = r.getCASEW1();
                if (CASEW1 != null) {
                    if (iDListsW1[0].contains(CASEW1)) {
                        tCASEW1IDsInCASEW2.add(CASEW1);
                    } else {
                        System.out.println("Warning Wave 2 CASEW1 " + CASEW1
                                + " is reported to exist, but no such ID is in "
                                + "the input!");
                    }
                }
            }
        }
        System.out.println("" + tCASEW1IDsInCASEW2.size()
                + "\tNumber of HHOLDs in Waves 5, 4, 3, 2 and 1");

        /**
         * Step 5: Working forward from Wave 1, get the subset of households
         * that are in all 5 waves; create mappings from the wave ID to the Wave
         * 1 ID and vice versa as appropriate (a mapping from Wave 1 to itself
         * is unnecessary); and, get the person records for each household
         * stored in a cache. These will next be linked to each hhold record.
         */
        int wave;
        /**
         * Wave 1.
         */
        wave = 1;
        WIGB_PERSON_Handler personHandler;
        personHandler = new WIGB_PERSON_Handler(Env, indir);
        HashMap<WIGB_PERSON_ID, WIGB_Wave1_PERSON_Record> personWave1;
        // Load or generate personWave1 subset.
        personWave1 = personHandler.loadSubsetWave1(tCASEW1IDsInCASEW2);
        // Load or generate hholdWave1 subset.
        HashMap<Integer, WIGB_Wave1_HHOLD_Record> hholdWave1;
        try {
            hholdWave1 = hholdHandler.loadSubsetWave1();
        } catch (Exception ex) {
            hholdWave1 = new HashMap<>();
            ite = hholdW1recs.keySet().iterator();
            while (ite.hasNext()) {
                CASEW1 = ite.next();
                if (tCASEW1IDsInCASEW2.contains(CASEW1)) {
                    hholdWave1.put(CASEW1,
                            (WIGB_Wave1_HHOLD_Record) hholdW1recs.get(CASEW1));
                }
            }
            hholdHandler.storeSubset(1, hholdWave1);
        }
        /**
         * Wave 2.
         */
        wave = 2;
        // tWave2ToWave1HHOLDIDLookup keys are CASEW2 and values are CASEW1.
        HashMap<Integer, Integer> tWave2ToWave1HHOLDIDLookup;
        tWave2ToWave1HHOLDIDLookup = new HashMap<>();
        // tWave1ToWave2HHOLDIDLookup keys are CASEW1 and values are sets of CASEW2.
        HashMap<Integer, HashSet<Integer>> tWave1ToWave2HHOLDIDLookup;
        tWave1ToWave2HHOLDIDLookup = new HashMap<>();
        ite = tCASEW2IDsInCASEW3.iterator();
        while (ite.hasNext()) {
            CASEW2 = ite.next();
            WIGB_Wave2_HHOLD_Record r;
            r = (WIGB_Wave2_HHOLD_Record) hholdW2recs.get(CASEW2);
            CASEW1 = r.getCASEW1();
            if (CASEW1 != null) {
                if (tCASEW1IDsInCASEW2.contains(CASEW1)) {
                    if (tWave1ToWave2HHOLDIDLookup.containsKey(CASEW1)) {
                        System.out.println("Warning the hhold from Wave 1 with ID "
                                + CASEW1 + " has more than one hhold record in "
                                + "wave " + wave);
                    }
                    Generic_Collections.addToMap(tWave1ToWave2HHOLDIDLookup,
                            CASEW1, CASEW2);
                    tWave2ToWave1HHOLDIDLookup.put(CASEW2, CASEW1);
                }
            }
        }
        HashMap<WIGB_PERSON_ID, WIGB_Wave2_PERSON_Record> personWave2;
        personWave2 = personHandler.loadSubsetWave2(tWave2ToWave1HHOLDIDLookup.keySet());
        HashMap<Integer, WIGB_Wave2_HHOLD_Record> hholdWave2;
        try {
            hholdWave2 = hholdHandler.loadSubsetWave2();
        } catch (Exception ex) {
            hholdWave2 = new HashMap<>();
            ite = hholdW2recs.keySet().iterator();
            while (ite.hasNext()) {
                CASEW2 = ite.next();
                if (tWave2ToWave1HHOLDIDLookup.keySet().contains(CASEW2)) {
                    hholdWave2.put(CASEW2,
                            (WIGB_Wave2_HHOLD_Record) hholdW2recs.get(CASEW2));
                }
            }
            hholdHandler.storeSubset(2, hholdWave2);
        }
        /**
         * Wave 3.
         */
        wave = 3;
        // tWave3ToWave1HHOLDIDLookup keys are CASEW3 and values are CASEW1.
        HashMap<Integer, Integer> tWave3ToWave1HHOLDIDLookup;
        tWave3ToWave1HHOLDIDLookup = new HashMap<>();
        // tWave1ToWave3HHOLDIDLookup keys are CASEW1 and values are sets of CASEW3.
        HashMap<Integer, HashSet<Integer>> tWave1ToWave3HHOLDIDLookup;
        tWave1ToWave3HHOLDIDLookup = new HashMap<>();
        ite = tCASEW3IDsInCASEW4.iterator();
        while (ite.hasNext()) {
            CASEW3 = ite.next();
            WIGB_Wave3_HHOLD_Record r;
            r = (WIGB_Wave3_HHOLD_Record) hholdW3recs.get(CASEW3);
            CASEW1 = r.getCASEW1();
            if (CASEW1 != null) {
                if (tCASEW1IDsInCASEW2.contains(CASEW1)) {
                    if (tWave3ToWave1HHOLDIDLookup.containsValue(CASEW1)) {
                        System.out.println("Warning the hhold from Wave 1 with ID "
                                + CASEW1 + " has more than one hhold record in "
                                + "wave " + wave);
                    }
                    Generic_Collections.addToMap(tWave1ToWave3HHOLDIDLookup,
                            CASEW1, CASEW3);
                    tWave3ToWave1HHOLDIDLookup.put(CASEW3, CASEW1);
                }
            }
        }
        HashMap<WIGB_PERSON_ID, WIGB_Wave3_PERSON_Record> personWave3;
        personWave3 = personHandler.loadSubsetWave3(tWave3ToWave1HHOLDIDLookup.keySet());
        HashMap<Integer, WIGB_Wave3_HHOLD_Record> hholdWave3;
        try {
            hholdWave3 = hholdHandler.loadSubsetWave3();
        } catch (Exception ex) {
            hholdWave3 = new HashMap<>();
            ite = hholdW3recs.keySet().iterator();
            while (ite.hasNext()) {
                CASEW3 = ite.next();
                if (tWave3ToWave1HHOLDIDLookup.keySet().contains(CASEW3)) {
                    hholdWave3.put(CASEW3,
                            (WIGB_Wave3_HHOLD_Record) hholdW3recs.get(CASEW3));
                }
            }
            hholdHandler.storeSubset(3, hholdWave3);
        }
        /**
         * Wave 4: The iterator is set up slightly differently to the one for
         * waves 2 and 3.
         */
        wave = 4;
        // tWave4ToWave1HHOLDIDLookup keys are CASEW4 and values are CASEW1.
        HashMap<Integer, Integer> tWave4ToWave1HHOLDIDLookup;
        tWave4ToWave1HHOLDIDLookup = new HashMap<>();
        // tWave1ToWave4HHOLDIDLookup keys are CASEW1 and values are sets of CASEW4.
        HashMap<Integer, HashSet<Integer>> tWave1ToWave4HHOLDIDLookup;
        tWave1ToWave4HHOLDIDLookup = new HashMap<>();
        ite = iDListsW5[1].iterator();
        while (ite.hasNext()) {
            CASEW4 = ite.next();
            WIGB_Wave4_HHOLD_Record r;
            r = (WIGB_Wave4_HHOLD_Record) hholdW4recs.get(CASEW4);
            CASEW1 = r.getCASEW1();
            if (CASEW1 != null) {
                if (tCASEW1IDsInCASEW2.contains(CASEW1)) {
                    if (tWave4ToWave1HHOLDIDLookup.containsValue(CASEW1)) {
                        System.out.println("Warning the hhold from Wave 1 with ID "
                                + CASEW1 + " has more than one hhold record in "
                                + "wave " + wave);
                    }
                    Generic_Collections.addToMap(tWave1ToWave4HHOLDIDLookup,
                            CASEW1, CASEW4);
                    tWave4ToWave1HHOLDIDLookup.put(CASEW4, CASEW1);
                }
            }
        }
        HashMap<WIGB_PERSON_ID, WIGB_Wave4_PERSON_Record> personWave4;
        personWave4 = personHandler.loadSubsetWave4(tWave4ToWave1HHOLDIDLookup.keySet());
        HashMap<Integer, WIGB_Wave4_HHOLD_Record> hholdWave4;
        try {
            hholdWave4 = hholdHandler.loadSubsetWave4();
        } catch (Exception ex) {
            hholdWave4 = new HashMap<>();
            ite = hholdW4recs.keySet().iterator();
            while (ite.hasNext()) {
                CASEW4 = ite.next();
                if (tWave4ToWave1HHOLDIDLookup.keySet().contains(CASEW4)) {
                    hholdWave4.put(CASEW4,
                            (WIGB_Wave4_HHOLD_Record) hholdW4recs.get(CASEW4));
                }
            }
            hholdHandler.storeSubset(4, hholdWave4);
        }
        /**
         * Wave 5. The iterator is set up slightly differently to the one for
         * waves 2 and 3.
         */
        wave = 5;
        // tWave4ToWave1HHOLDIDLookup keys are CASEW5 and values are CASEW1.
        HashMap<Integer, Integer> tWave5ToWave1HHOLDIDLookup;
        tWave5ToWave1HHOLDIDLookup = new HashMap<>();
        // tWave1ToWave5HHOLDIDLookup keys are CASEW1 and values are sets of CASEW5.
        HashMap<Integer, HashSet<Integer>> tWave1ToWave5HHOLDIDLookup;
        tWave1ToWave5HHOLDIDLookup = new HashMap<>();
        ite = iDListsW5[0].iterator();
        while (ite.hasNext()) {
            CASEW5 = ite.next();
            WIGB_Wave5_HHOLD_Record r;
            r = (WIGB_Wave5_HHOLD_Record) hholdW5recs.get(CASEW5);
            CASEW1 = r.getCASEW1();
            if (CASEW1 != null) {
                if (tCASEW1IDsInCASEW2.contains(CASEW1)) {
                    if (tWave5ToWave1HHOLDIDLookup.containsValue(CASEW1)) {
                        System.out.println("Warning the hhold from Wave 1 with ID "
                                + CASEW1 + " has more than one hhold record in "
                                + "wave " + wave);
                    }
                    Generic_Collections.addToMap(tWave1ToWave5HHOLDIDLookup,
                            CASEW1, CASEW5);
                    tWave5ToWave1HHOLDIDLookup.put(CASEW5, CASEW1);
                }
            }
        }
        HashMap<WIGB_PERSON_ID, WIGB_Wave5_PERSON_Record> personWave5;
        personWave5 = personHandler.loadSubsetWave5(tWave5ToWave1HHOLDIDLookup.keySet());
        HashMap<Integer, WIGB_Wave5_HHOLD_Record> hholdWave5;
        try {
            hholdWave5 = hholdHandler.loadSubsetWave5();
        } catch (Exception ex) {
            hholdWave5 = new HashMap<>();
            ite = hholdW5recs.keySet().iterator();
            while (ite.hasNext()) {
                CASEW5 = ite.next();
                if (tWave5ToWave1HHOLDIDLookup.keySet().contains(CASEW5)) {
                    hholdWave5.put(CASEW5,
                            (WIGB_Wave5_HHOLD_Record) hholdW5recs.get(CASEW5));
                }
            }
            hholdHandler.storeSubset(5, hholdWave5);
        }
        result[0] = hholdWave1;
        result[1] = hholdWave2;
        result[2] = hholdWave3;
        result[3] = hholdWave4;
        result[4] = hholdWave5;
        result[5] = personWave1;
        result[6] = personWave2;
        result[7] = personWave3;
        result[8] = personWave4;
        result[9] = personWave5;
        result[10] = tWave1ToWave5HHOLDIDLookup;
        result[11] = tWave5ToWave1HHOLDIDLookup;
        result[12] = tWave1ToWave4HHOLDIDLookup;
        result[13] = tWave4ToWave1HHOLDIDLookup;
        result[14] = tWave1ToWave3HHOLDIDLookup;
        result[15] = tWave3ToWave1HHOLDIDLookup;
        result[16] = tWave1ToWave2HHOLDIDLookup;
        result[17] = tWave2ToWave1HHOLDIDLookup;
        return result;
    }

    boolean doJavaCodeGeneration = false;
    boolean doLoadDataIntoCaches = false;
}

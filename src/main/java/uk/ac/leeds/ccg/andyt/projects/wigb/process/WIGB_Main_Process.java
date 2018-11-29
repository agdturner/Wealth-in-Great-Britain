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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.generic.utilities.Generic_Collections;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Collection;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Combined_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Data;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_HHOLD_Handler;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_PERSON_Handler;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Wave1_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Wave2_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Wave3_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Wave4_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Wave5_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave2_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave3_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave4_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave1_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave2_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave3_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave4_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave5_PERSON_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_Main_Process extends WIGB_Object {

    // For convenience
    protected WIGB_WaAS_Data data;
    protected WIGB_Strings Strings;
    protected WIGB_Files Files;

    public WIGB_Main_Process(WIGB_Environment env) {
        super(env);
        data = env.data;
        Strings = env.Strings;
        Files = env.Files;
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
        File outdir;
        File generateddir;
        WIGB_WaAS_HHOLD_Handler hholdHandler;

        indir = Files.getWaASInputDir();
        generateddir = Files.getGeneratedWaASDirectory();
        outdir = new File(generateddir, "Subsets");
        outdir.mkdirs();
        hholdHandler = new WIGB_WaAS_HHOLD_Handler(Env, indir);

        //doDataProcessingStep1(indir, outdir, hholdHandler, nwaves);
        run2(indir, outdir, hholdHandler);

    }

    public void run2(File indir, File outdir, WIGB_WaAS_HHOLD_Handler hholdHandler) {
        byte nwaves;
        nwaves = WIGB_WaAS_Data.nwaves;
        Object[] personSubsetW1;
        Object[] personSubsetW2;
        Object[] personSubsetW3;
        Object[] personSubsetW4;
        Object[] personSubsetW5;
        WIGB_WaAS_PERSON_Handler personHandler;
        personHandler = new WIGB_WaAS_PERSON_Handler(Env, indir);
        Object[] o;
        byte wave;
        int chunkSize;
        chunkSize = 1000;
        /**
         * Get lookups and selected person data for Waves 1 and Wave 2.
         */
        wave = 2;
        o = getLookups(wave, 1, outdir);
        HashMap<Short, Short> tWave2ToWave1HHOLDIDLookup;
        HashMap<Short, HashSet<Short>> tWave1ToWave2HHOLDIDLookup;
        tWave2ToWave1HHOLDIDLookup = (HashMap<Short, Short>) o[0];
        tWave1ToWave2HHOLDIDLookup = (HashMap<Short, HashSet<Short>>) o[1];
        wave = 1;
        personSubsetW1 = personHandler.loadSubsetWave1(tWave1ToWave2HHOLDIDLookup.keySet(), chunkSize, wave);
        Env.data.personLookupW1 = (HashMap<Short, Byte>) personSubsetW1[0];
        Env.data.personDataW1 = (HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>>>) personSubsetW1[0];
        wave = 2;
        personSubsetW2 = personHandler.loadSubsetWave2(tWave2ToWave1HHOLDIDLookup.keySet(), chunkSize, wave);
        Env.data.personLookupW2 = (HashMap<Short, Byte>) personSubsetW2[0];
        Env.data.personDataW2 = (HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>>>) personSubsetW2[0];

        /**
         * Get lookups and selected person data for Wave 3.
         */
        wave = 3;
        o = getLookups(wave, 1, outdir);
        HashMap<Short, Short> tWave3ToWave1HHOLDIDLookup;
        HashMap<Short, HashSet<Short>> tWave1ToWave3HHOLDIDLookup;
        tWave3ToWave1HHOLDIDLookup = (HashMap<Short, Short>) o[0];
        tWave1ToWave3HHOLDIDLookup = (HashMap<Short, HashSet<Short>>) o[1];
        personSubsetW3 = personHandler.loadSubsetWave3(tWave3ToWave1HHOLDIDLookup.keySet(), chunkSize, wave);
        Env.data.personLookupW3 = (HashMap<Short, Byte>) personSubsetW1[0];
        Env.data.personDataW3 = (HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>>>) personSubsetW3[0];
        /**
         * Get lookups and selected person data for Wave 4.
         */
        wave = 4;
        o = getLookups(wave, 1, outdir);
        HashMap<Short, Short> tWave4ToWave1HHOLDIDLookup;
        HashMap<Short, HashSet<Short>> tWave1ToWave4HHOLDIDLookup;
        tWave4ToWave1HHOLDIDLookup = (HashMap<Short, Short>) o[0];
        tWave1ToWave4HHOLDIDLookup = (HashMap<Short, HashSet<Short>>) o[1];
        personSubsetW4 = personHandler.loadSubsetWave4(tWave1ToWave4HHOLDIDLookup.keySet(), chunkSize, wave);
        Env.data.personLookupW4 = (HashMap<Short, Byte>) personSubsetW4[0];
        Env.data.personDataW4 = (HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>>>) personSubsetW4[0];

        /**
         * Get lookups and selected person data for Wave 5.
         */
        wave = 5;
        o = getLookups(wave, 1, outdir);
        HashMap<Short, Short> tWave5ToWave1HHOLDIDLookup;
        HashMap<Short, HashSet<Short>> tWave1ToWave5HHOLDIDLookup;
        tWave5ToWave1HHOLDIDLookup = (HashMap<Short, Short>) o[0];
        tWave1ToWave5HHOLDIDLookup = (HashMap<Short, HashSet<Short>>) o[1];
        personSubsetW5 = personHandler.loadSubsetWave5(tWave5ToWave1HHOLDIDLookup.keySet(), chunkSize, wave);
        Env.data.personLookupW5 = (HashMap<Short, Byte>) personSubsetW5[0];
        Env.data.personDataW5 = (HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>>>) personSubsetW1[0];
        /**
         * Load HHOLD Data.
         */
        HashMap<Short, WIGB_WaAS_Wave1_HHOLD_Record> hholdSubsetW1 = null;
        HashMap<Short, WIGB_WaAS_Wave2_HHOLD_Record> hholdSubsetW2 = null;
        HashMap<Short, WIGB_WaAS_Wave3_HHOLD_Record> hholdSubsetW3 = null;
        HashMap<Short, WIGB_WaAS_Wave4_HHOLD_Record> hholdSubsetW4 = null;
        HashMap<Short, WIGB_WaAS_Wave5_HHOLD_Record> hholdSubsetW5 = null;
        try {
            hholdSubsetW1 = hholdHandler.loadCacheSubsetWave1();
            hholdSubsetW2 = hholdHandler.loadCacheSubsetWave2();
            hholdSubsetW3 = hholdHandler.loadCacheSubsetWave3();
            hholdSubsetW4 = hholdHandler.loadCacheSubsetWave4();
            hholdSubsetW5 = hholdHandler.loadCacheSubsetWave5();
        } catch (Exception ex) {
            Logger.getLogger(WIGB_Main_Process.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * Merge Person and Household Data
         */
        System.out.println("Merging Person and Household Data");
        Iterator<Short> ite;
        short CASEW1;

        byte collectionID;
        collectionID = 0;
        WIGB_WaAS_Collection collection;
        collection = new WIGB_WaAS_Collection(collectionID);

        HashMap<Short, WIGB_WaAS_Combined_Record> crs;
        WIGB_WaAS_Combined_Record cr;
        // Wave 1.
        wave = 1;
        ite = hholdSubsetW1.keySet().iterator();
        while (ite.hasNext()) {
            CASEW1 = ite.next();
            System.out.println("CASEW1 " + CASEW1);
            WIGB_WaAS_Wave1_HHOLD_Record w1hhold;
            w1hhold = hholdSubsetW1.get(CASEW1);
            cr = new WIGB_WaAS_Combined_Record(CASEW1, nwaves);
            crs = new HashMap<>();
            HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> personCollection;
            personCollection = Env.data.getPersonCollectionW1(personHandler, CASEW1);
            ArrayList<WIGB_WaAS_Wave1_PERSON_Record> list;
            list = personCollection.get(CASEW1);
            WIGB_WaAS_Wave1_Record w1rec;
            w1rec = new WIGB_WaAS_Wave1_Record(w1hhold, list);
            cr.add(w1rec);
            crs.put(CASEW1, cr);
            collection.getData().put(CASEW1, cr);
        }
        Env.data.clearAllCache();
        // Wave 2.
        wave = 2;
        short CASEW2;
        ite = hholdSubsetW2.keySet().iterator();
        while (ite.hasNext()) {
            CASEW2 = ite.next();
            System.out.println("CASEW2 " + CASEW2);
            WIGB_WaAS_Wave2_HHOLD_Record w2hhold;
            w2hhold = hholdSubsetW2.get(CASEW2);
            CASEW1 = w2hhold.getCASEW1();
            cr = new WIGB_WaAS_Combined_Record(CASEW1, nwaves);
            crs = new HashMap<>();
            HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> personCollection;
            personCollection = Env.data.getPersonCollectionW2(personHandler, CASEW2);
            ArrayList<WIGB_WaAS_Wave2_PERSON_Record> list;
            list = personCollection.get(CASEW2);
            WIGB_WaAS_Wave2_Record w2rec;
            w2rec = new WIGB_WaAS_Wave2_Record(w2hhold, list);
            cr.add(w2rec);
            crs.put(CASEW1, cr);
            collection.getData().put(CASEW1, cr);
        }
        Env.data.clearAllCache();
        // Wave 3.
        wave = 3;
        short CASEW3;
        ite = hholdSubsetW3.keySet().iterator();
        while (ite.hasNext()) {
            CASEW3 = ite.next();
            System.out.println("CASEW3 " + CASEW3);
            WIGB_WaAS_Wave3_HHOLD_Record w3hhold;
            w3hhold = hholdSubsetW3.get(CASEW3);
            CASEW1 = w3hhold.getCASEW1();
            cr = new WIGB_WaAS_Combined_Record(CASEW1, nwaves);
            crs = new HashMap<>();
            HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> personCollection;
            personCollection = Env.data.getPersonCollectionW3(personHandler, CASEW3);
            ArrayList<WIGB_WaAS_Wave3_PERSON_Record> list;
            list = personCollection.get(CASEW3);
            WIGB_WaAS_Wave3_Record w3rec;
            w3rec = new WIGB_WaAS_Wave3_Record(w3hhold, list);
            cr.add(w3rec);
            crs.put(CASEW1, cr);
            collection.getData().put(CASEW1, cr);
        }
        Env.data.clearAllCache();
        // Wave 4.
        wave = 4;
        short CASEW4;
        ite = hholdSubsetW4.keySet().iterator();
        while (ite.hasNext()) {
            CASEW4 = ite.next();
            System.out.println("CASEW4 " + CASEW4);
            WIGB_WaAS_Wave4_HHOLD_Record w4hhold;
            w4hhold = hholdSubsetW4.get(CASEW4);
            CASEW1 = w4hhold.getCASEW1();
            cr = new WIGB_WaAS_Combined_Record(CASEW1, nwaves);
            crs = new HashMap<>();
            HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> personCollection;
            personCollection = Env.data.getPersonCollectionW4(personHandler, CASEW4);
            ArrayList<WIGB_WaAS_Wave4_PERSON_Record> list;
            list = personCollection.get(CASEW4);
            WIGB_WaAS_Wave4_Record w4rec;
            w4rec = new WIGB_WaAS_Wave4_Record(w4hhold, list);
            cr.add(w4rec);
            crs.put(CASEW1, cr);
            collection.getData().put(CASEW1, cr);
        }
        Env.data.clearAllCache();
        // Wave 5.
        wave = 5;
        short CASEW5;
        ite = hholdSubsetW5.keySet().iterator();
        while (ite.hasNext()) {
            CASEW5 = ite.next();
            System.out.println("CASEW5 " + CASEW5);
            WIGB_WaAS_Wave5_HHOLD_Record w5hhold;
            w5hhold = hholdSubsetW5.get(CASEW5);
            CASEW1 = w5hhold.getCASEW1();
            cr = new WIGB_WaAS_Combined_Record(CASEW1, nwaves);
            crs = new HashMap<>();
            HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> personCollection;
            personCollection = Env.data.getPersonCollectionW5(personHandler, CASEW5);
            ArrayList<WIGB_WaAS_Wave5_PERSON_Record> list;
            list = personCollection.get(CASEW5);
            WIGB_WaAS_Wave5_Record w5rec;
            w5rec = new WIGB_WaAS_Wave5_Record(w5hhold, list);
            cr.add(w5rec);
            crs.put(CASEW1, cr);
            collection.getData().put(CASEW1, cr);
        }
        Env.data.clearAllCache();
    }

    /**
     * Method for running JavaCodeGeneration
     */
    public void runJavaCodeGeneration() {
        String[] args;
        args = null;
        WIGB_JavaCodeGenerator.main(args);
    }

    /**
     *
     * @param indir
     * @param outdir
     * @param hholdHandler
     */
    public void doDataProcessingStep1(File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, byte nwaves) {

        /**
         * Step 1: Load household data into cache and memory.
         */
        Object[] hholdData;
        hholdData = hholdHandler.load();

        /**
         * Step 2: Unpack hholdData. hholdData is an Object[] of length 2. r[0]
         * is a HashMap with Integer keys which are the CASE id for the wave and
         * the values are WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>. r[1] is an
         * array of HashSets where: For Wave 5; r[1][0] is a list of CASEW5
         * values, r[1][1] is a list of CASEW4 values, r[1][2] is a list of
         * CASEW3 values, r[1][3] is a list of CASEW2 values, r[1][4] is a list
         * of CASEW1 values. For Wave 4; r[1][0] is a list of CASEW4 values,
         * r[1][1] is a list of CASEW3 values, r[1][2] is a list of CASEW2
         * values, r[1][3] is a list of CASEW1 values. For Wave 3; r[1][0] is a
         * list of CASEW3 values, r[1][1] is a list of CASEW2 values, r[1][2] is
         * a list of CASEW1 values. For Wave 2; r[1][0] is a list of CASEW2
         * values, r[1][1] is a list of CASEW1 values. For Wave 1: r[1][0] is a
         * list of CASEW1 values.
         */
        HashMap<Integer, HashSet<Short>[]> iDLists;
        iDLists = new HashMap<>();
        // W1
        Object[] hholdDataW1;
        hholdDataW1 = (Object[]) hholdData[0];
        HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW1;
        hholdW1 = (HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW1[0];
        HashSet<Short>[] iDListsW1;
        iDListsW1 = (HashSet<Short>[]) hholdDataW1[1];
        iDLists.put(1, iDListsW1);
        // W2
        Object[] hholdDataW2;
        hholdDataW2 = (Object[]) hholdData[1];
        HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW2;
        hholdW2 = (HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW2[0];
        HashSet<Short>[] iDListsW2;
        iDListsW2 = (HashSet<Short>[]) hholdDataW2[1];
        iDLists.put(2, iDListsW2);
        // W3
        Object[] hholdDataW3;
        hholdDataW3 = (Object[]) hholdData[2];
        HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW3;
        hholdW3 = (HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW3[0];
        HashSet<Short>[] iDListsW3;
        iDListsW3 = (HashSet<Short>[]) hholdDataW3[1];
        iDLists.put(3, iDListsW3);
        // W4
        Object[] hholdDataW4;
        hholdDataW4 = (Object[]) hholdData[3];
        HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW4;
        hholdW4 = (HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW4[0];
        HashSet<Short>[] iDListsW4;
        iDListsW4 = (HashSet<Short>[]) hholdDataW4[1];
        iDLists.put(4, iDListsW4);
        // W5
        Object[] hholdDataW5;
        hholdDataW5 = (Object[]) hholdData[4];
        HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW5;
        hholdW5 = (HashMap<Short, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW5[0];
        HashSet<Short>[] iDListsW5;
        iDListsW5 = (HashSet<Short>[]) hholdDataW5[1];
        iDLists.put(5, iDListsW5);

        /**
         * Step 3: Print out the Number of Households in each wave.
         */
        for (int wave = nwaves; wave > 0; wave--) {
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
        Iterator<Short> ite;
        short CASEW5;
        short CASEW4;
        short CASEW3;
        short CASEW2;
        short CASEW1;
        /**
         * Get IDs of all hholds in waves 5, 4 and 3.
         */
        HashSet<Short> tCASEW3IDsInCASEW4;
        tCASEW3IDsInCASEW4 = new HashSet<>();
        // Initialise the iterator to go through the list of all hhold IDs from 
        // Wave 5 that are in Wave 4.
        ite = iDListsW5[1].iterator();
        while (ite.hasNext()) {
            CASEW4 = ite.next();
            WIGB_WaAS_Wave4_HHOLD_Record r;
            r = (WIGB_WaAS_Wave4_HHOLD_Record) hholdW4.get(CASEW4);
            if (r != null) {
                CASEW3 = r.getCASEW3();
                if (CASEW3 > Short.MIN_VALUE) {
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
        HashSet<Short> tCASEW2IDsInCASEW3;
        tCASEW2IDsInCASEW3 = new HashSet<>();
        // Initialise the iterator to go through the list of all hhold IDs that 
        // are in Waves 5, Wave 4 and Wave 3.
        ite = tCASEW3IDsInCASEW4.iterator();
        while (ite.hasNext()) {
            CASEW3 = ite.next();
            WIGB_WaAS_Wave3_HHOLD_Record r;
            r = (WIGB_WaAS_Wave3_HHOLD_Record) hholdW3.get(CASEW3);
            if (r != null) {
                CASEW2 = r.getCASEW2();
                if (CASEW2 > Short.MIN_VALUE) {
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
        HashSet<Short> tCASEW1IDsInCASEW2;
        tCASEW1IDsInCASEW2 = new HashSet<>();
        ite = tCASEW2IDsInCASEW3.iterator();
        while (ite.hasNext()) {
            CASEW2 = ite.next();
            WIGB_WaAS_Wave2_HHOLD_Record r;
            r = (WIGB_WaAS_Wave2_HHOLD_Record) hholdW2.get(CASEW2);
            if (r != null) {
                CASEW1 = r.getCASEW1();
                if (CASEW1 > Short.MIN_VALUE) {
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
         * is unnecessary).
         */
        int wave;
        /**
         * Step 5: Wave 1.
         */
        wave = 1;

        // Load test or generate hholdSubsetW1 subset.
        try {
            HashMap<Short, WIGB_WaAS_Wave1_HHOLD_Record> hholdSubsetW1;
            hholdSubsetW1 = hholdHandler.loadCacheSubsetWave1();
        } catch (Exception ex) {
            HashMap<Short, WIGB_WaAS_Wave1_HHOLD_Record> hholdSubsetW1;
            hholdSubsetW1 = new HashMap<>();
            ite = hholdW1.keySet().iterator();
            while (ite.hasNext()) {
                CASEW1 = ite.next();
                if (tCASEW1IDsInCASEW2.contains(CASEW1)) {
                    hholdSubsetW1.put(CASEW1,
                            (WIGB_WaAS_Wave1_HHOLD_Record) hholdW1.get(CASEW1));
                }
            }
            hholdHandler.storeCacheSubset(1, hholdSubsetW1);
        }
        hholdW1 = null;

        /**
         * Step 5: Wave 2.
         */
        wave = 2;
        // tWave2ToWave1HHOLDIDLookup keys are CASEW2 and values are CASEW1.
        HashMap<Short, Short> tWave2ToWave1HHOLDIDLookup;
        File cf;
        cf = new File(outdir, "Wave2ToFromWave1Lookups." + Env.Strings.S_dat);
        if (cf.exists()) {
            Object[] o;
            o = (Object[]) Generic_StaticIO.readObject(cf);
            tWave2ToWave1HHOLDIDLookup = (HashMap<Short, Short>) o[0];
        } else {
            tWave2ToWave1HHOLDIDLookup = new HashMap<>();
            // tWave1ToWave2HHOLDIDLookup keys are CASEW1 and values are sets of CASEW2.
            HashMap<Short, HashSet<Short>> tWave1ToWave2HHOLDIDLookup;
            tWave1ToWave2HHOLDIDLookup = new HashMap<>();
            ite = tCASEW2IDsInCASEW3.iterator();
            while (ite.hasNext()) {
                CASEW2 = ite.next();
                WIGB_WaAS_Wave2_HHOLD_Record r;
                r = (WIGB_WaAS_Wave2_HHOLD_Record) hholdW2.get(CASEW2);
                CASEW1 = r.getCASEW1();
                if (CASEW1 > Short.MIN_VALUE) {
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
            Object[] o;
            o = new Object[2];
            o[0] = tWave2ToWave1HHOLDIDLookup;
            o[1] = tWave1ToWave2HHOLDIDLookup;
            Generic_StaticIO.writeObject(o, cf);
        }
        // Load test or generate hholdSubsetW2 subset.
        try {
            HashMap<Short, WIGB_WaAS_Wave2_HHOLD_Record> hholdSubsetW2;
            hholdSubsetW2 = hholdHandler.loadCacheSubsetWave2();
        } catch (Exception ex) {
            HashMap<Short, WIGB_WaAS_Wave2_HHOLD_Record> hholdSubsetW2;
            hholdSubsetW2 = new HashMap<>();
            ite = hholdW2.keySet().iterator();
            while (ite.hasNext()) {
                CASEW2 = ite.next();
                if (tWave2ToWave1HHOLDIDLookup.keySet().contains(CASEW2)) {
                    hholdSubsetW2.put(CASEW2,
                            (WIGB_WaAS_Wave2_HHOLD_Record) hholdW2.get(CASEW2));
                }
            }
            hholdHandler.storeCacheSubset(2, hholdSubsetW2);
        }
        hholdW2 = null;

        /**
         * Step 5: Wave 3.
         */
        wave = 3;
        // tWave3ToWave1HHOLDIDLookup keys are CASEW3 and values are CASEW1.
        HashMap<Short, Short> tWave3ToWave1HHOLDIDLookup;
        cf = new File(outdir, "Wave3ToFromWave1Lookups." + Env.Strings.S_dat);
        if (cf.exists()) {
            Object[] o;
            o = (Object[]) Generic_StaticIO.readObject(cf);
            tWave3ToWave1HHOLDIDLookup = (HashMap<Short, Short>) o[0];
        } else {
            tWave3ToWave1HHOLDIDLookup = new HashMap<>();
            // tWave1ToWave3HHOLDIDLookup keys are CASEW1 and values are sets of CASEW3.
            HashMap<Short, HashSet<Short>> tWave1ToWave3HHOLDIDLookup;
            tWave1ToWave3HHOLDIDLookup = new HashMap<>();
            ite = tCASEW3IDsInCASEW4.iterator();
            while (ite.hasNext()) {
                CASEW3 = ite.next();
                WIGB_WaAS_Wave3_HHOLD_Record r;
                r = (WIGB_WaAS_Wave3_HHOLD_Record) hholdW3.get(CASEW3);
                CASEW1 = r.getCASEW1();
                if (CASEW1 > Short.MIN_VALUE) {
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
            Object[] o;
            o = new Object[2];
            o[0] = tWave3ToWave1HHOLDIDLookup;
            o[1] = tWave1ToWave3HHOLDIDLookup;
            Generic_StaticIO.writeObject(o, cf);
        }
        // Load test or generate hholdSubsetW3 subset.
        try {
            HashMap<Short, WIGB_WaAS_Wave3_HHOLD_Record> hholdSubsetW3;
            hholdSubsetW3 = hholdHandler.loadCacheSubsetWave3();
        } catch (Exception ex) {
            HashMap<Short, WIGB_WaAS_Wave3_HHOLD_Record> hholdSubsetW3;
            hholdSubsetW3 = new HashMap<>();
            ite = hholdW3.keySet().iterator();
            while (ite.hasNext()) {
                CASEW3 = ite.next();
                if (tWave3ToWave1HHOLDIDLookup.keySet().contains(CASEW3)) {
                    hholdSubsetW3.put(CASEW3,
                            (WIGB_WaAS_Wave3_HHOLD_Record) hholdW3.get(CASEW3));
                }
            }
            hholdHandler.storeCacheSubset(3, hholdSubsetW3);
        }
        hholdW3 = null;

        /**
         * Step 5: Wave 4: The iterator is set up slightly differently to the
         * one for waves 2 and 3.
         */
        wave = 4;
        // tWave4ToWave1HHOLDIDLookup keys are CASEW4 and values are CASEW1.
        HashMap<Short, Short> tWave4ToWave1HHOLDIDLookup;
        cf = new File(outdir, "Wave4ToFromWave1Lookups." + Env.Strings.S_dat);
        if (cf.exists()) {
            Object[] o;
            o = (Object[]) Generic_StaticIO.readObject(cf);
            tWave4ToWave1HHOLDIDLookup = (HashMap<Short, Short>) o[0];
        } else {
            tWave4ToWave1HHOLDIDLookup = new HashMap<>();
            // tWave1ToWave4HHOLDIDLookup keys are CASEW1 and values are sets of CASEW4.
            HashMap<Short, HashSet<Short>> tWave1ToWave4HHOLDIDLookup;
            tWave1ToWave4HHOLDIDLookup = new HashMap<>();
            ite = iDListsW5[1].iterator();
            while (ite.hasNext()) {
                CASEW4 = ite.next();
                WIGB_WaAS_Wave4_HHOLD_Record r;
                r = (WIGB_WaAS_Wave4_HHOLD_Record) hholdW4.get(CASEW4);
                // The following if statement is here to deal with the rogue case 
                // where CASEW3 6564 is reported exist in wave 4, but doesn't!
                if (r != null) {
                    CASEW1 = r.getCASEW1();
                    if (CASEW1 > Short.MIN_VALUE) {
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
            }
            Object[] o;
            o = new Object[2];
            o[0] = tWave4ToWave1HHOLDIDLookup;
            o[1] = tWave1ToWave4HHOLDIDLookup;
            Generic_StaticIO.writeObject(o, cf);
        }
        // Load test or generate hholdSubsetW4 subset.
        try {
            HashMap<Short, WIGB_WaAS_Wave4_HHOLD_Record> hholdSubsetW4;
            hholdSubsetW4 = hholdHandler.loadCacheSubsetWave4();
        } catch (Exception ex) {
            HashMap<Short, WIGB_WaAS_Wave4_HHOLD_Record> hholdSubsetW4;
            hholdSubsetW4 = new HashMap<>();
            ite = hholdW4.keySet().iterator();
            while (ite.hasNext()) {
                CASEW4 = ite.next();
                if (tWave4ToWave1HHOLDIDLookup.keySet().contains(CASEW4)) {
                    hholdSubsetW4.put(CASEW4,
                            (WIGB_WaAS_Wave4_HHOLD_Record) hholdW4.get(CASEW4));
                }
            }
            hholdHandler.storeCacheSubset(4, hholdSubsetW4);
        }
        hholdW4 = null;
        /**
         * Wave 5. The iterator is set up slightly differently to the one for
         * waves 2 and 3.
         */
        wave = 5;
        // tWave4ToWave1HHOLDIDLookup keys are CASEW5 and values are CASEW1.
        HashMap<Short, Short> tWave5ToWave1HHOLDIDLookup;
        cf = new File(outdir, "Wave5ToFromWave1Lookups." + Env.Strings.S_dat);
        if (cf.exists()) {
            Object[] o;
            o = (Object[]) Generic_StaticIO.readObject(cf);
            tWave5ToWave1HHOLDIDLookup = (HashMap<Short, Short>) o[0];
        } else {
            tWave5ToWave1HHOLDIDLookup = new HashMap<>();
            // tWave1ToWave5HHOLDIDLookup keys are CASEW1 and values are sets of CASEW5.
            HashMap<Short, HashSet<Short>> tWave1ToWave5HHOLDIDLookup;
            tWave1ToWave5HHOLDIDLookup = new HashMap<>();
            ite = iDListsW5[0].iterator();
            while (ite.hasNext()) {
                CASEW5 = ite.next();
                WIGB_WaAS_Wave5_HHOLD_Record r;
                r = (WIGB_WaAS_Wave5_HHOLD_Record) hholdW5.get(CASEW5);
                CASEW1 = r.getCASEW1();
                if (CASEW1 > Short.MIN_VALUE) {
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
            Object[] o;
            o = new Object[2];
            o[0] = tWave5ToWave1HHOLDIDLookup;
            o[1] = tWave1ToWave5HHOLDIDLookup;
            Generic_StaticIO.writeObject(o, cf);
        }
        // Load test or generate hholdSubsetW5 subset.
        try {
            HashMap<Short, WIGB_WaAS_Wave5_HHOLD_Record> hholdSubsetW5;
            hholdSubsetW5 = hholdHandler.loadCacheSubsetWave5();
        } catch (Exception ex) {
            HashMap<Short, WIGB_WaAS_Wave5_HHOLD_Record> hholdSubsetW5;
            hholdSubsetW5 = new HashMap<>();
            ite = hholdW5.keySet().iterator();
            while (ite.hasNext()) {
                CASEW5 = ite.next();
                if (tWave5ToWave1HHOLDIDLookup.keySet().contains(CASEW5)) {
                    hholdSubsetW5.put(CASEW5,
                            (WIGB_WaAS_Wave5_HHOLD_Record) hholdW5.get(CASEW5));
                }
            }
            hholdHandler.storeCacheSubset(5, hholdSubsetW5);
        }
        hholdW5 = null;
//        WIGB_PERSON_Handler personHandler;
//        personHandler = new WIGB_PERSON_Handler(Env, indir);
//        personHandler.loadSubsetWave1(tCASEW1IDsInCASEW2);
//        personHandler.loadSubsetWave2(tWave2ToWave1HHOLDIDLookup.keySet()); # Fails due to OutOfMemoryError.
//        personHandler.loadSubsetWave3(tWave3ToWave1HHOLDIDLookup.keySet());
//        personHandler.loadSubsetWave4(tWave4ToWave1HHOLDIDLookup.keySet());
//        personHandler.loadSubsetWave5(tWave5ToWave1HHOLDIDLookup.keySet());
    }

    protected Object[] getLookups(int wavel, int waveh, File dir) {
        Object[] r = null;
        String filename;
        filename = "Wave" + wavel + "ToFromWave" + waveh + "Lookups."
                + Env.Strings.S_dat;
        File cf;
        cf = new File(dir, filename);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        }
        return r;
    }

    boolean doJavaCodeGeneration = false;
    boolean doLoadDataIntoCaches = false;
}

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

import java.io.BufferedReader;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Collection;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Combined_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Data;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_HHOLD_Handler;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_ID;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_PERSON_Handler;
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
        WIGB_Environment env;
        env = new WIGB_Environment();
        p = new WIGB_Main_Process(env);
        p.Env.data.Env = env;
        p.Files.setDataDirectory(new File(System.getProperty("user.dir"), "data"));
        // Main switches
        //p.doJavaCodeGeneration = true;
        p.doLoadDataIntoCaches = true; // rename/reuse just left here for convenience...
//        // For adding back collection keys.
//        for (short s = 0; s < 18; s ++ ){
//            p.Env.data.data.put(s, null);
//        }
//        p.Env.cacheData();
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

        int chunkSize;
        chunkSize = 256; //1024; 512; 256;
        doDataProcessingStep1(indir, outdir, hholdHandler);
        doDataProcessingStep2(indir, outdir, hholdHandler, chunkSize);
        doDataProcessingStep3(indir, outdir, hholdHandler, chunkSize);
    }

    public void doDataProcessingStep3(File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int chunkSize) {
        System.out.println(Env.data.lookup.size());
        System.out.println(Env.data.data.size());
//        // This way although good in that it uses streams is inefficient as is 
//        // due to repetitive loading and caching of data.
//        long tDVLUKVAL = Env.data.lookup.entrySet().stream()
//                .mapToLong(o -> {
//                    Env.checkAndMaybeFreeMemory();
//                    short CASEW1 = o.getKey();
//                    short collectionID = Env.data.lookup.get(CASEW1);
//                    WIGB_WaAS_Collection c;
//                    c = Env.data.getCollection(collectionID);
//                    WIGB_WaAS_Combined_Record cr;
//                    cr = c.getData().get(CASEW1);
//                    return cr.w1Record.getHhold().getDVLUKVAL();  // Value of UK Land
//                }).sum();
//        System.out.println("tDVLUKVAL " + tDVLUKVAL);
        // This way although good in that it uses streams is inefficient as is 
        // due to repetitive loading and caching of data.
        long total_DVLUKVAL = Env.data.data.keySet().stream()
                .mapToLong(collectionID -> {
                    WIGB_WaAS_Collection c;
                    c = Env.data.getCollection(collectionID);
                    long tDVLUKVAL = c.getData().keySet().stream()
                            .mapToLong(CASEW1 -> {
                                WIGB_WaAS_Combined_Record cr;
                                cr = c.getData().get(CASEW1);
                                c.getData().get(CASEW1);
                                return cr.w1Record.getHhold().getDVLUKVAL();  // Value of UK Land
                            }).sum();
                    Env.data.clearCollection(collectionID);
                    return tDVLUKVAL;  // Value of UK Land
                }).sum();
        System.out.println("total_DVLUKVAL " + total_DVLUKVAL);
//        .forEach(CASEW1 -> {
//                    
//                    WIGB_WaAS_Combined_Record cr;
//                    cr = c.getData().get(CASEW1);
//                    //cr.w1Record.getHhold().getDVLUKDEBT(); // Debt on UK Land
//                    tDVLUKVAL += cr.w1Record.getHhold().getDVLUKVAL();  // Value of UK Land
//                    //cr.w1Record.getHhold().getDVOPRDEBT(); // Debt on other property
//                    //cr.w1Record.getHhold().getDVOPRVAL();  // Value of other property
//                    //cr.w1Record.getHhold().getHPROPW(); // Total property wealth
//                    //cr.w1Record.getHhold().getGOR(); // Government Office Region Code
//                    cr.w2Record.getHhold().getDVLUKVAL();  // Value of UK Land
//                    cr.w3Record.getHhold().getDVLUKVAL_SUM();  // Value of UK Land
//                    cr.w4Record.getHhold().getDVLUKVAL_SUM();  // Value of UK Land
//                    cr.w5Record.getHhold().getDVLUKVAL_SUM();  // Value of UK Land
//                    cr.w1Record.getPeople().stream()
//                            .forEach(w1person -> {
//                                w1person.getDVLUKDEBT(); // Derived - Total land uk debt
//                                w1person.getDVLUKV(); // Derived - Total land uk value
//                            });
//                });
    }

    
    /**
     * Merge Person and Household Data
     *
     * @param indir
     * @param outdir
     * @param hholdHandler
     * @param chunkSize 
     */
    public void doDataProcessingStep2(File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int chunkSize) {
        WIGB_WaAS_PERSON_Handler personHandler;
        personHandler = new WIGB_WaAS_PERSON_Handler(Env, indir);
        System.out.println("Merge Person and Household Data");
        int numberOfCollections = doDataProcessingStep2Wave1(
                data, personHandler, indir, outdir, hholdHandler, chunkSize);
        doDataProcessingStep2Wave2(
                data, personHandler, indir, outdir, hholdHandler, numberOfCollections);
        doDataProcessingStep2Wave3(
                data, personHandler, indir, outdir, hholdHandler, numberOfCollections);
        doDataProcessingStep2Wave4(
                data, personHandler, indir, outdir, hholdHandler, numberOfCollections);
        doDataProcessingStep2Wave5(
                data, personHandler, indir, outdir, hholdHandler, numberOfCollections);
        data.clearAllData();
        Env.cacheData();
    }

    /**
     * Merge Person and Household Data for Wave 1.
     *
     * @param data
     * @param personHandler
     * @param indir
     * @param outdir
     * @param hholdHandler
     * @param chunkSize
     * @return
     */
    public static int doDataProcessingStep2Wave1(
            WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler,
            File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int chunkSize) {
        int numberOfCollections;
        System.out.println("Wave 1");
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave1();
        Set<WIGB_WaAS_ID> set;
        set = hs.keySet();
        numberOfCollections = (int) Math.ceil((double) set.size() / (double) chunkSize);
        Object[] ps;
        ps = personHandler.loadSubsetWave1(set,
                numberOfCollections, WIGB_WaAS_Data.W1, outdir);
        HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
        collectionIDSets = (HashMap<Short, Set<WIGB_WaAS_ID>>) ps[0];
        HashMap<Short, File> collectionIDFiles;
        collectionIDFiles = (HashMap<Short, File>) ps[1];
        collectionIDSets.keySet().stream()
                .forEach(collectionID -> {
                    System.out.println("collectionID " + collectionID);
                    WIGB_WaAS_Collection c;
                    c = new WIGB_WaAS_Collection(collectionID);
                    data.data.put(collectionID, c);
                    // Add household records.
                    Set<WIGB_WaAS_ID> s;
                    s = collectionIDSets.get(collectionID);
                    s.stream()
                            .forEach(ID -> {
                                short CASEW1 = ID.getCASEW1();
                                data.lookup.put(CASEW1, collectionID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    cr = new WIGB_WaAS_Combined_Record(CASEW1);
                                    m.put(CASEW1, cr);
                                }
                                cr.w1Record.setHhold(hs.get(ID));
                            });
                    // Add person records.
                    File f;
                    BufferedReader reader;
                    f = collectionIDFiles.get(collectionID);
                    reader = Generic_StaticIO.getBufferedReader(f);
                    reader.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave1_PERSON_Record p;
                                p = new WIGB_WaAS_Wave1_PERSON_Record(line);
                                short CASEW1;
                                CASEW1 = p.getCASEW1();
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                cr.w1Record.getPeople().add(p);
                            });
                    data.clearCollection(collectionID);
                });
        return numberOfCollections;
    }

    /**
     * Merge Person and Household Data for Wave 1.
     *
     * @param data
     * @param personHandler
     * @param indir
     * @param outdir
     * @param hholdHandler
     * @param numberOfCollections
     */
    public static void doDataProcessingStep2Wave2(
            WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler,
            File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int numberOfCollections) {
        System.out.println("Wave 2");
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave2();
        Object[] ps;
        ps = personHandler.loadSubsetWave2(hs.keySet(),
                numberOfCollections, WIGB_WaAS_Data.W2, outdir);
        HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
        collectionIDSets = (HashMap<Short, Set<WIGB_WaAS_ID>>) ps[0];
        HashMap<Short, File> collectionIDFiles;
        collectionIDFiles = (HashMap<Short, File>) ps[1];
        collectionIDSets.keySet().stream()
                .forEach(collectionID -> {
                    System.out.println("collectionID " + collectionID);
                    WIGB_WaAS_Collection c;
                    c = data.getCollection(collectionID);
                    // Add household records.
                    Set<WIGB_WaAS_ID> s;
                    s = collectionIDSets.get(collectionID);
                    s.stream()
                            .forEach(ID -> {
                                short CASEW1 = ID.getCASEW1();
                                data.lookup.put(CASEW1, collectionID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    System.out.println("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error?");
                                } else {
                                    cr.w2Record.setHhold(
                                            hs.get(ID));
                                }
                            });
                    // Add person records.
                    File f;
                    BufferedReader reader;
                    f = collectionIDFiles.get(collectionID);
                    reader = Generic_StaticIO.getBufferedReader(f);
                    reader.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave2_PERSON_Record p;
                                p = new WIGB_WaAS_Wave2_PERSON_Record(line);
                                short CASEW1;
                                CASEW1 = p.getCASEW1();
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    System.out.println("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error, "
                                            + "or this person may have "
                                            + "moved from one household "
                                            + "to another?");
                                } else {
                                    cr.w2Record.getPeople().add(p);
                                }
                            });
                    data.clearCollection(collectionID);
                });
    }

    /**
     * Merge Person and Household Data for Wave 3.
     *
     * @param data
     * @param personHandler
     * @param indir
     * @param outdir
     * @param hholdHandler
     * @param numberOfCollections
     */
    public static void doDataProcessingStep2Wave3(
            WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler,
            File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int numberOfCollections) {
        System.out.println("Wave 3");
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave3();
        Object[] ps;
        ps = personHandler.loadSubsetWave3(hs.keySet(),
                numberOfCollections, WIGB_WaAS_Data.W3, outdir);
        HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
        collectionIDSets = (HashMap<Short, Set<WIGB_WaAS_ID>>) ps[0];
        HashMap<Short, File> collectionIDFiles;
        collectionIDFiles = (HashMap<Short, File>) ps[1];
        collectionIDSets.keySet().stream()
                .forEach(collectionID -> {
                    System.out.println("collectionID " + collectionID);
                    WIGB_WaAS_Collection c;
                    c = data.getCollection(collectionID);
                    // Add household records.
                    Set<WIGB_WaAS_ID> s;
                    s = collectionIDSets.get(collectionID);
                    s.stream()
                            .forEach(ID -> {
                                short CASEW1 = ID.getCASEW1();
                                data.lookup.put(CASEW1, collectionID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    System.out.println("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error?");
                                } else {
                                    cr.w3Record.setHhold(
                                            hs.get(ID));
                                }
                            });
                    // Add person records.
                    File f;
                    BufferedReader reader;
                    f = collectionIDFiles.get(collectionID);
                    reader = Generic_StaticIO.getBufferedReader(f);
                    reader.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave3_PERSON_Record p;
                                p = new WIGB_WaAS_Wave3_PERSON_Record(line);
                                short CASEW1;
                                CASEW1 = p.getCASEW1();
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    System.out.println("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error, "
                                            + "or this person may have "
                                            + "moved from one household "
                                            + "to another?");
                                } else {
                                    cr.w3Record.getPeople().add(p);
                                }
                            });
                    data.clearCollection(collectionID);
                });
    }

    /**
     * Merge Person and Household Data for Wave 4.
     *
     * @param data
     * @param personHandler
     * @param indir
     * @param outdir
     * @param hholdHandler
     * @param numberOfCollections
     */
    public static void doDataProcessingStep2Wave4(
            WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler,
            File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int numberOfCollections) {
        // Wave 4
        System.out.println("Wave 4");
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave4();
        Object[] ps;
        ps = personHandler.loadSubsetWave4(hs.keySet(),
                numberOfCollections, WIGB_WaAS_Data.W4, outdir);
        HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
        collectionIDSets = (HashMap<Short, Set<WIGB_WaAS_ID>>) ps[0];
        HashMap<Short, File> collectionIDFiles;
        collectionIDFiles = (HashMap<Short, File>) ps[1];
        collectionIDSets.keySet().stream()
                .forEach(collectionID -> {
                    System.out.println("collectionID " + collectionID);
                    WIGB_WaAS_Collection c;
                    c = data.getCollection(collectionID);
                    // Add household records.
                    Set<WIGB_WaAS_ID> s;
                    s = collectionIDSets.get(collectionID);
                    s.stream()
                            .forEach(ID -> {
                                short CASEW1 = ID.getCASEW1();
                                data.lookup.put(CASEW1, collectionID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    System.out.println("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error?");
                                } else {
                                    cr.w4Record.setHhold(
                                            hs.get(ID));
                                }
                            });
                    // Add person records.
                    File f;
                    BufferedReader reader;
                    f = collectionIDFiles.get(collectionID);
                    reader = Generic_StaticIO.getBufferedReader(f);
                    reader.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave4_PERSON_Record p;
                                p = new WIGB_WaAS_Wave4_PERSON_Record(line);
                                short CASEW1;
                                CASEW1 = p.getCASEW1();
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    System.out.println("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error, "
                                            + "or this person may have "
                                            + "moved from one household "
                                            + "to another?");
                                } else {
                                    cr.w4Record.getPeople().add(p);
                                }
                            });
                    data.clearCollection(collectionID);
                });
    }

    /**
     * Merge Person and Household Data for Wave 5.
     *
     * @param data
     * @param personHandler
     * @param indir
     * @param outdir
     * @param hholdHandler
     * @param numberOfCollections
     */
    public static void doDataProcessingStep2Wave5(
            WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler,
            File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int numberOfCollections) {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave5();
        Object[] ps;
        ps = personHandler.loadSubsetWave5(hs.keySet(),
                numberOfCollections, WIGB_WaAS_Data.W5, outdir);
        HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
        collectionIDSets = (HashMap<Short, Set<WIGB_WaAS_ID>>) ps[0];
        HashMap<Short, File> collectionIDFiles;
        collectionIDFiles = (HashMap<Short, File>) ps[1];
        collectionIDSets.keySet().stream()
                .forEach(collectionID -> {
                    WIGB_WaAS_Collection c;
                    c = data.getCollection(collectionID);
                    // Add household records.
                    Set<WIGB_WaAS_ID> s;
                    s = collectionIDSets.get(collectionID);
                    s.stream()
                            .forEach(ID -> {
                                short CASEW1 = ID.getCASEW1();
                                data.lookup.put(CASEW1, collectionID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    System.out.println("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error?");
                                } else {
                                    cr.w5Record.setHhold(
                                            hs.get(ID));
                                }
                            });
                    // Add person records.
                    File f;
                    BufferedReader reader;
                    f = collectionIDFiles.get(collectionID);
                    reader = Generic_StaticIO.getBufferedReader(f);
                    reader.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave5_PERSON_Record p;
                                p = new WIGB_WaAS_Wave5_PERSON_Record(line);
                                short CASEW1;
                                CASEW1 = p.getCASEW1();
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    System.out.println("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error, "
                                            + "or this person may have "
                                            + "moved from one household "
                                            + "to another?");
                                } else {
                                    cr.w5Record.getPeople().add(p);
                                }
                            });
                    data.clearCollection(collectionID);
                });
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
     * Read input data and create subsets. Organise for person records that each
     * subset is split into separate files one for each collection. The
     * collections will be merged one by one in doDataProcessingStep2.
     *
     * @param indir
     * @param outdir
     * @param hholdHandler
     */
    public void doDataProcessingStep1(File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler) {
        // For convenience/code brevity.
        byte nwaves;
        nwaves = WIGB_WaAS_Data.NWAVES;
        /**
         * Step 1: Load household data into cache and memory.
         */
        Object[] hholdData;
        hholdData = hholdHandler.load();

        /**
         * Step 2: Unpack hholdData. hholdData is an Object[] of length 2. r[0]
         * is a TreeMap with Integer keys which are the CASE id for the wave and
         * the values are WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>. r[1] is an
         * array of TreeSets where: For Wave 5; r[1][0] is a list of CASEW5
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
        HashMap<Byte, TreeSet<WIGB_WaAS_ID>[]> iDLists;
        iDLists = new HashMap<>();
        // W1
        Object[] hholdDataW1;
        hholdDataW1 = (Object[]) hholdData[0];
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW1;
        hholdW1 = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW1[0];
        TreeSet<WIGB_WaAS_ID>[] iDListsW1;
        iDListsW1 = (TreeSet<WIGB_WaAS_ID>[]) hholdDataW1[1];
        iDLists.put(WIGB_WaAS_Data.W1, iDListsW1);
        // W2
        Object[] hholdDataW2;
        hholdDataW2 = (Object[]) hholdData[1];
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW2;
        hholdW2 = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW2[0];
        TreeSet<WIGB_WaAS_ID>[] iDListsW2;
        iDListsW2 = (TreeSet<WIGB_WaAS_ID>[]) hholdDataW2[1];
        iDLists.put(WIGB_WaAS_Data.W2, iDListsW2);
        // W3
        Object[] hholdDataW3;
        hholdDataW3 = (Object[]) hholdData[2];
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW3;
        hholdW3 = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW3[0];
        TreeSet<WIGB_WaAS_ID>[] iDListsW3;
        iDListsW3 = (TreeSet<WIGB_WaAS_ID>[]) hholdDataW3[1];
        iDLists.put(WIGB_WaAS_Data.W3, iDListsW3);
        // W4
        Object[] hholdDataW4;
        hholdDataW4 = (Object[]) hholdData[3];
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW4;
        hholdW4 = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW4[0];
        TreeSet<WIGB_WaAS_ID>[] iDListsW4;
        iDListsW4 = (TreeSet<WIGB_WaAS_ID>[]) hholdDataW4[1];
        iDLists.put(WIGB_WaAS_Data.W4, iDListsW4);
        // W5
        Object[] hholdDataW5;
        hholdDataW5 = (Object[]) hholdData[4];
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW5;
        hholdW5 = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdDataW5[0];
        TreeSet<WIGB_WaAS_ID>[] iDListsW5;
        iDListsW5 = (TreeSet<WIGB_WaAS_ID>[]) hholdDataW5[1];
        iDLists.put(WIGB_WaAS_Data.W5, iDListsW5);

        /**
         * Step 3: Print out the Number of Households in each wave.
         */
        for (byte wave = nwaves; wave > 0; wave--) {
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
         * Step 4: Get IDs of all hholds in all waves. This assumes that CASEW1
         * has been correctly encoded in each wave.
         */
        // Wave 1
        TreeSet<WIGB_WaAS_ID> all;
        all = new TreeSet<>();
        all.addAll(iDListsW1[0]);
        all.retainAll(iDListsW2[1]);
        all.retainAll(iDListsW3[2]);
        all.retainAll(iDListsW4[3]);
        all.retainAll(iDListsW5[4]);
        System.out.println("" + all.size()
                + "\tNumber of HHOLDs in Waves 5, 4, 3, 2 and 1");
// Previously we assumed that the links from one wave to the follwoing wave were 
// correct, but it odd that only 2 records were matched from Wave 3 to Wave 2 so
// this could be a data issue and the new way above is probably better!? It 
// might be worth contacting ONS about this... 
//        /**
//         * Step 4: Get IDs of all hholds in waves going back from Wave 5 to Wave
//         * 1; and, report the number of Households that are in all 5 waves
//         * unchanged.
//         */
//        Iterator<WIGB_WaAS_ID> ite;
//        WIGB_WaAS_ID ID;
//        short CASEW5;
//        short CASEW4;
//        short CASEW3;
//        short CASEW2;
//        short CASEW1;
//        /**
//         * Get IDs of all hholds in waves 5, 4, 3 and 1.
//         */
//        TreeSet<WIGB_WaAS_ID> tCASEW3IDsInCASEW4;
//        tCASEW3IDsInCASEW4 = new TreeSet<>();
//        // Initialise the iterator to go through the list of all hhold IDs from 
//        // Wave 5 that are in Wave 4.
//        ite = iDListsW5[1].iterator();
//        while (ite.hasNext()) {
//            ID = ite.next();
//            CASEW1 = ID.getCASEW1();
//            if (CASEW1 > Short.MIN_VALUE) {
//                if (hholdW4.containsKey(ID)) {
//                    WIGB_WaAS_Wave4_HHOLD_Record r;
//                    r = (WIGB_WaAS_Wave4_HHOLD_Record) hholdW4.get(ID);
//                    CASEW3 = r.getCASEW3();
//                    if (CASEW3 > Short.MIN_VALUE) {
//                        ID = new WIGB_WaAS_ID(CASEW1, CASEW3);
//                        if (iDListsW3[0].contains(ID)) {
//                            tCASEW3IDsInCASEW4.add(ID);
//                        } else {
//                            System.out.println("Warning Wave 4 CASEW3 " + CASEW3
//                                    + " is reported to exist, but no such ID is in "
//                                    + "the input!");
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("" + tCASEW3IDsInCASEW4.size()
//                + "\tNumber of HHOLDs in Waves 5, 4, 3 and 1");
//        /**
//         * Get IDs of all hholds in waves 5, 4, 3, 2 and 1.
//         */
//        TreeSet<WIGB_WaAS_ID> tCASEW2IDsInCASEW3;
//        tCASEW2IDsInCASEW3 = new TreeSet<>();
//        // Initialise the iterator to go through the list of all hhold IDs that 
//        // are in Waves 5, Wave 4 and Wave 3.
//        ite = tCASEW3IDsInCASEW4.iterator();
//        
//        int count = 0;
//        
//        while (ite.hasNext()) {
//            ID = ite.next();
//            if (hholdW3.containsKey(ID)) {
//                count ++;
//                WIGB_WaAS_Wave3_HHOLD_Record r;
//                r = (WIGB_WaAS_Wave3_HHOLD_Record) hholdW3.get(ID);
//                CASEW2 = r.getCASEW2();
//                if (CASEW2 > Short.MIN_VALUE) {
//                    CASEW1 = ID.getCASEW1();
//                    ID = new WIGB_WaAS_ID(CASEW1, CASEW2);
//                    if (iDListsW2[0].contains(ID)) {
//                        tCASEW2IDsInCASEW3.add(ID);
//                    } else {
//                        System.out.println("Warning Wave 3 CASEW2 " + CASEW2
//                                + " is reported to exist, but no such ID is in "
//                                + "the input!");
//                    }
//                }
//            }
//        }
//        System.out.println("" + tCASEW2IDsInCASEW3.size()
//                + "\tNumber of HHOLDs in Waves 5, 4, 3 and 2");

        /**
         * Step 5: Working forward from Wave 1, get the subset of households
         * that are in all 5 waves; create mappings from the wave ID to the Wave
         * 1 ID and vice versa as appropriate (a mapping from Wave 1 to itself
         * is unnecessary).
         */
        Iterator<WIGB_WaAS_ID> ite;
        WIGB_WaAS_ID ID;
//        short CASEW5;
//        short CASEW4;
//        short CASEW3;
//        short CASEW2;
        short CASEW1;
        /**
         * Wave 1.
         */
        // Load test or generate hholdSubsetW1 subset.
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record> hholdSubsetW1;
        hholdSubsetW1 = hholdHandler.loadCacheSubsetWave1();
        if (hholdSubsetW1 == null) {
            hholdSubsetW1 = new TreeMap<>();
            ite = all.iterator();
            while (ite.hasNext()) {
                ID = ite.next();
                WIGB_WaAS_Wave1_HHOLD_Record rec;
                rec = (WIGB_WaAS_Wave1_HHOLD_Record) hholdW1.get(ID);
//                if (rec == null) {
//                    System.out.println("Debug"); // Never occurs.
//                }
                hholdSubsetW1.put(ID, rec);
            }
            hholdHandler.cacheSubset(WIGB_WaAS_Data.W1, hholdSubsetW1);
        }
        /**
         * Wave 2.
         */
        // Load test or generate hholdSubsetW2 subset.
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record> hholdSubsetW2;
        hholdSubsetW2 = hholdHandler.loadCacheSubsetWave2();
        if (hholdSubsetW2 == null) {
            hholdSubsetW2 = new TreeMap<>();
            ite = hholdW2.keySet().iterator();
            while (ite.hasNext()) {
                ID = ite.next();
                WIGB_WaAS_Wave2_HHOLD_Record rec;
                rec = (WIGB_WaAS_Wave2_HHOLD_Record) hholdW2.get(ID);
                CASEW1 = rec.getCASEW1();
                WIGB_WaAS_ID ID2;
                ID2 = new WIGB_WaAS_ID(CASEW1, CASEW1);
                if (all.contains(ID2)) {
                    hholdSubsetW2.put(ID, rec);
                }
            }
            hholdHandler.cacheSubset(WIGB_WaAS_Data.W2, hholdSubsetW2);
        }
        /**
         * Wave 3.
         */
        // Load test or generate hholdSubsetW3 subset.
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record> hholdSubsetW3;
        hholdSubsetW3 = hholdHandler.loadCacheSubsetWave3();
        if (hholdSubsetW3 == null) {
            hholdSubsetW3 = new TreeMap<>();
            ite = hholdW3.keySet().iterator();
            while (ite.hasNext()) {
                ID = ite.next();
                WIGB_WaAS_Wave3_HHOLD_Record rec;
                rec = (WIGB_WaAS_Wave3_HHOLD_Record) hholdW3.get(ID);
                CASEW1 = rec.getCASEW1();
                WIGB_WaAS_ID ID2;
                ID2 = new WIGB_WaAS_ID(CASEW1, CASEW1);
                if (all.contains(ID2)) {
                    hholdSubsetW3.put(ID, rec);
                }
            }
            hholdHandler.cacheSubset(WIGB_WaAS_Data.W3, hholdSubsetW3);
        }
        /**
         * Wave 4.
         */
        // Load test or generate hholdSubsetW4 subset.
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record> hholdSubsetW4;
        hholdSubsetW4 = hholdHandler.loadCacheSubsetWave4();
        if (hholdSubsetW4 == null) {
            hholdSubsetW4 = new TreeMap<>();
            ite = hholdW4.keySet().iterator();
            while (ite.hasNext()) {
                ID = ite.next();
                WIGB_WaAS_Wave4_HHOLD_Record rec;
                rec = (WIGB_WaAS_Wave4_HHOLD_Record) hholdW4.get(ID);
                CASEW1 = rec.getCASEW1();
                WIGB_WaAS_ID ID2;
                ID2 = new WIGB_WaAS_ID(CASEW1, CASEW1);
                if (all.contains(ID2)) {
                    hholdSubsetW4.put(ID, rec);
                }
            }
            hholdHandler.cacheSubset(WIGB_WaAS_Data.W4, hholdSubsetW4);
        }
        /**
         * Wave 5.
         */
        // Load test or generate hholdSubsetW5 subset.
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record> hholdSubsetW5;
        hholdSubsetW5 = hholdHandler.loadCacheSubsetWave5();
        if (hholdSubsetW5 == null) {
            hholdSubsetW5 = new TreeMap<>();
            ite = hholdW5.keySet().iterator();
            while (ite.hasNext()) {
                ID = ite.next();
                WIGB_WaAS_Wave5_HHOLD_Record rec;
                rec = (WIGB_WaAS_Wave5_HHOLD_Record) hholdW5.get(ID);
                CASEW1 = rec.getCASEW1();
                WIGB_WaAS_ID ID2;
                ID2 = new WIGB_WaAS_ID(CASEW1, CASEW1);
                if (all.contains(ID2)) {
                    hholdSubsetW5.put(ID, rec);
                }
            }
            hholdHandler.cacheSubset(WIGB_WaAS_Data.W5, hholdSubsetW5);
        }
    }

    boolean doJavaCodeGeneration = false;
    boolean doLoadDataIntoCaches = false;

}

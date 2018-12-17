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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Collection;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Combined_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_Data;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_WaAS_HHOLD_Handler;
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
    protected final WIGB_WaAS_Data data;
    protected final WIGB_Strings Strings;
    protected final WIGB_Files Files;

    // For logging.
    File logF;
    public static transient PrintWriter logPW;
    File logF0;
    public static transient PrintWriter logPW0;

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
        p.Files.setDataDirectory(new File(System.getProperty("user.dir"), "data"));
        // Main switches
        //p.doJavaCodeGeneration = true;
        p.doLoadDataIntoCaches = true; // rename/reuse just left here for convenience...
        p.run();
    }

    public void run() {
        logF0 = new File(Files.getOutputDataDir(Strings), "log0.txt");
        logPW0 = Generic_IO.getPrintWriter(logF0, false); // Overwrite log file.

        if (doJavaCodeGeneration) {
            runJavaCodeGeneration();
        }

        File indir;
        File outdir;
        File generateddir;
        WIGB_WaAS_HHOLD_Handler hholdHandler;

        indir = Files.getWaASInputDir();
        generateddir = Files.getGeneratedWaASDir();
        outdir = new File(generateddir, "Subsets");
        outdir.mkdirs();
        hholdHandler = new WIGB_WaAS_HHOLD_Handler(Env.Files, Env.Strings, indir);

        int chunkSize;
        chunkSize = 256; //1024; 512; 256;
        //doDataProcessingStep1New(indir, outdir, hholdHandler);
        doDataProcessingStep2(indir, outdir, hholdHandler, chunkSize);
        //doDataProcessingStep3(outdir);

        logPW.close();
    }

    /**
     * Go through hholds for all waves and figure which ones have not
     * significantly changed in terms of hhold composition. Having children
     * and children leaving home is fine. Anything else is perhaps an issue.
     *
     * @param outdir
     */
    public void doDataProcessingStep3(File outdir) {
        initlog(3);
        //log(data.lookup.size());
        //log(data.data.size());
//        /**
//         * Calculate the number of hholds that have the same number of
//         * adults throughout.
//         */
//        long n = data.data.keySet().stream()
//                .mapToLong(cID -> {
//                    WIGB_WaAS_Collection c;
//                    c = data.getCollection(cID);
//                    long nc = c.getData().keySet().stream()
//                            .mapToLong(CASEW1 -> {
//                                WIGB_WaAS_Combined_Record cr;
//                                cr = c.getData().get(CASEW1);
//                                c.getData().get(CASEW1);
//                                try {
//                                    byte w1 = cr.w1Record.getHhold().getNUMADULT();
//                                    byte w2 = cr.w2Record.getHhold().getNUMADULT();
//                                    byte w3 = cr.w3Record.getHhold().getNUMADULT();
//                                    byte w4 = cr.w4Record.getHhold().getNUMADULT();
//                                    byte w5 = cr.w5Record.getHhold().getNUMADULT();
//                                    if (w1 == w2 && w2 == w3 && w3 == w4 && w4 == w5) {
//                                        return 1;
//                                    } else {
//                                        return 0;
//                                    }
//                                } catch (NullPointerException e) {
//                                    return 0;
//                                }
//                            }).sum();
//                    data.clearCollection(cID);
//                    return nc;
//                }).sum();
//        log("There are " + n + " hholds that contain the "
//                + "same number of adults throughout.");
// For brevity/convenience.
        byte W1 = WIGB_WaAS_Data.W1;
        byte W2 = WIGB_WaAS_Data.W2;
        byte W3 = WIGB_WaAS_Data.W3;
        byte W4 = WIGB_WaAS_Data.W4;
        byte W5 = WIGB_WaAS_Data.W5;

        HashSet<Short> s = new HashSet<>();
        Iterator<Short> ite;
        Iterator<Short> ite2;
        short cID;
        short CASEW1;
        WIGB_WaAS_Wave1_HHOLD_Record w1h;
        WIGB_WaAS_Wave2_HHOLD_Record w2h;
        WIGB_WaAS_Wave3_HHOLD_Record w3h;
        WIGB_WaAS_Wave4_HHOLD_Record w4h;
        WIGB_WaAS_Wave5_HHOLD_Record w5h;
        WIGB_WaAS_Collection c;
        WIGB_WaAS_Combined_Record cr;
        String m;
        // Check w1Records
        m = "Check w1Records";
        log("<" + m + ">");
        ite = data.data.keySet().iterator();
        while (ite.hasNext()) {
            cID = ite.next();
            c = data.getCollection(cID);
            ite2 = c.getData().keySet().iterator();
            while (ite2.hasNext()) {
                CASEW1 = ite2.next();
                cr = c.getData().get(CASEW1);
                c.getData().get(CASEW1);
                w1h = cr.w1Record.getHhold();
                process0(W1, CASEW1, w1h);
            }
            data.clearCollection(cID);
        }
        log("</" + m + ">");
        // Check w2Records
        m = "Check w2Records";
        log("<" + m + ">");
        ite = data.data.keySet().iterator();
        while (ite.hasNext()) {
            cID = ite.next();
            c = data.getCollection(cID);
            ite2 = c.getData().keySet().iterator();
            while (ite2.hasNext()) {
                CASEW1 = ite2.next();
                cr = c.getData().get(CASEW1);
                c.getData().get(CASEW1);
                w2h = cr.w2Record.getHhold();
                process0(W2, CASEW1, w2h);
            }
            data.clearCollection(cID);
        }
        log("</" + m + ">");
        // Check w3Records
        m = "Check w3Records";
        log("<" + m + ">");
        ite = data.data.keySet().iterator();
        while (ite.hasNext()) {
            cID = ite.next();
            c = data.getCollection(cID);
            ite2 = c.getData().keySet().iterator();
            while (ite2.hasNext()) {
                CASEW1 = ite2.next();
                cr = c.getData().get(CASEW1);
                c.getData().get(CASEW1);
                w3h = cr.w3Record.getHhold();
                process0(W3, CASEW1, w3h);
            }
            data.clearCollection(cID);
        }
        log("</" + m + ">");
        // Check w4Records
        m = "Check w4Records";
        log("<" + m + ">");
        ite = data.data.keySet().iterator();
        while (ite.hasNext()) {
            cID = ite.next();
            c = data.getCollection(cID);
            ite2 = c.getData().keySet().iterator();
            while (ite2.hasNext()) {
                CASEW1 = ite2.next();
                cr = c.getData().get(CASEW1);
                c.getData().get(CASEW1);
                w4h = cr.w4Record.getHhold();
                process0(W4, CASEW1, w4h);
            }
            data.clearCollection(cID);
        }
        log("</" + m + ">");
        // Check w5Records
        m = "Check w5Records";
        log("<" + m + ">");
        ite = data.data.keySet().iterator();
        while (ite.hasNext()) {
            cID = ite.next();
            c = data.getCollection(cID);
            ite2 = c.getData().keySet().iterator();
            while (ite2.hasNext()) {
                CASEW1 = ite2.next();
                cr = c.getData().get(CASEW1);
                c.getData().get(CASEW1);
                w5h = cr.w5Record.getHhold();
                process0(W5, CASEW1, w5h);
            }
            data.clearCollection(cID);
        }

//        /**
//         * Get the IDs of hholds that have the same number of adults
//         * throughout.
//         */
//        ite = data.data.keySet().iterator();
//        while (ite.hasNext()) {
//            cID = ite.next();
//            log1("Processing collection " + cID);
//            c = data.getCollection(cID);
//            ite2 = c.getData().keySet().iterator();
//            while (ite2.hasNext()) {
//                CASEW1 = ite2.next();
//                cr = c.getData().get(CASEW1);
//                c.getData().get(CASEW1);
//                w1h = cr.w1Record.getHhold();
//                w2h = cr.w2Record.getHhold();
//                w3h = cr.w3Record.getHhold();
//                w4h = cr.w4Record.getHhold();
//                w5h = cr.w5Record.getHhold();
//                if (process0(W1, CASEW1, w1h)) {
//                    if (process0(W2, CASEW1, w2h)) {
//                        if (process0(W3, CASEW1, w3h)) {
//                            if (process0(W4, CASEW1, w4h)) {
//                                if (process0(W5, CASEW1, w5h)) {
//                                    s.add(CASEW1);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            data.clearCollection(cID);
//        }
//        log("Number of combined records with all hhold records "
//                + "for each wave " + s.size() + ".");
//        /**
//         * Stream through the data and calculate the total value of UK Land in
//         * Wave 1 hholds. This value is an aggregate of numerical class
//         * values.
//         */
//        long tDVLUKVAL = data.data.keySet().stream()
//                .mapToLong(cID -> {
//                    WIGB_WaAS_Collection c;
//                    c = data.getCollection(cID);
//                    long cDVLUKVAL = c.getData().keySet().stream()
//                            .mapToLong(CASEW1 -> {
//                                WIGB_WaAS_Combined_Record cr;
//                                cr = c.getData().get(CASEW1);
//                                int DVLUKVAL;
//                                DVLUKVAL = cr.w1Record.getHhold().getDVLUKVAL();
//                                if (DVLUKVAL == Integer.MIN_VALUE) {
//                                    DVLUKVAL = 0;
//                                }
//                                return DVLUKVAL;
//                            }).sum();
//                    data.clearCollection(cID);
//                    return cDVLUKVAL;  // Total value of UK Land in c.
//                }).sum();
//        log("Total value of UK Land in Wave 1 " + tDVLUKVAL);
        //
//        /**
//         * Stream through the data and calculate the total income in the last 12
//         * months of all individual people. This value is an aggregate of
//         * numerical class values.
//         */
//        long tFINCVB = data.data.keySet().stream()
//                .mapToLong(cID -> {
//                    WIGB_WaAS_Collection c;
//                    c = data.getCollection(cID);
//                    long cFINCVB = c.getData().keySet().stream()
//                            .mapToLong(CASEW1 -> {
//                                WIGB_WaAS_Combined_Record cr;
//                                cr = c.getData().get(CASEW1);
//                                long hFINCVB = cr.w1Record.getPeople().stream()
//                                        .mapToLong(p -> {
//                                            byte FINCVB;
//                                            FINCVB = p.getFINCVB();
//                                            //if (FINCVB == Byte.MIN_VALUE) {
//                                            if (FINCVB < 0) {
//                                                FINCVB = 0;
//                                            }
//                                            return FINCVB;
//                                        }).sum();
//                                return hFINCVB;
//                            }).sum();
//                    data.clearCollection(cID);
//                    return cFINCVB;  // Total income in the last 12 months.
//                }).sum();
//        log("Total income in the last 12 months " + tFINCVB);
        /**
         * The main WaAS data store. Keys are Collection IDs.
         */
        TreeMap<Integer, String> vIDToVName;
        TreeMap<String, Integer> vNameToVID;
        vIDToVName = new TreeMap<>();
        vNameToVID = new TreeMap<>();
        String sDVLUKDEBT = "DVLUKDEBT";
        addVariable(sDVLUKDEBT, vIDToVName, vNameToVID);
        String sDVLUKVAL = "DVLUKVAL";
        addVariable(sDVLUKVAL, vIDToVName, vNameToVID);

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
        logPW.close();
    }

    protected boolean process0(byte wave, short CASEW1,
            WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record wxh) {
        if (wxh != null) {
            return true;
        } else {
            log("w" + wave + "h == null for CASEW1 " + CASEW1);
            return false;
        }
    }

    protected void addVariable(String s, TreeMap<Integer, String> vIDToVName,
            TreeMap<String, Integer> vNameToVID) {
        vIDToVName.put(0, s);
        vNameToVID.put(s, 0);
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
        initlog(2);
        WIGB_WaAS_PERSON_Handler personHandler;
        personHandler = new WIGB_WaAS_PERSON_Handler(Files, Strings, indir);
        log("Merge Person and Household Data");
        /**
         * Wave 1
         */
        Object[] o;
        o = doDataProcessingStep2Wave1(
                data, personHandler, indir, outdir, hholdHandler, chunkSize);
        int nOC = (Integer) o[0];
        TreeMap<Short, HashSet<Short>> CIDToCASEW1;
        CIDToCASEW1 = (TreeMap<Short, HashSet<Short>>) o[1];
        HashMap<Short, Short> CASEW1ToCID;
        CASEW1ToCID = (HashMap<Short, Short>) o[2];
        /**
         * Wave 2
         */
        Object[] lookups;
        lookups = hholdHandler.loadSubsetLookups(WIGB_WaAS_Data.W1);
        TreeMap<Short, HashSet<Short>> CASEW1ToCASEW2;
        CASEW1ToCASEW2 = (TreeMap<Short, HashSet<Short>>) lookups[0];
        TreeMap<Short, Short> CASEW2ToCASEW1;
        CASEW2ToCASEW1 = (TreeMap<Short, Short>) lookups[1];
        doDataProcessingStep2Wave2(data, personHandler, indir, outdir,
                hholdHandler, nOC, CASEW1ToCID, CIDToCASEW1, CASEW1ToCASEW2,
                CASEW2ToCASEW1);
        /**
         * Wave 3
         */
        lookups = hholdHandler.loadSubsetLookups(WIGB_WaAS_Data.W2);
        TreeMap<Short, HashSet<Short>> CASEW2ToCASEW3;
        CASEW2ToCASEW3 = (TreeMap<Short, HashSet<Short>>) lookups[0];
        TreeMap<Short, Short> CASEW3ToCASEW2;
        CASEW3ToCASEW2 = (TreeMap<Short, Short>) lookups[1];
        doDataProcessingStep2Wave3(data, personHandler, indir, outdir,
                hholdHandler, nOC, CASEW1ToCID, CIDToCASEW1, CASEW1ToCASEW2,
                CASEW2ToCASEW1, CASEW2ToCASEW3, CASEW3ToCASEW2);
        /**
         * Wave 4
         */
        lookups = hholdHandler.loadSubsetLookups(WIGB_WaAS_Data.W3);
        TreeMap<Short, HashSet<Short>> CASEW3ToCASEW4;
        CASEW3ToCASEW4 = (TreeMap<Short, HashSet<Short>>) lookups[0];
        TreeMap<Short, Short> CASEW4ToCASEW3;
        CASEW4ToCASEW3 = (TreeMap<Short, Short>) lookups[1];
        doDataProcessingStep2Wave4(data, personHandler, indir, outdir,
                hholdHandler, nOC, CASEW1ToCID, CIDToCASEW1, CASEW1ToCASEW2,
                CASEW2ToCASEW1, CASEW2ToCASEW3, CASEW3ToCASEW2, CASEW3ToCASEW4,
                CASEW4ToCASEW3);
        /**
         * Wave 5
         */
        lookups = hholdHandler.loadSubsetLookups(WIGB_WaAS_Data.W4);
        TreeMap<Short, HashSet<Short>> CASEW4ToCASEW5;
        CASEW4ToCASEW5 = (TreeMap<Short, HashSet<Short>>) lookups[0];
        TreeMap<Short, Short> CASEW5ToCASEW4;
        CASEW5ToCASEW4 = (TreeMap<Short, Short>) lookups[1];
        doDataProcessingStep2Wave5(data, personHandler, indir, outdir,
                hholdHandler, nOC, CASEW1ToCID, CIDToCASEW1, CASEW1ToCASEW2,
                CASEW2ToCASEW1, CASEW2ToCASEW3, CASEW3ToCASEW2, CASEW3ToCASEW4, 
                CASEW4ToCASEW3, CASEW4ToCASEW5, CASEW5ToCASEW4);
        log("data.lookup.size() " + data.lookup.size());
        log("data.data.size() " + data.data.size());
        Env.cacheData();
        logPW.close();
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
    public static Object[] doDataProcessingStep2Wave1(
            WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler,
            File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int chunkSize) {
        log("Wave 1");
        Object[] r;
        r = new Object[3];
        TreeMap<Short, WIGB_WaAS_Wave1_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave1();
        TreeSet<Short> CASEW1IDs;
        CASEW1IDs = new TreeSet<>();
        CASEW1IDs.addAll(hs.keySet());
        int nOC;
        nOC = (int) Math.ceil((double) CASEW1IDs.size() / (double) chunkSize);
        r[0] = nOC;
        Object[] ps;
        ps = personHandler.loadSubsetWave1(CASEW1IDs, nOC, WIGB_WaAS_Data.W1,
                outdir);
        TreeMap<Short, HashSet<Short>> CIDToCASEW1;
        CIDToCASEW1 = (TreeMap<Short, HashSet<Short>>) ps[0];
        TreeMap<Short, File> cFs;
        cFs = (TreeMap<Short, File>) ps[2];
        r[1] = ps[0];
        r[2] = ps[1];
        CIDToCASEW1.keySet().stream()
                .forEach(cID -> {
                    log("collectionID " + cID);
                    WIGB_WaAS_Collection c;
                    c = new WIGB_WaAS_Collection(cID);
                    data.data.put(cID, c);
                    // Add hhold records.
                    HashSet<Short> s;
                    s = CIDToCASEW1.get(cID);
                    s.stream()
                            .forEach(CASEW1 -> {
                                data.lookup.put(CASEW1, cID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    cr = new WIGB_WaAS_Combined_Record(CASEW1);
                                    m.put(CASEW1, cr);
                                }
                                cr.w1Record.setHhold(hs.get(CASEW1));
                            });
                    // Add person records.
                    File f;
                    BufferedReader br;
                    f = cFs.get(cID);
                    br = Generic_IO.getBufferedReader(f);
                    br.lines()
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
                    // Close br
                    Generic_IO.closeBufferedReader(br);
                    // Cache and clear collection
                    data.cacheSubsetCollection(cID, c);
                    data.clearCollection(cID);
                });
        return r;
    }

    /**
     * Merge Person and Household Data for Wave 2.
     *
     * @param data
     * @param personHandler
     * @param indir
     * @param outdir
     * @param hholdHandler
     * @param nOC
     * @param CASEW1ToCID
     *
     * @param CIDToCASEW1 @param CASEW1ToCASEW2
     * @param CASEW2ToCASEW1
     */
    public static void doDataProcessingStep2Wave2(WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler, File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int nOC,
            HashMap<Short, Short> CASEW1ToCID,
            TreeMap<Short, HashSet<Short>> CIDToCASEW1,
            TreeMap<Short, HashSet<Short>> CASEW1ToCASEW2,
            TreeMap<Short, Short> CASEW2ToCASEW1) {
        log("Wave 2");
        TreeMap<Short, WIGB_WaAS_Wave2_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave2();
        TreeMap<Short, File> cFs;
        cFs = personHandler.loadSubsetWave2(nOC, CASEW1ToCID, WIGB_WaAS_Data.W2,
                outdir, CASEW2ToCASEW1);
        cFs.keySet().stream()
                .forEach(cID -> {
                    log1("CollectionID " + cID);
                    WIGB_WaAS_Collection c;
                    c = data.getCollection(cID);
                    // Add hhold records.
                    HashSet<Short> s;
                    s = CIDToCASEW1.get(cID);
                    s.stream()
                            .forEach(CASEW1 -> {
                                data.lookup.put(CASEW1, cID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    log("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error?");
                                } else {
                                    cr.w2Record.setHhold(hs.get(CASEW1));
                                }
                            });
                    // Add person records.
                    File f;
                    BufferedReader br;
                    f = cFs.get(cID);
                    br = Generic_IO.getBufferedReader(f);
                    br.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave2_PERSON_Record p;
                                p = new WIGB_WaAS_Wave2_PERSON_Record(line);
                                short CASEW1Check;
                                CASEW1Check = p.getCASEW1();
                                short CASEW2;
                                CASEW2 = p.getCASEW2();
                                short CASEW1;
                                CASEW1 = CASEW2ToCASEW1.get(CASEW2);
                                printCheck(WIGB_WaAS_Data.W2, CASEW1Check, CASEW1, CASEW1ToCASEW2);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    log("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error, "
                                            + "or this person may have "
                                            + "moved from one hhold "
                                            + "to another?");
                                } else {
                                    cr.w2Record.getPeople().add(p);
                                }
                            });
                    // Close br
                    Generic_IO.closeBufferedReader(br);
                    // Cache and clear collection
                    data.cacheSubsetCollection(cID, c);
                    data.clearCollection(cID);
                });
    }

    protected static void printCheck(byte wave, short CASEWXCheck,
            short CASEWX, TreeMap<Short, HashSet<Short>> lookup) {
        if (CASEWXCheck != CASEWX) {
            log("Person in Wave " + wave + " record given by "
                    + "CASEW" + wave + " " + CASEWX + " has a "
                    + "CASEW" + (wave - 1) + " as " + CASEWXCheck + ", "
                    + "but in the CASEW" + wave + "ToCASEW" + (wave - 1) + " "
                    + "lookup this is " + CASEWX);
            if (lookup.get(CASEWXCheck) == null) {
                log("CASEW" + (wave - 1) + "ToCASEW" + wave + ".get(CASEW"
                        + (wave - 1) + "Check) == null");
            } else {
                log("CASEW" + (wave - 1) + "ToCASEW" + wave + ".get(CASEW"
                        + (wave - 1) + "Check).size() "
                        + lookup.get(CASEWXCheck).size());
            }
        }
    }

    /**
     * Merge Person and Household Data for Wave 3.
     *
     * @param data
     * @param personHandler
     * @param indir
     * @param outdir
     * @param hholdHandler
     * @param nOC
     * @param CASEW1ToCID
     * @param CIDToCASEW1
     * @param CASEW1ToCASEW2
     * @param CASEW2ToCASEW1
     * @param CASEW2ToCASEW3
     * @param CASEW3ToCASEW2
     */
    public static void doDataProcessingStep2Wave3(WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler, File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int nOC,
            HashMap<Short, Short> CASEW1ToCID,
            TreeMap<Short, HashSet<Short>> CIDToCASEW1,
            TreeMap<Short, HashSet<Short>> CASEW1ToCASEW2,
            TreeMap<Short, Short> CASEW2ToCASEW1,
            TreeMap<Short, HashSet<Short>> CASEW2ToCASEW3,
            TreeMap<Short, Short> CASEW3ToCASEW2) {
        log("Wave 3");
        TreeMap<Short, WIGB_WaAS_Wave3_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave3();
        TreeMap<Short, File> cFs;
        cFs = personHandler.loadSubsetWave3(nOC, CASEW1ToCID, WIGB_WaAS_Data.W3,
                outdir, CASEW2ToCASEW1, CASEW3ToCASEW2);
        cFs.keySet().stream()
                .forEach(cID -> {
                    log("collectionID " + cID);
                    WIGB_WaAS_Collection c;
                    c = data.getCollection(cID);
                    // Add hhold records.
                    HashSet<Short> s;
                    s = CIDToCASEW1.get(cID);
                    s.stream()
                            .forEach(CASEW1 -> {
                                data.lookup.put(CASEW1, cID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    log("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error?");
                                } else {
                                    cr.w3Record.setHhold(hs.get(CASEW1));
                                }
                            });
                    // Add person records.
                    File f;
                    BufferedReader br;
                    f = cFs.get(cID);
                    br = Generic_IO.getBufferedReader(f);
                    br.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave3_PERSON_Record p;
                                p = new WIGB_WaAS_Wave3_PERSON_Record(line);
                                short CASEW1Check;
                                CASEW1Check = p.getCASEW1();
                                short CASEW2Check;
                                CASEW2Check = p.getCASEW2();
                                short CASEW3;
                                CASEW3 = p.getCASEW3();
                                short CASEW2;
                                CASEW2 = CASEW3ToCASEW2.get(CASEW3);
                                short CASEW1;
                                CASEW1 = CASEW2ToCASEW1.get(CASEW2);
                                printCheck(WIGB_WaAS_Data.W2, CASEW1Check, CASEW1, CASEW1ToCASEW2);
                                printCheck(WIGB_WaAS_Data.W3, CASEW2Check, CASEW2, CASEW2ToCASEW3);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    log("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error, "
                                            + "or this person may have "
                                            + "moved from one hhold "
                                            + "to another?");
                                } else {
                                    cr.w3Record.getPeople().add(p);
                                }
                            });
                    // Close br
                    Generic_IO.closeBufferedReader(br);
                    // Cache and clear collection
                    data.cacheSubsetCollection(cID, c);
                    data.clearCollection(cID);
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
     * @param nOC
     * @param CASEW1ToCID
     * @param CIDToCASEW1
     * @param CASEW1ToCASEW2
     * @param CASEW2ToCASEW1
     * @param CASEW2ToCASEW3
     * @param CASEW3ToCASEW2
     * @param CASEW3ToCASEW4
     * @param CASEW4ToCASEW3
     */
    public static void doDataProcessingStep2Wave4(WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler, File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int nOC,
            HashMap<Short, Short> CASEW1ToCID,
            TreeMap<Short, HashSet<Short>> CIDToCASEW1,
            TreeMap<Short, HashSet<Short>> CASEW1ToCASEW2,
            TreeMap<Short, Short> CASEW2ToCASEW1,
            TreeMap<Short, HashSet<Short>> CASEW2ToCASEW3,
            TreeMap<Short, Short> CASEW3ToCASEW2,
            TreeMap<Short, HashSet<Short>> CASEW3ToCASEW4,
            TreeMap<Short, Short> CASEW4ToCASEW3) {
        // Wave 4
        log("Wave 4");
        TreeMap<Short, WIGB_WaAS_Wave4_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave4();
        TreeMap<Short, File> cFs;
        cFs = personHandler.loadSubsetWave4(nOC, CASEW1ToCID, WIGB_WaAS_Data.W4,
                outdir, CASEW2ToCASEW1, CASEW3ToCASEW2, CASEW4ToCASEW3);
        cFs.keySet().stream()
                .forEach(cID -> {
                    log("collectionID " + cID);
                    WIGB_WaAS_Collection c;
                    c = data.getCollection(cID);
                    // Add hhold records.
                    HashSet<Short> s;
                    s = CIDToCASEW1.get(cID);
                    s.stream()
                            .forEach(CASEW1 -> {
                                data.lookup.put(CASEW1, cID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    log("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error?");
                                } else {
                                    cr.w4Record.setHhold(hs.get(CASEW1));
                                }
                            });
                    // Add person records.
                    File f;
                    BufferedReader br;
                    f = cFs.get(cID);
                    br = Generic_IO.getBufferedReader(f);
                    br.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave4_PERSON_Record p;
                                p = new WIGB_WaAS_Wave4_PERSON_Record(line);
                                short CASEW1Check;
                                CASEW1Check = p.getCASEW1();
                                short CASEW2Check;
                                CASEW2Check = p.getCASEW2();
                                short CASEW3Check;
                                CASEW3Check = p.getCASEW3();
                                short CASEW4;
                                CASEW4 = p.getCASEW4();
                                short CASEW3;
                                CASEW3 = CASEW4ToCASEW3.get(CASEW4);
                                short CASEW2;
                                CASEW2 = CASEW3ToCASEW2.get(CASEW3);
                                short CASEW1;
                                CASEW1 = CASEW2ToCASEW1.get(CASEW2);
                                printCheck(WIGB_WaAS_Data.W2, CASEW1Check, CASEW1, CASEW1ToCASEW2);
                                printCheck(WIGB_WaAS_Data.W3, CASEW2Check, CASEW2, CASEW2ToCASEW3);
                                printCheck(WIGB_WaAS_Data.W4, CASEW3Check, CASEW3, CASEW3ToCASEW4);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    log("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error, "
                                            + "or this person may have "
                                            + "moved from one hhold "
                                            + "to another?");
                                } else {
                                    cr.w4Record.getPeople().add(p);
                                }
                            });
                    // Close br
                    Generic_IO.closeBufferedReader(br);
                    // Cache and clear collection
                    data.cacheSubsetCollection(cID, c);
                    data.clearCollection(cID);
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
     * @param nOC
     * @param CASEW1ToCID
     * @param CIDToCASEW1
     * @param CASEW1ToCASEW2
     * @param CASEW2ToCASEW1
     * @param CASEW2ToCASEW3
     * @param CASEW3ToCASEW2
     * @param CASEW3ToCASEW4
     * @param CASEW4ToCASEW3
     * @param CASEW4ToCASEW5
     * @param CASEW5ToCASEW4
     */
    public static void doDataProcessingStep2Wave5(WIGB_WaAS_Data data,
            WIGB_WaAS_PERSON_Handler personHandler, File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler, int nOC,
            HashMap<Short, Short> CASEW1ToCID,
            TreeMap<Short, HashSet<Short>> CIDToCASEW1,
            TreeMap<Short, HashSet<Short>> CASEW1ToCASEW2,
            TreeMap<Short, Short> CASEW2ToCASEW1,
            TreeMap<Short, HashSet<Short>> CASEW2ToCASEW3,
            TreeMap<Short, Short> CASEW3ToCASEW2,
            TreeMap<Short, HashSet<Short>> CASEW3ToCASEW4,
            TreeMap<Short, Short> CASEW4ToCASEW3,
            TreeMap<Short, HashSet<Short>> CASEW4ToCASEW5,
            TreeMap<Short, Short> CASEW5ToCASEW4) {
        TreeMap<Short, WIGB_WaAS_Wave5_HHOLD_Record> hs;
        hs = hholdHandler.loadCacheSubsetWave5();
        TreeMap<Short, File> cFs;
        cFs = personHandler.loadSubsetWave5(nOC, CASEW1ToCID, WIGB_WaAS_Data.W5,
                outdir, CASEW2ToCASEW1, CASEW3ToCASEW2, CASEW4ToCASEW3,
                CASEW5ToCASEW4);
        cFs.keySet().stream()
                .forEach(cID -> {
                    WIGB_WaAS_Collection c;
                    c = data.getCollection(cID);
                    // Add hhold records.
                    HashSet<Short> s;
                    s = CIDToCASEW1.get(cID);
                    s.stream()
                            .forEach(CASEW1 -> {
                                data.lookup.put(CASEW1, cID);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    log("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error?");
                                } else {
                                    cr.w5Record.setHhold(hs.get(CASEW1));
                                }
                            });
                    // Add person records.
                    File f;
                    BufferedReader br;
                    f = cFs.get(cID);
                    br = Generic_IO.getBufferedReader(f);
                    br.lines()
                            .skip(1) // Skip header.
                            .forEach(line -> {
                                WIGB_WaAS_Wave5_PERSON_Record p;
                                p = new WIGB_WaAS_Wave5_PERSON_Record(line);
                                short CASEW1Check;
                                CASEW1Check = p.getCASEW1();
                                short CASEW2Check;
                                CASEW2Check = p.getCASEW2();
                                short CASEW3Check;
                                CASEW3Check = p.getCASEW3();
                                short CASEW4Check;
                                CASEW4Check = p.getCASEW4();
                                short CASEW5;
                                CASEW5 = p.getCASEW5();
                                short CASEW4;
                                CASEW4 = CASEW5ToCASEW4.get(CASEW5);
                                short CASEW3;
                                CASEW3 = CASEW4ToCASEW3.get(CASEW4);
                                short CASEW2;
                                CASEW2 = CASEW3ToCASEW2.get(CASEW3);
                                short CASEW1;
                                CASEW1 = CASEW2ToCASEW1.get(CASEW2);
                                printCheck(WIGB_WaAS_Data.W2, CASEW1Check, CASEW1, CASEW1ToCASEW2);
                                printCheck(WIGB_WaAS_Data.W3, CASEW2Check, CASEW2, CASEW2ToCASEW3);
                                printCheck(WIGB_WaAS_Data.W4, CASEW3Check, CASEW3, CASEW3ToCASEW4);
                                printCheck(WIGB_WaAS_Data.W5, CASEW4Check, CASEW4, CASEW4ToCASEW5);
                                HashMap<Short, WIGB_WaAS_Combined_Record> m;
                                m = c.getData();
                                WIGB_WaAS_Combined_Record cr;
                                cr = m.get(CASEW1);
                                if (cr == null) {
                                    log("No combined record "
                                            + "for CASEW1 " + CASEW1 + "! "
                                            + "This may be a data error, "
                                            + "or this person may have "
                                            + "moved from one hhold "
                                            + "to another?");
                                } else {
                                    cr.w5Record.getPeople().add(p);
                                }
                            });
                    // Close br
                    Generic_IO.closeBufferedReader(br);
                    // Cache and clear collection
                    data.cacheSubsetCollection(cID, c);
                    data.clearCollection(cID);
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

    protected void initlog(int i) {
        logF = new File(Files.getOutputDataDir(Strings), "log" + i + ".txt");
        logPW = Generic_IO.getPrintWriter(logF, true); // Append to log file.
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
    public void doDataProcessingStep1New(File indir, File outdir,
            WIGB_WaAS_HHOLD_Handler hholdHandler) {
        initlog(1);
        // For convenience/code brevity.
        byte NWAVES;
        NWAVES = WIGB_WaAS_Data.NWAVES;
        /**
         * Step 1: Load hhold data into cache and memory.
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
        HashMap<Byte, TreeSet<Short>[]> iDLists;
        iDLists = new HashMap<>();
        TreeSet<Short>[] iDList;
        // W1
        Object[] hholdDataW1;
        hholdDataW1 = (Object[]) hholdData[0];
        iDLists.put(WIGB_WaAS_Data.W1, (TreeSet<Short>[]) hholdDataW1[1]);
        // W2
        Object[] hholdDataW2;
        hholdDataW2 = (Object[]) hholdData[1];
        iDLists.put(WIGB_WaAS_Data.W2, (TreeSet<Short>[]) hholdDataW2[1]);
        // W3
        Object[] hholdDataW3;
        hholdDataW3 = (Object[]) hholdData[2];
        iDLists.put(WIGB_WaAS_Data.W3, (TreeSet<Short>[]) hholdDataW3[1]);
        // W4
        Object[] hholdDataW4;
        hholdDataW4 = (Object[]) hholdData[3];
        iDLists.put(WIGB_WaAS_Data.W4, (TreeSet<Short>[]) hholdDataW4[1]);
        // W5
        Object[] hholdDataW5;
        hholdDataW5 = (Object[]) hholdData[4];
        iDLists.put(WIGB_WaAS_Data.W5, (TreeSet<Short>[]) hholdDataW5[1]);
        /**
         * Step 3: Print out the Number of Households in each wave.
         *
         * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
         * CASEW5 and values as WIGB_WaAS_Wave5_HHOLD_Records. r[1] is an array
         * of TreeSets where: r[1][0] is a list of CASEW1 values, r[1][1] is a
         * list of CASEW2 values, r[1][2] is a list of CASEW3 values, r[1][3] is
         * a list of CASEW4 values.
         */
        for (byte wave = NWAVES; wave > 0; wave--) {
            iDList = iDLists.get(wave);
            for (int i = wave; i > 0; i--) {
                String m;
                if (i == wave) {
                    if (wave > 2) {
                        m = "" + iDList[i].size()
                                + "\tNumber of HHOLD IDs in Wave " + wave
                                + " reported as being in ";
                        for (int j = wave - 1; j > 0; j--) {
                            m += "Wave " + j + ", ";
                        }
                        log(m);
                    }
                } else {
                    m = "" + iDList[i].size()
                            + "\tNumber of HHOLD IDs in Wave " + wave
                            + " reported as being in Wave " + i;
                    log(m);
                }
            }
        }
        logPW.close();
    }

    public static void log0(String s) {
        logPW.println(s);
    }

    public static void log1(String s) {
        System.out.println(s);
    }

    public static void log(String s) {
        logPW.println(s);
        System.out.println(s);
    }

    boolean doJavaCodeGeneration = false;
    boolean doLoadDataIntoCaches = false;

}

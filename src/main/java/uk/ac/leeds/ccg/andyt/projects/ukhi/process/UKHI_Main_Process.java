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
package uk.ac.leeds.ccg.andyt.projects.ukhi.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_Environment;
import uk.ac.leeds.ccg.andyt.generic.data.waas.core.WaAS_Environment;
import uk.ac.leeds.ccg.andyt.generic.data.waas.core.WaAS_Strings;
import uk.ac.leeds.ccg.andyt.projects.ukhi.core.UKHI_Environment;
import uk.ac.leeds.ccg.andyt.projects.ukhi.core.UKHI_Object;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Collection;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.records.WaAS_CombinedRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_GORSubsetsAndLookups;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.handlers.WaAS_HHOLD_Handler;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.handlers.WaAS_Handler;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.id.WaAS_ID;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.id.WaAS_W1ID;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.id.WaAS_W2ID;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.id.WaAS_W3ID;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.records.WaAS_W3Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.id.WaAS_W4ID;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.records.WaAS_W4Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_W5Data;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.id.WaAS_W5ID;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.records.WaAS_W5Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_W1HRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_W2HRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_W3HRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_W4HRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_W5HRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_W1PRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_W2PRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_W3PRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_W4PRecord;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_W5PRecord;
import uk.ac.leeds.ccg.andyt.generic.execution.Generic_Execution;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_Defaults;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_Files;
import uk.ac.leeds.ccg.andyt.generic.util.Generic_Collections;
import uk.ac.leeds.ccg.andyt.generic.visualisation.Generic_Visualisation;
import uk.ac.leeds.ccg.andyt.projects.ukhi.chart.UKHI_LineGraph;
import uk.ac.leeds.ccg.andyt.projects.ukhi.core.UKHI_Strings;
import uk.ac.leeds.ccg.andyt.projects.ukhi.io.UKHI_Files;

/**
 *
 * @author geoagdt
 */
public class UKHI_Main_Process extends UKHI_Object {

    // For convenience
    protected final UKHI_Files files;
    protected final WaAS_Environment we;

    protected final Generic_Visualisation vis;
    protected final Generic_Execution exec;

    /**
     * Subset of CASEW1 for all records that have the same household
     * composition.
     */
    HashSet<WaAS_W1ID> subset;

    ArrayList<Byte> gors;
    /**
     * Government Office Region (GOR) name lookup looks up the name from the
     * numerical code.
     */
    TreeMap<Byte, String> GORNameLookup;
    WaAS_GORSubsetsAndLookups GORSubsetsAndLookups;

    public UKHI_Main_Process(UKHI_Environment env) {
        super(env);
        files = env.files;
        we = env.we;
        vis = new Generic_Visualisation(env.env);
        exec = new Generic_Execution(env.env);
    }

    public UKHI_Main_Process(UKHI_Main_Process p) {
        files = p.files;
        env = p.env;
        subset = p.subset;
        gors = p.gors;
        GORNameLookup = p.GORNameLookup;
        GORSubsetsAndLookups = p.GORSubsetsAndLookups;
        we = env.we;
        vis = new Generic_Visualisation(env.env);
        exec = new Generic_Execution(env.env);
    }

    public static void main(String[] args) {
        try {
            Generic_Environment ge = new Generic_Environment();
            WaAS_Strings ws = new WaAS_Strings();
            File wasDataDir = new File(
                    ge.files.getDir().getParentFile().getParentFile().getParentFile(),
                    ws.s_generic);
            wasDataDir = new File(wasDataDir, UKHI_Strings.s_data);
            wasDataDir = new File(wasDataDir, ws.PROJECT_NAME);
            wasDataDir = new File(wasDataDir, UKHI_Strings.s_data);
            UKHI_Environment env = new UKHI_Environment(ge, wasDataDir);
            UKHI_Main_Process p = new UKHI_Main_Process(env);
            p.files.setDir(Generic_Defaults.getDefaultDir());
            // Main switches
            //p.doJavaCodeGeneration = true;
            p.doLoadDataIntoCaches = true; // rename/reuse just left here for convenience...
            p.run();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * The aim is to measure: 1) Costs associated with tenure (expenditure on
     * rent, net of housing benefit, and/or mortgage interest for primary
     * residences); 2) Net gains associated with property wealth (capital gains
     * and rental income, net of capital losses, property maintenance costs,
     * mortgage interest costs associated with investment properties and costs
     * associated with meeting landlord obligations).
     *
     * How does the balance between costs and benefits vary for households in
     * different tenures and property wealth percentiles, as well as different
     * regions, income percentiles and age cohorts? Which groups are the biggest
     * gainers and losers over the period 2006-2016? To what extent are the
     * costs and benefits affecting households consistent over time? In other
     * words, how much mobility do households experience between the categories
     * of ‘winners’ and ‘losers’ over the course of those ten years?
     */
    public void run() throws FileNotFoundException {
        String m = this.getClass().getName() + ".run()";
        env.logStartTag(m);
        subset = env.we.hh.getSubset(4);
        /**
         * Init gors, GORNameLookup, GORSubsets and GORLookups.
         */
        if (true) {
            gors = WaAS_Handler.getGORs();
            GORNameLookup = WaAS_Handler.getGORNameLookup();
            GORSubsetsAndLookups = env.we.hh.getGORSubsetsAndLookups("", gors, subset);
        }

        int[] ttotals = new int[we.NWAVES];
        env.log("GOR Subsets");
        env.log("NW1,NW2,NW3,NW4,NW5,GORNumber,GORName");
        Iterator<Byte> ite = gors.iterator();
        while (ite.hasNext()) {
            String s = "";
            int[] totals = new int[we.NWAVES];
            byte gor = ite.next();
            int size;
            // W1
            size = GORSubsetsAndLookups.gor_To_w1.get(gor).size();
            totals[0] += size;
            s += totals[0] + ",";
            ttotals[0] += totals[0];
            // W2
            size = GORSubsetsAndLookups.gor_To_w2.get(gor).size();
            totals[0] += size;
            s += totals[0] + ",";
            ttotals[0] += totals[0];
            // W3
            size = GORSubsetsAndLookups.gor_To_w3.get(gor).size();
            totals[0] += size;
            s += totals[0] + ",";
            ttotals[0] += totals[0];
            // W4
            size = GORSubsetsAndLookups.gor_To_w4.get(gor).size();
            totals[0] += size;
            s += totals[0] + ",";
            ttotals[0] += totals[0];
            // W5
            size = GORSubsetsAndLookups.gor_to_w5.get(gor).size();
            totals[0] += size;
            s += totals[0] + ",";
            ttotals[0] += totals[0];

            s += gor + "," + GORNameLookup.get(gor);
            env.log(s);
        }
        // Totals
        String s = "";
        for (byte w = 0; w < we.NWAVES; w++) {
            s += ttotals[w] + ",";
        }
        s += "0,All";
        env.log(s);
        /**
         * TENURE
         */
        UKHI_Process_TENURE tp = new UKHI_Process_TENURE(this);
        tp.createGraph();

        /**
         * HVALUE, HPROPW
         */
        UKHI_Process_Variable p = new UKHI_Process_Variable(this);
        p.createGraph(new BigDecimal("20000"), UKHI_Strings.s_HVALUE);
        p.createGraph(new BigDecimal("20000"), UKHI_Strings.s_HPROPW);

        // Check some counts
        WaAS_HHOLD_Handler hH = new WaAS_HHOLD_Handler(env.we);
        WaAS_W5Data w5 = hH.loadW5InW4();
        TreeMap<WaAS_W5ID, WaAS_W5Record> w5recs = w5.lookup;
        Iterator<WaAS_W5ID> ites = w5recs.keySet().iterator();
        int countMortgage = 0;
        int countNonMortgage = 0;
        int countBuyWithMortgage = 0;
        int countPartBuyWithMortgage = 0;
        int countZeroMIntRate1W5 = 0;
        int countPositiveMIntRate1W5 = 0;
        int countZeroMVal1W5 = 0;
        int countPositiveMVal1W5 = 0;
        int countZeroMNumbNW5 = 0;
        int countPositiveMNumbNW5 = 0;
        int countGT1MNumbNW5 = 0;
        while (ites.hasNext()) {
            WaAS_W5ID w5ID = ites.next();
            WaAS_W5HRecord w5rec = w5recs.get(w5ID).getHr();
            byte ten1 = w5rec.getTEN1();
            if (ten1 == 2 || ten1 == 3) {
                countMortgage++;
                if (ten1 == 2) {
                    countBuyWithMortgage++;
                }
                if (ten1 == 3) {
                    countPartBuyWithMortgage++;
                }
                double MIntRate1W5 = w5rec.getMINTRATE1();
                if (MIntRate1W5 == 0.0d) {
                    countZeroMIntRate1W5++;
                } else {
                    if (MIntRate1W5 > 0) {
                        countPositiveMIntRate1W5++;
                    }
                }
                int MVal1W5 = w5rec.getMVAL1();
                if (MVal1W5 == 0) {
                    countZeroMVal1W5++;
                } else {
                    if (MVal1W5 > 0) {
                        countPositiveMVal1W5++;
                    }
                }
                int MNumbNW5 = w5rec.getMNUMB();
                if (MNumbNW5 == 0) {
                    countZeroMNumbNW5++;
                } else {
                    if (MNumbNW5 > 0) {
                        countPositiveMNumbNW5++;
                        if (MNumbNW5 > 1) {
                            countGT1MNumbNW5++;
                        }
                    }
                }
            } else {
                countNonMortgage++;
            }
        }
        env.log("" + w5recs.size() + "\t countAllW5withW4");
        env.log("" + countMortgage + "\t countMortgage");
        env.log("" + countNonMortgage + "\t countNonMortgage");
        env.log("" + countBuyWithMortgage + "\t countBuyWithMortgage");
        env.log("" + countPartBuyWithMortgage + "\t countPartBuyWithMortgage");
        env.log("" + countZeroMIntRate1W5 + "\t countZeroMIntRate1W5");
        env.log("" + countPositiveMIntRate1W5 + "\t countPositiveMIntRate1W5");
        env.log("" + countZeroMVal1W5 + "\t countZeroMVal1W5");
        env.log("" + countPositiveMVal1W5 + "\t countPositiveMVal1W5");
        env.log("" + countZeroMNumbNW5 + "\t countZeroMNumbNW5");
        env.log("" + countPositiveMNumbNW5 + "\t countPositiveMNumbNW5");
        env.log("" + countGT1MNumbNW5 + "\t countGT1MNumbNW5");

        //TreeMap<Short, HashSet<Short>> CASEW4ToCASEW5;
        //CASEW4ToCASEW5 = (TreeMap<Short, HashSet<Short>>) ((Object[]) w5[4])[3];
        //Get Non-zero and zero counts for:
//        MIntRate1W5 (Interest rate on mortgage 1) 
//        MVal1W5 (Amount still outstanding mortgageloan) 
//        MValB1W5 (Banded amount outstanding on mortgageloan)
//        MNumbNW5 (Number of mortgages)
        // DVTotGIRW5	Variable label = Household Gross Annual (regular) income  - (rounded to 3 significant figures)
        // DVLUKVAL5
        // FINCVB5
        //getWave1Or2HPRICEBLookup();
        //getWave3Or4Or5HPRICEBLookup();
        env.logEndTag(m);
    }

    /**
     * Get the total DVLUKVAL in subset.
     *
     * @param subset
     * @param wave
     * @return
     */
    public long getDVLUKVAL(HashSet<WaAS_W1ID> subset, byte wave) {
//      Value label information for DVLUKVal
//	Value = -9.0	Label = Don t know
//	Value = -8.0	Label = Refused
//	Value = -7.0	Label = Does not apply
//	Value = -6.0	Label = Error Partial
        // For brevity/convenience.
        long tDVLUKVAL;
        if (wave == we.W1) {
            tDVLUKVAL = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(w1ID -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        DVLUKVAL = cr.w1Rec.getHr().getDVLUKVAL();
                        if (DVLUKVAL == Integer.MIN_VALUE) {
                            DVLUKVAL = 0;
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                env.we.data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else if (wave == we.W2) {
            tDVLUKVAL = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(w1ID -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite = cr.w2Recs.keySet().iterator();
                        while (ite.hasNext()) {
                            WaAS_W2ID w2ID = ite.next();
                            int DVLUKVALW2 = cr.w2Recs.get(w2ID).getHr().getDVLUKVAL();
                            if (DVLUKVALW2 != Integer.MIN_VALUE) {
                                DVLUKVAL += DVLUKVALW2;
                            }
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                env.we.data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else if (wave == we.W3) {
            tDVLUKVAL = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(w3ID -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(w3ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w3ID);
                        Iterator<WaAS_W2ID> ite = cr.w3Recs.keySet().iterator();
                        while (ite.hasNext()) {
                            WaAS_W2ID w2ID = ite.next();
                            HashMap<WaAS_W3ID, WaAS_W3Record> w3_2 = cr.w3Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w3_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID2 = ite2.next();
                                int DVLUKVALW3 = w3_2.get(w3ID2).getHr().getDVLUKVAL_SUM();
                                if (DVLUKVALW3 != Integer.MIN_VALUE) {
                                    DVLUKVAL += DVLUKVALW3;
                                }
                            }
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                env.we.data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else if (wave == we.W4) {
            tDVLUKVAL = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(w1ID -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite = cr.w4Recs.keySet().iterator();
                        while (ite.hasNext()) {
                            WaAS_W2ID w2ID = ite.next();
                            HashMap<WaAS_W3ID, HashMap<WaAS_W4ID, WaAS_W4Record>> w4_2;
                            w4_2 = cr.w4Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w4_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID = ite2.next();
                                HashMap<WaAS_W4ID, WaAS_W4Record> w4_3 = w4_2.get(w3ID);
                                Iterator<WaAS_W4ID> ite3 = w4_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    WaAS_W4ID w4ID = ite3.next();
                                    int DVLUKVALW4 = w4_3.get(w4ID).getHr().getDVLUKVAL_SUM();
                                    if (DVLUKVALW4 != Integer.MIN_VALUE) {
                                        DVLUKVAL += DVLUKVALW4;
                                    }
                                }
                            }
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                env.we.data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else if (wave == we.W5) {
            tDVLUKVAL = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(w1ID -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite = cr.w5Recs.keySet().iterator();
                        while (ite.hasNext()) {
                            WaAS_W2ID w2ID = ite.next();
                            HashMap<WaAS_W3ID, HashMap<WaAS_W4ID, HashMap<WaAS_W5ID, WaAS_W5Record>>> w5_2;
                            w5_2 = cr.w5Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w5_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID = ite2.next();
                                HashMap<WaAS_W4ID, HashMap<WaAS_W5ID, WaAS_W5Record>> w5_3;
                                w5_3 = w5_2.get(w3ID);
                                Iterator<WaAS_W4ID> ite3 = w5_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    WaAS_W4ID w4ID = ite3.next();
                                    HashMap<WaAS_W5ID, WaAS_W5Record> w5_4 = w5_3.get(w4ID);
                                    Iterator<WaAS_W5ID> ite4 = w5_4.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        WaAS_W5ID w5ID = ite4.next();
                                        int DVLUKVALW5 = w5_4.get(w5ID).getHr().getDVLUKVAL_SUM();
                                        if (DVLUKVALW5 != Integer.MIN_VALUE) {
                                            DVLUKVAL += DVLUKVALW5;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                env.we.data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else {
            tDVLUKVAL = 0;
        }
        env.log("Total (Hhold aggregate) DVLUKVAL in Wave " + wave + " " + tDVLUKVAL);
        return tDVLUKVAL;
    }

    /**
     * Get FINCVB.
     *
     * @param subset
     * @param wave
     * @return
     */
    public long getFINCVB(HashSet<WaAS_W1ID> subset, byte wave) {
        long tFINCVB;
        if (wave == we.W1) {
            tFINCVB = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = env.we.data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(w1ID -> {
                    byte FINCVB = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        ArrayList<WaAS_W1PRecord> w1p = cr.w1Rec.getPrs();
                        Iterator<WaAS_W1PRecord> ite = w1p.iterator();
                        while (ite.hasNext()) {
                            FINCVB = ite.next().getFINCVB();
                            if (FINCVB == Byte.MIN_VALUE) {
                                FINCVB = 0;
                            }
                        }
                    }
                    return FINCVB;
                }).sum();
                env.we.data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else if (wave == we.W2) {
            tFINCVB = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(w1ID -> {
                    byte FINCVB = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite = cr.w2Recs.keySet().iterator();
                        while (ite.hasNext()) {
                            WaAS_W2ID w2ID = ite.next();
                            ArrayList<WaAS_W2PRecord> w2p = cr.w2Recs.get(w2ID).getPrs();
                            Iterator<WaAS_W2PRecord> ite2 = w2p.iterator();
                            while (ite2.hasNext()) {
                                FINCVB = ite2.next().getFINCVB();
                                if (FINCVB == Byte.MIN_VALUE) {
                                    FINCVB = 0;
                                }
                            }
                        }
                    }
                    return FINCVB;
                }).sum();
                env.we.data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else if (wave == we.W3) {
            tFINCVB = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(w1ID -> {
                    byte FINCVB = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite = cr.w3Recs.keySet().iterator();
                        while (ite.hasNext()) {
                            WaAS_W2ID w2ID = ite.next();
                            HashMap<WaAS_W3ID, WaAS_W3Record> w3_2 = cr.w3Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w3_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID = ite2.next();
                                ArrayList<WaAS_W3PRecord> w3p = w3_2.get(w3ID).getPrs();
                                Iterator<WaAS_W3PRecord> ite3 = w3p.iterator();
                                while (ite3.hasNext()) {
                                    FINCVB = ite3.next().getFINCVB();
                                    if (FINCVB == Byte.MIN_VALUE) {
                                        FINCVB = 0;
                                    }
                                }
                            }
                        }
                    }
                    return FINCVB;
                }).sum();
                env.we.data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else if (wave == we.W4) {
            tFINCVB = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(w1ID -> {
                    byte FINCVB = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite = cr.w4Recs.keySet().iterator();
                        while (ite.hasNext()) {
                            WaAS_W2ID w2ID = ite.next();
                            HashMap<WaAS_W3ID, HashMap<WaAS_W4ID, WaAS_W4Record>> w4_2;
                            w4_2 = cr.w4Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w4_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID = ite2.next();
                                HashMap<WaAS_W4ID, WaAS_W4Record> w4_3 = w4_2.get(w3ID);
                                Iterator<WaAS_W4ID> ite3 = w4_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    WaAS_W4ID w4ID = ite3.next();
                                    ArrayList<WaAS_W4PRecord> w4p;
                                    w4p = w4_3.get(w4ID).getPrs();
                                    Iterator<WaAS_W4PRecord> ite4 = w4p.iterator();
                                    while (ite4.hasNext()) {
                                        FINCVB = ite4.next().getFINCVB();
                                        if (FINCVB == Byte.MIN_VALUE) {
                                            FINCVB = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return FINCVB;
                }).sum();
                env.we.data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else if (wave == we.W5) {
            tFINCVB = env.we.data.collections.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(w1ID -> {
                    byte FINCVB = 0;
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite = cr.w5Recs.keySet().iterator();
                        while (ite.hasNext()) {
                            WaAS_W2ID w2ID = ite.next();
                            HashMap<WaAS_W3ID, HashMap<WaAS_W4ID, HashMap<WaAS_W5ID, WaAS_W5Record>>> w5_2;
                            w5_2 = cr.w5Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w5_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID = ite2.next();
                                HashMap<WaAS_W4ID, HashMap<WaAS_W5ID, WaAS_W5Record>> w5_3;
                                w5_3 = w5_2.get(w3ID);
                                Iterator<WaAS_W4ID> ite3 = w5_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    WaAS_W4ID w4ID = ite3.next();
                                    HashMap<WaAS_W5ID, WaAS_W5Record> w5_4 = w5_3.get(w4ID);
                                    Iterator<WaAS_W5ID> ite4 = w5_4.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        WaAS_W5ID w5ID = ite4.next();
                                        ArrayList<WaAS_W5PRecord> w5p = w5_4.get(w5ID).getPrs();
                                        Iterator<WaAS_W5PRecord> ite5 = w5p.iterator();
                                        while (ite5.hasNext()) {
                                            FINCVB = ite5.next().getFINCVB();
                                            if (FINCVB == Byte.MIN_VALUE) {
                                                FINCVB = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return FINCVB;
                }).sum();
                env.we.data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else {
            tFINCVB = 0;
        }
        env.log("Total (Person aggregate) FINCVB in Wave " + wave + " " + tFINCVB);
        return tFINCVB;
    }

    /**
     * Get the total HPRICEB in subset.
     *
     * @param subset
     * @param gORSubsetsAndLookups
     * @param wave
     * @return Map with keys as GOR and Values as map with keys as CASEWX and
     * values as Houseprices.
     */
    public HashMap<Byte, HashMap<WaAS_ID, Integer>> getHPRICE(
            HashSet<WaAS_W1ID> subset,
            WaAS_GORSubsetsAndLookups gORSubsetsAndLookups,
            byte wave) {
        // Initialise result
        HashMap<Byte, HashMap<WaAS_ID, Integer>> r = new HashMap<>();
        Iterator<Byte> ite = GORSubsetsAndLookups.gor_To_w1.keySet().iterator();
        while (ite.hasNext()) {
            Byte GOR = ite.next();
            r.put(GOR, new HashMap<>());
        }
        // For brevity/convenience.
        if (wave == we.W1) {
            env.we.data.collections.keySet().stream().forEach(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                c.getData().keySet().stream().forEach(w1ID -> {
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        WaAS_W1HRecord w1 = cr.w1Rec.getHr();
                        Byte GOR = gORSubsetsAndLookups.w1_To_gor.get(w1ID);
                        int HPRICE = w1.getHPRICE();
                        if (HPRICE < 0) {
                            byte HPRICEB = w1.getHPRICEB();
                            if (HPRICEB > 0) {
                                HPRICE = Wave1Or2HPRICEBLookup.get(HPRICEB);
                                Generic_Collections.addToMap(r, GOR, w1ID, HPRICE);
                            }
                        } else {
                            Generic_Collections.addToMap(r, GOR, w1ID, HPRICE);
                        }
                    }
                });
                env.we.data.clearCollection(cID);
            });
        } else if (wave == we.W2) {
            env.we.data.collections.keySet().stream().forEach(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                c.getData().keySet().stream().forEach(w1ID -> {
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite2 = cr.w2Recs.keySet().iterator();
                        while (ite2.hasNext()) {
                            WaAS_W2ID w2ID = ite2.next();
                            Byte GOR = gORSubsetsAndLookups.w2_To_gor.get(w2ID);
                            WaAS_W2HRecord w2 = cr.w2Recs.get(w2ID).getHr();
                            int HPRICE = w2.getHPRICE();
                            if (HPRICE < 0) {
                                byte HPRICEB = w2.getHPRICEB();
                                if (HPRICEB > 0) {
                                    HPRICE = Wave1Or2HPRICEBLookup.get(HPRICEB);
                                    Generic_Collections.addToMap(r, GOR, w2ID, HPRICE);
                                }
                            } else {
                                Generic_Collections.addToMap(r, GOR, w2ID, HPRICE);
                            }
                        }
                    }
                });
                env.we.data.clearCollection(cID);
            });
        } else if (wave == we.W3) {
            env.we.data.collections.keySet().stream().forEach(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                c.getData().keySet().stream().forEach(w1ID -> {
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite1 = cr.w3Recs.keySet().iterator();
                        while (ite1.hasNext()) {
                            WaAS_W2ID w2ID = ite1.next();
                            HashMap<WaAS_W3ID, WaAS_W3Record> w3_2;
                            w3_2 = cr.w3Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w3_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID = ite2.next();
                                Byte GOR = gORSubsetsAndLookups.w3_To_gor.get(w3ID);
                                WaAS_W3HRecord w3 = w3_2.get(w3ID).getHr();
                                int HPRICE = w3.getHPRICE();
                                if (HPRICE < 0) {
                                    byte HPRICEB = w3.getHPRICEB();
                                    if (HPRICEB > 0) {
                                        HPRICE = Wave3Or4Or5HPRICEBLookup.get(HPRICEB);
                                        Generic_Collections.addToMap(r, GOR, w3ID, HPRICE);
                                    }
                                } else {
                                    Generic_Collections.addToMap(r, GOR, w3ID, HPRICE);
                                }
                            }
                        }
                    }
                });
                env.we.data.clearCollection(cID);
            });
        } else if (wave == we.W4) {
            env.we.data.collections.keySet().stream().forEach(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                c.getData().keySet().stream().forEach(w1ID -> {
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite1 = cr.w4Recs.keySet().iterator();
                        while (ite1.hasNext()) {
                            WaAS_W2ID w2ID = ite1.next();
                            HashMap<WaAS_W3ID, HashMap<WaAS_W4ID, WaAS_W4Record>> w4_2;
                            w4_2 = cr.w4Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w4_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID = ite2.next();
                                HashMap<WaAS_W4ID, WaAS_W4Record> w4_3 = w4_2.get(w3ID);
                                Iterator<WaAS_W4ID> ite3 = w4_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    WaAS_W4ID w4ID = ite3.next();
                                    Byte GOR = gORSubsetsAndLookups.w4_To_gor.get(w4ID);
                                    WaAS_W4HRecord w4 = w4_3.get(w4ID).getHr();
                                    int HPRICE = w4.getHPRICE();
                                    if (HPRICE < 0) {
                                        byte HPRICEB = w4.getHPRICEB();
                                        if (HPRICEB > 0) {
                                            HPRICE = Wave3Or4Or5HPRICEBLookup.get(HPRICEB);
                                            Generic_Collections.addToMap(r, GOR, w4ID, HPRICE);
                                        }
                                    } else {
                                        Generic_Collections.addToMap(r, GOR, w4ID, HPRICE);
                                    }
                                }
                            }
                        }
                    }
                });
                env.we.data.clearCollection(cID);
            });
        } else if (wave == we.W5) {
            env.we.data.collections.keySet().stream().forEach(cID -> {
                WaAS_Collection c = env.we.data.getCollection(cID);
                c.getData().keySet().stream().forEach(w1ID -> {
                    if (subset.contains(w1ID)) {
                        WaAS_CombinedRecord cr = c.getData().get(w1ID);
                        Iterator<WaAS_W2ID> ite1 = cr.w5Recs.keySet().iterator();
                        while (ite1.hasNext()) {
                            WaAS_W2ID w2ID = ite1.next();
                            HashMap<WaAS_W3ID, HashMap<WaAS_W4ID, HashMap<WaAS_W5ID, WaAS_W5Record>>> w5_2;
                            w5_2 = cr.w5Recs.get(w2ID);
                            Iterator<WaAS_W3ID> ite2 = w5_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                WaAS_W3ID w3ID = ite2.next();
                                HashMap<WaAS_W4ID, HashMap<WaAS_W5ID, WaAS_W5Record>> w5_3;
                                w5_3 = w5_2.get(w3ID);
                                Iterator<WaAS_W4ID> ite3 = w5_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    WaAS_W4ID w4ID = ite3.next();
                                    HashMap<WaAS_W5ID, WaAS_W5Record> w5_4 = w5_3.get(w4ID);
                                    Iterator<WaAS_W5ID> ite4 = w5_4.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        WaAS_W5ID w5ID = ite4.next();
                                        Byte GOR = gORSubsetsAndLookups.w5_To_gor.get(w5ID);
                                        WaAS_W5HRecord w5 = w5_4.get(w5ID).getHr();
                                        int HPRICE = w5.getHPRICE();
                                        if (HPRICE < 0) {
                                            byte HPRICEB = w5.getHPRICEB();
                                            if (HPRICEB > 0) {
                                                HPRICE = Wave3Or4Or5HPRICEBLookup.get(HPRICEB);
                                                Generic_Collections.addToMap(r, GOR, w5ID, HPRICE);
                                            }
                                        } else {
                                            Generic_Collections.addToMap(r, GOR, w5ID, HPRICE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                env.we.data.clearCollection(cID);
            });
        }
        return r;
    }

    HashMap<Byte, Integer> Wave1Or2HPRICEBLookup;

    /**
     * <ul>
     * <li>Value = 1.0	Label = Less than £20,000</li>
     * <li>Value = 2.0	Label = £20,000 to £39,999</li>
     * <li>Value = 3.0	Label = £40,000 to £59,999</li>
     * <li>Value = 4.0	Label = £60,000 to £99,999</li>
     * <li>Value = 5.0	Label = £100,000 to £149,999</li>
     * <li>Value = 6.0	Label = £150,000 to £199,999</li>
     * <li>Value = 7.0	Label = £200,000 to £249,999</li>
     * <li>Value = 8.0	Label = £250,000 to £299,999</li>
     * <li>Value = 9.0	Label = £300,000 to £499,999</li>
     * <li>Value = 10.0	Label = £500,000 or more</li>
     * </ul>
     *
     * @return
     */
    public HashMap<Byte, Integer> getWave1Or2HPRICEBLookup() {
        if (Wave1Or2HPRICEBLookup == null) {
            Wave1Or2HPRICEBLookup = new HashMap<>();
            Wave1Or2HPRICEBLookup.put((byte) 1, 10000);
            Wave1Or2HPRICEBLookup.put((byte) 2, 30000);
            Wave1Or2HPRICEBLookup.put((byte) 3, 55000);
            Wave1Or2HPRICEBLookup.put((byte) 4, 80000);
            Wave1Or2HPRICEBLookup.put((byte) 5, 125000);
            Wave1Or2HPRICEBLookup.put((byte) 6, 175000);
            Wave1Or2HPRICEBLookup.put((byte) 7, 225000);
            Wave1Or2HPRICEBLookup.put((byte) 8, 275000);
            Wave1Or2HPRICEBLookup.put((byte) 9, 400000);
            Wave1Or2HPRICEBLookup.put((byte) 10, 600000);
        }
        return Wave1Or2HPRICEBLookup;
    }

    HashMap<Byte, Integer> Wave3Or4Or5HPRICEBLookup;

    /**
     * <ul>
     * <li>Value = 1.0	Label = Less than £60,000</li>
     * <li>Value = 2.0	Label = £60,000 to £99,999</li>
     * <li>Value = 3.0	Label = £100,000 to £149,999</li>
     * <li>Value = 4.0	Label = £150,000 to £199,999</li>
     * <li>Value = 5.0	Label = £200,000 to £249,999</li>
     * <li>Value = 6.0	Label = £250,000 to £299,999</li>
     * <li>Value = 7.0	Label = £300,000 to £349,999</li>
     * <li>Value = 8.0	Label = £350,000 to £399,999</li>
     * <li>Value = 9.0	Label = £400,000 to £499,999</li>
     * <li>Value = 10.0	Label = £500,000 to £749,999</li>
     * <li>Value = 11.0	Label = £750,000 to £999,999</li>
     * <li>Value = 12.0	Label = £1 million or more</li>
     * <li>Value = -9.0	Label = Does not know</li>
     * <li>Value = -8.0	Label = No answer</li>
     * <li>Value = -7.0	Label = Does not apply</li>
     * <li>Value = -6.0	Label = Error/Partial</li>
     * </ul>
     *
     * @return
     */
    public HashMap<Byte, Integer> getWave3Or4Or5HPRICEBLookup() {
        if (Wave3Or4Or5HPRICEBLookup == null) {
            Wave3Or4Or5HPRICEBLookup = new HashMap<>();
            Wave3Or4Or5HPRICEBLookup.put((byte) 1, 30000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 2, 80000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 3, 125000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 4, 175000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 5, 225000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 6, 275000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 7, 325000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 8, 375000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 9, 450000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 10, 625000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 11, 875000);
            Wave3Or4Or5HPRICEBLookup.put((byte) 12, 1500000);
        }
        return Wave3Or4Or5HPRICEBLookup;
    }

    boolean doJavaCodeGeneration = false;
    boolean doLoadDataIntoCaches = false;

    /**
     *
     * @param title
     * @param xAxisLabel
     * @param yAxisLabel
     * @param variableName
     * @param changeSubset
     * @param changeAll
     * @param numberOfYAxisTicks
     * @param yIncrement
     */
    public void createLineGraph(String title, String xAxisLabel,
            String yAxisLabel, String variableName,
            TreeMap<Byte, Double> changeSubset,
            TreeMap<Byte, Double> changeAll, int numberOfYAxisTicks,
            BigDecimal yIncrement) {
        vis.getHeadlessEnvironment();
        /*
         * Initialise title and File to write image to
         */
        File file;
        String format = "PNG";
        System.out.println("Title: " + title);
        Generic_Files gf = env.env.files;
        File outdir = gf.getOutputDir();
        String filename;
        filename = title.replace(" ", "_");
        file = new File(outdir, filename + "." + format);
        System.out.println("File: " + file.toString());
        int dataWidth = 500;
        int dataHeight = 250;
        //int numberOfYAxisTicks = 10;
        BigDecimal yMax;
        yMax = null;
        ArrayList<BigDecimal> yPin;
        yPin = new ArrayList<>();
        yPin.add(BigDecimal.ZERO);
        //BigDecimal yIncrement = BigDecimal.ONE;
        //BigDecimal yIncrement = null; // Setting this to null means that numberOfYAxisTicks is used.
        //BigDecimal yIncrement = new BigDecimal("20000");
        //int yAxisStartOfEndInterval = 60;
        /**
         * decimalPlacePrecisionForCalculations
         */
        int dpc = 10;
        /**
         * decimalPlacePrecisionForDisplay
         */
        int dpd = 3;
        boolean drawYZero = false;
        RoundingMode rm = RoundingMode.HALF_UP;
        ExecutorService es = Executors.newSingleThreadExecutor();
        UKHI_LineGraph chart = new UKHI_LineGraph(env.env, es, file, format,
                title, dataWidth, dataHeight, xAxisLabel, yAxisLabel, yMax,
                yPin, yIncrement, numberOfYAxisTicks, drawYZero, dpc, dpd, rm);
        chart.setData(variableName, gors, GORNameLookup, changeSubset, changeAll);
        chart.run();

        Future future = chart.future;
        exec.shutdownExecutorService(es, future, chart);
    }
}

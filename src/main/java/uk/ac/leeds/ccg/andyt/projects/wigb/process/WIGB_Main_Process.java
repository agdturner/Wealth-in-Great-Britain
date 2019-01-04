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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.TreeMap;
import uk.ac.leeds.ccg.andyt.generic.data.waas.core.WaAS_Strings;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Collection;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Combined_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Data;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Wave2_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Wave3_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Wave4_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Wave5_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave2_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave3_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave4_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_Wave1Or2Or3Or4Or5_PERSON_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_Wave1_PERSON_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_Wave2_PERSON_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_Wave3_PERSON_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_Wave4_PERSON_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.person.WaAS_Wave5_PERSON_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.io.WaAS_Files;
import uk.ac.leeds.ccg.andyt.generic.util.Generic_Collections;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;

/**
 *
 * @author geoagdt
 */
public class WIGB_Main_Process extends WIGB_Object {

    // For convenience
    protected final WaAS_Data data;
    protected final WIGB_Strings Strings;
    protected final WIGB_Files Files;
    protected final WaAS_Strings wStrings;
    protected final WaAS_Files wFiles;

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
        wStrings = env.wStrings;
        wFiles = env.wFiles;
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
     * of ‘winners’ and ‘losers’ over the course of those ten years? *
     */
    public void run() {
        logF0 = new File(Files.getOutputDataDir(Strings), "log0.txt");
        logPW0 = Generic_IO.getPrintWriter(logF0, false); // Overwrite log file.
        initlog(4);

        File indir;
        File outdir;
        File generateddir;

        indir = Files.getWaASInputDir();
        generateddir = Files.getGeneratedWaASDir();
        outdir = new File(generateddir, "Subsets");
        outdir.mkdirs();

        HashSet<Short> subset;
        File subsetF;
        subsetF = new File(outdir, "SameCompositionHashSet_CASEW1.dat");
        if (subsetF.exists()) {
            subset = (HashSet<Short>) Generic_IO.readObject(subsetF);
        } else {
            subset = doDataProcessingStep3(outdir);
            Generic_IO.writeObject(subset, subsetF);
        }
        log("Total number of initial households in wave 1 " + subset.size());

        byte W1 = WaAS_Data.W1;
        byte W2 = WaAS_Data.W2;
        byte W3 = WaAS_Data.W3;
        byte W4 = WaAS_Data.W4;
        byte W5 = WaAS_Data.W5;

        // Value label information for GOR
//	Value = 1.0	Label = North East
//	Value = 2.0	Label = North West
//	Value = 3.0	Label = The Wirral
//	Value = 4.0	Label = Yorkshire & Humber
//	Value = 5.0	Label = East Midlands
//	Value = 6.0	Label = West Midlands
//	Value = 7.0	Label = East of England
//	Value = 8.0	Label = London
//	Value = 9.0	Label = South East
//	Value = 10.0	Label = South West
//	Value = 11.0	Label = Wales
//	Value = 12.0	Label = Scotland
//	Value = -9.0	Label = Don't know
//	Value = -8.0	Label = Refused
//	Value = -7.0	Label = Does not apply
//	Value = -6.0	Label = Error/partial
        Object[] GORSubsetsAndLookups;
        File GORSubsetsAndLookupF;
        GORSubsetsAndLookupF = new File(outdir, "GORSubsetsAndLookups.dat");
        HashMap<Byte, HashSet<Short>>[] GORSubsets;
        HashMap<Short, Byte>[] GORLookups;
        if (GORSubsetsAndLookupF.exists()) {
            GORSubsetsAndLookups = (Object[]) Generic_IO.readObject(GORSubsetsAndLookupF);
        } else {
            GORSubsetsAndLookups = getGORSubsetsAndLookup(subset);
        }
        GORSubsets = (HashMap<Byte, HashSet<Short>>[]) GORSubsetsAndLookups[0];
        GORLookups = (HashMap<Short, Byte>[]) GORSubsetsAndLookups[1];
        int[] totals = new int[WaAS_Data.NWAVES];
        for (byte w = 0; w < WaAS_Data.NWAVES; w++) {
            log("Wave " + (w + 1));
            totals[w] = 0;
            for (byte b = 1; b < 13; b++) {
                HashSet<Short> GORSubset;
                GORSubset = GORSubsets[w].get(b);
                totals[w] += GORSubset.size();
                log("GOR " + b + " size " + GORSubset.size());
            }
            Generic_IO.writeObject(GORSubsets, GORSubsetsAndLookupF);
            log("Total all GORs " + totals[w]);
        }

        // HPROPW Total Household Property Wealth.
        double HPROPW1 = getHPROPW(subset, W1);
        double HPROPW2 = getHPROPW(subset, W2);
        double HPROPW3 = getHPROPW(subset, W3);
        double HPROPW4 = getHPROPW(subset, W4);
        double HPROPW5 = getHPROPW(subset, W5);
//        Value label information for HPROPWW3
//	Value = -9.0	Label = Does not know
//	Value = -8.0	Label = Refusal
//	Value = -7.0	Label = Not applicable
//	Value = -6.0	Label = Error / partial

        // DVTotGIRW5	Variable label = Household Gross Annual (regular) income  - (rounded to 3 significant figures)
        //getTenures(subset, W1);        
//      Value label information for Ten1W1W2W3W4W5
//	Value = 1.0	Label = Own it outright
//	Value = 2.0	Label = mortgage
//	Value = 3.0	Label = part rent-part mortgage
//	Value = 4.0	Label = Rent it
//	Value = 5.0	Label = Rent-free
//	Value = 6.0	Label = Squatting
//	Value = -9.0	Label = Don t know
//	Value = -8.0	Label = Refused
//	Value = -7.0	Label = Does not apply
//	Value = -6.0	Label = Error partial
        long DVLUKVAL1 = getDVLUKVAL(subset, W1);
        long DVLUKVAL2 = getDVLUKVAL(subset, W2);
        long DVLUKVAL3 = getDVLUKVAL(subset, W3);
        long DVLUKVAL4 = getDVLUKVAL(subset, W4);
        long DVLUKVAL5 = getDVLUKVAL(subset, W5);

        long FINCVB1 = getFINCVB(subset, W1);
        long FINCVB2 = getFINCVB(subset, W2);
        long FINCVB3 = getFINCVB(subset, W3);
        long FINCVB4 = getFINCVB(subset, W4);
        long FINCVB5 = getFINCVB(subset, W5);

        getWave1Or2HPRICEBLookup();
        getWave3Or4Or5HPRICEBLookup();

        // Map with keys as GOR and Values as map with keys as CASEWX and 
        // values as Houseprices.  
        HashMap<Byte, HashMap<Short, Integer>> HPRICEW1 = getHPRICE(subset, GORSubsets, GORLookups, W1);
        log("HPRICEW1 SummaryStatistics");
        logSummaryStatistics(HPRICEW1.get(W1));
        HashMap<Byte, HashMap<Short, Integer>> HPRICEW2 = getHPRICE(subset, GORSubsets, GORLookups, W2);
        log("HPRICEW2 SummaryStatistics");
        logSummaryStatistics(HPRICEW2.get(W2));
        HashMap<Byte, HashMap<Short, Integer>> HPRICEW3 = getHPRICE(subset, GORSubsets, GORLookups, W3);
        log("HPRICEW3 SummaryStatistics");
        logSummaryStatistics(HPRICEW3.get(W3));
        HashMap<Byte, HashMap<Short, Integer>> HPRICEW4 = getHPRICE(subset, GORSubsets, GORLookups, W4);
        log("HPRICEW4 SummaryStatistics");
        logSummaryStatistics(HPRICEW4.get(W4));
        HashMap<Byte, HashMap<Short, Integer>> HPRICEW5 = getHPRICE(subset, GORSubsets, GORLookups, W5);
        log("HPRICEW5 SummaryStatistics");
        logSummaryStatistics(HPRICEW5.get(W5));

        for (byte w = 0; w < WaAS_Data.NWAVES; w++) {
            log("Wave " + (w + 1));
            totals[w] = 0;
            for (byte b = 1; b < 13; b++) {
                HashSet<Short> GORSubset;
                GORSubset = GORSubsets[w].get(b);
                totals[w] += GORSubset.size();
                log("GOR " + b + " size " + GORSubset.size());
            }
            Generic_IO.writeObject(GORSubsets, GORSubsetsAndLookupF);
            log("Total all GORs " + totals[w]);
        }

        // DVLUKDEBT Debt on UK Land
        // DVOPRDEBT Debt on other property
        // DVOPRVAL Value of other property
        // DVGrsRentAmtAnnualw5_aggr	Variable label = Household Gross annual income from rent  - (rounded to 3 significant figures)
        // Pos. = 32	Variable = HValueW4	Variable label = Expected current value of main residence (£)
        // Pos. = 33	Variable = HValBW4	Variable label = Estimate of main residence value 
        log("Total (Hhold aggregate) HPROPW in Wave 1 " + HPROPW1);
        log("Total (Hhold aggregate) HPROPW in Wave 2 " + HPROPW2);
        log("Total (Hhold aggregate) HPROPW in Wave 3 " + HPROPW3);
        log("Total (Hhold aggregate) HPROPW in Wave 4 " + HPROPW4);
        log("Total (Hhold aggregate) HPROPW in Wave 5 " + HPROPW5);

        log("Total (Hhold aggregate) DVLUKVAL in Wave 1 " + DVLUKVAL1);
        log("Total (Hhold aggregate) DVLUKVAL in Wave 2 " + DVLUKVAL2);
        log("Total (Hhold aggregate) DVLUKVAL in Wave 3 " + DVLUKVAL3);
        log("Total (Hhold aggregate) DVLUKVAL in Wave 4 " + DVLUKVAL4);
        log("Total (Hhold aggregate) DVLUKVAL in Wave 5 " + DVLUKVAL5);

        log("Total (Person aggregate) FINCVB1 in Wave 1 " + FINCVB1);
        log("Total (Person aggregate) FINCVB1 in Wave 2 " + FINCVB2);
        log("Total (Person aggregate) FINCVB1 in Wave 3 " + FINCVB3);
        log("Total (Person aggregate) FINCVB1 in Wave 4 " + FINCVB4);
        log("Total (Person aggregate) FINCVB1 in Wave 5 " + FINCVB5);

//        log("Total (Hhold aggregate) HPRICEB Wave 1 " + HPRICEB1);
//        log("Total (Hhold aggregate) HPRICEB Wave 2 " + HPRICEB2);
//        log("Total (Hhold aggregate) HPRICEB Wave 3 " + HPRICEB3);
//        log("Total (Hhold aggregate) HPRICEB Wave 4 " + HPRICEB4);
//        log("Total (Hhold aggregate) HPRICEB Wave 5 " + HPRICEB5);
        logPW.close();
    }

    protected void logSummaryStatistics(HashMap<?, Integer> m) {
        IntSummaryStatistics stats = m.values().stream().
                collect(IntSummaryStatistics::new, IntSummaryStatistics::accept,
                        IntSummaryStatistics::combine);
        log("Max " + stats.getMax());
        log("Min " + stats.getMin());
        log("Count " + stats.getCount());
        log("Sum " + stats.getSum());
        log("Average " + stats.getAverage());
    }
    
    protected void initlog(int i) {
        logF = new File(Files.getOutputDataDir(Strings), "log" + i + ".txt");
        logPW = Generic_IO.getPrintWriter(logF, true); // Append to log file.
    }

    /**
     * Go through hholds for all waves and figure which ones have not
     * significantly changed in terms of hhold composition. Having children and
     * children leaving home is fine. Anything else is perhaps an issue...
     *
     * @param outdir
     * @return
     */
    public HashSet<Short> doDataProcessingStep3(File outdir) {
        HashSet<Short> r;
        r = new HashSet<>();
        initlog(3);
        log("Number of combined records " + data.CASEW1ToCID.size());
        log("Number of collections of combined records " + data.data.size());

        //HashSet<Short> s = new HashSet<>();
        Iterator<Short> ite;
        Iterator<Short> ite2;
        short cID;
        short CASEW1;
        WaAS_Collection c;
        WaAS_Combined_Record cr;
        HashMap<Short, WaAS_Combined_Record> cData;
        String m;
        // Check For Household Records
        m = "Check For Household Records";
        boolean check;
        int count0;
        count0 = 0;
        int count1;
        count1 = 0;
        int count2;
        count2 = 0;
        int count3;
        count3 = 0;
        log("<" + m + ">");
        ite = data.data.keySet().iterator();
        while (ite.hasNext()) {
            cID = ite.next();
            c = data.getCollection(cID);
            cData = c.getData();
            ite2 = cData.keySet().iterator();
            while (ite2.hasNext()) {
                CASEW1 = ite2.next();
                cr = cData.get(CASEW1);
                check = process0(CASEW1, cr);
                if (check) {
                    count0++;
                }
                check = process1(CASEW1, cr);
                if (check) {
                    count1++;
                }
                check = process2(CASEW1, cr);
                if (check) {
                    count2++;
                }
                check = process3(CASEW1, cr);
                if (check) {
                    count3++;
                    r.add(CASEW1);
                }
            }
            data.clearCollection(cID);
        }
        log("" + count0 + " Total hholds in all 5 waves.");
        log("" + count1 + " Total hholds that are a single hhold over all 5 "
                + "waves.");
        log("" + count2 + " Total hholds that are a single hhold over all 5 "
                + "waves and have same number of adults in all 5 waves.");
        log("" + count3 + " Total hholds that are a single hhold over all 5 "
                + "waves and have the same basic adult household composition "
                + "over all 5 waves.");
        log("</" + m + ">");
        return r;
    }

    /**
     * Checks if cr has the same basic adult household composition in each wave
     * for those hholds that have only 1 record for each wave. Iff this is the
     * case then true is returned. The number of adults in a household is
     * allowed to decrease. If the number of adults increases, then a further
     * check is done: If the number of householders is the same and the number
     * of children has decreased (it might be assumed that children have become
     * non-dependents). But, if that is not the case, then if the number of
     * dependents increases for any wave then false is returned.
     *
     * @param CASEW1
     * @param cr
     * @return true iff cr has only 1 record for each wave.
     */
    protected boolean process3(short CASEW1, WaAS_Combined_Record cr) {
        boolean r;
        r = true;
        if (cr.w2Records.size() > 1) {
            log("There are multiple Wave 2 records for CASEW1 " + CASEW1);
            r = false;
        }
        Short CASEW2;
        Iterator<Short> ite2;
        ite2 = cr.w2Records.keySet().iterator();
        while (ite2.hasNext()) {
            CASEW2 = ite2.next();
            WaAS_Wave2_Record w2rec;
            w2rec = cr.w2Records.get(CASEW2);
            String m3;
            m3 = "There are multiple Wave 3 records for "
                    + "CASEW2 " + CASEW2 + " in CASEW1 " + CASEW1;
            if (cr.w3Records.containsKey(CASEW2)) {
                HashMap<Short, WaAS_Wave3_Record> w3_2;
                w3_2 = cr.w3Records.get(CASEW2);
                if (w3_2.size() > 1) {
                    log(m3);
                    r = false;
                } else {
                    Short CASEW3;
                    Iterator<Short> ite3;
                    ite3 = w3_2.keySet().iterator();
                    while (ite3.hasNext()) {
                        CASEW3 = ite3.next();
                        WaAS_Wave3_Record w3rec;
                        w3rec = w3_2.get(CASEW3);
                        String m4;
                        m4 = "There are multiple Wave 4 records for "
                                + "CASEW3 " + CASEW3
                                + " in CASEW2 " + CASEW2
                                + " in CASEW1 " + CASEW1;
                        if (cr.w4Records.containsKey(CASEW2)) {
                            HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                            w4_2 = cr.w4Records.get(CASEW2);
                            if (w4_2.containsKey(CASEW3)) {
                                HashMap<Short, WaAS_Wave4_Record> w4_3;
                                w4_3 = w4_2.get(CASEW3);
                                if (w4_3.size() > 1) {
                                    log(m4);
                                    r = false;
                                } else {
                                    Iterator<Short> ite4;
                                    ite4 = w4_3.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        Short CASEW4;
                                        CASEW4 = ite4.next();
                                        WaAS_Wave4_Record w4rec;
                                        w4rec = w4_3.get(CASEW4);
                                        String m5;
                                        m5 = "There are multiple Wave 5 records for "
                                                + "CASEW4 " + CASEW4
                                                + " in CASEW3 " + CASEW3
                                                + " in CASEW2 " + CASEW2
                                                + " in CASEW1 " + CASEW1;
                                        if (cr.w5Records.containsKey(CASEW2)) {
                                            HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                                            w5_2 = cr.w5Records.get(CASEW2);
                                            if (w5_2.containsKey(CASEW3)) {
                                                HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                                                w5_3 = w5_2.get(CASEW3);
                                                if (w5_3.containsKey(CASEW4)) {
                                                    HashMap<Short, WaAS_Wave5_Record> w5_4;
                                                    w5_4 = w5_3.get(CASEW4);
                                                    if (w5_4.size() > 1) {
                                                        log(m5);
                                                        r = false;
                                                    } else {
                                                        Iterator<Short> ite5;
                                                        ite5 = w5_4.keySet().iterator();
                                                        while (ite5.hasNext()) {
                                                            Short CASEW5;
                                                            CASEW5 = ite5.next();
                                                            WaAS_Wave5_Record w5rec;
                                                            w5rec = w5_4.get(CASEW5);
                                                            // Wave 1
                                                            WaAS_Wave1_HHOLD_Record w1hhold;
                                                            w1hhold = cr.w1Record.getHhold();
                                                            ArrayList<WaAS_Wave1_PERSON_Record> w1people;
                                                            w1people = cr.w1Record.getPeople();
                                                            // Wave 2
                                                            WaAS_Wave2_HHOLD_Record w2hhold;
                                                            w2hhold = w2rec.getHhold();
                                                            ArrayList<WaAS_Wave2_PERSON_Record> w2people;
                                                            w2people = w2rec.getPeople();
                                                            // Wave 3
                                                            WaAS_Wave3_HHOLD_Record w3hhold;
                                                            w3hhold = w3rec.getHhold();
                                                            ArrayList<WaAS_Wave3_PERSON_Record> w3people;
                                                            w3people = w3rec.getPeople();
                                                            // Wave 4
                                                            WaAS_Wave4_HHOLD_Record w4hhold;
                                                            w4hhold = w4rec.getHhold();
                                                            ArrayList<WaAS_Wave4_PERSON_Record> w4people;
                                                            w4people = w4rec.getPeople();
                                                            // Wave 5
                                                            WaAS_Wave5_HHOLD_Record w5hhold;
                                                            w5hhold = w5rec.getHhold();
                                                            ArrayList<WaAS_Wave5_PERSON_Record> w5people;
                                                            w5people = w5rec.getPeople();
                                                            byte w1NUMADULT = w1hhold.getNUMADULT();
                                                            byte w1NUMCHILD = w1hhold.getNUMCHILD();
                                                            //byte w1NUMDEPCH = w1hhold.getNUMDEPCH();
                                                            byte w1NUMHHLDR = w1hhold.getNUMHHLDR();

                                                            byte w2NUMADULT = w2hhold.getNUMADULT();
                                                            byte w2NUMCHILD = w2hhold.getNUMCHILD();
                                                            //byte w2NUMDEPCH = w2hhold.getNUMDEPCH_HH();
                                                            //boolean w2NUMNDEP = w2hhold.getNUMNDEP();
                                                            byte w2NUMHHLDR = w2hhold.getNUMHHLDR();

                                                            byte w3NUMADULT = w3hhold.getNUMADULT();
                                                            byte w3NUMCHILD = w3hhold.getNUMCHILD();
                                                            //byte w3NUMDEPCH = w3hhold.getNUMDEPCH();
                                                            byte w3NUMHHLDR = w3hhold.getNUMHHLDR();

                                                            byte w4NUMADULT = w4hhold.getNUMADULT();
                                                            byte w4NUMCHILD = w4hhold.getNUMCHILD();
                                                            //byte w4NUMDEPCH = w4hhold.getNUMDEPCH();
                                                            byte w4NUMHHLDR = w4hhold.getNUMHHLDR();

                                                            byte w5NUMADULT = w5hhold.getNUMADULT();
                                                            byte w5NUMCHILD = w5hhold.getNUMCHILD();
                                                            //byte w5NUMDEPCH = w5hhold.getNUMDEPCH();
                                                            byte w5NUMHHLDR = w5hhold.getNUMHHLDR();
                                                            // Compare Wave 1 to Wave 2
                                                            if (w1NUMADULT > w2NUMADULT) {
                                                                if (!(w1NUMHHLDR == w2NUMHHLDR
                                                                        && w1NUMCHILD > w2NUMCHILD)) {
                                                                    // Compare Number of Non dependents in Waves 1 and 2
                                                                    int w1NUMNDep = getNUMNDEP(w1people);
                                                                    int w2NUMNDep = getNUMNDEP(w2people);
                                                                    if (w1NUMNDep < w2NUMNDep) {
                                                                        r = false;
                                                                    }
                                                                }
                                                            }
                                                            // Compare Wave 2 to Wave 3
                                                            if (w2NUMADULT > w3NUMADULT) {
                                                                if (!(w2NUMHHLDR == w3NUMHHLDR
                                                                        && w2NUMCHILD > w3NUMCHILD)) {
                                                                    // Compare Number of Non dependents in Waves 2 and 3
                                                                    int w2NUMNDep = getNUMNDEP(w2people);
                                                                    int w3NUMNDep = getNUMNDEP(w3people);
                                                                    if (w2NUMNDep < w3NUMNDep) {
                                                                        r = false;
                                                                    }
                                                                }
                                                            }
                                                            // Compare Wave 3 to Wave 4
                                                            if (w3NUMADULT > w4NUMADULT) {
                                                                if (!(w3NUMHHLDR == w4NUMHHLDR
                                                                        && w3NUMCHILD > w4NUMCHILD)) {
                                                                    // Compare Number of Non dependents in Waves 3 and 4
                                                                    int w3NUMNDep = getNUMNDEP(w3people);
                                                                    int w4NUMNDep = getNUMNDEP(w4people);
                                                                    if (w3NUMNDep < w4NUMNDep) {
                                                                        r = false;
                                                                    }
                                                                }
                                                            }
                                                            // Compare Wave 4 to Wave 5
                                                            if (w4NUMADULT > w5NUMADULT) {
                                                                if (!(w4NUMHHLDR == w5NUMHHLDR
                                                                        && w4NUMCHILD > w5NUMCHILD)) {
                                                                    // Compare Number of Non dependents in Waves 4 and 5
                                                                    int w4NUMNDep = getNUMNDEP(w4people);
                                                                    int w5NUMNDep = getNUMNDEP(w5people);
                                                                    if (w4NUMNDep < w5NUMNDep) {
                                                                        r = false;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    log("!w5_3.containsKey(CASEW4) " + m5);
                                                }
                                            } else {
                                                log("!w5_2.containsKey(CASEW3) " + m5);
                                                r = false;
                                            }
                                        } else {
                                            log("!cr.w5Records.containsKey(CASEW2) " + m5);
                                            r = false;
                                        }
                                    }
                                }
                            } else {
                                log("!w4_2.containsKey(CASEW3) " + m4);
                                r = false;
                            }
                        } else {
                            log("!cr.w4Records.containsKey(CASEW2) " + m4);
                            r = false;
                        }
                    }
                }
            } else {
                log(m3);
            }
        }
        return r;
    }

    /**
     * Get number of non child dependents.
     *
     * @param <T>
     * @param people
     * @return
     * @TODO Move to Person Handler
     */
    public <T> int getNUMNDEP(ArrayList<T> people) {
        int r = 0;
        WaAS_Wave1Or2Or3Or4Or5_PERSON_Record p2;
        Iterator<T> ite;
        ite = people.iterator();
        while (ite.hasNext()) {
            p2 = (WaAS_Wave1Or2Or3Or4Or5_PERSON_Record) ite.next();
//            p2.getISHRP();
//            p2.getISHRPPART();
//            p2.getISCHILD();
            if (p2.getISNDEP()) {
                r++;
            }
        }
        return r;
    }

    /**
     * Checks if cr has the same number of adults in each wave for those hholds
     * that have only 1 record for each wave.
     *
     * @param CASEW1
     * @param cr
     * @return true iff cr has only 1 record for each wave.
     */
    protected boolean process2(short CASEW1, WaAS_Combined_Record cr) {
        boolean r;
        r = true;
        if (cr.w2Records.size() > 1) {
            log("There are multiple Wave 2 records for CASEW1 " + CASEW1);
            r = false;
        }
        Short CASEW2;
        Iterator<Short> ite2;
        ite2 = cr.w2Records.keySet().iterator();
        while (ite2.hasNext()) {
            CASEW2 = ite2.next();
            WaAS_Wave2_Record w2rec;
            w2rec = cr.w2Records.get(CASEW2);
            String m3;
            m3 = "There are multiple Wave 3 records for "
                    + "CASEW2 " + CASEW2 + " in CASEW1 " + CASEW1;
            if (cr.w3Records.containsKey(CASEW2)) {
                HashMap<Short, WaAS_Wave3_Record> w3_2;
                w3_2 = cr.w3Records.get(CASEW2);
                if (w3_2.size() > 1) {
                    log(m3);
                    r = false;
                } else {
                    Short CASEW3;
                    Iterator<Short> ite3;
                    ite3 = w3_2.keySet().iterator();
                    while (ite3.hasNext()) {
                        CASEW3 = ite3.next();
                        WaAS_Wave3_Record w3rec;
                        w3rec = w3_2.get(CASEW3);
                        String m4;
                        m4 = "There are multiple Wave 4 records for "
                                + "CASEW3 " + CASEW3
                                + " in CASEW2 " + CASEW2
                                + " in CASEW1 " + CASEW1;
                        if (cr.w4Records.containsKey(CASEW2)) {
                            HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                            w4_2 = cr.w4Records.get(CASEW2);
                            if (w4_2.containsKey(CASEW3)) {
                                HashMap<Short, WaAS_Wave4_Record> w4_3;
                                w4_3 = w4_2.get(CASEW3);
                                if (w4_3.size() > 1) {
                                    log(m4);
                                    r = false;
                                } else {
                                    Iterator<Short> ite4;
                                    ite4 = w4_3.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        Short CASEW4;
                                        CASEW4 = ite4.next();
                                        WaAS_Wave4_Record w4rec;
                                        w4rec = w4_3.get(CASEW4);
                                        String m5;
                                        m5 = "There are multiple Wave 5 records for "
                                                + "CASEW4 " + CASEW4
                                                + " in CASEW3 " + CASEW3
                                                + " in CASEW2 " + CASEW2
                                                + " in CASEW1 " + CASEW1;
                                        if (cr.w5Records.containsKey(CASEW2)) {
                                            HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                                            w5_2 = cr.w5Records.get(CASEW2);
                                            if (w5_2.containsKey(CASEW3)) {
                                                HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                                                w5_3 = w5_2.get(CASEW3);
                                                if (w5_3.containsKey(CASEW4)) {
                                                    HashMap<Short, WaAS_Wave5_Record> w5_4;
                                                    w5_4 = w5_3.get(CASEW4);
                                                    if (w5_4.size() > 1) {
                                                        log(m5);
                                                        r = false;
                                                    } else {
                                                        Iterator<Short> ite5;
                                                        ite5 = w5_4.keySet().iterator();
                                                        while (ite5.hasNext()) {
                                                            Short CASEW5;
                                                            CASEW5 = ite5.next();
                                                            WaAS_Wave5_Record w5rec;
                                                            w5rec = w5_4.get(CASEW5);
                                                            byte w1 = cr.w1Record.getHhold().getNUMADULT();
                                                            byte w2 = w2rec.getHhold().getNUMADULT();
                                                            byte w3 = w3rec.getHhold().getNUMADULT();
                                                            byte w4 = w4rec.getHhold().getNUMADULT();
                                                            byte w5 = w5rec.getHhold().getNUMADULT();
                                                            if (!(w1 == w2 && w2 == w3 && w3 == w4 && w4 == w5)) {
                                                                r = false;
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    log("!w5_3.containsKey(CASEW4) " + m5);
                                                }
                                            } else {
                                                log("!w5_2.containsKey(CASEW3) " + m5);
                                                r = false;
                                            }
                                        } else {
                                            log("!cr.w5Records.containsKey(CASEW2) " + m5);
                                            r = false;
                                        }
                                    }
                                }
                            } else {
                                log("!w4_2.containsKey(CASEW3) " + m4);
                                r = false;
                            }
                        } else {
                            log("!cr.w4Records.containsKey(CASEW2) " + m4);
                            r = false;
                        }
                    }
                }
            } else {
                log(m3);
            }
        }
        return r;
    }

    /**
     * Checks if cr has only 1 record for each wave.
     *
     * @param CASEW1
     * @param cr
     * @return true iff cr has only 1 record for each wave.
     */
    protected boolean process1(short CASEW1, WaAS_Combined_Record cr) {
        boolean r;
        r = true;
        if (cr.w2Records.size() > 1) {
            log("There are multiple Wave 2 records for CASEW1 " + CASEW1);
            r = false;
        }
        Short CASEW2;
        Iterator<Short> ite2;
        ite2 = cr.w2Records.keySet().iterator();
        while (ite2.hasNext()) {
            CASEW2 = ite2.next();
            //WaAS_Wave2_Record w2rec;
            //w2rec = cr.w2Records.get(CASEW2);
            String m3;
            m3 = "There are multiple Wave 3 records for "
                    + "CASEW2 " + CASEW2 + " in CASEW1 " + CASEW1;
            if (cr.w3Records.containsKey(CASEW2)) {
                HashMap<Short, WaAS_Wave3_Record> w3_2;
                w3_2 = cr.w3Records.get(CASEW2);
                if (w3_2.size() > 1) {
                    log(m3);
                    r = false;
                } else {
                    Short CASEW3;
                    Iterator<Short> ite3;
                    ite3 = w3_2.keySet().iterator();
                    while (ite3.hasNext()) {
                        CASEW3 = ite3.next();
                        //WaAS_Wave3_Record w3rec;
                        //w3rec = w3_2.get(CASEW3);
                        String m4;
                        m4 = "There are multiple Wave 4 records for "
                                + "CASEW3 " + CASEW3
                                + " in CASEW2 " + CASEW2
                                + " in CASEW1 " + CASEW1;
                        if (cr.w4Records.containsKey(CASEW2)) {
                            HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                            w4_2 = cr.w4Records.get(CASEW2);
                            if (w4_2.containsKey(CASEW3)) {
                                HashMap<Short, WaAS_Wave4_Record> w4_3;
                                w4_3 = w4_2.get(CASEW3);
                                if (w4_3.size() > 1) {
                                    log(m4);
                                    r = false;
                                } else {
                                    Iterator<Short> ite4;
                                    ite4 = w4_3.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        Short CASEW4;
                                        CASEW4 = ite4.next();
                                        //WaAS_Wave4_Record w4rec;
                                        //w4rec = w4_3.get(CASEW4);
                                        String m5;
                                        m5 = "There are multiple Wave 5 records for "
                                                + "CASEW4 " + CASEW4
                                                + " in CASEW3 " + CASEW3
                                                + " in CASEW2 " + CASEW2
                                                + " in CASEW1 " + CASEW1;
                                        if (cr.w5Records.containsKey(CASEW2)) {
                                            HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                                            w5_2 = cr.w5Records.get(CASEW2);
                                            if (w5_2.containsKey(CASEW3)) {
                                                HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                                                w5_3 = w5_2.get(CASEW3);
                                                if (w5_3.containsKey(CASEW4)) {
                                                    HashMap<Short, WaAS_Wave5_Record> w5_4;
                                                    w5_4 = w5_3.get(CASEW4);
                                                    if (w5_4.size() > 1) {
                                                        log(m5);
                                                        r = false;
                                                    } else {
//                                                        Iterator<Short> ite5;
//                                                        ite5 = w5_4.keySet().iterator();
//                                                        while (ite5.hasNext()) {
//                                                            Short CASEW5;
//                                                            CASEW5 = ite5.next();
//                                                            WaAS_Wave5_Record w5rec;
//                                                            w5rec = w5_4.get(CASEW5);
//                                                        }
                                                    }
                                                } else {
                                                    log("!w5_3.containsKey(CASEW4) " + m5);
                                                }
                                            } else {
                                                log("!w5_2.containsKey(CASEW3) " + m5);
                                                r = false;
                                            }
                                        } else {
                                            log("!cr.w5Records.containsKey(CASEW2) " + m5);
                                            r = false;
                                        }
                                    }
                                }
                            } else {
                                log("!w4_2.containsKey(CASEW3) " + m4);
                                r = false;
                            }
                        } else {
                            log("!cr.w4Records.containsKey(CASEW2) " + m4);
                            r = false;
                        }
                    }
                }
            } else {
                log(m3);
            }
        }
        return r;
    }

    /**
     * Checks if cr has records for each wave.
     *
     * @param CASEW1
     * @param cr
     * @return true iff cr has records for each wave.
     */
    protected boolean process0(short CASEW1, WaAS_Combined_Record cr) {
        boolean r;
        r = true;
        if (cr.w1Record == null) {
            log("There is no Wave 1 record for CASEW1 " + CASEW1);
            r = false;
        }
        if (cr.w2Records.isEmpty()) {
            log("There are no Wave 2 records for CASEW1 " + CASEW1);
            r = false;
        }
        Short CASEW2;
        Iterator<Short> ite2;
        ite2 = cr.w2Records.keySet().iterator();
        while (ite2.hasNext()) {
            CASEW2 = ite2.next();
            //WaAS_Wave2_Record w2rec;
            //w2rec = cr.w2Records.get(CASEW2);
            String m3;
            m3 = "There are no Wave 3 records for "
                    + "CASEW2 " + CASEW2 + " in CASEW1 " + CASEW1;
            if (cr.w3Records.containsKey(CASEW2)) {
                HashMap<Short, WaAS_Wave3_Record> w3_2;
                w3_2 = cr.w3Records.get(CASEW2);
                Short CASEW3;
                Iterator<Short> ite3;
                ite3 = w3_2.keySet().iterator();
                while (ite3.hasNext()) {
                    CASEW3 = ite3.next();
                    //WaAS_Wave3_Record w3rec;
                    //w3rec = w3_2.get(CASEW3);
                    String m4;
                    m4 = "There are no Wave 4 records for "
                            + "CASEW3 " + CASEW3
                            + " in CASEW2 " + CASEW2
                            + " in CASEW1 " + CASEW1;
                    if (cr.w4Records.containsKey(CASEW2)) {
                        HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                        w4_2 = cr.w4Records.get(CASEW2);
                        if (w4_2.containsKey(CASEW3)) {
                            HashMap<Short, WaAS_Wave4_Record> w4_3;
                            w4_3 = w4_2.get(CASEW3);
                            Iterator<Short> ite4;
                            ite4 = w4_3.keySet().iterator();
                            while (ite4.hasNext()) {
                                Short CASEW4;
                                CASEW4 = ite4.next();
                                //WaAS_Wave4_Record w4rec;
                                //w4rec = w4_3.get(CASEW4);
                                String m5;
                                m5 = "There are no Wave 5 records for "
                                        + "CASEW4 " + CASEW4
                                        + " in CASEW3 " + CASEW3
                                        + " in CASEW2 " + CASEW2
                                        + " in CASEW1 " + CASEW1;
                                if (cr.w5Records.containsKey(CASEW2)) {
                                    HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                                    w5_2 = cr.w5Records.get(CASEW2);
                                    if (w5_2.containsKey(CASEW3)) {
                                        HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                                        w5_3 = w5_2.get(CASEW3);
                                        if (w5_3.containsKey(CASEW4)) {
                                            HashMap<Short, WaAS_Wave5_Record> w5_4;
                                            w5_4 = w5_3.get(CASEW4);
//                                            Iterator<Short> ite5;
//                                            ite5 = w5_4.keySet().iterator();
//                                            while (ite5.hasNext()) {
//                                                Short CASEW5;
//                                                CASEW5 = ite5.next();
//                                                WaAS_Wave5_Record w5rec;
//                                                w5rec = w5_4.get(CASEW5);
//                                            }
                                        } else {
                                            log("!w5_3.containsKey(CASEW4) " + m5);
                                        }
                                    } else {
                                        log("!w5_2.containsKey(CASEW3) " + m5);
                                        r = false;
                                    }
                                } else {
                                    log("!cr.w5Records.containsKey(CASEW2) " + m5);
                                    r = false;
                                }
                            }
                        } else {
                            log("!w4_2.containsKey(CASEW3) " + m4);
                            r = false;
                        }
                    } else {
                        log("!cr.w4Records.containsKey(CASEW2) " + m4);
                        r = false;
                    }
                }
            } else {
                log("!cr.w3Records.containsKey(CASEW2) " + m3);
                r = false;
            }
        }
        return r;
    }

    protected void addVariable(String s, TreeMap<Integer, String> vIDToVName,
            TreeMap<String, Integer> vNameToVID) {
        vIDToVName.put(0, s);
        vNameToVID.put(s, 0);
    }

    /**
     * Get the total DVLUKVAL in subset.
     *
     * @param subset
     * @param wave
     * @return
     */
    public long getDVLUKVAL(HashSet<Short> subset, byte wave) {
//      Value label information for DVLUKVal
//	Value = -9.0	Label = Don t know
//	Value = -8.0	Label = Refused
//	Value = -7.0	Label = Does not apply
//	Value = -6.0	Label = Error Partial
        // For brevity/convenience.
        byte W1 = WaAS_Data.W1;
        byte W2 = WaAS_Data.W2;
        byte W3 = WaAS_Data.W3;
        byte W4 = WaAS_Data.W4;
        byte W5 = WaAS_Data.W5;
        long tDVLUKVAL;
        if (wave == W1) {
            tDVLUKVAL = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        DVLUKVAL = cr.w1Record.getHhold().getDVLUKVAL();
                        if (DVLUKVAL == Integer.MIN_VALUE) {
                            DVLUKVAL = 0;
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else if (wave == W2) {
            tDVLUKVAL = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, WaAS_Wave2_Record> w2Records;
                        w2Records = cr.w2Records;
                        Short CASEW2;
                        Iterator<Short> ite;
                        ite = w2Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            int DVLUKVALW2 = w2Records.get(CASEW2).getHhold().getDVLUKVAL();
                            if (DVLUKVALW2 != Integer.MIN_VALUE) {
                                DVLUKVAL += DVLUKVALW2;
                            }
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else if (wave == W3) {
            tDVLUKVAL = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, WaAS_Wave3_Record>> w3Records;
                        w3Records = cr.w3Records;
                        Short CASEW2;
                        Short CASEW3;
                        Iterator<Short> ite;
                        ite = w3Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, WaAS_Wave3_Record> w3_2;
                            w3_2 = w3Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w3_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                int DVLUKVALW3 = w3_2.get(CASEW3).getHhold().getDVLUKVAL_SUM();
                                if (DVLUKVALW3 != Integer.MIN_VALUE) {
                                    DVLUKVAL += DVLUKVALW3;
                                }
                            }
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else if (wave == W4) {
            tDVLUKVAL = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave4_Record>>> w4Records;
                        w4Records = cr.w4Records;
                        Short CASEW2;
                        Short CASEW3;
                        Short CASEW4;
                        Iterator<Short> ite;
                        ite = w4Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                            w4_2 = w4Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w4_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                HashMap<Short, WaAS_Wave4_Record> w4_3;
                                w4_3 = w4_2.get(CASEW3);
                                Iterator<Short> ite3;
                                ite3 = w4_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    CASEW4 = ite3.next();
                                    int DVLUKVALW4 = w4_3.get(CASEW4).getHhold().getDVLUKVAL_SUM();
                                    if (DVLUKVALW4 != Integer.MIN_VALUE) {
                                        DVLUKVAL += DVLUKVALW4;
                                    }
                                }
                            }
                        }
                    }
                    return DVLUKVAL;
                }).sum();
                data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else if (wave == W5) {
            tDVLUKVAL = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cDVLUKVAL = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    int DVLUKVAL = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>>> w5Records;
                        w5Records = cr.w5Records;
                        Short CASEW2;
                        Short CASEW3;
                        Short CASEW4;
                        Short CASEW5;
                        Iterator<Short> ite;
                        ite = w5Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                            w5_2 = w5Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w5_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                                w5_3 = w5_2.get(CASEW3);
                                Iterator<Short> ite3;
                                ite3 = w5_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    CASEW4 = ite3.next();
                                    HashMap<Short, WaAS_Wave5_Record> w5_4;
                                    w5_4 = w5_3.get(CASEW4);
                                    Iterator<Short> ite4;
                                    ite4 = w5_4.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        CASEW5 = ite4.next();
                                        int DVLUKVALW5 = w5_4.get(CASEW5).getHhold().getDVLUKVAL_SUM();
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
                data.clearCollection(cID);
                return cDVLUKVAL;
            }).sum();
        } else {
            tDVLUKVAL = 0;
        }
        log("Total (Hhold aggregate) DVLUKVAL in Wave " + wave + " " + tDVLUKVAL);
        return tDVLUKVAL;
    }

    /**
     * Get the total HPROPW in subset.
     *
     * @param subset
     * @param wave
     * @return
     */
    public double getHPROPW(HashSet<Short> subset, byte wave) {
//	Value label information for HPROPW
//	Value = -9.0	Label = Do not know
//	Value = -8.0	Label = Refusal
//	Value = -7.0	Label = Not applicable
//	Value = -6.0	Label = Error_partial
        // For brevity/convenience.
        byte W1 = WaAS_Data.W1;
        byte W2 = WaAS_Data.W2;
        byte W3 = WaAS_Data.W3;
        byte W4 = WaAS_Data.W4;
        byte W5 = WaAS_Data.W5;
        double tHPROPW;
        if (wave == W1) {
            tHPROPW = data.data.keySet().stream().mapToDouble(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                double cHPROPW = c.getData().keySet().stream().mapToDouble(CASEW1 -> {
                    double HPROPW = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HPROPW = cr.w1Record.getHhold().getHPROPW();
                        if (HPROPW < 0) {
                            HPROPW = 0;
                        }
                    }
                    return HPROPW;
                }).sum();
                data.clearCollection(cID);
                return cHPROPW;
            }).sum();
        } else if (wave == W2) {
            tHPROPW = data.data.keySet().stream().mapToDouble(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                double cHPROPW = c.getData().keySet().stream().mapToDouble(CASEW1 -> {
                    double HPROPW = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, WaAS_Wave2_Record> w2Records;
                        w2Records = cr.w2Records;
                        Short CASEW2;
                        Iterator<Short> ite;
                        ite = w2Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            double HPROPWW2 = w2Records.get(CASEW2).getHhold().getHPROPW();
                            if (HPROPWW2 > 0) {
                                HPROPW += HPROPWW2;
                            }
                        }
                    }
                    return HPROPW;
                }).sum();
                data.clearCollection(cID);
                return cHPROPW;
            }).sum();
        } else if (wave == W3) {
            tHPROPW = data.data.keySet().stream().mapToDouble(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                double cHPROPW = c.getData().keySet().stream().mapToDouble(CASEW1 -> {
                    double HPROPW = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, WaAS_Wave3_Record>> w3Records;
                        w3Records = cr.w3Records;
                        Short CASEW2;
                        Short CASEW3;
                        Iterator<Short> ite;
                        ite = w3Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, WaAS_Wave3_Record> w3_2;
                            w3_2 = w3Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w3_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                double HPROPWW3 = w3_2.get(CASEW3).getHhold().getHPROPW();
                                if (HPROPWW3 > 0) {
                                    HPROPW += HPROPWW3;
                                }
                            }
                        }
                    }
                    return HPROPW;
                }).sum();
                data.clearCollection(cID);
                return cHPROPW;
            }).sum();
        } else if (wave == W4) {
            tHPROPW = data.data.keySet().stream().mapToDouble(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                double cHPROPW = c.getData().keySet().stream().mapToDouble(CASEW1 -> {
                    double HPROPW = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave4_Record>>> w4Records;
                        w4Records = cr.w4Records;
                        Short CASEW2;
                        Short CASEW3;
                        Short CASEW4;
                        Iterator<Short> ite;
                        ite = w4Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                            w4_2 = w4Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w4_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                HashMap<Short, WaAS_Wave4_Record> w4_3;
                                w4_3 = w4_2.get(CASEW3);
                                Iterator<Short> ite3;
                                ite3 = w4_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    CASEW4 = ite3.next();
                                    double HPROPWW4 = w4_3.get(CASEW4).getHhold().getHPROPW();
                                    if (HPROPWW4 > 0) {
                                        HPROPW += HPROPWW4;
                                    }
                                }
                            }
                        }
                    }
                    return HPROPW;
                }).sum();
                data.clearCollection(cID);
                return cHPROPW;
            }).sum();
        } else if (wave == W5) {
            tHPROPW = data.data.keySet().stream().mapToDouble(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                double cHPROPW = c.getData().keySet().stream().mapToDouble(CASEW1 -> {
                    double HPROPW = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>>> w5Records;
                        w5Records = cr.w5Records;
                        Short CASEW2;
                        Short CASEW3;
                        Short CASEW4;
                        Short CASEW5;
                        Iterator<Short> ite;
                        ite = w5Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                            w5_2 = w5Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w5_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                                w5_3 = w5_2.get(CASEW3);
                                Iterator<Short> ite3;
                                ite3 = w5_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    CASEW4 = ite3.next();
                                    HashMap<Short, WaAS_Wave5_Record> w5_4;
                                    w5_4 = w5_3.get(CASEW4);
                                    Iterator<Short> ite4;
                                    ite4 = w5_4.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        CASEW5 = ite4.next();
                                        double HPROPWW5 = w5_4.get(CASEW5).getHhold().getHPROPW();
                                        if (HPROPWW5 > 0) {
                                            HPROPW += HPROPWW5;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return HPROPW;
                }).sum();
                data.clearCollection(cID);
                return cHPROPW;
            }).sum();
        } else {
            tHPROPW = 0;
        }
        log("Total (Hhold aggregate) HPROPW in Wave " + wave + " " + tHPROPW);
        return tHPROPW;
    }

    /**
     * Get FINCVB.
     *
     * @param subset
     * @param wave
     * @return
     */
    public long getFINCVB(HashSet<Short> subset, byte wave) {
        // For brevity/convenience.
        byte W1 = WaAS_Data.W1;
        byte W2 = WaAS_Data.W2;
        byte W3 = WaAS_Data.W3;
        byte W4 = WaAS_Data.W4;
        byte W5 = WaAS_Data.W5;
        long tFINCVB;
        if (wave == W1) {
            tFINCVB = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    byte FINCVB = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        ArrayList<WaAS_Wave1_PERSON_Record> w1p;
                        w1p = cr.w1Record.getPeople();
                        Iterator<WaAS_Wave1_PERSON_Record> ite;
                        ite = w1p.iterator();
                        while (ite.hasNext()) {
                            FINCVB = ite.next().getFINCVB();
                            if (FINCVB == Byte.MIN_VALUE) {
                                FINCVB = 0;
                            }
                        }
                    }
                    return FINCVB;
                }).sum();
                data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else if (wave == W2) {
            tFINCVB = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    byte FINCVB = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, WaAS_Wave2_Record> w2Records;
                        w2Records = cr.w2Records;
                        Short CASEW2;
                        Iterator<Short> ite;
                        ite = w2Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            ArrayList<WaAS_Wave2_PERSON_Record> w2p;
                            w2p = w2Records.get(CASEW2).getPeople();
                            Iterator<WaAS_Wave2_PERSON_Record> ite2;
                            ite2 = w2p.iterator();
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
                data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else if (wave == W3) {
            tFINCVB = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    byte FINCVB = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, WaAS_Wave3_Record>> w3Records;
                        w3Records = cr.w3Records;
                        Short CASEW2;
                        Short CASEW3;
                        Iterator<Short> ite;
                        ite = w3Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, WaAS_Wave3_Record> w3_2;
                            w3_2 = w3Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w3_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                ArrayList<WaAS_Wave3_PERSON_Record> w3p;
                                w3p = w3_2.get(CASEW3).getPeople();
                                Iterator<WaAS_Wave3_PERSON_Record> ite3;
                                ite3 = w3p.iterator();
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
                data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else if (wave == W4) {
            tFINCVB = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    byte FINCVB = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave4_Record>>> w4Records;
                        w4Records = cr.w4Records;
                        Short CASEW2;
                        Short CASEW3;
                        Short CASEW4;
                        Iterator<Short> ite;
                        ite = w4Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                            w4_2 = w4Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w4_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                HashMap<Short, WaAS_Wave4_Record> w4_3;
                                w4_3 = w4_2.get(CASEW3);
                                Iterator<Short> ite3;
                                ite3 = w4_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    CASEW4 = ite3.next();
                                    ArrayList<WaAS_Wave4_PERSON_Record> w4p;
                                    w4p = w4_3.get(CASEW4).getPeople();
                                    Iterator<WaAS_Wave4_PERSON_Record> ite4;
                                    ite4 = w4p.iterator();
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
                data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else if (wave == W5) {
            tFINCVB = data.data.keySet().stream().mapToLong(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                long cFINCVB = c.getData().keySet().stream().mapToLong(CASEW1 -> {
                    byte FINCVB = 0;
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>>> w5Records;
                        w5Records = cr.w5Records;
                        Short CASEW2;
                        Short CASEW3;
                        Short CASEW4;
                        Short CASEW5;
                        Iterator<Short> ite;
                        ite = w5Records.keySet().iterator();
                        while (ite.hasNext()) {
                            CASEW2 = ite.next();
                            HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                            w5_2 = w5Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w5_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                                w5_3 = w5_2.get(CASEW3);
                                Iterator<Short> ite3;
                                ite3 = w5_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    CASEW4 = ite3.next();
                                    HashMap<Short, WaAS_Wave5_Record> w5_4;
                                    w5_4 = w5_3.get(CASEW4);
                                    Iterator<Short> ite4;
                                    ite4 = w5_4.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        CASEW5 = ite4.next();
                                        ArrayList<WaAS_Wave5_PERSON_Record> w5p;
                                        w5p = w5_4.get(CASEW5).getPeople();
                                        Iterator<WaAS_Wave5_PERSON_Record> ite5;
                                        ite5 = w5p.iterator();
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
                data.clearCollection(cID);
                return cFINCVB;
            }).sum();
        } else {
            tFINCVB = 0;
        }
        log("Total (Person aggregate) FINCVB in Wave " + wave + " " + tFINCVB);
        return tFINCVB;
    }

    /**
     * Get the total HPRICEB in subset.
     *
     * @param subset
     * @param GORSubsets
     * @param GORLookups
     * @param wave
     * @return Map with keys as GOR and Values as map with keys as CASEWX and
     * values as Houseprices.
     */
    public HashMap<Byte, HashMap<Short, Integer>> getHPRICE(
            HashSet<Short> subset,
            HashMap<Byte, HashSet<Short>>[] GORSubsets,
            HashMap<Short, Byte>[] GORLookups, byte wave) {
        // Initialise result
        HashMap<Byte, HashMap<Short, Integer>> r;
        r = new HashMap<>();
        Iterator<Byte> ite;
        ite = GORSubsets[wave - 1].keySet().iterator();
        while (ite.hasNext()) {
            Byte GOR;
            GOR = ite.next();
            r.put(GOR, new HashMap<>());
        }
        // For brevity/convenience.
        byte W1 = WaAS_Data.W1;
        byte W2 = WaAS_Data.W2;
        byte W3 = WaAS_Data.W3;
        byte W4 = WaAS_Data.W4;
        byte W5 = WaAS_Data.W5;
        if (wave == W1) {
            data.data.keySet().stream().forEach(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                c.getData().keySet().stream().forEach(CASEW1 -> {
                    if (GORLookups[0].keySet().contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        WaAS_Wave1_HHOLD_Record w1;
                        w1 = cr.w1Record.getHhold();
                        Byte GOR = GORLookups[wave - 1].get(CASEW1);
                        int HPRICE = w1.getHPRICE();
                        if (HPRICE < 0) {
                            byte HPRICEB = w1.getHPRICEB();
                            if (HPRICEB > 0) {
                                HPRICE = Wave1Or2HPRICEBLookup.get(HPRICEB);
                                Generic_Collections.addToMap(r, GOR, CASEW1, HPRICE);
                            }
                        } else {
                            Generic_Collections.addToMap(r, GOR, CASEW1, HPRICE);
                        }
                    }
                });
                data.clearCollection(cID);
            });
        } else if (wave == W2) {
            data.data.keySet().stream().forEach(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                c.getData().keySet().stream().forEach(CASEW1 -> {
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, WaAS_Wave2_Record> w2Records;
                        w2Records = cr.w2Records;
                        Short CASEW2;
                        Iterator<Short> ite2;
                        ite2 = w2Records.keySet().iterator();
                        while (ite2.hasNext()) {
                            CASEW2 = ite2.next();
                            Byte GOR = GORLookups[wave - 1].get(CASEW2);
                            WaAS_Wave2_HHOLD_Record w2;
                            w2 = w2Records.get(CASEW2).getHhold();
                            int HPRICE = w2.getHPRICE();
                            if (HPRICE < 0) {
                                byte HPRICEB = w2.getHPRICEB();
                                if (HPRICEB > 0) {
                                    HPRICE = Wave1Or2HPRICEBLookup.get(HPRICEB);
                                    Generic_Collections.addToMap(r, GOR, CASEW2, HPRICE);
                                }
                            } else {
                                Generic_Collections.addToMap(r, GOR, CASEW2, HPRICE);
                            }
                        }
                    }
                });
                data.clearCollection(cID);
            });
        } else if (wave == W3) {
            data.data.keySet().stream().forEach(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                c.getData().keySet().stream().forEach(CASEW1 -> {
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, WaAS_Wave3_Record>> w3Records;
                        w3Records = cr.w3Records;
                        Short CASEW2;
                        Short CASEW3;
                        Iterator<Short> ite1;
                        ite1 = w3Records.keySet().iterator();
                        while (ite1.hasNext()) {
                            CASEW2 = ite1.next();
                            HashMap<Short, WaAS_Wave3_Record> w3_2;
                            w3_2 = w3Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w3_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                Byte GOR = GORLookups[wave - 1].get(CASEW3);
                                WaAS_Wave3_HHOLD_Record w3;
                                w3 = w3_2.get(CASEW3).getHhold();
                                int HPRICE = w3.getHPRICE();
                                if (HPRICE < 0) {
                                    byte HPRICEB = w3.getHPRICEB();
                                    if (HPRICEB > 0) {
                                        HPRICE = Wave3Or4Or5HPRICEBLookup.get(HPRICEB);
                                        Generic_Collections.addToMap(r, GOR, CASEW3, HPRICE);
                                    }
                                } else {
                                    Generic_Collections.addToMap(r, GOR, CASEW3, HPRICE);
                                }
                            }
                        }
                    }
                });
                data.clearCollection(cID);
            });
        } else if (wave == W4) {
            data.data.keySet().stream().forEach(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                c.getData().keySet().stream().forEach(CASEW1 -> {
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave4_Record>>> w4Records;
                        w4Records = cr.w4Records;
                        Short CASEW2;
                        Short CASEW3;
                        Short CASEW4;
                        Iterator<Short> ite1;
                        ite1 = w4Records.keySet().iterator();
                        while (ite1.hasNext()) {
                            CASEW2 = ite1.next();
                            HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                            w4_2 = w4Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w4_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                HashMap<Short, WaAS_Wave4_Record> w4_3;
                                w4_3 = w4_2.get(CASEW3);
                                Iterator<Short> ite3;
                                ite3 = w4_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    CASEW4 = ite3.next();
                                    Byte GOR = GORLookups[wave - 1].get(CASEW4);
                                    WaAS_Wave4_HHOLD_Record w4;
                                    w4 = w4_3.get(CASEW4).getHhold();
                                    int HPRICE = w4.getHPRICE();
                                    if (HPRICE < 0) {
                                        byte HPRICEB = w4.getHPRICEB();
                                        if (HPRICEB > 0) {
                                            HPRICE = Wave3Or4Or5HPRICEBLookup.get(HPRICEB);
                                            Generic_Collections.addToMap(r, GOR, CASEW4, HPRICE);
                                        }
                                    } else {
                                        Generic_Collections.addToMap(r, GOR, CASEW4, HPRICE);
                                    }
                                }
                            }
                        }
                    }
                });
                data.clearCollection(cID);
            });
        } else if (wave == W5) {
            data.data.keySet().stream().forEach(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                c.getData().keySet().stream().forEach(CASEW1 -> {
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        HashMap<Short, HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>>> w5Records;
                        w5Records = cr.w5Records;
                        Short CASEW2;
                        Short CASEW3;
                        Short CASEW4;
                        Short CASEW5;
                        Iterator<Short> ite1;
                        ite1 = w5Records.keySet().iterator();
                        while (ite1.hasNext()) {
                            CASEW2 = ite1.next();
                            HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                            w5_2 = w5Records.get(CASEW2);
                            Iterator<Short> ite2;
                            ite2 = w5_2.keySet().iterator();
                            while (ite2.hasNext()) {
                                CASEW3 = ite2.next();
                                HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                                w5_3 = w5_2.get(CASEW3);
                                Iterator<Short> ite3;
                                ite3 = w5_3.keySet().iterator();
                                while (ite3.hasNext()) {
                                    CASEW4 = ite3.next();
                                    HashMap<Short, WaAS_Wave5_Record> w5_4;
                                    w5_4 = w5_3.get(CASEW4);
                                    Iterator<Short> ite4;
                                    ite4 = w5_4.keySet().iterator();
                                    while (ite4.hasNext()) {
                                        CASEW5 = ite4.next();
                                        Byte GOR = GORLookups[wave - 1].get(CASEW5);
                                        WaAS_Wave5_HHOLD_Record w5;
                                        w5 = w5_4.get(CASEW5).getHhold();
                                        int HPRICE = w5.getHPRICE();
                                        if (HPRICE < 0) {
                                            byte HPRICEB = w5.getHPRICEB();
                                            if (HPRICEB > 0) {
                                                HPRICE = Wave3Or4Or5HPRICEBLookup.get(HPRICEB);
                                                Generic_Collections.addToMap(r, GOR, CASEW5, HPRICE);
                                            }
                                        } else {
                                            Generic_Collections.addToMap(r, GOR, CASEW5, HPRICE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                data.clearCollection(cID);
            });
        }
        return r;
    }

    HashMap<Byte, Integer> Wave1Or2HPRICEBLookup;

    public HashMap<Byte, Integer> getWave1Or2HPRICEBLookup() {
//            Value = 1.0	Label = Less than £20,000
//            Value = 2.0	Label = £20,000 to £39,999
//            Value = 3.0	Label = £40,000 to £59,999
//            Value = 4.0	Label = £60,000 to £99,999
//            Value = 5.0	Label = £100,000 to £149,999
//            Value = 6.0	Label = £150,000 to £199,999
//            Value = 7.0	Label = £200,000 to £249,999
//            Value = 8.0	Label = £250,000 to £299,999
//            Value = 9.0	Label = £300,000 to £499,999
//            Value = 10.0	Label = £500,000 or more
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

    public HashMap<Byte, Integer> getWave3Or4Or5HPRICEBLookup() {
//	Value = 1.0	Label = Less than £60,000
//	Value = 2.0	Label = £60,000 to £99,999
//	Value = 3.0	Label = £100,000 to £149,999
//	Value = 4.0	Label = £150,000 to £199,999
//	Value = 5.0	Label = £200,000 to £249,999
//	Value = 6.0	Label = £250,000 to £299,999
//	Value = 7.0	Label = £300,000 to £349,999
//	Value = 8.0	Label = £350,000 to £399,999
//	Value = 9.0	Label = £400,000 to £499,999
//	Value = 10.0	Label = £500,000 to £749,999
//	Value = 11.0	Label = £750,000 to £999,999
//	Value = 12.0	Label = £1 million or more
//	Value = -9.0	Label = Does not know
//	Value = -8.0	Label = No answer
//	Value = -7.0	Label = Does not apply
//	Value = -6.0	Label = Error/Partial
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

    /**
     * Get the GOR Subsets for subset.
     *
     * @param subset
     * @param wave
     * @return
     */
    public Object[] getGORSubsetsAndLookup(HashSet<Short> subset) {
        Object[] r;
        r = new Object[2];
        HashMap<Byte, HashSet<Short>>[] r0;
        r0 = new HashMap[WaAS_Data.NWAVES];
        r[0] = r0;
        HashMap<Short, Byte>[] r1;
        r1 = new HashMap[WaAS_Data.NWAVES];
        for (byte w = 0; w < WaAS_Data.NWAVES; w++) {
            r0[w] = new HashMap<>();
            for (byte b = 1; b < 13; b++) {
                r0[w].put(b, new HashSet<>());
            }
            r1[w] = new HashMap<>();
        }
        // Wave 1
        data.data.keySet().stream().forEach(cID -> {
            WaAS_Collection c;
            c = data.getCollection(cID);
            c.getData().keySet().stream().forEach(CASEW1 -> {
                if (subset.contains(CASEW1)) {
                    WaAS_Combined_Record cr;
                    cr = c.getData().get(CASEW1);
                    byte GOR = cr.w1Record.getHhold().getGOR();
                    Generic_Collections.addToMap(r0[0], GOR, CASEW1);
                    r1[0].put(CASEW1, GOR);
                }
            });
            data.clearCollection(cID);
        });
        // Wave 2
        data.data.keySet().stream().forEach(cID -> {
            WaAS_Collection c;
            c = data.getCollection(cID);
            c.getData().keySet().stream().forEach(CASEW1 -> {
                if (subset.contains(CASEW1)) {
                    WaAS_Combined_Record cr;
                    cr = c.getData().get(CASEW1);
                    HashMap<Short, WaAS_Wave2_Record> w2Records;
                    w2Records = cr.w2Records;
                    Iterator<Short> ite2;
                    ite2 = w2Records.keySet().iterator();
                    while (ite2.hasNext()) {
                        Short CASEW2 = ite2.next();
                        WaAS_Wave2_Record w2;
                        w2 = w2Records.get(CASEW2);
                        byte GOR = w2.getHhold().getGOR();
                        Generic_Collections.addToMap(r0[1], GOR, CASEW2);
                        r1[1].put(CASEW2, GOR);
                    }
                }
            });
            data.clearCollection(cID);
        });
        // Wave 3
        data.data.keySet().stream().forEach(cID -> {
            WaAS_Collection c;
            c = data.getCollection(cID);
            c.getData().keySet().stream().forEach(CASEW1 -> {
                if (subset.contains(CASEW1)) {
                    WaAS_Combined_Record cr;
                    cr = c.getData().get(CASEW1);
                    HashMap<Short, HashMap<Short, WaAS_Wave3_Record>> w3Records;
                    w3Records = cr.w3Records;
                    Iterator<Short> ite2;
                    ite2 = w3Records.keySet().iterator();
                    while (ite2.hasNext()) {
                        Short CASEW2 = ite2.next();
                        HashMap<Short, WaAS_Wave3_Record> w3_2;
                        w3_2 = w3Records.get(CASEW2);
                        Iterator<Short> ite3;
                        ite3 = w3_2.keySet().iterator();
                        while (ite3.hasNext()) {
                            Short CASEW3 = ite3.next();
                            WaAS_Wave3_Record w3 = w3_2.get(CASEW3);
                            byte GOR = w3.getHhold().getGOR();
                            Generic_Collections.addToMap(r0[2], GOR, CASEW3);
                            r1[2].put(CASEW3, GOR);
                        }
                    }
                }
            });
            data.clearCollection(cID);
        });
        // Wave 4
        data.data.keySet().stream().forEach(cID -> {
            WaAS_Collection c;
            c = data.getCollection(cID);
            c.getData().keySet().stream().forEach(CASEW1 -> {
                if (subset.contains(CASEW1)) {
                    WaAS_Combined_Record cr;
                    cr = c.getData().get(CASEW1);
                    HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave4_Record>>> w4Records;
                    w4Records = cr.w4Records;
                    Iterator<Short> ite2;
                    ite2 = w4Records.keySet().iterator();
                    while (ite2.hasNext()) {
                        Short CASEW2 = ite2.next();
                        HashMap<Short, HashMap<Short, WaAS_Wave4_Record>> w4_2;
                        w4_2 = w4Records.get(CASEW2);
                        Iterator<Short> ite3;
                        ite3 = w4_2.keySet().iterator();
                        while (ite3.hasNext()) {
                            Short CASEW3 = ite3.next();
                            HashMap<Short, WaAS_Wave4_Record> w4_3;
                            w4_3 = w4_2.get(CASEW3);
                            Iterator<Short> ite4;
                            ite4 = w4_3.keySet().iterator();
                            while (ite4.hasNext()) {
                                Short CASEW4 = ite4.next();
                                WaAS_Wave4_Record w4 = w4_3.get(CASEW4);
                                byte GOR = w4.getHhold().getGOR();
                                Generic_Collections.addToMap(r0[3], GOR, CASEW4);
                                r1[3].put(CASEW4, GOR);
                            }
                        }
                    }
                }
            });
            data.clearCollection(cID);
        });
        // Wave 5
        data.data.keySet().stream().forEach(cID -> {
            WaAS_Collection c;
            c = data.getCollection(cID);
            c.getData().keySet().stream().forEach(CASEW1 -> {
                if (subset.contains(CASEW1)) {
                    WaAS_Combined_Record cr;
                    cr = c.getData().get(CASEW1);
                    HashMap<Short, HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>>> w5Records;
                    w5Records = cr.w5Records;
                    Iterator<Short> ite2;
                    ite2 = w5Records.keySet().iterator();
                    while (ite2.hasNext()) {
                        Short CASEW2 = ite2.next();
                        HashMap<Short, HashMap<Short, HashMap<Short, WaAS_Wave5_Record>>> w5_2;
                        w5_2 = w5Records.get(CASEW2);
                        Iterator<Short> ite3;
                        ite3 = w5_2.keySet().iterator();
                        while (ite3.hasNext()) {
                            Short CASEW3 = ite3.next();
                            HashMap<Short, HashMap<Short, WaAS_Wave5_Record>> w5_3;
                            w5_3 = w5_2.get(CASEW3);
                            Iterator<Short> ite4;
                            ite4 = w5_3.keySet().iterator();
                            while (ite4.hasNext()) {
                                Short CASEW4 = ite4.next();
                                HashMap<Short, WaAS_Wave5_Record> w5_4;
                                w5_4 = w5_3.get(CASEW4);
                                Iterator<Short> ite5;
                                ite5 = w5_4.keySet().iterator();
                                while (ite5.hasNext()) {
                                    Short CASEW5 = ite5.next();
                                    WaAS_Wave5_Record w5 = w5_4.get(CASEW5);
                                    byte GOR = w5.getHhold().getGOR();
                                    Generic_Collections.addToMap(r0[4], GOR, CASEW5);
                                    r1[4].put(CASEW5, GOR);
                                }
                            }
                        }
                    }
                }
            });
            data.clearCollection(cID);
        });
        return r;
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

    public static void logStart(String s) {
        s = "<" + s + ">";
        logPW.println(s);
        System.out.println(s);
    }

    public static void logEnd(String s) {
        s = "</" + s + ">";
        logPW.println(s);
        System.out.println(s);
    }

    boolean doJavaCodeGeneration = false;
    boolean doLoadDataIntoCaches = false;

}

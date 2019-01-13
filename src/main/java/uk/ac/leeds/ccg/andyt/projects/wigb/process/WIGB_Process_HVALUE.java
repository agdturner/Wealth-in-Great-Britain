/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.ccg.andyt.projects.wigb.process;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import uk.ac.leeds.ccg.andyt.generic.data.waas.core.WaAS_Strings;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Collection;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Combined_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Data;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_HHOLD_Handler;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Wave2_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Wave3_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Wave4_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.WaAS_Wave5_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave2_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave3_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave4_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.data.hhold.WaAS_Wave5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.generic.data.waas.io.WaAS_Files;
import uk.ac.leeds.ccg.andyt.generic.util.Generic_Collections;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import static uk.ac.leeds.ccg.andyt.projects.wigb.process.WIGB_Main_Process.log;

/**
 *
 * @author Andy Turner
 */
public class WIGB_Process_HVALUE extends WIGB_Main_Process {

    public WIGB_Process_HVALUE(WIGB_Main_Process p) {
        super(p);
    }

    /**
     * 
     */
    public void createGraph() {
    /**
         * Get HVALUE Total Household Property Wealth for each wave in the
         * subsets.
         */
        TreeMap<Byte, Double> changeHVALUESubset;
        changeHVALUESubset = getChangeHVALUESubset();

        /**
         * Get HVALUE Total Household Property Wealth for each wave for all
         * records.
         */
        TreeMap<Byte, Double> changeHVALUEAll;
        changeHVALUEAll = getChangeHVALUEAll();

        // Data to graph.
        log("Data to graph");
        log("GOR,GORName,changeHVALUESubset,changeHVALUEAll");
        Iterator<Byte> ite;
        ite = gors.iterator();
        while (ite.hasNext()) {
            byte gor = ite.next();
            if (gor != 3) {
                log("" + gor + "," + GORNameLookup.get(gor) + "," 
                        + changeHVALUESubset.get(gor) + "," 
                        + changeHVALUEAll.get(gor));
            }
        }

        // Graph data
        String title;
        String xAxisLabel;
        String yAxisLabel;
        title = "Average change in HVALUE (Wave 5 minus Wave 1)";
        xAxisLabel = "Government Office Region";
        yAxisLabel = "£";
        createLineGraph(title, xAxisLabel, yAxisLabel, gors, GORNameLookup,
                changeHVALUESubset, changeHVALUEAll);
    }
        
    /**
     * Get HVALUE Total Household Property Wealth for each wave in the subsets.
     *
     * @return
     */
    public TreeMap<Byte, Double> getChangeHVALUESubset() {
        TreeMap<Byte, Double> r;
        HashMap<Byte, HashMap<Short, Double>>[] HVALUESubsets;
        HVALUESubsets = new HashMap[WaAS_Data.NWAVES];
        byte w;
        for (w = 0; w < WaAS_Data.NWAVES; w++) {
            HVALUESubsets[w] = getHVALUEForGORSubsets((byte) (w + 1));
        }
        r = new TreeMap<>();
        double countW1 = 0;
        double countZeroW1 = 0;
        double countNegativeW1 = 0;
        double countW5 = 0;
        double countZeroW5 = 0;
        double countNegativeW5 = 0;
        log("HVALUE for each wave in the subsets.");
        String h = "GORNumber,GORName,HVALUE5_Average-HVALUE1_Average";
        for (w = 1; w < WaAS_Data.NWAVES + 1; w++) {
            h += ",HVALUEW" + w + "_Count,HVALUEW" + w + "_ZeroCount,HVALUEW"
                    + w + "_NegativeCount,HVALUEW" + w + "_Average";
        }
        log(h);
        Iterator<Byte> ite;
        ite = gors.iterator();
        while (ite.hasNext()) {
            byte gor = ite.next();
            double[][] aHVALUE = new double[WaAS_Data.NWAVES][];
            for (w = 0; w < WaAS_Data.NWAVES; w++) {
                aHVALUE[w] = getSummaryStatistics(
                        HVALUESubsets[w].get(gor).values());
            }
            countW1 += aHVALUE[0][5];
            countZeroW1 += aHVALUE[0][6];
            countNegativeW1 += aHVALUE[0][7];
            countW5 += aHVALUE[4][5];
            countZeroW5 += aHVALUE[4][6];
            countNegativeW5 += aHVALUE[4][7];
            double diff = aHVALUE[4][4] - aHVALUE[0][4];
            String s;
            s = "" + gor + "," + GORNameLookup.get(gor) + "," + diff;
            for (w = 0; w < WaAS_Data.NWAVES; w++) {
                s += "," + aHVALUE[w][4] + "," + aHVALUE[w][5] + ","
                        + aHVALUE[w][6] + "," + aHVALUE[w][7];
            }
            log(s);
            r.put(gor, diff);
        }
        log("HVALUE For Wave 1 Subset");
        log("" + countW1 + "\t Count");
        log("" + countZeroW1 + "\t Zero");
        log("" + countNegativeW1 + "\t Negative");
        log("HVALUE For Wave 5 Subset");
        log("" + countW5 + "\t Count");
        log("" + countZeroW5 + "\t Zero");
        log("" + countNegativeW5 + "\t Negative");
        return r;
    }

    /**
     * Get the total HVALUE in subset.
     *
     * @param wave
     * @return Map with keys as GOR and Values as map with keys as CASEWX and
     * values as HVALUE.
     */
    public HashMap<Byte, HashMap<Short, Double>> getHVALUEForGORSubsets(
            byte wave) {
        // Initialise result
        HashMap<Byte, HashMap<Short, Double>> r;
        r = new HashMap<>();
        Iterator<Byte> ite;
        ite = GORSubsets[wave - 1].keySet().iterator();
        while (ite.hasNext()) {
            Byte GOR;
            GOR = ite.next();
            r.put(GOR, new HashMap<>());
        }
        if (wave == W1) {
            data.data.keySet().stream().forEach(cID -> {
                WaAS_Collection c;
                c = data.getCollection(cID);
                c.getData().keySet().stream().forEach(CASEW1 -> {
                    if (subset.contains(CASEW1)) {
                        WaAS_Combined_Record cr;
                        cr = c.getData().get(CASEW1);
                        WaAS_Wave1_HHOLD_Record w1;
                        w1 = cr.w1Record.getHhold();
                        Byte GOR = GORLookups[wave - 1].get(CASEW1);
                        Generic_Collections.addToMap(r, GOR, CASEW1, 
                                w1.getHVALUE());
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
                            Generic_Collections.addToMap(r, GOR, CASEW2, 
                                    w2.getHVALUE());
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
                                Generic_Collections.addToMap(r, GOR, CASEW3, 
                                        w3.getHVALUE());
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
                                    Generic_Collections.addToMap(r, GOR, CASEW4, w4.getHVALUE());
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
                                        Generic_Collections.addToMap(r, GOR, CASEW5, w5.getHVALUE());
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

    /**
     * Get the HVALUE.
     *
     * @param gors
     * @param w5All
     * @param wave
     * @return Map with keys as GOR and Values as map with keys as CASEWX and
     * values as HVALUE.
     */
    public HashMap<Byte, HashMap<Short, Double>> getHVALUEForGOR(
            ArrayList<Byte> gors,
            TreeMap<Short, ?> w5All,
            //TreeMap<Short, WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> w5All, 
            byte wave) {
        // Initialise result
        HashMap<Byte, HashMap<Short, Double>> r;
        r = new HashMap<>();
        gors.stream().forEach(gor -> {
            r.put(gor, new HashMap<>());
        });
        int countNegative = 0;
        int countZero = 0;
        Iterator<Short> ite = w5All.keySet().iterator();
        Short CASEWX;
        WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record rec;
        Byte GOR;
        double HVALUE;
        while (ite.hasNext()) {
            CASEWX = ite.next();
            rec = (WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record) w5All.get(CASEWX);
            GOR = rec.getGOR();
            HVALUE = rec.getHVALUE();
            if (HVALUE == 0.0d) {
                countZero++;
            } else if (HVALUE < 0.0d) {
                countNegative++;
            }
            Generic_Collections.addToMap(r, GOR, CASEWX, HVALUE);
        }
        log("HVALUE for GOR W" + wave);
        log("count " + w5All.size());
        log("countZero " + countZero);
        log("countNegative " + countNegative);
        return r;
    }

    /**
     * Get HVALUE Total Household Property Wealth for each wave for all records.
     *
     * @return
     */
    public TreeMap<Byte, Double> getChangeHVALUEAll() {
        TreeMap<Byte, Double> r;
        r = new TreeMap<>();
        WaAS_Strings tWaAS_Strings;
        tWaAS_Strings = new WaAS_Strings();
        WaAS_Files tWaAS_Files;
        tWaAS_Files = new WaAS_Files(tWaAS_Strings);
        WaAS_HHOLD_Handler handler;
        File inDir = Files.getGeneratedWaASDir();
        handler = new WaAS_HHOLD_Handler(tWaAS_Files, tWaAS_Strings, inDir);
        HashMap<Byte, HashMap<Short, Double>>[] HVALUEAll;
        HVALUEAll = new HashMap[WaAS_Data.NWAVES];
        TreeMap<Short, WaAS_Wave1_HHOLD_Record> allW1 = handler.loadAllWave1(WaAS_Data.W1);
        HVALUEAll[0] = getHVALUEForGOR(gors, allW1, (byte) 1);
        allW1 = null; // Set to null to free memory.
        TreeMap<Short, WaAS_Wave5_HHOLD_Record> allW5 = handler.loadAllWave5(WaAS_Data.W5);
        HVALUEAll[4] = getHVALUEForGOR(gors, allW5, (byte) 5);
        allW5 = null; // Set to null to free memory.
        log("HVALUE Total Household Property Wealth for each wave for all records.");
        String h;
        h = "GORNumber,GORName,HVALUE5_Average-HVALUE1_Average";
        byte w;
        for (w = 1; w < WaAS_Data.NWAVES + 1; w++) {
            if (w == 1 || w == 5) {
                h += ",HVALUEW" + w + "_Count,HVALUEW" + w + "_ZeroCount,HVALUEW"
                        + w + "_NegativeCount,HVALUEW" + w + "_Average";
            }
        }
        log(h);
        Iterator<Byte> ite;
        ite = gors.iterator();
        while (ite.hasNext()) {
            byte gor = ite.next();
            double[][] aHVALUE = new double[WaAS_Data.NWAVES][];
            for (w = 0; w < WaAS_Data.NWAVES; w++) {
                if (w == 0 || w == 4) {
                    aHVALUE[w] = getSummaryStatistics(
                            HVALUEAll[w].get(gor).values());
                }
            }
            double diff = aHVALUE[4][4] - aHVALUE[0][4];
            String s;
            s = "" + gor + "," + GORNameLookup.get(gor) + "," + diff;
            for (w = 0; w < WaAS_Data.NWAVES; w++) {
                if (w == 0 || w == 4) {
                    s += "," + aHVALUE[w][4] + "," + aHVALUE[w][5] + ","
                            + aHVALUE[w][6] + "," + aHVALUE[w][7];
                }
            }
            log(s);
            r.put(gor, diff);
        }
        return r;
    }
}

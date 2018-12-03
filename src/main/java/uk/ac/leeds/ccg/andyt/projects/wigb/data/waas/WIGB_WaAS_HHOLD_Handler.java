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

import java.io.BufferedReader;
import java.io.File;
import java.io.StreamTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_ReadCSV;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave2_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave3_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave4_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave5_HHOLD_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_HHOLD_Handler extends WIGB_WaAS_Handler {

    public WIGB_WaAS_HHOLD_Handler(WIGB_Environment env, File indir) {
        super(env);
        TYPE = "hhold";
        INDIR = indir;
    }

    /**
     * Loads all hhold WaAS from a cache in generated data if it exists and from
     * the source input data otherwise. It requires more than 4GB, but less than
     * 7GB of memory to hold all the data in memory.
     *
     * @return an Object[] r with size 5. Each element is an Object[] containing
     * the data from loading each wave @see load(File,int). r[0] is the data
     * from Wave 1, r[1] is the data from Wave 2 etc...
     */
    public Object[] load() {
        Object[] r;
        r = new Object[5];
        for (byte wave = 1; wave < 6; wave++) {
            r[wave - 1] = load(wave);
        }
        return r;
    }

    /**
     * Loads a specific wave of the hhold WaAS from f. The result r is an
     * Object[] of length 2. r[0] is a HashMap with Integer keys which are the
     * CASE id for the wave and the values are
     * WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record>. r[1] is an array of HashSets
     * where: For Wave 5; r[1][0] is a list of CASEW5 values, r[1][1] is a list
     * of CASEW4 values, r[1][2] is a list of CASEW3 values, r[1][3] is a list
     * of CASEW2 values, r[1][4] is a list of CASEW1 values. For Wave 4; r[1][0]
     * is a list of CASEW4 values, r[1][1] is a list of CASEW3 values, r[1][2]
     * is a list of CASEW2 values, r[1][3] is a list of CASEW1 values. For Wave
     * 3; r[1][0] is a list of CASEW3 values, r[1][1] is a list of CASEW2
     * values, r[1][2] is a list of CASEW1 values. For Wave 2; r[1][0] is a list
     * of CASEW2 values, r[1][1] is a list of CASEW1 values. For Wave 1: r[1][0]
     * is a list of CASEW1 values.
     *
     * @param wave
     * @return
     */
    public Object[] load(byte wave) {
        Object[] r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        File cf;
        cf = new File(dir, TYPE + wave + "." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (Object[]) loadCache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1Or2Or3Or4Or5_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<WIGB_WaAS_ID>[] r1;
            r1 = new TreeSet[wave];
            for (int i = 0; i < wave; i++) {
                r1[i] = new TreeSet<>();
            }
            r[1] = r1;
            System.out.println("<Loading wave " + wave + " " + TYPE + " WaAS "
                    + "data from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            StreamTokenizer st;
            st = new StreamTokenizer(br);
            Generic_StaticIO.setStreamTokenizerSyntax7(st);
            int lineNumber;
            lineNumber = 0;
            String line;

            // skip header
            Generic_ReadCSV.readLine(st, null);

            boolean read;
            read = false;

            while (!read) {
                line = Generic_ReadCSV.readLine(st, null);
                lineNumber++;
                if (lineNumber % 1000 == 0) {
                    System.out.println("loaded " + lineNumber + " lines...");
                }
                if (line == null) {
                    read = true;
                } else {
                    switch (wave) {
                        case 5: {
                            WIGB_WaAS_Wave5_HHOLD_Record rec;
                            try {
                                rec = new WIGB_WaAS_Wave5_HHOLD_Record(line);
                                WIGB_WaAS_ID ID;
                                short CASEW5 = rec.getCASEW5();
                                short CASEW4 = rec.getCASEW4();
                                short CASEW3 = rec.getCASEW3();
                                short CASEW2 = rec.getCASEW2();
                                short CASEW1 = rec.getCASEW1();
                                if (CASEW1 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW5);
                                    r0.put(ID, rec);
                                    r1[0].add(ID);
                                }
                                if (CASEW4 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW4);
                                    r1[1].add(ID);
                                }
                                if (CASEW3 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW3);
                                    r1[2].add(ID);
                                }
                                if (CASEW2 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW2);
                                    r1[3].add(ID);
                                }
                                if (CASEW1 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW1);
                                    r1[4].add(ID);
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_WaAS_Wave5_HHOLD_Record(line); // For debugging
                            }
                            break;
                        }
                        case 4: {
                            WIGB_WaAS_Wave4_HHOLD_Record rec;
                            try {
                                rec = new WIGB_WaAS_Wave4_HHOLD_Record(line);
                                WIGB_WaAS_ID ID;
                                short CASEW4 = rec.getCASEW4();
                                short CASEW3 = rec.getCASEW3();
                                short CASEW2 = rec.getCASEW2();
                                short CASEW1 = rec.getCASEW1();
                                if (CASEW1 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW4);
                                    r0.put(ID, rec);
                                    r1[0].add(ID);
                                }
                                if (CASEW3 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW3);
                                    r1[1].add(ID);
                                }
                                if (CASEW2 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW2);
                                    r1[2].add(ID);
                                }
                                if (CASEW1 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW1);
                                    r1[3].add(ID);
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_WaAS_Wave4_HHOLD_Record(line); // For debugging
                            }
                            break;
                        }
                        case 3: {
                            WIGB_WaAS_Wave3_HHOLD_Record rec;
                            try {
                                rec = new WIGB_WaAS_Wave3_HHOLD_Record(line);
                                WIGB_WaAS_ID ID;
                                short CASEW3 = rec.getCASEW3();
                                short CASEW2 = rec.getCASEW2();
                                short CASEW1 = rec.getCASEW1();
                                if (CASEW1 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW3);
                                    r0.put(ID, rec);
                                    r1[0].add(ID);
                                }
                                if (CASEW2 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW2);
                                    r1[1].add(ID);
                                }
                                if (CASEW1 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW1);
                                    r1[2].add(ID);
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_WaAS_Wave3_HHOLD_Record(line); // For debugging
                            }
                            break;
                        }
                        case 2: {
                            WIGB_WaAS_Wave2_HHOLD_Record rec;
                            try {
                                rec = new WIGB_WaAS_Wave2_HHOLD_Record(line);
                                WIGB_WaAS_ID ID;
                                short CASEW2 = rec.getCASEW2();
                                short CASEW1 = rec.getCASEW1();
                                if (CASEW1 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW2);
                                    r0.put(ID, rec);
                                    r1[0].add(ID);
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW1);
                                    r1[1].add(ID);
                                }
                            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_WaAS_Wave2_HHOLD_Record(line); // For debugging
                            }
                            // For debugging
                            break;
                        }
                        case 1: {
                            WIGB_WaAS_Wave1_HHOLD_Record rec;
                            try {
                                rec = new WIGB_WaAS_Wave1_HHOLD_Record(line);
                                WIGB_WaAS_ID ID;
                                short CASEW1 = rec.getCASEW1();
                                if (CASEW1 > Short.MIN_VALUE) {
                                    ID = new WIGB_WaAS_ID(CASEW1, CASEW1);
                                    r0.put(ID, rec);
                                    r1[0].add(ID);
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_WaAS_Wave1_HHOLD_Record(line); // For debugging
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
            System.out.println("</Loading wave " + wave + " " + TYPE + " WaAS "
                    + "data from " + f + ">");
            storeCache(wave, cf, r);
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record> loadCacheSubsetWave1() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "1." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record>) Generic_StaticIO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record> loadCacheSubsetWave2() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "2." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record>) Generic_StaticIO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record> loadCacheSubsetWave3() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "3." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record>) Generic_StaticIO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record> loadCacheSubsetWave4() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "4." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record>) Generic_StaticIO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record> loadCacheSubsetWave5() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "5." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record>) Generic_StaticIO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }
}

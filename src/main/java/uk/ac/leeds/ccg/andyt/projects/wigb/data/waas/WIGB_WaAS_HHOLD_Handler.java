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
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
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
     * the data from loading each wave...
     */
    public Object[] load() {
        Object[] r;
        r = new Object[5];
        r[4] = loadWave5(WIGB_WaAS_Data.W5);
        TreeSet<WIGB_WaAS_ID>[] sa = (TreeSet<WIGB_WaAS_ID>[]) ((Object[]) r[4])[1];
        TreeSet<WIGB_WaAS_ID> s;
        s = sa[sa.length - 1];
        r[3] = loadWave4(WIGB_WaAS_Data.W4, s);
        r[2] = loadWave3(WIGB_WaAS_Data.W3, s);
        r[1] = loadWave2(WIGB_WaAS_Data.W2, s);
        r[0] = loadWave1(WIGB_WaAS_Data.W1, s);
        return r;
    }

    /**
     * Load Wave 5 household records that are reportedly in all 5 waves and some
     * sets of WIGB_WaAS_ID for those records in multiple waves.
     *
     * @param wave
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW1 and values as WIGB_WaAS_Wave5_HHOLD_Records. r[1] is an array of
     * TreeSet where: r[1][0] is a list of CASEW5 values, r[1][1] is a list of
     * CASEW4 values, r[1][2] is a list of CASEW3 values, r[1][3] is a list of
     * CASEW2 values, r[1][4] is a list of CASEW1 values.
     */
    public Object[] loadWave5(byte wave) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<WIGB_WaAS_ID>[] r1;
            r1 = new TreeSet[wave];
            for (int i = 0; i < wave; i++) {
                r1[i] = new TreeSet<>();
            }
            r[1] = r1;
            System.out.println("<load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave5_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave5_HHOLD_Record(line);
                        WIGB_WaAS_ID ID;
                        short CASEW5 = rec.getCASEW5();
                        short CASEW4 = rec.getCASEW4();
                        short CASEW3 = rec.getCASEW3();
                        short CASEW2 = rec.getCASEW2();
                        short CASEW1 = rec.getCASEW1();
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
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW5);
                            r1[0].add(ID);
                            WIGB_WaAS_ID ID2;
                            ID2 = new WIGB_WaAS_ID(CASEW1, CASEW1);
                            r1[4].add(ID2);
                            if (CASEW2 > Short.MIN_VALUE
                                    && CASEW3 > Short.MIN_VALUE
                                    && CASEW4 > Short.MIN_VALUE) {
                                r0.put(ID, rec);
                            }
                        }
                    });
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(WIGB_WaAS_HHOLD_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("</load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    protected File getGeneratedFile(short wave) {
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        File f;
        f = new File(dir, TYPE + wave + "." + Env.Strings.S_dat);
        return f;
    }

    /**
     * Load Wave 4 household records that are reportedly in all 5 waves and some
     * sets of WIGB_WaAS_ID for those records in multiple waves.
     *
     * @param wave
     * @param s A set of CASEW1 values for all CASEW1 in Wave 5.
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW1 and values as WIGB_WaAS_Wave4_HHOLD_Records. r[1] is an array of
     * TreeSet where: r[1][0] is a list of CASEW4 values, r[1][1] is a list of
     * CASEW3 values, r[1][2] is a list of CASEW2 values, r[1][3] is a list of
     * CASEW1 values.
     */
    public Object[] loadWave4(byte wave, Set<WIGB_WaAS_ID> s) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<WIGB_WaAS_ID>[] r1;
            r1 = new TreeSet[wave];
            for (int i = 0; i < wave; i++) {
                r1[i] = new TreeSet<>();
            }
            r[1] = r1;
            System.out.println("<load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave4_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave4_HHOLD_Record(line);
                        WIGB_WaAS_ID ID;
                        short CASEW4 = rec.getCASEW4();
                        short CASEW3 = rec.getCASEW3();
                        short CASEW2 = rec.getCASEW2();
                        short CASEW1 = rec.getCASEW1();
                        if (CASEW3 > Short.MIN_VALUE) {
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW3);
                            r1[1].add(ID);
                        }
                        if (CASEW2 > Short.MIN_VALUE) {
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW2);
                            r1[2].add(ID);
                        }
                        if (CASEW1 > Short.MIN_VALUE) {
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW4);
                            r1[0].add(ID);
                            WIGB_WaAS_ID ID2;
                            ID2 = new WIGB_WaAS_ID(CASEW1, CASEW1);
                            r1[3].add(ID2);
                            if (CASEW2 > Short.MIN_VALUE
                                    && CASEW3 > Short.MIN_VALUE) {
                                if (s.contains(ID2)) {
                                    r0.put(ID, rec);
                                }
                            }
                        }
                    });
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(WIGB_WaAS_HHOLD_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("</load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Load Wave 3 household records that are reportedly in all 5 waves and some
     * sets of WIGB_WaAS_ID for those records in multiple waves.
     *
     * @param wave
     * @param s A set of CASEW1 values for all CASEW1 in Wave 5.
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW1 and values as WIGB_WaAS_Wave3_HHOLD_Records. r[1] is an array of
     * TreeSet where: r[1][0] is a list of CASEW3 values, r[1][1] is a list of
     * CASEW2 values, r[1][2] is a list of CASEW1 values.
     */
    public Object[] loadWave3(byte wave, Set<WIGB_WaAS_ID> s) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<WIGB_WaAS_ID>[] r1;
            r1 = new TreeSet[wave];
            for (int i = 0; i < wave; i++) {
                r1[i] = new TreeSet<>();
            }
            r[1] = r1;
            System.out.println("<load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave3_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave3_HHOLD_Record(line);
                        WIGB_WaAS_ID ID;
                        short CASEW3 = rec.getCASEW3();
                        short CASEW2 = rec.getCASEW2();
                        short CASEW1 = rec.getCASEW1();
                        if (CASEW2 > Short.MIN_VALUE) {
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW2);
                            r1[1].add(ID);
                        }
                        if (CASEW1 > Short.MIN_VALUE) {
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW3);
                            r1[0].add(ID);
                            WIGB_WaAS_ID ID2;
                            ID2 = new WIGB_WaAS_ID(CASEW1, CASEW1);
                            r1[2].add(ID2);
                            if (CASEW2 > Short.MIN_VALUE) {
                                if (s.contains(ID2)) {
                                    r0.put(ID, rec);
                                }
                            }
                        }
                    });
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(WIGB_WaAS_HHOLD_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("</load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Load Wave 2 household records that are reportedly in all 5 waves and some
     * sets of WIGB_WaAS_ID for those records in multiple waves.
     *
     * @param wave
     * @param s A set of CASEW1 values for all CASEW1 in Wave 5.
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW1 and values as WIGB_WaAS_Wave2_HHOLD_Records. r[1] is an array of
     * TreeSet where: r[1][0] is a list of CASEW2 values, r[1][1] is a list of
     * CASEW2 values.
     */
    public Object[] loadWave2(byte wave, Set<WIGB_WaAS_ID> s) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<WIGB_WaAS_ID>[] r1;
            r1 = new TreeSet[wave];
            for (int i = 0; i < wave; i++) {
                r1[i] = new TreeSet<>();
            }
            r[1] = r1;
            System.out.println("<load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave2_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave2_HHOLD_Record(line);
                        WIGB_WaAS_ID ID;
                        short CASEW2 = rec.getCASEW2();
                        short CASEW1 = rec.getCASEW1();
                        if (CASEW1 > Short.MIN_VALUE) {
                            WIGB_WaAS_ID ID2;
                            ID2 = new WIGB_WaAS_ID(CASEW1, CASEW2);
                            r1[0].add(ID2);
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW1);
                            r1[1].add(ID);
                            if (CASEW2 > Short.MIN_VALUE) {
                                if (s.contains(ID)) {
                                    r0.put(ID2, rec);
                                }
                            }
                        }
                    });
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(WIGB_WaAS_HHOLD_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("</load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Load Wave 2 household records that are reportedly in all 5 waves and some
     * sets of WIGB_WaAS_ID for those records in multiple waves.
     *
     * @param wave
     * @param s A set of CASEW1 values for all CASEW1 in Wave 5.
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW1 and values as WIGB_WaAS_Wave1_HHOLD_Records. r[1] is an array of
     * TreeSet where: r[1][0] is a list of CASEW1 values.
     */
    public Object[] loadWave1(byte wave, Set<WIGB_WaAS_ID> s) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<WIGB_WaAS_ID>[] r1;
            r1 = new TreeSet[wave];
            for (int i = 0; i < wave; i++) {
                r1[i] = new TreeSet<>();
            }
            r[1] = r1;
            System.out.println("<load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave1_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave1_HHOLD_Record(line);
                        WIGB_WaAS_ID ID;
                        short CASEW1 = rec.getCASEW1();
                        if (CASEW1 > Short.MIN_VALUE) {
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW1);
                            r1[0].add(ID);
                            if (s.contains(ID)) {
                                r0.put(ID, rec);
                            }
                        }
                    });
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(WIGB_WaAS_HHOLD_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("</load wave " + wave + " " + TYPE + " WaAS "
                    + "from " + f + ">");
            cache(wave, cf, r);
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

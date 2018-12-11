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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave2_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave3_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave4_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_WaAS_Wave5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.process.WIGB_Main_Process;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_HHOLD_Handler extends WIGB_WaAS_Handler {

    public WIGB_WaAS_HHOLD_Handler(WIGB_Files Files, WIGB_Strings Strings, File indir) {
        super(Files, Strings);
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
     * Loads all hhold WaAS from a cache in generated data if it exists and from
     * the source input data otherwise. It requires more than 4GB, but less than
     * 7GB of memory to hold all the data in memory.
     *
     * @return an Object[] r with size 5. Each element is an Object[] containing
     * the data from loading each wave...
     */
    public Object[] loadNew() {
        Object[] r;
        r = new Object[5];
        r[4] = loadWave5New(WIGB_WaAS_Data.W5);
        TreeSet<Short> s;
        s = ((TreeSet<Short>[]) ((Object[]) r[4])[1])[3];
        r[3] = loadWave4New(WIGB_WaAS_Data.W4, s);
        s = ((TreeSet<Short>[]) ((Object[]) r[3])[1])[2];
        r[2] = loadWave3New(WIGB_WaAS_Data.W3, s);
        s = ((TreeSet<Short>[]) ((Object[]) r[2])[1])[1];
        r[1] = loadWave2New(WIGB_WaAS_Data.W2, s);
        s = ((TreeSet<Short>[]) ((Object[]) r[1])[1])[0];
        r[0] = loadWave1New(WIGB_WaAS_Data.W1, s);
        s = (TreeSet<Short>) ((Object[]) r[0])[1];
        short CASEW2;
        Iterator<Entry<Short, WIGB_WaAS_Wave2_HHOLD_Record>> ite;
        Entry<Short, WIGB_WaAS_Wave2_HHOLD_Record> e;
        TreeMap<Short, WIGB_WaAS_Wave2_HHOLD_Record> w2filtered;
        w2filtered = new TreeMap<>();
        TreeMap<Short, WIGB_WaAS_Wave2_HHOLD_Record> w2;
        w2 = (TreeMap<Short, WIGB_WaAS_Wave2_HHOLD_Record>) ((Object[]) r[1])[0];
        ite = w2.entrySet().iterator();
        while (ite.hasNext()) {
            e = ite.next();
            CASEW2 = e.getKey();
            if (s.contains(CASEW2)) {
                w2filtered.put(CASEW2, e.getValue());
            }
        }
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
            r1 = getSets(wave);
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
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
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Load Wave 5 records that are reportedly in Wave 4.
     *
     * @param wave
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW5 and values as WIGB_WaAS_Wave5_HHOLD_Records. r[1] is an array of
     * TreeSets where: r[1][0] is a list of CASEW1 values, r[1][1] is a list of
     * CASEW2 values, r[1][2] is a list of CASEW3 values, r[1][3] is a list of
     * CASEW4 values.
     */
    public Object[] loadWave5New(byte wave) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<Short, WIGB_WaAS_Wave5_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<Short>[] r1;
            r1 = getSetsNew(wave - 1);
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave5_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave5_HHOLD_Record(line);
                        short CASEW5 = rec.getCASEW5();
                        short CASEW4 = rec.getCASEW4();
                        short CASEW3 = rec.getCASEW3();
                        short CASEW2 = rec.getCASEW2();
                        short CASEW1 = rec.getCASEW1();
                        if (CASEW4 > Short.MIN_VALUE) {
                            r0.put(CASEW5, rec);
                            if (r1[3].add(CASEW4)) {
                                WIGB_Main_Process.log("In Wave 5: household "
                                        + "with CASEW4 " + CASEW4 + " has "
                                        + "reportedly split into multiple "
                                        + "households.");
                            }
                        }
                        if (CASEW3 > Short.MIN_VALUE) {
                            r1[2].add(CASEW3);
                        }
                        if (CASEW2 > Short.MIN_VALUE) {
                            r1[1].add(CASEW2);
                        }
                        if (CASEW1 > Short.MIN_VALUE) {
                            r1[0].add(CASEW1);
                        }
                    });
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    public String getMessage(byte wave, File f) {
        return "load wave " + wave + " " + TYPE + " WaAS from " + f;
    }

    /**
     *
     * @param wave
     * @return
     */
    protected TreeSet<WIGB_WaAS_ID>[] getSets(byte wave) {
        TreeSet<WIGB_WaAS_ID>[] r;
        r = new TreeSet[wave];
        for (int i = 0; i < wave; i++) {
            r[i] = new TreeSet<>();
        }
        return r;
    }

    /**
     *
     * @param n
     * @return
     */
    protected TreeSet<Short>[] getSetsNew(int n) {
        TreeSet<Short>[] r;
        r = new TreeSet[n];
        for (int i = 0; i < n; i++) {
            r[i] = new TreeSet<>();
        }
        return r;
    }

    protected File getGeneratedFile(short wave) {
        File dir;
        dir = Files.getGeneratedWaASDir();
        File f;
        f = new File(dir, TYPE + wave + "." + Strings.S_dat);
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
            r1 = getSets(wave);
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
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
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Load Wave 4 records (that have Wave 5 records) that are reportedly in
     * Wave 3.
     *
     * @param wave
     * @param s A set containing CASEW4 values from Wave 5.
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW5 and values as WIGB_WaAS_Wave5_HHOLD_Records. r[1] is an array of
     * TreeSets where: r[1][0] is a list of CASEW1 values, r[1][1] is a list of
     * CASEW2 values, r[1][2] is a list of CASEW3 values.
     */
    public Object[] loadWave4New(byte wave, Set<Short> s) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<Short, WIGB_WaAS_Wave4_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<Short>[] r1;
            r1 = getSetsNew(wave - 1);
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave4_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave4_HHOLD_Record(line);
                        short CASEW4 = rec.getCASEW4();
                        short CASEW3 = rec.getCASEW3();
                        short CASEW2 = rec.getCASEW2();
                        short CASEW1 = rec.getCASEW1();
                        if (s.contains(CASEW4)) {
                            if (CASEW3 > Short.MIN_VALUE) {
                                r0.put(CASEW4, rec);
                                r1[2].add(CASEW3);
                            }
                        }
                        if (CASEW2 > Short.MIN_VALUE) {
                            r1[1].add(CASEW2);
                        }
                        if (CASEW1 > Short.MIN_VALUE) {
                            r1[0].add(CASEW1);
                        }
                    });
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
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
            r1 = getSets(wave);
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
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
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Load Wave 3 records (that have Wave 5 and Wave 4 records) that are
     * reportedly in Wave 2.
     *
     * @param wave
     * @param s A set containing CASEW3 values from Wave 4 (for Wave 4 records
     * that have a Wave 5 record).
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW5 and values as WIGB_WaAS_Wave5_HHOLD_Records. r[1] is an array of
     * TreeSets where: r[1][0] is a list of CASEW1 values, r[1][1] is a list of
     * CASEW2 values.
     */
    public Object[] loadWave3New(byte wave, Set<Short> s) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<Short, WIGB_WaAS_Wave3_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<Short>[] r1;
            r1 = getSetsNew(wave - 1);
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave3_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave3_HHOLD_Record(line);
                        short CASEW3 = rec.getCASEW3();
                        short CASEW2 = rec.getCASEW2();
                        short CASEW1 = rec.getCASEW1();
                        if (s.contains(CASEW3)) {
                            if (CASEW2 > Short.MIN_VALUE) {
                                r0.put(CASEW3, rec);
                                r1[1].add(CASEW3);
                            }
                        }
                        if (CASEW1 > Short.MIN_VALUE) {
                            r1[0].add(CASEW1);
                        }
                    });
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
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
            r1 = getSets(wave);
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
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
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Load Wave 2 records (that have Wave 5, Wave 4 and Wave 3 records) that
     * are reportedly in Wave 1.
     *
     * @param wave
     * @param s A set containing CASEW2 values from Wave 3 (for Wave 3 records
     * that have a Wave 4 record that have a Wave 5 record).
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW5 and values as WIGB_WaAS_Wave5_HHOLD_Records. r[1] is an array of
     * TreeSets where: r[1][0] is a list of CASEW1 values.
     */
    public Object[] loadWave2New(byte wave, Set<Short> s) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<Short, WIGB_WaAS_Wave2_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<Short>[] r1;
            r1 = getSetsNew(wave - 1);
            r[1] = r1;
            
            TreeMap<Short, Short> CASEW2ToCASEW1;
            TreeMap<Short, HashSet<Short>> CASEW1ToCASEW2;
            dsa
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave2_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave2_HHOLD_Record(line);
                        short CASEW2 = rec.getCASEW2();
                        short CASEW1 = rec.getCASEW1();
                        if (s.contains(CASEW2)) {
                            if (CASEW1 > Short.MIN_VALUE) {
                                r0.put(CASEW2, rec);
                                r1[0].add(CASEW2);
                            }
                        }
                    });
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
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
            r1 = getSets(wave);
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
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
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Load Wave 1 records (that have Wave 5, Wave 4, Wave 3 and Wave 2
     * records).
     *
     * @param wave
     * @param s A set containing CASEW1 values from Wave 2 (for Wave 2 records
     * that have a Wave 3 records that have a Wave 4 record that have a Wave 5
     * record).
     *
     * @return r - an Object[] of length 2. r[0] is a TreeMap with keys as
     * CASEW5 and values as WIGB_WaAS_Wave5_HHOLD_Records. r[1] is a TreeSet of
     * CASEW2 that are definitely needed.
     */
    public Object[] loadWave1New(byte wave, Set<Short> s) {
        Object[] r;
        File cf;
        cf = getGeneratedFile(wave);
        if (cf.exists()) {
            r = (Object[]) cache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            TreeMap<Short, WIGB_WaAS_Wave1_HHOLD_Record> r0;
            r0 = new TreeMap<>();
            r[0] = r0;
            TreeSet<Short> r1;
            r1 = new TreeSet<>();
            r[1] = r1;
            String m;
            m = getMessage(wave, f);
            WIGB_Main_Process.log("<" + m + ">");
            BufferedReader br;
            br = Generic_IO.getBufferedReader(f);
            br.lines()
                    .skip(1) // Skip header
                    .forEach(line -> {
                        WIGB_WaAS_Wave1_HHOLD_Record rec;
                        rec = new WIGB_WaAS_Wave1_HHOLD_Record(line);
                        short CASEW1 = rec.getCASEW1();
                        if (s.contains(CASEW1)) {
                            if (CASEW1 > Short.MIN_VALUE) {
                                r0.put(CASEW1, rec);
                                r1.add(CASEW1);
                            }
                        }
                    });
            // Close br
            Generic_IO.closeBufferedReader(br);
            WIGB_Main_Process.log("</" + m + ">");
            cache(wave, cf, r);
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record> loadCacheSubsetWave1() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record> r;
        File cf;
        cf = getFile(WIGB_WaAS_Data.W1);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave1_HHOLD_Record>) Generic_IO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }

    /**
     *
     * @param wave
     * @return
     */
    protected File getFile(byte wave) {
        File r;
        File dir;
        dir = Files.getGeneratedWaASDir();
        dir = new File(dir, "Subsets");
        r = new File(dir, TYPE + wave + "." + Strings.S_dat);
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record> loadCacheSubsetWave2() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record> r;
        File cf;
        cf = getFile(WIGB_WaAS_Data.W2);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave2_HHOLD_Record>) Generic_IO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record> loadCacheSubsetWave3() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record> r;
        File cf;
        cf = getFile(WIGB_WaAS_Data.W3);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave3_HHOLD_Record>) Generic_IO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record> loadCacheSubsetWave4() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record> r;
        File cf;
        cf = getFile(WIGB_WaAS_Data.W4);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave4_HHOLD_Record>) Generic_IO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }

    public TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record> loadCacheSubsetWave5() {
        TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record> r;
        File cf;
        cf = getFile(WIGB_WaAS_Data.W5);
        if (cf.exists()) {
            r = (TreeMap<WIGB_WaAS_ID, WIGB_WaAS_Wave5_HHOLD_Record>) Generic_IO.readObject(cf);
        } else {
            r = null;
        }
        return r;
    }
}

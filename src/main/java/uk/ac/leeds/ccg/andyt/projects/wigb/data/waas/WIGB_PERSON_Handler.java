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
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_ReadCSV;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave1_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave2_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave3_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave4_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave5_PERSON_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_PERSON_Handler extends WIGB_WAAS_Handler {

    public WIGB_PERSON_Handler(WIGB_Environment env, File indir) {
        super(env);
        TYPE = "person";
        INDIR = indir;
    }

    /**
     * Loads Wave 1 of the person WaAS for those records with CASEW1 in
     * CASEW1IDs. If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW1IDs
     * @return
     */
    public HashMap<WIGB_PERSON_ID, WIGB_Wave1_PERSON_Record> loadSubsetWave1(
            Set<Integer> CASEW1IDs) {
        HashMap<WIGB_PERSON_ID, WIGB_Wave1_PERSON_Record> r;
        try {
            r = loadCacheSubsetWave1();
        } catch (Exception ex) {
            int wave;
            wave = 1;
            File f;
            f = getInputFile(wave);
            r = new HashMap<>();
            System.out.println("<Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
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
            Integer ID;
            WIGB_PERSON_ID PERSON_ID;

            while (!read) {
                line = Generic_ReadCSV.readLine(st, null);
                lineNumber++;
                if (lineNumber % 1000 == 0) {
                    System.out.println("lineNumber " + lineNumber);
                }
                if (line == null) {
                    read = true;
                } else {
                    /**
                     * Optimisation using the fact that CASEW1 is the first part
                     * of the string.
                     */
                    ID = Integer.valueOf(line.substring(0, line.indexOf("\t")));
                    if (CASEW1IDs.contains(ID)) {
                        WIGB_Wave1_PERSON_Record rec;
                        rec = new WIGB_Wave1_PERSON_Record(line);
                        PERSON_ID = new WIGB_PERSON_ID(wave, ID,
                                rec.getPERSONW1());
                        r.put(PERSON_ID, rec);
                    }
                }
            }
            System.out.println("</Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            storeCacheSubset(wave, r);
        }
        return r;
    }

    /**
     * Loads Wave 2 of the person WaAS for those records with CASEW2 in
     * CASEW2IDs. If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW2IDs
     * @return
     */
    public HashMap<WIGB_PERSON_ID, WIGB_Wave2_PERSON_Record> loadSubsetWave2(
            Set<Integer> CASEW2IDs) {
        HashMap<WIGB_PERSON_ID, WIGB_Wave2_PERSON_Record> r;
        try {
            r = loadCacheSubsetWave2();
        } catch (Exception ex) {
            int wave;
            wave = 2;
            File f;
            f = getInputFile(wave);
            r = new HashMap<>();
            System.out.println("<Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
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
            Integer ID;
            WIGB_PERSON_ID PERSON_ID;
            while (!read) {
                line = Generic_ReadCSV.readLine(st, null);
                lineNumber++;
                if (lineNumber % 1000 == 0) {
                    System.out.println("lineNumber " + lineNumber);
                }
                if (line == null) {
                    read = true;
                } else {
                    /**
                     * Optimisation using the fact that CASEW2 is the first part
                     * of the string.
                     */
                    ID = Integer.valueOf(line.substring(0, line.indexOf("\t")));
                    if (CASEW2IDs.contains(ID)) {
                        WIGB_Wave2_PERSON_Record rec;
                        rec = new WIGB_Wave2_PERSON_Record(line);
                        PERSON_ID = new WIGB_PERSON_ID(wave, ID,
                                rec.getPERSONW2());
                        r.put(PERSON_ID, rec);
                    }
                }
            }
            System.out.println("</Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            storeCacheSubset(wave, r);
        }
        return r;
    }

    /**
     * Loads Wave 3 of the person WaAS for those records with CASEW3 in
     * CASEW3IDs. If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW3IDs
     * @return
     */
    public HashMap<WIGB_PERSON_ID, WIGB_Wave3_PERSON_Record> loadSubsetWave3(
            Set<Integer> CASEW3IDs) {
        HashMap<WIGB_PERSON_ID, WIGB_Wave3_PERSON_Record> r;
        try {
            r = loadCacheSubsetWave3();
        } catch (Exception ex) {
            int wave;
            wave = 3;
            File f;
            f = getInputFile(wave);
            r = new HashMap<>();
            System.out.println("<Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            StreamTokenizer st;
            st = new StreamTokenizer(br);
            Generic_StaticIO.setStreamTokenizerSyntax7(st);
            int lineNumber;
            lineNumber = 0;
            String line;
            String tab;
            tab = "\t";
            String l;
            // skip header
            Generic_ReadCSV.readLine(st, null);
            boolean read;
            read = false;
            Integer ID;
            WIGB_PERSON_ID PERSON_ID;
            while (!read) {
                line = Generic_ReadCSV.readLine(st, null);
                lineNumber++;
                if (lineNumber % 1000 == 0) {
                    System.out.println("lineNumber " + lineNumber);
                }
                if (line == null) {
                    read = true;
                } else {
                    /**
                     * Optimisation using the fact that CASEW3 is the 3763 part
                     * of the string.
                     */
                    l = line.substring(getIndex(line, tab, 3763));
                    ID = Integer.valueOf(l.substring(0, getIndex(l, tab, 2)).split(tab)[1]);
                    if (CASEW3IDs.contains(ID)) {
                        WIGB_Wave3_PERSON_Record rec;
                        rec = new WIGB_Wave3_PERSON_Record(line);
                        PERSON_ID = new WIGB_PERSON_ID(wave, ID,
                                rec.getPERSONW3());
                        r.put(PERSON_ID, rec);
                    }
                }
            }
            System.out.println("</Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            storeCacheSubset(wave, r);
        }
        return r;
    }

    public int getIndex(String line, String del, int n) {
        int r = line.indexOf(del);
        while (--n > 0 && r != -1) {
            r = line.indexOf(del, r + 1);
        }
        return r;
    }

    /**
     * Loads Wave 4 of the person WaAS for those records with CASEW4 in
     * CASEW4IDs. If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW4IDs
     * @return
     */
    public HashMap<WIGB_PERSON_ID, WIGB_Wave4_PERSON_Record> loadSubsetWave4(
            Set<Integer> CASEW4IDs) {
        HashMap<WIGB_PERSON_ID, WIGB_Wave4_PERSON_Record> r;
        try {
            r = loadCacheSubsetWave4();
        } catch (Exception ex) {
            int wave;
            wave = 4;
            File f;
            f = getInputFile(wave);
            r = new HashMap<>();
            System.out.println("<Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            StreamTokenizer st;
            st = new StreamTokenizer(br);
            Generic_StaticIO.setStreamTokenizerSyntax7(st);
            int lineNumber;
            lineNumber = 0;
            String line;
            String tab;
            tab = "\t";
            String l;
            // skip header
            Generic_ReadCSV.readLine(st, null);
            boolean read;
            read = false;
            Integer ID;
            WIGB_PERSON_ID PERSON_ID;
            while (!read) {
                line = Generic_ReadCSV.readLine(st, null);
                lineNumber++;
                if (lineNumber % 1000 == 0) {
                    System.out.println("lineNumber " + lineNumber);
                }
                if (line == null) {
                    read = true;
                } else {
                    /**
                     * Optimisation using the fact that CASEW4 is the 3055 part
                     * of the string.
                     */
                    l = line.substring(getIndex(line, tab, 3055));
                    ID = Integer.valueOf(l.substring(0, getIndex(l, tab, 2)).split(tab)[1]);
                    if (CASEW4IDs.contains(ID)) {
                        WIGB_Wave4_PERSON_Record rec;
                        rec = new WIGB_Wave4_PERSON_Record(line);
                        PERSON_ID = new WIGB_PERSON_ID(wave, ID,
                                rec.getPERSONW4());
                        r.put(PERSON_ID, rec);
                    }
                }
            }
            System.out.println("</Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            storeCacheSubset(wave, r);
        }
        return r;
    }

    /**
     * Loads Wave 5 of the person WaAS for those records with CASEW5 in
     * CASEW5IDs. If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW5IDs
     * @return
     */
    public HashMap<WIGB_PERSON_ID, WIGB_Wave5_PERSON_Record> loadSubsetWave5(
            Set<Integer> CASEW5IDs) {
        HashMap<WIGB_PERSON_ID, WIGB_Wave5_PERSON_Record> r;
        try {
            r = loadCacheSubsetWave5();
        } catch (Exception ex) {
            int wave;
            wave = 5;
            File dir;
            dir = Env.Files.getGeneratedWaASDirectory();
            File cf;
            cf = new File(dir, TYPE + wave + "." + Env.Strings.S_dat);
            File f;
            f = getInputFile(wave);
            r = new HashMap<>();
            System.out.println("<Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            StreamTokenizer st;
            st = new StreamTokenizer(br);
            Generic_StaticIO.setStreamTokenizerSyntax7(st);
            int lineNumber;
            lineNumber = 0;
            String line;
            String tab;
            tab = "\t";
            String l;
            // skip header
            Generic_ReadCSV.readLine(st, null);
            boolean read;
            read = false;
            Integer ID;
            WIGB_PERSON_ID PERSON_ID;
            while (!read) {
                line = Generic_ReadCSV.readLine(st, null);
                lineNumber++;
                if (lineNumber % 1000 == 0) {
                    System.out.println("lineNumber " + lineNumber);
                }
                if (line == null) {
                    read = true;
                } else {
                    /**
                     * Optimisation using the fact that CASEW5 is the 2987 part
                     * of the string.
                     */
                    l = line.substring(getIndex(line, tab, 2987));
                    ID = Integer.valueOf(l.substring(0, getIndex(l, tab, 2)).split(tab)[1]);
                    if (CASEW5IDs.contains(ID)) {
                        WIGB_Wave5_PERSON_Record rec;
                        rec = new WIGB_Wave5_PERSON_Record(line);
                        PERSON_ID = new WIGB_PERSON_ID(wave, ID,
                                rec.getPERSONW5());
                        r.put(PERSON_ID, rec);
                    }
                }
            }
            System.out.println("</Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            storeCache(wave, cf, r);
        }
        return r;
    }

    /**
     * Loads subsets from a cache in generated data.
     *
     * @return an Object[] r with size 5. r[] is a HashMap with keys that are
     * Integer CASEW1Each element is an Object[] ...
     */
    public Object[] loadSubsets() {
        Object[] r;
        r = new Object[5];
        // Load waves
        try {
            // Load Waves 1 to 5 inclusive.
            r[0] = loadCacheSubsetWave1();
            r[1] = loadCacheSubsetWave2();
            r[2] = loadCacheSubsetWave3();
            r[3] = loadCacheSubsetWave4();
            r[4] = loadCacheSubsetWave5();
        } catch (Exception ex) {
            Logger.getLogger(WIGB_PERSON_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public HashMap<WIGB_PERSON_ID, WIGB_Wave1_PERSON_Record> loadCacheSubsetWave1()
            throws Exception {
        HashMap<WIGB_PERSON_ID, WIGB_Wave1_PERSON_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "1." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (HashMap<WIGB_PERSON_ID, WIGB_Wave1_PERSON_Record>) Generic_StaticIO.readObject(cf);
        } else {
            throw new Exception("Subset for Wave 1 not found!");
        }
        return r;
    }

    public HashMap<WIGB_PERSON_ID, WIGB_Wave2_PERSON_Record> loadCacheSubsetWave2()
            throws Exception {
        HashMap<WIGB_PERSON_ID, WIGB_Wave2_PERSON_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "2." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (HashMap<WIGB_PERSON_ID, WIGB_Wave2_PERSON_Record>) Generic_StaticIO.readObject(cf);
        } else {
            throw new Exception("Subset for Wave 2 not found!");
        }
        return r;
    }

    public HashMap<WIGB_PERSON_ID, WIGB_Wave3_PERSON_Record> loadCacheSubsetWave3()
            throws Exception {
        HashMap<WIGB_PERSON_ID, WIGB_Wave3_PERSON_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "3." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (HashMap<WIGB_PERSON_ID, WIGB_Wave3_PERSON_Record>) Generic_StaticIO.readObject(cf);
        } else {
            throw new Exception("Subset for Wave 3 not found!");
        }
        return r;
    }

    public HashMap<WIGB_PERSON_ID, WIGB_Wave4_PERSON_Record> loadCacheSubsetWave4()
            throws Exception {
        HashMap<WIGB_PERSON_ID, WIGB_Wave4_PERSON_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "4." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (HashMap<WIGB_PERSON_ID, WIGB_Wave4_PERSON_Record>) Generic_StaticIO.readObject(cf);
        } else {
            throw new Exception("Subset for Wave 4 not found!");
        }
        return r;
    }

    public HashMap<WIGB_PERSON_ID, WIGB_Wave5_PERSON_Record> loadCacheSubsetWave5()
            throws Exception {
        HashMap<WIGB_PERSON_ID, WIGB_Wave5_PERSON_Record> r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + "5." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (HashMap<WIGB_PERSON_ID, WIGB_Wave5_PERSON_Record>) Generic_StaticIO.readObject(cf);
        } else {
            throw new Exception("Subset for Wave 5 not found!");
        }
        return r;
    }
}

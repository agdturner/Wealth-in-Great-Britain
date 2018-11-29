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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_ReadCSV;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave1_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave2_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave3_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave4_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave5_PERSON_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_PERSON_Handler extends WIGB_WaAS_Handler {

    public WIGB_WaAS_PERSON_Handler(WIGB_Environment env, File indir) {
        super(env);
        TYPE = "person";
        INDIR = indir;
    }

    /**
     * Loads Wave 1 of the person WaAS for those records with CASEW1 in
     * CASEW1IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW1IDs
     * @param chunkSize
     * @return
     */
    public Object[] loadSubsetWave1(Set<Short> CASEW1IDs, int chunkSize, byte wave) {
        Object[] r;
        try {
            r = (Object[]) loadCacheSubset(wave);
        } catch (Exception ex) {
            r = new Object[2];
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW1, the value is the CollectionID.
             */
            HashMap<Short, Byte> lookup;
            /**
             * This data holds the data, the keys are the CollectionID and the
             * values are a Map with the keys as CASEW1 and values a List of
             * WIGB_WaAS_Wave1_PERSON_Record records.
             */
            HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>>> data;
            lookup = new HashMap<>();
            data = new HashMap<>();
            r[0] = lookup;
            r[1] = data;
            int recID;
            recID = 0;
            byte collectionID;
            collectionID = 0;
            File f;
            f = getInputFile(wave);
            HashMap<Short, ArrayList<WIGB_WaAS_Wave1_PERSON_Record>> records;
            ArrayList<WIGB_WaAS_Wave1_PERSON_Record> list;
            records = new HashMap<>();
            data.put(collectionID, records);
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
            Short ID;
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
                    ID = Short.valueOf(line.substring(0, line.indexOf("\t")));
                    if (CASEW1IDs.contains(ID)) {
                        WIGB_WaAS_Wave1_PERSON_Record rec;
                        rec = new WIGB_WaAS_Wave1_PERSON_Record(line);
                        short CASEW1;
                        CASEW1 = rec.getCASEW1();
                        lookup.put(CASEW1, collectionID);
                        if (records.containsKey(CASEW1)) {
                            list = records.get(CASEW1);
                        } else {
                            list = new ArrayList<>();
                            records.put(CASEW1, list);
                        }
                        list.add(rec);
                        if (recID > 0 && recID % chunkSize == 0) {
                            // Cache collection
                            storeCacheSubsetCollection(collectionID, wave, records);
                            data.remove(collectionID, records);
                            // Start a new collection.
                            collectionID++;
                            records = new HashMap<>();
                            data.put(collectionID, records);
                        }
                        recID++;
                    }
                }
            }
            // Cache final collection=
            storeCacheSubsetCollection(collectionID, wave, records);
            data.remove(collectionID, records);
            System.out.println("</Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            storeCacheSubset(wave, r);
        }
        return r;
    }

    /**
     * Loads Wave 2 of the person WaAS for those records with CASEW2 in
     * CASEW2IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW2IDs
     * @param chunkSize
     * @return
     */
    public Object[] loadSubsetWave2(Set<Short> CASEW2IDs, int chunkSize, byte wave) {
        Object[] r;
        try {
            r = (Object[]) loadCacheSubset(wave);
        } catch (Exception ex) {
            r = new Object[2];
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW2, the value is the CollectionID.
             */
            HashMap<Short, Byte> lookup;
            /**
             * This data holds the data, the keys are the CollectionID and the
             * values are a Map with the keys as CASEW2 and values a List of
             * WIGB_WaAS_Wave2_PERSON_Record records.
             */
            HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>>> data;
            lookup = new HashMap<>();
            data = new HashMap<>();
            r[0] = lookup;
            r[1] = data;
            int recID;
            recID = 0;
            byte collectionID;
            collectionID = 0;
            File f;
            f = getInputFile(wave);
            HashMap<Short, ArrayList<WIGB_WaAS_Wave2_PERSON_Record>> records;
            ArrayList<WIGB_WaAS_Wave2_PERSON_Record> list;
            records = new HashMap<>();
            data.put(collectionID, records);
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
            Short ID;
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
                    ID = Short.valueOf(line.substring(0, line.indexOf("\t")));
                    if (CASEW2IDs.contains(ID)) {
                        WIGB_WaAS_Wave2_PERSON_Record rec;
                        rec = new WIGB_WaAS_Wave2_PERSON_Record(line);
                        short CASEW2;
                        CASEW2 = rec.getCASEW2();
                        lookup.put(CASEW2, collectionID);
                        if (records.containsKey(CASEW2)) {
                            list = records.get(CASEW2);
                        } else {
                            list = new ArrayList<>();
                            records.put(ID, list);
                        }
                        list.add(rec);
                        if (recID > 0 && recID % chunkSize == 0) {
                            // Cache collection
                            storeCacheSubsetCollection(collectionID, wave, records);
                            data.remove(collectionID, records);
                            // Start a new collection.
                            collectionID++;
                            records = new HashMap<>();
                            data.put(collectionID, records);
                        }
                        recID++;
                    }
                }
            }
            // Cache final collection=
            storeCacheSubsetCollection(collectionID, wave, records);
            data.remove(collectionID, records);
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
    public Object[] loadSubsetWave3(Set<Short> CASEW3IDs, int chunkSize, byte wave) {
        Object[] r;
        try {
            r = (Object[]) loadCacheSubset(wave);
        } catch (Exception ex) {
            r = new Object[2];
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW3, the value is the CollectionID.
             */
            HashMap<Short, Byte> lookup;
            /**
             * This data holds the data, the keys are the CollectionID and the
             * values are a Map with the keys as CASEW2 and values a List of
             * WIGB_WaAS_Wave3_PERSON_Record records.
             */
            HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>>> data;
            lookup = new HashMap<>();
            data = new HashMap<>();
            r[0] = lookup;
            r[1] = data;
            int recID;
            recID = 0;
            byte collectionID;
            collectionID = 0;
            File f;
            f = getInputFile(wave);
            HashMap<Short, ArrayList<WIGB_WaAS_Wave3_PERSON_Record>> records;
            ArrayList<WIGB_WaAS_Wave3_PERSON_Record> list;
            records = new HashMap<>();
            data.put(collectionID, records);
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
            String l;
            String tab = "\t";
            // skip header
            Generic_ReadCSV.readLine(st, null);
            boolean read;
            read = false;
            Short ID;
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
                    ID = Short.valueOf(l.substring(0, getIndex(l, tab, 2)).split(tab)[1]);
                    if (CASEW3IDs.contains(ID)) {
                        WIGB_WaAS_Wave3_PERSON_Record rec;
                        rec = new WIGB_WaAS_Wave3_PERSON_Record(line);
                        short CASEW3;
                        CASEW3 = rec.getCASEW3();
                        lookup.put(CASEW3, collectionID);
                        if (records.containsKey(CASEW3)) {
                            list = records.get(CASEW3);
                        } else {
                            list = new ArrayList<>();
                            records.put(ID, list);
                        }
                        list.add(rec);
                        if (recID > 0 && recID % chunkSize == 0) {
                            // Cache collection
                            storeCacheSubsetCollection(collectionID, wave, records);
                            data.remove(collectionID, records);
                            // Start a new collection.
                            collectionID++;
                            records = new HashMap<>();
                            data.put(collectionID, records);
                        }
                        recID++;
                    }
                }
            }
            // Cache final collection=
            storeCacheSubsetCollection(collectionID, wave, records);
            data.remove(collectionID, records);
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
     * Loads Wave 3 of the person WaAS for those records with CASEW3 in
     * CASEW3IDs. If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW3IDs
     * @return
     */
    public Object[] loadSubsetWave4(Set<Short> CASEW4IDs, int chunkSize, byte wave) {
        Object[] r;
        try {
            r = (Object[]) loadCacheSubset(wave);
        } catch (Exception ex) {
            r = new Object[2];
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW4, the value is the CollectionID.
             */
            HashMap<Short, Byte> lookup;
            /**
             * This data holds the data, the keys are the CollectionID and the
             * values are a Map with the keys as CASEW2 and values a List of
             * WIGB_WaAS_Wave4_PERSON_Record records.
             */
            HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>>> data;
            lookup = new HashMap<>();
            data = new HashMap<>();
            r[0] = lookup;
            r[1] = data;
            int recID;
            recID = 0;
            byte collectionID;
            collectionID = 0;
            File f;
            f = getInputFile(wave);
            HashMap<Short, ArrayList<WIGB_WaAS_Wave4_PERSON_Record>> records;
            ArrayList<WIGB_WaAS_Wave4_PERSON_Record> list;
            records = new HashMap<>();
            data.put(collectionID, records);
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
            String l;
            String tab = "\t";
            // skip header
            Generic_ReadCSV.readLine(st, null);
            boolean read;
            read = false;
            Short ID;
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
                    ID = Short.valueOf(l.substring(0, getIndex(l, tab, 2)).split(tab)[1]);
                    if (CASEW4IDs.contains(ID)) {
                        WIGB_WaAS_Wave4_PERSON_Record rec;
                        rec = new WIGB_WaAS_Wave4_PERSON_Record(line);
                        short CASEW4;
                        CASEW4 = rec.getCASEW4();
                        lookup.put(CASEW4, collectionID);
                        if (records.containsKey(CASEW4)) {
                            list = records.get(CASEW4);
                        } else {
                            list = new ArrayList<>();
                            records.put(ID, list);
                        }
                        list.add(rec);
                        if (recID > 0 && recID % chunkSize == 0) {
                            // Cache collection
                            storeCacheSubsetCollection(collectionID, wave, records);
                            data.remove(collectionID, records);
                            // Start a new collection.
                            collectionID++;
                            records = new HashMap<>();
                            data.put(collectionID, records);
                        }
                        recID++;
                    }
                }
            }
            // Cache final collection=
            storeCacheSubsetCollection(collectionID, wave, records);
            data.remove(collectionID, records);
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
    public Object[] loadSubsetWave5(Set<Short> CASEW5IDs, int chunkSize, byte wave) {
        Object[] r;
        try {
            r = (Object[]) loadCacheSubset(wave);
        } catch (Exception ex) {
            r = new Object[2];
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW5, the value is the CollectionID.
             */
            HashMap<Short, Byte> lookup;
            /**
             * This data holds the data, the keys are the CollectionID and the
             * values are a Map with the keys as CASEW2 and values a List of
             * WIGB_WaAS_Wave5_PERSON_Record records.
             */
            HashMap<Byte, HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>>> data;
            lookup = new HashMap<>();
            data = new HashMap<>();
            r[0] = lookup;
            r[1] = data;
            int recID;
            recID = 0;
            byte collectionID;
            collectionID = 0;
            File f;
            f = getInputFile(wave);
            HashMap<Short, ArrayList<WIGB_WaAS_Wave5_PERSON_Record>> records;
            ArrayList<WIGB_WaAS_Wave5_PERSON_Record> list;
            records = new HashMap<>();
            data.put(collectionID, records);
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
            String l;
            String tab = "\t";
            // skip header
            Generic_ReadCSV.readLine(st, null);
            boolean read;
            read = false;
            Short ID;
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
                    ID = Short.valueOf(l.substring(0, getIndex(l, tab, 2)).split(tab)[1]);
                    if (CASEW5IDs.contains(ID)) {
                        WIGB_WaAS_Wave5_PERSON_Record rec;
                        rec = new WIGB_WaAS_Wave5_PERSON_Record(line);
                        short CASEW5;
                        CASEW5 = rec.getCASEW5();
                        lookup.put(CASEW5, collectionID);
                        if (records.containsKey(CASEW5)) {
                            list = records.get(CASEW5);
                        } else {
                            list = new ArrayList<>();
                            records.put(ID, list);
                        }
                        list.add(rec);
                        if (recID > 0 && recID % chunkSize == 0) {
                            // Cache collection
                            storeCacheSubsetCollection(collectionID, wave, records);
                            data.remove(collectionID, records);
                            // Start a new collection.
                            collectionID++;
                            records = new HashMap<>();
                            data.put(collectionID, records);
                        }
                        recID++;
                    }
                }
            }
            // Cache final collection=
            storeCacheSubsetCollection(collectionID, wave, records);
            data.remove(collectionID, records);
            System.out.println("</Loading wave " + wave + " subset " + TYPE
                    + " WaAS data from " + f + ">");
            storeCacheSubset(wave, r);
        }
        return r;
    }

    /**
     * Loads subsets from a cache in generated data.
     *
     * @return an Object[] r with size 5. r[] is a HashMap with keys that are
     * Integer CASEW1Each element is an Object[] ...
     */
    public Object[] loadSubsets(byte nwaves) {
        Object[] r;
        r = new Object[nwaves];
        for (byte wave = 1; wave <= nwaves; wave++) {
            try {
                // Load Waves 1 to 5 inclusive.
                r[wave] = loadCacheSubset(wave);
            } catch (Exception ex) {
                Logger.getLogger(WIGB_WaAS_PERSON_Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return r;
    }

    public Object[] loadCacheSubset(byte wave) throws Exception {
        Object[] r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + wave + "." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            throw new Exception("Subset for Wave " + wave + " not found!");
        }
        return r;
    }
}

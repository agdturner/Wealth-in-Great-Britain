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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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

    protected File getFile(File dir, byte wave) {
        File f;
        f = new File(dir, "person" + wave + ".dat");
        return f;
    }

    /**
     * Loads Wave 1 of the person WaAS for those records with CASEW1 in
     * CASEW1IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW1IDs
     * @param numberOfCollections
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave1(Set<WIGB_WaAS_ID> CASEW1IDs,
            double numberOfCollections,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW1, the value is the CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            // Calculate how many subsets
            int n;
            n = CASEW1IDs.size();
            int sizeOfCollection;
            sizeOfCollection = (int) Math.ceil((double) n / numberOfCollections);
            /**
             * Initialise collectionIDSets and collectionIDPrintWriters.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
            HashMap<Short, PrintWriter> collectionIDPrintWriters;
            HashMap<Short, File> collectionIDFiles;
            collectionIDSets = new HashMap<>();
            collectionIDPrintWriters = new HashMap<>();
            collectionIDFiles = new HashMap<>();
            int start = 0;
            int count = sizeOfCollection;
            for (short collectionID = 0; collectionID < numberOfCollections; collectionID++) {
                Set<WIGB_WaAS_ID> set;
                set = CASEW1IDs.stream()
                        .skip(start) // offset
                        .limit(count) // number of items wanted
                        .collect(Collectors.toSet());
                collectionIDSets.put(collectionID, set);
                File f;
                f = new File(outdir, "person" + wave + "_" + collectionID + ".tab");
                collectionIDPrintWriters.put(collectionID,
                        Generic_StaticIO.getPrintWriter(f, false));
                collectionIDFiles.put(collectionID, f);
                start += count;
            }
            r[0] = collectionIDSets;
            r[1] = collectionIDFiles;
            /**
             * Initialise lookup.
             */
            collectionIDSets.keySet().stream()
                    .forEach(collectionID -> {
                        Set<WIGB_WaAS_ID> set;
                        set = collectionIDSets.get(collectionID);
                        set.stream()
                                .forEach(y -> {
                                    lookup.put(y.getCASEW1(), collectionID);
                                });
                    });
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read the header and write this out to the split files.
             */
            final String header;
            try {
                header = br.readLine();
                collectionIDPrintWriters.keySet().stream()
                        .forEach(collectionID -> {
                            PrintWriter pw;
                            pw = collectionIDPrintWriters.get(collectionID);
                            pw.println(header);
                        });
            } catch (IOException ex1) {
                Logger.getLogger(WIGB_WaAS_PERSON_Handler.class.getName()).log(Level.SEVERE, null, ex1);
            }
            /**
             * Read through the lines and write them to the appropriate files.
             */
            br.lines()
                    .skip(1) // Skip the header.
                    .forEach(line -> {
                        WIGB_WaAS_Wave1_PERSON_Record rec;
                        short CASEW1;
                        WIGB_WaAS_ID ID;
                        rec = new WIGB_WaAS_Wave1_PERSON_Record(line);
                        CASEW1 = rec.getCASEW1();
                        if (CASEW1 > Short.MIN_VALUE) {
                            ID = new WIGB_WaAS_ID(CASEW1, CASEW1);
                            if (lookup.containsKey(CASEW1)) {
                                short collectionID;
                                collectionID = lookup.get(CASEW1);
                                Set<WIGB_WaAS_ID> set;
                                set = collectionIDSets.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = collectionIDPrintWriters.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close the PrintWriters.
            collectionIDPrintWriters.keySet().stream()
                    .forEach(collectionID -> {
                        PrintWriter pw;
                        pw = collectionIDPrintWriters.get(collectionID);
                        pw.close();
                    });
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Loads Wave 2 of the person WaAS for those records with CASEW2 in
     * CASEW2IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW2IDs
     * @param numberOfCollections
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave2(Set<WIGB_WaAS_ID> CASEW2IDs,
            double numberOfCollections,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW2, the value is the CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            // Calculate how many subsets
            int n;
            n = CASEW2IDs.size();
            int sizeOfCollection;
            sizeOfCollection = (int) Math.ceil((double) n / numberOfCollections);
            /**
             * Initialise collectionIDSets and collectionIDPrintWriters.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
            HashMap<Short, PrintWriter> collectionIDPrintWriters;
            HashMap<Short, File> collectionIDFiles;
            collectionIDSets = new HashMap<>();
            collectionIDPrintWriters = new HashMap<>();
            collectionIDFiles = new HashMap<>();
            int start = 0;
            int count = sizeOfCollection;
            for (short collectionID = 0; collectionID < numberOfCollections; collectionID++) {
                Set<WIGB_WaAS_ID> set;
                set = CASEW2IDs.stream()
                        .skip(start) // offset
                        .limit(count) // number of items wanted
                        .collect(Collectors.toSet());
                collectionIDSets.put(collectionID, set);
                File f;
                f = new File(outdir, "person" + wave + "_" + collectionID + ".tab");
                collectionIDPrintWriters.put(collectionID,
                        Generic_StaticIO.getPrintWriter(f, false));
                collectionIDFiles.put(collectionID, f);
                start += count;
            }
            r[0] = collectionIDSets;
            r[1] = collectionIDFiles;
            /**
             * Initialise lookup.
             */
            collectionIDSets.keySet().stream()
                    .forEach(collectionID -> {
                        Set<WIGB_WaAS_ID> set;
                        set = collectionIDSets.get(collectionID);
                        set.stream()
                                .forEach(y -> {
                                    lookup.put(y.getCASEW1(), collectionID);
                                });
                    });
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read the header and write this out to the split files.
             */
            final String header;
            try {
                header = br.readLine();
                collectionIDPrintWriters.keySet().stream()
                        .forEach(collectionID -> {
                            PrintWriter pw;
                            pw = collectionIDPrintWriters.get(collectionID);
                            pw.println(header);
                        });
            } catch (IOException ex1) {
                Logger.getLogger(WIGB_WaAS_PERSON_Handler.class.getName()).log(Level.SEVERE, null, ex1);
            }
            /**
             * Read through the lines and write them to the appropriate files.
             */
            br.lines()
                    .skip(1) // Skip the header.
                    .forEach(line -> {
                        WIGB_WaAS_Wave2_PERSON_Record rec;
                        short CASEW1;
                        WIGB_WaAS_ID ID;
                        rec = new WIGB_WaAS_Wave2_PERSON_Record(line);
                        CASEW1 = rec.getCASEW1();
                        if (CASEW1 > Short.MIN_VALUE) {
                            if (lookup.containsKey(CASEW1)) {
                                short CASEW2;
                                CASEW2 = rec.getCASEW2();
                                ID = new WIGB_WaAS_ID(CASEW1, CASEW2);
                                short collectionID;
                                collectionID = lookup.get(CASEW1);
                                Set<WIGB_WaAS_ID> set;
                                set = collectionIDSets.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = collectionIDPrintWriters.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close the PrintWriters.
            collectionIDPrintWriters.keySet().stream()
                    .forEach(collectionID -> {
                        PrintWriter pw;
                        pw = collectionIDPrintWriters.get(collectionID);
                        pw.close();
                    });
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Loads Wave 3 of the person WaAS for those records with CASEW3 in
     * CASEW3IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW3IDs
     * @param numberOfCollections
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave3(Set<WIGB_WaAS_ID> CASEW3IDs, double numberOfCollections,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW3, the value is the CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            // Calculate how many subsets
            int n;
            n = CASEW3IDs.size();
            int sizeOfCollection;
            sizeOfCollection = (int) Math.ceil((double) n / numberOfCollections);
            /**
             * Initialise collectionIDSets and collectionIDPrintWriters.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
            HashMap<Short, PrintWriter> collectionIDPrintWriters;
            HashMap<Short, File> collectionIDFiles;
            collectionIDSets = new HashMap<>();
            collectionIDPrintWriters = new HashMap<>();
            collectionIDFiles = new HashMap<>();
            int start = 0;
            int count = sizeOfCollection;
            for (short collectionID = 0; collectionID < numberOfCollections; collectionID++) {
                Set<WIGB_WaAS_ID> set;
                set = CASEW3IDs.stream()
                        .skip(start) // offset
                        .limit(count) // number of items wanted
                        .collect(Collectors.toSet());
                collectionIDSets.put(collectionID, set);
                File f;
                f = new File(outdir, "person" + wave + "_" + collectionID + ".tab");
                collectionIDPrintWriters.put(collectionID,
                        Generic_StaticIO.getPrintWriter(f, false));
                collectionIDFiles.put(collectionID, f);
                start += count;
            }
            r[0] = collectionIDSets;
            r[1] = collectionIDFiles;
            /**
             * Initialise lookup.
             */
            collectionIDSets.keySet().stream()
                    .forEach(collectionID -> {
                        Set<WIGB_WaAS_ID> set;
                        set = collectionIDSets.get(collectionID);
                        set.stream()
                                .forEach(y -> {
                                    lookup.put(y.getCASEW1(), collectionID);
                                });
                    });
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read the header and write this out to the split files.
             */
            final String header;
            try {
                header = br.readLine();
                collectionIDPrintWriters.keySet().stream()
                        .forEach(collectionID -> {
                            PrintWriter pw;
                            pw = collectionIDPrintWriters.get(collectionID);
                            pw.println(header);
                        });
            } catch (IOException ex1) {
                Logger.getLogger(WIGB_WaAS_PERSON_Handler.class.getName()).log(Level.SEVERE, null, ex1);
            }
            /**
             * Read through the lines and write them to the appropriate files.
             */
            br.lines()
                    .skip(1) // Skip the header.
                    .forEach(line -> {
                        WIGB_WaAS_Wave3_PERSON_Record rec;
                        short CASEW1;
                        WIGB_WaAS_ID ID;
                        rec = new WIGB_WaAS_Wave3_PERSON_Record(line);
                        CASEW1 = rec.getCASEW1();
                        if (CASEW1 > Short.MIN_VALUE) {
                            if (lookup.containsKey(CASEW1)) {
                                short CASEW3;
                                CASEW3 = rec.getCASEW3();
                                ID = new WIGB_WaAS_ID(CASEW1, CASEW3);
                                short collectionID;
                                collectionID = lookup.get(CASEW1);
                                Set<WIGB_WaAS_ID> set;
                                set = collectionIDSets.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = collectionIDPrintWriters.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close the PrintWriters.
            collectionIDPrintWriters.keySet().stream()
                    .forEach(collectionID -> {
                        PrintWriter pw;
                        pw = collectionIDPrintWriters.get(collectionID);
                        pw.close();
                    });
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Loads Wave 4 of the person WaAS for those records with CASEW4 in
     * CASEW4IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW4IDs
     * @param numberOfCollections
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave4(Set<WIGB_WaAS_ID> CASEW4IDs, double numberOfCollections,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW4, the value is the CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            // Calculate how many subsets
            int n;
            n = CASEW4IDs.size();
            int sizeOfCollection;
            sizeOfCollection = (int) Math.ceil((double) n / numberOfCollections);
            /**
             * Initialise collectionIDSets and collectionIDPrintWriters.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
            HashMap<Short, PrintWriter> collectionIDPrintWriters;
            HashMap<Short, File> collectionIDFiles;
            collectionIDSets = new HashMap<>();
            collectionIDPrintWriters = new HashMap<>();
            collectionIDFiles = new HashMap<>();
            int start = 0;
            int count = sizeOfCollection;
            for (short collectionID = 0; collectionID < numberOfCollections; collectionID++) {
                Set<WIGB_WaAS_ID> set;
                set = CASEW4IDs.stream()
                        .skip(start) // offset
                        .limit(count) // number of items wanted
                        .collect(Collectors.toSet());
                collectionIDSets.put(collectionID, set);
                File f;
                f = new File(outdir, "person" + wave + "_" + collectionID + ".tab");
                collectionIDPrintWriters.put(collectionID,
                        Generic_StaticIO.getPrintWriter(f, false));
                collectionIDFiles.put(collectionID, f);
                start += count;
            }
            r[0] = collectionIDSets;
            r[1] = collectionIDFiles;
            /**
             * Initialise lookup.
             */
            collectionIDSets.keySet().stream()
                    .forEach(collectionID -> {
                        Set<WIGB_WaAS_ID> set;
                        set = collectionIDSets.get(collectionID);
                        set.stream()
                                .forEach(y -> {
                                    lookup.put(y.getCASEW1(), collectionID);
                                });
                    });
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read the header and write this out to the split files.
             */
            final String header;
            try {
                header = br.readLine();
                collectionIDPrintWriters.keySet().stream()
                        .forEach(collectionID -> {
                            PrintWriter pw;
                            pw = collectionIDPrintWriters.get(collectionID);
                            pw.println(header);
                        });
            } catch (IOException ex1) {
                Logger.getLogger(WIGB_WaAS_PERSON_Handler.class.getName()).log(Level.SEVERE, null, ex1);
            }
            /**
             * Read through the lines and write them to the appropriate files.
             */
            br.lines()
                    .skip(1) // Skip the header.
                    .forEach(line -> {
                        WIGB_WaAS_Wave4_PERSON_Record rec;
                        short CASEW1;
                        WIGB_WaAS_ID ID;
                        rec = new WIGB_WaAS_Wave4_PERSON_Record(line);
                        CASEW1 = rec.getCASEW1();
                        if (CASEW1 > Short.MIN_VALUE) {
                            if (lookup.containsKey(CASEW1)) {
                                short CASEW4;
                                CASEW4 = rec.getCASEW4();
                                ID = new WIGB_WaAS_ID(CASEW1, CASEW4);
                                short collectionID;
                                collectionID = lookup.get(CASEW1);
                                Set<WIGB_WaAS_ID> set;
                                set = collectionIDSets.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = collectionIDPrintWriters.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close the PrintWriters.
            collectionIDPrintWriters.keySet().stream()
                    .forEach(collectionID -> {
                        PrintWriter pw;
                        pw = collectionIDPrintWriters.get(collectionID);
                        pw.close();
                    });
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Loads Wave 5 of the person WaAS for those records with CASEW5 in
     * CASEW5IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW5IDs
     * @param numberOfCollections
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave5(Set<WIGB_WaAS_ID> CASEW5IDs, double numberOfCollections,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            /**
             * This lookup is used to identify which collection a person record
             * is in. The key is CASEW5, the value is the CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            // Calculate how many subsets
            int n;
            n = CASEW5IDs.size();
            int sizeOfCollection;
            sizeOfCollection = (int) Math.ceil((double) n / numberOfCollections);
            /**
             * Initialise collectionIDSets and collectionIDPrintWriters.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> collectionIDSets;
            HashMap<Short, PrintWriter> collectionIDPrintWriters;
            HashMap<Short, File> collectionIDFiles;
            collectionIDSets = new HashMap<>();
            collectionIDPrintWriters = new HashMap<>();
            collectionIDFiles = new HashMap<>();
            int start = 0;
            int count = sizeOfCollection;
            for (short collectionID = 0; collectionID < numberOfCollections; collectionID++) {
                Set<WIGB_WaAS_ID> set;
                set = CASEW5IDs.stream()
                        .skip(start) // offset
                        .limit(count) // number of items wanted
                        .collect(Collectors.toSet());
                collectionIDSets.put(collectionID, set);
                File f;
                f = new File(outdir, "person" + wave + "_" + collectionID + ".tab");
                collectionIDPrintWriters.put(collectionID,
                        Generic_StaticIO.getPrintWriter(f, false));
                collectionIDFiles.put(collectionID, f);
                start += count;
            }
            r[0] = collectionIDSets;
            r[1] = collectionIDFiles;
            /**
             * Initialise lookup.
             */
            collectionIDSets.keySet().stream()
                    .forEach(collectionID -> {
                        Set<WIGB_WaAS_ID> set;
                        set = collectionIDSets.get(collectionID);
                        set.stream()
                                .forEach(y -> {
                                    lookup.put(y.getCASEW1(), collectionID);
                                });
                    });
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read the header and write this out to the split files.
             */
            final String header;
            try {
                header = br.readLine();
                collectionIDPrintWriters.keySet().stream()
                        .forEach(collectionID -> {
                            PrintWriter pw;
                            pw = collectionIDPrintWriters.get(collectionID);
                            pw.println(header);
                        });
            } catch (IOException ex1) {
                Logger.getLogger(WIGB_WaAS_PERSON_Handler.class.getName()).log(Level.SEVERE, null, ex1);
            }
            /**
             * Read through the lines and write them to the appropriate files.
             */
            br.lines()
                    .skip(1) // Skip the header.
                    .forEach(line -> {
                        WIGB_WaAS_Wave5_PERSON_Record rec;
                        short CASEW1;
                        WIGB_WaAS_ID ID;
                        rec = new WIGB_WaAS_Wave5_PERSON_Record(line);
                        CASEW1 = rec.getCASEW1();
                        if (CASEW1 > Short.MIN_VALUE) {
                            if (lookup.containsKey(CASEW1)) {
                                short CASEW5;
                                CASEW5 = rec.getCASEW5();
                                ID = new WIGB_WaAS_ID(CASEW1, CASEW5);
                                short collectionID;
                                collectionID = lookup.get(CASEW1);
                                Set<WIGB_WaAS_ID> set;
                                set = collectionIDSets.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = collectionIDPrintWriters.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close the PrintWriters.
            collectionIDPrintWriters.keySet().stream()
                    .forEach(collectionID -> {
                        PrintWriter pw;
                        pw = collectionIDPrintWriters.get(collectionID);
                        pw.close();
                    });
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Loads subsets from a cache in generated data.
     *
     * @param nwaves
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

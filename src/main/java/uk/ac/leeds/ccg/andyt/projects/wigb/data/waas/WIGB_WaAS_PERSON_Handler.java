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
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave1_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave2_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave3_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave4_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_WaAS_Wave5_PERSON_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;

/**
 *
 * @author geoagdt
 */
public class WIGB_WaAS_PERSON_Handler extends WIGB_WaAS_Handler {

    public WIGB_WaAS_PERSON_Handler(WIGB_Files Files, WIGB_Strings Strings, File indir) {
        super(Files, Strings);
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
     * @param nOC Number of collections.
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave1(Set<WIGB_WaAS_ID> CASEW1IDs,
            int nOC, byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            // Calculate how many subsets
            int cSize;
            cSize = getCSize(CASEW1IDs, nOC);
            /**
             * Initialise collectionIDSets, collectionIDPrintWriters and
             * collectionIDFiles.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> cIDs;
            HashMap<Short, PrintWriter> cPWs;
            HashMap<Short, File> cFs;
            cIDs = new HashMap<>();
            cPWs = new HashMap<>();
            cFs = new HashMap<>();
            initialiseSetsFilesAndPrintWriters(cIDs, cFs, cPWs, CASEW1IDs,
                    nOC, cSize, wave, outdir);
            r[0] = cIDs;
            r[1] = cFs;
            /**
             * Initialise lookup to be used to identify which collection a
             * person record is in. The key is CASEW1, the value is the
             * CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            initialiseLookup(lookup, cIDs);
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read and write header.
             */
            addHeader(br, cPWs);
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
                                set = cIDs.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = cPWs.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close br
            Generic_StaticIO.closeBufferedReader(br);
            // Close the PrintWriters.
            closePrintWriters(cPWs);
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     * Initialise lookup.
     *
     * @param lookup
     * @param cIDs
     */
    protected void initialiseLookup(HashMap<Short, Short> lookup,
            HashMap<Short, Set<WIGB_WaAS_ID>> cIDs) {
        cIDs.keySet().stream()
                .forEach(cID -> {
                    Set<WIGB_WaAS_ID> set;
                    set = cIDs.get(cID);
                    set.stream()
                            .forEach(y -> {
                                lookup.put(y.getCASEW1(), cID);
                            });
                });
    }

    /**
     * Initialise cIDs, cPWs and cFs.
     *
     * @param cIDs
     * @param cFs
     * @param cPWs
     * @param CASEWXIDs
     * @param nCs Number of collections.
     * @param cSize Normal size of each collection.
     * @param wave
     * @param outdir
     */
    protected void initialiseSetsFilesAndPrintWriters(
            HashMap<Short, Set<WIGB_WaAS_ID>> cIDs, HashMap<Short, File> cFs,
            HashMap<Short, PrintWriter> cPWs, Set<WIGB_WaAS_ID> CASEWXIDs,
            int nCs, int cSize, byte wave, File outdir) {
        int start = 0;
        for (short cID = 0; cID < nCs; cID++) {
            Set<WIGB_WaAS_ID> set;
            set = CASEWXIDs.stream()
                    .skip(start) // offset
                    .limit(cSize) // number of items wanted
                    .collect(Collectors.toSet());
            cIDs.put(cID, set);
            File f;
            f = new File(outdir, "person" + wave + "_" + cID + ".tab");
            cPWs.put(cID, Generic_StaticIO.getPrintWriter(f, false));
            cFs.put(cID, f);
            start += cSize;
        }
    }

    /**
     * Reads the header from br and writes this out to the
     * collectionIDPrintWriters.
     *
     * @param br
     * @param CPWs
     */
    protected void addHeader(
            BufferedReader br,
            HashMap<Short, PrintWriter> CPWs) {
        try {
            String header;
            header = br.readLine();
            CPWs.keySet().stream()
                    .forEach(collectionID -> {
                        PrintWriter pw;
                        pw = CPWs.get(collectionID);
                        pw.println(header);
                    });
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            Logger.getLogger(WIGB_WaAS_PERSON_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Close the PrintWriters.
     *
     * @param cPWs
     */
    protected void closePrintWriters(HashMap<Short, PrintWriter> cPWs) {
        cPWs.keySet().stream()
                .forEach(collectionID -> {
                    PrintWriter pw;
                    pw = cPWs.get(collectionID);
                    pw.close();
                });
    }

    /**
     * Loads Wave 2 of the person WaAS for those records with CASEW2 in
     * CASEW2IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW2IDs
     * @param nOC Number of collections.
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave2(Set<WIGB_WaAS_ID> CASEW2IDs, int nOC,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            // Get the size of each collection.
            int cSize;
            cSize = getCSize(CASEW2IDs, nOC);
            /**
             * Initialise collectionIDSets and collectionIDPrintWriters.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> cIDs;
            HashMap<Short, PrintWriter> cPWs;
            HashMap<Short, File> cFs;
            cIDs = new HashMap<>();
            cPWs = new HashMap<>();
            cFs = new HashMap<>();
            initialiseSetsFilesAndPrintWriters(cIDs, cFs, cPWs, CASEW2IDs,
                    nOC, cSize, wave, outdir);
            r[0] = cIDs;
            r[1] = cFs;
            /**
             * Initialise lookup to be used to identify which collection a
             * person record is in. The key is CASEW1, the value is the
             * CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            initialiseLookup(lookup, cIDs);
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read and write header.
             */
            addHeader(br, cPWs);
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
                                set = cIDs.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = cPWs.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close br
            Generic_StaticIO.closeBufferedReader(br);
            // Close the PrintWriters.
            closePrintWriters(cPWs);
            cache(wave, cf, r);
        }
        return r;
    }

    /**
     *
     * @param c
     * @param numberOfCollections
     * @return (int) Math.ceil(c.size()/ (double) numberOfCollections)
     */
    protected int getCSize(Collection c, int numberOfCollections) {
        int r;
        int n;
        n = c.size();
        r = (int) Math.ceil((double) n / (double) numberOfCollections);
        return r;
    }

    /**
     * Loads Wave 3 of the person WaAS for those records with CASEW3 in
     * CASEW3IDs.If this data are in a cache then the cache is loaded otherwise
     * the data are selected and the cache is written for next time.
     *
     * @param CASEW3IDs
     * @param nOC Number of collections.
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave3(Set<WIGB_WaAS_ID> CASEW3IDs, int nOC,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            // Get the size of each collection.
            int cSize;
            cSize = getCSize(CASEW3IDs, nOC);
            /**
             * Initialise collectionIDSets and collectionIDPrintWriters.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> cIDs;
            HashMap<Short, PrintWriter> cPWs;
            HashMap<Short, File> cFs;
            cIDs = new HashMap<>();
            cPWs = new HashMap<>();
            cFs = new HashMap<>();
            initialiseSetsFilesAndPrintWriters(cIDs, cFs, cPWs, CASEW3IDs,
                    nOC, cSize, wave, outdir);
            r[0] = cIDs;
            r[1] = cFs;
            /**
             * Initialise lookup to be used to identify which collection a
             * person record is in. The key is CASEW1, the value is the
             * CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            initialiseLookup(lookup, cIDs);
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read and write header.
             */
            addHeader(br, cPWs);
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
                                set = cIDs.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = cPWs.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close br
            Generic_StaticIO.closeBufferedReader(br);
            // Close the PrintWriters.
            closePrintWriters(cPWs);
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
     * @param nOC Number of collections.
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave4(Set<WIGB_WaAS_ID> CASEW4IDs, int nOC,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            // Get the size of each collection.
            int cSize;
            cSize = getCSize(CASEW4IDs, nOC);
            /**
             * Initialise collectionIDSets and collectionIDPrintWriters.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> cIDs;
            HashMap<Short, PrintWriter> cPWs;
            HashMap<Short, File> cFs;
            cIDs = new HashMap<>();
            cPWs = new HashMap<>();
            cFs = new HashMap<>();
            initialiseSetsFilesAndPrintWriters(cIDs, cFs, cPWs, CASEW4IDs,
                    nOC, cSize, wave, outdir);
            r[0] = cIDs;
            r[1] = cFs;
            /**
             * Initialise lookup to be used to identify which collection a
             * person record is in. The key is CASEW1, the value is the
             * CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            initialiseLookup(lookup, cIDs);
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read and write header.
             */
            addHeader(br, cPWs);
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
                                set = cIDs.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = cPWs.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close br
            Generic_StaticIO.closeBufferedReader(br);
            // Close the PrintWriters.
            closePrintWriters(cPWs);
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
     * @param nOC Number of collections.
     * @param wave
     * @param outdir
     * @return
     */
    public Object[] loadSubsetWave5(Set<WIGB_WaAS_ID> CASEW5IDs, int nOC,
            byte wave, File outdir) {
        Object[] r;
        r = new Object[2];
        File cf;
        cf = getFile(outdir, wave);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            // Get the size of each collection.
            int cSize;
            cSize = getCSize(CASEW5IDs, nOC);
            /**
             * Initialise collectionIDSets, collectionIDPrintWriters and
             * collectionIDFiles.
             */
            HashMap<Short, Set<WIGB_WaAS_ID>> cIDs;
            HashMap<Short, PrintWriter> cPWs;
            HashMap<Short, File> cFs;
            cIDs = new HashMap<>();
            cPWs = new HashMap<>();
            cFs = new HashMap<>();
            initialiseSetsFilesAndPrintWriters(cIDs, cFs, cPWs, CASEW5IDs,
                    nOC, cSize, wave, outdir);
            r[0] = cIDs;
            r[1] = cFs;
            /**
             * Initialise lookup to be used to identify which collection a
             * person record is in. The key is CASEW1, the value is the
             * CollectionID.
             */
            HashMap<Short, Short> lookup;
            lookup = new HashMap<>();
            initialiseLookup(lookup, cIDs);
            /**
             * Read through the lines and figure out which lines should be put
             * in which collection.
             */
            File f;
            f = getInputFile(wave);
            BufferedReader br;
            br = Generic_StaticIO.getBufferedReader(f);
            /**
             * Read and write header.
             */
            addHeader(br, cPWs);
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
                                set = cIDs.get(collectionID);
                                if (set.contains(ID)) {
                                    PrintWriter pw;
                                    pw = cPWs.get(collectionID);
                                    pw.println(line);
                                }
                            }
                        }
                    });
            // Close br
            Generic_StaticIO.closeBufferedReader(br);
            // Close the PrintWriters.
            closePrintWriters(cPWs);
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
        dir = Files.getGeneratedWaASDirectory();
        dir = new File(dir, "Subsets");
        File cf;
        cf = new File(dir, TYPE + wave + "." + Strings.S_dat);
        if (cf.exists()) {
            r = (Object[]) Generic_StaticIO.readObject(cf);
        } else {
            throw new Exception("Subset for Wave " + wave + " not found!");
        }
        return r;
    }
}

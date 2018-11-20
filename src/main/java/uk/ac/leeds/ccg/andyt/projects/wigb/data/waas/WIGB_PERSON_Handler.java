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
import java.util.HashSet;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_ReadCSV;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.WIGB_Wave1Or2Or3Or4Or5_PERSON_Record;
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
     * Loads a specific wave of the person WaAS from f. The result r is an
     * Object[] of length 2. r[0] is a HashMap with Integer keys which are the
     * CASE id for the wave and the values are
     * WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>. r[1] is an array of HashSets
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
     public Object[] load(int wave) {
        Object[] r;
        File dir;
        dir = Env.Files.getGeneratedWaASDirectory();
        File cf;
        cf = new File(dir, TYPE + wave + "." + Env.Strings.S_dat);
        if (cf.exists()) {
            r = loadCache(wave, cf);
        } else {
            File f;
            f = getInputFile(wave);
            r = new Object[2];
            HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_PERSON_Record> r0;
            r0 = new HashMap<>();
            r[0] = r0;
            HashSet<Integer>[] r1;
            r1 = new HashSet[wave];
            for (int i = 0; i < wave; i++) {
                r1[i] = new HashSet<>();
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
                    System.out.println("lineNumber " + lineNumber);
                }
                if (line == null) {
                    read = true;
                } else {
                    switch (wave) {
                        case 5: {
                            WIGB_Wave5_PERSON_Record rec;
                            try {
                                rec = new WIGB_Wave5_PERSON_Record(line);
                                r1[0].add(rec.getCASEW5());
                                r1[1].add(rec.getCASEW4());
                                r1[2].add(rec.getCASEW3());
                                r1[3].add(rec.getCASEW2());
                                r1[4].add(rec.getCASEW1());
                                r0.put(rec.getCASEW5(), rec);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_Wave5_PERSON_Record(line); // For debugging
                            }
                            break;
                        }
                        case 4: {
                            WIGB_Wave4_PERSON_Record rec;
                            try {
                                rec = new WIGB_Wave4_PERSON_Record(line);
                                r1[0].add(rec.getCASEW4());
                                r1[1].add(rec.getCASEW3());
                                r1[2].add(rec.getCASEW2());
                                r1[3].add(rec.getCASEW1());
                                r0.put(rec.getCASEW4(), rec);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_Wave4_PERSON_Record(line); // For debugging
                            }
                            break;
                        }
                        case 3: {
                            WIGB_Wave3_PERSON_Record rec;
                            try {
                                rec = new WIGB_Wave3_PERSON_Record(line);
                                r1[0].add(rec.getCASEW3());
                                r1[1].add(rec.getCASEW2());
                                r1[2].add(rec.getCASEW1());
                                r0.put(rec.getCASEW3(), rec);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_Wave3_PERSON_Record(line); // For debugging
                            }
                            break;
                        }
                        case 2: {
                            WIGB_Wave2_PERSON_Record rec;
                            try {
                                rec = new WIGB_Wave2_PERSON_Record(line);
                                r1[0].add(rec.getCASEW2());
                                r1[1].add(rec.getCASEW1());
                                r0.put(rec.getCASEW2(), rec);
                            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_Wave2_PERSON_Record(line); // For debugging
                            }
                            // For debugging
                            break;
                        }
                        case 1: {
                            WIGB_Wave1_PERSON_Record rec;
                            try {
                                rec = new WIGB_Wave1_PERSON_Record(line);
                                r1[0].add(rec.getCASEW1());
                                r0.put(rec.getCASEW1(), rec);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace(System.err);
                                System.err.println(line);
                                //rec = new WIGB_Wave1_PERSON_Record(line); // For debugging
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

}

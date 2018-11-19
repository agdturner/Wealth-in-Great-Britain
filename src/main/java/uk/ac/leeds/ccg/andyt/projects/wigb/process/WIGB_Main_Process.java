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

import java.io.BufferedReader;
import java.io.File;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_ReadCSV;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_Wave1_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_Wave2_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_Wave3_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_Wave4_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_Wave5_HHOLD_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_Main_Process extends WIGB_Object {

    // For convenience
    public WIGB_Strings Strings;
    public WIGB_Files Files;

    protected WIGB_Main_Process() {
        super();
        Strings = Env.Strings;
        Files = Env.Files;
    }

    public WIGB_Main_Process(WIGB_Environment env) {
        super(env);
        Strings = Env.Strings;
        Files = Env.Files;
    }

    public static void main(String[] args) {
        WIGB_Main_Process p;
        p = new WIGB_Main_Process(new WIGB_Environment());
        p.Files.setDataDirectory(new File(System.getProperty("user.dir"), "data"));

        // Main switches
        p.doJavaCodeGeneration = false;
        p.run();
    }

    public void run() {

        if (doJavaCodeGeneration) {
            runJavaCodeGeneration();
        }

        File indir;
//        indir = new File(Files.getGeneratedDataDir(Strings), "WaAS");
        indir = Files.getWaASInputDir();
        testLoad(indir);
        //runWaves5And4(indir);
        
    }

    public void runJavaCodeGeneration() {
        WIGB_JavaCodeGenerator p;
        p = new WIGB_JavaCodeGenerator(new WIGB_Environment());
        p.Files.setDataDirectory(new File(System.getProperty("user.dir"), "data"));
        String type;
        type = "hhold";
        p.run(type);
//        type = "person";
//        p.run(type);
    }

    public void testLoad(File indir) {
        File fin;
        // Load waves
        // Load Wave 1
        fin = new File(indir, "was_wave_1_hhold_eul_final_v2.tab");
        ArrayList<Double>[] iDLists1;
        iDLists1 = load(fin, 1);
        // Load Wave 2
        fin = new File(indir, "was_wave_2_hhold_eul_final_v2.tab");
        ArrayList<Double>[] iDLists2;
        iDLists2 = load(fin, 2);
        // Load Wave 3
        fin = new File(indir, "was_wave_3_hhold_eul_final_v2.tab");
        ArrayList<Double>[] iDLists3;
        iDLists2 = load(fin, 3);
         // Load Wave 4
        fin = new File(indir, "was_wave_4_hhold_eul_final.tab");
        ArrayList<Double>[] iDLists4;
        iDLists4 = load(fin, 4);
        // Load Wave 5
        fin = new File(indir, "was_wave_5_hhold_eul_final.tab");
        ArrayList<Double>[] iDLists5;
        iDLists5 = load(fin, 5);
    }

    public void runWaves5And4(File indir) {
        File fin;
        // Load waves
        // Load Wave 5
        fin = new File(indir, "was_wave_5_hhold_eul_final.tab");
        ArrayList<Double>[] iDLists5;
        iDLists5 = load(fin, 5);
        // Load Wave 4
        fin = new File(indir, "was_wave_4_hhold_eul_final.tab");
        ArrayList<Double>[] iDLists4;
        iDLists4 = load(fin, 4);

        HashSet<Double> casew4IDsInWave5;
        casew4IDsInWave5 = new HashSet<>();
        casew4IDsInWave5.addAll(iDLists5[1]);
        System.out.println("casew4IDsInWave5.size() " + casew4IDsInWave5.size());
        System.out.println("nWave5 " + iDLists5[1].size());

        HashSet<Double> casew4IDsInWave4;
        casew4IDsInWave4 = new HashSet<>();
        casew4IDsInWave4.addAll(iDLists4[0]);
        System.out.println("casew4IDsInWave4.size() " + casew4IDsInWave4.size());
        System.out.println("nWave4 " + iDLists4[1].size());

        int countin;
        int countout;
        countin = 0;
        countout = 0;
        Double casew4IDInWave5;
        Iterator<Double> ite;
        ite = casew4IDsInWave5.iterator();
        while (ite.hasNext()) {
            casew4IDInWave5 = ite.next();
            if (casew4IDsInWave5.contains(casew4IDInWave5)) {
                countin++;
            } else {
                countout++;
            }
        }
        System.out.println("nWave4And5 " + countin);
        System.out.println("nWave4AndNot5 " + countout);

        HashSet<Double> s;
        for (int i = 4; i > -1; i--) {
            s = new HashSet<>();
            s.addAll(iDLists5[i]);
            System.out.println("nWave" + (4 - i) + " " + s.size());
        }

        // Find all the records in wave 4 that are also in wave 5;
    }

    /**
     * Thinking to returns a lists of IDs...
     *
     * @param f
     * @param wave
     * @return
     */
    public ArrayList<Double>[] load(File f, int wave) {
        ArrayList<Double>[] result;
        result = new ArrayList[wave];
        for (int i = 0; i < wave; i++) {
            result[i] = new ArrayList<>();
        }
        BufferedReader br;
        br = Generic_StaticIO.getBufferedReader(f);
        StreamTokenizer st;
        st = new StreamTokenizer(br);
        Generic_StaticIO.setStreamTokenizerSyntax7(st);

//        File outdir;
//        outdir = Files.getOutputDataDir(Strings);
//        outdir.mkdirs();
//        File outf;
//        outf = new File(outdir, "test.csv");
//
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter(outf);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(WIGB_Main_Process.class.getName()).log(Level.SEVERE, null, ex);
//        }
        int lineNumber;
        lineNumber = 0;
        String line;

        // skip header
        Generic_ReadCSV.readLine(st, null);

        boolean read;
        read = false;

        //WIGB_Wave4Or5Household_Record rec;
        //WIGB_Wave5Household_Record rec;
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
                        WIGB_Wave5_HHOLD_Record rec;
                        try {
                            rec = new WIGB_Wave5_HHOLD_Record(line);
                            result[0].add(rec.getCASEW5());
                            result[1].add(rec.getCASEW4());
                            result[2].add(rec.getCASEW3());
                            result[3].add(rec.getCASEW2());
                            result[4].add(rec.getCASEW1());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace(System.err);
                            System.err.println(line);
                            rec = new WIGB_Wave5_HHOLD_Record(line); // For debugging
                        }
                        break;
                    }
                    case 4: {
                        WIGB_Wave4_HHOLD_Record rec;
                        try {
                            rec = new WIGB_Wave4_HHOLD_Record(line);
                            result[0].add(rec.getCASEW4());
                            result[1].add(rec.getCASEW3());
                            result[2].add(rec.getCASEW2());
                            result[3].add(rec.getCASEW1());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace(System.err);
                            System.err.println(line);
                            rec = new WIGB_Wave4_HHOLD_Record(line); // For debugging
                        }
                        break;
                    }
                    case 3: {
                        WIGB_Wave3_HHOLD_Record rec;
                        try {
                            rec = new WIGB_Wave3_HHOLD_Record(line);
                            result[0].add(rec.getCASEW3());
                            result[0].add(rec.getCASEW2());
                            result[1].add(rec.getCASEW1());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace(System.err);
                            System.err.println(line);
                            rec = new WIGB_Wave3_HHOLD_Record(line); // For debugging
                        }
                        break;
                    }
                    case 2: {
                        WIGB_Wave2_HHOLD_Record rec;
                        try {
                            rec = new WIGB_Wave2_HHOLD_Record(line);
                            result[0].add(rec.getCASEW2());
                            result[1].add(rec.getCASEW1());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace(System.err);
                            System.err.println(line);
                            rec = new WIGB_Wave2_HHOLD_Record(line); // For debugging
                        } catch (NumberFormatException e) {
                            e.printStackTrace(System.err);
                            System.err.println(line);
                            rec = new WIGB_Wave2_HHOLD_Record(line); // For debugging
                        }
                        break;
                    }
                    case 1: {
                        WIGB_Wave1_HHOLD_Record rec;
                        try {
                            rec = new WIGB_Wave1_HHOLD_Record(line);
                            result[0].add(rec.getCASEW1());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace(System.err);
                            System.err.println(line);
                            rec = new WIGB_Wave1_HHOLD_Record(line); // For debugging
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        }
//        pw.close();
        return result;
    }

    boolean doJavaCodeGeneration = false;
}

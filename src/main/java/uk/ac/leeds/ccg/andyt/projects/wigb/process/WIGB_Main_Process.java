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
import uk.ac.leeds.ccg.andyt.projects.wigb.data.WIGB_Wave4Household_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.WIGB_Wave4Or5Household_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.WIGB_Wave5Household_Record;

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
        p.run();
    }

    public void run() {
        File indir;
        File fin;

        // Load waves
        indir = new File(Files.getGeneratedDataDir(Strings), "WaAS");
        // Load Wave 5
        fin = new File(indir, "was_wave_5_hhold_eul_final.csv");
        ArrayList<Double>[] iDLists5;
        iDLists5 = load(fin, 5);
        // Load Wave 4
        fin = new File(indir, "was_wave_4_hhold_eul_final.csv");
        load(fin, 4);
        ArrayList<Double>[] iDLists4;
        iDLists4 = load(fin, 5);

        HashSet<Double> casew4IDsInWave5;
        casew4IDsInWave5 = new HashSet<>();
        casew4IDsInWave5.addAll(iDLists5[1]);
        System.out.println("nWave5 " + iDLists5[1].size());

        HashSet<Double> casew4IDsInWave4;
        casew4IDsInWave4 = new HashSet<>();
        casew4IDsInWave4.addAll(iDLists4[0]);
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
            if (lineNumber % 100 == 0) {
                System.out.println("lineNumber " + lineNumber);
            }
            if (line == null) {
                read = true;
            } else {
                if (wave == 5) {
                    WIGB_Wave5Household_Record rec;
                    try {
                        rec = new WIGB_Wave5Household_Record(line);
                        result[0].add(rec.getCASEw5());
                        result[1].add(rec.getCASEw4());
                        result[2].add(rec.getCASEw3());
                        result[3].add(rec.getCASEW2());
                        result[4].add(rec.getCasew1());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("dfgadsfgafg");
                    }
                } else if (wave == 4) {
                    WIGB_Wave4Household_Record rec;
                    try {
                        rec = new WIGB_Wave4Household_Record(line);
                        result[0].add(rec.getCASEw5()); // I think this should be 4?
                        result[1].add(rec.getCASEw3());
                        result[2].add(rec.getCASEW2());
                        result[3].add(rec.getCasew1());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace(System.err);
                        System.err.println(line);
                        rec = new WIGB_Wave4Household_Record(line);
                    }
                }
                //rec.getH
//                pw.println(line);
            }
        }
//        pw.close();
        return result;
    }

}

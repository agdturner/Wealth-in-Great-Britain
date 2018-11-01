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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_ReadCSV;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
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
//        File indir;
//        indir = new File(Files.getInputDataDir(),
//                "source/7215tab_4DE9A1F2827AD9DE2F924F33D5C2A674_V1/UKDA-7215-tab");
//        File f;
//        f = new File(indir, "test.csv");
        File indir;
        indir = new File(Files.getInputDataDir(),
                "source/7215tab_4DE9A1F2827AD9DE2F924F33D5C2A674_V1/UKDA-7215-tab/csv");
        File f;
        f = new File(indir, "was_wave_5_hhold_eul_final.csv");
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
        WIGB_Wave5Household_Record rec;
        while (!read) {
            line = Generic_ReadCSV.readLine(st, null);
            lineNumber ++;
            if (lineNumber % 100 == 0) {
                System.out.println("lineNumber " + lineNumber);
            }
            if (line == null) {
                read = true;
            } else {
                rec = new WIGB_Wave5Household_Record(line);
                //rec.getH
//                pw.println(line);
            }
        }
//        pw.close();
    }

}

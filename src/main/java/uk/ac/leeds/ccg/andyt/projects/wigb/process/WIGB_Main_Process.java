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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.WIGB_HHOLD_Handler;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave2_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave3_HHOLD_Record;
import uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold.WIGB_Wave4_HHOLD_Record;

/**
 *
 * @author geoagdt
 */
public class WIGB_Main_Process extends WIGB_Object {

    // For convenience
    public WIGB_Strings Strings;
    public WIGB_Files Files;

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
        //p.doJavaCodeGeneration = true;
        p.run();
    }

    public void run() {

        if (doJavaCodeGeneration) {
            runJavaCodeGeneration();
        }

        File indir;
//        indir = new File(Files.getGeneratedDataDir(Strings), "WaAS");
        indir = Files.getWaASInputDir();

        WIGB_HHOLD_Handler hholdHandler;
        hholdHandler = new WIGB_HHOLD_Handler(Env, indir);
        Object[] hholdData;
        hholdData = hholdHandler.load();

        process(hholdData);

//        WIGB_PERSON_Handler personHandler;
//        personHandler = new WIGB_PERSON_Handler(Env, indir);
//        Object[] personData;
//        personData = personHandler.load();
    }

    /**
     * Method for running JavaCodeGeneration
     */
    public void runJavaCodeGeneration() {
        WIGB_JavaCodeGenerator p;
        p = new WIGB_JavaCodeGenerator(new WIGB_Environment());
        p.Files.setDataDirectory(new File(System.getProperty("user.dir"), "data"));
        String type;
        type = "hhold";
        p.run(type);
        type = "person";
        p.run(type);
    }

    public void process(Object[] hholdData) {
        HashMap<Integer, HashSet<Integer>[]> iDLists;
        iDLists = new HashMap<>();
        // W1
        Object[] hholdW1;
        hholdW1 = (Object[]) hholdData[0];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW1recs;
        hholdW1recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW1[0];
        HashSet<Integer>[] iDListsW1;
        iDListsW1 = (HashSet<Integer>[]) hholdW1[1];
        iDLists.put(1, iDListsW1);
        // W2
        Object[] hholdW2;
        hholdW2 = (Object[]) hholdData[1];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW2recs;
        hholdW2recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW2[0];
        HashSet<Integer>[] iDListsW2;
        iDListsW2 = (HashSet<Integer>[]) hholdW2[1];
        iDLists.put(2, iDListsW2);
        // W3
        Object[] hholdW3;
        hholdW3 = (Object[]) hholdData[2];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW3recs;
        hholdW3recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW3[0];
        HashSet<Integer>[] iDListsW3;
        iDListsW3 = (HashSet<Integer>[]) hholdW3[1];
        iDLists.put(3, iDListsW3);
        // W4
        Object[] hholdW4;
        hholdW4 = (Object[]) hholdData[3];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW4recs;
        hholdW4recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW4[0];
        HashSet<Integer>[] iDListsW4;
        iDListsW4 = (HashSet<Integer>[]) hholdW4[1];
        iDLists.put(4, iDListsW4);
        // W5
        Object[] hholdW5;
        hholdW5 = (Object[]) hholdData[4];
        HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record> hholdW5recs;
        hholdW5recs = (HashMap<Integer, WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>) hholdW5[0];
        HashSet<Integer>[] iDListsW5;
        iDListsW5 = (HashSet<Integer>[]) hholdW5[1];
        iDLists.put(5, iDListsW5);

        /**
         * Object[] of length 2. r[0] is a HashMap with Integer keys which are
         * the CASE id for the wave and the values are
         * WIGB_Wave1Or2Or3Or4Or5_HHOLD_Record>. r[1] is an array of HashSets
         * where: For Wave 5; r[1][0] is a list of CASEW5 values, r[1][1] is a
         * list of CASEW4 values, r[1][2] is a list of CASEW3 values, r[1][3] is
         * a list of CASEW2 values, r[1][4] is a list of CASEW1 values. For Wave
         * 4; r[1][0] is a list of CASEW4 values, r[1][1] is a list of CASEW3
         * values, r[1][2] is a list of CASEW2 values, r[1][3] is a list of
         * CASEW1 values. For Wave 3; r[1][0] is a list of CASEW3 values,
         * r[1][1] is a list of CASEW2 values, r[1][2] is a list of CASEW1
         * values. For Wave 2; r[1][0] is a list of CASEW2 values, r[1][1] is a
         * list of CASEW1 values. For Wave 1: r[1][0] is a list of CASEW1
         * values.
         *
         * Print out the Number of Households in
         */
        for (int wave = 5; wave > 0; wave--) {
            for (int i = 0; i < wave; i++) {
                if (i == 0) {
                    System.out.println("" + iDLists.get(wave)[i].size() + "\tNumber of HHOLD IDs in Wave " + wave);
                } else {
                    System.out.println("" + iDLists.get(wave)[i].size() + "\tNumber of HHOLD IDs from Wave " + (wave - i) + " in Wave " + wave);
                }
            }
        }

        /**
         * Calculate and report the number of Households that are in all 5 waves
         * unchanged.
         */
        Iterator<Integer> ite;
        Integer CASEW4;
        Integer CASEW3;
        Integer CASEW2;
        Integer CASEW1;
        // Calculate all hholds in waves 5, 4 and 3.
        HashSet<Integer> tCASEW3IDsInCASEW4;
        tCASEW3IDsInCASEW4 = new HashSet<>();
        ite = iDListsW5[1].iterator();
        while (ite.hasNext()) {
            CASEW4 = ite.next();
            WIGB_Wave4_HHOLD_Record r;
            r = (WIGB_Wave4_HHOLD_Record) hholdW4recs.get(CASEW4);
            if (r != null) {
                tCASEW3IDsInCASEW4.add(r.getCASEW3());
            }
        }
        System.out.println("" + tCASEW3IDsInCASEW4.size() + "\tNumber of HHOLDs in Waves 5, 4 and 3");
        // Calculate all hholds in waves 5, 4, 3 and 2.
        HashSet<Integer> tCASEW2IDsInCASEW3;
        tCASEW2IDsInCASEW3 = new HashSet<>();
        ite = tCASEW3IDsInCASEW4.iterator();
        while (ite.hasNext()) {
            CASEW3 = ite.next();
            WIGB_Wave3_HHOLD_Record r;
            r = (WIGB_Wave3_HHOLD_Record) hholdW3recs.get(CASEW3);
            if (r != null) {
                tCASEW2IDsInCASEW3.add(r.getCASEW2());
            }
        }
        System.out.println("" + tCASEW2IDsInCASEW3.size() + "\tNumber of HHOLDs in Waves 5, 4, 3 and 2");
        // Calculate all hholds in waves 5, 4, 3 and 2.
        HashSet<Integer> tCASEW1IDsInCASEW2;
        tCASEW1IDsInCASEW2 = new HashSet<>();
        ite = tCASEW2IDsInCASEW3.iterator();
        while (ite.hasNext()) {
            CASEW2 = ite.next();
            WIGB_Wave2_HHOLD_Record r;
            r = (WIGB_Wave2_HHOLD_Record) hholdW2recs.get(CASEW2);
            if (r != null) {
                tCASEW1IDsInCASEW2.add(r.getCASEW1());
            }
        }
        System.out.println("" + tCASEW1IDsInCASEW2.size() + "\tNumber of HHOLDs in Waves 5, 4, 3, 2 and 1");

    }

    boolean doJavaCodeGeneration = false;
}

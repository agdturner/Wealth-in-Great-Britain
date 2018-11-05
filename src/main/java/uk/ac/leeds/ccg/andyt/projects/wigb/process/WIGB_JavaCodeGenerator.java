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
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_ReadCSV;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Environment;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Strings;
import uk.ac.leeds.ccg.andyt.projects.wigb.io.WIGB_Files;
import uk.ac.leeds.ccg.andyt.projects.wigb.core.WIGB_Object;

/**
 *
 * @author geoagdt
 */
public class WIGB_JavaCodeGenerator extends WIGB_Object {

    // For convenience
    public WIGB_Strings Strings;
    public WIGB_Files Files;

    protected WIGB_JavaCodeGenerator() {
        super();
        Strings = Env.Strings;
        Files = Env.Files;
    }

    public WIGB_JavaCodeGenerator(WIGB_Environment env) {
        super(env);
        Strings = Env.Strings;
        Files = Env.Files;
    }

    public static void main(String[] args) {
        WIGB_JavaCodeGenerator p;
        p = new WIGB_JavaCodeGenerator(new WIGB_Environment());
        p.Files.setDataDirectory(new File(System.getProperty("user.dir"), "data"));
        p.run();
    }

    public void run() {
        File indir;
        indir = Files.getWaASInputDir();
        File fin;
        String type;
        type = "hhold";
        ArrayList<String> headers;
        headers = new ArrayList<>();
        // Load wave headers for hhold
        // Load Waves 1 to 3
        for (int wave = 1; wave <= 3; wave++) {
            fin = new File(indir, "was_wave_" + wave + "_" + type + "_eul_final_v2.tab");
            headers.add(load(fin, wave));
        }
        // Load Waves 4 and 5
        for (int wave = 4; wave <= 5; wave++) {
            fin = new File(indir, "was_wave_" + wave + "_" + type + "_eul_final.tab");
            headers.add(load(fin, wave));
        }

        //
        TreeSet<String>[] allFields;
        allFields = getFields(headers);

        ArrayList<String>[] fieldsLists;
        fieldsLists = getFieldsList(headers);

        File outdir;
        outdir = new File(Files.getDataDir(), "..");
        outdir = new File(outdir, "src");
        outdir = new File(outdir, "main");
        outdir = new File(outdir, "java");
        outdir = new File(outdir, "uk");
        outdir = new File(outdir, "ac");
        outdir = new File(outdir, "leeds");
        outdir = new File(outdir, "ccg");
        outdir = new File(outdir, "andyt");
        outdir = new File(outdir, "projects");
        outdir = new File(outdir, "wigb");
        outdir = new File(outdir, "data");

        File fout;
        PrintWriter pw;
        TreeSet<String> fields;
        String field;
        int wave;
        String name;
        String extendedClassName;
        int size;
        size = 5;
        String prepend;
        prepend = "WIGB_";
        type = type.toUpperCase();

        for (int i = 0; i < allFields.length; i++) {
            fields = allFields[i];
            if (i < size) {
                // Non-abstract classes
                wave = i + 1;
                name = prepend + "Wave" + wave + "_" + type + "_Record";
                fout = new File(outdir, name + ".java");
                pw = Generic_StaticIO.getPrintWriter(fout, false);
                pw.println("package uk.ac.leeds.ccg.andyt.projects.wigb.data;");
                switch (i) {
                    case 0:
                        extendedClassName = prepend + "Wave1Or2_" + type + "_Record";
                        pw.println("public class " + name + " extends " + extendedClassName + " {");
                        break;
                    case 1:
                        extendedClassName = prepend + "Wave1Or2_" + type + "_Record";
                        pw.println("public class " + name + " extends " + extendedClassName + " {");
                        break;
                    case 2:
                        extendedClassName = prepend + "Wave3Or4Or5_" + type + "_Record";
                        pw.println("public class " + name + " extends " + extendedClassName + " {");
                        break;
                    case 3:
                        extendedClassName = prepend + "Wave4Or5_" + type + "_Record";
                        pw.println("public class " + name + " extends " + extendedClassName + " {");
                        break;
                    case 4:
                        extendedClassName = prepend + "Wave4Or5_" + type + "_Record";
                        pw.println("public class " + name + " extends " + extendedClassName + " {");
                        break;
                    default:
                        break;
                }
                // Field declarations
                printFieldDeclarations(pw, fields);
                // Field getters
                printFieldGetters(pw, fields);
                // Constructor
                pw.println("public " + name + "(String line) {");
                pw.println("split = line.split(\"\\t\");");
                ArrayList<String> fieldsList;
                fieldsList = fieldsLists[i];
                for (int j = 0; j < fieldsList.size(); j++) {
                    field = fieldsList.get(j);
                    pw.println("if (!split[" + j + "].trim().isEmpty()) {");
                    pw.println(field + " = new Double(split[" + j + "]);");
                    pw.println("}");
                }
                pw.println("}");
                pw.println("}");
                pw.close();
            } else {
                // Abstract classes
                pw = null;
                if (i == size) {
                    name = prepend + "Wave1Or2Or3Or4Or5_" + type + "_Record";
                    fout = new File(outdir, name + ".java");
                    pw = Generic_StaticIO.getPrintWriter(fout, false);
                    pw.println("package uk.ac.leeds.ccg.andyt.projects.wigb.data;");
                    pw.println("public abstract class " + name + " {");
                    pw.println("protected String[] split;");
                } else if (i == (size + 1)) {
                    name = prepend + "Wave1Or2_" + type + "_Record";
                    fout = new File(outdir, name + ".java");
                    pw = Generic_StaticIO.getPrintWriter(fout, false);
                    pw.println("package uk.ac.leeds.ccg.andyt.projects.wigb.data;");
                    extendedClassName = prepend + "Wave1Or2Or3Or4Or5_" + type + "_Record";
                    pw.println("public abstract class " + name + " extends " + extendedClassName + " {");
                } else if (i == (size + 2)) {
                    name = prepend + "Wave3Or4Or5_" + type + "_Record";
                    fout = new File(outdir, name + ".java");
                    pw = Generic_StaticIO.getPrintWriter(fout, false);
                    pw.println("package uk.ac.leeds.ccg.andyt.projects.wigb.data;");
                    extendedClassName = prepend + "Wave1Or2Or3Or4Or5_" + type + "_Record";
                    pw.println("public abstract class " + name + " extends " + extendedClassName + " {");
                } else if (i == (size + 3)) {
                    name = prepend + "Wave4Or5_" + type + "_Record";
                    fout = new File(outdir, name + ".java");
                    pw = Generic_StaticIO.getPrintWriter(fout, false);
                    pw.println("package uk.ac.leeds.ccg.andyt.projects.wigb.data;");
                    extendedClassName = prepend + "Wave3Or4Or5_" + type + "_Record";
                    pw.println("public abstract class " + name + " extends " + extendedClassName + " {");
                }
                // Field declarations
                printFieldDeclarations(pw, fields);
                // Field getters
                printFieldGetters(pw, fields);
                pw.println("}");
                pw.close();
            }
        }

    }

    /**
     *
     * @param pw
     * @param fields
     */
    public void printFieldDeclarations(PrintWriter pw, TreeSet<String> fields) {
        String field;
        Iterator<String> ite;
        ite = fields.iterator();
        while (ite.hasNext()) {
            field = ite.next();
            pw.println("protected static Double " + field + ";");
            pw.println();
        }
    }

    /**
     *
     * @param pw
     * @param fields
     */
    public void printFieldGetters(PrintWriter pw, TreeSet<String> fields) {
        String field;
        Iterator<String> ite;
        ite = fields.iterator();
        while (ite.hasNext()) {
            field = ite.next();
            pw.println("public Double get" + field + "() {");
            pw.println("return " + field + ";");
            pw.println("}");
            pw.println();
        }
    }

    /**
     * Thinking to returns a lists of IDs...
     *
     * @param f
     * @param wave
     * @return
     */
    public String load(File f, int wave) {
        BufferedReader br;
        br = Generic_StaticIO.getBufferedReader(f);
        StreamTokenizer st;
        st = new StreamTokenizer(br);
        Generic_StaticIO.setStreamTokenizerSyntax7(st);
        String header;
        header = Generic_ReadCSV.readLine(st, null);
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(WIGB_JavaCodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ws;
        ws = "W" + wave;
        String keyIdentifier1;
        keyIdentifier1 = "CASE" + ws;
        String keyIdentifier2;
        keyIdentifier2 = "PERSON" + ws;
        String uniqueString1;
        uniqueString1 = "uniqueString1";
        String uniqueString2;
        uniqueString2 = "uniqueString2";
        String h1;
        h1 = header.toUpperCase();
        try {
            if (h1.contains(uniqueString1)) {
                throw new Exception(uniqueString1 + " is not unique!");
            }
            if (h1.contains(uniqueString2)) {
                throw new Exception(uniqueString2 + " is not unique!");
            }
        } catch (Exception ex) {
            Logger.getLogger(WIGB_JavaCodeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        h1 = h1.replaceAll("\t", " ,");
        h1 = h1 + " ";
        h1 = h1.replaceAll(keyIdentifier1, uniqueString1);
        h1 = h1.replaceAll(keyIdentifier2, uniqueString2);
        h1 = h1.replaceAll(ws + " ", " ");
        h1 = h1.replaceAll(" " + ws, " ");
        h1 = h1.replaceAll(ws + "_", "_");
        h1 = h1.replaceAll("_" + ws, "_");
        h1 = h1.replaceAll(ws + " ", "___" + ws + " ");
        h1 = h1.trim();
        h1 = h1.replaceAll(" ,", "\t");
        h1 = h1.replaceAll(uniqueString1, keyIdentifier1);
        h1 = h1.replaceAll(uniqueString2, keyIdentifier2);
        return h1;
    }

    /**
     * Finds and returns those fields that are in common and those fields .
     * result[0] are the fields in common with all.
     *
     * @param headers
     * @return
     */
    public TreeSet<String>[] getFields(ArrayList<String> headers) {
        TreeSet<String>[] r;
        int size;
        size = headers.size();
        r = new TreeSet[(size * 2) - 1];
        Iterator<String> ite;
        ite = headers.iterator();
        int i;
        i = 0;
        while (ite.hasNext()) {
            r[i] = getFields(ite.next());
            i++;
        }
        // Get fields common to waves 1, 2, 3, 4 and 5 (12345)
        r[5] = getFieldsInCommon(r[0], r[1], r[2], r[3], r[4]);
        System.out.println("Number of fields common to waves 1, 2, 3, 4 and 5 (12345) " + r[5].size());
        // Get fields other than 12345 that are common to waves 1 and 2 (12)
        r[6] = getFieldsInCommon(r[0], r[1], null, null, null);
        r[6].removeAll(r[5]);
        System.out.println("Number of fields other than 12345 that are common to waves 1 and 2 (12) " + r[6].size());
        // Get fields other than 12345 that are in common to waves 3, 4 and 5 (345)
        r[7] = getFieldsInCommon(r[0], r[3], r[4], r[5], null);
        r[6].removeAll(r[5]);
        System.out.println("Number of fields other than 12345 that are in common to waves 3, 4 and 5 (345) " + r[7].size());
        // Get fields other than 345 that are in common to waves 4 and 5 (45)
        r[8] = getFieldsInCommon(r[4], r[5], null, null, null);
        r[8].removeAll(r[7]);
        System.out.println("Number of fields other than 345 that are in common to waves 4 and 5 (45) " + r[8].size());
        r[0].removeAll(r[5]);
        r[0].removeAll(r[6]);
        r[1].removeAll(r[5]);
        r[1].removeAll(r[6]);
        r[2].removeAll(r[5]);
        r[2].removeAll(r[7]);
        r[3].removeAll(r[5]);
        r[3].removeAll(r[7]);
        r[4].removeAll(r[5]);
        r[4].removeAll(r[7]);
        return r;
    }

    /**
     * Finds and returns those fields that are in common and those fields .
     * result[0] are the fields in common with all.
     *
     * @param headers
     * @return
     */
    public ArrayList<String>[] getFieldsList(ArrayList<String> headers) {
        ArrayList<String>[] r;
        int size;
        size = headers.size();
        r = new ArrayList[size];
        Iterator<String> ite;
        ite = headers.iterator();
        int i;
        i = 0;
        while (ite.hasNext()) {
            r[i] = getFieldsList(ite.next());
            i++;
        }
        return r;
    }

    /**
     *
     * @param s
     * @return
     */
    public TreeSet<String> getFields(String s) {
        TreeSet<String> r;
        r = new TreeSet<>();
        String[] split;
        split = s.split("\t");
        r.addAll(Arrays.asList(split));
        return r;
    }

    /**
     *
     * @param s
     * @return
     */
    public ArrayList<String> getFieldsList(String s) {
        ArrayList<String> r;
        r = new ArrayList<>();
        String[] split;
        split = s.split("\t");
        r.addAll(Arrays.asList(split));
        return r;
    }

    /**
     * Returns all the values common to s1, s2, s3, s4 and s5 and removes all
     * these common fields from s1, s2, s3, s4 and s5.
     *
     * @param s1
     * @param s2
     * @param s3 May be null.
     * @param s4 May be null.
     * @param s5 May be null.
     * @return
     */
    public TreeSet<String> getFieldsInCommon(TreeSet<String> s1,
            TreeSet<String> s2, TreeSet<String> s3, TreeSet<String> s4,
            TreeSet<String> s5) {
        TreeSet<String> result;
        result = new TreeSet<>();
        result.addAll(s1);
        result.retainAll(s2);
        if (s3 != null) {
            result.retainAll(s3);
        }
        if (s4 != null) {
            result.retainAll(s4);
        }
        if (s5 != null) {
            result.retainAll(s5);
        }
        return result;
    }
}

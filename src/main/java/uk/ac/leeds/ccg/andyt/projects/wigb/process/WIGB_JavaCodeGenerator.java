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
 * This class produces source code for loading survey data. Source code classes
 * written in order to load the Wealth and Assets Survey (WaAS) household data
 * is written to uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold. Source
 * code classes written in order to load the WaAS person data is written to
 * uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.person.
 *
 * As these survey data contained many variables, it was thought best to write
 * some code that wrote some code to load these data and provide access to the
 * variables. Most variables are loaded as Double types. Some such as dates have
 * been loaded as String types. There are documents:
 * data\input\WaAS\UKDA-7215-tab\mrdoc\pdf\7215_was_questionnaire_wave_1.pdf
 * data\input\WaAS\UKDA-7215-tab\mrdoc\pdf\7215_was_questionnaire_wave_2.pdf
 * data\input\WaAS\UKDA-7215-tab\mrdoc\pdf\7215_was_questionnaire_wave_3.pdf
 * data\input\WaAS\UKDA-7215-tab\mrdoc\pdf\7215_was_questionnaire_wave_4.pdf
 * data\input\WaAS\UKDA-7215-tab\mrdoc\pdf\7215_was_questionnaire_wave_5.pdf
 * that detail what values are expected from what variables. Another way to
 * create the data loading classes would be to parse this document. A thorough
 * job of exploring these data would check the data values to make sure that
 * they conformed to these schemas. This would also allow the variables to be
 * stored in the most appropriate way (e.g. as an integer, double, String, date
 * etc.).
 *
 * @author geoagdt
 */
public class WIGB_JavaCodeGenerator extends WIGB_Object {

    private static final long serialVersionUID = 1L;

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
        String type;
        type = "hhold";
        p.run(type);
        type = "person";
        p.run(type);
    }

    public void run(String type) {
        File indir;
        indir = Files.getWaASInputDir();
        File fin;

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
        outdir = new File(outdir, "waas");
        outdir = new File(outdir, type);
        outdir.mkdirs();
        String packageName;
        packageName = "uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.";
        packageName += type;

        // stringFields are to distinguish those fields that are not 
        // represented by numbers. From the documentation it should be possible 
        // to figure out which to store as Integer or Boolean too.
        HashSet<String> stringFields;
        stringFields = new HashSet<>();
        stringFields.add("SOA2");
        stringFields.add("SOA1");
        stringFields.add("STATSWARD");
        stringFields.add("VOTYO");
        // The following are for Person files
        stringFields.add("DTJBL");
        stringFields.add("BACCBEG");
        stringFields.add("BACCBEG1");
        stringFields.add("BACCBEG2");
        stringFields.add("BACCBEG3");
        stringFields.add("BACCEND");
        stringFields.add("BACCEND1");
        stringFields.add("BACCEND2");
        stringFields.add("BACCEND3");

        // stringFields are to distinguish those fields that are not 
        // represented by numbers. From the documentation it should be possible 
        // to figure out which to store as Integer or Boolean too.
        HashSet<String> integerFields;
        integerFields = new HashSet<>();
        integerFields.add("CASEW5");
        integerFields.add("CASEW4");
        integerFields.add("CASEW3");
        integerFields.add("CASEW2");
        integerFields.add("CASEW1");
        integerFields.add("PERSONW5");
        integerFields.add("PERSONW4");
        integerFields.add("PERSONW3");
        integerFields.add("PERSONW2");
        integerFields.add("PERSONW1");

        File fout;
        PrintWriter pw;
        TreeSet<String> fields;
        String field;
        int wave;
        String className;
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
                className = prepend + "Wave" + wave + "_" + type + "_Record";
                fout = new File(outdir, className + ".java");
                pw = Generic_StaticIO.getPrintWriter(fout, false);
                writeHeaderPackageAndImports(pw, packageName, "");
                switch (i) {
                    case 0:
                        extendedClassName = prepend + "Wave1Or2_" + type + "_Record";
                        break;
                    case 1:
                        extendedClassName = prepend + "Wave1Or2_" + type + "_Record";
                        break;
                    case 2:
                        extendedClassName = prepend + "Wave3Or4Or5_" + type + "_Record";
                        break;
                    case 3:
                        extendedClassName = prepend + "Wave4Or5_" + type + "_Record";
                        break;
                    case 4:
                        extendedClassName = prepend + "Wave4Or5_" + type + "_Record";
                        break;
                    default:
                        extendedClassName = "";
                        break;
                }
                printClassDeclarationSerialVersionUID(pw, packageName, 
                        className, "", extendedClassName);
                // Print Field Declarations Inits And Getters
                printFieldDeclarationsInitsAndGetters(integerFields,
                        stringFields, pw, fields);
                // Constructor
                pw.println("public " + className + "(String line) {");
                pw.println("s = line.split(\"\\t\");");
                ArrayList<String> fieldsList;
                fieldsList = fieldsLists[i];
                for (int j = 0; j < fieldsList.size(); j++) {
                    field = fieldsList.get(j);
                    pw.println("init" + field + "(s[" + j + "]);");
                }
                pw.println("}");
                pw.println("}");
                pw.close();
            } else {
                // Abstract classes
                pw = null;
                if (i == size) {
                    className = prepend + "Wave1Or2Or3Or4Or5_" + type + "_Record";
                    fout = new File(outdir, className + ".java");
                    pw = Generic_StaticIO.getPrintWriter(fout, false);
                    writeHeaderPackageAndImports(pw, packageName, 
                            "java.io.Serializable");
                    printClassDeclarationSerialVersionUID(pw, packageName, 
                        className, "Serializable", "");
                    pw.println("protected String[] s;");
                } else if (i == (size + 1)) {
                    className = prepend + "Wave1Or2_" + type + "_Record";
                    fout = new File(outdir, className + ".java");
                    pw = Generic_StaticIO.getPrintWriter(fout, false);
                    writeHeaderPackageAndImports(pw, packageName, "");
                    extendedClassName = prepend + "Wave1Or2Or3Or4Or5_" + type + "_Record";
                    printClassDeclarationSerialVersionUID(pw, packageName, 
                            className, "", extendedClassName);
                } else if (i == (size + 2)) {
                    className = prepend + "Wave3Or4Or5_" + type + "_Record";
                    fout = new File(outdir, className + ".java");
                    pw = Generic_StaticIO.getPrintWriter(fout, false);
                    writeHeaderPackageAndImports(pw, packageName, "");
                    extendedClassName = prepend + "Wave1Or2Or3Or4Or5_" + type + "_Record";
                   printClassDeclarationSerialVersionUID(pw, packageName, 
                            className, "", extendedClassName);
                } else if (i == (size + 3)) {
                    className = prepend + "Wave4Or5_" + type + "_Record";
                    fout = new File(outdir, className + ".java");
                    pw = Generic_StaticIO.getPrintWriter(fout, false);
                    writeHeaderPackageAndImports(pw, packageName, "");
                    extendedClassName = prepend + "Wave3Or4Or5_" + type + "_Record";
                    printClassDeclarationSerialVersionUID(pw, packageName, 
                            className, "", extendedClassName);
                }
                // Print Field Declarations Inits And Getters
                printFieldDeclarationsInitsAndGetters(integerFields, stringFields, pw, fields);
                pw.println("}");
                pw.close();
            }
        }

    }

    /**
     *
     * @param pw
     * @param packageName
     */
    public void writeHeaderPackageAndImports(PrintWriter pw, 
            String packageName, String imports) {
        pw.println("/**");
        pw.println(" * Source code generated by " + this.getClass().getName());
        pw.println(" */");
        pw.println("package " + packageName + ";");
        if (!imports.isEmpty()) {
            pw.println("import " + imports + ";");
        }
    }

    /**
     * 
     * @param pw
     * @param packageName
     * @param className
     * @param implementations
     * @param extendedClassName 
     */
    public void printClassDeclarationSerialVersionUID(PrintWriter pw,
            String packageName, String className, String implementations,
            String extendedClassName) {
        pw.print("public class " + className);
        if (!extendedClassName.isEmpty()) {
            pw.print(" extends " + extendedClassName + " {");
        }
        if (!implementations.isEmpty()) {
            pw.print(" implements " + implementations + " {");
        }
        pw.println();
        /** This is not included for performance reasons.
        pw.println("private static final long serialVersionUID = "
                + serialVersionUID + ";");
        */
    }

    /**
     * @param integerFields
     * @param stringFields
     * @param pw
     * @param fields
     */
    public void printFieldDeclarationsInitsAndGetters(
            HashSet<String> integerFields, HashSet<String> stringFields,
            PrintWriter pw, TreeSet<String> fields) {
        // Field declarations
        printFieldDeclarations(integerFields, stringFields, pw, fields);
        // Field init
        printFieldInits(integerFields, stringFields, pw, fields);
        // Field getters
        printFieldGetters(integerFields, stringFields, pw, fields);
    }

    /**
     * @param integerFields
     * @param stringFields
     * @param pw
     * @param fields
     */
    public void printFieldDeclarations(HashSet<String> integerFields,
            HashSet<String> stringFields, PrintWriter pw,
            TreeSet<String> fields) {
        String field;
        Iterator<String> ite;
        ite = fields.iterator();
        while (ite.hasNext()) {
            field = ite.next();
            if (stringFields.contains(field)) {
                pw.println("protected String " + field + ";");
            } else {
                if (integerFields.contains(field)) {
                    pw.println("protected Integer " + field + ";");
                } else {
                    pw.println("protected Double " + field + ";");
                }
            }
        }
    }

    /**
     *
     * @param integerFields
     * @param stringFields
     * @param pw
     * @param fields
     */
    public void printFieldGetters(HashSet<String> integerFields,
            HashSet<String> stringFields, PrintWriter pw,
            TreeSet<String> fields) {
        String field;
        Iterator<String> ite;
        ite = fields.iterator();
        while (ite.hasNext()) {
            field = ite.next();
            if (stringFields.contains(field)) {
                pw.println("public String get" + field + "() {");
            } else {
                if (integerFields.contains(field)) {
                    pw.println("public Integer get" + field + "() {");
                } else {
                    pw.println("public Double get" + field + "() {");
                }
            }
            pw.println("return " + field + ";");
            pw.println("}");
            pw.println();

        }
    }

    /**
     *
     * @param integerFields
     * @param stringFields
     * @param pw
     * @param fields
     */
    public void printFieldInits(HashSet<String> integerFields,
            HashSet<String> stringFields, PrintWriter pw,
            TreeSet<String> fields) {
        String field;
        Iterator<String> ite;
        ite = fields.iterator();
        while (ite.hasNext()) {
            field = ite.next();
            if (stringFields.contains(field)) {
                pw.println("protected final void init" + field + "(String s) {");
                pw.println("if (!s.trim().isEmpty()) {");
                pw.println(field + " = s;");
            } else {
                if (integerFields.contains(field)) {
                    pw.println("protected final void init" + field + "(String s) {");
                    pw.println("if (!s.trim().isEmpty()) {");
                    pw.println(field + " = new Integer(s);");
                } else {
                    pw.println("protected final void init" + field + "(String s) {");
                    pw.println("if (!s.trim().isEmpty()) {");
                    pw.println(field + " = new Double(s);");
                }
            }
            pw.println("}");
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
     * Finds and returns r where. r[0] are the fields in common with all waves.
     * r[1] are the fields in common with all waves. r[2] are the fields in
     * common with all waves. r[3] are the fields in common with all waves. r[4]
     * are the fields in common with all waves. r[5] fields common to waves 1,
     * 2, 3, 4 and 5 (12345) r[6] fields other than 12345 that are common to
     * waves 1 and 2 (12). r[7] fields other than 12345 that are in common to
     * waves 3, 4 and 5 (345) r[8] fields other than 345 that are in common to
     * waves 4 and 5 (45)
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
        r[7] = getFieldsInCommon(r[2], r[3], r[4], null, null);
        r[7].removeAll(r[5]);
        System.out.println("Number of fields other than 12345 that are in common to waves 3, 4 and 5 (345) " + r[7].size());
        // Get fields other than 345 that are in common to waves 4 and 5 (45)
        r[8] = getFieldsInCommon(r[3], r[4], null, null, null);
        r[8].removeAll(r[5]);
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
        r[3].removeAll(r[8]);
        r[4].removeAll(r[5]);
        r[4].removeAll(r[7]);
        r[4].removeAll(r[8]);
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
     * @Todo generalise
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

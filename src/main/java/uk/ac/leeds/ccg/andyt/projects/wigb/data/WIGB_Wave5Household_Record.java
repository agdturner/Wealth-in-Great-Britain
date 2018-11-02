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
package uk.ac.leeds.ccg.andyt.projects.wigb.data;

/**
 *
 * @author geoagdt
 */
public class WIGB_Wave5Household_Record extends WIGB_Wave4Or5Household_Record {

    // Extras 1
    private Double YearW5;
    private Double MonthW5;
    // Extras 2
    private Double MIntPaid1;
    private Double MIntFix1;
    private Double MIntRate1;
    // Extras 3
    private Double MPastSPA1;
    private Double MYPastSPA1;
    // Extras 4
    private Double MEndVb8;
    private Double MEndy8;
    // Extras 5
    private Double MPastSPA2;
    private Double MYPastSPA2;
    // Extras 6
    private Double MIntPaid2;
    private Double MIntFix2;
    private Double MIntRate2;
    // Extras 7
    private Double MPastSPA3;
    private Double MYPastSPA3;
    // Extras 8
    private Double MIntPaid3;
    private Double MIntFix3;
    private Double MIntRate3;
    // Extras 9
    private Double VType3;
    // Extras 10
    private Double VType1_i;
    private Double VCarN_i;
    private Double VCarN_iflag;
    private Double VEstVTotal;
    private Double VEstVTotalB;
    private Double VEstVTotalB_i;
    private Double VEstVTotalB_iflag;
    private Double VEstVTotal_i;
    private Double VEstVTotal_iflag;
    private Double VOTyp_iflag;
    private Double VOVlB_i;
    private Double VOVlB_iflag;
    // Extras 11
    private Double mendv10i;
    private Double mendv10iflag;
    private Double mendv11i;
    private Double mendv11iflag;
    // Extras 11
    private Double VType1i;
    // Extras 12
    private Double VType3i;
    // Extras 13
    private Double DVTotGIR;
    // Extras 14
    private Double NumAdISw;
    // Extras 15
    private Double CASEw4;
    private Double CASEw3;
    private Double CASEW2;
    private Double casew1;

    public WIGB_Wave5Household_Record(String line) {
        init0(line);
        // Extras 1
        if (split[n].trim().isEmpty()) {
            YearW5 = null;
        } else {
            YearW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MonthW5 = null;
        } else {
            MonthW5 = new Double(split[n]);
        }
        n++;
        init1();
        init2();
        // Extras 2
        if (split[n].trim().isEmpty()) {
            MIntPaid1 = null;
        } else {
            MIntPaid1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntFix1 = null;
        } else {
            MIntFix1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntRate1 = null;
        } else {
            MIntRate1 = new Double(split[n]);
        }
        n++;
        init3();
        // Extras 3
        if (split[n].trim().isEmpty()) {
            MPastSPA1 = null;
        } else {
            MPastSPA1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYPastSPA1 = null;
        } else {
            MYPastSPA1 = new Double(split[n]);
        }
        n++;
        init4();
        // Extras 4
        if (split[n].trim().isEmpty()) {
            MEndVb8 = null;
        } else {
            MEndVb8 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy8 = null;
        } else {
            MEndy8 = new Double(split[n]);
        }
        n++;
        init5();
        // Extras 5
        if (split[n].trim().isEmpty()) {
            MPastSPA2 = null;
        } else {
            MPastSPA2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYPastSPA2 = null;
        } else {
            MYPastSPA2 = new Double(split[n]);
        }
        n++;
        init6();
        // Extras 6        
        if (split[n].trim().isEmpty()) {
            MIntPaid2 = null;
        } else {
            MIntPaid2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntFix2 = null;
        } else {
            MIntFix2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntRate2 = null;
        } else {
            MIntRate2 = new Double(split[n]);
        }
        n++;
        init7();
        // Extras 7
        if (split[n].trim().isEmpty()) {
            MPastSPA3 = null;
        } else {
            MPastSPA3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYPastSPA3 = null;
        } else {
            MYPastSPA3 = new Double(split[n]);
        }
        n++;
        init8();
        // Extras 8
        if (split[n].trim().isEmpty()) {
            MIntPaid3 = null;
        } else {
            MIntPaid3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntFix3 = null;
        } else {
            MIntFix3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntRate3 = null;
        } else {
            MIntRate3 = new Double(split[n]);
        }
        n++;
        init9();
        init10();
        init11();
        // Extras 9
        if (split[n].trim().isEmpty()) {
            VType3 = null;
        } else {
            VType3 = new Double(split[n]);
        }
        n++;
        init12();
        // Extras 10
        if (split[n].trim().isEmpty()) {
            VCarN_i = null;
        } else {
            VCarN_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VCarN_iflag = null;
        } else {
            VCarN_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotal = null;
        } else {
            VEstVTotal = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalB = null;
        } else {
            VEstVTotalB = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotal_i = null;
        } else {
            VEstVTotal_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalB_i = null;
        } else {
            VEstVTotalB_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalB_iflag = null;
        } else {
            VEstVTotalB_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotal_i = null;
        } else {
            VEstVTotal_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotal_iflag = null;
        } else {
            VEstVTotal_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VType_iflag = null;
        } else {
            VType_iflag = new Double(split[n]);

        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp_iflag = null;
        } else {
            VOTyp_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOVlB_i = null;
        } else {
            VOVlB_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOVlB_iflag = null;
        } else {
            VOVlB_iflag = new Double(split[n]);
        }
        n++;
        init13();
        if (split[n].trim().isEmpty()) {
            mendv10i = null;
        } else {
            mendv10i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv10iflag = null;
        } else {
            mendv10iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv11i = null;
        } else {
            mendv11i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv11iflag = null;
        } else {
            mendv11iflag = new Double(split[n]);
        }
        n++;
        init14();
        // Extras 11
        if (split[n].trim().isEmpty()) {
            VType1i = null;
        } else {
            VType1i = new Double(split[n]);
        }
        n++;
        init15();
        // Extras 12
        if (split[n].trim().isEmpty()) {
            VType3i = null;
        } else {
            VType3i = new Double(split[n]);
        }
        n++;
        init16();
        init17();
        // Extras 13
        if (split[n].trim().isEmpty()) {
            DVTotGIR = null;
        } else {
            DVTotGIR = new Double(split[n]);
        }
        n++;
        init18();
        // Extras 14
        if (split[n].trim().isEmpty()) {
            NumAdISw = null;
        } else {
            NumAdISw = new Double(split[n]);
        }
        n++;
        init19();
        init20();
        init21();
        init22();
        // Extras 15
        if (split[n].trim().isEmpty()) {
            CASEw4 = null;
        } else {
            CASEw4 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            CASEw3 = null;
        } else {
            CASEw3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            CASEW2 = null;
        } else {
            CASEW2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            casew1 = null;
        } else {
            casew1 = new Double(split[n]);
        }
        n++;
        if (n != split.length) {
            int debug = 1;
            System.out.println("n(" + n + ")!= split.length(" + split.length + ")");
        }
    }
}

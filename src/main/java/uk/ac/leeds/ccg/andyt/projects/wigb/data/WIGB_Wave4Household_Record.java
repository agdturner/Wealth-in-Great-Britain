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
public class WIGB_Wave4Household_Record extends WIGB_Wave4Or5Household_Record {

    // Extras 1
    private Double HIntro;
    // Extras 2
    private Double EqReas1;
    private Double EqReas2;
    private Double EqReas3;
    private Double EqReas4;
    // Extras 3
    private Double VEstV1;
    private Double VEsVb1;
    // Extras 3
    private Double VEstV2;
    private Double VEsVb2;
    // Extras 5
    //protected Double VCarN_i; // In both, but in very different places!
    //protected Double VCarN_iflag; // In both, but in very different places!
    private Double VEstVTotal;
    private Double VEstVTotalB;
    private Double VEstVTotalB_i;
    private Double VEstVTotalB_iflag;
    private Double VEstVTotal_i;
    private Double VEstVTotal_iflag;
    //protected Double VType_iflag;
    private Double VOTyp_iflag;
    private Double VOVlB_i;
    private Double VOVlB_iflag;
    // Extras 6
    private Double VEstV_i;
    private Double VEstV_iflag;
    private Double VEstV2_i;
    private Double VEstV2_iflag;
    private Double VType_i;
    //private Double VType_iflag; // In both, but in very different places!
    // Extras 7
    private Double VType2_iflag;
    // Extras 8
    private Double DVTotCarVal;
    private Double DVTotVanVal;
    private Double DVTotMotBVal;
    // Extras 8
    private Double vesvb3;
    // Extras 9
    private Double CASEw3;
    private Double CASEW2;
    private Double casew1;
    // Extras 10
    private Double dvtotgir_3sf;
    
    public WIGB_Wave4Household_Record(String line) {
        init0(line);
        init1();
        // Extras 1
        if (split[n].trim().isEmpty()) {
            HIntro = null;
        } else {
            HIntro = new Double(split[n]);
        }
        n++;
        init2();
        init3();
        init4();
        init5();
        init6();
        init7();
        init8();
        init9();
        // Extras 2
        if (split[n].trim().isEmpty()) {
            EqReas1 = null;
        } else {
            EqReas1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqReas2 = null;
        } else {
            EqReas2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqReas3 = null;
        } else {
            EqReas3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqReas4 = null;
        } else {
            EqReas4 = new Double(split[n]);
        }
        n++;
        init10();
        // Extras 3
        if (split[n].trim().isEmpty()) {
            VEstV1 = null;
        } else {
            VEstV1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEsVb1 = null;
        } else {
            VEsVb1 = new Double(split[n]);
        }
        n++;
        init11();
        // Extras 4
        if (split[n].trim().isEmpty()) {
            VEsVb2 = null;
        } else {
            VEsVb2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEsVb2 = null;
        } else {
            VEsVb2 = new Double(split[n]);
        }
        n++;
        init12();
        // Extras 5
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
        init14();
        // Extras 6
        if (split[n].trim().isEmpty()) {
            VCarN_i = null;
        } else {
            VCarN_i = new Double(split[n]);
        }
        n++;if (split[n].trim().isEmpty()) {
            VCarN_iflag = null;
        } else {
            VCarN_iflag = new Double(split[n]);
        }
        n++;if (split[n].trim().isEmpty()) {
            VEstV_i = null;
        } else {
            VEstV_i = new Double(split[n]);
        }
        n++;if (split[n].trim().isEmpty()) {
            VEstV_iflag = null;
        } else {
            VEstV_iflag = new Double(split[n]);
        }
        n++;if (split[n].trim().isEmpty()) {
            VEstV2_i = null;
        } else {
            VEstV2_i = new Double(split[n]);
        }
        n++;
         if (split[n].trim().isEmpty()) {
            VEstV2_iflag = null;
        } else {
            VEstV2_iflag = new Double(split[n]);
        }
        n++;
         if (split[n].trim().isEmpty()) {
            VType_i = null;
        } else {
            VType_i = new Double(split[n]);
        }
        n++;
         if (split[n].trim().isEmpty()) {
            VType_iflag = null;
        } else {
            VType_iflag = new Double(split[n]);
        }
        n++;
        init15();
        // Extras 7
        if (split[n].trim().isEmpty()) {
            VType2_iflag = null;
        } else {
            VType2_iflag = new Double(split[n]);
        }
        n++;
        init16();
        if (split[n].trim().isEmpty()) {
            DVTotCarVal = null;
        } else {
            DVTotCarVal = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVTotVanVal = null;
        } else {
            DVTotVanVal = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVTotMotBVal = null;
        } else {
            DVTotMotBVal = new Double(split[n]);
        }
        n++;
        init17();
        init18();
        init19();
        // Extras 8
                if (split[n].trim().isEmpty()) {
            vesvb3 = null;
        } else {
            vesvb3 = new Double(split[n]);
        }
        n++;
        init20();
        // Extras 9
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
        init21();
        // Extras 10
        if (split[n].trim().isEmpty()) {
            dvtotgir_3sf = null;
        } else {
            dvtotgir_3sf = new Double(split[n]);
        }
        n++;
        init22();
    }

}

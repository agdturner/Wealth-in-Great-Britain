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
    private final Double HIntro;
    // Extras 2
    private final Double EqReas1;
    private final Double EqReas2;
    private final Double EqReas3;
    private final Double EqReas4;
    // Extras 3
    private final Double VEstV1;
    private final Double VEsVb1;
    // Extras 3
    private final Double VEstV2;
    private final Double VEsVb2;
    // Extras 4
    private final Double VEstV_i;
    private final Double VEstV_iflag;
    private final Double VEstV2_i;
    private final Double VEstV2_iflag;
    private final Double VType_i;
    //private Double VType_iflag; // In both, but in very different places!
    // Extras 5
    private final Double VType2_iflag;
    // Extras 6
    private final Double DVTotCarVal;
    private final Double DVTotVanVal;
    private final Double DVTotMotBVal;
    // Extras 7
    private final Double vesvb3;
    // Extras 8
    private final Double CASEw3;
    private final Double CASEW2;
    private final Double casew1;
    // Extras 9
    private final Double dvtotgir_3sf;
    
    public WIGB_Wave4Household_Record(String line) {
        init0(line);
        // Extras 1
        if (split[n].trim().isEmpty()) {
            HIntro = null;
        } else {
            HIntro = new Double(split[n]);
        }
        n++;
        init1();
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
        // Extras 5
        if (split[n].trim().isEmpty()) {
            VEstV2 = null;
        } else {
            VEstV2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEsVb2 = null;
        } else {
            VEsVb2 = new Double(split[n]);
        }
        n++;
        init12();
        init13();
        init14();
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
            VEstV_i = null;
        } else {
            VEstV_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstV_iflag = null;
        } else {
            VEstV_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
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
        // Extras 8
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
        if (n != split.length) {
            int debug = 1;
            System.out.println("n(" +n +")!= split.length(" + split.length +")");
        }
    }

    /**
     * @return the HIntro
     */
    public Double getHIntro() {
        return HIntro;
    }

    /**
     * @return the EqReas1
     */
    public Double getEqReas1() {
        return EqReas1;
    }

    /**
     * @return the EqReas2
     */
    public Double getEqReas2() {
        return EqReas2;
    }

    /**
     * @return the EqReas3
     */
    public Double getEqReas3() {
        return EqReas3;
    }

    /**
     * @return the EqReas4
     */
    public Double getEqReas4() {
        return EqReas4;
    }

    /**
     * @return the VEstV1
     */
    public Double getVEstV1() {
        return VEstV1;
    }

    /**
     * @return the VEsVb1
     */
    public Double getVEsVb1() {
        return VEsVb1;
    }

    /**
     * @return the VEstV2
     */
    public Double getVEstV2() {
        return VEstV2;
    }

    /**
     * @return the VEsVb2
     */
    public Double getVEsVb2() {
        return VEsVb2;
    }

    /**
     * @return the VEstV_i
     */
    public Double getVEstV_i() {
        return VEstV_i;
    }

    /**
     * @return the VEstV_iflag
     */
    public Double getVEstV_iflag() {
        return VEstV_iflag;
    }

    /**
     * @return the VEstV2_i
     */
    public Double getVEstV2_i() {
        return VEstV2_i;
    }

    /**
     * @return the VEstV2_iflag
     */
    public Double getVEstV2_iflag() {
        return VEstV2_iflag;
    }

    /**
     * @return the VType_i
     */
    public Double getVType_i() {
        return VType_i;
    }

    /**
     * @return the VType2_iflag
     */
    public Double getVType2_iflag() {
        return VType2_iflag;
    }

    /**
     * @return the DVTotCarVal
     */
    public Double getDVTotCarVal() {
        return DVTotCarVal;
    }

    /**
     * @return the DVTotVanVal
     */
    public Double getDVTotVanVal() {
        return DVTotVanVal;
    }

    /**
     * @return the DVTotMotBVal
     */
    public Double getDVTotMotBVal() {
        return DVTotMotBVal;
    }

    /**
     * @return the vesvb3
     */
    public Double getVesvb3() {
        return vesvb3;
    }

    /**
     * @return the CASEw3
     */
    public Double getCASEw3() {
        return CASEw3;
    }

    /**
     * @return the CASEW2
     */
    public Double getCASEW2() {
        return CASEW2;
    }

    /**
     * @return the casew1
     */
    public Double getCasew1() {
        return casew1;
    }

    /**
     * @return the dvtotgir_3sf
     */
    public Double getDvtotgir_3sf() {
        return dvtotgir_3sf;
    }

    
}

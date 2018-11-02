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
    private final Double YearW5;
    private final Double MonthW5;
    // Extras 2
    private final Double MIntPaid1;
    private final Double MIntFix1;
    private final Double MIntRate1;
    // Extras 3
    private final Double MPastSPA1;
    private final Double MYPastSPA1;
    // Extras 4
    private final Double MEndVb8;
    private final Double MEndy8;
    // Extras 5
    private final Double MPastSPA2;
    private final Double MYPastSPA2;
    // Extras 6
    private final Double MIntPaid2;
    private final Double MIntFix2;
    private final Double MIntRate2;
    // Extras 7
    private final Double MPastSPA3;
    private final Double MYPastSPA3;
    // Extras 8
    private final Double MIntPaid3;
    private final Double MIntFix3;
    private final Double MIntRate3;
    // Extras 9
    private final Double VType3;
    // Extras 10
    private Double VType1_i;
    private final Double VCarN_i;
    private final Double VCarN_iflag;
    private final Double VEstVTotal;
    private final Double VEstVTotalB;
    private final Double VEstVTotalB_i;
    private final Double VEstVTotalB_iflag;
    private Double VEstVTotal_i;
    private final Double VEstVTotal_iflag;
    private final Double VOTyp_iflag;
    private final Double VOVlB_i;
    private final Double VOVlB_iflag;
    // Extras 11
    private final Double mendv10i;
    private final Double mendv10iflag;
    private final Double mendv11i;
    private final Double mendv11iflag;
    // Extras 11
    private final Double VType1i;
    // Extras 12
    private final Double VType3i;
    // Extras 13
    private final Double DVTotGIR;
    // Extras 14
    private final Double NumAdISw;
    // Extras 15
    private final Double CASEw4;
    private final Double CASEw3;
    private final Double CASEW2;
    private final Double casew1;

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

    /**
     * @return the YearW5
     */
    public Double getYearW5() {
        return YearW5;
    }

    /**
     * @return the MonthW5
     */
    public Double getMonthW5() {
        return MonthW5;
    }

    /**
     * @return the MIntPaid1
     */
    public Double getMIntPaid1() {
        return MIntPaid1;
    }

    /**
     * @return the MIntFix1
     */
    public Double getMIntFix1() {
        return MIntFix1;
    }

    /**
     * @return the MIntRate1
     */
    public Double getMIntRate1() {
        return MIntRate1;
    }

    /**
     * @return the MPastSPA1
     */
    public Double getMPastSPA1() {
        return MPastSPA1;
    }

    /**
     * @return the MYPastSPA1
     */
    public Double getMYPastSPA1() {
        return MYPastSPA1;
    }

    /**
     * @return the MEndVb8
     */
    public Double getMEndVb8() {
        return MEndVb8;
    }

    /**
     * @return the MEndy8
     */
    public Double getMEndy8() {
        return MEndy8;
    }

    /**
     * @return the MPastSPA2
     */
    public Double getMPastSPA2() {
        return MPastSPA2;
    }

    /**
     * @return the MYPastSPA2
     */
    public Double getMYPastSPA2() {
        return MYPastSPA2;
    }

    /**
     * @return the MIntPaid2
     */
    public Double getMIntPaid2() {
        return MIntPaid2;
    }

    /**
     * @return the MIntFix2
     */
    public Double getMIntFix2() {
        return MIntFix2;
    }

    /**
     * @return the MIntRate2
     */
    public Double getMIntRate2() {
        return MIntRate2;
    }

    /**
     * @return the MPastSPA3
     */
    public Double getMPastSPA3() {
        return MPastSPA3;
    }

    /**
     * @return the MYPastSPA3
     */
    public Double getMYPastSPA3() {
        return MYPastSPA3;
    }

    /**
     * @return the MIntPaid3
     */
    public Double getMIntPaid3() {
        return MIntPaid3;
    }

    /**
     * @return the MIntFix3
     */
    public Double getMIntFix3() {
        return MIntFix3;
    }

    /**
     * @return the MIntRate3
     */
    public Double getMIntRate3() {
        return MIntRate3;
    }

    /**
     * @return the VType3
     */
    public Double getVType3() {
        return VType3;
    }

    /**
     * @return the VType1_i
     */
    public Double getVType1_i() {
        return VType1_i;
    }

    /**
     * @return the VCarN_i
     */
    public Double getVCarN_i() {
        return VCarN_i;
    }

    /**
     * @return the VCarN_iflag
     */
    public Double getVCarN_iflag() {
        return VCarN_iflag;
    }

    /**
     * @return the VEstVTotal
     */
    public Double getVEstVTotal() {
        return VEstVTotal;
    }

    /**
     * @return the VEstVTotalB
     */
    public Double getVEstVTotalB() {
        return VEstVTotalB;
    }

    /**
     * @return the VEstVTotalB_i
     */
    public Double getVEstVTotalB_i() {
        return VEstVTotalB_i;
    }

    /**
     * @return the VEstVTotalB_iflag
     */
    public Double getVEstVTotalB_iflag() {
        return VEstVTotalB_iflag;
    }

    /**
     * @return the VEstVTotal_i
     */
    public Double getVEstVTotal_i() {
        return VEstVTotal_i;
    }

    /**
     * @return the VEstVTotal_iflag
     */
    public Double getVEstVTotal_iflag() {
        return VEstVTotal_iflag;
    }

    /**
     * @return the VOTyp_iflag
     */
    public Double getVOTyp_iflag() {
        return VOTyp_iflag;
    }

    /**
     * @return the VOVlB_i
     */
    public Double getVOVlB_i() {
        return VOVlB_i;
    }

    /**
     * @return the VOVlB_iflag
     */
    public Double getVOVlB_iflag() {
        return VOVlB_iflag;
    }

    /**
     * @return the mendv10i
     */
    public Double getMendv10i() {
        return mendv10i;
    }

    /**
     * @return the mendv10iflag
     */
    public Double getMendv10iflag() {
        return mendv10iflag;
    }

    /**
     * @return the mendv11i
     */
    public Double getMendv11i() {
        return mendv11i;
    }

    /**
     * @return the mendv11iflag
     */
    public Double getMendv11iflag() {
        return mendv11iflag;
    }

    /**
     * @return the VType1i
     */
    public Double getVType1i() {
        return VType1i;
    }

    /**
     * @return the VType3i
     */
    public Double getVType3i() {
        return VType3i;
    }

    /**
     * @return the DVTotGIR
     */
    public Double getDVTotGIR() {
        return DVTotGIR;
    }

    /**
     * @return the NumAdISw
     */
    public Double getNumAdISw() {
        return NumAdISw;
    }

    /**
     * @return the CASEw4
     */
    public Double getCASEw4() {
        return CASEw4;
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
    
    
}

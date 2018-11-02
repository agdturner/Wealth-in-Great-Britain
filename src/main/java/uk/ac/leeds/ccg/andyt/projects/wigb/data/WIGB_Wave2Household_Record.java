/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.leeds.ccg.andyt.projects.wigb.data;

/**
 *
 * @author spcar
 */
public class WIGB_Wave2Household_Record extends WIGB_Wave1Or2Household_Record {

    //Extras 0
    private Double H_Flag1;
    private Double H_Flag2;
    private Double H_Flag4;
    private Double H_Flag5;

    //Extras 1
    private Double EqNew;
    private Double EqNew_i;
    private Double EQnew_iflag;
    private Double EqOld;
    private Double EqOld_i;
    private Double EQold_iflag;

    //Extras 2
    private Double EqReas5;
    private Double EqReas6;
    private Double EqReas7;
    private Double EqReas8;
    private Double EqReas9;

    //Extras 3
    private Double HExt1;
    private Double HExt2;
    private Double HExt3;
    private Double HExt4;

    //Extras 4
    private Double HMortg;
    private Double HPHYSW;

    //Extras 5
    private Double MArrsV2;
    private Double MArrsV3;
    private Double MArrsV;

    //Extras 6
    private Double MChgeNum;
    private Double MChge;

    //Extras 7
    private Double MEndv10;

    //Extras 8
    private Double MEndv12;
    private Double MEndv13;
    private Double MEndv14;
    private Double MEndv15;

    //Extras 9
    private Double MEndv9;
    private Double MEndVb10;

    //Extras 10
    private Double MEndVb12;
    private Double MEndVb13;
    private Double MEndVb14;
    private Double MEndVb15;

    //Extras 11
    private Double MEndVb9;

    //Extras 12
    private Double MEndy10;

    //Extras 13
    private Double MEndy12;
    private Double MEndy13;
    private Double MEndy14;
    private Double MEndy15;

    //Extras 14
    private Double MEndy9;

    //Extras 15
    private Double MExtn;
    private Double MExtRs1;
    private Double MExtRs2;
    private Double MExtRs3;
    private Double MExtRs4;
    private Double MExtRs5;
    private Double MExtRs6;
    private Double MExtRs7;
    private Double MExtRs8;
    private Double MExtRs9;

    //Extras 16
    private Double MInc10;

    //Extras 17
    private Double MInc13;
    private Double MInc14;
    private Double MInc15;

    //Extras 18
    private Double MNumbN;
    private Double MNumbN_i;
    private Double mnumbn_iflag;
    private Double MNumbO;
    private Double MNumbO_i;
    private Double mnumbo_iflag;

    //Extras 19
    private Double MoArr_sum;

    //Extras 20
    private Double MorTID2;
    private Double MorTID3;
    private Double MorTID;

    //Extras 21
    private Double MReas14;
    private Double MReas15;

    //Extras 22
    private Double MReas24;

    //Extras 23
    private Double MValB2_I;
    private Double MValB2_iflag;

    //Extras 24
    private Double MW1Chk1;
    private Double MW1Chk2;
    private Double MW1Chk3;
    private Double MYIfCh2;
    private Double MYIfCh3;
    private Double MYIfCh;

    //Extras 25
    private Double VCAddN;
    private Double VCAdd;

    //Extras 26
    private Double VPers_i;
    private Double VPers_iflag;

    //Extras 27
    private Double BuyLGdsT_Sum;

    //Extras 28
    private Double DVBLtDebt;
    private Double DVBLtVal;

    //Extras 29
    private Double DVNwFLn_sum;
    private Double DVNwILn_sum;
    
    //Extras 30
    private Double NoUnits;
    
    //Extras 31
    private Double NumCivPtr;
    
    //Extras 32
    private Double  NumNDep;
    
    //Extras 33
    private Double votyp1_i;
    private Double votyp2_i;
    private Double votyp3_i;
    private Double votyp4_i;
    private Double votyp5_i;
    private Double CASEW2;

    public WIGB_Wave2Household_Record(String line) {
        init0(line);
        if (split[n].trim().isEmpty()) {
            H_Flag1 = null;
        } else {
            H_Flag1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            H_Flag2 = null;
        } else {
            H_Flag2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            H_Flag4 = null;
        } else {
            H_Flag4 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            H_Flag5 = null;
        } else {
            H_Flag5 = new Double(split[n]);
        }
        n++;
        init1();
        if (split[n].trim().isEmpty()) {
            EqNew = null;
        } else {
            EqNew = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqNew_i = null;
        } else {
            EqNew_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EQnew_iflag = null;
        } else {
            EQnew_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqOld = null;
        } else {
            EqOld = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqOld_i = null;
        } else {
            EqOld_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EQold_iflag = null;
        } else {
            EQold_iflag = new Double(split[n]);
        }
        n++;
        init2();
        if (split[n].trim().isEmpty()) {
            EqReas5 = null;
        } else {
            EqReas5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqReas6 = null;
        } else {
            EqReas6 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqReas7 = null;
        } else {
            EqReas7 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqReas8 = null;
        } else {
            EqReas8 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqReas9 = null;
        } else {
            EqReas9 = new Double(split[n]);
        }
        n++;
        init3();
        init4();
        init5();
        if (split[n].trim().isEmpty()) {
            HExt1 = null;
        } else {
            HExt1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HExt2 = null;
        } else {
            HExt2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HExt3 = null;
        } else {
            HExt3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HExt4 = null;
        } else {
            HExt4 = new Double(split[n]);
        }
        n++;
        init6();
        if (split[n].trim().isEmpty()) {
            HMortg = null;
        } else {
            HMortg = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HPHYSW = null;
        } else {
            HPHYSW = new Double(split[n]);
        }
        n++;
        init7();
        init8();
        if (split[n].trim().isEmpty()) {
            MArrsV2 = null;
        } else {
            MArrsV2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrsV3 = null;
        } else {
            MArrsV3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrsV = null;
        } else {
            MArrsV = new Double(split[n]);
        }
        n++;
        init9();
        if (split[n].trim().isEmpty()) {
            MChgeNum = null;
        } else {
            MChgeNum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MChge = null;
        } else {
            MChge = new Double(split[n]);
        }
        n++;
        init10();
        init11();
        init12();
        if (split[n].trim().isEmpty()) {
            MEndv10 = null;
        } else {
            MEndv10 = new Double(split[n]);
        }
        init13();
        if (split[n].trim().isEmpty()) {
            MEndv12 = null;
        } else {
            MEndv12 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv13 = null;
        } else {
            MEndv13 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv14 = null;
        } else {
            MEndv14 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv15 = null;
        } else {
            MEndv15 = new Double(split[n]);
        }
        n++;
        init14();
        init15();
        if (split[n].trim().isEmpty()) {
            MEndv9 = null;
        } else {
            MEndv9 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb10 = null;
        } else {
            MEndVb10 = new Double(split[n]);
        }
        n++;
        init16();
        if (split[n].trim().isEmpty()) {
            MEndVb12 = null;
        } else {
            MEndVb12 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb13 = null;
        } else {
            MEndVb13 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb14 = null;
        } else {
            MEndVb14 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb15 = null;
        } else {
            MEndVb15 = new Double(split[n]);
        }
        n++;
        init17();
        if (split[n].trim().isEmpty()) {
            MEndVb9 = null;
        } else {
            MEndVb9 = new Double(split[n]);
        }
        n++;
        init18();
        if (split[n].trim().isEmpty()) {
            MEndy10 = null;
        } else {
            MEndy10 = new Double(split[n]);
        }
        n++;
        init19();
        if (split[n].trim().isEmpty()) {
            MEndy12 = null;
        } else {
            MEndy12 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy13 = null;
        } else {
            MEndy13 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy14 = null;
        } else {
            MEndy14 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy15 = null;
        } else {
            MEndy15 = new Double(split[n]);
        }
        n++;
        init20();
        if (split[n].trim().isEmpty()) {
            MEndy9 = null;
        } else {
            MEndy9 = new Double(split[n]);
        }
        n++;
        init21();
        if (split[n].trim().isEmpty()) {
            MExtn = null;
        } else {
            MExtn = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs1 = null;
        } else {
            MExtRs1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs2 = null;
        } else {
            MExtRs2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs3 = null;
        } else {
            MExtRs3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs4 = null;
        } else {
            MExtRs4 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs5 = null;
        } else {
            MExtRs5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs6 = null;
        } else {
            MExtRs6 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs7 = null;
        } else {
            MExtRs7 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs8 = null;
        } else {
            MExtRs8 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs9 = null;
        } else {
            MExtRs9 = new Double(split[n]);
        }
        n++;
        init22();
        if (split[n].trim().isEmpty()) {
            MInc10 = null;
        } else {
            MInc10 = new Double(split[n]);
        }
        n++;
        init23();
        if (split[n].trim().isEmpty()) {
            MInc13 = null;
        } else {
            MInc13 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc14 = null;
        } else {
            MInc14 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc15 = null;
        } else {
            MInc15 = new Double(split[n]);
        }
        n++;
        init24();
        init25();
        if (split[n].trim().isEmpty()) {
            MNumbN = null;
        } else {
            MNumbN = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNumbN_i = null;
        } else {
            MNumbN_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mnumbn_iflag = null;
        } else {
            mnumbn_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNumbO = null;
        } else {
            MNumbO = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNumbO_i = null;
        } else {
            MNumbO_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mnumbo_iflag = null;
        } else {
            mnumbo_iflag = new Double(split[n]);
        }
        n++;
        init26();
        if (split[n].trim().isEmpty()) {
            MoArr_sum = null;
        } else {
            MoArr_sum = new Double(split[n]);
        }
        n++;
        init27();
        if (split[n].trim().isEmpty()) {
            MorTID2 = null;
        } else {
            MorTID2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MorTID3 = null;
        } else {
            MorTID3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MorTID = null;
        } else {
            MorTID = new Double(split[n]);
        }
        n++;
        init28();
        if (split[n].trim().isEmpty()) {
            MReas14 = null;
        } else {
            MReas14 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas15 = null;
        } else {
            MReas15 = new Double(split[n]);
        }
        n++;
        init29();
        if (split[n].trim().isEmpty()) {
            MReas24 = null;
        } else {
            MReas24 = new Double(split[n]);
        }
        n++;
        init30();
        if (split[n].trim().isEmpty()) {
            MValB2_I = null;
        } else {
            MValB2_I = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MValB2_iflag = null;
        } else {
            MValB2_iflag = new Double(split[n]);
        }
        n++;
        init31();
        if (split[n].trim().isEmpty()) {
            MW1Chk1 = null;
        } else {
            MW1Chk1 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MW1Chk2 = null;
        } else {
            MW1Chk2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MW1Chk3 = null;
        } else {
            MW1Chk3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYIfCh2 = null;
        } else {
            MYIfCh2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYIfCh3 = null;
        } else {
            MYIfCh3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYIfCh = null;
        } else {
            MYIfCh = new Double(split[n]);
        }
        n++;
        init32();
        init33();
        if (split[n].trim().isEmpty()) {
            VCAddN = null;
        } else {
            VCAddN = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VCAdd = null;
        } else {
            VCAdd = new Double(split[n]);
        }
        n++;
        init34();
        init35();
        init36();
        init37();
        init38();
        init39();
        init40();
        init41();
        init42();
        if (split[n].trim().isEmpty()) {
            VPers_i = null;
        } else {
            VPers_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPers_iflag = null;
        } else {
            VPers_iflag = new Double(split[n]);
        }
        n++;
        init43();
        init44();
        if (split[n].trim().isEmpty()) {
            BuyLGdsT_Sum = null;
        } else {
            BuyLGdsT_Sum = new Double(split[n]);
        }
        n++;
        init45();
        if (split[n].trim().isEmpty()) {
            DVBLtDebt = null;
        } else {
            DVBLtDebt = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVBLtVal = null;
        } else {
            DVBLtVal = new Double(split[n]);
        }
        n++;
        init46();
        if (split[n].trim().isEmpty()) {
            DVNwFLn_sum = null;
        } else {
            DVNwFLn_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVNwILn_sum = null;
        } else {
            DVNwILn_sum = new Double(split[n]);
        }
        n++;
        init47();
        init48();
        init49();
        init50();
        init51();
        init52();
        init53();
        if (split[n].trim().isEmpty()) {
            NoUnits = null;
        } else {
            NoUnits = new Double(split[n]);
        }
        n++;
        init54();
        if (split[n].trim().isEmpty()) {
            NumCivPtr = null;
        } else {
            NumCivPtr = new Double(split[n]);
        }
        n++;
        init55();
        if (split[n].trim().isEmpty()) {
            NumNDep = null;
        } else {
            NumNDep = new Double(split[n]);
        }
        n++;
        init56();
        if (split[n].trim().isEmpty()) {
            votyp1_i = null;
        } else {
            votyp1_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            votyp2_i = null;
        } else {
            votyp2_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            votyp3_i = null;
        } else {
            votyp3_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            votyp4_i = null;
        } else {
            votyp4_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            votyp5_i = null;
        } else {
            votyp5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            CASEW2 = null;
        } else {
            CASEW2 = new Double(split[n]);
        }
        n++;
        init57();
    }

    /**
     * @return the H_Flag1
     */
    public Double getH_Flag1() {
        return H_Flag1;
    }

    /**
     * @return the H_Flag2
     */
    public Double getH_Flag2() {
        return H_Flag2;
    }

    /**
     * @return the H_Flag4
     */
    public Double getH_Flag4() {
        return H_Flag4;
    }

    /**
     * @return the H_Flag5
     */
    public Double getH_Flag5() {
        return H_Flag5;
    }

    /**
     * @return the EqNew
     */
    public Double getEqNew() {
        return EqNew;
    }

    /**
     * @return the EqNew_i
     */
    public Double getEqNew_i() {
        return EqNew_i;
    }

    /**
     * @return the EQnew_iflag
     */
    public Double getEQnew_iflag() {
        return EQnew_iflag;
    }

    /**
     * @return the EqOld
     */
    public Double getEqOld() {
        return EqOld;
    }

    /**
     * @return the EqOld_i
     */
    public Double getEqOld_i() {
        return EqOld_i;
    }

    /**
     * @return the EQold_iflag
     */
    public Double getEQold_iflag() {
        return EQold_iflag;
    }

    /**
     * @return the EqReas5
     */
    public Double getEqReas5() {
        return EqReas5;
    }

    /**
     * @return the EqReas6
     */
    public Double getEqReas6() {
        return EqReas6;
    }

    /**
     * @return the EqReas7
     */
    public Double getEqReas7() {
        return EqReas7;
    }

    /**
     * @return the EqReas8
     */
    public Double getEqReas8() {
        return EqReas8;
    }

    /**
     * @return the EqReas9
     */
    public Double getEqReas9() {
        return EqReas9;
    }

    /**
     * @return the HExt1
     */
    public Double getHExt1() {
        return HExt1;
    }

    /**
     * @return the HExt2
     */
    public Double getHExt2() {
        return HExt2;
    }

    /**
     * @return the HExt3
     */
    public Double getHExt3() {
        return HExt3;
    }

    /**
     * @return the HExt4
     */
    public Double getHExt4() {
        return HExt4;
    }

    /**
     * @return the HMortg
     */
    public Double getHMortg() {
        return HMortg;
    }

    /**
     * @return the HPHYSW
     */
    public Double getHPHYSW() {
        return HPHYSW;
    }

    /**
     * @return the MArrsV2
     */
    public Double getMArrsV2() {
        return MArrsV2;
    }

    /**
     * @return the MArrsV3
     */
    public Double getMArrsV3() {
        return MArrsV3;
    }

    /**
     * @return the MArrsV
     */
    public Double getMArrsV() {
        return MArrsV;
    }

    /**
     * @return the MChgeNum
     */
    public Double getMChgeNum() {
        return MChgeNum;
    }

    /**
     * @return the MChge
     */
    public Double getMChge() {
        return MChge;
    }

    /**
     * @return the MEndv10
     */
    public Double getMEndv10() {
        return MEndv10;
    }

    /**
     * @return the MEndv12
     */
    public Double getMEndv12() {
        return MEndv12;
    }

    /**
     * @return the MEndv13
     */
    public Double getMEndv13() {
        return MEndv13;
    }

    /**
     * @return the MEndv14
     */
    public Double getMEndv14() {
        return MEndv14;
    }

    /**
     * @return the MEndv15
     */
    public Double getMEndv15() {
        return MEndv15;
    }

    /**
     * @return the MEndv9
     */
    public Double getMEndv9() {
        return MEndv9;
    }

    /**
     * @return the MEndVb10
     */
    public Double getMEndVb10() {
        return MEndVb10;
    }

    /**
     * @return the MEndVb12
     */
    public Double getMEndVb12() {
        return MEndVb12;
    }

    /**
     * @return the MEndVb13
     */
    public Double getMEndVb13() {
        return MEndVb13;
    }

    /**
     * @return the MEndVb14
     */
    public Double getMEndVb14() {
        return MEndVb14;
    }

    /**
     * @return the MEndVb15
     */
    public Double getMEndVb15() {
        return MEndVb15;
    }

    /**
     * @return the MEndVb9
     */
    public Double getMEndVb9() {
        return MEndVb9;
    }

    /**
     * @return the MEndy10
     */
    public Double getMEndy10() {
        return MEndy10;
    }

    /**
     * @return the MEndy12
     */
    public Double getMEndy12() {
        return MEndy12;
    }

    /**
     * @return the MEndy13
     */
    public Double getMEndy13() {
        return MEndy13;
    }

    /**
     * @return the MEndy14
     */
    public Double getMEndy14() {
        return MEndy14;
    }

    /**
     * @return the MEndy15
     */
    public Double getMEndy15() {
        return MEndy15;
    }

    /**
     * @return the MEndy9
     */
    public Double getMEndy9() {
        return MEndy9;
    }

    /**
     * @return the MExtn
     */
    public Double getMExtn() {
        return MExtn;
    }

    /**
     * @return the MExtRs1
     */
    public Double getMExtRs1() {
        return MExtRs1;
    }

    /**
     * @return the MExtRs2
     */
    public Double getMExtRs2() {
        return MExtRs2;
    }

    /**
     * @return the MExtRs3
     */
    public Double getMExtRs3() {
        return MExtRs3;
    }

    /**
     * @return the MExtRs4
     */
    public Double getMExtRs4() {
        return MExtRs4;
    }

    /**
     * @return the MExtRs5
     */
    public Double getMExtRs5() {
        return MExtRs5;
    }

    /**
     * @return the MExtRs6
     */
    public Double getMExtRs6() {
        return MExtRs6;
    }

    /**
     * @return the MExtRs7
     */
    public Double getMExtRs7() {
        return MExtRs7;
    }

    /**
     * @return the MExtRs8
     */
    public Double getMExtRs8() {
        return MExtRs8;
    }

    /**
     * @return the MExtRs9
     */
    public Double getMExtRs9() {
        return MExtRs9;
    }

    /**
     * @return the MInc10
     */
    public Double getMInc10() {
        return MInc10;
    }

    /**
     * @return the MInc13
     */
    public Double getMInc13() {
        return MInc13;
    }

    /**
     * @return the MInc14
     */
    public Double getMInc14() {
        return MInc14;
    }

    /**
     * @return the MInc15
     */
    public Double getMInc15() {
        return MInc15;
    }

    /**
     * @return the MNumbN
     */
    public Double getMNumbN() {
        return MNumbN;
    }

    /**
     * @return the MNumbN_i
     */
    public Double getMNumbN_i() {
        return MNumbN_i;
    }

    /**
     * @return the mnumbn_iflag
     */
    public Double getMnumbn_iflag() {
        return mnumbn_iflag;
    }

    /**
     * @return the MNumbO
     */
    public Double getMNumbO() {
        return MNumbO;
    }

    /**
     * @return the MNumbO_i
     */
    public Double getMNumbO_i() {
        return MNumbO_i;
    }

    /**
     * @return the mnumbo_iflag
     */
    public Double getMnumbo_iflag() {
        return mnumbo_iflag;
    }

    /**
     * @return the MoArr_sum
     */
    public Double getMoArr_sum() {
        return MoArr_sum;
    }

    /**
     * @return the MorTID2
     */
    public Double getMorTID2() {
        return MorTID2;
    }

    /**
     * @return the MorTID3
     */
    public Double getMorTID3() {
        return MorTID3;
    }

    /**
     * @return the MorTID
     */
    public Double getMorTID() {
        return MorTID;
    }

    /**
     * @return the MReas14
     */
    public Double getMReas14() {
        return MReas14;
    }

    /**
     * @return the MReas15
     */
    public Double getMReas15() {
        return MReas15;
    }

    /**
     * @return the MReas24
     */
    public Double getMReas24() {
        return MReas24;
    }

    /**
     * @return the MValB2_I
     */
    public Double getMValB2_I() {
        return MValB2_I;
    }

    /**
     * @return the MValB2_iflag
     */
    public Double getMValB2_iflag() {
        return MValB2_iflag;
    }

    /**
     * @return the MW1Chk1
     */
    public Double getMW1Chk1() {
        return MW1Chk1;
    }

    /**
     * @return the MW1Chk2
     */
    public Double getMW1Chk2() {
        return MW1Chk2;
    }

    /**
     * @return the MW1Chk3
     */
    public Double getMW1Chk3() {
        return MW1Chk3;
    }

    /**
     * @return the MYIfCh2
     */
    public Double getMYIfCh2() {
        return MYIfCh2;
    }

    /**
     * @return the MYIfCh3
     */
    public Double getMYIfCh3() {
        return MYIfCh3;
    }

    /**
     * @return the MYIfCh
     */
    public Double getMYIfCh() {
        return MYIfCh;
    }

    /**
     * @return the VCAddN
     */
    public Double getVCAddN() {
        return VCAddN;
    }

    /**
     * @return the VCAdd
     */
    public Double getVCAdd() {
        return VCAdd;
    }

    /**
     * @return the VPers_i
     */
    public Double getVPers_i() {
        return VPers_i;
    }

    /**
     * @return the VPers_iflag
     */
    public Double getVPers_iflag() {
        return VPers_iflag;
    }

    /**
     * @return the BuyLGdsT_Sum
     */
    public Double getBuyLGdsT_Sum() {
        return BuyLGdsT_Sum;
    }

    /**
     * @return the DVBLtDebt
     */
    public Double getDVBLtDebt() {
        return DVBLtDebt;
    }

    /**
     * @return the DVBLtVal
     */
    public Double getDVBLtVal() {
        return DVBLtVal;
    }

    /**
     * @return the DVNwFLn_sum
     */
    public Double getDVNwFLn_sum() {
        return DVNwFLn_sum;
    }

    /**
     * @return the DVNwILn_sum
     */
    public Double getDVNwILn_sum() {
        return DVNwILn_sum;
    }

    /**
     * @return the NoUnits
     */
    public Double getNoUnits() {
        return NoUnits;
    }

    /**
     * @return the NumCivPtr
     */
    public Double getNumCivPtr() {
        return NumCivPtr;
    }

    /**
     * @return the NumNDep
     */
    public Double getNumNDep() {
        return NumNDep;
    }

    /**
     * @return the votyp1_i
     */
    public Double getVotyp1_i() {
        return votyp1_i;
    }

    /**
     * @return the votyp2_i
     */
    public Double getVotyp2_i() {
        return votyp2_i;
    }

    /**
     * @return the votyp3_i
     */
    public Double getVotyp3_i() {
        return votyp3_i;
    }

    /**
     * @return the votyp4_i
     */
    public Double getVotyp4_i() {
        return votyp4_i;
    }

    /**
     * @return the votyp5_i
     */
    public Double getVotyp5_i() {
        return votyp5_i;
    }

    /**
     * @return the CASEW2
     */
    public Double getCASEW2() {
        return CASEW2;
    }
}

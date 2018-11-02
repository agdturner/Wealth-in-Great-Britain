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
}

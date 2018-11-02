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
public class WIGB_Wave1Household_Record extends WIGB_Wave1Or2Household_Record {

    //Extras 0
    private Double GContMn;
    private Double GContRc;

    //Extras 1
    private Double GContVl;

    //Extras 2
    private Double HRTBYr;

    //Extras 3
    private Double MDbeh2;
    private Double MDbeh3;
    private Double MDbeh;
    private Double MDCap2;
    private Double MDCap3;
    private Double MDCap;

    //Extras 4
    private Double MDRst2;
    private Double MDRst3;
    private Double MDRst;
    private Double MDWPf2;
    private Double MDWPf3;
    private Double MDWPf;

    //Extras 5
    private Double Mendnum3_i;
    private Double Mendnum3_iflag;

    //Extras 6
    private Double MEndv11_i;
    private Double MEndv11_iflag;

    //Extras 7
    private Double MEndv5_i;
    private Double MEndv5_iflag;

    //Extras 8
    private Double MEver;

    //Extras 9
    private Double MNeg3_i;
    private Double MNeg3_iflag;

    //Extras 10
    private Double NumCivPtr;

    //Extras 11
    private Double VCarO;
    private Double Vcarw;

    //Extras 12
    private Double VModu;
    private Double VNumV2;
    private Double VNumV3;
    private Double VNumV4;
    private Double VNumV5;
    private Double VNumV;

    //Extras 13
    private Double VOTyp1_i;

    //Extras 14
    private Double VOTyp2_i;

    //Extras 15
    private Double VOTyp3_i;

    //Extras 16
    private Double VOTyp4_i;

    //Extras 17
    private Double VOTyp5_i;

    //Extras 18
    private Double VPers2;
    private Double VPers3;
    private Double VPers4;
    private Double VPers5;
    private Double Vpers6;
    private Double VPers6_i;
    private Double VPers6_iflag;

    //Extras 19
    private Double VShPct2;
    private Double VShPct3;
    private Double VShPct4;
    private Double VShPct5;
    private Double VShPct;

    //Extras 20
    private Double DVPEPV_sum;

    //Extras 21
    private Double HMortg;

    //Extras 22
    private Double HPHYSW;

    //Extras 23
    private Double LandOvSeaT_Sum;

    //Extras 24
    private Double MoArr_sum;

    //Extras 25
    private Double Hhldr;

    

    public WIGB_Wave1Household_Record(String line) {
        init0(line);
        init1();
        init2();
        init3();
        if (split[n].trim().isEmpty()) {
            GContMn = null;
        } else {
            GContMn = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            GContRc = null;
        } else {
            GContRc = new Double(split[n]);
        }
        n++;
        init4();
        if (split[n].trim().isEmpty()) {
            GContVl = null;
        } else {
            GContVl = new Double(split[n]);
        }
        init5();
        init6();
        init7();
        if (split[n].trim().isEmpty()) {
            HRTBYr = null;
        } else {
            HRTBYr = new Double(split[n]);
        }
        init8();
        init9();
        if (split[n].trim().isEmpty()) {
            MDbeh2 = null;
        } else {
            MDbeh2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDbeh3 = null;
        } else {
            MDbeh3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDbeh = null;
        } else {
            MDbeh = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDCap2 = null;
        } else {
            MDCap2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDCap3 = null;
        } else {
            MDCap3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDCap = null;
        } else {
            MDCap = new Double(split[n]);
        }
        n++;
        init10();
        if (split[n].trim().isEmpty()) {
            MDRst2 = null;
        } else {
            MDRst2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDRst3 = null;
        } else {
            MDRst3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDRst = null;
        } else {
            MDRst = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDWPf2 = null;
        } else {
            MDWPf2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDWPf3 = null;
        } else {
            MDWPf3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MDWPf = null;
        } else {
            MDWPf = new Double(split[n]);
        }
        n++;
        init11();
        if (split[n].trim().isEmpty()) {
            Mendnum3_i = null;
        } else {
            Mendnum3_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Mendnum3_iflag = null;
        } else {
            Mendnum3_iflag = new Double(split[n]);
        }
        n++;
        init12();
        init13();
        if (split[n].trim().isEmpty()) {
            MEndv11_i = null;
        } else {
            MEndv11_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv11_iflag = null;
        } else {
            MEndv11_iflag = new Double(split[n]);
        }
        n++;
        init14();
        if (split[n].trim().isEmpty()) {
            MEndv5_i = null;
        } else {
            MEndv5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv5_iflag = null;
        } else {
            MEndv5_iflag = new Double(split[n]);
        }
        n++;
        init15();
        init16();
        init17();
        init18();
        init19();
        init20();
        init21();
        if (split[n].trim().isEmpty()) {
            MEver = null;
        } else {
            MEver = new Double(split[n]);
        }
        n++;
        init22();
        init23();
        init24();
        if (split[n].trim().isEmpty()) {
            MNeg3_i = null;
        } else {
            MNeg3_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNeg3_iflag = null;
        } else {
            MNeg3_iflag = new Double(split[n]);
        }
        n++;
        init25();
        init26();
        init27();
        init28();
        init29();
        init30();
        init31();
        init32();
        if (split[n].trim().isEmpty()) {
            NumCivPtr = null;
        } else {
            NumCivPtr = new Double(split[n]);
        }
        n++;
        init33();
        init34();
        if (split[n].trim().isEmpty()) {
            VCarO = null;
        } else {
            VCarO = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Vcarw = null;
        } else {
            Vcarw = new Double(split[n]);
        }
        n++;
        init35();
        if (split[n].trim().isEmpty()) {
            VModu = null;
        } else {
            VModu = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VNumV2 = null;
        } else {
            VNumV2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VNumV3 = null;
        } else {
            VNumV3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VNumV4 = null;
        } else {
            VNumV4 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VNumV5 = null;
        } else {
            VNumV5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VNumV = null;
        } else {
            VNumV = new Double(split[n]);
        }
        n++;
        init36();
        if (split[n].trim().isEmpty()) {
            VOTyp1_i = null;
        } else {
            VOTyp1_i = new Double(split[n]);
        }
        n++;
        init37();
        if (split[n].trim().isEmpty()) {
            VOTyp2_i = null;
        } else {
            VOTyp2_i = new Double(split[n]);
        }
        n++;
        init38();
        if (split[n].trim().isEmpty()) {
            VOTyp3_i = null;
        } else {
            VOTyp3_i = new Double(split[n]);
        }
        n++;
        init39();
        if (split[n].trim().isEmpty()) {
            VOTyp4_i = null;
        } else {
            VOTyp4_i = new Double(split[n]);
        }
        n++;
        init40();
        if (split[n].trim().isEmpty()) {
            VOTyp5_i = null;
        } else {
            VOTyp5_i = new Double(split[n]);
        }
        n++;
        init41();
        if (split[n].trim().isEmpty()) {
            VPers2 = null;
        } else {
            VPers2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPers3 = null;
        } else {
            VPers3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPers4 = null;
        } else {
            VPers4 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPers5 = null;
        } else {
            VPers5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Vpers6 = null;
        } else {
            Vpers6 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPers6_i = null;
        } else {
            VPers6_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPers6_iflag = null;
        } else {
            VPers6_iflag = new Double(split[n]);
        }
        n++;
        init42();
        init43();
        if (split[n].trim().isEmpty()) {
            VShPct2 = null;
        } else {
            VShPct2 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VShPct3 = null;
        } else {
            VShPct3 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VShPct4 = null;
        } else {
            VShPct4 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VShPct5 = null;
        } else {
            VShPct5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VShPct = null;
        } else {
            VShPct = new Double(split[n]);
        }
        n++;
        init44();
        init45();
        init46();
        init47();
        if (split[n].trim().isEmpty()) {
            DVPEPV_sum = null;
        } else {
            DVPEPV_sum = new Double(split[n]);
        }
        n++;
        init48();
        if (split[n].trim().isEmpty()) {
            HMortg = null;
        } else {
            HMortg = new Double(split[n]);
        }
        n++;
        init49();
        if (split[n].trim().isEmpty()) {
            HPHYSW = null;
        } else {
            HPHYSW = new Double(split[n]);
        }
        n++;
        init50();
        if (split[n].trim().isEmpty()) {
            LandOvSeaT_Sum = null;
        } else {
            LandOvSeaT_Sum = new Double(split[n]);
        }
        n++;
        init51();
        if (split[n].trim().isEmpty()) {
            MoArr_sum = null;
        } else {
            MoArr_sum = new Double(split[n]);
        }
        n++;
        init52();
        if (split[n].trim().isEmpty()) {
            Hhldr = null;
        } else {
            Hhldr = new Double(split[n]);
        }
        n++;
        init53();
        init54();
        init55();
        init56();
        init57();
    }
}

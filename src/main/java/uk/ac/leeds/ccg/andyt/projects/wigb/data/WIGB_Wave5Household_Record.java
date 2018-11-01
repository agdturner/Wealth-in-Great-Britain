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

    private final Double YearW5;
    private final Double MonthW5;
    private final Double HOutW5;
    private final Double w5xshhwgt;
    private final Double AccomW5;
    private final Double FltTypW5;
    private final Double AccOthW5;
    private final Double Ten1W5;
    private final Double TiedW5;
    private final Double LLordW5;
    private final Double FurnW5;
    private final Double DVPriRntW5;
    private final Double HHownW5;
    private final Double HAgeBW5;
    private final Double HAgeYrW5;
    private final Double HBuyYrW5;
    private final Double HBuySeW5;
    private final Double HShareW5;
    private final Double HSharePW5;
    private final Double HPriceW5;
    private final Double HPriceBW5;
    private final Double HBFromW5;
    private final Double HRTBevW5;
    private final Double HHOSchW5;
    private final Double HValueW5;
    private final Double HValBW5;
    private final Double Hext1W5;
    private final Double Hext2W5;
    private final Double Hext3W5;
    private final Double Hext4W5;
    private final Double MNumbNW5;
    private final Double MNumbW5;
    private final Double MW2Chk1W5;
    private final Double MW2Chk2W5;
    private final Double MW2Chk3W5;
    private final Double MExtnW5;
    private final Double MExtRs1W5;
    private final Double MExtRs2W5;
    private final Double MExtRs3W5;
    private final Double MChgeW5;
    private final Double MChgeNumW5;
    private final Double MNumbOW5;
    private final Double MName1W5;
    private final Double MOname1W5;
    private final Double MJname01W5;
    private final Double MJname02W5;
    private final Double MJname03W5;
    private final Double MJname04W5;
    private final Double MReas01W5;
    private final Double MReas02W5;
    private final Double MReas03W5;
    private final Double MReas04W5;
    private final Double MReas05W5;
    private final Double MReas06W5;
    private final Double MReas07W5;
    private final Double MType1W5;
    private final Double MEndw1W5;
    private final Double Mendnum1W5;
    private final Double MAll1W5;
    private final Double MAllty1W5;
    private final Double MVal1W5;
    private final Double MValB1W5;
    private final Double MNeg1W5;
    private final Double MNegB1W5;
    private final Double MEndv1W5;
    private final Double MEndVb1W5;
    private final Double MEndy1W5;
    private final Double MEndv2W5;
    private final Double MEndVb2W5;
    private final Double MEndy2W5;
    private final Double MEndv3W5;
    private final Double MEndVb3W5;
    private final Double MEndy3W5;
    private final Double MEndv4W5;
    private final Double MEndVb4W5;
    private final Double MEndy4W5;
    private final Double MEndv5W5;
    private final Double MEndx1W5;
    private final Double MinvW1W5;
    private final Double MinvW2W5;
    private final Double MinvW3W5;
    private final Double MPolicy1W5;
    private final Double MPolicy2W5;
    private final Double MYLft1W5;
    private final Double MYIfCh1W5;
    private final Double MIntPaid1W5;
    private final Double MIntFix1W5;
    private final Double MIntRate1W5;
    private final Double MPayM1W5;
    private final Double MPayB1W5;
    private final Double MPastSPA1W5;
    private final Double MYPastSPA1W5;
    private final Double MInc1W5;
    private final Double MInc2W5;
    private final Double MInc3W5;
    private final Double MInc4W5;
    private final Double MInc5W5;
    private final Double MPP1W5;
    private final Double MHowPy1W5;
    private final Double MArrs1W5;
    private final Double MArrsV1W5;
    private final Double MArr2Yr1W5;
    private final Double MArr2Yr2W5;
    private final Double MArr2Yr3W5;
    private final Double MArrCl01W5;
    private final Double MArrCl02W5;
    private final Double MArrCl12W5;
    private final Double MName2W5;
    private final Double MOname2W5;
    private final Double MJname18W5;
    private final Double MJname19W5;
    private final Double MJname20W5;
    private final Double MReas12W5;
    private final Double MReas13W5;
    private final Double MReas14W5;
    private final Double MType2W5;
    private final Double BridLn1W5;
    private final Double BridLn2W5;
    private final Double BridLn3W5;
    private final Double MEndw2W5;
    private final Double Mendnum2W5;
    private final Double MAll2W5;
    private final Double MAllty2W5;
    private final Double MVal2W5;
    private final Double MValB2W5;
    private final Double MNeg2W5;
    private final Double MNegB2W5;
    private final Double MEndv6W5;
    private final Double MEndVb6W5;
    private final Double MEndy6W5;
    private final Double MEndv7W5;
    private final Double MEndVb7W5;
    private final Double MEndy7W5;
    private final Double MEndv8W5;
    private final Double MEndVb8W5;
    private final Double MEndy8W5;
    private final Double MEndx2W5;
    private final Double MYLft2W5;
    private final Double MYIfCh2W5;
    private final Double MPastSPA2W5;
    private final Double MYPastSPA2W5;
    private final Double MPayM2W5;
    private final Double MPayB2W5;
    private final Double MInc6W5;
    private final Double MPP2W5;
    private final Double MIntPaid2W5;
    private final Double MIntFix2W5;
    private final Double MIntRate2W5;
    private final Double MHowPy2W5;
    private final Double MArrs2W5;
    private final Double MArrsV2W5;
    private final Double MName3W5;
    private final Double MOname3W5;
    private final Double MJname35W5;
    private final Double MJname36W5;
    private final Double MReas23W5;
    private final Double MReas24W5;
    private final Double MType3W5;
    private final Double MEndw3W5;
    private final Double Mendnum3W5;
    private final Double MAll3W5;
    private final Double MAllty3W5;
    private final Double MVal3W5;
    private final Double MValB3W5;
    private final Double MNeg3W5;
    private final Double MNegB3W5;
    private final Double MEndv11W5;
    private final Double MEndVb11W5;
    private final Double MEndy11W5;
    private final Double MEndx3W5;
    private final Double MYLft3W5;
    private final Double MYIfCh3W5;
    private final Double MPastSPA3W5;
    private final Double MYPastSPA3W5;
    private final Double MPayM3W5;
    private final Double MPayB3W5;
    private final Double MInc11W5;
    private final Double MPP3W5;
    private final Double MIntPaid3W5;
    private final Double MIntFix3W5;
    private final Double MIntRate3W5;
    private final Double MHowPy3W5;
    private final Double MArrs3W5;
    private final Double MArrsV3W5;
    private final Double EqOldW5;
    private final Double EqNewW5;
    private final Double EqYesW5;
    private final Double EqType1W5;
    private final Double EqType2W5;
    private final Double EqWhenYW5;
    private final Double EqWhenMW5;
    private final Double EqDrawW5;
    private final Double EqValW5;
    private final Double EqValBW5;
    private final Double EqValDrW5;
    private final Double EqValDrBW5;
    private final Double ERecW5;
    private final Double ERecMPDW5;
    private final Double ERecMBYW5;
    private final Double ERecTaxW5;
    private final Double EIntW5;
    private final Double EIntFixW5;
    private final Double EIntRatW5;
    private final Double EMSPayW5;
    private final Double EMSArrW5;
    private final Double FRRepfixW5;
    private final Double SharAppW5;
    private final Double ESharAppW5;
    private final Double ERvReg1W5;
    private final Double ERvReg2W5;
    private final Double ERvSumW5;
    private final Double ERvSumBW5;
    private final Double ERvRecPDW5;
    private final Double ERvRecBYW5;
    private final Double ERvTaxW5;
    private final Double ERvRentW5;
    private final Double ErvPrpW5;
    private final Double EPrvPayW5;
    private final Double EPrValW5;
    private final Double EPrPerW5;
    private final Double EOthRegW5;
    private final Double EOthRVaW5;
    private final Double EOthRVBW5;
    private final Double PRRepayW5;
    private final Double PRRepfixW5;
    private final Double PRReppcW5;
    private final Double GCollecW5;
    private final Double GCollVW5;
    private final Double GCollVbW5;
    private final Double GContVbW5;
    private final Double VCarNW5;
    private final Double VType1W5;
    private final Double VType2W5;
    private final Double VType3W5;
    private final Double VpersW5;
    private final Double VPerVW5;
    private final Double VOTyp1W5;
    private final Double VOTyp2W5;
    private final Double VOTyp3W5;
    private final Double VOTyp4W5;
    private final Double VOTpNW5;
    private final Double VOValW5;
    private final Double VOVlBW5;
    private final Double VCarNw5_i;
    private final Double VCarNw5_iflag;
    private final Double VEstVTotalW5;
    private final Double VEstVTotalBW5;
    private final Double VEstVTotalBw5_i;
    private final Double VEstVTotalBw5_iflag;
    private final Double VEstVTotalw5_i;
    private final Double VEstVTotalw5_iflag;
    private final Double VTypew5_iflag;
    private final Double VOTypw5_iflag;
    private final Double VOVlBw5_i;
    private final Double VOVlBw5_iflag;
    private final Double MNumbw5_i;
    private final Double MNumbw5_iflag;
    private final Double MAll1w5_i;
    private final Double MAll1w5_iflag;
    private final Double MAll2w5_i;
    private final Double MAll2w5_iflag;
    private final Double MAll3w5_i;
    private final Double MAll3w5_iflag;
    private final Double MAllTy1w5_i;
    private final Double MAllTy1w5_iflag;
    private final Double MAllTy2w5_i;
    private final Double MAllTy2w5_iflag;
    private final Double MVal1w5_i;
    private final Double MVal1w5_iflag;
    private final Double MVal2w5_i;
    private final Double MVal2w5_iflag;
    private final Double MVal3w5_i;
    private final Double MVal3w5_iflag;
    private final Double MNeg1w5_i;
    private final Double MNeg1w5_iflag;
    private final Double MNeg2w5_i;
    private final Double MNeg2w5_iflag;
    private final Double Ten1w5_i;
    private final Double Ten1w5_iflag;
    private final Double HSharew5_i;
    private final Double HSharew5_iflag;
    private final Double HSharePw5_i;
    private final Double HSharePw5_iflag;
    private final Double equity_ynw5_i;
    private final Double equity_ynw5_iflag;
    private final Double eqtype1w5_i;
    private final Double eqtype1w5_iflag;
    private final Double eqdraww5_i;
    private final Double eqdraww5_iflag;
    private final Double eqvalbw5_i;
    private final Double eqvalbw5_iflag;
    private final Double eqvalw5_i;
    private final Double eqvalw5_iflag;
    private final Double eintw5_i;
    private final Double eintw5_iflag;
    private final Double eintratw5_i;
    private final Double eintratw5_iflag;
    private final Double ervprpw5_i;
    private final Double ervprpw5_iflag;
    private final Double prrepayw5_i;
    private final Double prrepayw5_iflag;
    private final Double prrepfixw5_i;
    private final Double prrepfixw5_iflag;
    private final Double prreppcw5_i;
    private final Double prreppcw5_iflag;
    private final Double mtype1w5_i;
    private final Double mtype1w5_iflag;
    private final Double mtype2w5_i;
    private final Double mtype2w5_iflag;
    private final Double mtype3w5_i;
    private final Double mtype3w5_iflag;
    private final Double mendw1W5_i;
    private final Double mendw1W5_iflag;
    private final Double mendw2w5_i;
    private final Double mendw2w5_iflag;
    private final Double mendw3w5_i;
    private final Double mendw3w5_iflag;
    private final Double mendnum1w5_i;
    private final Double mendnum1w5_iflag;
    private final Double mendnum2w5_i;
    private final Double mendnum2w5_iflag;
    private final Double mendnum3w5_i;
    private final Double mendnum3w5_iflag;
    private final Double mendvb1w5_i;
    private final Double mendvb1w5_iflag;
    private final Double mendvb2w5_i;
    private final Double mendvb2w5_iflag;
    private final Double mendvb3w5_i;
    private final Double mendvb3w5_iflag;
    private final Double mendvb4w5_i;
    private final Double mendvb4w5_iflag;
    private final Double mendvb5w5_i;
    private final Double mendvb5w5_iflag;
    private final Double mendvb9w5_i;
    private final Double mendvb9w5_iflag;
    private final Double mendvb6w5_i;
    private final Double mendvb6w5_iflag;
    private final Double mendvb7w5_i;
    private final Double mendvb7w5_iflag;
    private final Double mendvb8w5_i;
    private final Double mendvb8w5_iflag;
    private final Double mendvb11w5_i;
    private final Double mendvb11w5_iflag;
    private final Double mendv1w5_i;
    private final Double mendv1w5_iflag;
    private final Double mendv2w5_i;
    private final Double mendv2w5_iflag;
    private final Double mendv3w5_i;
    private final Double mendv3w5_iflag;
    private final Double mendv4w5_i;
    private final Double mendv4w5_iflag;
    private final Double mendv5w5_i;
    private final Double mendv5w5_iflag;
    private final Double mendv6w5_i;
    private final Double mendv6w5_iflag;
    private final Double mendv7w5_i;
    private final Double mendv7w5_iflag;
    private final Double mendv10w5_i;
    private final Double mendv10w5_iflag;
    private final Double mendv11w5_i;
    private final Double mendv11w5_iflag;
    private final Double HValuew5_i;
    private final Double HValuew5_iflag;
    private final Double DVHValuew5;
    private final Double TotMValw5;
    private final Double TotMNegw5;
    private final Double TotMortw5;
    private final Double DVEqRelValw5;
    private final Double DVEqLTMw5;
    private final Double DVHoRPw5;
    private final Double DVEqSRbw5;
    private final Double DVEqPrUnw5;
    private final Double HMORTGw5;
    private final Double AllEndWw5;
    private final Double HseTypeW5;
    private final Double VType1w5_i;
    private final Double VType2w5_i;
    private final Double VType3w5_i;
    private final Double VPerSw5_i;
    private final Double VPerSw5_iflag;
    private final Double VPerVw5_i;
    private final Double VPerVw5_iflag;
    private final Double VOTyp1w5_i;
    private final Double VOTyp2w5_i;
    private final Double VOTyp3w5_i;
    private final Double VOTyp4w5_i;
    private final Double VOTyp5w5_i;
    private final Double VOValw5_i;
    private final Double VOValw5_iflag;
    private final Double gcollecw5_i;
    private final Double gcollecw5_iflag;
    private final Double gcollvw5_i;
    private final Double gcollvw5_iflag;
    private final Double gcontvbw5_i;
    private final Double gcontvbw5_iflag;
    private final Double DVHseValw5_sum;
    private final Double DVBltValw5_sum;
    private final Double DVBldValw5_sum;
    private final Double DVLUKValw5_sum;
    private final Double DVLOSValw5_sum;
    private final Double DVOPrValw5_sum;
    private final Double DVHseDebtw5_sum;
    private final Double DVBLtDebtw5_sum;
    private final Double DVBldDebtw5_sum;
    private final Double DVLUKDebtw5_sum;
    private final Double DVLOSDebtw5_sum;
    private final Double DVOPrDebtw5_sum;
    private final Double OthMortw5_sum;
    private final Double HousGdsTW5_sum;
    private final Double BuyLGdsTW5_sum;
    private final Double HousGdsOSTW5_sum;
    private final Double PhysHousGdsTW5_aggr;
    private final Double PhysBuyLGdsTW5_aggr;
    private final Double PhysHousGdsOSTW5_aggr;
    private final Double GContVlsW5;
    private final Double DVGCollVW5;
    private final Double AllGdW5;
    private final Double DVTotOthVehValW5;
    private final Double DVTotPerNPValW5;
    private final Double DVTotVehValW5;
    private final Double HPHYSWW5;
    private final Double DVPropertyw5;
    private final Double HPROPWw5;
    private final Double DVCISAVw5_aggr;
    private final Double DVIISAVw5_aggr;
    private final Double DVKISAVw5_aggr;
    private final Double DVFNSValw5_aggr;
    private final Double DVFShUKVw5_aggr;
    private final Double DVInsVw5_aggr;
    private final Double DVFLfEnVw5_aggr;
    private final Double DVFLfSiVw5_aggr;
    private final Double DVFLfFSVw5_aggr;
    private final Double DVFLfTEVw5_aggr;
    private final Double DVFBondVw5_aggr;
    private final Double DVFESHARESw5_aggr;
    private final Double DVFEShavw5_aggr;
    private final Double DVFEOptVw5_aggr;
    private final Double DVFCollVw5_aggr;
    private final Double DVFShOSVw5_aggr;
    private final Double DVFGltUKVw5_aggr;
    private final Double DVFGltFOVw5_aggr;
    private final Double DVFInvOtVw5_aggr;
    private final Double DVFInfValw5_aggr;
    private final Double DVFInfLVw5_aggr;
    private final Double DVFInfVw5_aggr;
    private final Double DVISAValw5_aggr;
    private final Double DVFFAssetsw5_aggr;
    private final Double DVCACTvw5_aggr;
    private final Double DVCASVVw5_aggr;
    private final Double DVCAValw5_aggr;
    private final Double DVCaCrValw5_aggr;
    private final Double DVCAOdValw5_aggr;
    private final Double DVSaValw5_aggr;
    private final Double TOTCCw5_aggr;
    private final Double TOTSCw5_aggr;
    private final Double TotMow5_aggr;
    private final Double TotNslBnkw5_aggr;
    private final Double TotNslCw5_aggr;
    private final Double TotOslbnkw5_aggr;
    private final Double TotOSLCw5_aggr;
    private final Double TotSLBnkw5_aggr;
    private final Double TotSLCw5_aggr;
    private final Double TotBillArrw5_aggr;
    private final Double TotHPArrw5_aggr;
    private final Double TotMOArrw5_aggr;
    private final Double TotLNArrw5_aggr;
    private final Double TotDVNWFLNw5_aggr;
    private final Double TOTDVNWILNw5_aggr;
    private final Double TotArr_excMortw5_aggr;
    private final Double HFINW_excENDWw5_aggr;
    private final Double HFINWw5_sum;
    private final Double DVValDBTw5_aggr;
    private final Double DVValDCosw5_aggr;
    private final Double DVPAVCUVw5_aggr;
    private final Double DVPFDDVw5_aggr;
    private final Double DVPPValw5_aggr;
    private final Double TotLosw5_aggr;
    private final Double TotNLosw5_aggr;
    private final Double TotFLNSw5_aggr;
    private final Double TotHPw5_aggr;
    private final Double TotLNSw5_aggr;
    private final Double HFINLw5_aggr;
    private final Double HFINWNTw5_sum;
    private final Double DVSPenw5_aggr;
    private final Double DVPInPValw5_aggr;
    private final Double DVDBRWealthValw5_aggr;
    private final Double TOTPENw5_aggr;
    private final Double TotWlthW5;
    private final Double DVPFCurValw5_aggr;
    private final Double DVTotGIRW5;
    private final Double DVNIothIW5_aggr;
    private final Double HRPDVAgeW5;
    private final Double HRPDVAge9W5;
    private final Double HRPSexW5;
    private final Double HRPNSSEC3W5;
    private final Double HRPDVILO3aW5;
    private final Double HRPDVMrDfW5;
    private final Double HRPEdAttn1W5;
    private final Double HRPEdAttn2W5;
    private final Double HRPEdAttn3W5;
    private final Double HRPDVecactw5;
    private final Double HRPIOut1W5;
    private final Double HRPPartIOut1W5;
    private final Double OfinalW5;
    private final Double NoUnitsW5;
    private final Double DVHSizeW5;
    private final Double GORW5;
    private final Double NumAdISwW5;
    private final Double NumAdultW5;
    private final Double NumCh18W5;
    private final Double NumChildW5;
    private final Double NumCivPtrW5;
    private final Double NumCPartW5;
    private final Double NumDepChW5;
    private final Double NumElChW5;
    private final Double NumHHldrW5;
    private final Double NumMPartW5;
    private final Double NumNoElChW5;
    private final Double HHoldTypeW5;
    private final Double HRPEdLevelW5;
    private final Double HBedrmw5;
    private final Double CASEw5;
    private final Double DVTotNIRW5;
    private final Double dvtotgirw5_3sf;
    private final Double DVGISEw5_AGGR;
    private final Double DVGIPPENw5_AGGR;
    private final Double DVGIEMPw5_AGGR;
    private final Double DVBenefitAnnualw5_aggr;
    private final Double DVGIINVw5_aggr;
    private final Double DVGrsRentAmtAnnualw5_aggr;
    private final Double DVNISEw5_aggr;
    private final Double DVNIPpenw5_aggr;
    private final Double DVNIEMPw5_aggr;
    private final Double DVNIINVw5_aggr;
    private final Double DVNetRentAmtAnnualw5_aggr;
    private final Double DVoiNrrAnnualw5_aggr;
    private final Double DVoiNgtAnnualw5_aggr;
    private final Double DVoiNegAnnualw5_aggr;
    private final Double DVoiNfrAnnualw5_aggr;
    private final Double DVoiNmaAnnualw5_aggr;
    private final Double DVoiNroAnnualw5_aggr;
    private final Double DVoiNopAnnualw5_aggr;
    private final Double DVoiGrrAnnualw5_aggr;
    private final Double DVoiGgtAnnualw5_aggr;
    private final Double DVoiGegAnnualw5_aggr;
    private final Double DVoiGfrAnnualw5_aggr;
    private final Double DVoiGmaAnnualw5_aggr;
    private final Double DVoiGroAnnualw5_aggr;
    private final Double DVoiGopAnnualw5_aggr;
    private final Double DVGIothRw5_aggr;
    private final Double DVNIothRw5_aggr;
    private final Double DVErecNetAnnualw5;
    private final Double DVERvrecNetAnnualw5;
    private final Double DVErecGrossAnnualw5;
    private final Double DVERvrecGrossAnnualw5;
    private final Double CASEw4;
    private final Double CASEw3;
    private final Double CASEW2;
    private final Double casew1;

    public WIGB_Wave5Household_Record(String line) {
        super(line);
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
        if (split[n].trim().isEmpty()) {
            HOutW5 = null;
        } else {
            HOutW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            w5xshhwgt = null;
        } else {
            w5xshhwgt = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            AccomW5 = null;
        } else {
            AccomW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            FltTypW5 = null;
        } else {
            FltTypW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            AccOthW5 = null;
        } else {
            AccOthW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Ten1W5 = null;
        } else {
            Ten1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TiedW5 = null;
        } else {
            TiedW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            LLordW5 = null;
        } else {
            LLordW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            FurnW5 = null;
        } else {
            FurnW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVPriRntW5 = null;
        } else {
            DVPriRntW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HHownW5 = null;
        } else {
            HHownW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HAgeBW5 = null;
        } else {
            HAgeBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HAgeYrW5 = null;
        } else {
            HAgeYrW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HBuyYrW5 = null;
        } else {
            HBuyYrW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HBuySeW5 = null;
        } else {
            HBuySeW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HShareW5 = null;
        } else {
            HShareW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HSharePW5 = null;
        } else {
            HSharePW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HPriceW5 = null;
        } else {
            HPriceW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HPriceBW5 = null;
        } else {
            HPriceBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HBFromW5 = null;
        } else {
            HBFromW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRTBevW5 = null;
        } else {
            HRTBevW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HHOSchW5 = null;
        } else {
            HHOSchW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HValueW5 = null;
        } else {
            HValueW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HValBW5 = null;
        } else {
            HValBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Hext1W5 = null;
        } else {
            Hext1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Hext2W5 = null;
        } else {
            Hext2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Hext3W5 = null;
        } else {
            Hext3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Hext4W5 = null;
        } else {
            Hext4W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNumbNW5 = null;
        } else {
            MNumbNW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNumbW5 = null;
        } else {
            MNumbW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MW2Chk1W5 = null;
        } else {
            MW2Chk1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MW2Chk2W5 = null;
        } else {
            MW2Chk2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MW2Chk3W5 = null;
        } else {
            MW2Chk3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtnW5 = null;
        } else {
            MExtnW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs1W5 = null;
        } else {
            MExtRs1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs2W5 = null;
        } else {
            MExtRs2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MExtRs3W5 = null;
        } else {
            MExtRs3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MChgeW5 = null;
        } else {
            MChgeW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MChgeNumW5 = null;
        } else {
            MChgeNumW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNumbOW5 = null;
        } else {
            MNumbOW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MName1W5 = null;
        } else {
            MName1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MOname1W5 = null;
        } else {
            MOname1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname01W5 = null;
        } else {
            MJname01W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname02W5 = null;
        } else {
            MJname02W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname03W5 = null;
        } else {
            MJname03W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname04W5 = null;
        } else {
            MJname04W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas01W5 = null;
        } else {
            MReas01W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas02W5 = null;
        } else {
            MReas02W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas03W5 = null;
        } else {
            MReas03W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas04W5 = null;
        } else {
            MReas04W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas05W5 = null;
        } else {
            MReas05W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas06W5 = null;
        } else {
            MReas06W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas07W5 = null;
        } else {
            MReas07W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MType1W5 = null;
        } else {
            MType1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndw1W5 = null;
        } else {
            MEndw1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Mendnum1W5 = null;
        } else {
            Mendnum1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll1W5 = null;
        } else {
            MAll1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAllty1W5 = null;
        } else {
            MAllty1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal1W5 = null;
        } else {
            MVal1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MValB1W5 = null;
        } else {
            MValB1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNeg1W5 = null;
        } else {
            MNeg1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNegB1W5 = null;
        } else {
            MNegB1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv1W5 = null;
        } else {
            MEndv1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb1W5 = null;
        } else {
            MEndVb1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy1W5 = null;
        } else {
            MEndy1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv2W5 = null;
        } else {
            MEndv2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb2W5 = null;
        } else {
            MEndVb2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy2W5 = null;
        } else {
            MEndy2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv3W5 = null;
        } else {
            MEndv3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb3W5 = null;
        } else {
            MEndVb3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy3W5 = null;
        } else {
            MEndy3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv4W5 = null;
        } else {
            MEndv4W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb4W5 = null;
        } else {
            MEndVb4W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy4W5 = null;
        } else {
            MEndy4W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv5W5 = null;
        } else {
            MEndv5W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndx1W5 = null;
        } else {
            MEndx1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MinvW1W5 = null;
        } else {
            MinvW1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MinvW2W5 = null;
        } else {
            MinvW2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MinvW3W5 = null;
        } else {
            MinvW3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPolicy1W5 = null;
        } else {
            MPolicy1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPolicy2W5 = null;
        } else {
            MPolicy2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYLft1W5 = null;
        } else {
            MYLft1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYIfCh1W5 = null;
        } else {
            MYIfCh1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntPaid1W5 = null;
        } else {
            MIntPaid1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntFix1W5 = null;
        } else {
            MIntFix1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntRate1W5 = null;
        } else {
            MIntRate1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPayM1W5 = null;
        } else {
            MPayM1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPayB1W5 = null;
        } else {
            MPayB1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPastSPA1W5 = null;
        } else {
            MPastSPA1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYPastSPA1W5 = null;
        } else {
            MYPastSPA1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc1W5 = null;
        } else {
            MInc1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc2W5 = null;
        } else {
            MInc2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc3W5 = null;
        } else {
            MInc3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc4W5 = null;
        } else {
            MInc4W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc5W5 = null;
        } else {
            MInc5W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPP1W5 = null;
        } else {
            MPP1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MHowPy1W5 = null;
        } else {
            MHowPy1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrs1W5 = null;
        } else {
            MArrs1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrsV1W5 = null;
        } else {
            MArrsV1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArr2Yr1W5 = null;
        } else {
            MArr2Yr1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArr2Yr2W5 = null;
        } else {
            MArr2Yr2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArr2Yr3W5 = null;
        } else {
            MArr2Yr3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrCl01W5 = null;
        } else {
            MArrCl01W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrCl02W5 = null;
        } else {
            MArrCl02W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrCl12W5 = null;
        } else {
            MArrCl12W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MName2W5 = null;
        } else {
            MName2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MOname2W5 = null;
        } else {
            MOname2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname18W5 = null;
        } else {
            MJname18W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname19W5 = null;
        } else {
            MJname19W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname20W5 = null;
        } else {
            MJname20W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas12W5 = null;
        } else {
            MReas12W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas13W5 = null;
        } else {
            MReas13W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas14W5 = null;
        } else {
            MReas14W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MType2W5 = null;
        } else {
            MType2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            BridLn1W5 = null;
        } else {
            BridLn1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            BridLn2W5 = null;
        } else {
            BridLn2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            BridLn3W5 = null;
        } else {
            BridLn3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndw2W5 = null;
        } else {
            MEndw2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Mendnum2W5 = null;
        } else {
            Mendnum2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll2W5 = null;
        } else {
            MAll2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAllty2W5 = null;
        } else {
            MAllty2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal2W5 = null;
        } else {
            MVal2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MValB2W5 = null;
        } else {
            MValB2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNeg2W5 = null;
        } else {
            MNeg2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNegB2W5 = null;
        } else {
            MNegB2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv6W5 = null;
        } else {
            MEndv6W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb6W5 = null;
        } else {
            MEndVb6W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy6W5 = null;
        } else {
            MEndy6W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv7W5 = null;
        } else {
            MEndv7W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb7W5 = null;
        } else {
            MEndVb7W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy7W5 = null;
        } else {
            MEndy7W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv8W5 = null;
        } else {
            MEndv8W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb8W5 = null;
        } else {
            MEndVb8W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy8W5 = null;
        } else {
            MEndy8W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndx2W5 = null;
        } else {
            MEndx2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYLft2W5 = null;
        } else {
            MYLft2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYIfCh2W5 = null;
        } else {
            MYIfCh2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPastSPA2W5 = null;
        } else {
            MPastSPA2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYPastSPA2W5 = null;
        } else {
            MYPastSPA2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPayM2W5 = null;
        } else {
            MPayM2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPayB2W5 = null;
        } else {
            MPayB2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc6W5 = null;
        } else {
            MInc6W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPP2W5 = null;
        } else {
            MPP2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntPaid2W5 = null;
        } else {
            MIntPaid2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntFix2W5 = null;
        } else {
            MIntFix2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntRate2W5 = null;
        } else {
            MIntRate2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MHowPy2W5 = null;
        } else {
            MHowPy2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrs2W5 = null;
        } else {
            MArrs2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrsV2W5 = null;
        } else {
            MArrsV2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MName3W5 = null;
        } else {
            MName3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MOname3W5 = null;
        } else {
            MOname3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname35W5 = null;
        } else {
            MJname35W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MJname36W5 = null;
        } else {
            MJname36W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas23W5 = null;
        } else {
            MReas23W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MReas24W5 = null;
        } else {
            MReas24W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MType3W5 = null;
        } else {
            MType3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndw3W5 = null;
        } else {
            MEndw3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Mendnum3W5 = null;
        } else {
            Mendnum3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll3W5 = null;
        } else {
            MAll3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAllty3W5 = null;
        } else {
            MAllty3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal3W5 = null;
        } else {
            MVal3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MValB3W5 = null;
        } else {
            MValB3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNeg3W5 = null;
        } else {
            MNeg3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNegB3W5 = null;
        } else {
            MNegB3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndv11W5 = null;
        } else {
            MEndv11W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndVb11W5 = null;
        } else {
            MEndVb11W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndy11W5 = null;
        } else {
            MEndy11W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MEndx3W5 = null;
        } else {
            MEndx3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYLft3W5 = null;
        } else {
            MYLft3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYIfCh3W5 = null;
        } else {
            MYIfCh3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPastSPA3W5 = null;
        } else {
            MPastSPA3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MYPastSPA3W5 = null;
        } else {
            MYPastSPA3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPayM3W5 = null;
        } else {
            MPayM3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPayB3W5 = null;
        } else {
            MPayB3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MInc11W5 = null;
        } else {
            MInc11W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MPP3W5 = null;
        } else {
            MPP3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntPaid3W5 = null;
        } else {
            MIntPaid3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntFix3W5 = null;
        } else {
            MIntFix3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MIntRate3W5 = null;
        } else {
            MIntRate3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MHowPy3W5 = null;
        } else {
            MHowPy3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrs3W5 = null;
        } else {
            MArrs3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MArrsV3W5 = null;
        } else {
            MArrsV3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqOldW5 = null;
        } else {
            EqOldW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqNewW5 = null;
        } else {
            EqNewW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqYesW5 = null;
        } else {
            EqYesW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqType1W5 = null;
        } else {
            EqType1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqType2W5 = null;
        } else {
            EqType2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqWhenYW5 = null;
        } else {
            EqWhenYW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqWhenMW5 = null;
        } else {
            EqWhenMW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqDrawW5 = null;
        } else {
            EqDrawW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqValW5 = null;
        } else {
            EqValW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqValBW5 = null;
        } else {
            EqValBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqValDrW5 = null;
        } else {
            EqValDrW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EqValDrBW5 = null;
        } else {
            EqValDrBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERecW5 = null;
        } else {
            ERecW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERecMPDW5 = null;
        } else {
            ERecMPDW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERecMBYW5 = null;
        } else {
            ERecMBYW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERecTaxW5 = null;
        } else {
            ERecTaxW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EIntW5 = null;
        } else {
            EIntW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EIntFixW5 = null;
        } else {
            EIntFixW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EIntRatW5 = null;
        } else {
            EIntRatW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EMSPayW5 = null;
        } else {
            EMSPayW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EMSArrW5 = null;
        } else {
            EMSArrW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            FRRepfixW5 = null;
        } else {
            FRRepfixW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            SharAppW5 = null;
        } else {
            SharAppW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ESharAppW5 = null;
        } else {
            ESharAppW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERvReg1W5 = null;
        } else {
            ERvReg1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERvReg2W5 = null;
        } else {
            ERvReg2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERvSumW5 = null;
        } else {
            ERvSumW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERvSumBW5 = null;
        } else {
            ERvSumBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERvRecPDW5 = null;
        } else {
            ERvRecPDW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERvRecBYW5 = null;
        } else {
            ERvRecBYW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERvTaxW5 = null;
        } else {
            ERvTaxW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ERvRentW5 = null;
        } else {
            ERvRentW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ErvPrpW5 = null;
        } else {
            ErvPrpW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EPrvPayW5 = null;
        } else {
            EPrvPayW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EPrValW5 = null;
        } else {
            EPrValW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EPrPerW5 = null;
        } else {
            EPrPerW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EOthRegW5 = null;
        } else {
            EOthRegW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EOthRVaW5 = null;
        } else {
            EOthRVaW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            EOthRVBW5 = null;
        } else {
            EOthRVBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            PRRepayW5 = null;
        } else {
            PRRepayW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            PRRepfixW5 = null;
        } else {
            PRRepfixW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            PRReppcW5 = null;
        } else {
            PRReppcW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            GCollecW5 = null;
        } else {
            GCollecW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            GCollVW5 = null;
        } else {
            GCollVW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            GCollVbW5 = null;
        } else {
            GCollVbW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            GContVbW5 = null;
        } else {
            GContVbW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VCarNW5 = null;
        } else {
            VCarNW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VType1W5 = null;
        } else {
            VType1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VType2W5 = null;
        } else {
            VType2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VType3W5 = null;
        } else {
            VType3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VpersW5 = null;
        } else {
            VpersW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPerVW5 = null;
        } else {
            VPerVW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp1W5 = null;
        } else {
            VOTyp1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp2W5 = null;
        } else {
            VOTyp2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp3W5 = null;
        } else {
            VOTyp3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp4W5 = null;
        } else {
            VOTyp4W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTpNW5 = null;
        } else {
            VOTpNW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOValW5 = null;
        } else {
            VOValW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOVlBW5 = null;
        } else {
            VOVlBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VCarNw5_i = null;
        } else {
            VCarNw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VCarNw5_iflag = null;
        } else {
            VCarNw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalW5 = null;
        } else {
            VEstVTotalW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalBW5 = null;
        } else {
            VEstVTotalBW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalBw5_i = null;
        } else {
            VEstVTotalBw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalBw5_iflag = null;
        } else {
            VEstVTotalBw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalw5_i = null;
        } else {
            VEstVTotalw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VEstVTotalw5_iflag = null;
        } else {
            VEstVTotalw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VTypew5_iflag = null;
        } else {
            VTypew5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTypw5_iflag = null;
        } else {
            VOTypw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOVlBw5_i = null;
        } else {
            VOVlBw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOVlBw5_iflag = null;
        } else {
            VOVlBw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNumbw5_i = null;
        } else {
            MNumbw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNumbw5_iflag = null;
        } else {
            MNumbw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll1w5_i = null;
        } else {
            MAll1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll1w5_iflag = null;
        } else {
            MAll1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll2w5_i = null;
        } else {
            MAll2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll2w5_iflag = null;
        } else {
            MAll2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll3w5_i = null;
        } else {
            MAll3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAll3w5_iflag = null;
        } else {
            MAll3w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAllTy1w5_i = null;
        } else {
            MAllTy1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAllTy1w5_iflag = null;
        } else {
            MAllTy1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAllTy2w5_i = null;
        } else {
            MAllTy2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MAllTy2w5_iflag = null;
        } else {
            MAllTy2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal1w5_i = null;
        } else {
            MVal1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal1w5_iflag = null;
        } else {
            MVal1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal2w5_i = null;
        } else {
            MVal2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal2w5_iflag = null;
        } else {
            MVal2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal3w5_i = null;
        } else {
            MVal3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MVal3w5_iflag = null;
        } else {
            MVal3w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNeg1w5_i = null;
        } else {
            MNeg1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNeg1w5_iflag = null;
        } else {
            MNeg1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNeg2w5_i = null;
        } else {
            MNeg2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            MNeg2w5_iflag = null;
        } else {
            MNeg2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Ten1w5_i = null;
        } else {
            Ten1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            Ten1w5_iflag = null;
        } else {
            Ten1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HSharew5_i = null;
        } else {
            HSharew5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HSharew5_iflag = null;
        } else {
            HSharew5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HSharePw5_i = null;
        } else {
            HSharePw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HSharePw5_iflag = null;
        } else {
            HSharePw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            equity_ynw5_i = null;
        } else {
            equity_ynw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            equity_ynw5_iflag = null;
        } else {
            equity_ynw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eqtype1w5_i = null;
        } else {
            eqtype1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eqtype1w5_iflag = null;
        } else {
            eqtype1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eqdraww5_i = null;
        } else {
            eqdraww5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eqdraww5_iflag = null;
        } else {
            eqdraww5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eqvalbw5_i = null;
        } else {
            eqvalbw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eqvalbw5_iflag = null;
        } else {
            eqvalbw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eqvalw5_i = null;
        } else {
            eqvalw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eqvalw5_iflag = null;
        } else {
            eqvalw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eintw5_i = null;
        } else {
            eintw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eintw5_iflag = null;
        } else {
            eintw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eintratw5_i = null;
        } else {
            eintratw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            eintratw5_iflag = null;
        } else {
            eintratw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ervprpw5_i = null;
        } else {
            ervprpw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            ervprpw5_iflag = null;
        } else {
            ervprpw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            prrepayw5_i = null;
        } else {
            prrepayw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            prrepayw5_iflag = null;
        } else {
            prrepayw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            prrepfixw5_i = null;
        } else {
            prrepfixw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            prrepfixw5_iflag = null;
        } else {
            prrepfixw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            prreppcw5_i = null;
        } else {
            prreppcw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            prreppcw5_iflag = null;
        } else {
            prreppcw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mtype1w5_i = null;
        } else {
            mtype1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mtype1w5_iflag = null;
        } else {
            mtype1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mtype2w5_i = null;
        } else {
            mtype2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mtype2w5_iflag = null;
        } else {
            mtype2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mtype3w5_i = null;
        } else {
            mtype3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mtype3w5_iflag = null;
        } else {
            mtype3w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendw1W5_i = null;
        } else {
            mendw1W5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendw1W5_iflag = null;
        } else {
            mendw1W5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendw2w5_i = null;
        } else {
            mendw2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendw2w5_iflag = null;
        } else {
            mendw2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendw3w5_i = null;
        } else {
            mendw3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendw3w5_iflag = null;
        } else {
            mendw3w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendnum1w5_i = null;
        } else {
            mendnum1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendnum1w5_iflag = null;
        } else {
            mendnum1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendnum2w5_i = null;
        } else {
            mendnum2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendnum2w5_iflag = null;
        } else {
            mendnum2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendnum3w5_i = null;
        } else {
            mendnum3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendnum3w5_iflag = null;
        } else {
            mendnum3w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb1w5_i = null;
        } else {
            mendvb1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb1w5_iflag = null;
        } else {
            mendvb1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb2w5_i = null;
        } else {
            mendvb2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb2w5_iflag = null;
        } else {
            mendvb2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb3w5_i = null;
        } else {
            mendvb3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb3w5_iflag = null;
        } else {
            mendvb3w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb4w5_i = null;
        } else {
            mendvb4w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb4w5_iflag = null;
        } else {
            mendvb4w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb5w5_i = null;
        } else {
            mendvb5w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb5w5_iflag = null;
        } else {
            mendvb5w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb9w5_i = null;
        } else {
            mendvb9w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb9w5_iflag = null;
        } else {
            mendvb9w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb6w5_i = null;
        } else {
            mendvb6w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb6w5_iflag = null;
        } else {
            mendvb6w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb7w5_i = null;
        } else {
            mendvb7w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb7w5_iflag = null;
        } else {
            mendvb7w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb8w5_i = null;
        } else {
            mendvb8w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb8w5_iflag = null;
        } else {
            mendvb8w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb11w5_i = null;
        } else {
            mendvb11w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendvb11w5_iflag = null;
        } else {
            mendvb11w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv1w5_i = null;
        } else {
            mendv1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv1w5_iflag = null;
        } else {
            mendv1w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv2w5_i = null;
        } else {
            mendv2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv2w5_iflag = null;
        } else {
            mendv2w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv3w5_i = null;
        } else {
            mendv3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv3w5_iflag = null;
        } else {
            mendv3w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv4w5_i = null;
        } else {
            mendv4w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv4w5_iflag = null;
        } else {
            mendv4w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv5w5_i = null;
        } else {
            mendv5w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv5w5_iflag = null;
        } else {
            mendv5w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv6w5_i = null;
        } else {
            mendv6w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv6w5_iflag = null;
        } else {
            mendv6w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv7w5_i = null;
        } else {
            mendv7w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv7w5_iflag = null;
        } else {
            mendv7w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv10w5_i = null;
        } else {
            mendv10w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv10w5_iflag = null;
        } else {
            mendv10w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv11w5_i = null;
        } else {
            mendv11w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            mendv11w5_iflag = null;
        } else {
            mendv11w5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HValuew5_i = null;
        } else {
            HValuew5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HValuew5_iflag = null;
        } else {
            HValuew5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVHValuew5 = null;
        } else {
            DVHValuew5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotMValw5 = null;
        } else {
            TotMValw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotMNegw5 = null;
        } else {
            TotMNegw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotMortw5 = null;
        } else {
            TotMortw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVEqRelValw5 = null;
        } else {
            DVEqRelValw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVEqLTMw5 = null;
        } else {
            DVEqLTMw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVHoRPw5 = null;
        } else {
            DVHoRPw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVEqSRbw5 = null;
        } else {
            DVEqSRbw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVEqPrUnw5 = null;
        } else {
            DVEqPrUnw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HMORTGw5 = null;
        } else {
            HMORTGw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            AllEndWw5 = null;
        } else {
            AllEndWw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HseTypeW5 = null;
        } else {
            HseTypeW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VType1w5_i = null;
        } else {
            VType1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VType2w5_i = null;
        } else {
            VType2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VType3w5_i = null;
        } else {
            VType3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPerSw5_i = null;
        } else {
            VPerSw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPerSw5_iflag = null;
        } else {
            VPerSw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPerVw5_i = null;
        } else {
            VPerVw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VPerVw5_iflag = null;
        } else {
            VPerVw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp1w5_i = null;
        } else {
            VOTyp1w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp2w5_i = null;
        } else {
            VOTyp2w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp3w5_i = null;
        } else {
            VOTyp3w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp4w5_i = null;
        } else {
            VOTyp4w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOTyp5w5_i = null;
        } else {
            VOTyp5w5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOValw5_i = null;
        } else {
            VOValw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            VOValw5_iflag = null;
        } else {
            VOValw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            gcollecw5_i = null;
        } else {
            gcollecw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            gcollecw5_iflag = null;
        } else {
            gcollecw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            gcollvw5_i = null;
        } else {
            gcollvw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            gcollvw5_iflag = null;
        } else {
            gcollvw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            gcontvbw5_i = null;
        } else {
            gcontvbw5_i = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            gcontvbw5_iflag = null;
        } else {
            gcontvbw5_iflag = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVHseValw5_sum = null;
        } else {
            DVHseValw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVBltValw5_sum = null;
        } else {
            DVBltValw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVBldValw5_sum = null;
        } else {
            DVBldValw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVLUKValw5_sum = null;
        } else {
            DVLUKValw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVLOSValw5_sum = null;
        } else {
            DVLOSValw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVOPrValw5_sum = null;
        } else {
            DVOPrValw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVHseDebtw5_sum = null;
        } else {
            DVHseDebtw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVBLtDebtw5_sum = null;
        } else {
            DVBLtDebtw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVBldDebtw5_sum = null;
        } else {
            DVBldDebtw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVLUKDebtw5_sum = null;
        } else {
            DVLUKDebtw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVLOSDebtw5_sum = null;
        } else {
            DVLOSDebtw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVOPrDebtw5_sum = null;
        } else {
            DVOPrDebtw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            OthMortw5_sum = null;
        } else {
            OthMortw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HousGdsTW5_sum = null;
        } else {
            HousGdsTW5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            BuyLGdsTW5_sum = null;
        } else {
            BuyLGdsTW5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HousGdsOSTW5_sum = null;
        } else {
            HousGdsOSTW5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            PhysHousGdsTW5_aggr = null;
        } else {
            PhysHousGdsTW5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            PhysBuyLGdsTW5_aggr = null;
        } else {
            PhysBuyLGdsTW5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            PhysHousGdsOSTW5_aggr = null;
        } else {
            PhysHousGdsOSTW5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            GContVlsW5 = null;
        } else {
            GContVlsW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVGCollVW5 = null;
        } else {
            DVGCollVW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            AllGdW5 = null;
        } else {
            AllGdW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVTotOthVehValW5 = null;
        } else {
            DVTotOthVehValW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVTotPerNPValW5 = null;
        } else {
            DVTotPerNPValW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVTotVehValW5 = null;
        } else {
            DVTotVehValW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HPHYSWW5 = null;
        } else {
            HPHYSWW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVPropertyw5 = null;
        } else {
            DVPropertyw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HPROPWw5 = null;
        } else {
            HPROPWw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVCISAVw5_aggr = null;
        } else {
            DVCISAVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVIISAVw5_aggr = null;
        } else {
            DVIISAVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVKISAVw5_aggr = null;
        } else {
            DVKISAVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFNSValw5_aggr = null;
        } else {
            DVFNSValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFShUKVw5_aggr = null;
        } else {
            DVFShUKVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVInsVw5_aggr = null;
        } else {
            DVInsVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFLfEnVw5_aggr = null;
        } else {
            DVFLfEnVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFLfSiVw5_aggr = null;
        } else {
            DVFLfSiVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFLfFSVw5_aggr = null;
        } else {
            DVFLfFSVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFLfTEVw5_aggr = null;
        } else {
            DVFLfTEVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFBondVw5_aggr = null;
        } else {
            DVFBondVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFESHARESw5_aggr = null;
        } else {
            DVFESHARESw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFEShavw5_aggr = null;
        } else {
            DVFEShavw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFEOptVw5_aggr = null;
        } else {
            DVFEOptVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFCollVw5_aggr = null;
        } else {
            DVFCollVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFShOSVw5_aggr = null;
        } else {
            DVFShOSVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFGltUKVw5_aggr = null;
        } else {
            DVFGltUKVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFGltFOVw5_aggr = null;
        } else {
            DVFGltFOVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFInvOtVw5_aggr = null;
        } else {
            DVFInvOtVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFInfValw5_aggr = null;
        } else {
            DVFInfValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFInfLVw5_aggr = null;
        } else {
            DVFInfLVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFInfVw5_aggr = null;
        } else {
            DVFInfVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVISAValw5_aggr = null;
        } else {
            DVISAValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVFFAssetsw5_aggr = null;
        } else {
            DVFFAssetsw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVCACTvw5_aggr = null;
        } else {
            DVCACTvw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVCASVVw5_aggr = null;
        } else {
            DVCASVVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVCAValw5_aggr = null;
        } else {
            DVCAValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVCaCrValw5_aggr = null;
        } else {
            DVCaCrValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVCAOdValw5_aggr = null;
        } else {
            DVCAOdValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVSaValw5_aggr = null;
        } else {
            DVSaValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TOTCCw5_aggr = null;
        } else {
            TOTCCw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TOTSCw5_aggr = null;
        } else {
            TOTSCw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotMow5_aggr = null;
        } else {
            TotMow5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotNslBnkw5_aggr = null;
        } else {
            TotNslBnkw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotNslCw5_aggr = null;
        } else {
            TotNslCw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotOslbnkw5_aggr = null;
        } else {
            TotOslbnkw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotOSLCw5_aggr = null;
        } else {
            TotOSLCw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotSLBnkw5_aggr = null;
        } else {
            TotSLBnkw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotSLCw5_aggr = null;
        } else {
            TotSLCw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotBillArrw5_aggr = null;
        } else {
            TotBillArrw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotHPArrw5_aggr = null;
        } else {
            TotHPArrw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotMOArrw5_aggr = null;
        } else {
            TotMOArrw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotLNArrw5_aggr = null;
        } else {
            TotLNArrw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotDVNWFLNw5_aggr = null;
        } else {
            TotDVNWFLNw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TOTDVNWILNw5_aggr = null;
        } else {
            TOTDVNWILNw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotArr_excMortw5_aggr = null;
        } else {
            TotArr_excMortw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HFINW_excENDWw5_aggr = null;
        } else {
            HFINW_excENDWw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HFINWw5_sum = null;
        } else {
            HFINWw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVValDBTw5_aggr = null;
        } else {
            DVValDBTw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVValDCosw5_aggr = null;
        } else {
            DVValDCosw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVPAVCUVw5_aggr = null;
        } else {
            DVPAVCUVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVPFDDVw5_aggr = null;
        } else {
            DVPFDDVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVPPValw5_aggr = null;
        } else {
            DVPPValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotLosw5_aggr = null;
        } else {
            TotLosw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotNLosw5_aggr = null;
        } else {
            TotNLosw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotFLNSw5_aggr = null;
        } else {
            TotFLNSw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotHPw5_aggr = null;
        } else {
            TotHPw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotLNSw5_aggr = null;
        } else {
            TotLNSw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HFINLw5_aggr = null;
        } else {
            HFINLw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HFINWNTw5_sum = null;
        } else {
            HFINWNTw5_sum = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVSPenw5_aggr = null;
        } else {
            DVSPenw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVPInPValw5_aggr = null;
        } else {
            DVPInPValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVDBRWealthValw5_aggr = null;
        } else {
            DVDBRWealthValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TOTPENw5_aggr = null;
        } else {
            TOTPENw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            TotWlthW5 = null;
        } else {
            TotWlthW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVPFCurValw5_aggr = null;
        } else {
            DVPFCurValw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVTotGIRW5 = null;
        } else {
            DVTotGIRW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVNIothIW5_aggr = null;
        } else {
            DVNIothIW5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPDVAgeW5 = null;
        } else {
            HRPDVAgeW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPDVAge9W5 = null;
        } else {
            HRPDVAge9W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPSexW5 = null;
        } else {
            HRPSexW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPNSSEC3W5 = null;
        } else {
            HRPNSSEC3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPDVILO3aW5 = null;
        } else {
            HRPDVILO3aW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPDVMrDfW5 = null;
        } else {
            HRPDVMrDfW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPEdAttn1W5 = null;
        } else {
            HRPEdAttn1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPEdAttn2W5 = null;
        } else {
            HRPEdAttn2W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPEdAttn3W5 = null;
        } else {
            HRPEdAttn3W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPDVecactw5 = null;
        } else {
            HRPDVecactw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPIOut1W5 = null;
        } else {
            HRPIOut1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPPartIOut1W5 = null;
        } else {
            HRPPartIOut1W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            OfinalW5 = null;
        } else {
            OfinalW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NoUnitsW5 = null;
        } else {
            NoUnitsW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVHSizeW5 = null;
        } else {
            DVHSizeW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            GORW5 = null;
        } else {
            GORW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumAdISwW5 = null;
        } else {
            NumAdISwW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumAdultW5 = null;
        } else {
            NumAdultW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumCh18W5 = null;
        } else {
            NumCh18W5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumChildW5 = null;
        } else {
            NumChildW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumCivPtrW5 = null;
        } else {
            NumCivPtrW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumCPartW5 = null;
        } else {
            NumCPartW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumDepChW5 = null;
        } else {
            NumDepChW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumElChW5 = null;
        } else {
            NumElChW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumHHldrW5 = null;
        } else {
            NumHHldrW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumMPartW5 = null;
        } else {
            NumMPartW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            NumNoElChW5 = null;
        } else {
            NumNoElChW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HHoldTypeW5 = null;
        } else {
            HHoldTypeW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HRPEdLevelW5 = null;
        } else {
            HRPEdLevelW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            HBedrmw5 = null;
        } else {
            HBedrmw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            CASEw5 = null;
        } else {
            CASEw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVTotNIRW5 = null;
        } else {
            DVTotNIRW5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            dvtotgirw5_3sf = null;
        } else {
            dvtotgirw5_3sf = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVGISEw5_AGGR = null;
        } else {
            DVGISEw5_AGGR = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVGIPPENw5_AGGR = null;
        } else {
            DVGIPPENw5_AGGR = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVGIEMPw5_AGGR = null;
        } else {
            DVGIEMPw5_AGGR = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVBenefitAnnualw5_aggr = null;
        } else {
            DVBenefitAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVGIINVw5_aggr = null;
        } else {
            DVGIINVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVGrsRentAmtAnnualw5_aggr = null;
        } else {
            DVGrsRentAmtAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVNISEw5_aggr = null;
        } else {
            DVNISEw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVNIPpenw5_aggr = null;
        } else {
            DVNIPpenw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVNIEMPw5_aggr = null;
        } else {
            DVNIEMPw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVNIINVw5_aggr = null;
        } else {
            DVNIINVw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVNetRentAmtAnnualw5_aggr = null;
        } else {
            DVNetRentAmtAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiNrrAnnualw5_aggr = null;
        } else {
            DVoiNrrAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiNgtAnnualw5_aggr = null;
        } else {
            DVoiNgtAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiNegAnnualw5_aggr = null;
        } else {
            DVoiNegAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiNfrAnnualw5_aggr = null;
        } else {
            DVoiNfrAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiNmaAnnualw5_aggr = null;
        } else {
            DVoiNmaAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiNroAnnualw5_aggr = null;
        } else {
            DVoiNroAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiNopAnnualw5_aggr = null;
        } else {
            DVoiNopAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiGrrAnnualw5_aggr = null;
        } else {
            DVoiGrrAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiGgtAnnualw5_aggr = null;
        } else {
            DVoiGgtAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiGegAnnualw5_aggr = null;
        } else {
            DVoiGegAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiGfrAnnualw5_aggr = null;
        } else {
            DVoiGfrAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiGmaAnnualw5_aggr = null;
        } else {
            DVoiGmaAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiGroAnnualw5_aggr = null;
        } else {
            DVoiGroAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVoiGopAnnualw5_aggr = null;
        } else {
            DVoiGopAnnualw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVGIothRw5_aggr = null;
        } else {
            DVGIothRw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVNIothRw5_aggr = null;
        } else {
            DVNIothRw5_aggr = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVErecNetAnnualw5 = null;
        } else {
            DVErecNetAnnualw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVERvrecNetAnnualw5 = null;
        } else {
            DVERvrecNetAnnualw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVErecGrossAnnualw5 = null;
        } else {
            DVErecGrossAnnualw5 = new Double(split[n]);
        }
        n++;
        if (split[n].trim().isEmpty()) {
            DVERvrecGrossAnnualw5 = null;
        } else {
            DVERvrecGrossAnnualw5 = new Double(split[n]);
        }
        n++;
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
     * @return the HOutW5
     */
    public Double getHOutW5() {
        return HOutW5;
    }

    /**
     * @return the w5xshhwgt
     */
    public Double getW5xshhwgt() {
        return w5xshhwgt;
    }

    /**
     * @return the AccomW5
     */
    public Double getAccomW5() {
        return AccomW5;
    }

    /**
     * @return the FltTypW5
     */
    public Double getFltTypW5() {
        return FltTypW5;
    }

    /**
     * @return the AccOthW5
     */
    public Double getAccOthW5() {
        return AccOthW5;
    }

    /**
     * @return the Ten1W5
     */
    public Double getTen1W5() {
        return Ten1W5;
    }

    /**
     * @return the TiedW5
     */
    public Double getTiedW5() {
        return TiedW5;
    }

    /**
     * @return the LLordW5
     */
    public Double getLLordW5() {
        return LLordW5;
    }

    /**
     * @return the FurnW5
     */
    public Double getFurnW5() {
        return FurnW5;
    }

    /**
     * @return the DVPriRntW5
     */
    public Double getDVPriRntW5() {
        return DVPriRntW5;
    }

    /**
     * @return the HHownW5
     */
    public Double getHHownW5() {
        return HHownW5;
    }

    /**
     * @return the HAgeBW5
     */
    public Double getHAgeBW5() {
        return HAgeBW5;
    }

    /**
     * @return the HAgeYrW5
     */
    public Double getHAgeYrW5() {
        return HAgeYrW5;
    }

    /**
     * @return the HBuyYrW5
     */
    public Double getHBuyYrW5() {
        return HBuyYrW5;
    }

    /**
     * @return the HBuySeW5
     */
    public Double getHBuySeW5() {
        return HBuySeW5;
    }

    /**
     * @return the HShareW5
     */
    public Double getHShareW5() {
        return HShareW5;
    }

    /**
     * @return the HSharePW5
     */
    public Double getHSharePW5() {
        return HSharePW5;
    }

    /**
     * @return the HPriceW5
     */
    public Double getHPriceW5() {
        return HPriceW5;
    }

    /**
     * @return the HPriceBW5
     */
    public Double getHPriceBW5() {
        return HPriceBW5;
    }

    /**
     * @return the HBFromW5
     */
    public Double getHBFromW5() {
        return HBFromW5;
    }

    /**
     * @return the HRTBevW5
     */
    public Double getHRTBevW5() {
        return HRTBevW5;
    }

    /**
     * @return the HHOSchW5
     */
    public Double getHHOSchW5() {
        return HHOSchW5;
    }

    /**
     * @return the HValueW5
     */
    public Double getHValueW5() {
        return HValueW5;
    }

    /**
     * @return the HValBW5
     */
    public Double getHValBW5() {
        return HValBW5;
    }

    /**
     * @return the Hext1W5
     */
    public Double getHext1W5() {
        return Hext1W5;
    }

    /**
     * @return the Hext2W5
     */
    public Double getHext2W5() {
        return Hext2W5;
    }

    /**
     * @return the Hext3W5
     */
    public Double getHext3W5() {
        return Hext3W5;
    }

    /**
     * @return the Hext4W5
     */
    public Double getHext4W5() {
        return Hext4W5;
    }

    /**
     * @return the MNumbNW5
     */
    public Double getMNumbNW5() {
        return MNumbNW5;
    }

    /**
     * @return the MNumbW5
     */
    public Double getMNumbW5() {
        return MNumbW5;
    }

    /**
     * @return the MW2Chk1W5
     */
    public Double getMW2Chk1W5() {
        return MW2Chk1W5;
    }

    /**
     * @return the MW2Chk2W5
     */
    public Double getMW2Chk2W5() {
        return MW2Chk2W5;
    }

    /**
     * @return the MW2Chk3W5
     */
    public Double getMW2Chk3W5() {
        return MW2Chk3W5;
    }

    /**
     * @return the MExtnW5
     */
    public Double getMExtnW5() {
        return MExtnW5;
    }

    /**
     * @return the MExtRs1W5
     */
    public Double getMExtRs1W5() {
        return MExtRs1W5;
    }

    /**
     * @return the MExtRs2W5
     */
    public Double getMExtRs2W5() {
        return MExtRs2W5;
    }

    /**
     * @return the MExtRs3W5
     */
    public Double getMExtRs3W5() {
        return MExtRs3W5;
    }

    /**
     * @return the MChgeW5
     */
    public Double getMChgeW5() {
        return MChgeW5;
    }

    /**
     * @return the MChgeNumW5
     */
    public Double getMChgeNumW5() {
        return MChgeNumW5;
    }

    /**
     * @return the MNumbOW5
     */
    public Double getMNumbOW5() {
        return MNumbOW5;
    }

    /**
     * @return the MName1W5
     */
    public Double getMName1W5() {
        return MName1W5;
    }

    /**
     * @return the MOname1W5
     */
    public Double getMOname1W5() {
        return MOname1W5;
    }

    /**
     * @return the MJname01W5
     */
    public Double getMJname01W5() {
        return MJname01W5;
    }

    /**
     * @return the MJname02W5
     */
    public Double getMJname02W5() {
        return MJname02W5;
    }

    /**
     * @return the MJname03W5
     */
    public Double getMJname03W5() {
        return MJname03W5;
    }

    /**
     * @return the MJname04W5
     */
    public Double getMJname04W5() {
        return MJname04W5;
    }

    /**
     * @return the MReas01W5
     */
    public Double getMReas01W5() {
        return MReas01W5;
    }

    /**
     * @return the MReas02W5
     */
    public Double getMReas02W5() {
        return MReas02W5;
    }

    /**
     * @return the MReas03W5
     */
    public Double getMReas03W5() {
        return MReas03W5;
    }

    /**
     * @return the MReas04W5
     */
    public Double getMReas04W5() {
        return MReas04W5;
    }

    /**
     * @return the MReas05W5
     */
    public Double getMReas05W5() {
        return MReas05W5;
    }

    /**
     * @return the MReas06W5
     */
    public Double getMReas06W5() {
        return MReas06W5;
    }

    /**
     * @return the MReas07W5
     */
    public Double getMReas07W5() {
        return MReas07W5;
    }

    /**
     * @return the MType1W5
     */
    public Double getMType1W5() {
        return MType1W5;
    }

    /**
     * @return the MEndw1W5
     */
    public Double getMEndw1W5() {
        return MEndw1W5;
    }

    /**
     * @return the Mendnum1W5
     */
    public Double getMendnum1W5() {
        return Mendnum1W5;
    }

    /**
     * @return the MAll1W5
     */
    public Double getMAll1W5() {
        return MAll1W5;
    }

    /**
     * @return the MAllty1W5
     */
    public Double getMAllty1W5() {
        return MAllty1W5;
    }

    /**
     * @return the MVal1W5
     */
    public Double getMVal1W5() {
        return MVal1W5;
    }

    /**
     * @return the MValB1W5
     */
    public Double getMValB1W5() {
        return MValB1W5;
    }

    /**
     * @return the MNeg1W5
     */
    public Double getMNeg1W5() {
        return MNeg1W5;
    }

    /**
     * @return the MNegB1W5
     */
    public Double getMNegB1W5() {
        return MNegB1W5;
    }

    /**
     * @return the MEndv1W5
     */
    public Double getMEndv1W5() {
        return MEndv1W5;
    }

    /**
     * @return the MEndVb1W5
     */
    public Double getMEndVb1W5() {
        return MEndVb1W5;
    }

    /**
     * @return the MEndy1W5
     */
    public Double getMEndy1W5() {
        return MEndy1W5;
    }

    /**
     * @return the MEndv2W5
     */
    public Double getMEndv2W5() {
        return MEndv2W5;
    }

    /**
     * @return the MEndVb2W5
     */
    public Double getMEndVb2W5() {
        return MEndVb2W5;
    }

    /**
     * @return the MEndy2W5
     */
    public Double getMEndy2W5() {
        return MEndy2W5;
    }

    /**
     * @return the MEndv3W5
     */
    public Double getMEndv3W5() {
        return MEndv3W5;
    }

    /**
     * @return the MEndVb3W5
     */
    public Double getMEndVb3W5() {
        return MEndVb3W5;
    }

    /**
     * @return the MEndy3W5
     */
    public Double getMEndy3W5() {
        return MEndy3W5;
    }

    /**
     * @return the MEndv4W5
     */
    public Double getMEndv4W5() {
        return MEndv4W5;
    }

    /**
     * @return the MEndVb4W5
     */
    public Double getMEndVb4W5() {
        return MEndVb4W5;
    }

    /**
     * @return the MEndy4W5
     */
    public Double getMEndy4W5() {
        return MEndy4W5;
    }

    /**
     * @return the MEndv5W5
     */
    public Double getMEndv5W5() {
        return MEndv5W5;
    }

    /**
     * @return the MEndx1W5
     */
    public Double getMEndx1W5() {
        return MEndx1W5;
    }

    /**
     * @return the MinvW1W5
     */
    public Double getMinvW1W5() {
        return MinvW1W5;
    }

    /**
     * @return the MinvW2W5
     */
    public Double getMinvW2W5() {
        return MinvW2W5;
    }

    /**
     * @return the MinvW3W5
     */
    public Double getMinvW3W5() {
        return MinvW3W5;
    }

    /**
     * @return the MPolicy1W5
     */
    public Double getMPolicy1W5() {
        return MPolicy1W5;
    }

    /**
     * @return the MPolicy2W5
     */
    public Double getMPolicy2W5() {
        return MPolicy2W5;
    }

    /**
     * @return the MYLft1W5
     */
    public Double getMYLft1W5() {
        return MYLft1W5;
    }

    /**
     * @return the MYIfCh1W5
     */
    public Double getMYIfCh1W5() {
        return MYIfCh1W5;
    }

    /**
     * @return the MIntPaid1W5
     */
    public Double getMIntPaid1W5() {
        return MIntPaid1W5;
    }

    /**
     * @return the MIntFix1W5
     */
    public Double getMIntFix1W5() {
        return MIntFix1W5;
    }

    /**
     * @return the MIntRate1W5
     */
    public Double getMIntRate1W5() {
        return MIntRate1W5;
    }

    /**
     * @return the MPayM1W5
     */
    public Double getMPayM1W5() {
        return MPayM1W5;
    }

    /**
     * @return the MPayB1W5
     */
    public Double getMPayB1W5() {
        return MPayB1W5;
    }

    /**
     * @return the MPastSPA1W5
     */
    public Double getMPastSPA1W5() {
        return MPastSPA1W5;
    }

    /**
     * @return the MYPastSPA1W5
     */
    public Double getMYPastSPA1W5() {
        return MYPastSPA1W5;
    }

    /**
     * @return the MInc1W5
     */
    public Double getMInc1W5() {
        return MInc1W5;
    }

    /**
     * @return the MInc2W5
     */
    public Double getMInc2W5() {
        return MInc2W5;
    }

    /**
     * @return the MInc3W5
     */
    public Double getMInc3W5() {
        return MInc3W5;
    }

    /**
     * @return the MInc4W5
     */
    public Double getMInc4W5() {
        return MInc4W5;
    }

    /**
     * @return the MInc5W5
     */
    public Double getMInc5W5() {
        return MInc5W5;
    }

    /**
     * @return the MPP1W5
     */
    public Double getMPP1W5() {
        return MPP1W5;
    }

    /**
     * @return the MHowPy1W5
     */
    public Double getMHowPy1W5() {
        return MHowPy1W5;
    }

    /**
     * @return the MArrs1W5
     */
    public Double getMArrs1W5() {
        return MArrs1W5;
    }

    /**
     * @return the MArrsV1W5
     */
    public Double getMArrsV1W5() {
        return MArrsV1W5;
    }

    /**
     * @return the MArr2Yr1W5
     */
    public Double getMArr2Yr1W5() {
        return MArr2Yr1W5;
    }

    /**
     * @return the MArr2Yr2W5
     */
    public Double getMArr2Yr2W5() {
        return MArr2Yr2W5;
    }

    /**
     * @return the MArr2Yr3W5
     */
    public Double getMArr2Yr3W5() {
        return MArr2Yr3W5;
    }

    /**
     * @return the MArrCl01W5
     */
    public Double getMArrCl01W5() {
        return MArrCl01W5;
    }

    /**
     * @return the MArrCl02W5
     */
    public Double getMArrCl02W5() {
        return MArrCl02W5;
    }

    /**
     * @return the MArrCl12W5
     */
    public Double getMArrCl12W5() {
        return MArrCl12W5;
    }

    /**
     * @return the MName2W5
     */
    public Double getMName2W5() {
        return MName2W5;
    }

    /**
     * @return the MOname2W5
     */
    public Double getMOname2W5() {
        return MOname2W5;
    }

    /**
     * @return the MJname18W5
     */
    public Double getMJname18W5() {
        return MJname18W5;
    }

    /**
     * @return the MJname19W5
     */
    public Double getMJname19W5() {
        return MJname19W5;
    }

    /**
     * @return the MJname20W5
     */
    public Double getMJname20W5() {
        return MJname20W5;
    }

    /**
     * @return the MReas12W5
     */
    public Double getMReas12W5() {
        return MReas12W5;
    }

    /**
     * @return the MReas13W5
     */
    public Double getMReas13W5() {
        return MReas13W5;
    }

    /**
     * @return the MReas14W5
     */
    public Double getMReas14W5() {
        return MReas14W5;
    }

    /**
     * @return the MType2W5
     */
    public Double getMType2W5() {
        return MType2W5;
    }

    /**
     * @return the BridLn1W5
     */
    public Double getBridLn1W5() {
        return BridLn1W5;
    }

    /**
     * @return the BridLn2W5
     */
    public Double getBridLn2W5() {
        return BridLn2W5;
    }

    /**
     * @return the BridLn3W5
     */
    public Double getBridLn3W5() {
        return BridLn3W5;
    }

    /**
     * @return the MEndw2W5
     */
    public Double getMEndw2W5() {
        return MEndw2W5;
    }

    /**
     * @return the Mendnum2W5
     */
    public Double getMendnum2W5() {
        return Mendnum2W5;
    }

    /**
     * @return the MAll2W5
     */
    public Double getMAll2W5() {
        return MAll2W5;
    }

    /**
     * @return the MAllty2W5
     */
    public Double getMAllty2W5() {
        return MAllty2W5;
    }

    /**
     * @return the MVal2W5
     */
    public Double getMVal2W5() {
        return MVal2W5;
    }

    /**
     * @return the MValB2W5
     */
    public Double getMValB2W5() {
        return MValB2W5;
    }

    /**
     * @return the MNeg2W5
     */
    public Double getMNeg2W5() {
        return MNeg2W5;
    }

    /**
     * @return the MNegB2W5
     */
    public Double getMNegB2W5() {
        return MNegB2W5;
    }

    /**
     * @return the MEndv6W5
     */
    public Double getMEndv6W5() {
        return MEndv6W5;
    }

    /**
     * @return the MEndVb6W5
     */
    public Double getMEndVb6W5() {
        return MEndVb6W5;
    }

    /**
     * @return the MEndy6W5
     */
    public Double getMEndy6W5() {
        return MEndy6W5;
    }

    /**
     * @return the MEndv7W5
     */
    public Double getMEndv7W5() {
        return MEndv7W5;
    }

    /**
     * @return the MEndVb7W5
     */
    public Double getMEndVb7W5() {
        return MEndVb7W5;
    }

    /**
     * @return the MEndy7W5
     */
    public Double getMEndy7W5() {
        return MEndy7W5;
    }

    /**
     * @return the MEndv8W5
     */
    public Double getMEndv8W5() {
        return MEndv8W5;
    }

    /**
     * @return the MEndVb8W5
     */
    public Double getMEndVb8W5() {
        return MEndVb8W5;
    }

    /**
     * @return the MEndy8W5
     */
    public Double getMEndy8W5() {
        return MEndy8W5;
    }

    /**
     * @return the MEndx2W5
     */
    public Double getMEndx2W5() {
        return MEndx2W5;
    }

    /**
     * @return the MYLft2W5
     */
    public Double getMYLft2W5() {
        return MYLft2W5;
    }

    /**
     * @return the MYIfCh2W5
     */
    public Double getMYIfCh2W5() {
        return MYIfCh2W5;
    }

    /**
     * @return the MPastSPA2W5
     */
    public Double getMPastSPA2W5() {
        return MPastSPA2W5;
    }

    /**
     * @return the MYPastSPA2W5
     */
    public Double getMYPastSPA2W5() {
        return MYPastSPA2W5;
    }

    /**
     * @return the MPayM2W5
     */
    public Double getMPayM2W5() {
        return MPayM2W5;
    }

    /**
     * @return the MPayB2W5
     */
    public Double getMPayB2W5() {
        return MPayB2W5;
    }

    /**
     * @return the MInc6W5
     */
    public Double getMInc6W5() {
        return MInc6W5;
    }

    /**
     * @return the MPP2W5
     */
    public Double getMPP2W5() {
        return MPP2W5;
    }

    /**
     * @return the MIntPaid2W5
     */
    public Double getMIntPaid2W5() {
        return MIntPaid2W5;
    }

    /**
     * @return the MIntFix2W5
     */
    public Double getMIntFix2W5() {
        return MIntFix2W5;
    }

    /**
     * @return the MIntRate2W5
     */
    public Double getMIntRate2W5() {
        return MIntRate2W5;
    }

    /**
     * @return the MHowPy2W5
     */
    public Double getMHowPy2W5() {
        return MHowPy2W5;
    }

    /**
     * @return the MArrs2W5
     */
    public Double getMArrs2W5() {
        return MArrs2W5;
    }

    /**
     * @return the MArrsV2W5
     */
    public Double getMArrsV2W5() {
        return MArrsV2W5;
    }

    /**
     * @return the MName3W5
     */
    public Double getMName3W5() {
        return MName3W5;
    }

    /**
     * @return the MOname3W5
     */
    public Double getMOname3W5() {
        return MOname3W5;
    }

    /**
     * @return the MJname35W5
     */
    public Double getMJname35W5() {
        return MJname35W5;
    }

    /**
     * @return the MJname36W5
     */
    public Double getMJname36W5() {
        return MJname36W5;
    }

    /**
     * @return the MReas23W5
     */
    public Double getMReas23W5() {
        return MReas23W5;
    }

    /**
     * @return the MReas24W5
     */
    public Double getMReas24W5() {
        return MReas24W5;
    }

    /**
     * @return the MType3W5
     */
    public Double getMType3W5() {
        return MType3W5;
    }

    /**
     * @return the MEndw3W5
     */
    public Double getMEndw3W5() {
        return MEndw3W5;
    }

    /**
     * @return the Mendnum3W5
     */
    public Double getMendnum3W5() {
        return Mendnum3W5;
    }

    /**
     * @return the MAll3W5
     */
    public Double getMAll3W5() {
        return MAll3W5;
    }

    /**
     * @return the MAllty3W5
     */
    public Double getMAllty3W5() {
        return MAllty3W5;
    }

    /**
     * @return the MVal3W5
     */
    public Double getMVal3W5() {
        return MVal3W5;
    }

    /**
     * @return the MValB3W5
     */
    public Double getMValB3W5() {
        return MValB3W5;
    }

    /**
     * @return the MNeg3W5
     */
    public Double getMNeg3W5() {
        return MNeg3W5;
    }

    /**
     * @return the MNegB3W5
     */
    public Double getMNegB3W5() {
        return MNegB3W5;
    }

    /**
     * @return the MEndv11W5
     */
    public Double getMEndv11W5() {
        return MEndv11W5;
    }

    /**
     * @return the MEndVb11W5
     */
    public Double getMEndVb11W5() {
        return MEndVb11W5;
    }

    /**
     * @return the MEndy11W5
     */
    public Double getMEndy11W5() {
        return MEndy11W5;
    }

    /**
     * @return the MEndx3W5
     */
    public Double getMEndx3W5() {
        return MEndx3W5;
    }

    /**
     * @return the MYLft3W5
     */
    public Double getMYLft3W5() {
        return MYLft3W5;
    }

    /**
     * @return the MYIfCh3W5
     */
    public Double getMYIfCh3W5() {
        return MYIfCh3W5;
    }

    /**
     * @return the MPastSPA3W5
     */
    public Double getMPastSPA3W5() {
        return MPastSPA3W5;
    }

    /**
     * @return the MYPastSPA3W5
     */
    public Double getMYPastSPA3W5() {
        return MYPastSPA3W5;
    }

    /**
     * @return the MPayM3W5
     */
    public Double getMPayM3W5() {
        return MPayM3W5;
    }

    /**
     * @return the MPayB3W5
     */
    public Double getMPayB3W5() {
        return MPayB3W5;
    }

    /**
     * @return the MInc11W5
     */
    public Double getMInc11W5() {
        return MInc11W5;
    }

    /**
     * @return the MPP3W5
     */
    public Double getMPP3W5() {
        return MPP3W5;
    }

    /**
     * @return the MIntPaid3W5
     */
    public Double getMIntPaid3W5() {
        return MIntPaid3W5;
    }

    /**
     * @return the MIntFix3W5
     */
    public Double getMIntFix3W5() {
        return MIntFix3W5;
    }

    /**
     * @return the MIntRate3W5
     */
    public Double getMIntRate3W5() {
        return MIntRate3W5;
    }

    /**
     * @return the MHowPy3W5
     */
    public Double getMHowPy3W5() {
        return MHowPy3W5;
    }

    /**
     * @return the MArrs3W5
     */
    public Double getMArrs3W5() {
        return MArrs3W5;
    }

    /**
     * @return the MArrsV3W5
     */
    public Double getMArrsV3W5() {
        return MArrsV3W5;
    }

    /**
     * @return the EqOldW5
     */
    public Double getEqOldW5() {
        return EqOldW5;
    }

    /**
     * @return the EqNewW5
     */
    public Double getEqNewW5() {
        return EqNewW5;
    }

    /**
     * @return the EqYesW5
     */
    public Double getEqYesW5() {
        return EqYesW5;
    }

    /**
     * @return the EqType1W5
     */
    public Double getEqType1W5() {
        return EqType1W5;
    }

    /**
     * @return the EqType2W5
     */
    public Double getEqType2W5() {
        return EqType2W5;
    }

    /**
     * @return the EqWhenYW5
     */
    public Double getEqWhenYW5() {
        return EqWhenYW5;
    }

    /**
     * @return the EqWhenMW5
     */
    public Double getEqWhenMW5() {
        return EqWhenMW5;
    }

    /**
     * @return the EqDrawW5
     */
    public Double getEqDrawW5() {
        return EqDrawW5;
    }

    /**
     * @return the EqValW5
     */
    public Double getEqValW5() {
        return EqValW5;
    }

    /**
     * @return the EqValBW5
     */
    public Double getEqValBW5() {
        return EqValBW5;
    }

    /**
     * @return the EqValDrW5
     */
    public Double getEqValDrW5() {
        return EqValDrW5;
    }

    /**
     * @return the EqValDrBW5
     */
    public Double getEqValDrBW5() {
        return EqValDrBW5;
    }

    /**
     * @return the ERecW5
     */
    public Double getERecW5() {
        return ERecW5;
    }

    /**
     * @return the ERecMPDW5
     */
    public Double getERecMPDW5() {
        return ERecMPDW5;
    }

    /**
     * @return the ERecMBYW5
     */
    public Double getERecMBYW5() {
        return ERecMBYW5;
    }

    /**
     * @return the ERecTaxW5
     */
    public Double getERecTaxW5() {
        return ERecTaxW5;
    }

    /**
     * @return the EIntW5
     */
    public Double getEIntW5() {
        return EIntW5;
    }

    /**
     * @return the EIntFixW5
     */
    public Double getEIntFixW5() {
        return EIntFixW5;
    }

    /**
     * @return the EIntRatW5
     */
    public Double getEIntRatW5() {
        return EIntRatW5;
    }

    /**
     * @return the EMSPayW5
     */
    public Double getEMSPayW5() {
        return EMSPayW5;
    }

    /**
     * @return the EMSArrW5
     */
    public Double getEMSArrW5() {
        return EMSArrW5;
    }

    /**
     * @return the FRRepfixW5
     */
    public Double getFRRepfixW5() {
        return FRRepfixW5;
    }

    /**
     * @return the SharAppW5
     */
    public Double getSharAppW5() {
        return SharAppW5;
    }

    /**
     * @return the ESharAppW5
     */
    public Double getESharAppW5() {
        return ESharAppW5;
    }

    /**
     * @return the ERvReg1W5
     */
    public Double getERvReg1W5() {
        return ERvReg1W5;
    }

    /**
     * @return the ERvReg2W5
     */
    public Double getERvReg2W5() {
        return ERvReg2W5;
    }

    /**
     * @return the ERvSumW5
     */
    public Double getERvSumW5() {
        return ERvSumW5;
    }

    /**
     * @return the ERvSumBW5
     */
    public Double getERvSumBW5() {
        return ERvSumBW5;
    }

    /**
     * @return the ERvRecPDW5
     */
    public Double getERvRecPDW5() {
        return ERvRecPDW5;
    }

    /**
     * @return the ERvRecBYW5
     */
    public Double getERvRecBYW5() {
        return ERvRecBYW5;
    }

    /**
     * @return the ERvTaxW5
     */
    public Double getERvTaxW5() {
        return ERvTaxW5;
    }

    /**
     * @return the ERvRentW5
     */
    public Double getERvRentW5() {
        return ERvRentW5;
    }

    /**
     * @return the ErvPrpW5
     */
    public Double getErvPrpW5() {
        return ErvPrpW5;
    }

    /**
     * @return the EPrvPayW5
     */
    public Double getEPrvPayW5() {
        return EPrvPayW5;
    }

    /**
     * @return the EPrValW5
     */
    public Double getEPrValW5() {
        return EPrValW5;
    }

    /**
     * @return the EPrPerW5
     */
    public Double getEPrPerW5() {
        return EPrPerW5;
    }

    /**
     * @return the EOthRegW5
     */
    public Double getEOthRegW5() {
        return EOthRegW5;
    }

    /**
     * @return the EOthRVaW5
     */
    public Double getEOthRVaW5() {
        return EOthRVaW5;
    }

    /**
     * @return the EOthRVBW5
     */
    public Double getEOthRVBW5() {
        return EOthRVBW5;
    }

    /**
     * @return the PRRepayW5
     */
    public Double getPRRepayW5() {
        return PRRepayW5;
    }

    /**
     * @return the PRRepfixW5
     */
    public Double getPRRepfixW5() {
        return PRRepfixW5;
    }

    /**
     * @return the PRReppcW5
     */
    public Double getPRReppcW5() {
        return PRReppcW5;
    }

    /**
     * @return the GCollecW5
     */
    public Double getGCollecW5() {
        return GCollecW5;
    }

    /**
     * @return the GCollVW5
     */
    public Double getGCollVW5() {
        return GCollVW5;
    }

    /**
     * @return the GCollVbW5
     */
    public Double getGCollVbW5() {
        return GCollVbW5;
    }

    /**
     * @return the GContVbW5
     */
    public Double getGContVbW5() {
        return GContVbW5;
    }

    /**
     * @return the VCarNW5
     */
    public Double getVCarNW5() {
        return VCarNW5;
    }

    /**
     * @return the VType1W5
     */
    public Double getVType1W5() {
        return VType1W5;
    }

    /**
     * @return the VType2W5
     */
    public Double getVType2W5() {
        return VType2W5;
    }

    /**
     * @return the VType3W5
     */
    public Double getVType3W5() {
        return VType3W5;
    }

    /**
     * @return the VpersW5
     */
    public Double getVpersW5() {
        return VpersW5;
    }

    /**
     * @return the VPerVW5
     */
    public Double getVPerVW5() {
        return VPerVW5;
    }

    /**
     * @return the VOTyp1W5
     */
    public Double getVOTyp1W5() {
        return VOTyp1W5;
    }

    /**
     * @return the VOTyp2W5
     */
    public Double getVOTyp2W5() {
        return VOTyp2W5;
    }

    /**
     * @return the VOTyp3W5
     */
    public Double getVOTyp3W5() {
        return VOTyp3W5;
    }

    /**
     * @return the VOTyp4W5
     */
    public Double getVOTyp4W5() {
        return VOTyp4W5;
    }

    /**
     * @return the VOTpNW5
     */
    public Double getVOTpNW5() {
        return VOTpNW5;
    }

    /**
     * @return the VOValW5
     */
    public Double getVOValW5() {
        return VOValW5;
    }

    /**
     * @return the VOVlBW5
     */
    public Double getVOVlBW5() {
        return VOVlBW5;
    }

    /**
     * @return the VCarNw5_i
     */
    public Double getVCarNw5_i() {
        return VCarNw5_i;
    }

    /**
     * @return the VCarNw5_iflag
     */
    public Double getVCarNw5_iflag() {
        return VCarNw5_iflag;
    }

    /**
     * @return the VEstVTotalW5
     */
    public Double getVEstVTotalW5() {
        return VEstVTotalW5;
    }

    /**
     * @return the VEstVTotalBW5
     */
    public Double getVEstVTotalBW5() {
        return VEstVTotalBW5;
    }

    /**
     * @return the VEstVTotalBw5_i
     */
    public Double getVEstVTotalBw5_i() {
        return VEstVTotalBw5_i;
    }

    /**
     * @return the VEstVTotalBw5_iflag
     */
    public Double getVEstVTotalBw5_iflag() {
        return VEstVTotalBw5_iflag;
    }

    /**
     * @return the VEstVTotalw5_i
     */
    public Double getVEstVTotalw5_i() {
        return VEstVTotalw5_i;
    }

    /**
     * @return the VEstVTotalw5_iflag
     */
    public Double getVEstVTotalw5_iflag() {
        return VEstVTotalw5_iflag;
    }

    /**
     * @return the VTypew5_iflag
     */
    public Double getVTypew5_iflag() {
        return VTypew5_iflag;
    }

    /**
     * @return the VOTypw5_iflag
     */
    public Double getVOTypw5_iflag() {
        return VOTypw5_iflag;
    }

    /**
     * @return the VOVlBw5_i
     */
    public Double getVOVlBw5_i() {
        return VOVlBw5_i;
    }

    /**
     * @return the VOVlBw5_iflag
     */
    public Double getVOVlBw5_iflag() {
        return VOVlBw5_iflag;
    }

    /**
     * @return the MNumbw5_i
     */
    public Double getMNumbw5_i() {
        return MNumbw5_i;
    }

    /**
     * @return the MNumbw5_iflag
     */
    public Double getMNumbw5_iflag() {
        return MNumbw5_iflag;
    }

    /**
     * @return the MAll1w5_i
     */
    public Double getMAll1w5_i() {
        return MAll1w5_i;
    }

    /**
     * @return the MAll1w5_iflag
     */
    public Double getMAll1w5_iflag() {
        return MAll1w5_iflag;
    }

    /**
     * @return the MAll2w5_i
     */
    public Double getMAll2w5_i() {
        return MAll2w5_i;
    }

    /**
     * @return the MAll2w5_iflag
     */
    public Double getMAll2w5_iflag() {
        return MAll2w5_iflag;
    }

    /**
     * @return the MAll3w5_i
     */
    public Double getMAll3w5_i() {
        return MAll3w5_i;
    }

    /**
     * @return the MAll3w5_iflag
     */
    public Double getMAll3w5_iflag() {
        return MAll3w5_iflag;
    }

    /**
     * @return the MAllTy1w5_i
     */
    public Double getMAllTy1w5_i() {
        return MAllTy1w5_i;
    }

    /**
     * @return the MAllTy1w5_iflag
     */
    public Double getMAllTy1w5_iflag() {
        return MAllTy1w5_iflag;
    }

    /**
     * @return the MAllTy2w5_i
     */
    public Double getMAllTy2w5_i() {
        return MAllTy2w5_i;
    }

    /**
     * @return the MAllTy2w5_iflag
     */
    public Double getMAllTy2w5_iflag() {
        return MAllTy2w5_iflag;
    }

    /**
     * @return the MVal1w5_i
     */
    public Double getMVal1w5_i() {
        return MVal1w5_i;
    }

    /**
     * @return the MVal1w5_iflag
     */
    public Double getMVal1w5_iflag() {
        return MVal1w5_iflag;
    }

    /**
     * @return the MVal2w5_i
     */
    public Double getMVal2w5_i() {
        return MVal2w5_i;
    }

    /**
     * @return the MVal2w5_iflag
     */
    public Double getMVal2w5_iflag() {
        return MVal2w5_iflag;
    }

    /**
     * @return the MVal3w5_i
     */
    public Double getMVal3w5_i() {
        return MVal3w5_i;
    }

    /**
     * @return the MVal3w5_iflag
     */
    public Double getMVal3w5_iflag() {
        return MVal3w5_iflag;
    }

    /**
     * @return the MNeg1w5_i
     */
    public Double getMNeg1w5_i() {
        return MNeg1w5_i;
    }

    /**
     * @return the MNeg1w5_iflag
     */
    public Double getMNeg1w5_iflag() {
        return MNeg1w5_iflag;
    }

    /**
     * @return the MNeg2w5_i
     */
    public Double getMNeg2w5_i() {
        return MNeg2w5_i;
    }

    /**
     * @return the MNeg2w5_iflag
     */
    public Double getMNeg2w5_iflag() {
        return MNeg2w5_iflag;
    }

    /**
     * @return the Ten1w5_i
     */
    public Double getTen1w5_i() {
        return Ten1w5_i;
    }

    /**
     * @return the Ten1w5_iflag
     */
    public Double getTen1w5_iflag() {
        return Ten1w5_iflag;
    }

    /**
     * @return the HSharew5_i
     */
    public Double getHSharew5_i() {
        return HSharew5_i;
    }

    /**
     * @return the HSharew5_iflag
     */
    public Double getHSharew5_iflag() {
        return HSharew5_iflag;
    }

    /**
     * @return the HSharePw5_i
     */
    public Double getHSharePw5_i() {
        return HSharePw5_i;
    }

    /**
     * @return the HSharePw5_iflag
     */
    public Double getHSharePw5_iflag() {
        return HSharePw5_iflag;
    }

    /**
     * @return the equity_ynw5_i
     */
    public Double getEquity_ynw5_i() {
        return equity_ynw5_i;
    }

    /**
     * @return the equity_ynw5_iflag
     */
    public Double getEquity_ynw5_iflag() {
        return equity_ynw5_iflag;
    }

    /**
     * @return the eqtype1w5_i
     */
    public Double getEqtype1w5_i() {
        return eqtype1w5_i;
    }

    /**
     * @return the eqtype1w5_iflag
     */
    public Double getEqtype1w5_iflag() {
        return eqtype1w5_iflag;
    }

    /**
     * @return the eqdraww5_i
     */
    public Double getEqdraww5_i() {
        return eqdraww5_i;
    }

    /**
     * @return the eqdraww5_iflag
     */
    public Double getEqdraww5_iflag() {
        return eqdraww5_iflag;
    }

    /**
     * @return the eqvalbw5_i
     */
    public Double getEqvalbw5_i() {
        return eqvalbw5_i;
    }

    /**
     * @return the eqvalbw5_iflag
     */
    public Double getEqvalbw5_iflag() {
        return eqvalbw5_iflag;
    }

    /**
     * @return the eqvalw5_i
     */
    public Double getEqvalw5_i() {
        return eqvalw5_i;
    }

    /**
     * @return the eqvalw5_iflag
     */
    public Double getEqvalw5_iflag() {
        return eqvalw5_iflag;
    }

    /**
     * @return the eintw5_i
     */
    public Double getEintw5_i() {
        return eintw5_i;
    }

    /**
     * @return the eintw5_iflag
     */
    public Double getEintw5_iflag() {
        return eintw5_iflag;
    }

    /**
     * @return the eintratw5_i
     */
    public Double getEintratw5_i() {
        return eintratw5_i;
    }

    /**
     * @return the eintratw5_iflag
     */
    public Double getEintratw5_iflag() {
        return eintratw5_iflag;
    }

    /**
     * @return the ervprpw5_i
     */
    public Double getErvprpw5_i() {
        return ervprpw5_i;
    }

    /**
     * @return the ervprpw5_iflag
     */
    public Double getErvprpw5_iflag() {
        return ervprpw5_iflag;
    }

    /**
     * @return the prrepayw5_i
     */
    public Double getPrrepayw5_i() {
        return prrepayw5_i;
    }

    /**
     * @return the prrepayw5_iflag
     */
    public Double getPrrepayw5_iflag() {
        return prrepayw5_iflag;
    }

    /**
     * @return the prrepfixw5_i
     */
    public Double getPrrepfixw5_i() {
        return prrepfixw5_i;
    }

    /**
     * @return the prrepfixw5_iflag
     */
    public Double getPrrepfixw5_iflag() {
        return prrepfixw5_iflag;
    }

    /**
     * @return the prreppcw5_i
     */
    public Double getPrreppcw5_i() {
        return prreppcw5_i;
    }

    /**
     * @return the prreppcw5_iflag
     */
    public Double getPrreppcw5_iflag() {
        return prreppcw5_iflag;
    }

    /**
     * @return the mtype1w5_i
     */
    public Double getMtype1w5_i() {
        return mtype1w5_i;
    }

    /**
     * @return the mtype1w5_iflag
     */
    public Double getMtype1w5_iflag() {
        return mtype1w5_iflag;
    }

    /**
     * @return the mtype2w5_i
     */
    public Double getMtype2w5_i() {
        return mtype2w5_i;
    }

    /**
     * @return the mtype2w5_iflag
     */
    public Double getMtype2w5_iflag() {
        return mtype2w5_iflag;
    }

    /**
     * @return the mtype3w5_i
     */
    public Double getMtype3w5_i() {
        return mtype3w5_i;
    }

    /**
     * @return the mtype3w5_iflag
     */
    public Double getMtype3w5_iflag() {
        return mtype3w5_iflag;
    }

    /**
     * @return the mendw1W5_i
     */
    public Double getMendw1W5_i() {
        return mendw1W5_i;
    }

    /**
     * @return the mendw1W5_iflag
     */
    public Double getMendw1W5_iflag() {
        return mendw1W5_iflag;
    }

    /**
     * @return the mendw2w5_i
     */
    public Double getMendw2w5_i() {
        return mendw2w5_i;
    }

    /**
     * @return the mendw2w5_iflag
     */
    public Double getMendw2w5_iflag() {
        return mendw2w5_iflag;
    }

    /**
     * @return the mendw3w5_i
     */
    public Double getMendw3w5_i() {
        return mendw3w5_i;
    }

    /**
     * @return the mendw3w5_iflag
     */
    public Double getMendw3w5_iflag() {
        return mendw3w5_iflag;
    }

    /**
     * @return the mendnum1w5_i
     */
    public Double getMendnum1w5_i() {
        return mendnum1w5_i;
    }

    /**
     * @return the mendnum1w5_iflag
     */
    public Double getMendnum1w5_iflag() {
        return mendnum1w5_iflag;
    }

    /**
     * @return the mendnum2w5_i
     */
    public Double getMendnum2w5_i() {
        return mendnum2w5_i;
    }

    /**
     * @return the mendnum2w5_iflag
     */
    public Double getMendnum2w5_iflag() {
        return mendnum2w5_iflag;
    }

    /**
     * @return the mendnum3w5_i
     */
    public Double getMendnum3w5_i() {
        return mendnum3w5_i;
    }

    /**
     * @return the mendnum3w5_iflag
     */
    public Double getMendnum3w5_iflag() {
        return mendnum3w5_iflag;
    }

    /**
     * @return the mendvb1w5_i
     */
    public Double getMendvb1w5_i() {
        return mendvb1w5_i;
    }

    /**
     * @return the mendvb1w5_iflag
     */
    public Double getMendvb1w5_iflag() {
        return mendvb1w5_iflag;
    }

    /**
     * @return the mendvb2w5_i
     */
    public Double getMendvb2w5_i() {
        return mendvb2w5_i;
    }

    /**
     * @return the mendvb2w5_iflag
     */
    public Double getMendvb2w5_iflag() {
        return mendvb2w5_iflag;
    }

    /**
     * @return the mendvb3w5_i
     */
    public Double getMendvb3w5_i() {
        return mendvb3w5_i;
    }

    /**
     * @return the mendvb3w5_iflag
     */
    public Double getMendvb3w5_iflag() {
        return mendvb3w5_iflag;
    }

    /**
     * @return the mendvb4w5_i
     */
    public Double getMendvb4w5_i() {
        return mendvb4w5_i;
    }

    /**
     * @return the mendvb4w5_iflag
     */
    public Double getMendvb4w5_iflag() {
        return mendvb4w5_iflag;
    }

    /**
     * @return the mendvb5w5_i
     */
    public Double getMendvb5w5_i() {
        return mendvb5w5_i;
    }

    /**
     * @return the mendvb5w5_iflag
     */
    public Double getMendvb5w5_iflag() {
        return mendvb5w5_iflag;
    }

    /**
     * @return the mendvb9w5_i
     */
    public Double getMendvb9w5_i() {
        return mendvb9w5_i;
    }

    /**
     * @return the mendvb9w5_iflag
     */
    public Double getMendvb9w5_iflag() {
        return mendvb9w5_iflag;
    }

    /**
     * @return the mendvb6w5_i
     */
    public Double getMendvb6w5_i() {
        return mendvb6w5_i;
    }

    /**
     * @return the mendvb6w5_iflag
     */
    public Double getMendvb6w5_iflag() {
        return mendvb6w5_iflag;
    }

    /**
     * @return the mendvb7w5_i
     */
    public Double getMendvb7w5_i() {
        return mendvb7w5_i;
    }

    /**
     * @return the mendvb7w5_iflag
     */
    public Double getMendvb7w5_iflag() {
        return mendvb7w5_iflag;
    }

    /**
     * @return the mendvb8w5_i
     */
    public Double getMendvb8w5_i() {
        return mendvb8w5_i;
    }

    /**
     * @return the mendvb8w5_iflag
     */
    public Double getMendvb8w5_iflag() {
        return mendvb8w5_iflag;
    }

    /**
     * @return the mendvb11w5_i
     */
    public Double getMendvb11w5_i() {
        return mendvb11w5_i;
    }

    /**
     * @return the mendvb11w5_iflag
     */
    public Double getMendvb11w5_iflag() {
        return mendvb11w5_iflag;
    }

    /**
     * @return the mendv1w5_i
     */
    public Double getMendv1w5_i() {
        return mendv1w5_i;
    }

    /**
     * @return the mendv1w5_iflag
     */
    public Double getMendv1w5_iflag() {
        return mendv1w5_iflag;
    }

    /**
     * @return the mendv2w5_i
     */
    public Double getMendv2w5_i() {
        return mendv2w5_i;
    }

    /**
     * @return the mendv2w5_iflag
     */
    public Double getMendv2w5_iflag() {
        return mendv2w5_iflag;
    }

    /**
     * @return the mendv3w5_i
     */
    public Double getMendv3w5_i() {
        return mendv3w5_i;
    }

    /**
     * @return the mendv3w5_iflag
     */
    public Double getMendv3w5_iflag() {
        return mendv3w5_iflag;
    }

    /**
     * @return the mendv4w5_i
     */
    public Double getMendv4w5_i() {
        return mendv4w5_i;
    }

    /**
     * @return the mendv4w5_iflag
     */
    public Double getMendv4w5_iflag() {
        return mendv4w5_iflag;
    }

    /**
     * @return the mendv5w5_i
     */
    public Double getMendv5w5_i() {
        return mendv5w5_i;
    }

    /**
     * @return the mendv5w5_iflag
     */
    public Double getMendv5w5_iflag() {
        return mendv5w5_iflag;
    }

    /**
     * @return the mendv6w5_i
     */
    public Double getMendv6w5_i() {
        return mendv6w5_i;
    }

    /**
     * @return the mendv6w5_iflag
     */
    public Double getMendv6w5_iflag() {
        return mendv6w5_iflag;
    }

    /**
     * @return the mendv7w5_i
     */
    public Double getMendv7w5_i() {
        return mendv7w5_i;
    }

    /**
     * @return the mendv7w5_iflag
     */
    public Double getMendv7w5_iflag() {
        return mendv7w5_iflag;
    }

    /**
     * @return the mendv10w5_i
     */
    public Double getMendv10w5_i() {
        return mendv10w5_i;
    }

    /**
     * @return the mendv10w5_iflag
     */
    public Double getMendv10w5_iflag() {
        return mendv10w5_iflag;
    }

    /**
     * @return the mendv11w5_i
     */
    public Double getMendv11w5_i() {
        return mendv11w5_i;
    }

    /**
     * @return the mendv11w5_iflag
     */
    public Double getMendv11w5_iflag() {
        return mendv11w5_iflag;
    }

    /**
     * @return the HValuew5_i
     */
    public Double getHValuew5_i() {
        return HValuew5_i;
    }

    /**
     * @return the HValuew5_iflag
     */
    public Double getHValuew5_iflag() {
        return HValuew5_iflag;
    }

    /**
     * @return the DVHValuew5
     */
    public Double getDVHValuew5() {
        return DVHValuew5;
    }

    /**
     * @return the TotMValw5
     */
    public Double getTotMValw5() {
        return TotMValw5;
    }

    /**
     * @return the TotMNegw5
     */
    public Double getTotMNegw5() {
        return TotMNegw5;
    }

    /**
     * @return the TotMortw5
     */
    public Double getTotMortw5() {
        return TotMortw5;
    }

    /**
     * @return the DVEqRelValw5
     */
    public Double getDVEqRelValw5() {
        return DVEqRelValw5;
    }

    /**
     * @return the DVEqLTMw5
     */
    public Double getDVEqLTMw5() {
        return DVEqLTMw5;
    }

    /**
     * @return the DVHoRPw5
     */
    public Double getDVHoRPw5() {
        return DVHoRPw5;
    }

    /**
     * @return the DVEqSRbw5
     */
    public Double getDVEqSRbw5() {
        return DVEqSRbw5;
    }

    /**
     * @return the DVEqPrUnw5
     */
    public Double getDVEqPrUnw5() {
        return DVEqPrUnw5;
    }

    /**
     * @return the HMORTGw5
     */
    public Double getHMORTGw5() {
        return HMORTGw5;
    }

    /**
     * @return the AllEndWw5
     */
    public Double getAllEndWw5() {
        return AllEndWw5;
    }

    /**
     * @return the HseTypeW5
     */
    public Double getHseTypeW5() {
        return HseTypeW5;
    }

    /**
     * @return the VType1w5_i
     */
    public Double getVType1w5_i() {
        return VType1w5_i;
    }

    /**
     * @return the VType2w5_i
     */
    public Double getVType2w5_i() {
        return VType2w5_i;
    }

    /**
     * @return the VType3w5_i
     */
    public Double getVType3w5_i() {
        return VType3w5_i;
    }

    /**
     * @return the VPerSw5_i
     */
    public Double getVPerSw5_i() {
        return VPerSw5_i;
    }

    /**
     * @return the VPerSw5_iflag
     */
    public Double getVPerSw5_iflag() {
        return VPerSw5_iflag;
    }

    /**
     * @return the VPerVw5_i
     */
    public Double getVPerVw5_i() {
        return VPerVw5_i;
    }

    /**
     * @return the VPerVw5_iflag
     */
    public Double getVPerVw5_iflag() {
        return VPerVw5_iflag;
    }

    /**
     * @return the VOTyp1w5_i
     */
    public Double getVOTyp1w5_i() {
        return VOTyp1w5_i;
    }

    /**
     * @return the VOTyp2w5_i
     */
    public Double getVOTyp2w5_i() {
        return VOTyp2w5_i;
    }

    /**
     * @return the VOTyp3w5_i
     */
    public Double getVOTyp3w5_i() {
        return VOTyp3w5_i;
    }

    /**
     * @return the VOTyp4w5_i
     */
    public Double getVOTyp4w5_i() {
        return VOTyp4w5_i;
    }

    /**
     * @return the VOTyp5w5_i
     */
    public Double getVOTyp5w5_i() {
        return VOTyp5w5_i;
    }

    /**
     * @return the VOValw5_i
     */
    public Double getVOValw5_i() {
        return VOValw5_i;
    }

    /**
     * @return the VOValw5_iflag
     */
    public Double getVOValw5_iflag() {
        return VOValw5_iflag;
    }

    /**
     * @return the gcollecw5_i
     */
    public Double getGcollecw5_i() {
        return gcollecw5_i;
    }

    /**
     * @return the gcollecw5_iflag
     */
    public Double getGcollecw5_iflag() {
        return gcollecw5_iflag;
    }

    /**
     * @return the gcollvw5_i
     */
    public Double getGcollvw5_i() {
        return gcollvw5_i;
    }

    /**
     * @return the gcollvw5_iflag
     */
    public Double getGcollvw5_iflag() {
        return gcollvw5_iflag;
    }

    /**
     * @return the gcontvbw5_i
     */
    public Double getGcontvbw5_i() {
        return gcontvbw5_i;
    }

    /**
     * @return the gcontvbw5_iflag
     */
    public Double getGcontvbw5_iflag() {
        return gcontvbw5_iflag;
    }

    /**
     * @return the DVHseValw5_sum
     */
    public Double getDVHseValw5_sum() {
        return DVHseValw5_sum;
    }

    /**
     * @return the DVBltValw5_sum
     */
    public Double getDVBltValw5_sum() {
        return DVBltValw5_sum;
    }

    /**
     * @return the DVBldValw5_sum
     */
    public Double getDVBldValw5_sum() {
        return DVBldValw5_sum;
    }

    /**
     * @return the DVLUKValw5_sum
     */
    public Double getDVLUKValw5_sum() {
        return DVLUKValw5_sum;
    }

    /**
     * @return the DVLOSValw5_sum
     */
    public Double getDVLOSValw5_sum() {
        return DVLOSValw5_sum;
    }

    /**
     * @return the DVOPrValw5_sum
     */
    public Double getDVOPrValw5_sum() {
        return DVOPrValw5_sum;
    }

    /**
     * @return the DVHseDebtw5_sum
     */
    public Double getDVHseDebtw5_sum() {
        return DVHseDebtw5_sum;
    }

    /**
     * @return the DVBLtDebtw5_sum
     */
    public Double getDVBLtDebtw5_sum() {
        return DVBLtDebtw5_sum;
    }

    /**
     * @return the DVBldDebtw5_sum
     */
    public Double getDVBldDebtw5_sum() {
        return DVBldDebtw5_sum;
    }

    /**
     * @return the DVLUKDebtw5_sum
     */
    public Double getDVLUKDebtw5_sum() {
        return DVLUKDebtw5_sum;
    }

    /**
     * @return the DVLOSDebtw5_sum
     */
    public Double getDVLOSDebtw5_sum() {
        return DVLOSDebtw5_sum;
    }

    /**
     * @return the DVOPrDebtw5_sum
     */
    public Double getDVOPrDebtw5_sum() {
        return DVOPrDebtw5_sum;
    }

    /**
     * @return the OthMortw5_sum
     */
    public Double getOthMortw5_sum() {
        return OthMortw5_sum;
    }

    /**
     * @return the HousGdsTW5_sum
     */
    public Double getHousGdsTW5_sum() {
        return HousGdsTW5_sum;
    }

    /**
     * @return the BuyLGdsTW5_sum
     */
    public Double getBuyLGdsTW5_sum() {
        return BuyLGdsTW5_sum;
    }

    /**
     * @return the HousGdsOSTW5_sum
     */
    public Double getHousGdsOSTW5_sum() {
        return HousGdsOSTW5_sum;
    }

    /**
     * @return the PhysHousGdsTW5_aggr
     */
    public Double getPhysHousGdsTW5_aggr() {
        return PhysHousGdsTW5_aggr;
    }

    /**
     * @return the PhysBuyLGdsTW5_aggr
     */
    public Double getPhysBuyLGdsTW5_aggr() {
        return PhysBuyLGdsTW5_aggr;
    }

    /**
     * @return the PhysHousGdsOSTW5_aggr
     */
    public Double getPhysHousGdsOSTW5_aggr() {
        return PhysHousGdsOSTW5_aggr;
    }

    /**
     * @return the GContVlsW5
     */
    public Double getGContVlsW5() {
        return GContVlsW5;
    }

    /**
     * @return the DVGCollVW5
     */
    public Double getDVGCollVW5() {
        return DVGCollVW5;
    }

    /**
     * @return the AllGdW5
     */
    public Double getAllGdW5() {
        return AllGdW5;
    }

    /**
     * @return the DVTotOthVehValW5
     */
    public Double getDVTotOthVehValW5() {
        return DVTotOthVehValW5;
    }

    /**
     * @return the DVTotPerNPValW5
     */
    public Double getDVTotPerNPValW5() {
        return DVTotPerNPValW5;
    }

    /**
     * @return the DVTotVehValW5
     */
    public Double getDVTotVehValW5() {
        return DVTotVehValW5;
    }

    /**
     * @return the HPHYSWW5
     */
    public Double getHPHYSWW5() {
        return HPHYSWW5;
    }

    /**
     * @return the DVPropertyw5
     */
    public Double getDVPropertyw5() {
        return DVPropertyw5;
    }

    /**
     * @return the HPROPWw5
     */
    public Double getHPROPWw5() {
        return HPROPWw5;
    }

    /**
     * @return the DVCISAVw5_aggr
     */
    public Double getDVCISAVw5_aggr() {
        return DVCISAVw5_aggr;
    }

    /**
     * @return the DVIISAVw5_aggr
     */
    public Double getDVIISAVw5_aggr() {
        return DVIISAVw5_aggr;
    }

    /**
     * @return the DVKISAVw5_aggr
     */
    public Double getDVKISAVw5_aggr() {
        return DVKISAVw5_aggr;
    }

    /**
     * @return the DVFNSValw5_aggr
     */
    public Double getDVFNSValw5_aggr() {
        return DVFNSValw5_aggr;
    }

    /**
     * @return the DVFShUKVw5_aggr
     */
    public Double getDVFShUKVw5_aggr() {
        return DVFShUKVw5_aggr;
    }

    /**
     * @return the DVInsVw5_aggr
     */
    public Double getDVInsVw5_aggr() {
        return DVInsVw5_aggr;
    }

    /**
     * @return the DVFLfEnVw5_aggr
     */
    public Double getDVFLfEnVw5_aggr() {
        return DVFLfEnVw5_aggr;
    }

    /**
     * @return the DVFLfSiVw5_aggr
     */
    public Double getDVFLfSiVw5_aggr() {
        return DVFLfSiVw5_aggr;
    }

    /**
     * @return the DVFLfFSVw5_aggr
     */
    public Double getDVFLfFSVw5_aggr() {
        return DVFLfFSVw5_aggr;
    }

    /**
     * @return the DVFLfTEVw5_aggr
     */
    public Double getDVFLfTEVw5_aggr() {
        return DVFLfTEVw5_aggr;
    }

    /**
     * @return the DVFBondVw5_aggr
     */
    public Double getDVFBondVw5_aggr() {
        return DVFBondVw5_aggr;
    }

    /**
     * @return the DVFESHARESw5_aggr
     */
    public Double getDVFESHARESw5_aggr() {
        return DVFESHARESw5_aggr;
    }

    /**
     * @return the DVFEShavw5_aggr
     */
    public Double getDVFEShavw5_aggr() {
        return DVFEShavw5_aggr;
    }

    /**
     * @return the DVFEOptVw5_aggr
     */
    public Double getDVFEOptVw5_aggr() {
        return DVFEOptVw5_aggr;
    }

    /**
     * @return the DVFCollVw5_aggr
     */
    public Double getDVFCollVw5_aggr() {
        return DVFCollVw5_aggr;
    }

    /**
     * @return the DVFShOSVw5_aggr
     */
    public Double getDVFShOSVw5_aggr() {
        return DVFShOSVw5_aggr;
    }

    /**
     * @return the DVFGltUKVw5_aggr
     */
    public Double getDVFGltUKVw5_aggr() {
        return DVFGltUKVw5_aggr;
    }

    /**
     * @return the DVFGltFOVw5_aggr
     */
    public Double getDVFGltFOVw5_aggr() {
        return DVFGltFOVw5_aggr;
    }

    /**
     * @return the DVFInvOtVw5_aggr
     */
    public Double getDVFInvOtVw5_aggr() {
        return DVFInvOtVw5_aggr;
    }

    /**
     * @return the DVFInfValw5_aggr
     */
    public Double getDVFInfValw5_aggr() {
        return DVFInfValw5_aggr;
    }

    /**
     * @return the DVFInfLVw5_aggr
     */
    public Double getDVFInfLVw5_aggr() {
        return DVFInfLVw5_aggr;
    }

    /**
     * @return the DVFInfVw5_aggr
     */
    public Double getDVFInfVw5_aggr() {
        return DVFInfVw5_aggr;
    }

    /**
     * @return the DVISAValw5_aggr
     */
    public Double getDVISAValw5_aggr() {
        return DVISAValw5_aggr;
    }

    /**
     * @return the DVFFAssetsw5_aggr
     */
    public Double getDVFFAssetsw5_aggr() {
        return DVFFAssetsw5_aggr;
    }

    /**
     * @return the DVCACTvw5_aggr
     */
    public Double getDVCACTvw5_aggr() {
        return DVCACTvw5_aggr;
    }

    /**
     * @return the DVCASVVw5_aggr
     */
    public Double getDVCASVVw5_aggr() {
        return DVCASVVw5_aggr;
    }

    /**
     * @return the DVCAValw5_aggr
     */
    public Double getDVCAValw5_aggr() {
        return DVCAValw5_aggr;
    }

    /**
     * @return the DVCaCrValw5_aggr
     */
    public Double getDVCaCrValw5_aggr() {
        return DVCaCrValw5_aggr;
    }

    /**
     * @return the DVCAOdValw5_aggr
     */
    public Double getDVCAOdValw5_aggr() {
        return DVCAOdValw5_aggr;
    }

    /**
     * @return the DVSaValw5_aggr
     */
    public Double getDVSaValw5_aggr() {
        return DVSaValw5_aggr;
    }

    /**
     * @return the TOTCCw5_aggr
     */
    public Double getTOTCCw5_aggr() {
        return TOTCCw5_aggr;
    }

    /**
     * @return the TOTSCw5_aggr
     */
    public Double getTOTSCw5_aggr() {
        return TOTSCw5_aggr;
    }

    /**
     * @return the TotMow5_aggr
     */
    public Double getTotMow5_aggr() {
        return TotMow5_aggr;
    }

    /**
     * @return the TotNslBnkw5_aggr
     */
    public Double getTotNslBnkw5_aggr() {
        return TotNslBnkw5_aggr;
    }

    /**
     * @return the TotNslCw5_aggr
     */
    public Double getTotNslCw5_aggr() {
        return TotNslCw5_aggr;
    }

    /**
     * @return the TotOslbnkw5_aggr
     */
    public Double getTotOslbnkw5_aggr() {
        return TotOslbnkw5_aggr;
    }

    /**
     * @return the TotOSLCw5_aggr
     */
    public Double getTotOSLCw5_aggr() {
        return TotOSLCw5_aggr;
    }

    /**
     * @return the TotSLBnkw5_aggr
     */
    public Double getTotSLBnkw5_aggr() {
        return TotSLBnkw5_aggr;
    }

    /**
     * @return the TotSLCw5_aggr
     */
    public Double getTotSLCw5_aggr() {
        return TotSLCw5_aggr;
    }

    /**
     * @return the TotBillArrw5_aggr
     */
    public Double getTotBillArrw5_aggr() {
        return TotBillArrw5_aggr;
    }

    /**
     * @return the TotHPArrw5_aggr
     */
    public Double getTotHPArrw5_aggr() {
        return TotHPArrw5_aggr;
    }

    /**
     * @return the TotMOArrw5_aggr
     */
    public Double getTotMOArrw5_aggr() {
        return TotMOArrw5_aggr;
    }

    /**
     * @return the TotLNArrw5_aggr
     */
    public Double getTotLNArrw5_aggr() {
        return TotLNArrw5_aggr;
    }

    /**
     * @return the TotDVNWFLNw5_aggr
     */
    public Double getTotDVNWFLNw5_aggr() {
        return TotDVNWFLNw5_aggr;
    }

    /**
     * @return the TOTDVNWILNw5_aggr
     */
    public Double getTOTDVNWILNw5_aggr() {
        return TOTDVNWILNw5_aggr;
    }

    /**
     * @return the TotArr_excMortw5_aggr
     */
    public Double getTotArr_excMortw5_aggr() {
        return TotArr_excMortw5_aggr;
    }

    /**
     * @return the HFINW_excENDWw5_aggr
     */
    public Double getHFINW_excENDWw5_aggr() {
        return HFINW_excENDWw5_aggr;
    }

    /**
     * @return the HFINWw5_sum
     */
    public Double getHFINWw5_sum() {
        return HFINWw5_sum;
    }

    /**
     * @return the DVValDBTw5_aggr
     */
    public Double getDVValDBTw5_aggr() {
        return DVValDBTw5_aggr;
    }

    /**
     * @return the DVValDCosw5_aggr
     */
    public Double getDVValDCosw5_aggr() {
        return DVValDCosw5_aggr;
    }

    /**
     * @return the DVPAVCUVw5_aggr
     */
    public Double getDVPAVCUVw5_aggr() {
        return DVPAVCUVw5_aggr;
    }

    /**
     * @return the DVPFDDVw5_aggr
     */
    public Double getDVPFDDVw5_aggr() {
        return DVPFDDVw5_aggr;
    }

    /**
     * @return the DVPPValw5_aggr
     */
    public Double getDVPPValw5_aggr() {
        return DVPPValw5_aggr;
    }

    /**
     * @return the TotLosw5_aggr
     */
    public Double getTotLosw5_aggr() {
        return TotLosw5_aggr;
    }

    /**
     * @return the TotNLosw5_aggr
     */
    public Double getTotNLosw5_aggr() {
        return TotNLosw5_aggr;
    }

    /**
     * @return the TotFLNSw5_aggr
     */
    public Double getTotFLNSw5_aggr() {
        return TotFLNSw5_aggr;
    }

    /**
     * @return the TotHPw5_aggr
     */
    public Double getTotHPw5_aggr() {
        return TotHPw5_aggr;
    }

    /**
     * @return the TotLNSw5_aggr
     */
    public Double getTotLNSw5_aggr() {
        return TotLNSw5_aggr;
    }

    /**
     * @return the HFINLw5_aggr
     */
    public Double getHFINLw5_aggr() {
        return HFINLw5_aggr;
    }

    /**
     * @return the HFINWNTw5_sum
     */
    public Double getHFINWNTw5_sum() {
        return HFINWNTw5_sum;
    }

    /**
     * @return the DVSPenw5_aggr
     */
    public Double getDVSPenw5_aggr() {
        return DVSPenw5_aggr;
    }

    /**
     * @return the DVPInPValw5_aggr
     */
    public Double getDVPInPValw5_aggr() {
        return DVPInPValw5_aggr;
    }

    /**
     * @return the DVDBRWealthValw5_aggr
     */
    public Double getDVDBRWealthValw5_aggr() {
        return DVDBRWealthValw5_aggr;
    }

    /**
     * @return the TOTPENw5_aggr
     */
    public Double getTOTPENw5_aggr() {
        return TOTPENw5_aggr;
    }

    /**
     * @return the TotWlthW5
     */
    public Double getTotWlthW5() {
        return TotWlthW5;
    }

    /**
     * @return the DVPFCurValw5_aggr
     */
    public Double getDVPFCurValw5_aggr() {
        return DVPFCurValw5_aggr;
    }

    /**
     * @return the DVTotGIRW5
     */
    public Double getDVTotGIRW5() {
        return DVTotGIRW5;
    }

    /**
     * @return the DVNIothIW5_aggr
     */
    public Double getDVNIothIW5_aggr() {
        return DVNIothIW5_aggr;
    }

    /**
     * @return the HRPDVAgeW5
     */
    public Double getHRPDVAgeW5() {
        return HRPDVAgeW5;
    }

    /**
     * @return the HRPDVAge9W5
     */
    public Double getHRPDVAge9W5() {
        return HRPDVAge9W5;
    }

    /**
     * @return the HRPSexW5
     */
    public Double getHRPSexW5() {
        return HRPSexW5;
    }

    /**
     * @return the HRPNSSEC3W5
     */
    public Double getHRPNSSEC3W5() {
        return HRPNSSEC3W5;
    }

    /**
     * @return the HRPDVILO3aW5
     */
    public Double getHRPDVILO3aW5() {
        return HRPDVILO3aW5;
    }

    /**
     * @return the HRPDVMrDfW5
     */
    public Double getHRPDVMrDfW5() {
        return HRPDVMrDfW5;
    }

    /**
     * @return the HRPEdAttn1W5
     */
    public Double getHRPEdAttn1W5() {
        return HRPEdAttn1W5;
    }

    /**
     * @return the HRPEdAttn2W5
     */
    public Double getHRPEdAttn2W5() {
        return HRPEdAttn2W5;
    }

    /**
     * @return the HRPEdAttn3W5
     */
    public Double getHRPEdAttn3W5() {
        return HRPEdAttn3W5;
    }

    /**
     * @return the HRPDVecactw5
     */
    public Double getHRPDVecactw5() {
        return HRPDVecactw5;
    }

    /**
     * @return the HRPIOut1W5
     */
    public Double getHRPIOut1W5() {
        return HRPIOut1W5;
    }

    /**
     * @return the HRPPartIOut1W5
     */
    public Double getHRPPartIOut1W5() {
        return HRPPartIOut1W5;
    }

    /**
     * @return the OfinalW5
     */
    public Double getOfinalW5() {
        return OfinalW5;
    }

    /**
     * @return the NoUnitsW5
     */
    public Double getNoUnitsW5() {
        return NoUnitsW5;
    }

    /**
     * @return the DVHSizeW5
     */
    public Double getDVHSizeW5() {
        return DVHSizeW5;
    }

    /**
     * @return the GORW5
     */
    public Double getGORW5() {
        return GORW5;
    }

    /**
     * @return the NumAdISwW5
     */
    public Double getNumAdISwW5() {
        return NumAdISwW5;
    }

    /**
     * @return the NumAdultW5
     */
    public Double getNumAdultW5() {
        return NumAdultW5;
    }

    /**
     * @return the NumCh18W5
     */
    public Double getNumCh18W5() {
        return NumCh18W5;
    }

    /**
     * @return the NumChildW5
     */
    public Double getNumChildW5() {
        return NumChildW5;
    }

    /**
     * @return the NumCivPtrW5
     */
    public Double getNumCivPtrW5() {
        return NumCivPtrW5;
    }

    /**
     * @return the NumCPartW5
     */
    public Double getNumCPartW5() {
        return NumCPartW5;
    }

    /**
     * @return the NumDepChW5
     */
    public Double getNumDepChW5() {
        return NumDepChW5;
    }

    /**
     * @return the NumElChW5
     */
    public Double getNumElChW5() {
        return NumElChW5;
    }

    /**
     * @return the NumHHldrW5
     */
    public Double getNumHHldrW5() {
        return NumHHldrW5;
    }

    /**
     * @return the NumMPartW5
     */
    public Double getNumMPartW5() {
        return NumMPartW5;
    }

    /**
     * @return the NumNoElChW5
     */
    public Double getNumNoElChW5() {
        return NumNoElChW5;
    }

    /**
     * @return the HHoldTypeW5
     */
    public Double getHHoldTypeW5() {
        return HHoldTypeW5;
    }

    /**
     * @return the HRPEdLevelW5
     */
    public Double getHRPEdLevelW5() {
        return HRPEdLevelW5;
    }

    /**
     * @return the HBedrmw5
     */
    public Double getHBedrmw5() {
        return HBedrmw5;
    }

    /**
     * @return the CASEw5
     */
    public Double getCASEw5() {
        return CASEw5;
    }

    /**
     * @return the DVTotNIRW5
     */
    public Double getDVTotNIRW5() {
        return DVTotNIRW5;
    }

    /**
     * @return the dvtotgirw5_3sf
     */
    public Double getDvtotgirw5_3sf() {
        return dvtotgirw5_3sf;
    }

    /**
     * @return the DVGISEw5_AGGR
     */
    public Double getDVGISEw5_AGGR() {
        return DVGISEw5_AGGR;
    }

    /**
     * @return the DVGIPPENw5_AGGR
     */
    public Double getDVGIPPENw5_AGGR() {
        return DVGIPPENw5_AGGR;
    }

    /**
     * @return the DVGIEMPw5_AGGR
     */
    public Double getDVGIEMPw5_AGGR() {
        return DVGIEMPw5_AGGR;
    }

    /**
     * @return the DVBenefitAnnualw5_aggr
     */
    public Double getDVBenefitAnnualw5_aggr() {
        return DVBenefitAnnualw5_aggr;
    }

    /**
     * @return the DVGIINVw5_aggr
     */
    public Double getDVGIINVw5_aggr() {
        return DVGIINVw5_aggr;
    }

    /**
     * @return the DVGrsRentAmtAnnualw5_aggr
     */
    public Double getDVGrsRentAmtAnnualw5_aggr() {
        return DVGrsRentAmtAnnualw5_aggr;
    }

    /**
     * @return the DVNISEw5_aggr
     */
    public Double getDVNISEw5_aggr() {
        return DVNISEw5_aggr;
    }

    /**
     * @return the DVNIPpenw5_aggr
     */
    public Double getDVNIPpenw5_aggr() {
        return DVNIPpenw5_aggr;
    }

    /**
     * @return the DVNIEMPw5_aggr
     */
    public Double getDVNIEMPw5_aggr() {
        return DVNIEMPw5_aggr;
    }

    /**
     * @return the DVNIINVw5_aggr
     */
    public Double getDVNIINVw5_aggr() {
        return DVNIINVw5_aggr;
    }

    /**
     * @return the DVNetRentAmtAnnualw5_aggr
     */
    public Double getDVNetRentAmtAnnualw5_aggr() {
        return DVNetRentAmtAnnualw5_aggr;
    }

    /**
     * @return the DVoiNrrAnnualw5_aggr
     */
    public Double getDVoiNrrAnnualw5_aggr() {
        return DVoiNrrAnnualw5_aggr;
    }

    /**
     * @return the DVoiNgtAnnualw5_aggr
     */
    public Double getDVoiNgtAnnualw5_aggr() {
        return DVoiNgtAnnualw5_aggr;
    }

    /**
     * @return the DVoiNegAnnualw5_aggr
     */
    public Double getDVoiNegAnnualw5_aggr() {
        return DVoiNegAnnualw5_aggr;
    }

    /**
     * @return the DVoiNfrAnnualw5_aggr
     */
    public Double getDVoiNfrAnnualw5_aggr() {
        return DVoiNfrAnnualw5_aggr;
    }

    /**
     * @return the DVoiNmaAnnualw5_aggr
     */
    public Double getDVoiNmaAnnualw5_aggr() {
        return DVoiNmaAnnualw5_aggr;
    }

    /**
     * @return the DVoiNroAnnualw5_aggr
     */
    public Double getDVoiNroAnnualw5_aggr() {
        return DVoiNroAnnualw5_aggr;
    }

    /**
     * @return the DVoiNopAnnualw5_aggr
     */
    public Double getDVoiNopAnnualw5_aggr() {
        return DVoiNopAnnualw5_aggr;
    }

    /**
     * @return the DVoiGrrAnnualw5_aggr
     */
    public Double getDVoiGrrAnnualw5_aggr() {
        return DVoiGrrAnnualw5_aggr;
    }

    /**
     * @return the DVoiGgtAnnualw5_aggr
     */
    public Double getDVoiGgtAnnualw5_aggr() {
        return DVoiGgtAnnualw5_aggr;
    }

    /**
     * @return the DVoiGegAnnualw5_aggr
     */
    public Double getDVoiGegAnnualw5_aggr() {
        return DVoiGegAnnualw5_aggr;
    }

    /**
     * @return the DVoiGfrAnnualw5_aggr
     */
    public Double getDVoiGfrAnnualw5_aggr() {
        return DVoiGfrAnnualw5_aggr;
    }

    /**
     * @return the DVoiGmaAnnualw5_aggr
     */
    public Double getDVoiGmaAnnualw5_aggr() {
        return DVoiGmaAnnualw5_aggr;
    }

    /**
     * @return the DVoiGroAnnualw5_aggr
     */
    public Double getDVoiGroAnnualw5_aggr() {
        return DVoiGroAnnualw5_aggr;
    }

    /**
     * @return the DVoiGopAnnualw5_aggr
     */
    public Double getDVoiGopAnnualw5_aggr() {
        return DVoiGopAnnualw5_aggr;
    }

    /**
     * @return the DVGIothRw5_aggr
     */
    public Double getDVGIothRw5_aggr() {
        return DVGIothRw5_aggr;
    }

    /**
     * @return the DVNIothRw5_aggr
     */
    public Double getDVNIothRw5_aggr() {
        return DVNIothRw5_aggr;
    }

    /**
     * @return the DVErecNetAnnualw5
     */
    public Double getDVErecNetAnnualw5() {
        return DVErecNetAnnualw5;
    }

    /**
     * @return the DVERvrecNetAnnualw5
     */
    public Double getDVERvrecNetAnnualw5() {
        return DVERvrecNetAnnualw5;
    }

    /**
     * @return the DVErecGrossAnnualw5
     */
    public Double getDVErecGrossAnnualw5() {
        return DVErecGrossAnnualw5;
    }

    /**
     * @return the DVERvrecGrossAnnualw5
     */
    public Double getDVERvrecGrossAnnualw5() {
        return DVERvrecGrossAnnualw5;
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

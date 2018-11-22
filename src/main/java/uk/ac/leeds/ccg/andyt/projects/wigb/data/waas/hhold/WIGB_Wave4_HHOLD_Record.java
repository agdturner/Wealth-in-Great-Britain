/**
 * Source code generated by uk.ac.leeds.ccg.andyt.projects.wigb.process.WIGB_JavaCodeGenerator
 */
package uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold;
public class WIGB_Wave4_HHOLD_Record extends WIGB_Wave4Or5_HHOLD_Record {
protected Double DVTOTCARVAL;
protected Double DVTOTMOTBVAL;
protected Double DVTOTVANVAL;
protected Double EQREAS1;
protected Double EQREAS2;
protected Double EQREAS3;
protected Double EQREAS4;
protected Double HINTRO;
protected Double VESTV1;
protected Double VESTV2;
protected Double VESTV2_I;
protected Double VESTV2_IFLAG;
protected Double VESTV_I;
protected Double VESTV_IFLAG;
protected Double VESVB1;
protected Double VESVB2;
protected Double VESVB3;
protected Double VTYPE2_IFLAG;
protected Double VTYPE_I;
protected Double W4XSHHWGT;
protected final void initDVTOTCARVAL(String s) {
if (!s.trim().isEmpty()) {
DVTOTCARVAL = new Double(s);
}
}

protected final void initDVTOTMOTBVAL(String s) {
if (!s.trim().isEmpty()) {
DVTOTMOTBVAL = new Double(s);
}
}

protected final void initDVTOTVANVAL(String s) {
if (!s.trim().isEmpty()) {
DVTOTVANVAL = new Double(s);
}
}

protected final void initEQREAS1(String s) {
if (!s.trim().isEmpty()) {
EQREAS1 = new Double(s);
}
}

protected final void initEQREAS2(String s) {
if (!s.trim().isEmpty()) {
EQREAS2 = new Double(s);
}
}

protected final void initEQREAS3(String s) {
if (!s.trim().isEmpty()) {
EQREAS3 = new Double(s);
}
}

protected final void initEQREAS4(String s) {
if (!s.trim().isEmpty()) {
EQREAS4 = new Double(s);
}
}

protected final void initHINTRO(String s) {
if (!s.trim().isEmpty()) {
HINTRO = new Double(s);
}
}

protected final void initVESTV1(String s) {
if (!s.trim().isEmpty()) {
VESTV1 = new Double(s);
}
}

protected final void initVESTV2(String s) {
if (!s.trim().isEmpty()) {
VESTV2 = new Double(s);
}
}

protected final void initVESTV2_I(String s) {
if (!s.trim().isEmpty()) {
VESTV2_I = new Double(s);
}
}

protected final void initVESTV2_IFLAG(String s) {
if (!s.trim().isEmpty()) {
VESTV2_IFLAG = new Double(s);
}
}

protected final void initVESTV_I(String s) {
if (!s.trim().isEmpty()) {
VESTV_I = new Double(s);
}
}

protected final void initVESTV_IFLAG(String s) {
if (!s.trim().isEmpty()) {
VESTV_IFLAG = new Double(s);
}
}

protected final void initVESVB1(String s) {
if (!s.trim().isEmpty()) {
VESVB1 = new Double(s);
}
}

protected final void initVESVB2(String s) {
if (!s.trim().isEmpty()) {
VESVB2 = new Double(s);
}
}

protected final void initVESVB3(String s) {
if (!s.trim().isEmpty()) {
VESVB3 = new Double(s);
}
}

protected final void initVTYPE2_IFLAG(String s) {
if (!s.trim().isEmpty()) {
VTYPE2_IFLAG = new Double(s);
}
}

protected final void initVTYPE_I(String s) {
if (!s.trim().isEmpty()) {
VTYPE_I = new Double(s);
}
}

protected final void initW4XSHHWGT(String s) {
if (!s.trim().isEmpty()) {
W4XSHHWGT = new Double(s);
}
}

public Double getDVTOTCARVAL() {
return DVTOTCARVAL;
}

public Double getDVTOTMOTBVAL() {
return DVTOTMOTBVAL;
}

public Double getDVTOTVANVAL() {
return DVTOTVANVAL;
}

public Double getEQREAS1() {
return EQREAS1;
}

public Double getEQREAS2() {
return EQREAS2;
}

public Double getEQREAS3() {
return EQREAS3;
}

public Double getEQREAS4() {
return EQREAS4;
}

public Double getHINTRO() {
return HINTRO;
}

public Double getVESTV1() {
return VESTV1;
}

public Double getVESTV2() {
return VESTV2;
}

public Double getVESTV2_I() {
return VESTV2_I;
}

public Double getVESTV2_IFLAG() {
return VESTV2_IFLAG;
}

public Double getVESTV_I() {
return VESTV_I;
}

public Double getVESTV_IFLAG() {
return VESTV_IFLAG;
}

public Double getVESVB1() {
return VESVB1;
}

public Double getVESVB2() {
return VESVB2;
}

public Double getVESVB3() {
return VESVB3;
}

public Double getVTYPE2_IFLAG() {
return VTYPE2_IFLAG;
}

public Double getVTYPE_I() {
return VTYPE_I;
}

public Double getW4XSHHWGT() {
return W4XSHHWGT;
}

public WIGB_Wave4_HHOLD_Record(String line) {
s = line.split("\t");
initYEARW1(s[0]);
initMONTHW1(s[1]);
initYEARW2(s[2]);
initMONTHW2(s[3]);
initYEARW3(s[4]);
initMONTHW3(s[5]);
initYEAR(s[6]);
initMONTH(s[7]);
initHOUT(s[8]);
initW4XSHHWGT(s[9]);
initACCOM(s[10]);
initFLTTYP(s[11]);
initACCOTH(s[12]);
initTEN1(s[13]);
initTIED(s[14]);
initLLORD(s[15]);
initFURN(s[16]);
initDVPRIRNT(s[17]);
initHHOWN(s[18]);
initHINTRO(s[19]);
initHAGEB(s[20]);
initHAGEYR(s[21]);
initHBUYYR(s[22]);
initHBUYSE(s[23]);
initHSHARE(s[24]);
initHSHAREP(s[25]);
initHPRICE(s[26]);
initHPRICEB(s[27]);
initHBFROM(s[28]);
initHRTBEV(s[29]);
initHHOSCH(s[30]);
initHVALUE(s[31]);
initHVALB(s[32]);
initHEXT1(s[33]);
initHEXT2(s[34]);
initHEXT3(s[35]);
initHEXT4(s[36]);
initMNUMBN(s[37]);
initMNUMB(s[38]);
initMW2CHK1(s[39]);
initMW2CHK2(s[40]);
initMW2CHK3(s[41]);
initMEXTN(s[42]);
initMEXTRS1(s[43]);
initMEXTRS2(s[44]);
initMEXTRS3(s[45]);
initMCHGE(s[46]);
initMCHGENUM(s[47]);
initMNUMBO(s[48]);
initMNAME1(s[49]);
initMONAME1(s[50]);
initMJNAME01(s[51]);
initMJNAME02(s[52]);
initMJNAME03(s[53]);
initMJNAME04(s[54]);
initMREAS01(s[55]);
initMREAS02(s[56]);
initMREAS03(s[57]);
initMREAS04(s[58]);
initMREAS05(s[59]);
initMREAS06(s[60]);
initMREAS07(s[61]);
initMTYPE1(s[62]);
initMENDW1(s[63]);
initMENDNUM1(s[64]);
initMALL1(s[65]);
initMALLTY1(s[66]);
initMVAL1(s[67]);
initMVALB1(s[68]);
initMNEG1(s[69]);
initMNEGB1(s[70]);
initMENDV1(s[71]);
initMENDVB1(s[72]);
initMENDY1(s[73]);
initMENDV2(s[74]);
initMENDVB2(s[75]);
initMENDY2(s[76]);
initMENDV3(s[77]);
initMENDVB3(s[78]);
initMENDY3(s[79]);
initMENDV4(s[80]);
initMENDVB4(s[81]);
initMENDY4(s[82]);
initMENDV5(s[83]);
initMENDX1(s[84]);
initMINVW1(s[85]);
initMINVW2(s[86]);
initMINVW3(s[87]);
initMPOLICY1(s[88]);
initMPOLICY2(s[89]);
initMYLFT1(s[90]);
initMYIFCH1(s[91]);
initMPAYM1(s[92]);
initMPAYB1(s[93]);
initMINC1(s[94]);
initMINC2(s[95]);
initMINC3(s[96]);
initMINC4(s[97]);
initMINC5(s[98]);
initMPP1(s[99]);
initMHOWPY1(s[100]);
initMARRS1(s[101]);
initMARRSV1(s[102]);
initMARR2YR1(s[103]);
initMARR2YR2(s[104]);
initMARR2YR3(s[105]);
initMARRCL01(s[106]);
initMARRCL02(s[107]);
initMARRCL12(s[108]);
initMNAME2(s[109]);
initMONAME2(s[110]);
initMJNAME18(s[111]);
initMJNAME19(s[112]);
initMJNAME20(s[113]);
initMREAS12(s[114]);
initMREAS13(s[115]);
initMREAS14(s[116]);
initMTYPE2(s[117]);
initBRIDLN1(s[118]);
initBRIDLN2(s[119]);
initBRIDLN3(s[120]);
initMENDW2(s[121]);
initMENDNUM2(s[122]);
initMALL2(s[123]);
initMALLTY2(s[124]);
initMVAL2(s[125]);
initMVALB2(s[126]);
initMNEG2(s[127]);
initMNEGB2(s[128]);
initMENDV6(s[129]);
initMENDVB6(s[130]);
initMENDY6(s[131]);
initMENDV7(s[132]);
initMENDVB7(s[133]);
initMENDY7(s[134]);
initMENDV8(s[135]);
initMENDX2(s[136]);
initMYLFT2(s[137]);
initMYIFCH2(s[138]);
initMPAYM2(s[139]);
initMPAYB2(s[140]);
initMINC6(s[141]);
initMPP2(s[142]);
initMHOWPY2(s[143]);
initMARRS2(s[144]);
initMARRSV2(s[145]);
initMNAME3(s[146]);
initMONAME3(s[147]);
initMJNAME35(s[148]);
initMJNAME36(s[149]);
initMREAS23(s[150]);
initMREAS24(s[151]);
initMTYPE3(s[152]);
initMENDW3(s[153]);
initMENDNUM3(s[154]);
initMALL3(s[155]);
initMALLTY3(s[156]);
initMVAL3(s[157]);
initMVALB3(s[158]);
initMNEG3(s[159]);
initMNEGB3(s[160]);
initMENDV11(s[161]);
initMENDVB11(s[162]);
initMENDY11(s[163]);
initMENDX3(s[164]);
initMYLFT3(s[165]);
initMYIFCH3(s[166]);
initMPAYM3(s[167]);
initMPAYB3(s[168]);
initMINC11(s[169]);
initMPP3(s[170]);
initMHOWPY3(s[171]);
initMARRS3(s[172]);
initMARRSV3(s[173]);
initEQOLD(s[174]);
initEQNEW(s[175]);
initEQYES(s[176]);
initEQTYPE1(s[177]);
initEQTYPE2(s[178]);
initEQREAS1(s[179]);
initEQREAS2(s[180]);
initEQREAS3(s[181]);
initEQREAS4(s[182]);
initEQWHENY(s[183]);
initEQWHENM(s[184]);
initEQDRAW(s[185]);
initEQVAL(s[186]);
initEQVALB(s[187]);
initEQVALDR(s[188]);
initEQVALDRB(s[189]);
initEREC(s[190]);
initERECMPD(s[191]);
initERECMBY(s[192]);
initERECTAX(s[193]);
initEINT(s[194]);
initEINTFIX(s[195]);
initEINTRAT(s[196]);
initEMSPAY(s[197]);
initEMSARR(s[198]);
initFRREPFIX(s[199]);
initSHARAPP(s[200]);
initESHARAPP(s[201]);
initERVREG1(s[202]);
initERVREG2(s[203]);
initERVSUM(s[204]);
initERVSUMB(s[205]);
initERVRECPD(s[206]);
initERVRECBY(s[207]);
initERVTAX(s[208]);
initERVRENT(s[209]);
initERVPRP(s[210]);
initEPRVPAY(s[211]);
initEPRVAL(s[212]);
initEPRPER(s[213]);
initEOTHREG(s[214]);
initEOTHRVA(s[215]);
initEOTHRVB(s[216]);
initPRREPAY(s[217]);
initPRREPFIX(s[218]);
initPRREPPC(s[219]);
initGCOLLEC(s[220]);
initGCOLLV(s[221]);
initGCOLLVB(s[222]);
initGCONTVB(s[223]);
initVCARN(s[224]);
initVTYPE1(s[225]);
initVESTV1(s[226]);
initVESVB1(s[227]);
initVTYPE2(s[228]);
initVESTV2(s[229]);
initVESVB2(s[230]);
initVPERS(s[231]);
initVPERV(s[232]);
initVOTYP1(s[233]);
initVOTYP2(s[234]);
initVOTYP3(s[235]);
initVOTYP4(s[236]);
initVOTPN(s[237]);
initVOVAL(s[238]);
initVOVLB(s[239]);
initMNUMB_I(s[240]);
initMNUMB_IFLAG(s[241]);
initMALL1_I(s[242]);
initMALL1_IFLAG(s[243]);
initMALL2_I(s[244]);
initMALL2_IFLAG(s[245]);
initMALL3_I(s[246]);
initMALL3_IFLAG(s[247]);
initMALLTY1_I(s[248]);
initMALLTY1_IFLAG(s[249]);
initMALLTY2_I(s[250]);
initMALLTY2_IFLAG(s[251]);
initMVAL1_I(s[252]);
initMVAL1_IFLAG(s[253]);
initMVAL2_I(s[254]);
initMVAL2_IFLAG(s[255]);
initMVAL3_I(s[256]);
initMVAL3_IFLAG(s[257]);
initMNEG1_I(s[258]);
initMNEG1_IFLAG(s[259]);
initMNEG2_I(s[260]);
initMNEG2_IFLAG(s[261]);
initTEN1_I(s[262]);
initTEN1_IFLAG(s[263]);
initHSHARE_I(s[264]);
initHSHARE_IFLAG(s[265]);
initHSHAREP_I(s[266]);
initHSHAREP_IFLAG(s[267]);
initEQUITY_YN_I(s[268]);
initEQUITY_YN_IFLAG(s[269]);
initEQTYPE1_I(s[270]);
initEQTYPE1_IFLAG(s[271]);
initEQDRAW_I(s[272]);
initEQDRAW_IFLAG(s[273]);
initEQVALB_I(s[274]);
initEQVALB_IFLAG(s[275]);
initEQVAL_I(s[276]);
initEQVAL_IFLAG(s[277]);
initEINT_I(s[278]);
initEINT_IFLAG(s[279]);
initEINTRAT_I(s[280]);
initEINTRAT_IFLAG(s[281]);
initERVPRP_I(s[282]);
initERVPRP_IFLAG(s[283]);
initPRREPAY_I(s[284]);
initPRREPAY_IFLAG(s[285]);
initPRREPFIX_I(s[286]);
initPRREPFIX_IFLAG(s[287]);
initPRREPPC_I(s[288]);
initPRREPPC_IFLAG(s[289]);
initMTYPE1_I(s[290]);
initMTYPE1_IFLAG(s[291]);
initMTYPE2_I(s[292]);
initMTYPE2_IFLAG(s[293]);
initMTYPE3_I(s[294]);
initMTYPE3_IFLAG(s[295]);
initMENDW1_I(s[296]);
initMENDW1_IFLAG(s[297]);
initMENDW2_I(s[298]);
initMENDW2_IFLAG(s[299]);
initMENDW3_I(s[300]);
initMENDW3_IFLAG(s[301]);
initMENDNUM1_I(s[302]);
initMENDNUM1_IFLAG(s[303]);
initMENDNUM2_I(s[304]);
initMENDNUM2_IFLAG(s[305]);
initMENDNUM3_I(s[306]);
initMENDNUM3_IFLAG(s[307]);
initMENDVB1_I(s[308]);
initMENDVB1_IFLAG(s[309]);
initMENDVB2_I(s[310]);
initMENDVB2_IFLAG(s[311]);
initMENDVB3_I(s[312]);
initMENDVB3_IFLAG(s[313]);
initMENDVB4_I(s[314]);
initMENDVB4_IFLAG(s[315]);
initMENDVB5_I(s[316]);
initMENDVB5_IFLAG(s[317]);
initMENDVB9_I(s[318]);
initMENDVB9_IFLAG(s[319]);
initMENDVB6_I(s[320]);
initMENDVB6_IFLAG(s[321]);
initMENDVB7_I(s[322]);
initMENDVB7_IFLAG(s[323]);
initMENDVB8_I(s[324]);
initMENDVB8_IFLAG(s[325]);
initMENDVB11_I(s[326]);
initMENDVB11_IFLAG(s[327]);
initMENDV1_I(s[328]);
initMENDV1_IFLAG(s[329]);
initMENDV2_I(s[330]);
initMENDV2_IFLAG(s[331]);
initMENDV3_I(s[332]);
initMENDV3_IFLAG(s[333]);
initMENDV4_I(s[334]);
initMENDV4_IFLAG(s[335]);
initMENDV5_I(s[336]);
initMENDV5_IFLAG(s[337]);
initMENDV6_I(s[338]);
initMENDV6_IFLAG(s[339]);
initMENDV7_I(s[340]);
initMENDV7_IFLAG(s[341]);
initHVALUE_I(s[342]);
initHVALUE_IFLAG(s[343]);
initDVHVALUE(s[344]);
initTOTMVAL(s[345]);
initTOTMNEG(s[346]);
initTOTMORT(s[347]);
initDVEQRELVAL(s[348]);
initDVEQLTM(s[349]);
initDVHORP(s[350]);
initDVEQSRB(s[351]);
initDVEQPRUN(s[352]);
initHMORTG(s[353]);
initALLENDW(s[354]);
initHSETYPE(s[355]);
initVCARN_I(s[356]);
initVCARN_IFLAG(s[357]);
initVESTV_I(s[358]);
initVESTV_IFLAG(s[359]);
initVESTV2_I(s[360]);
initVESTV2_IFLAG(s[361]);
initVTYPE_I(s[362]);
initVTYPE_IFLAG(s[363]);
initVTYPE2_I(s[364]);
initVTYPE2_IFLAG(s[365]);
initVPERS_I(s[366]);
initVPERS_IFLAG(s[367]);
initVPERV_I(s[368]);
initVPERV_IFLAG(s[369]);
initVOTYP1_I(s[370]);
initVOTYP2_I(s[371]);
initVOTYP3_I(s[372]);
initVOTYP4_I(s[373]);
initVOTYP5_I(s[374]);
initVOVAL_I(s[375]);
initVOVAL_IFLAG(s[376]);
initGCOLLEC_I(s[377]);
initGCOLLEC_IFLAG(s[378]);
initGCOLLV_I(s[379]);
initGCOLLV_IFLAG(s[380]);
initGCONTVB_I(s[381]);
initGCONTVB_IFLAG(s[382]);
initDVHSEVAL_SUM(s[383]);
initDVBLTVAL_SUM(s[384]);
initDVBLDVAL_SUM(s[385]);
initDVLUKVAL_SUM(s[386]);
initDVLOSVAL_SUM(s[387]);
initDVOPRVAL_SUM(s[388]);
initDVHSEDEBT_SUM(s[389]);
initDVBLTDEBT_SUM(s[390]);
initDVBLDDEBT_SUM(s[391]);
initDVLUKDEBT_SUM(s[392]);
initDVLOSDEBT_SUM(s[393]);
initDVOPRDEBT_SUM(s[394]);
initOTHMORT_SUM(s[395]);
initHOUSGDST_SUM(s[396]);
initBUYLGDST_SUM(s[397]);
initHOUSGDSOST_SUM(s[398]);
initPHYSHOUSGDST_AGGR(s[399]);
initPHYSBUYLGDST_AGGR(s[400]);
initPHYSHOUSGDSOST_AGGR(s[401]);
initGCONTVLS(s[402]);
initDVGCOLLV(s[403]);
initALLGD(s[404]);
initDVTOTCARVAL(s[405]);
initDVTOTVANVAL(s[406]);
initDVTOTMOTBVAL(s[407]);
initDVTOTOTHVEHVAL(s[408]);
initDVTOTPERNPVAL(s[409]);
initDVTOTVEHVAL(s[410]);
initHPHYSW(s[411]);
initDVPROPERTY(s[412]);
initHPROPW(s[413]);
initDVCISAV_AGGR(s[414]);
initDVIISAV_AGGR(s[415]);
initDVKISAV_AGGR(s[416]);
initDVFNSVAL_AGGR(s[417]);
initDVFSHUKV_AGGR(s[418]);
initDVINSV_AGGR(s[419]);
initDVFLFENV_AGGR(s[420]);
initDVFLFSIV_AGGR(s[421]);
initDVFLFFSV_AGGR(s[422]);
initDVFLFTEV_AGGR(s[423]);
initDVFBONDV_AGGR(s[424]);
initDVFESHARES_AGGR(s[425]);
initDVFESHAV_AGGR(s[426]);
initDVFEOPTV_AGGR(s[427]);
initDVFCOLLV_AGGR(s[428]);
initDVFSHOSV_AGGR(s[429]);
initDVFGLTUKV_AGGR(s[430]);
initDVFGLTFOV_AGGR(s[431]);
initDVFINVOTV_AGGR(s[432]);
initDVFINFVAL_AGGR(s[433]);
initDVFINFLV_AGGR(s[434]);
initDVFINFV_AGGR(s[435]);
initDVISAVAL_AGGR(s[436]);
initDVFFASSETS_AGGR(s[437]);
initDVCACTV_AGGR(s[438]);
initDVCASVV_AGGR(s[439]);
initDVCAVAL_AGGR(s[440]);
initDVCACRVAL_AGGR(s[441]);
initDVCAODVAL_AGGR(s[442]);
initDVSAVAL_AGGR(s[443]);
initTOTCC_AGGR(s[444]);
initTOTSC_AGGR(s[445]);
initTOTMO_AGGR(s[446]);
initTOTNSLBNK_AGGR(s[447]);
initTOTNSLC_AGGR(s[448]);
initTOTOSLBNK_AGGR(s[449]);
initTOTOSLC_AGGR(s[450]);
initTOTSLBNK_AGGR(s[451]);
initTOTSLC_AGGR(s[452]);
initTOTBILLARR_AGGR(s[453]);
initTOTHPARR_AGGR(s[454]);
initTOTMOARR_AGGR(s[455]);
initTOTLNARR_AGGR(s[456]);
initTOTDVNWFLN_AGGR(s[457]);
initTOTDVNWILN_AGGR(s[458]);
initTOTARR_EXCMORT_AGGR(s[459]);
initHFINW_EXCENDW_AGGR(s[460]);
initHFINW_SUM(s[461]);
initDVVALDBT_AGGR(s[462]);
initDVVALDCOS_AGGR(s[463]);
initDVPAVCUV_AGGR(s[464]);
initDVPFDDV_AGGR(s[465]);
initDVPPVAL_AGGR(s[466]);
initTOTLOS_AGGR(s[467]);
initTOTNLOS_AGGR(s[468]);
initTOTFLNS_AGGR(s[469]);
initTOTHP_AGGR(s[470]);
initTOTLNS_AGGR(s[471]);
initHFINL_AGGR(s[472]);
initHFINWNT_SUM(s[473]);
initDVSPEN_AGGR(s[474]);
initDVPINPVAL_AGGR(s[475]);
initDVDBRWEALTHVAL_AGGR(s[476]);
initTOTPEN_AGGR(s[477]);
initTOTWLTH(s[478]);
initDVPFCURVAL_AGGR(s[479]);
initDVNIOTHI_AGGR(s[480]);
initHRPDVAGE(s[481]);
initHRPDVAGE9(s[482]);
initHRPSEX(s[483]);
initHRPNSSEC3(s[484]);
initHRPDVILO3A(s[485]);
initHRPDVMRDF(s[486]);
initHRPEDATTN1(s[487]);
initHRPEDATTN2(s[488]);
initHRPEDATTN3(s[489]);
initHRPDVECACT(s[490]);
initHRPIOUT1(s[491]);
initHRPPARTIOUT1(s[492]);
initOFINAL(s[493]);
initNOUNITS(s[494]);
initDVHSIZE(s[495]);
initGOR(s[496]);
initNUMADULT(s[497]);
initNUMCH18(s[498]);
initNUMCHILD(s[499]);
initNUMCIVPTR(s[500]);
initNUMCPART(s[501]);
initNUMDEPCH(s[502]);
initNUMELCH(s[503]);
initNUMHHLDR(s[504]);
initNUMMPART(s[505]);
initNUMNOELCH(s[506]);
initHHOLDTYPE(s[507]);
initHRPEDLEVEL(s[508]);
initVESVB3(s[509]);
initHBEDRM(s[510]);
initCASEW4(s[511]);
initCASEW3(s[512]);
initCASEW2(s[513]);
initCASEW1(s[514]);
initDVTOTNIR(s[515]);
initDVTOTGIR(s[516]);
initDVGISE_AGGR(s[517]);
initDVGIPPEN_AGGR(s[518]);
initDVGIEMP_AGGR(s[519]);
initDVBENEFITANNUAL_AGGR(s[520]);
initDVGIINV_AGGR(s[521]);
initDVGRSRENTAMTANNUAL_AGGR(s[522]);
initDVNISE_AGGR(s[523]);
initDVNIPPEN_AGGR(s[524]);
initDVNIEMP_AGGR(s[525]);
initDVNIINV_AGGR(s[526]);
initDVNETRENTAMTANNUAL_AGGR(s[527]);
initDVOINRRANNUAL_AGGR(s[528]);
initDVOINGTANNUAL_AGGR(s[529]);
initDVOINEGANNUAL_AGGR(s[530]);
initDVOINFRANNUAL_AGGR(s[531]);
initDVOINMAANNUAL_AGGR(s[532]);
initDVOINROANNUAL_AGGR(s[533]);
initDVOINOPANNUAL_AGGR(s[534]);
initDVOIGRRANNUAL_AGGR(s[535]);
initDVOIGGTANNUAL_AGGR(s[536]);
initDVOIGEGANNUAL_AGGR(s[537]);
initDVOIGFRANNUAL_AGGR(s[538]);
initDVOIGMAANNUAL_AGGR(s[539]);
initDVOIGROANNUAL_AGGR(s[540]);
initDVOIGOPANNUAL_AGGR(s[541]);
initDVGIOTHR_AGGR(s[542]);
initDVNIOTHR_AGGR(s[543]);
initDVERECNETANNUAL(s[544]);
initDVERVRECNETANNUAL(s[545]);
initDVERECGROSSANNUAL(s[546]);
initDVERVRECGROSSANNUAL(s[547]);
}
}

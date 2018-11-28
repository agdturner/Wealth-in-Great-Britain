/**
 * Source code generated by uk.ac.leeds.ccg.andyt.projects.wigb.process.WIGB_JavaCodeGenerator
 */
package uk.ac.leeds.ccg.andyt.projects.wigb.data.waas.hhold;
public class WIGB_Wave5_HHOLD_Record extends WIGB_Wave4Or5_HHOLD_Record {
protected short CASEW5;
protected int DVTOTGIR_3SF;
protected boolean MENDV10_I;
protected boolean MENDV10_IFLAG;
protected int MENDV11_I;
protected boolean MENDV11_IFLAG;
protected boolean MENDVB8;
protected boolean MENDY8;
protected byte MINTFIX1;
protected byte MINTFIX2;
protected byte MINTFIX3;
protected byte MINTPAID1;
protected byte MINTPAID2;
protected byte MINTPAID3;
protected double MINTRATE1;
protected double MINTRATE2;
protected double MINTRATE3;
protected byte MONTHW4;
protected byte MPASTSPA1;
protected byte MPASTSPA2;
protected byte MPASTSPA3;
protected byte MYPASTSPA1;
protected byte MYPASTSPA2;
protected byte MYPASTSPA3;
protected byte NUMADISW;
protected int VESTVTOTAL;
protected byte VESTVTOTALB;
protected byte VESTVTOTALB_I;
protected boolean VESTVTOTALB_IFLAG;
protected int VESTVTOTAL_I;
protected boolean VESTVTOTAL_IFLAG;
protected boolean VOTYP_IFLAG;
protected byte VOVLB_I;
protected boolean VOVLB_IFLAG;
protected byte VTYPE1_I;
protected byte VTYPE3;
protected byte VTYPE3_I;
protected double W5XSHHWGT;
protected short YEARW4;
protected final void initCASEW5(String s) {
if (!s.trim().isEmpty()) {
CASEW5 = Short.parseShort(s);
} else {
CASEW5 = Short.MIN_VALUE;
}
}

protected final void initDVTOTGIR_3SF(String s) {
if (!s.trim().isEmpty()) {
DVTOTGIR_3SF = Integer.parseInt(s);
} else {
DVTOTGIR_3SF = Integer.MIN_VALUE;
}
}

protected final void initMENDV10_I(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == -7) {
MENDV10_I = false;
} else {
MENDV10_I = true;
}
}
}

protected final void initMENDV10_IFLAG(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == 0) {
MENDV10_IFLAG = false;
} else {
MENDV10_IFLAG = true;
}
}
}

protected final void initMENDV11_I(String s) {
if (!s.trim().isEmpty()) {
MENDV11_I = Integer.parseInt(s);
} else {
MENDV11_I = Integer.MIN_VALUE;
}
}

protected final void initMENDV11_IFLAG(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == 0) {
MENDV11_IFLAG = false;
} else {
MENDV11_IFLAG = true;
}
}
}

protected final void initMENDVB8(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == -7) {
MENDVB8 = false;
} else {
MENDVB8 = true;
}
}
}

protected final void initMENDY8(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == -7) {
MENDY8 = false;
} else {
MENDY8 = true;
}
}
}

protected final void initMINTFIX1(String s) {
if (!s.trim().isEmpty()) {
MINTFIX1 = Byte.parseByte(s);
} else {
MINTFIX1 = Byte.MIN_VALUE;
}
}

protected final void initMINTFIX2(String s) {
if (!s.trim().isEmpty()) {
MINTFIX2 = Byte.parseByte(s);
} else {
MINTFIX2 = Byte.MIN_VALUE;
}
}

protected final void initMINTFIX3(String s) {
if (!s.trim().isEmpty()) {
MINTFIX3 = Byte.parseByte(s);
} else {
MINTFIX3 = Byte.MIN_VALUE;
}
}

protected final void initMINTPAID1(String s) {
if (!s.trim().isEmpty()) {
MINTPAID1 = Byte.parseByte(s);
} else {
MINTPAID1 = Byte.MIN_VALUE;
}
}

protected final void initMINTPAID2(String s) {
if (!s.trim().isEmpty()) {
MINTPAID2 = Byte.parseByte(s);
} else {
MINTPAID2 = Byte.MIN_VALUE;
}
}

protected final void initMINTPAID3(String s) {
if (!s.trim().isEmpty()) {
MINTPAID3 = Byte.parseByte(s);
} else {
MINTPAID3 = Byte.MIN_VALUE;
}
}

protected final void initMINTRATE1(String s) {
if (!s.trim().isEmpty()) {
MINTRATE1 = Double.parseDouble(s);
} else {
MINTRATE1 = Double.NaN;
}
}

protected final void initMINTRATE2(String s) {
if (!s.trim().isEmpty()) {
MINTRATE2 = Double.parseDouble(s);
} else {
MINTRATE2 = Double.NaN;
}
}

protected final void initMINTRATE3(String s) {
if (!s.trim().isEmpty()) {
MINTRATE3 = Double.parseDouble(s);
} else {
MINTRATE3 = Double.NaN;
}
}

protected final void initMONTHW4(String s) {
if (!s.trim().isEmpty()) {
MONTHW4 = Byte.parseByte(s);
} else {
MONTHW4 = Byte.MIN_VALUE;
}
}

protected final void initMPASTSPA1(String s) {
if (!s.trim().isEmpty()) {
MPASTSPA1 = Byte.parseByte(s);
} else {
MPASTSPA1 = Byte.MIN_VALUE;
}
}

protected final void initMPASTSPA2(String s) {
if (!s.trim().isEmpty()) {
MPASTSPA2 = Byte.parseByte(s);
} else {
MPASTSPA2 = Byte.MIN_VALUE;
}
}

protected final void initMPASTSPA3(String s) {
if (!s.trim().isEmpty()) {
MPASTSPA3 = Byte.parseByte(s);
} else {
MPASTSPA3 = Byte.MIN_VALUE;
}
}

protected final void initMYPASTSPA1(String s) {
if (!s.trim().isEmpty()) {
MYPASTSPA1 = Byte.parseByte(s);
} else {
MYPASTSPA1 = Byte.MIN_VALUE;
}
}

protected final void initMYPASTSPA2(String s) {
if (!s.trim().isEmpty()) {
MYPASTSPA2 = Byte.parseByte(s);
} else {
MYPASTSPA2 = Byte.MIN_VALUE;
}
}

protected final void initMYPASTSPA3(String s) {
if (!s.trim().isEmpty()) {
MYPASTSPA3 = Byte.parseByte(s);
} else {
MYPASTSPA3 = Byte.MIN_VALUE;
}
}

protected final void initNUMADISW(String s) {
if (!s.trim().isEmpty()) {
NUMADISW = Byte.parseByte(s);
} else {
NUMADISW = Byte.MIN_VALUE;
}
}

protected final void initVESTVTOTAL(String s) {
if (!s.trim().isEmpty()) {
VESTVTOTAL = Integer.parseInt(s);
} else {
VESTVTOTAL = Integer.MIN_VALUE;
}
}

protected final void initVESTVTOTALB(String s) {
if (!s.trim().isEmpty()) {
VESTVTOTALB = Byte.parseByte(s);
} else {
VESTVTOTALB = Byte.MIN_VALUE;
}
}

protected final void initVESTVTOTALB_I(String s) {
if (!s.trim().isEmpty()) {
VESTVTOTALB_I = Byte.parseByte(s);
} else {
VESTVTOTALB_I = Byte.MIN_VALUE;
}
}

protected final void initVESTVTOTALB_IFLAG(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == 0) {
VESTVTOTALB_IFLAG = false;
} else {
VESTVTOTALB_IFLAG = true;
}
}
}

protected final void initVESTVTOTAL_I(String s) {
if (!s.trim().isEmpty()) {
VESTVTOTAL_I = Integer.parseInt(s);
} else {
VESTVTOTAL_I = Integer.MIN_VALUE;
}
}

protected final void initVESTVTOTAL_IFLAG(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == 0) {
VESTVTOTAL_IFLAG = false;
} else {
VESTVTOTAL_IFLAG = true;
}
}
}

protected final void initVOTYP_IFLAG(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == 0) {
VOTYP_IFLAG = false;
} else {
VOTYP_IFLAG = true;
}
}
}

protected final void initVOVLB_I(String s) {
if (!s.trim().isEmpty()) {
VOVLB_I = Byte.parseByte(s);
} else {
VOVLB_I = Byte.MIN_VALUE;
}
}

protected final void initVOVLB_IFLAG(String s) {
if (!s.trim().isEmpty()) {
byte b = Byte.parseByte(s);
if (b == 0) {
VOVLB_IFLAG = false;
} else {
VOVLB_IFLAG = true;
}
}
}

protected final void initVTYPE1_I(String s) {
if (!s.trim().isEmpty()) {
VTYPE1_I = Byte.parseByte(s);
} else {
VTYPE1_I = Byte.MIN_VALUE;
}
}

protected final void initVTYPE3(String s) {
if (!s.trim().isEmpty()) {
VTYPE3 = Byte.parseByte(s);
} else {
VTYPE3 = Byte.MIN_VALUE;
}
}

protected final void initVTYPE3_I(String s) {
if (!s.trim().isEmpty()) {
VTYPE3_I = Byte.parseByte(s);
} else {
VTYPE3_I = Byte.MIN_VALUE;
}
}

protected final void initW5XSHHWGT(String s) {
if (!s.trim().isEmpty()) {
W5XSHHWGT = Double.parseDouble(s);
} else {
W5XSHHWGT = Double.NaN;
}
}

protected final void initYEARW4(String s) {
if (!s.trim().isEmpty()) {
YEARW4 = Short.parseShort(s);
} else {
YEARW4 = Short.MIN_VALUE;
}
}

public short getCASEW5() {
return CASEW5;
}

public int getDVTOTGIR_3SF() {
return DVTOTGIR_3SF;
}

public boolean getMENDV10_I() {
return MENDV10_I;
}

public boolean getMENDV10_IFLAG() {
return MENDV10_IFLAG;
}

public int getMENDV11_I() {
return MENDV11_I;
}

public boolean getMENDV11_IFLAG() {
return MENDV11_IFLAG;
}

public boolean getMENDVB8() {
return MENDVB8;
}

public boolean getMENDY8() {
return MENDY8;
}

public byte getMINTFIX1() {
return MINTFIX1;
}

public byte getMINTFIX2() {
return MINTFIX2;
}

public byte getMINTFIX3() {
return MINTFIX3;
}

public byte getMINTPAID1() {
return MINTPAID1;
}

public byte getMINTPAID2() {
return MINTPAID2;
}

public byte getMINTPAID3() {
return MINTPAID3;
}

protected double MINTRATE1() {
return MINTRATE1;
}

protected double MINTRATE2() {
return MINTRATE2;
}

protected double MINTRATE3() {
return MINTRATE3;
}

public byte getMONTHW4() {
return MONTHW4;
}

public byte getMPASTSPA1() {
return MPASTSPA1;
}

public byte getMPASTSPA2() {
return MPASTSPA2;
}

public byte getMPASTSPA3() {
return MPASTSPA3;
}

public byte getMYPASTSPA1() {
return MYPASTSPA1;
}

public byte getMYPASTSPA2() {
return MYPASTSPA2;
}

public byte getMYPASTSPA3() {
return MYPASTSPA3;
}

public byte getNUMADISW() {
return NUMADISW;
}

public int getVESTVTOTAL() {
return VESTVTOTAL;
}

public byte getVESTVTOTALB() {
return VESTVTOTALB;
}

public byte getVESTVTOTALB_I() {
return VESTVTOTALB_I;
}

public boolean getVESTVTOTALB_IFLAG() {
return VESTVTOTALB_IFLAG;
}

public int getVESTVTOTAL_I() {
return VESTVTOTAL_I;
}

public boolean getVESTVTOTAL_IFLAG() {
return VESTVTOTAL_IFLAG;
}

public boolean getVOTYP_IFLAG() {
return VOTYP_IFLAG;
}

public byte getVOVLB_I() {
return VOVLB_I;
}

public boolean getVOVLB_IFLAG() {
return VOVLB_IFLAG;
}

public byte getVTYPE1_I() {
return VTYPE1_I;
}

public byte getVTYPE3() {
return VTYPE3;
}

public byte getVTYPE3_I() {
return VTYPE3_I;
}

protected double W5XSHHWGT() {
return W5XSHHWGT;
}

public short getYEARW4() {
return YEARW4;
}

public WIGB_Wave5_HHOLD_Record(String line) {
s = line.split("\t");
initYEARW1(s[0]);
initMONTHW1(s[1]);
initYEARW2(s[2]);
initMONTHW2(s[3]);
initYEARW3(s[4]);
initMONTHW3(s[5]);
initYEARW4(s[6]);
initMONTHW4(s[7]);
initYEAR(s[8]);
initMONTH(s[9]);
initHOUT(s[10]);
initW5XSHHWGT(s[11]);
initACCOM(s[12]);
initFLTTYP(s[13]);
initACCOTH(s[14]);
initTEN1(s[15]);
initTIED(s[16]);
initLLORD(s[17]);
initFURN(s[18]);
initDVPRIRNT(s[19]);
initHHOWN(s[20]);
initHAGEB(s[21]);
initHAGEYR(s[22]);
initHBUYYR(s[23]);
initHBUYSE(s[24]);
initHSHARE(s[25]);
initHSHAREP(s[26]);
initHPRICE(s[27]);
initHPRICEB(s[28]);
initHBFROM(s[29]);
initHRTBEV(s[30]);
initHHOSCH(s[31]);
initHVALUE(s[32]);
initHVALB(s[33]);
initHEXT1(s[34]);
initHEXT2(s[35]);
initHEXT3(s[36]);
initHEXT4(s[37]);
initMNUMBN(s[38]);
initMNUMB(s[39]);
initMW2CHK1(s[40]);
initMW2CHK2(s[41]);
initMW2CHK3(s[42]);
initMEXTN(s[43]);
initMEXTRS1(s[44]);
initMEXTRS2(s[45]);
initMEXTRS3(s[46]);
initMCHGE(s[47]);
initMCHGENUM(s[48]);
initMNUMBO(s[49]);
initMNAME1(s[50]);
initMONAME1(s[51]);
initMJNAME01(s[52]);
initMJNAME02(s[53]);
initMJNAME03(s[54]);
initMJNAME04(s[55]);
initMREAS01(s[56]);
initMREAS02(s[57]);
initMREAS03(s[58]);
initMREAS04(s[59]);
initMREAS05(s[60]);
initMREAS06(s[61]);
initMREAS07(s[62]);
initMTYPE1(s[63]);
initMENDW1(s[64]);
initMENDNUM1(s[65]);
initMALL1(s[66]);
initMALLTY1(s[67]);
initMVAL1(s[68]);
initMVALB1(s[69]);
initMNEG1(s[70]);
initMNEGB1(s[71]);
initMENDV1(s[72]);
initMENDVB1(s[73]);
initMENDY1(s[74]);
initMENDV2(s[75]);
initMENDVB2(s[76]);
initMENDY2(s[77]);
initMENDV3(s[78]);
initMENDVB3(s[79]);
initMENDY3(s[80]);
initMENDV4(s[81]);
initMENDVB4(s[82]);
initMENDY4(s[83]);
initMENDV5(s[84]);
initMENDX1(s[85]);
initMINVW1(s[86]);
initMINVW2(s[87]);
initMINVW3(s[88]);
initMPOLICY1(s[89]);
initMPOLICY2(s[90]);
initMYLFT1(s[91]);
initMYIFCH1(s[92]);
initMINTPAID1(s[93]);
initMINTFIX1(s[94]);
initMINTRATE1(s[95]);
initMPAYM1(s[96]);
initMPAYB1(s[97]);
initMPASTSPA1(s[98]);
initMYPASTSPA1(s[99]);
initMINC1(s[100]);
initMINC2(s[101]);
initMINC3(s[102]);
initMINC4(s[103]);
initMINC5(s[104]);
initMPP1(s[105]);
initMHOWPY1(s[106]);
initMARRS1(s[107]);
initMARRSV1(s[108]);
initMARR2YR1(s[109]);
initMARR2YR2(s[110]);
initMARR2YR3(s[111]);
initMARRCL01(s[112]);
initMARRCL02(s[113]);
initMARRCL12(s[114]);
initMNAME2(s[115]);
initMONAME2(s[116]);
initMJNAME18(s[117]);
initMJNAME19(s[118]);
initMJNAME20(s[119]);
initMREAS12(s[120]);
initMREAS13(s[121]);
initMREAS14(s[122]);
initMTYPE2(s[123]);
initBRIDLN1(s[124]);
initBRIDLN2(s[125]);
initBRIDLN3(s[126]);
initMENDW2(s[127]);
initMENDNUM2(s[128]);
initMALL2(s[129]);
initMALLTY2(s[130]);
initMVAL2(s[131]);
initMVALB2(s[132]);
initMNEG2(s[133]);
initMNEGB2(s[134]);
initMENDV6(s[135]);
initMENDVB6(s[136]);
initMENDY6(s[137]);
initMENDV7(s[138]);
initMENDVB7(s[139]);
initMENDY7(s[140]);
initMENDV8(s[141]);
initMENDVB8(s[142]);
initMENDY8(s[143]);
initMENDX2(s[144]);
initMYLFT2(s[145]);
initMYIFCH2(s[146]);
initMPASTSPA2(s[147]);
initMYPASTSPA2(s[148]);
initMPAYM2(s[149]);
initMPAYB2(s[150]);
initMINC6(s[151]);
initMPP2(s[152]);
initMINTPAID2(s[153]);
initMINTFIX2(s[154]);
initMINTRATE2(s[155]);
initMHOWPY2(s[156]);
initMARRS2(s[157]);
initMARRSV2(s[158]);
initMNAME3(s[159]);
initMONAME3(s[160]);
initMJNAME35(s[161]);
initMJNAME36(s[162]);
initMREAS23(s[163]);
initMREAS24(s[164]);
initMTYPE3(s[165]);
initMENDW3(s[166]);
initMENDNUM3(s[167]);
initMALL3(s[168]);
initMALLTY3(s[169]);
initMVAL3(s[170]);
initMVALB3(s[171]);
initMNEG3(s[172]);
initMNEGB3(s[173]);
initMENDV11(s[174]);
initMENDVB11(s[175]);
initMENDY11(s[176]);
initMENDX3(s[177]);
initMYLFT3(s[178]);
initMYIFCH3(s[179]);
initMPASTSPA3(s[180]);
initMYPASTSPA3(s[181]);
initMPAYM3(s[182]);
initMPAYB3(s[183]);
initMINC11(s[184]);
initMPP3(s[185]);
initMINTPAID3(s[186]);
initMINTFIX3(s[187]);
initMINTRATE3(s[188]);
initMHOWPY3(s[189]);
initMARRS3(s[190]);
initMARRSV3(s[191]);
initEQOLD(s[192]);
initEQNEW(s[193]);
initEQYES(s[194]);
initEQTYPE1(s[195]);
initEQTYPE2(s[196]);
initEQWHENY(s[197]);
initEQWHENM(s[198]);
initEQDRAW(s[199]);
initEQVAL(s[200]);
initEQVALB(s[201]);
initEQVALDR(s[202]);
initEQVALDRB(s[203]);
initEREC(s[204]);
initERECMPD(s[205]);
initERECMBY(s[206]);
initERECTAX(s[207]);
initEINT(s[208]);
initEINTFIX(s[209]);
initEINTRAT(s[210]);
initEMSPAY(s[211]);
initEMSARR(s[212]);
initFRREPFIX(s[213]);
initSHARAPP(s[214]);
initESHARAPP(s[215]);
initERVREG1(s[216]);
initERVREG2(s[217]);
initERVSUM(s[218]);
initERVSUMB(s[219]);
initERVRECPD(s[220]);
initERVRECBY(s[221]);
initERVTAX(s[222]);
initERVRENT(s[223]);
initERVPRP(s[224]);
initEPRVPAY(s[225]);
initEPRVAL(s[226]);
initEPRPER(s[227]);
initEOTHREG(s[228]);
initEOTHRVA(s[229]);
initEOTHRVB(s[230]);
initPRREPAY(s[231]);
initPRREPFIX(s[232]);
initPRREPPC(s[233]);
initGCOLLEC(s[234]);
initGCOLLV(s[235]);
initGCOLLVB(s[236]);
initGCONTVB(s[237]);
initVCARN(s[238]);
initVTYPE1(s[239]);
initVTYPE2(s[240]);
initVTYPE3(s[241]);
initVPERS(s[242]);
initVPERV(s[243]);
initVOTYP1(s[244]);
initVOTYP2(s[245]);
initVOTYP3(s[246]);
initVOTYP4(s[247]);
initVOTPN(s[248]);
initVOVAL(s[249]);
initVOVLB(s[250]);
initVCARN_I(s[251]);
initVCARN_IFLAG(s[252]);
initVESTVTOTAL(s[253]);
initVESTVTOTALB(s[254]);
initVESTVTOTALB_I(s[255]);
initVESTVTOTALB_IFLAG(s[256]);
initVESTVTOTAL_I(s[257]);
initVESTVTOTAL_IFLAG(s[258]);
initVTYPE_IFLAG(s[259]);
initVOTYP_IFLAG(s[260]);
initVOVLB_I(s[261]);
initVOVLB_IFLAG(s[262]);
initMNUMB_I(s[263]);
initMNUMB_IFLAG(s[264]);
initMALL1_I(s[265]);
initMALL1_IFLAG(s[266]);
initMALL2_I(s[267]);
initMALL2_IFLAG(s[268]);
initMALL3_I(s[269]);
initMALL3_IFLAG(s[270]);
initMALLTY1_I(s[271]);
initMALLTY1_IFLAG(s[272]);
initMALLTY2_I(s[273]);
initMALLTY2_IFLAG(s[274]);
initMVAL1_I(s[275]);
initMVAL1_IFLAG(s[276]);
initMVAL2_I(s[277]);
initMVAL2_IFLAG(s[278]);
initMVAL3_I(s[279]);
initMVAL3_IFLAG(s[280]);
initMNEG1_I(s[281]);
initMNEG1_IFLAG(s[282]);
initMNEG2_I(s[283]);
initMNEG2_IFLAG(s[284]);
initTEN1_I(s[285]);
initTEN1_IFLAG(s[286]);
initHSHARE_I(s[287]);
initHSHARE_IFLAG(s[288]);
initHSHAREP_I(s[289]);
initHSHAREP_IFLAG(s[290]);
initEQUITY_YN_I(s[291]);
initEQUITY_YN_IFLAG(s[292]);
initEQTYPE1_I(s[293]);
initEQTYPE1_IFLAG(s[294]);
initEQDRAW_I(s[295]);
initEQDRAW_IFLAG(s[296]);
initEQVALB_I(s[297]);
initEQVALB_IFLAG(s[298]);
initEQVAL_I(s[299]);
initEQVAL_IFLAG(s[300]);
initEINT_I(s[301]);
initEINT_IFLAG(s[302]);
initEINTRAT_I(s[303]);
initEINTRAT_IFLAG(s[304]);
initERVPRP_I(s[305]);
initERVPRP_IFLAG(s[306]);
initPRREPAY_I(s[307]);
initPRREPAY_IFLAG(s[308]);
initPRREPFIX_I(s[309]);
initPRREPFIX_IFLAG(s[310]);
initPRREPPC_I(s[311]);
initPRREPPC_IFLAG(s[312]);
initMTYPE1_I(s[313]);
initMTYPE1_IFLAG(s[314]);
initMTYPE2_I(s[315]);
initMTYPE2_IFLAG(s[316]);
initMTYPE3_I(s[317]);
initMTYPE3_IFLAG(s[318]);
initMENDW1_I(s[319]);
initMENDW1_IFLAG(s[320]);
initMENDW2_I(s[321]);
initMENDW2_IFLAG(s[322]);
initMENDW3_I(s[323]);
initMENDW3_IFLAG(s[324]);
initMENDNUM1_I(s[325]);
initMENDNUM1_IFLAG(s[326]);
initMENDNUM2_I(s[327]);
initMENDNUM2_IFLAG(s[328]);
initMENDNUM3_I(s[329]);
initMENDNUM3_IFLAG(s[330]);
initMENDVB1_I(s[331]);
initMENDVB1_IFLAG(s[332]);
initMENDVB2_I(s[333]);
initMENDVB2_IFLAG(s[334]);
initMENDVB3_I(s[335]);
initMENDVB3_IFLAG(s[336]);
initMENDVB4_I(s[337]);
initMENDVB4_IFLAG(s[338]);
initMENDVB5_I(s[339]);
initMENDVB5_IFLAG(s[340]);
initMENDVB9_I(s[341]);
initMENDVB9_IFLAG(s[342]);
initMENDVB6_I(s[343]);
initMENDVB6_IFLAG(s[344]);
initMENDVB7_I(s[345]);
initMENDVB7_IFLAG(s[346]);
initMENDVB8_I(s[347]);
initMENDVB8_IFLAG(s[348]);
initMENDVB11_I(s[349]);
initMENDVB11_IFLAG(s[350]);
initMENDV1_I(s[351]);
initMENDV1_IFLAG(s[352]);
initMENDV2_I(s[353]);
initMENDV2_IFLAG(s[354]);
initMENDV3_I(s[355]);
initMENDV3_IFLAG(s[356]);
initMENDV4_I(s[357]);
initMENDV4_IFLAG(s[358]);
initMENDV5_I(s[359]);
initMENDV5_IFLAG(s[360]);
initMENDV6_I(s[361]);
initMENDV6_IFLAG(s[362]);
initMENDV7_I(s[363]);
initMENDV7_IFLAG(s[364]);
initMENDV10_I(s[365]);
initMENDV10_IFLAG(s[366]);
initMENDV11_I(s[367]);
initMENDV11_IFLAG(s[368]);
initHVALUE_I(s[369]);
initHVALUE_IFLAG(s[370]);
initDVHVALUE(s[371]);
initTOTMVAL(s[372]);
initTOTMNEG(s[373]);
initTOTMORT(s[374]);
initDVEQRELVAL(s[375]);
initDVEQLTM(s[376]);
initDVHORP(s[377]);
initDVEQSRB(s[378]);
initDVEQPRUN(s[379]);
initHMORTG(s[380]);
initALLENDW(s[381]);
initHSETYPE(s[382]);
initVTYPE1_I(s[383]);
initVTYPE2_I(s[384]);
initVTYPE3_I(s[385]);
initVPERS_I(s[386]);
initVPERS_IFLAG(s[387]);
initVPERV_I(s[388]);
initVPERV_IFLAG(s[389]);
initVOTYP1_I(s[390]);
initVOTYP2_I(s[391]);
initVOTYP3_I(s[392]);
initVOTYP4_I(s[393]);
initVOTYP5_I(s[394]);
initVOVAL_I(s[395]);
initVOVAL_IFLAG(s[396]);
initGCOLLEC_I(s[397]);
initGCOLLEC_IFLAG(s[398]);
initGCOLLV_I(s[399]);
initGCOLLV_IFLAG(s[400]);
initGCONTVB_I(s[401]);
initGCONTVB_IFLAG(s[402]);
initDVHSEVAL_SUM(s[403]);
initDVBLTVAL_SUM(s[404]);
initDVBLDVAL_SUM(s[405]);
initDVLUKVAL_SUM(s[406]);
initDVLOSVAL_SUM(s[407]);
initDVOPRVAL_SUM(s[408]);
initDVHSEDEBT_SUM(s[409]);
initDVBLTDEBT_SUM(s[410]);
initDVBLDDEBT_SUM(s[411]);
initDVLUKDEBT_SUM(s[412]);
initDVLOSDEBT_SUM(s[413]);
initDVOPRDEBT_SUM(s[414]);
initOTHMORT_SUM(s[415]);
initHOUSGDST_SUM(s[416]);
initBUYLGDST_SUM(s[417]);
initHOUSGDSOST_SUM(s[418]);
initPHYSHOUSGDST_AGGR(s[419]);
initPHYSBUYLGDST_AGGR(s[420]);
initPHYSHOUSGDSOST_AGGR(s[421]);
initGCONTVLS(s[422]);
initDVGCOLLV(s[423]);
initALLGD(s[424]);
initDVTOTOTHVEHVAL(s[425]);
initDVTOTPERNPVAL(s[426]);
initDVTOTVEHVAL(s[427]);
initHPHYSW(s[428]);
initDVPROPERTY(s[429]);
initHPROPW(s[430]);
initDVCISAV_AGGR(s[431]);
initDVIISAV_AGGR(s[432]);
initDVKISAV_AGGR(s[433]);
initDVFNSVAL_AGGR(s[434]);
initDVFSHUKV_AGGR(s[435]);
initDVINSV_AGGR(s[436]);
initDVFLFENV_AGGR(s[437]);
initDVFLFSIV_AGGR(s[438]);
initDVFLFFSV_AGGR(s[439]);
initDVFLFTEV_AGGR(s[440]);
initDVFBONDV_AGGR(s[441]);
initDVFESHARES_AGGR(s[442]);
initDVFESHAV_AGGR(s[443]);
initDVFEOPTV_AGGR(s[444]);
initDVFCOLLV_AGGR(s[445]);
initDVFSHOSV_AGGR(s[446]);
initDVFGLTUKV_AGGR(s[447]);
initDVFGLTFOV_AGGR(s[448]);
initDVFINVOTV_AGGR(s[449]);
initDVFINFVAL_AGGR(s[450]);
initDVFINFLV_AGGR(s[451]);
initDVFINFV_AGGR(s[452]);
initDVISAVAL_AGGR(s[453]);
initDVFFASSETS_AGGR(s[454]);
initDVCACTV_AGGR(s[455]);
initDVCASVV_AGGR(s[456]);
initDVCAVAL_AGGR(s[457]);
initDVCACRVAL_AGGR(s[458]);
initDVCAODVAL_AGGR(s[459]);
initDVSAVAL_AGGR(s[460]);
initTOTCC_AGGR(s[461]);
initTOTSC_AGGR(s[462]);
initTOTMO_AGGR(s[463]);
initTOTNSLBNK_AGGR(s[464]);
initTOTNSLC_AGGR(s[465]);
initTOTOSLBNK_AGGR(s[466]);
initTOTOSLC_AGGR(s[467]);
initTOTSLBNK_AGGR(s[468]);
initTOTSLC_AGGR(s[469]);
initTOTBILLARR_AGGR(s[470]);
initTOTHPARR_AGGR(s[471]);
initTOTMOARR_AGGR(s[472]);
initTOTLNARR_AGGR(s[473]);
initTOTDVNWFLN_AGGR(s[474]);
initTOTDVNWILN_AGGR(s[475]);
initTOTARR_EXCMORT_AGGR(s[476]);
initHFINW_EXCENDW_AGGR(s[477]);
initHFINW_SUM(s[478]);
initDVVALDBT_AGGR(s[479]);
initDVVALDCOS_AGGR(s[480]);
initDVPAVCUV_AGGR(s[481]);
initDVPFDDV_AGGR(s[482]);
initDVPPVAL_AGGR(s[483]);
initTOTLOS_AGGR(s[484]);
initTOTNLOS_AGGR(s[485]);
initTOTFLNS_AGGR(s[486]);
initTOTHP_AGGR(s[487]);
initTOTLNS_AGGR(s[488]);
initHFINL_AGGR(s[489]);
initHFINWNT_SUM(s[490]);
initDVSPEN_AGGR(s[491]);
initDVPINPVAL_AGGR(s[492]);
initDVDBRWEALTHVAL_AGGR(s[493]);
initTOTPEN_AGGR(s[494]);
initTOTWLTH(s[495]);
initDVPFCURVAL_AGGR(s[496]);
initDVTOTGIR(s[497]);
initDVNIOTHI_AGGR(s[498]);
initHRPDVAGE(s[499]);
initHRPDVAGE9(s[500]);
initHRPSEX(s[501]);
initHRPNSSEC3(s[502]);
initHRPDVILO3A(s[503]);
initHRPDVMRDF(s[504]);
initHRPEDATTN1(s[505]);
initHRPEDATTN2(s[506]);
initHRPEDATTN3(s[507]);
initHRPDVECACT(s[508]);
initHRPIOUT1(s[509]);
initHRPPARTIOUT1(s[510]);
initOFINAL(s[511]);
initNOUNITS(s[512]);
initDVHSIZE(s[513]);
initGOR(s[514]);
initNUMADISW(s[515]);
initNUMADULT(s[516]);
initNUMCH18(s[517]);
initNUMCHILD(s[518]);
initNUMCIVPTR(s[519]);
initNUMCPART(s[520]);
initNUMDEPCH(s[521]);
initNUMELCH(s[522]);
initNUMHHLDR(s[523]);
initNUMMPART(s[524]);
initNUMNOELCH(s[525]);
initHHOLDTYPE(s[526]);
initHRPEDLEVEL(s[527]);
initHBEDRM(s[528]);
initCASEW5(s[529]);
initDVTOTNIR(s[530]);
initDVTOTGIR_3SF(s[531]);
initDVGISE_AGGR(s[532]);
initDVGIPPEN_AGGR(s[533]);
initDVGIEMP_AGGR(s[534]);
initDVBENEFITANNUAL_AGGR(s[535]);
initDVGIINV_AGGR(s[536]);
initDVGRSRENTAMTANNUAL_AGGR(s[537]);
initDVNISE_AGGR(s[538]);
initDVNIPPEN_AGGR(s[539]);
initDVNIEMP_AGGR(s[540]);
initDVNIINV_AGGR(s[541]);
initDVNETRENTAMTANNUAL_AGGR(s[542]);
initDVOINRRANNUAL_AGGR(s[543]);
initDVOINGTANNUAL_AGGR(s[544]);
initDVOINEGANNUAL_AGGR(s[545]);
initDVOINFRANNUAL_AGGR(s[546]);
initDVOINMAANNUAL_AGGR(s[547]);
initDVOINROANNUAL_AGGR(s[548]);
initDVOINOPANNUAL_AGGR(s[549]);
initDVOIGRRANNUAL_AGGR(s[550]);
initDVOIGGTANNUAL_AGGR(s[551]);
initDVOIGEGANNUAL_AGGR(s[552]);
initDVOIGFRANNUAL_AGGR(s[553]);
initDVOIGMAANNUAL_AGGR(s[554]);
initDVOIGROANNUAL_AGGR(s[555]);
initDVOIGOPANNUAL_AGGR(s[556]);
initDVGIOTHR_AGGR(s[557]);
initDVNIOTHR_AGGR(s[558]);
initDVERECNETANNUAL(s[559]);
initDVERVRECNETANNUAL(s[560]);
initDVERECGROSSANNUAL(s[561]);
initDVERVRECGROSSANNUAL(s[562]);
initCASEW4(s[563]);
initCASEW3(s[564]);
initCASEW2(s[565]);
initCASEW1(s[566]);
}
}

package com.jeecms.bbs.entity.base;

import java.io.Serializable;

public class BaseStockmessage implements Serializable{
	
	  private String NUM;// varchar(255) NOT NULL,
	  private String RIQI;// varchar(255) NOT NULL,
	  private String GPDM;// varchar(255) NOT NULL,
	  private String GPMC;// varchar(255) NOT NULL,
	  private double ZXJ;// double NOT NULL,
	  private String ZF;// varchar(255) DEFAULT NULL,
	  private String HSL;// varchar(255) DEFAULT NULL,
	  private double LB;// double DEFAULT NULL,
	  private double DDX1;// double DEFAULT NULL,
	  private double DDY1;// double DEFAULT NULL,
	  private double DDZ;// double DEFAULT NULL,
	  private double DDX3;// double DEFAULT NULL,
	  private double DDX5;// double DEFAULT NULL,
	  private double DDX10;// double DEFAULT NULL,
	  private double DDX60;// double DEFAULT NULL,
	  private double DDX5H;// double DEFAULT NULL,
	  private double DDX10H;// double DEFAULT NULL,
	  private double DDXLH;// double DEFAULT NULL,
	  private double DDXLZ;// double DEFAULT NULL,
	  private double ZF3R;// double DEFAULT NULL,
	  private double ZF5R;// double DEFAULT NULL,
	  private double ZF10R;// double DEFAULT NULL,
	  private double DDY3;// double DEFAULT NULL,
	  private double DDY5;// double DEFAULT NULL,
	  private double DDY10;// double DEFAULT NULL,
	  private double DDY60;// double DEFAULT NULL,
	  private double CJL;// double DEFAULT NULL,
	  private double BBD;// double DEFAULT NULL,
	  private double TCL1R;// double DEFAULT NULL,
	  private double TCL5R;// double DEFAULT NULL,
	  private double TCL10R;// double DEFAULT NULL,
	  private double TCL20R;// double DEFAULT NULL,
	  private double DSB;// double DEFAULT NULL,
	  private double TDC;// double DEFAULT NULL,
	  private double DDC;// double DEFAULT NULL,
	  private double ZDC;// double DEFAULT NULL,
	  private double XDC;// double DEFAULT NULL,
	  private double ZDL1R;// double DEFAULT NULL,
	  private double ZDL5R;// double DEFAULT NULL,
	  private double ZDL10R;// double DEFAULT NULL,
	  private double LTP;// double DEFAULT NULL,
	  private int Reccomendation;
	  
	  public BaseStockmessage(){
		  
	  }
	  
	  public BaseStockmessage( String NUM,// varchar(255) NOT NULL,
	   String RIQI,// varchar(255) NOT NULL,
	   String GPDM,// varchar(255) NOT NULL,
	   String GPMC,// varchar(255) NOT NULL,
	   double ZXJ,// double NOT NULL,
	   String ZF,// varchar(255) DEFAULT NULL,
	   String HSL,// varchar(255) DEFAULT NULL,
	   double LB,// double DEFAULT NULL,
	   double DDX1,// double DEFAULT NULL,
	   double DDY1,// double DEFAULT NULL,
	   double DDZ,// double DEFAULT NULL,
	   double DDX3,// double DEFAULT NULL,
	   double DDX5,// double DEFAULT NULL,
	   double DDX10,// double DEFAULT NULL,
	   double DDX60,// double DEFAULT NULL,
	   double DDX5H,// double DEFAULT NULL,
	   double DDX10H,// double DEFAULT NULL,
	   double DDXLH,// double DEFAULT NULL,
	   double DDXLZ,// double DEFAULT NULL,
	   double ZF3R,// double DEFAULT NULL,
	   double ZF5R,// double DEFAULT NULL,
	   double ZF10R,// double DEFAULT NULL,
	   double DDY3,// double DEFAULT NULL,
	   double DDY5,// double DEFAULT NULL,
	   double DDY10,// double DEFAULT NULL,
	   double DDY60,// double DEFAULT NULL,
	   double CJL,// double DEFAULT NULL,
	   double BBD,// double DEFAULT NULL,
	   double TCL1R,// double DEFAULT NULL,
	   double TCL5R,// double DEFAULT NULL,
	   double TCL10R,// double DEFAULT NULL,
	   double TCL20R,// double DEFAULT NULL,
	   double DSB,// double DEFAULT NULL,
	   double TDC,// double DEFAULT NULL,
	   double DDC,// double DEFAULT NULL,
	   double ZDC,// double DEFAULT NULL,
	   double XDC,// double DEFAULT NULL,
	   double ZDL1R,// double DEFAULT NULL,
	   double ZDL5R,// double DEFAULT NULL,
	   double ZDL10R,// double DEFAULT NULL,
	   double LTP,
	   int Recommendation){
		  
		  this.NUM=NUM;
		  this.RIQI=RIQI;// varchar(255) NOT NULL=
		   this.GPDM=GPDM;// varchar(255) NOT NULL=
		   this.GPMC=GPMC;// varchar(255) NOT NULL=
		   this.ZXJ=ZXJ;// double NOT NULL=
		   this.ZF=ZF;// varchar(255) DEFAULT NULL=
		   this.HSL=HSL;// varchar(255) DEFAULT NULL=
		   this.LB=LB;// this.DEFAULT NULL=
		   this.DDX1=DDX1;// this.DEFAULT NULL=
		   this.DDY1=DDY1;// this.DEFAULT NULL=
		   this.DDZ=DDZ;// this.DEFAULT NULL=
		   this.DDX3=DDX3;// this.DEFAULT NULL=
		   this.DDX5=DDX5;// this.DEFAULT NULL=
		   this.DDX10=DDX10;// this.DEFAULT NULL=
		   this.DDX60=DDX60;// this.DEFAULT NULL=
		   this.DDX5H=DDX5H;// this.DEFAULT NULL=
		   this.DDX10H=DDX10H;// this.DEFAULT NULL=
		   this.DDXLH=DDXLH;// this.DEFAULT NULL=
		   this.DDXLZ=DDXLZ;// this.DEFAULT NULL=
		   this.ZF3R=ZF3R;// this.DEFAULT NULL=
		   this.ZF5R=ZF5R;// this.DEFAULT NULL=
		   this.ZF10R=ZF10R;// this.DEFAULT NULL=
		   this.DDY3=DDY3;// this.DEFAULT NULL=
		   this.DDY5=DDY5;// this.DEFAULT NULL=
		   this.DDY10=DDY10;// this.DEFAULT NULL=
		   this.DDY60=DDY60;// this.DEFAULT NULL=
		   this.CJL=CJL;// this.DEFAULT NULL=
		   this.BBD=BBD;// this.DEFAULT NULL=
		   this.TCL1R=TCL1R;// this.DEFAULT NULL=
		   this.TCL5R=TCL5R;// this.DEFAULT NULL=
		   this.TCL10R=TCL10R;// this.DEFAULT NULL=
		   this.TCL20R=TCL20R;// this.DEFAULT NULL=
		   this.DSB=DSB;// this.DEFAULT NULL=
		   this.TDC=TDC;// this.DEFAULT NULL=
		   this.DDC=DDC;// this.DEFAULT NULL=
		   this.ZDC=ZDC;// this.DEFAULT NULL=
		   this.XDC=XDC;// this.DEFAULT NULL=
		   this.ZDL1R=ZDL1R;// this.DEFAULT NULL=
		   this.ZDL5R=ZDL5R;// this.DEFAULT NULL=
		   this.ZDL10R=ZDL10R;// this.DEFAULT NULL=
		   this.LTP=LTP;
		   this.Reccomendation=Reccomendation;
		  
	  }
	  
	  public String getRIQI(){
		  return this.RIQI;
	  }
	  
	  public String getNUM(){
		  return this.NUM;
	  }
	  
	  public String getGPDM(){
		  return this.GPDM;
	  }
	  
	  public String getGPMC(){
		  return this.GPMC;
	  }
	  
	  public double getZXJ(){
		  return this.ZXJ;
	  }
	  
	  public String getZF(){
		  return this.ZF;
	  }
	  
	  public String getHSL(){
		  return this.HSL;
	  }
	  
	  public double getLB(){
		  return this.LB;
	  }
	  
	  public double getDDX1(){
		  return this.DDX1;
	  }
	  
	  public double getDDY1(){
		  return this.DDY1;
	  }
	  
	  public double getDDZ(){
		  return this.DDZ;
	  }
	  
	  public double getDDX3(){
		  return this.DDX3;
	  }
	  
	  public double getDDX5(){
		  return this.DDX5;
	  }
	  
	  public double getDDX10(){
		  return this.DDX10;
	  }
	  
	  public double getDDX60(){
		  return this.DDX60;
	  }
	  
	  public double getDDX5H(){
		  return this.DDX5H;
	  }
	  
	  public double getDDX10H(){
		  return this.DDX10H;
	  }
	  
	  public double getDDXLH(){
		  return this.DDXLH;
	  }
	  
	  public double getDDXLZ(){
		  return this.DDXLZ;
	  }
	  
	  public double getZF3R(){
		  return this.ZF3R;
	  }
	  
	  public double getZF5R(){
		  return this.ZF5R;
	  }
	  
	  public double getZF10R(){
		  return this.ZF10R;
	  }
	  
	  public double getDDY3(){
		  return this.DDY3;
	  }
	  
	  public double getDDY5(){
		  return this.DDY5;
	  }
	  
	  public double getDDY10(){
		  return this.DDY10;
	  }
	  
	  public double getDDY60(){
		  return this.DDY60;
	  }
	  
	  public double getCJL(){
		  return this.CJL;
	  }
	  
	  public double getBBD(){
		  return this.BBD;
	  }
	  
	  public double getTCL1R(){
		  return this.TCL1R;
	  }
	  
	  public double getTCL5R(){
		  return this.TCL5R;
	  }
	  
	  public double getTCL10R(){
		  return this.TCL10R;
	  }
	  
	  public double getTCL20R(){
		  return this.TCL20R;
	  }
	  
	  public double getDSB(){
		  return this.DSB;
	  }
	  
	  public double getTDC(){
		  return this.TDC;
	  }
	  
	  public double getDDC(){
		  return this.DDC;
	  }
	  
	  public double getZDC(){
		  return this.ZDC;
	  }
	  
	  public double getXDC(){
		  return this.XDC;
	  }
	  
	  public double getZDL1R(){
		  return this.ZDL1R;
	  }
	  
	  public double getZDL5R(){
		  return this.ZDL5R;
	  }
	  
	  public double getZDL10R(){
		  return this.ZDL10R;
	  }
	  
	  public double getLTP(){
		  return this.LTP;
	  }
	  public int getReccomendation(){
		  return this.Reccomendation;
	  }
	  
	  
	  
	  //SET
	  
	  
	  public void setRIQI(String var){
		  this.RIQI=var;
	  }
	  
	  public void setNUM(String var){
		  this.NUM=var;
	  }
	  
	  public void setGPDM(String var){
		  this.GPDM=var;
	  }
	  
	  public void setGPMC(String var){
		  this.GPMC=var;
	  }
	  
	  public void setZXJ(double var){
		  this.ZXJ=var;
	  }
	  
	  public void setZF(String var){
		  this.ZF=var;
	  }
	  
	  public void setHSL(String var){
		  this.HSL=var;
	  }
	  
	  public void setLB(double var){
		  this.LB=var;
	  }
	  
	  public void setDDX1(double var){
		  this.DDX1=var;
	  }
	  
	  public void setDDY1(double var){
		  this.DDY1=var;
	  }
	  
	  public void setDDZ(double var){
		  this.DDZ=var;
	  }
	  
	  public void setDDX3(double var){
		  this.DDX3=var;
	  }
	  
	  public void setDDX5(double var){
		  this.DDX5=var;
	  }
	  
	  public void setDDX10(double var){
		  this.DDX10=var;
	  }
	  
	  public void setDDX60(double var){
		  this.DDX60=var;
	  }
	  
	  public void setDDX5H(double var){
		  this.DDX5H=var;
	  }
	  
	  public void setDDX10H(double var){
		  this.DDX10H=var;
	  }
	  
	  public void setDDXLH(double var){
		  this.DDXLH=var;
	  }
	  
	  public void setDDXLZ(double var){
		  this.DDXLZ=var;
	  }
	  
	  public void setZF3R(double var){
		  this.ZF3R=var;
	  }
	  
	  public void setZF5R(double var){
		  this.ZF5R=var;
	  }
	  
	  public void setZF10R(double var){
		  this.ZF10R=var;
	  }
	  
	  public void setDDY3(double var){
		  this.DDY3=var;
	  }
	  
	  public void setDDY5(double var){
		  this.DDY5=var;
	  }
	  
	  public void setDDY10(double var){
		  this.DDY10=var;
	  }
	  
	  public void setDDY60(double var){
		  this.DDY60=var;
	  }
	  
	  public void setCJL(double var){
		  this.CJL=var;
	  }
	  
	  public void setBBD(double var){
		  this.BBD=var;
	  }
	  
	  public void setTCL1R(double var){
		  this.TCL1R=var;
	  }
	  
	  public void setTCL5R(double var){
		  this.TCL5R=var;
	  }
	  
	  public void setTCL10R(double var){
		  this.TCL10R=var;
	  }
	  
	  public void setTCL20R(double var){
		  this.TCL20R=var;
	  }
	  
	  public void setDSB(double var){
		  this.DSB=var;
	  }
	  
	  public void setTDC(double var){
		  this.TDC=var;
	  }
	  
	  public void setDDC(double var){
		  this.DDC=var;
	  }
	  
	  public void setZDC(double var){
		  this.ZDC=var;
	  }
	  
	  public void setXDC(double var){
		  this.XDC=var;
	  }
	  
	  public void setZDL1R(double var){
		  this.ZDL1R=var;
	  }
	  
	  public void setZDL5R(double var){
		  this.ZDL5R=var;
	  }
	  
	  public void setZDL10R(double var){
		  this.ZDL10R=var;
	  }
	  
	  public void setLTP(double var){
		  this.LTP=var;
	  }
	  
	  public void setReccomendation(int var){
		  this.Reccomendation=var;
	  }

}

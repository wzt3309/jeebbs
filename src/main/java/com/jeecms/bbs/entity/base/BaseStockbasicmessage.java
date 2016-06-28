package com.jeecms.bbs.entity.base;

import java.lang.*;
import java.io.Serializable;

public class BaseStockbasicmessage implements Serializable{
	
	private String NUM;
	private String RIQI;
	private String GPDM;
	private String GPMC;// varchar(255) NOT NULL,
	private double MGSY;// double DEFAULT NULL,
	private double MGWFP;//double DEFAULT NULL,
	private double JTSYL;//double DEFAULT NULL,
	private double MGJZC;//double DEFAULT NULL,
	private double SJL;// double DEFAULT NULL,
	private double JZCSYL;// double DEFAULT NULL,
	private double MGZYSY;// double DEFAULT NULL,
	private double MGJYXJL;// double DEFAULT NULL,
	private double MGGJJ;// double DEFAULT NULL,
	private double GDQYB;// double DEFAULT NULL,
	private double LTGB;// double DEFAULT NULL,
	private double ZGB;//double DEFAULT NULL,
	private double LTSZ;// double DEFAULT NULL,
	private double ZSZ;//double DEFAULT NULL,

	public BaseStockbasicmessage(){
		
	}
	
	public BaseStockbasicmessage(String NUM,
			String RIQI,String GPDM,String GPMC,
			double MGSY,
			double MGWFP,
			double JTSYL,
		    double MGJZC,
	        double SJL,
	        double JZCSYL,
	        double MGZYSY,
	        double MGJYXJL,
	        double MGGJJ,
	        double GDQYB,
	        double LTGB,
	        double ZGB,
	        double LTSZ,
	        double ZSZ){
		
		this.NUM=NUM;
		this.RIQI=RIQI;
		this.GPDM=GPDM;
		this.GPMC=GPMC;
		this.MGSY=MGSY;
		this.MGWFP=MGWFP;
		this.JTSYL=JTSYL;
	    this.MGJZC=MGJZC;
        this.SJL=SJL;
        this.JZCSYL=JZCSYL;
        this.MGZYSY=MGZYSY;
        this.MGJYXJL=MGJYXJL;
        this.MGGJJ=MGGJJ;
        this.GDQYB=GDQYB;
        this.LTGB=LTGB;
        this.ZGB=ZGB;
        this.LTSZ=LTSZ;
        this.ZSZ=ZSZ;
		
	}
	
	public String getNUM(){
		return this.NUM;
	}
	
	public String getRIQI(){
		return this.RIQI;
	}
	
	public String getGPDM(){
		return this.GPDM;
	}
	
	public String getGPMC(){
		return this.GPMC;
	}
	
	public double getMGSY(){
		return this.MGSY;
	}
	
	public double getMGWFP(){
		return this.MGWFP;
	}
	
	public double getJTSYL(){
		return this.JTSYL;
	}
	
	public double getMGJZC(){
		return this.MGJZC;
	}
	
	public double getSJL(){
		return this.SJL;
	}
	
	public double getJZCSYL(){
		return this.JZCSYL;
	}
	
	public double getMGZYSY(){
		return this.MGZYSY;
	}
	
	public double getMGJYXJL(){
		return this.MGJYXJL;
	}
	
	public double getMGGJJ(){
		return this.MGGJJ;
	}
	
	public double getGDQYB(){
		return this.GDQYB;
	}
	
	public double getLTGB(){
		return this.LTGB;
	}
	
	public double getZGB(){
		return this.ZGB;
	}
	
	public double getLTSZ(){
		return this.LTSZ;
	}
	
	public double getZSZ(){
		return this.ZSZ;
	}
	
	
	
	//set
	
	
	public void setNUM(String var){
		this.NUM=var;
	}
	
	public void setRIQI(String var){
		this.RIQI=var;
	}
	
	public void setGPDM(String var){
		this.GPDM=var;
	}
	
	public void setGPMC(String var){
		this.GPMC=var;
	}
	
	public void setMGSY(double var){
		this.MGSY=var;
	}
	
	public void setMGWFP(double var){
		this.MGWFP=var;
	}
	
	public void setJTSYL(double var){
		this.JTSYL=var;
	}
	
	public void setMGJZC(double var){
		this.MGJZC=var;
	}
	
	public void setSJL(double var){
		this.SJL=var;
	}
	
	public void setJZCSYL(double var){
		this.JZCSYL=var;
	}
	
	public void setMGZYSY(double var){
		this.MGZYSY=var;
	}
	
	public void setMGJYXJL(double var){
		this.MGJYXJL=var;
	}
	
	public void setMGGJJ(double var){
		this.MGGJJ=var;
	}
	
	public void setGDQYB(double var){
		this.GDQYB=var;
	}
	
	public void setLTGB(double var){
		this.LTGB=var;
	}
	
	public void setZGB(double var){
		this.ZGB=var;
	}
	
	public void setLTSZ(double var){
		this.LTSZ=var;
	}
	
	public void setZSZ(double var){
		this.ZSZ=var;
	}
	
	
	

}

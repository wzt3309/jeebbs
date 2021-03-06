package com.jeecms.bbs.entity;


import java.lang.reflect.Field;

import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;

import com.jeecms.bbs.entity.base.BaseStockmessage;

public class Stockmessage extends BaseStockmessage{
	public Stockmessage(){
		super();
	}
	
	public Stockmessage(Integer id,String NUM,// varchar(255) NOT NULL,
			   String RIQI,// varchar(255) NOT NULL,
			   String GPDM,// varchar(2ULL,
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
			   int Reccomendation){
		
		super( id,NUM,// varchar(255) NOT NULL,
			   RIQI,// varchar(255) NOT NULL,
			   GPDM,// varchar(255) NOT NULL,
				 GPMC,// varchar(255) NOT NULL,
				 ZXJ,// double NOT NULL,
				 ZF,// varchar(255) DEFAULT NULL,
				 HSL,// varchar(255) DEFAULT NULL,
				 LB,// double DEFAULT NULL,
				 DDX1,// double DEFAULT NULL,
				 DDY1,// double DEFAULT NULL,
				 DDZ,// double DEFAULT NULL,
				 DDX3,// double DEFAULT NULL,
				 DDX5,// double DEFAULT NULL,
				 DDX10,// double DEFAULT NULL,
				 DDX60,// double DEFAULT NULL,
				 DDX5H,// double DEFAULT NULL,
				 DDX10H,// double DEFAULT NULL,
				 DDXLH,// double DEFAULT NULL,
				 DDXLZ,// double DEFAULT NULL,
				 ZF3R,// double DEFAULT NULL,
				 ZF5R,// double DEFAULT NULL,
				 ZF10R,// double DEFAULT NULL,
				 DDY3,// double DEFAULT NULL,
				 DDY5,// double DEFAULT NULL,
				 DDY10,// double DEFAULT NULL,
				 DDY60,// double DEFAULT NULL,
				 CJL,// double DEFAULT NULL,
				 BBD,// double DEFAULT NULL,
				 TCL1R,// double DEFAULT NULL,
				 TCL5R,// double DEFAULT NULL,
				 TCL10R,// double DEFAULT NULL,
				 TCL20R,// double DEFAULT NULL,
				 DSB,// double DEFAULT NULL,
				 TDC,// double DEFAULT NULL,
				 DDC,// double DEFAULT NULL,
				 ZDC,// double DEFAULT NULL,
				 XDC,// double DEFAULT NULL,
				 ZDL1R,// double DEFAULT NULL,
				 ZDL5R,// double DEFAULT NULL,
				 ZDL10R,// double DEFAULT NULL,
				 LTP,
				 Reccomendation);
	}

	@Override
	public String toString() {
		return "Stockmessage [getId()=" + getId() + ", getRIQI()=" + getRIQI()
				+ ", getNUM()=" + getNUM() + ", getGPDM()=" + getGPDM()
				+ ", getGPMC()=" + getGPMC() + ", getZXJ()=" + getZXJ()
				+ ", getZF()=" + getZF() + ", getHSL()=" + getHSL()
				+ ", getLB()=" + getLB() + ", getDDX1()=" + getDDX1()
				+ ", getDDY1()=" + getDDY1() + ", getDDZ()=" + getDDZ()
				+ ", getDDX3()=" + getDDX3() + ", getDDX5()=" + getDDX5()
				+ ", getDDX10()=" + getDDX10() + ", getDDX60()=" + getDDX60()
				+ ", getDDX5H()=" + getDDX5H() + ", getDDX10H()=" + getDDX10H()
				+ ", getDDXLH()=" + getDDXLH() + ", getDDXLZ()=" + getDDXLZ()
				+ ", getZF3R()=" + getZF3R() + ", getZF5R()=" + getZF5R()
				+ ", getZF10R()=" + getZF10R() + ", getDDY3()=" + getDDY3()
				+ ", getDDY5()=" + getDDY5() + ", getDDY10()=" + getDDY10()
				+ ", getDDY60()=" + getDDY60() + ", getCJL()=" + getCJL()
				+ ", getBBD()=" + getBBD() + ", getTCL1R()=" + getTCL1R()
				+ ", getTCL5R()=" + getTCL5R() + ", getTCL10R()=" + getTCL10R()
				+ ", getTCL20R()=" + getTCL20R() + ", getDSB()=" + getDSB()
				+ ", getTDC()=" + getTDC() + ", getDDC()=" + getDDC()
				+ ", getZDC()=" + getZDC() + ", getXDC()=" + getXDC()
				+ ", getZDL1R()=" + getZDL1R() + ", getZDL5R()=" + getZDL5R()
				+ ", getZDL10R()=" + getZDL10R() + ", getLTP()=" + getLTP()
				+ ", getReccomendation()=" + getReccomendation()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public static boolean validate(Stockmessage msg,Logger log){
		Field[] fields=Stockmessage.class.getFields();
		for(Field field:fields){
			try {
				Class clazz=field.getType();			;
				if(clazz==String.class){
					String obj=(String)field.get(msg);
					if(StringUtil.isBlank(obj))
						return false;
				}
			} catch (Exception e) {	
				log.error("validate Stockmessgae fail",e);
			}
		}
		return true;
	}
}

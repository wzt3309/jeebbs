package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseStockbasicmessage;

public class Stockbasicmessage extends BaseStockbasicmessage{
	
	public Stockbasicmessage(){
		super();
	}
	
	public Stockbasicmessage(String NUM,
			String RIQI,String GPDM,String GMPC,
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
		super(  NUM,
				RIQI,
                GPDM,
                GMPC,
				MGSY,
				MGWFP,
				JTSYL,
			    MGJZC,
		        SJL,
		        JZCSYL,
		        MGZYSY,
		        MGJYXJL,
		        MGGJJ,
		        GDQYB,
		        LTGB,
		        ZGB,
		        LTSZ,
		        ZSZ);
	}


}

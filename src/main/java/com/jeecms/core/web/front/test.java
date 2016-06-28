package com.jeecms.core.web.front;

public class test {
	
	public void main(){
		String sql="bean.mgwfp > 1 and bean.jtsyl > 1 and bean.mgjzc > 1 and bean.sjl > 1 and bean.jzcsyl > 1 and bean.mgzysy > 1 and bean.mgjyxjl > 1 and bean.mggjj > 1 and bean.gdqyb > 1 and bean.ltgb > 1 and bean.zgb > 1 and bean.ltsz > 1 and bean.zsz > 1 and 1=1";
		sql.replaceAll("mgwfp", "MGWFP");
		System.out.println(sql);
	}

}

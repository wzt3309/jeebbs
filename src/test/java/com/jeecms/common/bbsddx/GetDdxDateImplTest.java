package com.jeecms.common.bbsddx;

import java.util.List;

import org.junit.Test;

import com.jeecms.bbs.entity.Stockmessage;

public class GetDdxDateImplTest {

	@Test
	public void testGetStockmessges() {
		GetDdxDate test=new GetDdxDateImpl();
		List<Stockmessage> list=test.getStockmessages();
		int k=0;
		for(int i=0,j=list.size();i<j;i++){
			if(list.get(i).getGPMC()!=""){
				k++;
			}
		}
		System.out.println(k+","+list.size());
		
//		List<Stockmessage> list1=test.getStockmessages("2016-08-03");
//		List<Stockmessage> list2=test.getStockmessages("2016-08-02");
//		List<Stockmessage> list3=test.getStockmessages("2016-08-01");
//		System.out.println(list1.size()+list1.get(2).getRIQI()+list1.get(2).getZF()
//				+","+list2.size()+list2.get(2).getRIQI()+list2.get(2).getZF()
//				+","+list3.size()+list3.get(2).getRIQI()+list3.get(2).getZF());
	}

}

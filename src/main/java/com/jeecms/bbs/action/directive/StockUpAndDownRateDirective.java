package com.jeecms.bbs.action.directive;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.entity.StockUpDownRate;
import com.jeecms.bbs.manager.StockUpDownRateMng;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class StockUpAndDownRateDirective extends AbstractTopicPageDirective implements TemplateDirectiveModel{
	@Autowired
	private StockUpDownRateMng mng;
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		String dayStr=DirectiveUtils.getString("day", params);
		
		int dayInt=Integer.parseInt(dayStr);
		
		
		List<StockUpDownRate>stockUpDownRateList=mng.findRateList(dayInt);
		
		
		if(stockUpDownRateList.isEmpty()){
			System.out.println(stockUpDownRateList+"stockUpDownRateList is empty");
			paramWrap.put("stockUpDownRateList",null);
		}
		else{
			paramWrap.put("stockUpDownRateList", DEFAULT_WRAPPER.wrap(stockUpDownRateList));			
		}		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}	


}

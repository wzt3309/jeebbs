package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.action.directive.abs.AbstractTopicPageDirective;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.bbs.entity.reccomendstock;
import com.jeecms.bbs.manager.StockmessageMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class StockDataDirective extends AbstractTopicPageDirective implements TemplateDirectiveMode{

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		@SuppressWarnings("unchecked")
		String reccomendation= DirectiveUtils.getString("reccomendation", params);		
		Integer nowPage=DirectiveUtils.getInt("nowPage", params);
		Integer pageSize=20;
		List<Stockmessage> stocklist=null;
		String type=DirectiveUtils.getString("type", params);
		if(reccomendation!=null){
			System.out.println(reccomendation);
			if(reccomendation.trim().equals("1")){
				stocklist=bbsTopicMng.getReccomendation_detail(type);
				if(stocklist.isEmpty()){
					List<reccomendstock> list=bbsTopicMng.getReccomendation_simp(type);	
					if(list.isEmpty()){
						 list=bbsTopicMng.getReccomendation_simp2(type);	
					}
					paramWrap.put("stock_simple_list", DEFAULT_WRAPPER.wrap(list));
				}
				else{
				paramWrap.put("stock_list", DEFAULT_WRAPPER.wrap(stocklist));
				System.out.println("put list");
				}System.out.println("reccomendation=1");
			}
			else if(reccomendation.trim().equals("2")){
				String sql=DirectiveUtils.getString("sql", params);
				if(sql!=null){
					sql=" select bean from Stockmessage bean where "+sql;
					sql+=" and bean.RIQI=(select max(bean.RIQI) from Stockmessage bean) ";
					Pagination pagination =mng.getmess(sql,nowPage,pageSize);
					paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(pagination));
					if(pagination!=null){
						stocklist=(List<Stockmessage>) pagination.getList();
					}
					paramWrap.put("stock_list", DEFAULT_WRAPPER.wrap(stocklist));
				}
				else{
					paramWrap.put("stock_list", null);
				}
				
			}
			else if(reccomendation.trim().equals("3")){
				String sql=DirectiveUtils.getString("sql", params);
				if(sql!=null&&!"".equals(sql)){
					sql="select bean from Stockmessage bean where bean.GPDM="+sql;					
					Pagination pagination =mng.getmess(sql,nowPage,pageSize);
					paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(pagination));
					if(pagination!=null){
						stocklist=(List<Stockmessage>) pagination.getList();
					}
					paramWrap.put("stock_list", DEFAULT_WRAPPER.wrap(stocklist));
					if(stocklist!=null&&stocklist.size()>0){
						paramWrap.put("stock", DEFAULT_WRAPPER.wrap(stocklist.get(0)));
					}
				}
				else{
					paramWrap.put("stock_list", null);
				}
			}
			
			
		}else{
			System.out.println("reccomendation=null");
		}
		
		
		
		
		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		
	}
	@Autowired
	private StockmessageMng mng;

}

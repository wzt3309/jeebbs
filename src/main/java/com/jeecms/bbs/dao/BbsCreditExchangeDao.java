package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsCreditExchange;
import com.jeecms.common.hibernate3.Updater;

public interface BbsCreditExchangeDao {
	
	public BbsCreditExchange findById(Integer id);

	public BbsCreditExchange save(BbsCreditExchange bean);

	public BbsCreditExchange updateByUpdater(Updater<BbsCreditExchange> updater);

	public BbsCreditExchange deleteById(Integer id);
}
package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.BbsCommonMagic;

public interface BbsCommonMagicMng {

	public List<BbsCommonMagic> getList();

	public BbsCommonMagic findById(Integer id);
	
	public BbsCommonMagic findByIdentifier(String  mid);

	public BbsCommonMagic update(BbsCommonMagic bean);

	public BbsCommonMagic updateByGroup(BbsCommonMagic bean, Integer[] groupIds,Integer[] beUsedGroupIds);
}
package com.ss.vv.ss.service;

import java.util.List;

import com.ss.vv.common.IServiceOperations;
import com.ss.vv.ss.domain.Cultural;


public interface ICulturalService extends IServiceOperations<Cultural, Cultural>{
	public List<Cultural> getByDynasty(String cDynasty);
	
	public List<Cultural> getByCategory(String cCategory);
	
	public List<Cultural> getByArea(String cArea);
	
	public List<Cultural> getBySearch(String search);
}

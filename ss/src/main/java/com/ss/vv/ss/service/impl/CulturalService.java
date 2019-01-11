/** 
* 
* @author bingoWu 
* @data 2018年12月23日 00:03:48  
*/  

package com.ss.vv.ss.service.impl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.mapper.ICulturalMapper;
import com.ss.vv.ss.domain.Cultural;
import com.ss.vv.ss.service.ICulturalService;

@Service("culturalService")

public class CulturalService  extends AbstractService<Cultural, Cultural> implements ICulturalService{

	public CulturalService() {
		this.setTableName("cultural");
	}
	@Resource
	private ICulturalMapper culturalMapper;

	@Override
	protected IOperations<Cultural, Cultural> getMapper() {
		return culturalMapper;
	}
	@Override
	public void setTableName(String tableName){
		this.tableName = tableName;;
	}
	@Override
	public List<Cultural> getByDynasty(String cDynasty) {
		List<Cultural> list =culturalMapper.getByDynasty(cDynasty);
		return list;
	}
	@Override
	public List<Cultural> getByCategory(String cCategory) {
		List<Cultural> list =culturalMapper.getByCategory(cCategory);
		return list;
	}
	@Override
	public List<Cultural> getByArea(String cArea) {
		List<Cultural> list =culturalMapper.getByArea(cArea);
		return list;
	}
	@Override
	public List<Cultural> getBySearch(String search) {
		List<Cultural> list =culturalMapper.getBySearch(search);
		return list;
	}
	
}

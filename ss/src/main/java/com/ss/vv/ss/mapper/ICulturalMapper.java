/** 
* 
* @author bingoWu 
* @data 2018年12月23日 00:03:48  
*/  

package com.ss.vv.ss.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.Cultural;


public interface ICulturalMapper extends IOperations<Cultural, Cultural>{
	public List<Cultural> getByDynasty(@Param("cDynasty")String cDynasty);
	
	public List<Cultural> getByCategory(@Param("cCategory")String cCategory);
	
	public List<Cultural> getByArea(@Param("cArea")String cArea);
	
	public List<Cultural> getBySearch(@Param("search")String search);
}

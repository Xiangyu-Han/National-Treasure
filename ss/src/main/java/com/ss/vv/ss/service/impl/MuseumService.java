package com.ss.vv.ss.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.mapper.IMuseumMapper;
import com.ss.vv.ss.domain.Museum;
import com.ss.vv.ss.service.IMuseumService;

@Service("museumService")
public class MuseumService extends AbstractService<Museum, Museum> implements IMuseumService{
	public MuseumService() {
		this.setTableName("museum");
	}
	@Resource
	private IMuseumMapper museumMapper;

	@Override
	protected IOperations<Museum, Museum> getMapper() {
		return museumMapper;
	}
	@Override
	public void setTableName(String tableName){
		this.tableName = tableName;;
	}
}

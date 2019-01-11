package com.ss.vv.ss.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.mapper.IMuseumMapper;
import com.ss.vv.ss.mapper.IUserMapper;
import com.ss.vv.ss.domain.Museum;
import com.ss.vv.ss.domain.User;
import com.ss.vv.ss.service.IUserService;

@Service("userService")
public class UserService extends AbstractService<User, User> implements IUserService {

	public UserService() {
		this.setTableName("user");
	}
	@Resource
	private IUserMapper userMapper;

	@Override
	protected IOperations<User, User> getMapper() {
		return userMapper;
	}
	@Override
	public void setTableName(String tableName){
		this.tableName = tableName;;
	}
}
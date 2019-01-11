package com.ss.vv.ss.service.impl;

import org.springframework.stereotype.Service;
import com.ss.vv.ss.mapper.ILoginMapper;
import com.ss.vv.ss.service.ILoginService;
import com.ss.vv.common.AbstractService;
import com.ss.vv.common.IOperations;
import com.ss.vv.ss.domain.User;


import javax.annotation.Resource;
@Service("loginService")
public class LoginService extends AbstractService<User, User> implements ILoginService{

    public LoginService() {
		this.setTableName("user");
	}
    
	@Resource
    private ILoginMapper loginMapper;
	
	@Override
	protected IOperations<User, User> getMapper() {
		return loginMapper;
	}
    

	@Override
	public void setTableName(String tableName){
		this.tableName = tableName;;
	}

	@Override
	public User findByUidAndPassword(int id, String password) {
		User user = loginMapper.findByUidAndPassword(id, password);
		System.out.println(id+"----"+password);
		return user;
	}

	
}

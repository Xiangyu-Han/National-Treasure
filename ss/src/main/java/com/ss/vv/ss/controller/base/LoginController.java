package com.ss.vv.ss.controller.base;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;

import com.ss.vv.common.WebResponse;
import com.ss.vv.ss.service.ILoginService;
import com.ss.vv.ss.service.IUserService;
import com.ss.vv.ss.domain.User;
import com.ss.vv.ss.mapper.IUserMapper;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	protected WebResponse webResponse;

	@Resource
	protected ILoginService loginService;

	@RequestMapping(value = "/getUserByIdAndPassword",  produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getUserByIdAndPassword(String uId,String password) {
		System.out.println(uId+"   "+password);
		Object data = uId;
		Integer statusCode = 200;
		String statusMsg = "";
		if (uId == null || uId.length() == 0 || uId.length() > 20 ||password == null || password.length() == 0 || password.length() > 20) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer uIdNumNumer = uId.matches("^[0-9]*$") ? Integer.parseInt(uId) : 0;
		if (uIdNumNumer == 0 ) {
			statusMsg = "参数数字型错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		
		User userVo = this.loginService.findByUidAndPassword(uIdNumNumer,password);
		if (userVo != null && userVo.getuId() > 0) {
			data = userVo;
			statusMsg = "获取到用户信息！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
		
	}
	
}
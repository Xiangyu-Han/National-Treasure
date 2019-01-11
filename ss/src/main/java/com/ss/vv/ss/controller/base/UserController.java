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
import com.ss.vv.ss.service.IUserService;
import com.ss.vv.ss.domain.User;
import com.ss.vv.ss.mapper.IUserMapper;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	protected WebResponse webResponse;

	@Resource
	protected IUserService userService;

	@RequestMapping(value = "/addOrEditUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse addOrEditUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, String uId, @RequestParam(required = false) String password, @RequestParam(required = false) String uName, @RequestParam(required = false) String dynasty, @RequestParam(required = false) String category, @RequestParam(required = false) String area) {
		if (uId != null ) {
			return this.addUser(request, response, session,uId, password, uName,dynasty,category,area);
		} else {
			return this.editUser(request, response, session, uId, password, uName,dynasty,category,area);
		}
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse addUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, String uId,String password, String uName,String dynasty, String category, String area) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uId", uId);
		paramMap.put("password", password);
		paramMap.put("uName", uName);
		paramMap.put("dynasty", dynasty);
		paramMap.put("category", category);
		paramMap.put("area", area);
		data = paramMap;
		if (uId == null || "".equals(uId.trim()) || password == null || "".equals(password.trim()) || uName == null || "".equals(uName.trim())||dynasty == null || "".equals(dynasty.trim()) || category == null || "".equals(category.trim())||area == null || "".equals(area.trim())) {
			statusMsg = " 参数为空错误！！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		if (password.length() > 255 || uName.length() > 65535) {
			statusMsg = " 参数长度过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		User user = new User();


		boolean isAdd = true;
		return this.addOrEditUser(request, response, session, data, user,uId,password,uName,dynasty,category,area, isAdd);
	}


	@RequestMapping(value = "/editUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse editUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, String uId, @RequestParam(required = false) String password,  @RequestParam(required = false) String uName, @RequestParam(required = false) String dynasty, @RequestParam(required = false) String category, @RequestParam(required = false) String area){
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uId", uId);
		paramMap.put("password", password);
		paramMap.put("uName", uName);
		paramMap.put("dynasty", dynasty);
		paramMap.put("category", category);
		paramMap.put("area", area);
		data = paramMap;
		if (uId == null || "".equals(uId.trim())) {
			statusMsg = "未获得主键参数错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer userIdNumeri = uId.matches("^[0-9]*$") ? Integer.parseInt(uId) : 0;
		if (userIdNumeri == 0) {
			statusMsg = "主键不为数字错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		User userVo = this.userService.getById(userIdNumeri);
		User user = new User();
		BeanUtils.copyProperties(userVo, user);

		boolean isAdd = false;
		return this.addOrEditUser(request, response, session, data, user,uId,password,uName,dynasty,category,area, isAdd);
	}

/*
 * 
 */
	
	private WebResponse addOrEditUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, Object data, User user,String uId, String password, String uName,String dynasty, String category, String area, boolean isAdd) {
		String statusMsg = "";
		Integer statusCode = 200;
		if (uId != null && !("".equals(uId.trim()))) {
			if(uId.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			int id=Integer.parseInt(uId);
			user.setuId(id);
		}
		if (password != null && !("".equals(password.trim()))) {
			if(password.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setPassword(password);
		}
		if (uName != null && !("".equals(uName.trim()))) {
			if(uName.length() > 1000) {
				statusMsg = " 参数长度过长错误,other";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setuName(uName);
		}
		if (dynasty != null && !("".equals(dynasty.trim()))) {
			if(dynasty.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setDynasty(dynasty);
		}
		if (category != null && !("".equals(category.trim()))) {
			if(category.length() > 65535) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setCategory(category);
		}
		if (area != null && !("".equals(area.trim()))) {
			if(area.length() > 1000) {
				statusMsg = " 参数长度过长错误,other";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setArea(area);
		}
		if (isAdd) {
			this.userService.insert(user);
			if (user.getuId() > 0) {
				statusMsg = "成功插入！！！";
			} else {
				statusCode = 202;
				statusMsg = "insert false";
			} 
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		int num = this.userService.update(user);
		if (num > 0) {
			statusMsg = "成功修改！！！";
		} else {
			statusCode = 202;
			statusMsg = "update false";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}
	
/*private WebResponse addOrEditUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, Object data, User user, String password, String mail, String uName,String dynasty, String category, String area, boolean isAdd) {
		String statusMsg = "";
		Integer statusCode = 200;
		if (password != null && !("".equals(password.trim()))) {
			if(password.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setPassword(password);
		}
		if (password != null && !("".equals(password.trim()))) {
			if(password.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setPassword(password);
		}
		if (mail != null && !("".equals(mail.trim()))) {
			if(mail.length() > 65535) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setMail(mail);
		}
		if (uName != null && !("".equals(uName.trim()))) {
			if(uName.length() > 1000) {
				statusMsg = " 参数长度过长错误,other";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setuName(uName);
		}
		if (dynasty != null && !("".equals(dynasty.trim()))) {
			if(dynasty.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setPassword(dynasty);
		}
		if (category != null && !("".equals(category.trim()))) {
			if(category.length() > 65535) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setMail(category);
		}
		if (area != null && !("".equals(area.trim()))) {
			if(area.length() > 1000) {
				statusMsg = " 参数长度过长错误,other";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			user.setuName(area);
		}
		if (isAdd) {
			this.userService.insert(user);
			if (user.getuId() > 0) {
				statusMsg = "成功插入！！！";
			} else {
				statusCode = 202;
				statusMsg = "insert false";
			} 
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		int num = this.userService.update(user);
		if (num > 0) {
			statusMsg = "成功修改！！！";
		} else {
			statusCode = 202;
			statusMsg = "update false";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}*/


	@RequestMapping(value = "/getUserById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getUserById(String uId) {
		Object data = uId;
		Integer statusCode = 200;
		String statusMsg = "";
		if (uId == null || uId.length() == 0 || uId.length() > 11) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer testIdNumNumeri = uId.matches("^[0-9]*$") ? Integer.parseInt(uId) : 0;
		if (testIdNumNumeri == 0 ) {
			statusMsg = "参数数字型错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		User userVo = this.userService.getById(testIdNumNumeri);

		if (userVo != null && userVo.getuId() > 0) {
			data = userVo;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}


	/*@RequestMapping(value = "/getOneUserByIdAndPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getOneUserByIdAndPassword(String uId,String password) {
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
		User userVo = this.userService.getOne(uIdNumNumer,password);
		if (userVo != null && userVo.getuId() > 0) {
			data = userVo;
			statusMsg = "获取到用户信息！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
		
	}*/
	/*public WebResponse getOneUserByIdAndPassword(@RequestParam(defaultValue = "正常", required = false) String tbStatus) {
		LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();
		condition.put("tb_status='" + tbStatus + "'", "");
		User userVo = this.userService.getOne(condition);
		Object data = null;
		String statusMsg = "";
		if (userVo != null && userVo.getuId() > 0) {
			data = userVo;
			statusMsg = "根据条件获取单条数据成功！！！";
		} else {
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusMsg, data);
	}*/

	@RequestMapping(value = "/getUserList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
		@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
		@RequestParam(defaultValue = "10", required = false) Integer pageSize, 
		@RequestParam(defaultValue = "正常", required = false) String tbStatus, 
		@RequestParam(required = false) String keyword, 
		@RequestParam(defaultValue = "user_id", required = false) String order,
		@RequestParam(defaultValue = "desc", required = false) String desc ) {
		Object data = null;
		String statusMsg = "";
		int statusCode = 200;
		LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();
/*		if (tbStatus != null && tbStatus.length() > 0) {
			condition.put("tb_status='" + tbStatus + "'", "and");
		}*/
		if (keyword != null && keyword.length() > 0) {
			StringBuffer buf = new StringBuffer();
			buf.append("(");
			buf.append("password like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("user_name like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("dynasty like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("category like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("area like '%").append(keyword).append("%'");
			buf.append(")");
			condition.put(buf.toString(), "and");
		}
		String field = null;
		if (condition.size() > 0) {
			condition.put(condition.entrySet().iterator().next().getKey(), "");
		}
		int count = this.userService.getCount(condition, field);
		if (order != null && order.length() > 0 & "desc".equals(desc)) {
			order = order + " desc";
		}
		List<User> list = this.userService.getList(condition, pageNo, pageSize, order, field);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("total", count);
		int size = list.size();
		if (size > 0) {
			List<User> listFont = new ArrayList<User>();
			User vo;
			User voFont = new User(); 
			for (int i = 0; i < size; i++) {
				vo = list.get(i);
				BeanUtils.copyProperties(vo, voFont);
				listFont.add(voFont);
				voFont = new User();
			}
			map.put("list", listFont);
			data = map;
			statusMsg = "根据条件获取分页数据成功！！！";
		} else {
			map.put("list", list);
			data = map;
			statusCode = 202;
			statusMsg = "no record!!!";
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}

	@RequestMapping(value = "/getAdminUserList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAdminTestList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
		@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
		@RequestParam(defaultValue = "10", required = false) Integer pageSize, 
		@RequestParam(defaultValue = "正常", required = false) String tbStatus, 
		@RequestParam(required = false) String keyword, 
		@RequestParam(defaultValue = "test_id", required = false) String order,
		@RequestParam(defaultValue = "desc", required = false) String desc ) {
		Object data = null;
		String statusMsg = "";
		int statusCode = 200;
		LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();

		if (tbStatus != null && tbStatus.length() > 0) {
			condition.put("tb_status='" + tbStatus + "'", "and");
		}
		if (keyword != null && keyword.length() > 0) {
			StringBuffer buf = new StringBuffer();
			buf.append("(");
			buf.append("password like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("mail like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("user_name like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("dynasty like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("category like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("area like '%").append(keyword).append("%'");
			buf.append(")");
			condition.put(buf.toString(), "and");
		}
		String field = null;
		if (condition.size() > 0) {
			condition.put(condition.entrySet().iterator().next().getKey(), "");
		}
		int count = this.userService.getCount(condition, field);
		if (order != null && order.length() > 0 & "desc".equals(desc)) {
			order = order + " desc";
		}
		List<User> list = this.userService.getList(condition, pageNo, pageSize, order, field);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("total", count);
		int size = list.size();
		if (size > 0) {
			map.put("list", list);
			data = map;
			statusMsg = "根据条件获取分页数据成功！！！";
		} else {
			map.put("list", list);
			data = map;
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return JSON.toJSONString(data);
	}

	@RequestMapping(value = "/delUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse delUser(int id) {
		int num = this.userService.delBySign(id);;
		Object data = null;
		String statusMsg = "";
		if (num > 0) {
			statusMsg = "成功删除！！！";
		} else {
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusMsg, data);
	}

}
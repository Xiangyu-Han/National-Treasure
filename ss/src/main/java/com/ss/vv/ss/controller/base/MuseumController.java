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
import com.ss.vv.ss.service.IMuseumService;
import com.ss.vv.ss.domain.Museum;
import com.ss.vv.ss.mapper.IMuseumMapper;

@Controller
@RequestMapping("/museum")
public class MuseumController {
	@Autowired
	protected WebResponse webResponse;

	@Resource
	protected IMuseumService museumService;

	@RequestMapping(value = "/addOrEditMuseum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse addOrEditMuseum(HttpServletRequest request, HttpServletResponse response, HttpSession session, String mId, @RequestParam(required = false) String mName, @RequestParam(required = false) String mUrl1, @RequestParam(required = false) String mUrl2,@RequestParam(required = false) String mUrl3,@RequestParam(required = false) String mInfo1,@RequestParam(required = false) String mInfo2,@RequestParam(required = false) String mInfo3) {
		if (mId == null || mId.length() == 0) {
			return this.addMuseum(request, response, session, mName, mUrl1,mUrl2,mUrl3,mInfo1,mInfo2,mInfo3);
		} else {
			return this.editMuseum(request, response, session,mId, mName, mUrl1,mUrl2,mUrl3,mInfo1,mInfo2,mInfo3);
		}
	}

	@RequestMapping(value = "/addMuseum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse addMuseum(HttpServletRequest request, HttpServletResponse response, HttpSession session, String mName, String mUrl1,  String mUrl2, String mUrl3,String mInfo1,String mInfo2,String mInfo3) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mName", mName);
		paramMap.put("mUrl1", mUrl1);
		paramMap.put("mUrl2", mUrl2);
		paramMap.put("mUrl3", mUrl3);
		paramMap.put("mInfo1", mInfo1);
		paramMap.put("mInfo2", mInfo2);
		paramMap.put("mInfo3", mInfo3);
		data = paramMap;
		if (mName == null || "".equals(mName.trim()) || mInfo1 == null || "".equals(mInfo1.trim())|| mInfo2 == null || "".equals(mInfo2.trim())|| mInfo3 == null || "".equals(mInfo3.trim()) || mUrl1 == null || "".equals(mUrl1.trim())|| mUrl2 == null || "".equals(mUrl2.trim())|| mUrl3 == null || "".equals(mUrl3.trim())) {
			statusMsg = " 参数为空错误！！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		if (mName.length() > 255 || mInfo1.length() > 65535|| mInfo2.length() > 65535|| mInfo3.length() > 65535) {
			statusMsg = " 参数长度过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Museum museum = new Museum();


		boolean isAdd = true;
		return this.addOrEditMuseum(request, response, session, data, museum,mName,mUrl1,mUrl2,mUrl3,mInfo1,mInfo2,mInfo3,isAdd);
	}


	@RequestMapping(value = "/editMuseum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse editMuseum(HttpServletRequest request, HttpServletResponse response, HttpSession session, String mId, @RequestParam(required = false) String mName,@RequestParam(required = false) String mUrl1, @RequestParam(required = false) String mUrl2,@RequestParam(required = false) String mUrl3,@RequestParam(required = false) String mInfo1,@RequestParam(required = false) String mInfo2,@RequestParam(required = false) String mInfo3) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mName", mName);
		paramMap.put("mUrl1", mUrl1);
		paramMap.put("mUrl2", mUrl2);
		paramMap.put("mUrl3", mUrl3);
		paramMap.put("mInfo1", mInfo1);
		paramMap.put("mInfo2", mInfo2);
		paramMap.put("mInfo3", mInfo3);
		data = paramMap;
		if (mId == null || "".equals(mId.trim())) {
			statusMsg = "未获得主键参数错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer museumIdNumeri = mId.matches("^[0-9]*$") ? Integer.parseInt(mId) : 0;
		if (museumIdNumeri == 0) {
			statusMsg = "主键不为数字错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Museum museumVo = this.museumService.getById(museumIdNumeri);
		Museum museum = new Museum();
		BeanUtils.copyProperties(museumVo, museum);

		boolean isAdd = false;
		return this.addOrEditMuseum(request, response, session, data, museum,mName,mUrl1,mUrl2,mUrl3,mInfo1,mInfo2,mInfo3,isAdd);
	}

/*
 * 
 */
private WebResponse addOrEditMuseum(HttpServletRequest request, HttpServletResponse response, HttpSession session, Object data, Museum museum, String mName, String mUrl1,  String mUrl2, String mUrl3,String mInfo1,String mInfo2,String mInfo3,boolean isAdd) {
		String statusMsg = "";
		Integer statusCode = 200;
		if (mName != null && !("".equals(mName.trim()))) {
			if(mName.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			museum.setmName(mName);
		}
		if (mUrl1 != null && !("".equals(mUrl1.trim()))) {
			if(mUrl1.length() > 1000) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			museum.setmUrl1(mUrl1);
		}
		if (mUrl2 != null && !("".equals(mUrl2.trim()))) {
			if(mUrl2.length() > 1000) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			museum.setmUrl2(mUrl2);
		}
		if (mUrl3 != null && !("".equals(mUrl3.trim()))) {
			if(mUrl3.length() > 1000) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			museum.setmUrl3(mUrl3);
		}
		if (mInfo1 != null && !("".equals(mInfo1.trim()))) {
			if(mInfo1.length() > 65535) {
				statusMsg = " 参数长度过长错误,other";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			museum.setmInfo1(mInfo1);
		}
		if (mInfo2 != null && !("".equals(mInfo2.trim()))) {
			if(mInfo2.length() > 65535) {
				statusMsg = " 参数长度过长错误,other";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			museum.setmInfo2(mInfo2);
		}
		if (mInfo3 != null && !("".equals(mInfo3.trim()))) {
			if(mInfo3.length() > 65535) {
				statusMsg = " 参数长度过长错误,other";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			museum.setmInfo3(mInfo3);
		}
		if (isAdd) {
			this.museumService.insert(museum);
			if (museum.getmId() > 0) {
				statusMsg = "成功插入！！！";
			} else {
				statusCode = 202;
				statusMsg = "insert false";
			} 
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		int num = this.museumService.update(museum);
		if (num > 0) {
			statusMsg = "成功修改！！！";
		} else {
			statusCode = 202;
			statusMsg = "update false";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}


	@RequestMapping(value = "/getMuseumById", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getMuseumById(String mId) {
		Object data = mId;
		Integer statusCode = 200;
		String statusMsg = "";
		if (mId == null || mId.length() == 0 || mId.length() > 11) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer testIdNumNumeri = mId.matches("^[0-9]*$") ? Integer.parseInt(mId) : 0;
		if (testIdNumNumeri == 0 ) {
			statusMsg = "参数数字型错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Museum museumVo = this.museumService.getById(testIdNumNumeri);
		if (museumVo != null && museumVo.getmId() > 0) {
			data = museumVo;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}


	@RequestMapping(value = "/getOneMuseum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getOneMuseum(@RequestParam(defaultValue = "正常", required = false) String tbStatus) {
		LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();
		condition.put("tb_status='" + tbStatus + "'", "");
		Museum museumVo = this.museumService.getOne(condition);
		Object data = null;
		String statusMsg = "";
		if (museumVo != null && museumVo.getmId() > 0) {
			data = museumVo;
			statusMsg = "根据条件获取单条数据成功！！！";
		} else {
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusMsg, data);
	}

	@RequestMapping(value = "/getMuseumList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getMuseumList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
		@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
		@RequestParam(defaultValue = "10", required = false) Integer pageSize, 
		@RequestParam(defaultValue = "正常", required = false) String tbStatus, 
		@RequestParam(required = false) String keyword, 
		@RequestParam(defaultValue = "museum_id", required = false) String order,
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
			buf.append("museum_name like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("museum_url1 like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("museum_url2 like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("museum_url3 like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("museum_info1 like '%").append(keyword).append("%'");
			buf.append(")");
			buf.append("museum_info2 like '%").append(keyword).append("%'");
			buf.append(")");
			buf.append("museum_info3 like '%").append(keyword).append("%'");
			buf.append(")");
			condition.put(buf.toString(), "and");
		}
		String field = null;
		if (condition.size() > 0) {
			condition.put(condition.entrySet().iterator().next().getKey(), "");
		}
		int count = this.museumService.getCount(condition, field);
		if (order != null && order.length() > 0 & "desc".equals(desc)) {
			order = order + " desc";
		}
		List<Museum> list = this.museumService.getList(condition, pageNo, pageSize, order, field);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("total", count);
		int size = list.size();
		if (size > 0) {
			List<Museum> listFont = new ArrayList<Museum>();
			Museum vo;
			Museum voFont = new Museum(); 
			for (int i = 0; i < size; i++) {
				vo = list.get(i);
				BeanUtils.copyProperties(vo, voFont);
				listFont.add(voFont);
				voFont = new Museum();
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

	@RequestMapping(value = "/getAdminMuseumList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAdminMuseumList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
		@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
		@RequestParam(defaultValue = "10", required = false) Integer pageSize, 
		@RequestParam(defaultValue = "正常", required = false) String tbStatus, 
		@RequestParam(required = false) String keyword, 
		@RequestParam(defaultValue = "museum_id", required = false) String order,
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
			buf.append("test_name like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("info like '%").append(keyword).append("%'");
			buf.append(" or ");
			buf.append("other like '%").append(keyword).append("%'");
			buf.append(")");
			condition.put(buf.toString(), "and");
		}
		String field = null;
		if (condition.size() > 0) {
			condition.put(condition.entrySet().iterator().next().getKey(), "");
		}
		int count = this.museumService.getCount(condition, field);
		if (order != null && order.length() > 0 & "desc".equals(desc)) {
			order = order + " desc";
		}
		List<Museum> list = this.museumService.getList(condition, pageNo, pageSize, order, field);
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

	@RequestMapping(value = "/delMuseum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse delMuseum(int id) {
		int num = this.museumService.delBySign(id);;
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

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
import com.ss.vv.ss.service.ICulturalService;
import com.ss.vv.ss.domain.Cultural;
import com.ss.vv.ss.mapper.ICulturalMapper;
import com.ss.vv.ss.service.impl.CulturalService;

/** 
* 
* @author bingoWu 
* @data 2018年12月23日 00:03:48  
*/  


@Controller
@RequestMapping("/museum")
public class CulturalController {

	@Autowired
	protected WebResponse webResponse;

	@Resource
	protected ICulturalService CulturalService;

	@RequestMapping(value = "/addOrEditTest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse addOrEditTest(HttpServletRequest request, HttpServletResponse response, HttpSession session, String cId, @RequestParam(required = false) String cName, @RequestParam(required = false) String cPicture, @RequestParam(required = false) String cUrl,@RequestParam(required = false) String cDynasty,@RequestParam(required = false) String cCategory,@RequestParam(required = false) String cArea) {
		if (cId == null || cId.length() == 0) {
			return this.addTest(request, response, session,cId, cName, cPicture, cUrl,cDynasty,cCategory,cArea);
		} else {
			return this.editTest(request, response, session, cId,cName, cPicture, cUrl,cDynasty,cCategory,cArea);
		}
	}

	@RequestMapping(value = "/addTest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse addTest(HttpServletRequest request, HttpServletResponse response, HttpSession session, String cId,String cName, String cPicture,String cUrl,String cDynasty,String cCategory,String cArea) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cId", cId);
		paramMap.put("cName", cName);
		paramMap.put("cPicture",cPicture);
		paramMap.put("cUrl", cUrl);
		paramMap.put("cDynasty", cDynasty);
		paramMap.put("cCategory", cCategory);
		paramMap.put("cArea", cArea);
		data = paramMap;
		if (cName == null || "".equals(cName.trim()) || cPicture == null || "".equals(cPicture.trim()) || cUrl == null || "".equals(cUrl.trim())||cDynasty == null || "".equals(cDynasty.trim())||cCategory == null || "".equals(cCategory.trim())||cArea == null || "".equals(cArea.trim())) {
			statusMsg = " 参数为空错误！！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		if (cName.length() > 255 ) {
			statusMsg = " 参数长度过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Cultural cultural = new Cultural();


		boolean isAdd = true;
		return this.addOrEditTest(request, response, session, data, cultural,cId,cName,cPicture,cUrl,cDynasty,cCategory, cArea,isAdd);
	}


	@RequestMapping(value = "/editTest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse editTest(HttpServletRequest request, HttpServletResponse response, HttpSession session, String cId, @RequestParam(required = false) String cName, @RequestParam(required = false) String cPicture, @RequestParam(required = false) String cUrl,@RequestParam(required = false) String cDynasty,@RequestParam(required = false) String cCategory,@RequestParam(required = false) String cArea) {
		Object data = null;
		String statusMsg = "";
		Integer statusCode = 200;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cId", cId);
		paramMap.put("cName", cName);
		paramMap.put("cPicture",cPicture);
		paramMap.put("cUrl", cUrl);
		paramMap.put("cDynasty", cDynasty);
		paramMap.put("cCategory", cCategory);
		paramMap.put("cArea", cArea);
		data = paramMap;
		if (cId == null || "".equals(cId.trim())) {
			statusMsg = "未获得主键参数错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer testIdNumeri = cId.matches("^[0-9]*$") ? Integer.parseInt(cId) : 0;
		if (testIdNumeri == 0) {
			statusMsg = "主键不为数字错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Cultural testVo = this.CulturalService.getById(testIdNumeri);
		Cultural cultural = new Cultural();
		BeanUtils.copyProperties(testVo, cultural);

		boolean isAdd = false;
		return this.addOrEditTest(request, response, session, data, cultural,cId,cName,cPicture,cUrl,cDynasty,cCategory, cArea,isAdd);
	}

/*
 * 
 */
private WebResponse addOrEditTest(HttpServletRequest request, HttpServletResponse response, HttpSession session, Object data, Cultural cultural, String cId,String cName, String cPicture,String cUrl,String cDynasty,String cCategory,String cArea, boolean isAdd) {
		String statusMsg = "";
		Integer statusCode = 200;
		if (cId != null && !("".equals(cId.trim()))) {
			if(cId.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			cultural.setcName(cName);
		}
		if (cName != null && !("".equals(cName.trim()))) {
			if(cName.length() > 255) {
				statusMsg = " 参数长度过长错误,testName";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			cultural.setcName(cName);
		}
		if (cPicture != null && !("".equals(cPicture.trim()))) {
			if(cPicture.length() > 255) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			cultural.setcPicture(cPicture);
		}
		if (cUrl != null && !("".equals(cUrl.trim()))) {
			if(cUrl.length() > 255) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			cultural.setcUrl(cUrl);
		}
		if (cDynasty != null && !("".equals(cDynasty.trim()))) {
			if(cDynasty.length() > 255) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			cultural.setcDynasty(cDynasty);
		}
		if (cCategory != null && !("".equals(cCategory.trim()))) {
			if(cCategory.length() > 255) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			cultural.setcCategory(cCategory);
		}
		if (cArea != null && !("".equals(cArea.trim()))) {
			if(cArea.length() > 255) {
				statusMsg = " 参数长度过长错误,info";
				statusCode = 201;
				return webResponse.getWebResponse(statusCode, statusMsg, data);
			} 
			cultural.setcArea(cArea);
		}
		if (isAdd) {
			this.CulturalService.insert(cultural);
			if (cultural.getcId() > 0) {
				statusMsg = "成功插入！！！";
			} else {
				statusCode = 202;
				statusMsg = "insert false";
			} 
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		int num = this.CulturalService.update(cultural);
		if (num > 0) {
			statusMsg = "成功修改！！！";
		} else {
			statusCode = 202;
			statusMsg = "update false";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}


	@RequestMapping(value = "/getCulturalById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getCulturalById(String cId) {
		Object data = cId;
		Integer statusCode = 200;
		String statusMsg = "";
		if (cId == null || cId.length() == 0 || cId.length() > 11) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Integer testIdNumNumeri = cId.matches("^[0-9]*$") ? Integer.parseInt(cId) : 0;
		if (testIdNumNumeri == 0 ) {
			statusMsg = "参数数字型错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		Cultural testVo = this.CulturalService.getById(testIdNumNumeri);

		if (testVo != null && testVo.getcId() > 0) {
			data = testVo;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}
	
	@RequestMapping(value = "/getCulturalByDynasty", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getCulturalByDynasty(String cDynasty) {
		Object data = cDynasty;
		Integer statusCode = 200;
		String statusMsg = "";
		if (cDynasty == null || cDynasty.length() == 0 || cDynasty.length() > 255) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		
		List<Cultural> testVo = this.CulturalService.getByDynasty(cDynasty);
		for(int i =0 ; i<testVo.size();i++) {
		if (testVo != null && testVo.size() > 0) {
			data = testVo;
			statusMsg = "获取数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}
	
	@RequestMapping(value = "/getCulturalByCategory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getCulturalByCategory(String cCategory) {
		Object data = cCategory;
		Integer statusCode = 200;
		String statusMsg = "";
		if (cCategory == null || cCategory.length() == 0 || cCategory.length() > 255) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		
		List<Cultural> testVo = this.CulturalService.getByCategory(cCategory);

		if (testVo != null && testVo.size() > 0) {
			data = testVo;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}

	@RequestMapping(value = "/getCulturalByArea", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getCulturalByArea(String cArea) {
		Object data = cArea;
		Integer statusCode = 200;
		String statusMsg = "";
		if (cArea == null || cArea.length() == 0 || cArea.length() > 255) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		
		List<Cultural> testVo = this.CulturalService.getByArea(cArea);;

		if (testVo != null && testVo.size() > 0) {
			data = testVo;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}
	
	@RequestMapping(value = "/getCulturalBySearch",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getCulturalBySearch(String search) {
		Object data = search;
		Integer statusCode = 200;
		String statusMsg = "";
		if (search == null || search.length() == 0 || search.length() > 255) {
			statusMsg = "参数为空或参数过长错误！！！";
			statusCode = 201;
			return webResponse.getWebResponse(statusCode, statusMsg, data);
		}
		
		List<Cultural> testVo = this.CulturalService.getBySearch(search);

		if (testVo != null && testVo.size() > 0) {
			data = testVo;
			statusMsg = "获取单条数据成功！！！";
		} else {
			statusCode = 202;
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusCode, statusMsg, data);
	}
	

	@RequestMapping(value = "/getOneTest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getOneTest(@RequestParam(defaultValue = "正常", required = false) String tbStatus) {
		LinkedHashMap<String, String> condition = new LinkedHashMap<String, String>();
		condition.put("tb_status='" + tbStatus + "'", "");
		Cultural testVo = this.CulturalService.getOne(condition);
		Object data = null;
		String statusMsg = "";
		if (testVo != null && testVo.getcId() > 0) {
			data = testVo;
			statusMsg = "根据条件获取单条数据成功！！！";
		} else {
			statusMsg = "no record!!!";
		}
		return webResponse.getWebResponse(statusMsg, data);
	}

	@RequestMapping(value = "/getCulturalList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse getCulturalList(HttpServletRequest request, HttpServletResponse response, HttpSession session,
		@RequestParam(defaultValue = "1", required = false) Integer pageNo,  
		@RequestParam(defaultValue = "8", required = false) Integer pageSize, 
		@RequestParam(defaultValue = "cultural_id", required = false) String order,
		@RequestParam(defaultValue = "desc", required = false) String desc ) {
		Object data = null;
		String statusMsg = "";
		int statusCode = 200;

		
		if (order != null && order.length() > 0 & "desc".equals(desc)) {
			order = order + " desc";
		}
		List<Cultural> list = this.CulturalService.getList( pageNo, pageSize, order);
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		//map.put("total", count);
		int size = list.size();
		if (size > 0) {
			List<Cultural> listFont = new ArrayList<Cultural>();
			Cultural vo;
			Cultural voFont = new Cultural(); 
			for (int i = 0; i < size; i++) {
				vo = list.get(i);
				BeanUtils.copyProperties(vo, voFont);
				listFont.add(voFont);
				voFont = new Cultural();
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

	

	@RequestMapping(value = "/delCultural", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse delCultural(int id) {
		int num = this.CulturalService.delBySign(id);;
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


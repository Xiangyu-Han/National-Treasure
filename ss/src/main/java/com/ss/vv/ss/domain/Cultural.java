/** 
* 
* @author bingoWu 
* @data 2018年12月23日 00:03:48  
*/  

package com.ss.vv.ss.domain;
import java.math.BigDecimal;
import java.util.Date;
/** 
* 
* @author young 
* @data
*/  

public class Cultural implements java.io.Serializable{
	private  Integer cId; //编号
	private String cName; //文物名
	private String cPicture; //文物图片
	private String cUrl;  //文物页面地址
	private String cDynasty; // 朝代
	private String cCategory;// 类别
	private String cArea; //地区
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcPicture() {
		return cPicture;
	}
	public void setcPicture(String cPicture) {
		this.cPicture = cPicture;
	}
	public String getcUrl() {
		return cUrl;
	}
	public void setcUrl(String cUrl) {
		this.cUrl = cUrl;
	}
	public String getcDynasty() {
		return cDynasty;
	}
	public void setcDynasty(String cDynasty) {
		this.cDynasty = cDynasty;
	}
	public String getcCategory() {
		return cCategory;
	}
	public void setcCategory(String cCategory) {
		this.cCategory = cCategory;
	}
	public String getcArea() {
		return cArea;
	}
	public void setcArea(String cArea) {
		this.cArea = cArea;
	}
	@Override
	public String toString() {
		return "Cultural [cId=" + cId + ", cName=" + cName + ", cPicture=" + cPicture + ", cUrl=" + cUrl + ", cDynasty="
				+ cDynasty + ", cCategory=" + cCategory + ", cArea=" + cArea + "]";
	}
	
	
	
}

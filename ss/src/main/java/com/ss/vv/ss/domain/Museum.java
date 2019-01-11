package com.ss.vv.ss.domain;

public class Museum implements java.io.Serializable{
	private Integer mId; // ID
	private String mName; // 名称
	private String mUrl1; // 图片地址
	private String mUrl2;
	private String mUrl3;
	private String mInfo1;//图片信息
	private String mInfo2;
	private String mInfo3;
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmUrl1() {
		return mUrl1;
	}
	public void setmUrl1(String mUrl1) {
		this.mUrl1 = mUrl1;
	}
	public String getmUrl2() {
		return mUrl2;
	}
	public void setmUrl2(String mUrl2) {
		this.mUrl2 = mUrl2;
	}
	public String getmUrl3() {
		return mUrl3;
	}
	public void setmUrl3(String mUrl3) {
		this.mUrl3 = mUrl3;
	}
	public String getmInfo1() {
		return mInfo1;
	}
	public void setmInfo1(String mInfo1) {
		this.mInfo1 = mInfo1;
	}
	public String getmInfo2() {
		return mInfo2;
	}
	public void setmInfo2(String mInfo2) {
		this.mInfo2 = mInfo2;
	}
	public String getmInfo3() {
		return mInfo3;
	}
	public void setmInfo3(String mInfo3) {
		this.mInfo3 = mInfo3;
	}
	@Override
	public String toString() {
		return "Museum [mId=" + mId + ", mName=" + mName + ", mUrl1=" + mUrl1 + ", mUrl2=" + mUrl2 + ", mUrl3=" + mUrl3
				+ ", mInfo1=" + mInfo1 + ", mInfo2=" + mInfo2 + ", mInfo3=" + mInfo3 + "]";
	}
	
	
	
}

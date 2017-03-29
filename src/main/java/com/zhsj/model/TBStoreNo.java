package com.zhsj.model;

public class TBStoreNo {

	private int id;
	private String storeNo;
	private int status;//0、初始1、正在开店2、开店
	private int saleId;//销售员id
	private int qrCodeId;//绑定管理二维码id（关联）
	private long utime;
	private long ctime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public int getQrCodeId() {
		return qrCodeId;
	}
	public void setQrCodeId(int qrCodeId) {
		this.qrCodeId = qrCodeId;
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime(long utime) {
		this.utime = utime;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	
	
	
}

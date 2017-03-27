package com.zhsj.model;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：优惠活动
 * 类名称：com.zhsj.model.Discount     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 上午10:48:17
 */
public class Discount implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4001977317252598194L;
	
	private int id;
	private String name;//名称
	private String content;//描述
	private long startTime;//活动开始时间
	private long endTime;//活动结束时间
	private int valid;//1、有效0、无效
	private long utime;
	private long ctime;
	private int type;//活动类型
	private int status;//1 开始  2 结束
	private int aType;//1 排它优惠 2 商家优惠 3普通优惠    （优先级为1，3，2）
	private BigDecimal planAmount;//计划活动总金额，0表示不限制金额
	private String payMethod;//支付方式
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getaType() {
		return aType;
	}
	public void setaType(int aType) {
		this.aType = aType;
	}
	public BigDecimal getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(BigDecimal planAmount) {
		this.planAmount = planAmount;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	

}

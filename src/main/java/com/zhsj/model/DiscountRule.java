package com.zhsj.model;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：优惠活动规则
 * 类名称：com.zhsj.model.DiscountRule     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 下午1:12:23
 */
public class DiscountRule implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 3511376887619338837L;
	
	private int id;
	private int discountId;//优惠活动id
	private BigDecimal expendAmount;//满减
	private BigDecimal discount1;//第一个参数
	private BigDecimal discount2;//第二参数
	private long ctime;
	private long utime;//
	private BigDecimal planAmount;//计划活动金额，默认为0不限制钱数
	private BigDecimal actualAmount;//实际活动花费金额 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiscountId() {
		return discountId;
	}
	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}
	public BigDecimal getExpendAmount() {
		return expendAmount;
	}
	public void setExpendAmount(BigDecimal expendAmount) {
		this.expendAmount = expendAmount;
	}
	public BigDecimal getDiscount1() {
		return discount1;
	}
	public void setDiscount1(BigDecimal discount1) {
		this.discount1 = discount1;
	}
	public BigDecimal getDiscount2() {
		return discount2;
	}
	public void setDiscount2(BigDecimal discount2) {
		this.discount2 = discount2;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public long getUtime() {
		return utime;
	}
	public void setUtime(long utime) {
		this.utime = utime;
	}
	public BigDecimal getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(BigDecimal planAmount) {
		this.planAmount = planAmount;
	}
	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	
}

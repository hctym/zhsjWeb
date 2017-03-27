package com.zhsj.model;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：流水订单
 * 类名称：com.zhsj.model.Order     
 * 创建人：xulinchuang
 * 创建时间：2016年12月28日 上午11:11:43
 */
public class Order implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -1494901840783507180L;
	
	private long id;
	private String orderId;//订单编号
	private String orgIds;//父组织的id拼接（便于查询）。
	private BigDecimal planChargeAmount;//应收金额
	private BigDecimal actualChargeAmount;//实收金额
	private int status;//订单状态0、初始1、支付成功2、支付失败
	private int discountType;//折扣类型 1、优惠(随机减)
	private long discountId;//优惠类型
	private int payType;//支付类型1、微信支付宝2、民生银行
	private String payMethod;//支付方式
	private String storeNo;//商家编号
	private String parentStoreNo;//父商家编号
	private long orgId;//组织id
	private long userId;//用户id
	private long utime;
	private long ctime;
	
	//transient
    private transient User user;
	private transient Store store;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public BigDecimal getPlanChargeAmount() {
		return planChargeAmount;
	}
	public void setPlanChargeAmount(BigDecimal planChargeAmount) {
		this.planChargeAmount = planChargeAmount;
	}
	public BigDecimal getActualChargeAmount() {
		return actualChargeAmount;
	}
	public void setActualChargeAmount(BigDecimal actualChargeAmount) {
		this.actualChargeAmount = actualChargeAmount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDiscountType() {
		return discountType;
	}
	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}
	public long getDiscountId() {
		return discountId;
	}
	public void setDiscountId(long discountId) {
		this.discountId = discountId;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getParentStoreNo() {
		return parentStoreNo;
	}
	public void setParentStoreNo(String parentStoreNo) {
		this.parentStoreNo = parentStoreNo;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	
	
	

}

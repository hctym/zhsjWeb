package com.zhsj.dao;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.StoreBindOrg;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：商户和组织关系
 * 类名称：com.zhsj.dao.StoreBindOrgDao     
 * 创建人：xulinchuang
 * 创建时间：2017年1月10日 上午9:45:45
 */
public interface StoreBindOrgDao {

	int add(StoreBindOrg storeBindOrg);
    /**
     * 
     * @Title: getByStoreNo
     * @Description: 通过门店编号查找关联的组织
     * @param storeNo
     * @return
     */
	StoreBindOrg getByStoreNo(@Param("storeNo")String storeNo);
}

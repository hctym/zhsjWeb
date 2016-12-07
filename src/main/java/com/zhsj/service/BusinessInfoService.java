package com.zhsj.service;

import java.util.List;

import com.zhsj.model.BusinessInfo;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：
 * 类名称：com.zhsj.service.BusinessInfoService     
 * 创建人：xulinchuang
 * 创建时间：2016年12月7日 下午3:12:22
 */
public interface BusinessInfoService {
    /**
     * 
     * @Title: insert
     * @Description: 添加
     * @param businessInfo
     * @return
     * @throws Exception
     */
	int insert(BusinessInfo businessInfo) throws Exception;
	/**
	 * 
	 * @Title: getList
	 * @Description: 分页查询
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<BusinessInfo> getList(int page,int pageSize) throws Exception;
	/**
	 * 
	 * @Title: getBusinessInfoById
	 * @Description: 通过id查询businessInfo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BusinessInfo getBusinessInfoById(int id) throws Exception;
}

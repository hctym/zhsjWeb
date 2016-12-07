package com.zhsj.service;

import java.util.List;

import com.zhsj.model.ShopsPayset;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：
 * 类名称：com.zhsj.service.ShopsPaySetService     
 * 创建人：xulinchuang
 * 创建时间：2016年12月7日 下午3:26:31
 */
public interface ShopsPaySetService {
    /**
     * 
     * @Title: insert
     * @Description: 添加
     * @param shopsPayset
     * @return
     * @throws Exception
     */
	int insert(ShopsPayset shopsPayset) throws Exception;
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除根据id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delete(int id) throws Exception;
	/**
	 * 
	 * @Title: getListByBmUserId
	 * @Description: 通过bmuserId 查询该用户的支付方式
	 * @param bmUserId
	 * @return
	 */
	List<ShopsPayset> getListByBmUserId(int bmUserId);
	/**
	 * 
	 * @Title: getPaysetById
	 * @Description:通过id查询详情
	 * @param id
	 * @return
	 */
	ShopsPayset getPaysetById(int id);
}

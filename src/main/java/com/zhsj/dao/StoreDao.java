package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Store;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：门店信息dao
 * 类名称：com.zhsj.dao.StoreDao     
 * 创建人：xulinchuang
 * 创建时间：2016年12月30日 上午11:17:30
 */
public interface StoreDao {

	int add(Store store);
    /**
     * 
     * @Title: getListBy
     * @Description: 需要参数(暂时不加)用于查询
     * @param i 
     * @param pageSize
     * @return
     */
	List<Store> getListBy(@Param("row")int row, @Param("pageSize")int pageSize);
	/**
	 * 
	 * @Title: getListByOrgIdAndPage
	 * @Description: TODO
	 * @param row
	 * @param pageSize
	 * @param orgIds
	 * @return
	 */
	List<Store> getListByOrgIdAndPage(@Param("orgIds")String orgIds,@Param("row")int row,
			@Param("pageSize")int pageSize,@Param("status")int status);
	/**
	 * 
	 * @Title: getCount
	 * @Description: 根据条件  计算总数 (用于分页)
	 * @param orgIds
	 * @return
	 */
	int getCount(@Param("orgIds")String orgIds,@Param("status")int status);
	/**
	 * 
	 * @Title: getListByStoreNoAndPage
	 * @Description: 为门店 通过门店编号查找门店以及子门店 分页
	 * @param row
	 * @param pageSize
	 * @param storeNo
	 * @param status
	 * @return
	 */
	List<Store> getListByStoreNoAndPage(@Param("row")int row, @Param("pageSize")int pageSize,
			@Param("storeNo")String storeNo,@Param("status")int status);
	/**
	 * 
	 * @Title: getCountByStoreNo
	 * @Description: 通过门店编号查找门店以及子门店的总数 (用于分页)
	 * @param storeNo
	 * @param status
	 * @return
	 */
	int getCountByStoreNo(@Param("storeNo")String storeNo,@Param("status")int status);
	/**
	 * 
	 * @Title: getByStoreNo
	 * @Description: 通过编号查找门店
	 * @param storeNo
	 * @return
	 */
	Store getByStoreNo(@Param("storeNo")String storeNo);
	
	/**
	 * 
	 * @Title: getListBystoreNos
	 * @Description: 通过storeNo集合查找  所有的store
	 * @param objects
	 * @return
	 */
	List<Store> getListBystoreNos(Object[] objects);
	/**
	 * 
	 * @Title: deleteById
	 * @Description: TODO
	 * @param id
	 */
	void deleteById(long id);
	/**
	 * 
	 * @Title: getById
	 * @Description: TODO
	 * @param id
	 * @return
	 */
	Store getById(long id);
	/**
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @param store
	 * @return
	 */
	int update(Store store);
	
}

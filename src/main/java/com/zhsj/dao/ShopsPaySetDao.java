package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.ShopsPayset;

public interface ShopsPaySetDao {
    /**
     * 
     * @param shopsPayset
     * @return
     */
	int insert(ShopsPayset shopsPayset);
	/**
	 * 
	 * @param bmUserId
	 * @return
	 */
	List<ShopsPayset> getListByBmUserId(@Param("bmUserId")int bmUserId);
	/**
	 * 
	 * @param id
	 * @return
	 */
	ShopsPayset getPaysetById(@Param("id")int id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	int delete(@Param("id")int id);
}

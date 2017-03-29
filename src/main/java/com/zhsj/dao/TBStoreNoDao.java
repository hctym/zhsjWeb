package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.TBStoreNo;

public interface TBStoreNoDao {

	int insertAll(@Param("storeNos")List<TBStoreNo> storeNos);
	
	int insertOne(TBStoreNo tbStoreNo);
}

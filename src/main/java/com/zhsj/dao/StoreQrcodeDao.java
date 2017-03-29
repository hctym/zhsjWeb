package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.StoreQrcode;

public interface StoreQrcodeDao {

	int insertOne(StoreQrcode storeQrcode);
	
	List<StoreQrcode> getListByPage(@Param("start")int start,@Param("end")int end);
	
	int updateByStatus(@Param("status")int status,@Param("random")String random,
			@Param("id")int id);

	List<StoreQrcode> getListByNogs(@Param("nogIds")int[] nogIds);
	
	int getCount();
}

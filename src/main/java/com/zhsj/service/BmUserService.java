package com.zhsj.service;

import java.util.List;

import com.zhsj.model.BmUser;

public interface BmUserService {

	int insert(BmUser bmUser) throws Exception;
	
	List<BmUser> getList(int page,int pageSize) throws Exception;
	
	BmUser getBmUserById(int id) throws Exception;
}

package com.zhsj.dao;

import java.util.List;

import com.zhsj.model.User;

public interface UserDao {

	/**
	 * 
	 * @Title: getById
	 * @Description: 通过id查找用户
	 * @param id
	 * @return
	 */
	User getById(long id);

	List<User> getByIds(Object[] array);

    
}

package com.zhsj.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Org;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：组织
 * 类名称：com.zhsj.dao.OrgDao     
 * 创建人：xulinchuang
 * 创建时间：2016年12月29日 下午3:30:44
 */
public interface OrgDao {
    /**
     * 
     * @Title: add
     * @Description: 添加组织
     * @param org
     * @return
     */
	int add(Org org);
    /**
     * 
     * @Title: getChildrenOrgByOrgId
     * @Description: 通过组织id 获取子组织
     * @param id
     * @return
     */
	List<Org> getChildrenOrgByOrgId(@Param("id")long id);
	/**
	 * 
	 * @Title: getOrgById
	 * @Description: 通过组织id  获取信息以及子组织信息
	 * @param id
	 * @return
	 */
	Org getOrgById(@Param("id")long id);
	/**
	 * 
	 * @Title: update
	 * @Description: 更新
	 * @param org
	 * @return
	 */
	int update(Org org);
}

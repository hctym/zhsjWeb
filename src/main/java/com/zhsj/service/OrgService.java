package com.zhsj.service;

import java.util.List;

import com.zhsj.model.Org;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：
 * 类名称：com.zhsj.service.OrgService     
 * 创建人：xulinchuang
 * 创建时间：2017年1月6日 上午9:52:09
 */
public interface OrgService {

	int add(Org org) throws Exception;
    /**
     * 
     * @Title: getChildrenOrgByOrgId
     * @Description: 通过组织的id 查找子组织列表
     * @param id
     * @return
     * @throws Exception
     */
	List<Org> getChildrenOrgByOrgId(long id) throws Exception;
	/**
	 * 
	 * @Title: getOrgById
	 * @Description: TODO
	 * @param id
	 * @return
	 */
	Org getOrgById(long id) throws Exception;
	/**
	 * 
	 * @Title: update
	 * @Description: 更新组织
	 * @param org
	 * @return
	 */
	int update(Org org) throws Exception;
}

package com.zhsj.service;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：用户角色的关联service
 * 类名称：com.zhsj.service.AccountBindRoleService     
 * 创建人：xulinchuang
 * 创建时间：2017年1月19日 上午10:52:32
 */
public interface AccountBindRoleService {
    /**
     * 
     * @Title: addOrUpdate
     * @Description: 更新修改用户的角色
     * @param accountId
     * @param roleIds
     * @return
     * @throws Exception
     */
	int addOrUpdate(int accountId,int roleIds[]) throws Exception;
}

package com.zhsj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhsj.dao.OrgDao;
import com.zhsj.model.Org;
import com.zhsj.service.OrgService;

@Service
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgDao orgDao;
	/**
	 * 
	 * @see com.zhsj.service.OrgService#add(com.zhsj.model.Org)
	 */
	@Override
	public int add(Org org) throws Exception{
		Org porg = orgDao.getOrgById(org.getParentId());
		org.setOrgIds(porg.getOrgIds()+","+org.getParentId());
		org.setValid(1);
		org.setStatus(1);
		org.setUtime(System.currentTimeMillis()/1000);
		org.setCtime(System.currentTimeMillis()/1000);
		return orgDao.add(org);
	}
	/**
	 * 
	 * @see com.zhsj.service.OrgService#getChildrenOrgByOrgId(long)
	 */
	@Override
	public List<Org> getChildrenOrgByOrgId(long id) throws Exception {
		return orgDao.getChildrenOrgByOrgId(id);
	}
	/**
	 * 
	 * @see com.zhsj.service.OrgService#getOrgById(long)
	 */
	@Override
	public Org getOrgById(long id) throws Exception {
		
		return orgDao.getOrgById(id);
	}
	/**
	 * 
	 * @see com.zhsj.service.OrgService#update(com.zhsj.model.Org)
	 */
	@Override
	public int update(Org org) throws Exception{
        org.setUtime(System.currentTimeMillis()/1000);
		return orgDao.update(org);
	}
	
}

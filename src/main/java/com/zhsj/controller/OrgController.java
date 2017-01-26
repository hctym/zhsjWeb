package com.zhsj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhsj.model.Org;
import com.zhsj.service.OrgService;
import com.zhsj.util.CommonResult;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：组织
 * 类名称：com.zhsj.controller.OrgController     
 * 创建人：xulinchuang
 * 创建时间：2017年1月5日 下午5:07:57
 */
@RestController
@RequestMapping("org")
public class OrgController {
	
    @Autowired
	private OrgService orgService;
	/**
	 * 
	 * @Title: add
	 * @Description: 添加组织
	 * @param org
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(Org org){
		try {
			int code = orgService.add(org);
			return CommonResult.success("success", code);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getChildrenOrgByOrgId
	 * @Description: 通过组织id 获取字组织list
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getChildrenByOrgId")
	public Object getChildrenOrgByOrgId(long id){
		try {
			List<Org> orgList = orgService.getChildrenOrgByOrgId(id);
			return CommonResult.success("success", orgList);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: getOrgById
	 * @Description: 通过组织id 获取组织信息以及子组织信息
	 * @return
	 */
	@RequestMapping(value="getOrgById")
	public Object getOrgById(HttpServletRequest request,int id){
		try {
			Org org = null;
		    org = orgService.getOrgById(id);
			return CommonResult.success("success",org);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
	/**
	 * 
	 * @Title: update
	 * @Description: 更新
	 * @param org
	 * @return
	 */
	@RequestMapping(value="update")
	public Object update(Org org){
		try {
		    orgService.update(org);
			return CommonResult.success("success");
		} catch (Exception e) {
			e.printStackTrace();
			return CommonResult.defaultError("fail");
		}
	}
}

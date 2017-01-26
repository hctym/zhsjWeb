package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.CityCode;
/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：城市编号
 * 类名称：com.zhsj.dao.CityCodeDao     
 * 创建人：xulinchuang
 * 创建时间：2017年1月12日 下午2:57:58
 */
public interface CityCodeDao {
    /**
     * 
     * @Title: getListByParentCode
     * @Description: 通过父编号查找
     * @param pCode
     * @return
     */
	List<CityCode> getListByParentCode(@Param("pCode")String pCode);
	
	/**
	 * 
	 * @Title: getBycode
	 * @Description: 通过code  查询cityCode
	 * @param code
	 * @return
	 */
	CityCode getBycode(@Param("code")String code);
}

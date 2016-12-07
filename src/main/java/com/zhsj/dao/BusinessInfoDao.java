package com.zhsj.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.zhsj.model.BusinessInfo;

public interface BusinessInfoDao {

	
	BusinessInfo getBusinessInfoById(@Param("id")int id);
	
	int insert(BusinessInfo bInfo);
	
	int update(BusinessInfo bInfo);
	
	int delete(@Param("id")int id);
	
	List<BusinessInfo> getBusinessInfoList(Map<String, Object> map);
	
	
}

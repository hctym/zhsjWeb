package com.zhsj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhsj.model.Module;

public interface ModuleDao {
      /**
       * 
       * @Title: insert
       * @Description: 添加模块
       * @param module
       * @return
       */
	  int add(Module module);
	  
	  /**
	   * 
	   * @Title: getModulesByParentMonduleId
	   * @Description: 通过父模块id查询所有的模块(用于管理员分配模块给角色展示)
	   * @param id
	   * @return
	   */
	  List<Module> getModulesByParentMonduleId(@Param("parntId")int parentId);
	  /**
	   * 
	   * @Title: getModules
	   * @Description: 查询所有模块
	   * @return
	   */
	  List<Module> getModules();
	  
	  /**
	   * 
	   * @Title: getModulesByParentModuleIdAndRoleId
	   * @Description: 通过父模块id和角色id查询左边的子模块
	   * @param parentModuleId
	   * @param roleId
	   * @return
	   */
	  List<Module> getModulesByParentModuleIdAndRoleId(@Param("parentModuleId")int parentModuleId,
			  @Param("roleId")int roleId);
	  
	  
}

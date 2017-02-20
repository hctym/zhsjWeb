package com.zhsj.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhsj.util.CommonResult;
import com.zhsj.util.ConfigUtil;

@RestController
@RequestMapping("image")
public class ImageController {
    
	private Logger logger = LoggerFactory.getLogger(ImageController.class);
	@RequestMapping(value="upload",method={RequestMethod.GET,RequestMethod.POST})
	public synchronized Object upload(@RequestParam(value="logoImage")MultipartFile file,HttpServletRequest request){
		 // 判断文件是否为空  
		logger.info(request.getSession().getServletContext().getRealPath("/"));
        if (!file.isEmpty()) {  
            try {  
                // 文件保存路径  
                String filePath = ConfigUtil.filepath+ConfigUtil.imgpath;
                String filename =  System.currentTimeMillis()+".jpg";  
                File nfile=new File(filePath+filename);
        	    if(!nfile.getParentFile().exists()){
        		    nfile.getParentFile().mkdirs();
        	    }
        	    System.err.println(filePath);
        	    System.err.println(filename);
                // 转存文件  
                file.transferTo(new File(filePath+filename));  
                return CommonResult.success("",ConfigUtil.imgpath+filename);
            } catch (Exception e) {  
                e.printStackTrace();  
                return CommonResult.defaultError("error");
            }  
        }  
        return null;
		
	}
}

package com.zhsj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {

	public static void ZipMultiFile(String filepath ,String zippath) {
		try {
	        File file = new File(filepath);// 要被压缩的文件夹
	        File zipFile = new File(zippath+".zip");
	        InputStream input = null;
	        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
	        if(file.isDirectory()){
	            File[] files = file.listFiles();
	            for(int i = 0; i < files.length; ++i){
	                input = new FileInputStream(files[i]);
	                zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
	                int temp = 0;
	                while((temp = input.read()) != -1){
	                    zipOut.write(temp);
	                }
	                input.close();
	            }
	        }
	        zipOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void delete(String filepath){
    	File file = new File(filepath);
    	if(file.isDirectory()){
    		File[] files = file.listFiles();
    		for(int i= 0;i<files.length;i++){
    				files[i].delete();
    		}
    		file.delete();
    	}else{
    		file.delete();
    	}
    }
	
	public static void main(String[] args) {
		ZipMultiFile("D:/gitWorkSpace/zhsjWeb/src/main/webapp/zip/1-1490682779669",
				"D:/gitWorkSpace/zhsjWeb/src/main/webapp/zip/1-1490682779669");
	}
}

package com.zhsj.util;

import java.util.Random;
import java.util.zip.CRC32;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：生成编号工具类
 * 类名称：com.zhsj.util.GenerateNo     
 * 创建人：xulinchuang
 * 创建时间：2016年12月29日 下午4:08:50
 */
public class NoUtil {

	public static String getStoreNO(){
//        long time = DateUtil.unixTime();
		long time = System.currentTimeMillis();
        Random rnd = new Random();
        int num = rnd.nextInt(1000);
        String rd = String.format("%08x%03x",time ,num);
        return rd;
    }

    public static String getOrderNO(String StoreNO){
        CRC32 crc32 = new CRC32();
        crc32.update(StoreNO.getBytes());
        System.err.println(crc32.getValue());
        long time = System.currentTimeMillis();
        Random rnd = new Random();
        int num = rnd.nextInt(1000);
        String rd = String.format("%08x%03x%09x",time ,num,crc32.getValue());
        return rd;
    }
    
    public static void main(String[] args) {
//		System.err.println(getStoreNO());
//		System.err.println(getOrderNO(getStoreNO()));
		
    	int num = 11111;
    	String str = String.format("%06d", num);
    	System.err.println(str);
	}
}

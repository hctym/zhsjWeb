package com.zhsj.util;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;


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
    
    public static synchronized String getStoreNO(long seq){
    	seq = seq^674653;
        String rd = String.format("%3s%07d","111",seq);
        return rd;
    }

    public static synchronized String getOrderNO(String storeNo){
    	//8位商家编号+YYMMDD+84600+4位
    	if(StringUtils.isNotEmpty(storeNo)){
    		storeNo = storeNo.length() <= 8 ? storeNo:storeNo.substring(storeNo.length()-8);
    	}
    	String date = DateUtil.getDateFormat("yyMMdd");
    	long time = DateUtil.unixTime() - DateUtil.getTodayStartTime();
    	int min = 1,max = Integer.MAX_VALUE;
    	Random random = new Random();
    	int num = random.nextInt(max)%(max-min+1) + min;
    	String no = (String.valueOf(num)+"000").substring(0, 4);
    	
        String rd = String.format("%s%6s%05d%4s",storeNo ,date,time,no);
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

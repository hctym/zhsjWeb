package test;

import java.util.Random;

import org.aspectj.weaver.ast.Var;

import com.zhsj.util.DateUtil;

public class Demo {

	public static void main(String[] args) {
//		System.err.println(new Long(DateUtil.formatStringUnixTime("2017-03-04 16:21", "yyyy-MM-dd HH:mm")));
//		String.format(format, args);
//		System.setProperties(props);
		for(int i =0 ;i<10;i++){
		System.err.println(getRandomStringByLength(10));
		}
	}
	
	public static String getRandomStringByLength(int length){
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}

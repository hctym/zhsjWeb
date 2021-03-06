package com.zhsj.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * 项目名称：zhsjWeb   
 *
 * 类描述：AES加密解密
 * http://www.cnblogs.com/freeliver54/archive/2011/10/08/2202136.html
 * 类名称：com.zhsj.util.AES     
 * 创建人：xulinchuang
 * 创建时间：2016年12月8日 下午2:57:54
 */
public class AES {
	private static String KEY = "wwt-zhsj";
    /**
     * 
     * @Title: encrypt
     * @Description: 加密
     * @param content
     * @param password
     * @return
     */
	public static String encrypt(String content) {  
        try {             
                KeyGenerator kgen = KeyGenerator.getInstance("AES");  
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );   
                secureRandom.setSeed(KEY.getBytes());  
                kgen.init(128, secureRandom);  
                SecretKey secretKey = kgen.generateKey();  
                byte[] enCodeFormat = secretKey.getEncoded();  
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
                Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
                byte[] byteContent = content.getBytes("utf-8");  
                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
                byte[] result = cipher.doFinal(byteContent);  
                return parseByte2HexStr(result); // 加密   
        } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
                e.printStackTrace();  
        } catch (InvalidKeyException e) {  
                e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
                e.printStackTrace();  
        } catch (BadPaddingException e) {  
                e.printStackTrace();  
        }  
        return null;  
    }  
	
	/**
	 * 
	 * @Title: decrypt
	 * @Description: 解密
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decrypt(String content) {  
        try {  
                 KeyGenerator kgen = KeyGenerator.getInstance("AES");  
                 SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );   
                 secureRandom.setSeed(KEY.getBytes());  
                 kgen.init(128, secureRandom);  
                 SecretKey secretKey = kgen.generateKey();  
                 byte[] enCodeFormat = secretKey.getEncoded();  
                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
                 cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
                 byte[] result = cipher.doFinal(parseHexStr2Byte(content));  
                 return new String(result); // 加密   
        } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
                e.printStackTrace();  
        } catch (InvalidKeyException e) {  
                e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
                e.printStackTrace();  
        } catch (BadPaddingException e) {  
                e.printStackTrace();  
        }  
        return null;  
    }  
	
	
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}  
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
        if (hexStr.length() < 1)  
                return null;  
        byte[] result = new byte[hexStr.length()/2];  
        for (int i = 0;i< hexStr.length()/2; i++) {  
                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                result[i] = (byte) (high * 16 + low);  
        }  
        return result;  
    }  
	public static void main(String[] args) {
		String name = "4A8031F12AFCE28A767606CD2BABEB733FA7EECE3508A59C093914B52DF4439CD785861F10484F7888375B746301C7C1";
		String enString = decrypt(name);
		System.err.println(enString);
		System.err.println(decrypt(enString));
	}
}

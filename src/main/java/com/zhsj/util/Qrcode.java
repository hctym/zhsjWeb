package com.zhsj.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Qrcode {

	public static String encode(String filepath,String url) {
		File file = new File(filepath);
		if(!file.exists()){
			try {
				file.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String format = "png";
		String filename = System.currentTimeMillis()+".png";
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 2);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 1152, 1158,hints);
			Path path = FileSystems.getDefault().getPath(filepath, filename);
			MatrixToImageWriter.writeToPath(bitMatrix, format, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filepath+File.separator+filename;
	}
	
	public static void main(String[] args) {
//		encode();
//		encode("d://test","https://www.baidu.com");
		System.err.println(encode("d://test","https://www.baidu.com"));
	}
}

package com.zhsj.util;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
	public class ImageUtils {
	    /**
	     * 
	     * @Title: 构造图片
	     * @Description: 生成水印并返回java.awt.image.BufferedImage
	     * @param file
	     *            源文件(图片)
	     * @param waterFile
	     *            水印文件(图片)
	     * @param x
	     *            距离右下角的X偏移量
	     * @param y
	     *            距离右下角的Y偏移量
	     * @param alpha
	     *            透明度, 选择值从0.0~1.0: 完全透明~完全不透明
	     * @return BufferedImage
	     * @throws IOException
	     */
	    public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha,
	    		String storeNo,int fx,int fy) throws IOException {
	        // 获取底图
	        BufferedImage buffImg = ImageIO.read(file);
	        // 获取层图
	        BufferedImage waterImg = ImageIO.read(waterFile);
	        // 创建Graphics2D对象，用在底图对象上绘图
	        Graphics2D g2d = buffImg.createGraphics();
	        int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
	        int waterImgHeight = waterImg.getHeight();// 获取层图的高度
	        // 在图形和图像中实现混合和透明效果
	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
	        // 绘制
	        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);

	        int fontSize = 120;
	        Font f = new Font("宋体", Font.PLAIN, fontSize);
	        Color color = new Color(0, 0, 0);
	        g2d.setFont(f);
	        g2d.setColor(color);
	        g2d.drawString(storeNo, fx, fy);  
	        g2d.dispose();// 释放图形上下文使用的系统资源
	        return buffImg;
	    }
	    
	    public static BufferedImage logoMarkQrCode(File twodimensioncodeImg,File logoImg) throws IOException{
	    	   BufferedImage twodimensioncode = null;  
	        try{  
	            if(!twodimensioncodeImg.isFile() || !logoImg.isFile()){  
	                System.out.println("输入非图片");  
	                return null;  
	            }  
	            //读取二维码图片  
	            twodimensioncode = ImageIO.read(twodimensioncodeImg);  
	            //获取画笔  
	            Graphics2D g = twodimensioncode.createGraphics();  
	            //读取logo图片  
	            BufferedImage logo = ImageIO.read(logoImg);  
	            //设置二维码大小，太大，会覆盖二维码，此处20%  
	            int logoWidth = logo.getWidth(null) > twodimensioncode.getWidth()*2 /10 ? (twodimensioncode.getWidth()*2 /10) : logo.getWidth(null);  
	            int logoHeight = logo.getHeight(null) > twodimensioncode.getHeight()*2 /10 ? (twodimensioncode.getHeight()*2 /10) : logo.getHeight(null);  
	            //设置logo图片放置位置  
	            //中心  
	            int x = (twodimensioncode.getWidth() - logoWidth) / 2;  
	            int y = (twodimensioncode.getHeight() - logoHeight) / 2;  
	            //右下角，15为调整值  
//	          int x = twodimensioncode.getWidth()  - logoWidth-15;  
//	          int y = twodimensioncode.getHeight() - logoHeight-15;  
	            //开始合并绘制图片  
	            g.drawImage(logo, x, y, logoWidth, logoHeight, null);  
	            g.drawRoundRect(x, y, logoWidth, logoHeight, 15 ,15);  
	            //logo边框大小  
	            g.setStroke(new BasicStroke(2));  
	            //logo边框颜色  
	            g.setColor(Color.WHITE);  
	            g.drawRect(x, y, logoWidth, logoHeight);  
	            g.dispose();  
	            logo.flush();  
	            twodimensioncode.flush();  
	        }catch(Exception e){  
	            System.out.println("二维码绘制logo失败");  
	        }  
	        return twodimensioncode; 
	    }
	    
		public  static void logoHandler(String sourceFilePath,String waterFilePath,String saveFilePath) {
	    	try {
				File sourceFile = new File(sourceFilePath);
				File waterFile = new File(waterFilePath);
//				BufferedImage  sourceImg = ImageIO.read(sourceFile);
//				BufferedImage waterImg = ImageIO.read(waterFile);
//				int x = (sourceImg.getWidth() - waterImg.getWidth()) / 2;
//				int y = (sourceImg.getHeight() - waterImg.getHeight()) / 2;
				BufferedImage buffImg =  logoMarkQrCode(sourceFile,waterFile);
				ImageUtils newImageUtils = new ImageUtils();
				newImageUtils.generateWaterFile(buffImg, saveFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    /**
	     * 输出水印图片
	     * 
	     * @param buffImg
	     *            图像加水印之后的BufferedImage对象
	     * @param savePath
	     *            图像加水印之后的保存路径
	     */
	    private void generateWaterFile(BufferedImage buffImg, String savePath) {
	        int temp = savePath.lastIndexOf(".") + 1;
	        try {
	            ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }

	    /**
	     * 
	     * @param args
	     * @throws IOException
	     *             IO异常直接抛出了
	     * @author bls
	     */
	    public static void main(String[] args) throws IOException {
	        String sourceFilePath = "D:/test/1231231.png";
	        String waterFilePath = "D:/test/44.png";
	        String saveFilePath = "D:/test/zzzzz.png";
	        logoHandler(sourceFilePath,waterFilePath,saveFilePath);
//	        handler(sourceFilePath,waterFilePath,saveFilePath,"x");
//	        long time = new Date().getTime();
//	        ImageUtils newImageUtils = new ImageUtils();
//	        File sourceFile = new File(sourceFilePath);
//	        File waterFile = new File(waterFilePath);
//	        BufferedImage  sourceImg = ImageIO.read(sourceFile);
//	        BufferedImage  waterImg = ImageIO.read(waterFile);
//	        int height = sourceImg.getHeight()/2 - waterImg.getHeight()/2;
//	        int width = sourceImg.getWidth()/2 - waterImg.getWidth()/2;
//	        // 构建叠加层
//	        BufferedImage buffImg = ImageUtils.watermark(sourceFile, waterFile, width, 
//	        		height, 1.0f, "No.11123456789", waterImg.getHeight(),100);
//	        // 输出水印图片
//	        newImageUtils.generateWaterFile(buffImg, saveFilePath);
//	        System.out.println(new Date().getTime()-time);
//	    	System.err.println(11/2);
	    }
	    
	    
	    public static void handler(String sourceFilePath,String waterFilePath,String saveFilePath,String storeNo) {
	    	try {
	    		storeNo = "No."+storeNo;
	    		
				ImageUtils imgUtils = new ImageUtils();
				File sourceFile = new File(sourceFilePath);
				File waterFile = new File(waterFilePath);
				int qrwidth = 664,qrheight = 1062,fontWidth = 760,fontHeight=2350;
				BufferedImage buffImg = ImageUtils.watermark(sourceFile, waterFile, 
						qrwidth, qrheight, 1.0f, storeNo,fontWidth,fontHeight);
				imgUtils.generateWaterFile(buffImg, saveFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}

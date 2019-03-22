package com.zyh.spring.controller.sys.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyh.spring.utils.CookieUtils;
/**
 * 图形验证码绘制工具
 * @author HU
 * @date 2017-04-12
 * 
 */
public class ImgServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int width = 92;
		int height = 36; 
		// 验证码的制作过程：
		// 1、先在内存中生成一个用于存储图片的缓冲区对象
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

		// 2、获取给图片缓冲区中写数据的对象
		Graphics g = image.getGraphics(); // 画笔

		// 3、创建出来的图片缓冲区背景颜色默认黑色的，修改,设置画笔的颜色
		g.setColor(new Color(0xDCDCDC));
		// 把所有的图片缓冲区全部填充白色
		g.fillRect(0, 0, width, height);
		// 设置图片的边框
		g.setColor(Color.white);
		g.drawRect(0, 0, width, height);

		// 准备给图片中书写的数据
		String data = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz123456789";

		// 定义获取随机数的对象
		Random r = new Random();
		// 定义变量，用来提供图片中字开始写的位置

//		// 定义容器保存给图片上生成的字符数据
//		StringBuilder sb = new StringBuilder();
		
//		int x = 8;
//		// 使用循环写数据
//		for (int i = 0; i < 4; i++) {
//
//			// 把画笔强成2D画笔
//			Graphics2D g2 = (Graphics2D) g;
//
//			// 设置旋转
//			int dushu = r.nextInt(90) - 45;
//
//			// 变成弧度
//			double theta = dushu * Math.PI / 180;
//			// 设置旋转
//			g2.rotate(theta, x, 25);
//
//			// 设置字的大小，字体样式
//			g2.setFont(new Font("宋体", Font.BOLD, 25));
//			// 设置字体的颜色
//			g2.setColor(new Color(r.nextInt(255), r.nextInt(255), r
//					.nextInt(255)));
//
//			// 取出给给图片上写的字符数据
//			char ch = data.charAt(r.nextInt(data.length()));
//
//			// 把数据保存在sb容器中
//			sb.append(ch);
//			// 给图片上写字
//			g2.drawString(ch + "", x, 25);
//			// 在转回原来的位置
//			g2.rotate(-theta, x, 25);
//			x += 30;
//		}
		// 加干扰点
		for (int i = 0; i < 100; i++) {
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		// 加干扰线
		for (int i = 0; i < 13; i++) {
			// 设置画线的颜色
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			// 画线
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width),
					r.nextInt(height));
		}
		//方法一
		// 取出给给图片上写的字符数据
		//String hash1 = Integer.toHexString(r.nextInt());
		//String capstr = hash1.substring(0, 5);
		// 定义容器保存给图片上生成的字符数据
		StringBuilder capstr = new StringBuilder();
		for(int i = 0; i < 5; i++){
			char ch = data.charAt(r.nextInt(data.length()));
			// 把数据保存在sb容器中
			g.setColor(new Color(r.nextInt(128), r.nextInt(128), 255-r.nextInt(128)));
			g.setFont(new Font("Candara",  Font.BOLD, 20));
			g.drawString(ch+"", 2+ 17*i, 23);
			capstr.append(ch);
		}
		g.dispose();
		// 把sb中的数据保存在Session中
//		HttpSession session = request.getSession();
//		session.setAttribute("checkImg", sb.toString());
		CookieUtils.setCookie(request, response, "checkImg", capstr.toString());
		
		// 获取给客户端输出数据的流对象
		ServletOutputStream out = response.getOutputStream();
		// 把图片缓冲区中的图片写到客户端
		ImageIO.write(image, "JPG", out);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

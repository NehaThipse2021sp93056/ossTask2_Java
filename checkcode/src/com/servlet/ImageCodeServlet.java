package com.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/ImageCodeServlet")
public class ImageCodeServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		/**
		 * ç”Ÿæˆ�å¸¦å­—ç¬¦ä¸²çš„æ–‡æœ¬å›¾ç‰‡
		 */
//		1.åˆ›å»ºå›¾ç‰‡ç¼“å­˜åŒº     ä¼ å�‚ä¸ºå®½é«˜å’Œå›¾ç‰‡ç±»åž‹
		BufferedImage image = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
		
//		2.åˆ›å»ºç»˜åˆ¶çŽ¯å¢ƒï¼ˆæ‹¿åˆ°ç”»ç¬”ï¼‰
		Graphics paint = image.getGraphics();
		Color c = new Color(200,150,255);
//		è®¾ç½®ç”»ç¬”
		paint.setColor(c);
//		ç”»èƒŒæ™¯
		paint.fillRect(0, 0, 68, 22);
		
		StringBuffer codes = new StringBuffer();
//		è®¾ç½®æ–‡æœ¬
		char[] ch = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
		Random r = new Random(); //éš�æœºæ•°
		int index;
		for(int i=0;i<4;i++){
			index = r.nextInt(ch.length);//éš�æœºæ•°ä½�ç½®
			paint.setColor(new Color(r.nextInt(88),r.nextInt(150),r.nextInt(255))); //è®¾ç½®æ–‡æœ¬é¢œè‰²
//			ä½¿ç”¨æ­¤å›¾å½¢ä¸Šä¸‹æ–‡çš„å½“å‰�å­—ä½“å’Œé¢œè‰²ç»˜åˆ¶ç”±æŒ‡å®š string ç»™å®šçš„æ–‡æœ¬ã€‚
			paint.drawString(ch[index]+"", (i*16)+3, 18);
			codes.append(ch[index]);
		}
		req.getSession().setAttribute("codes", codes);
		
		
//		3.è¾“å‡ºå›¾ç‰‡
// adding new comment here!
		ImageIO.write(image, "JPG", resp.getOutputStream()); 
	}
}

package com.tentinet.diver.seal.model;

import javax.imageio.ImageIO;

import com.tentinet.diver.seal.model.SealElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EditPics {
	
	private Font font = new Font("StencilStd", Font.TRUETYPE_FONT , 12);// 添加字体的属性设置


	private Graphics2D g = null;

	private int fontsize = 12;

	
	private int x = 85;                                             //默认坐标

	private int y = 80;
	
	private int m = 0;

	private int n= 0;
	
	private Color redColor=new Color(193, 53, 58);
	
	private Color yellowColor=new Color(255, 181, 86);
	
	private Color blueColor=new Color(27, 147, 216);
	
	
	/**
	 * 导入本地图片到缓冲区
	 */
	public BufferedImage loadImageLocal(String imgName) {
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 导入网络图片到缓冲区
	 */
	public BufferedImage loadImageUrl(String imgName) {
		try {
			URL url = new URL(imgName);
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 设定文字的字体等(系统预设字体)
	 */
	public void setFont(String fontStyle, int fontSize) {
		this.fontsize = fontSize;
		this.font = new Font(fontStyle, Font.TRUETYPE_FONT, fontSize);
	}
	
	/**
	 * 设定文字的字体等（下载的字体）
	 */
	public void setFont(String path,Graphics2D g,float fontSize){
		 try {
			     File fontFile=new File(path);
				
			     Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);   //加载指定路径字体
				
				Font  font=dynamicFont.deriveFont(fontSize);   //指定大小，返回相同样式的字体
				
				 g.setFont(font);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	/**
	 * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
	 */
	public BufferedImage modifyImage(BufferedImage img, Object content, int x,
			int y,SealElement element) {
		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			
			//元素颜色
			String color=element.getSealElementFontColor();
			if(element.getSealElementFontColor() !=null){
				String[] colorArray=color.split(",");
				int a=Integer.valueOf(colorArray[0]);
				int b=Integer.valueOf(colorArray[1]);
				int c=Integer.valueOf(colorArray[2]);
				g.setColor(new Color(a, b, c));
			}
			
			//设置元素字体
			  /**不是预装字体，则URL存放字体文件位置**/
			if(element.getSealElementFontUrl()!=null){
				setFont(Class.class.getClass().getResource("/").getPath()+element.getSealElementFontUrl(),g,element.getSealElementFontSize());
			}else{
				setFont(element.getSealElementFontFamily(), (int) element.getSealElementFontSize());
			}
			
			
			// 验证输出位置的纵坐标和横坐标
			if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}
			if (content != null) {
				int strWidth = g.getFontMetrics().stringWidth(content.toString());
				g.drawString(content.toString(), this.x+ img.getWidth()/2-strWidth / 2, this.y);
			}
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}
	
	
	/**
	 * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出
	 */
	public BufferedImage modifyImage(BufferedImage img, Object[] contentArr,
			int x, int y, boolean xory,SealElement element) {
		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			
			//元素颜色
			String color=element.getSealElementFontColor();
			if(element.getSealElementFontColor() !=null){
				String[] colorArray=color.split(",");
				int a=Integer.valueOf(colorArray[0]);
				int b=Integer.valueOf(colorArray[1]);
				int c=Integer.valueOf(colorArray[2]);
				g.setColor(new Color(a, b, c));
			}
			
			//设置元素字体
			  /**不是预装字体，则URL存放字体文件位置**/
			if(element.getSealElementFontUrl()!=null){
				setFont(Class.class.getClass().getResource("/").getPath()+element.getSealElementFontUrl(),g,element.getSealElementFontSize());
			}else{
				setFont(element.getSealElementFontFamily(), (int) element.getSealElementFontSize());
			}
			// 验证输出位置的纵坐标和横坐标
			if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}
			if (contentArr != null) {
				int arrlen = contentArr.length;
				if (xory) {
					for (int i = 0; i < arrlen; i++) {
							
							g.drawString(contentArr[i].toString(), this.x, this.y);
							this.x += contentArr[i].toString().length()
									* this.fontsize / 2 + 5;// 重新计算文本输出位置
					}
				} else {
					for (int i = 0; i < arrlen; i++) {
						g.drawString(contentArr[i].toString(), this.x, this.y);
						this.y += this.fontsize + 2;// 重新计算文本输出位置
					}
				}
			}
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}
	
	
	/**
	 * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出
	 * x,y第一行内容坐标
	 * m,n第二行内容坐标
	 */
	public BufferedImage modifyImage(BufferedImage img, Object[] contentArr,
			int x, int y,int m ,int n, boolean xory,SealElement element1,SealElement element2) {
		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.BLUE);
			
			
			//元素颜色
			String color=element1.getSealElementFontColor();
			if(element1.getSealElementFontColor() !=null){
				String[] colorArray=color.split(",");
				int a=Integer.valueOf(colorArray[0]);
				int b=Integer.valueOf(colorArray[1]);
				int c=Integer.valueOf(colorArray[2]);
				g.setColor(new Color(a, b, c));
			}
			
			//设置元素字体
			  /**不是预装字体，则URL存放字体文件位置**/
			if(element1.getSealElementFontUrl()!=null){
				setFont(Class.class.getClass().getResource("/").getPath()+"ttf/"+element1.getSealElementFontUrl(),g,element1.getSealElementFontSize());
			}else{
				setFont(element1.getSealElementFontFamily(), (int) element1.getSealElementFontSize());
			}
						
			if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}
			if (m >= h || n >= w) {
				this.m = h - this.fontsize + 2;
				this.n = w;
			} else {
				this.m = m;
				this.n = n;
			}
			
			
			if (contentArr != null) {
				int arrlen = contentArr.length;
				  //在一行绘制输出
				if (xory) {             
					for (int i = 0; i < arrlen; i++) {
						int strWidth = g.getFontMetrics().stringWidth(contentArr.toString());
						g.drawString(contentArr[i].toString(), this.x+ img.getWidth()/2-strWidth / 2, this.y);
						this.x += contentArr[i].toString().length()
								* this.fontsize / 2 + 5;// 重新计算文本输出位置
					}
				} 
				//分二行绘制输出
				else {                                 
				
						int strWidth1 = g.getFontMetrics().stringWidth(contentArr[0].toString());
						g.drawString(contentArr[0].toString(), this.x+ img.getWidth()/2-strWidth1 / 2, this.y);
						
						setFont(Class.class.getClass().getResource("/").getPath()+element2.getSealElementFontUrl(),g,element2.getSealElementFontSize());
						int strWidth2 = g.getFontMetrics().stringWidth(contentArr[1].toString());
						g.drawString(contentArr[1].toString(), this.m+ img.getWidth()/2-strWidth2 / 2, this.n);
						
					
				}
			}
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}
	
	
	
	
	
	/**
	 * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
	 * 
	 * 
	 * @param img
	 * @return
	 */
	public BufferedImage modifyImageYe(BufferedImage img) {

		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.YELLOW);
			if (this.font != null)
				g.setFont(this.font);
			g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}
	
	/**
	 * 将2张图片叠加在一起
	 * @param b
	 * @param d
	 * @return
	 */
	public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {

		try {
			int w = b.getWidth();
			int h = b.getHeight();

			g = d.createGraphics();
			g.drawImage(b, 100, 10, w, h, null);
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return d;
	}

	
	
}

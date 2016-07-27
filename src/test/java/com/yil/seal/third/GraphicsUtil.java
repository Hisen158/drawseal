package com.yil.seal.third;


import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/** * Created by dave on 2016/3/22. */
public class GraphicsUtil {
   public static BufferedImage getSeal(String head,String center,String foot,int canvasWidth,int canvasHeight,double lineArc){
       BufferedImage bi = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
       Graphics2D g2d = bi.createGraphics();
       //设置画笔
       g2d.setPaint(Color.WHITE);
       g2d.fillRect(0, 0, canvasWidth, canvasWidth);

       int circleRadius = Math.min(canvasWidth,canvasHeight)/2;
       /***********draw circle*************/
       g2d.setPaint(Color.red);
       g2d.setStroke(new BasicStroke(5));//设置画笔的粗度
       Shape circle = new Arc2D.Double(0,0,circleRadius*2,circleRadius*2,0,360,Arc2D.OPEN);
       g2d.draw(circle);
       /************************************/

       /***************draw line*******************/
       double halfHeight = circleRadius * (Math.cos(lineArc));
       double halfWidth = circleRadius * (Math.sin(lineArc));

       g2d.drawLine((int)(circleRadius-halfWidth),(int)(circleRadius-halfHeight),(int)(circleRadius+halfWidth),(int)(circleRadius-halfHeight));//
       g2d.drawLine((int)(circleRadius-halfWidth),(int)(circleRadius+halfHeight),(int)(circleRadius+halfWidth),(int)(circleRadius+halfHeight));//
       /***********************END********************/

       /*****************draw string******************/
       int fontSize = 30;
       Font f = new Font("宋体", Font.PLAIN, fontSize);
       FontRenderContext context = g2d.getFontRenderContext();
       Rectangle2D bounds = f.getStringBounds(center, context);

       System.out.println("X:"+bounds.getX());
       System.out.println("Y:"+bounds.getY());
       System.out.println("height:"+bounds.getHeight());
       System.out.println("center y:"+bounds.getCenterY());
       g2d.setFont(f);
       g2d.drawString(center, (float) (circleRadius - bounds.getCenterX()), (float) (circleRadius - bounds.getCenterY()));

       /********************END*********************/

       /*****************draw foot*******************/
       fontSize = 50;
       f = new Font("黑体",Font.BOLD,fontSize);
       context = g2d.getFontRenderContext();
       bounds = f.getStringBounds(foot,context);
       g2d.setFont(f);
       g2d.drawString(foot, (float) (circleRadius - bounds.getCenterX()), (float) (circleRadius*1.5 - bounds.getCenterY()));

       /***************draw string head**************/
       fontSize = 30;
       f = new Font("宋体",Font.PLAIN,fontSize);
       context = g2d.getFontRenderContext();
       bounds = f.getStringBounds(head,context);

       double msgWidth = bounds.getWidth();
       int countOfMsg = head.length();
       double interval = msgWidth/(countOfMsg-1);//计算间距


       double newRadius = circleRadius + bounds.getY()-5;//bounds.getY()是负数，这样可以将弧形文字固定在圆内了。-5目的是离圆环稍远一点
       double radianPerInterval = 2 * Math.asin(interval / (2 * newRadius));//每个间距对应的角度

       //第一个元素的角度
       double firstAngle;
       if(countOfMsg % 2 == 1){//奇数
           firstAngle = (countOfMsg-1)*radianPerInterval/2.0 + Math.PI/2+0.08;
       }else{//偶数
           firstAngle = (countOfMsg/2.0-1)*radianPerInterval + radianPerInterval/2.0 +Math.PI/2+0.08;
       }

       for(int i = 0;i<countOfMsg;i++){
           double aa = firstAngle - i*radianPerInterval;
           double ax = newRadius * Math.sin(Math.PI/2 - aa);//小小的trick，将【0，pi】区间变换到[pi/2,-pi/2]区间
           double ay = newRadius * Math.cos(aa-Math.PI/2);//同上类似，这样处理就不必再考虑正负的问题了
           AffineTransform transform = AffineTransform .getRotateInstance(Math.PI/2 - aa);// ,x0 + ax, y0 + ay);
           Font f2 = f.deriveFont(transform);
           g2d.setFont(f2);
           g2d.drawString(head.substring(i,i+1), (float) (circleRadius+ax),  (float) (circleRadius - ay));
       }

       g2d.dispose();//销毁资源
       return bi;
   }
}
package com.yil.seal;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import com.yil.seal.util.Constants;

public class TestFive {
    
    private static final int WIDTH = 500;//ͼƬ���
    private static final int HEIGHT = 500;//ͼƬ�߶�
    private static String message = "���²������޹�˾";
    private static String centerName = "����˭";
    private static String year = "2016��06��23��";
    


    public static void main(String[] args) throws Exception{
        BufferedImage image = startGraphics2D();
        try {
            String filePath = Constants.IMG_OUTPUT_DIR
					+ File.separator +new Date().getTime()+".png";
            ImageIO.write(image, "png", new File(filePath)); //���䱣����C:\\Users\\huage\\Desktop\\121231\\��
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static BufferedImage startGraphics2D(){  
        // ����ͼ��buffer         
        BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);         
        Graphics2D g = buffImg.createGraphics();      
        g.setColor(Color.RED);
        //���þ��Բ��
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //����Բ
        int radius = HEIGHT/3;//�ܰ뾶
        int CENTERX = WIDTH/2;//��ͼ����λ��
        int CENTERY = HEIGHT/2;//��ͼ����λ��
        
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(CENTERX, CENTERY, CENTERX + radius, CENTERY + radius);
        g.draw(circle);
        
        //�����м�������
        g.setFont(new Font("����", Font.BOLD, 120));
        g.drawString("��", CENTERX-(120/2), CENTERY+(120/3));    

        //�������
        g.setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 30));// д��ǩ��
        g.drawString(centerName, CENTERX -(40), CENTERY +(30+50));
        
        //������
        g.setFont(new Font("����", Font.LAYOUT_LEFT_TO_RIGHT, 20));// д��ǩ��
        g.drawString(year, CENTERX -(60), CENTERY +(30+80));
        
        
        //���������ַ����õ��ַ�����
        String[] messages2 = message.split("",0);
        String[] messages = new String[messages2.length-1];
        System.arraycopy(messages2,1,messages,0,messages2.length-1);
        
        //���������
        int ilength = messages.length;
        
        //������������
        int fontsize = 40;
        Font f = new Font("Serif", Font.BOLD, fontsize);

        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(message, context);
        
        //�ַ���ȣ��ַ�������/�ַ���
        double char_interval = (bounds.getWidth() / ilength);
        //���¶�
        double ascent = -bounds.getY();

        int first = 0,second = 0;
        boolean odd = false;
        if (ilength%2 == 1)
        {
            first = (ilength-1)/2;
            odd = true;
        }
        else
        {
            first = (ilength)/2-1;
            second = (ilength)/2;
            odd = false;
        }
        
        double radius2 = radius - ascent;
        double x0 = CENTERX;
        double y0 = CENTERY - radius + ascent;
        //��ת�Ƕ�
        double a = 2*Math.asin(char_interval/(2*radius2));
        
        if (odd)
        {
            g.setFont(f);
            g.drawString(messages[first], (float)(x0 - char_interval/2), (float)y0);
            
            //���ĵ���ұ�
            for (int i=first+1;i<ilength;i++)
            {
                double aa = (i - first) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g.setFont(f2);
                g.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay - char_interval/2* Math.sin(aa)));
            }
            //���ĵ�����
            for (int i=first-1;i>-1;i--)
            {
                double aa = (first - i) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g.setFont(f2);
                g.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay + char_interval/2* Math.sin(aa)));
            }
            
        }
        else
        {
            //���ĵ���ұ�
            for (int i=second;i<ilength;i++)
            {
                double aa = (i - second + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g.setFont(f2);
                g.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay - char_interval/2* Math.sin(aa)));
            }
            
            //���ĵ�����
            for (int i=first;i>-1;i--)
            {
                double aa = (first - i + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
                Font f2 = f.deriveFont(transform);
                g.setFont(f2);
                g.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay + char_interval/2* Math.sin(aa)));
            }
        }
        
        return buffImg;
    }
}
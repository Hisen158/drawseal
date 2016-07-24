package com.yil.seal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.awt.image.RGBImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.yil.seal.util.Constants;

public class TestTwo {
    public static void main(String[] args) {
        try {
            BufferedImage image = generateCompanySeal(300,300,6,50,"北京天威诚信科技有限责任公司",new Font("FZYaoTi", Font.PLAIN, 40),5,240,"电子合同专用",new Font("FZYaoTi", Font.PLAIN, 30),new Color(230, 0, 0),false);
            ByteArrayOutputStream outCom = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outCom);
            //企业印章
            FileOutputStream fileOut = new FileOutputStream(new File("d://企业.png"));
            outCom.writeTo(fileOut);
            //个人印章
            BufferedImage perImage = generatePersonSeal(4, "杜文磊", new Font("FZYaoTi", Font.PLAIN, 80), 25, new Color(200, 0, 0));
            FileOutputStream filePerOut = new FileOutputStream(new File(Constants.IMG_OUTPUT_DIR
    				+ File.separator +"aaab.png"));
            ByteArrayOutputStream outPer = new ByteArrayOutputStream();
            ImageIO.write(perImage, "png", outPer);
            outPer.writeTo(filePerOut);
            System.out.println("印章生成完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage generateCompanySeal(int width, int height, int circleBorder, int pentagramRadius, String arcCompany, Font arcCompanyFont, int arcMargin, double arcDegree, String sealTitle, Font sealTitleFont, Color inkpadColor, boolean showReferenceLine) throws IOException {
        int centerX = width / 2;
        int centerY = height / 2;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, width, height);
        g2d.setColor(inkpadColor);
        double outerCircleSmallR = (double)(width / 2 - circleBorder);
        if(showReferenceLine) {
            java.awt.geom.Line2D.Double fontMetrics = new java.awt.geom.Line2D.Double(0.0D, (double)(height / 2), (double)width, (double)(height / 2));
            g2d.draw(fontMetrics);
            java.awt.geom.Line2D.Double arrSealName = new java.awt.geom.Line2D.Double((double)(width / 2), 0.0D, (double)(width / 2), (double)height);
            g2d.draw(arrSealName);
            java.awt.geom.Line2D.Double wHeight = new java.awt.geom.Line2D.Double(0.0D, 0.0D, (double)width, (double)height);
            g2d.draw(wHeight);
            java.awt.geom.Line2D.Double rectW = new java.awt.geom.Line2D.Double(0.0D, (double)height, (double)width, 0.0D);
            g2d.draw(rectW);
            java.awt.geom.Rectangle2D.Double sealNameRect = new java.awt.geom.Rectangle2D.Double((double)(centerX / 2), (double)(centerY + centerY / 2), (double)(width / 2), outerCircleSmallR * Math.sqrt(3.0D) / 2.0D - (double)(width / 4));
            g2d.draw(sealNameRect);
        }

        if(showReferenceLine) {
            java.awt.geom.Ellipse2D.Double var66 = new java.awt.geom.Ellipse2D.Double();
            var66.setFrameFromCenter((double)centerX, (double)centerY, (double)(centerX + pentagramRadius), (double)(centerY + pentagramRadius));
            g2d.draw(var66);
        }

        drawPentagram(g2d, centerX, centerY, pentagramRadius);
        g2d.setStroke(new BasicStroke((float)circleBorder));
        g2d.drawOval(0 + circleBorder / 2, 0 + circleBorder / 2, width - circleBorder, height - circleBorder);
        g2d.setFont(sealTitleFont);
        FontMetrics var67 = g2d.getFontMetrics();
        char[] var68 = sealTitle.toCharArray();
        int var69 = var67.getHeight();
        double var70 = (double)(width / 2);
        double rectH = outerCircleSmallR * Math.sqrt(3.0D) / 2.0D - (double)(width / 4);
        int sealNameY = (int)((double)(centerY + width / 4) + rectH / 2.0D + (double)(var69 / 2) - (double)var67.getDescent());
        double www = var70 * 0.9D;
        double deltaW = www / (double)var68.length;
        double wW = (double)(g2d.getFontMetrics().stringWidth(sealTitle) / var68.length);
        double left = (deltaW - wW) / 2.0D;

        for(int arrCompanyName = 0; arrCompanyName < var68.length; ++arrCompanyName) {
            g2d.drawString(String.valueOf(var68[arrCompanyName]), (int)((double)centerX - www / 2.0D + left + (double)arrCompanyName * deltaW), sealNameY);
        }

        if(showReferenceLine) {
            g2d.setStroke(new BasicStroke(0.0F));
            java.awt.geom.Line2D.Double var71 = new java.awt.geom.Line2D.Double((double)(centerX / 2), (double)(centerY + centerY / 2) + rectH / 2.0D - (double)(var69 / 2), (double)(centerX + centerX / 2), (double)(centerY + centerY / 2) + rectH / 2.0D - (double)(var69 / 2));
            g2d.draw(var71);
            java.awt.geom.Line2D.Double wordWidth = new java.awt.geom.Line2D.Double((double)(centerX - centerX / 2), (double)centerY + 2.0D * outerCircleSmallR * Math.sqrt(3.0D) / 4.0D - (rectH / 2.0D - (double)(var69 / 2)), (double)(centerX + centerX / 2), (double)centerY + 2.0D * outerCircleSmallR * Math.sqrt(3.0D) / 4.0D - (rectH / 2.0D - (double)(var69 / 2)));
            g2d.draw(wordWidth);
        }

        g2d.setFont(arcCompanyFont);
        var67 = g2d.getFontMetrics();
        char[] var72 = arcCompany.toCharArray();
        int var73 = var67.stringWidth(String.valueOf(var72[0]));
        int wordHeight = var67.getHeight();
        int textR = width / 2 - wordHeight + var67.getDescent() - arcMargin;
        if(showReferenceLine) {
            java.awt.geom.Ellipse2D.Double xx = new java.awt.geom.Ellipse2D.Double();
            g2d.setStroke(new BasicStroke(0.0F));
            xx.setFrameFromCenter((double)centerX, (double)centerY, (double)(centerX + textR), (double)(centerY + textR));
            g2d.draw(xx);
        }

        double var74 = Math.sqrt((double)(textR * textR - var73 * var73 / 4)) - (double)g2d.getFontMetrics().getDescent();
        double wordDegree = 2.0D * Math.toDegrees(Math.atan2((double)(var73 / 2), var74));
        double r1 = Math.sqrt((double)(textR * textR - var73 * var73 / 4));
        double c = Math.toDegrees(Math.atan((double)(var73 / 2) / r1));
        double totalDegree = arcDegree - wordDegree + 2.0D * c;
        double deltaDegree = totalDegree / (double)(arcCompany.length() - 1);
        int imageWithTransparency = var72.length - 1;

        for(int transparentImage = 0; imageWithTransparency >= 0; ++transparentImage) {
            double a = deltaDegree * (double)transparentImage - (arcDegree / 2.0D - 90.0D) + wordDegree - c;
            double x = (double)textR * Math.cos(Math.toRadians(a));
            double y = (double)textR * Math.sin(Math.toRadians(a));
            double b = 90.0D - Math.toDegrees(Math.atan2(y, x));
            double b1 = b + c;
            AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(b1));
            Font thisFont = arcCompanyFont.deriveFont(transform);
            g2d.setFont(thisFont);
            String word = String.valueOf(var72[imageWithTransparency]);
            g2d.drawString(word, (int)(x + (double)centerX), (int)((double)centerY - y));
            --imageWithTransparency;
        }

        g2d.dispose();
        Image var75 = makeColorTransparent(image, Color.WHITE);
        BufferedImage var76 = imageToBufferedImage(var75);
        return var76;
    }
    public static BufferedImage generatePersonSeal(int sealBorder, String personName, Font personNameFont, int wordSpacing, Color inkpadColor) throws IOException {
        short width = 800;
        short height = 200;
        int centerX = width / 2;
        int centerY = height / 2;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, width, height);
        g2d.setColor(inkpadColor);
        char[] arrSealName = personName.toCharArray();
        g2d.setFont(personNameFont);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int wHeight = fontMetrics.getHeight();
        int sealNameY = centerY + wHeight / 2 - fontMetrics.getDescent();
        double www = (double)(fontMetrics.stringWidth(personName) - wordSpacing * arrSealName.length);
        double deltaW = www / (double)arrSealName.length;
        double wW = (double)(fontMetrics.stringWidth(personName) / arrSealName.length);
        double left = (deltaW - wW) / 2.0D;
        int lineY = sealNameY + fontMetrics.getDescent();
        double arcW1 = 20.0D;
        double arcH1 = 20.0D;
        java.awt.geom.RoundRectangle2D.Double outerRoundedRectangle = new java.awt.geom.RoundRectangle2D.Double((double)centerX - www / 2.0D - (double)(sealBorder / 2), (double)(lineY - wHeight - sealBorder / 2), www + (double)sealBorder, (double)(wHeight + sealBorder), arcW1, arcH1);
        g2d.fill(outerRoundedRectangle);
        double arcW2 = 16.0D;
        double arcH2 = 16.0D;
        g2d.setColor(Color.WHITE);
        java.awt.geom.RoundRectangle2D.Double innerRoundedRectangle = new java.awt.geom.RoundRectangle2D.Double((double)centerX - www / 2.0D + (double)(sealBorder / 2), (double)(lineY - wHeight + sealBorder / 2), www - (double)sealBorder, (double)(wHeight - sealBorder), arcW2, arcH2);
        g2d.fill(innerRoundedRectangle);
        g2d.setColor(inkpadColor);

        int sWidth;
        for(sWidth = 0; sWidth < arrSealName.length; ++sWidth) {
            g2d.drawString(String.valueOf(arrSealName[sWidth]), (int)((double)centerX - www / 2.0D + left + (double)sWidth * deltaW), sealNameY);
        }

        g2d.dispose();
        sWidth = (int)(www + (double)sealBorder);
        int sHeight = wHeight + sealBorder;
        CropImageFilter cropFilter = new CropImageFilter((int)((double)centerX - www / 2.0D - (double)(sealBorder / 2)), lineY - wHeight - sealBorder / 2, sWidth, sHeight);
        Image slicedImage = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
        BufferedImage bufferedSlicedImage = new BufferedImage(sWidth, sHeight, 1);
        Graphics smallGraphics = bufferedSlicedImage.getGraphics();
        smallGraphics.drawImage(slicedImage, 0, 0, (ImageObserver)null);
        smallGraphics.dispose();
        Image imageWithTransparency = makeColorTransparent(bufferedSlicedImage, Color.WHITE);
        BufferedImage transparentImage = imageToBufferedImage(imageWithTransparency);
        return transparentImage;
    }

    public static void drawPentagram(Graphics g, int x0, int y0, int r) {
        int rr = (int)((double)r * (Math.sin(Math.toRadians(54.0D)) - Math.cos(Math.toRadians(54.0D)) * Math.tan(Math.toRadians(36.0D))));
        int x1 = (int)((double)x0 - (double)r * Math.cos(Math.toRadians(18.0D)));
        int y1 = (int)((double)y0 - (double)r * Math.sin(Math.toRadians(18.0D)));
        int y2 = y0 - r;
        int x3 = (int)((double)x0 + (double)r * Math.cos(Math.toRadians(18.0D)));
        int x4 = (int)((double)x0 + (double)r * Math.cos(Math.toRadians(54.0D)));
        int y4 = (int)((double)y0 + (double)r * Math.sin(Math.toRadians(54.0D)));
        int x5 = (int)((double)x0 - (double)r * Math.cos(Math.toRadians(54.0D)));
        int xx1 = (int)((double)x0 + (double)rr * Math.cos(Math.toRadians(18.0D)));
        int yy1 = (int)((double)y0 + (double)rr * Math.sin(Math.toRadians(18.0D)));
        int yy2 = y0 + rr;
        int xx3 = (int)((double)x0 - (double)rr * Math.cos(Math.toRadians(18.0D)));
        int xx4 = (int)((double)x0 - (double)rr * Math.cos(Math.toRadians(54.0D)));
        int yy4 = (int)((double)y0 - (double)rr * Math.sin(Math.toRadians(54.0D)));
        int xx5 = (int)((double)x0 + (double)rr * Math.cos(Math.toRadians(54.0D)));
        Polygon polygon = new Polygon();
        polygon.addPoint(x1, y1);
        polygon.addPoint(xx4, yy4);
        polygon.addPoint(x0, y2);
        polygon.addPoint(xx5, yy4);
        polygon.addPoint(x3, y1);
        polygon.addPoint(xx1, yy1);
        polygon.addPoint(x4, y4);
        polygon.addPoint(x0, yy2);
        polygon.addPoint(x5, y4);
        polygon.addPoint(xx3, yy1);
        g.fillPolygon(polygon);
    }
    public static Image makeColorTransparent(BufferedImage bufferedImage, final Color color) {
        RGBImageFilter filter = new RGBImageFilter() {
            public int markerRGB = color.getRGB() | -1;

            public final int filterRGB(int x, int y, int rgb) {
                return (rgb | -16777216) == this.markerRGB?16777215 & rgb:rgb;
            }
        };
        FilteredImageSource imageProducer = new FilteredImageSource(bufferedImage.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(imageProducer);
    }
    public static BufferedImage imageToBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth((ImageObserver)null), image.getHeight((ImageObserver)null), 2);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, (ImageObserver)null);
        g2.dispose();
        return bufferedImage;
    }
}

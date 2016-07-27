package com.yil.seal.third;

import java.awt.Point;
import java.util.Scanner;

public class Fitting {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args){
		//set the number of point
		int pointNum = 50;
		//set the vector (a,b,c,d,e,f)
		Matrix vectorA = MatrixFactory.getMatrix(1, 6, null);
		double a=-2, b=2, c=-1.5, d=0, e=0, f=1;
		vectorA.set(1, 1, a);
		vectorA.set(1, 2, b);
		vectorA.set(1, 3, c);
		vectorA.set(1, 4, d);
		vectorA.set(1, 5, e);
		vectorA.set(1, 6, f);
		
		//initialize opencv library
		System.loadLibrary("opencv_java245");
        Mat img = Mat.zeros(500, 500, CvType.CV_8UC3);
        printEllipse(img,a,b,c,d,e,f,new Scalar(255,0,0));
        
        
        //get the input of noisy
        Scanner input=new Scanner(System.in);
        System.out.println("Noisy=? (proper number can be 4-25)");
        double noisy = 0;
        noisy=input.nextDouble();
        while(noisy<5){
        	System.out.println("The noisy number is too small!");
        	noisy=input.nextDouble();
        }
        
        //initialize the array of all points
        //The block realized here is specially for the ellipse that a=-2, b=2, c=-1.5, f=1
		Point[] pointArray = new Point[pointNum];
		for(int i=0; i<pointNum; i++){
			double randomY=1;
			while(randomY <=-1 || randomY >=1){
				randomY = (Math.random() - 0.5)*2;
			}
			double s=1;
			if(Math.random()>0.5){
				s=-1;
			}
			pointArray[i] = new Point(
					randomY/2 + s* Math.sqrt(2*(1-randomY*randomY))/2,
					randomY);
			//add some noisy
			pointArray[i].x += s*Math.random()/noisy;
			pointArray[i].y += s*Math.random()/noisy;
			Point drawPoint = new Point(pointArray[i].x*100+250, pointArray[i].y*100+250);
			Core.line(img, drawPoint, drawPoint, new Scalar(255,255,255));
		}
		
		//initialize the matrix C
		Matrix C = MatrixFactory.getMatrix(6, 6, null);
		initialData(C, 6, 6);
		C.set(1, 3, 2);
		C.set(3, 1, 2);
		C.set(2, 2, -1);
		
		//initialize the matrix D
		Matrix D = MatrixFactory.getRandomMatrix(pointNum, 6, null);
		for(int i=1; i<=pointNum; i++){
			double currentX = pointArray[i-1].x;
			double currentY = pointArray[i-1].y;
			double x2 = currentX*currentX;
			double xy = currentX*currentY;
			double y2 = currentY*currentY;
			D.set(i, 1, x2);
			D.set(i, 2, xy);
			D.set(i, 3, y2);
			D.set(i, 4, currentX);
			D.set(i, 5, currentY);
			D.set(i, 6, 1);
		}
		
		//produce S-1*C
		Matrix DT = MatrixTransformer.transpose(D);
		Matrix S = MatrixOperator.multiply(DT, D);
		Matrix SI = MatrixTransformer.inverse(S);
		Matrix SIC = MatrixOperator.multiply(SI, C);
		
		//produce the eigenvalue and eigenVector
		EigenvalueDecomposition ed = new EigenvalueDecomposition(SIC);
		Matrix eigenMatrix = ed.getV();
		System.out.println(eigenMatrix);
		double[] eigenvalueList = ed.getRealEigenvalues();
		double eigenvalue=0;
		int index;
		
		for(index=0; index<6; ){
			if(eigenvalueList[index]>0){
				eigenvalue = eigenvalueList[index++];
				break;
			}
		}
		
		for(int i=1; i<=1; i++){
			Matrix eigenVector = eigenMatrix.getSubMatrix(1, index, 6, index);
			Matrix eigenVectorT = MatrixTransformer.transpose(eigenVector);
			Matrix tmp = MatrixOperator.multiply(eigenVectorT, S);
			Matrix tmp2 = MatrixOperator.multiply(tmp, eigenVector);
			double rate2 = Math.sqrt(1/((tmp2).get(1, 1)));
			f = eigenMatrix.get(6, index);
			double rate = f/1;
			f = 1;
			a = eigenMatrix.get(1, index) / rate;
			b = eigenMatrix.get(2, index) / rate;
			c = eigenMatrix.get(3, index) / rate;
			d = eigenMatrix.get(4, index) / rate;
			e = eigenMatrix.get(5, index) / rate;
			printEllipse(img,a,b,c,d,e,f,new Scalar(0,0,255));
		}

		
        String filename = "fitting_ellipse.png";
        Highgui.imwrite(filename, img);
        System.out.println("Done!");
	}
	
	private static void initialData(Matrix a, int n, int m){
		for(int i=1;i<=n;i++){
			for(int j=1;j<=m;j++){
				a.set(i, j, 0);
			}
		}
	}
	
	private static void printEllipse(
			Mat img, double a, double b, double c, double d, double e, double f, Scalar color){
		double x = (b*e-2*c*d)/(4*a*c-b*b);
        double y = (b*d-2*a*e)/(4*a*c-b*b);
        double tmp1 = 2*(a*x*x+c*y*y+b*x*y-1);
        double tmp2 = Math.sqrt((a-c)*(a-c)+b*b);
        double tmp3 = a+c;
        double longedge = Math.sqrt( tmp1 / ( tmp3 + tmp2 ) );
        double shortedge = Math.sqrt( tmp1 / ( tmp3 - tmp2 ) );
        double theta = 0.5 * Math.atan(b/(a-c)) * 360 / (2*Math.PI);
        Point center = new Point(x*100+250, y*100+250);
        //draw the first ellipse
        try{
        	Core.ellipse(img, center, new Size(shortedge*100,longedge*100), theta, 0, 360, color);
        } catch(CvException cve){
        	return;
        }
	}
}
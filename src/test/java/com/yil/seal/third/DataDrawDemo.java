package com.yil.seal.third;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import javax.swing.event.*;

import java.awt.geom.*;

public class DataDrawDemo extends JFrame

{

	public JPanel contentPane; // 控件容器

	JPanel jPanel1 = new JPanel();// 绘图控件

	JButton jButton1 = new JButton();

	JButton jButton2 = new JButton();

	JButton jButton3 = new JButton();

	JButton jButton4 = new JButton();

	JButton jButton5 = new JButton();

	JButton jButton6 = new JButton();

	JButton jButton7 = new JButton();

	JButton jButton8 = new JButton();

	JButton jButton9 = new JButton();

	JButton jButton10 = new JButton();

	JTextField jText1 = new JTextField();

	JTextField jText2 = new JTextField();

	boolean kcu = true;

	// 用户坐标转换对象

	myGraphicsData mp = new myGraphicsData();

	// 曲线转换对象

	GraphicsCurve gracu = new GraphicsCurve();

	// 窗口范围

	double wx1, wx2, wy1, wy2;

	// 构造函数

	public DataDrawDemo() {

		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		try {

			jbInit();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 初始化代码

	private void jbInit() throws Exception {

		contentPane = (JPanel) this.getContentPane();

		contentPane.setLayout(null);

		this.setSize(new Dimension(650, 500));

		this.setTitle("Frame Title");

		// contentPane.setSize(400,240);

		// jPanel1.setLayout(null);

		jPanel1.setBounds(0, 90, 650, 420);

		jButton1.setBounds(new Rectangle(30, 20, 80, 25));

		jButton1.setText("开始");

		jButton1.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton1_actionPerformed(e);

			}

		});

		jButton2.setBounds(new Rectangle(120, 20, 80, 25));

		jButton2.setText("左移");

		jButton2.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton2_actionPerformed(e);

			}

		});

		jButton3.setBounds(new Rectangle(210, 20, 80, 25));

		jButton3.setText("右移");

		jButton3.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton3_actionPerformed(e);

			}

		});

		jButton4.setBounds(new Rectangle(300, 20, 80, 25));

		jButton4.setText("上移");

		jButton4.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton4_actionPerformed(e);

			}

		});

		jButton5.setBounds(new Rectangle(390, 20, 80, 25));

		jButton5.setText("下移");

		jButton5.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton5_actionPerformed(e);

			}

		});

		jButton6.setBounds(new Rectangle(120, 50, 80, 25));

		jButton6.setText("X扩");

		jButton6.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton6_actionPerformed(e);

			}

		});

		jButton7.setBounds(new Rectangle(210, 50, 80, 25));

		jButton7.setText("X缩");

		jButton7.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton7_actionPerformed(e);

			}

		});

		jButton8.setBounds(new Rectangle(300, 50, 80, 25));

		jButton8.setText("Y扩");

		jButton8.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton8_actionPerformed(e);

			}

		});

		jButton9.setBounds(new Rectangle(390, 50, 80, 25));

		jButton9.setText("Y缩");

		jButton9.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton9_actionPerformed(e);

			}

		});

		// 样条控制

		jButton10.setBounds(new Rectangle(30, 50, 80, 25));

		jButton10.setText("样条");

		jButton10.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton10_actionPerformed(e);

			}

		});

		jText1.setBounds(new Rectangle(490, 20, 120, 20));

		jText1.setText("");

		jText2.setBounds(new Rectangle(490, 50, 120, 20));

		jText2.setText("");

		// 鼠标按下侦听器

		jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mousePressed(MouseEvent e) {

				JPanel_mousePressed(e);

			}

		});

		// 鼠标拖动侦听器

		jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {

				JPanel_mouseDragged(e);

			}

		});

		// 鼠标释放侦听器

		jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseReleased(MouseEvent e) {

				JPanel_mouseReleased(e);

			}

		});

		contentPane.add(jPanel1, BorderLayout.CENTER);

		contentPane.add(jButton1, null);

		contentPane.add(jButton2, null);

		contentPane.add(jButton3, null);

		contentPane.add(jButton4, null);

		contentPane.add(jButton5, null);

		contentPane.add(jButton6, null);

		contentPane.add(jButton7, null);

		contentPane.add(jButton8, null);

		contentPane.add(jButton9, null);

		contentPane.add(jButton10, null);

		contentPane.add(jText1, null);

		contentPane.add(jText2, null);

	}

	public static void main(String[] args) {

		DataDrawDemo frame = new DataDrawDemo();

		frame.show();

		frame.gracu.myGraphics = (Graphics2D) frame.jPanel1.getGraphics();

		frame.mp.myGraphics = (Graphics2D) frame.jPanel1.getGraphics();

		frame.mp.myGraphics.setBackground(Color.white);

		frame.mp.myGraphics.clearRect(0, 0, 650, 375);

	}

	// 第一组数据

	double[] Xs1 = new double[] { -2, 2, 4, 6, 8, 10, 12, 14 };

	double[] Ys1 = new double[] { -4, 10, 3, 14, 4, 10, 6, 7 };

	// 第二组数据

	double[] Xs2 = new double[] { -1, 2, 4, 6, 8, 10, 12, 14, 16 };

	double[] Ys2 = new double[] { 1, 5, 7, 1, 13, 11, 4, 10, 8 };

	// 这是一个画曲线的程序

	void DwData()

	{

		// 剪裁，可以试试没有剪裁是什么表现？

		mp.myGraphics.clipRect(10, 10, 621, 351);

		// 由于是用两个对象绘图，所以应该分别剪切

		gracu.myGraphics.clipRect(10, 10, 621, 351);

		// 清除绘图空间

		mp.myGraphics.clearRect(0, 0, 650, 375);

		// 用户坐标和屏幕坐标转换

		mp.truemode(10, 630, 10, 360, wx1, wx2, wy1, wy2);

		// 设置颜色

		mp.myGraphics.setPaint(Color.darkGray);

		// 画边框

		mp.myGraphics.drawRect(10, 10, 620, 350);

		mp.myGraphics.setFont(new Font("宋体", 0, 10)); // 9为字大小

		// 画坐标

		mp.axis(2, 2, 2, 2);

		// 画红线

		int[] x = new int[Xs1.length];

		int[] y = new int[Ys1.length];

		int[] myxy;

		// 统一实现坐标转换

		for (int i = 0; i < x.length; i++)

		{

			myxy = mp.moxy(Xs1[i], Ys1[i]);

			x[i] = myxy[0];

			y[i] = myxy[1];

		}

		if (kcu)

		{

			mp.myGraphics.setPaint(Color.red);

			mp.myGraphics.drawPolyline(x, y, x.length);

		}

		else

		{

			gracu.myGraphics.setPaint(Color.red);

			gracu.DrawCurves(x, y);

		}

		// 画蓝线

		x = new int[Xs2.length];

		y = new int[Ys2.length];

		for (int i = 0; i < x.length; i++)

		{

			myxy = mp.moxy(Xs2[i], Ys2[i]);

			x[i] = myxy[0];

			y[i] = myxy[1];

		}

		if (kcu)

		{

			mp.myGraphics.setPaint(Color.blue);

			mp.myGraphics.drawPolyline(x, y, x.length);

		}

		else

		{

			gracu.myGraphics.setPaint(Color.blue);

			gracu.DrawCurves(x, y);

		}

	}

	// 退出窗口事件

	protected void processWindowEvent(WindowEvent e) {

		super.processWindowEvent(e);

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {

			System.exit(0);

		}

	}

	// 鼠标按下事件

	void JPanel_mousePressed(MouseEvent e)

	{

		double[] zs = mp.ScrtoCon(e.getX(), e.getY());

		jText1.setText(String.valueOf((float) zs[0]));

		jText2.setText(String.valueOf((float) zs[1]));

	}

	// 鼠标释放事件

	void JPanel_mouseReleased(MouseEvent e)

	{

		double[] zs = mp.ScrtoCon(e.getX(), e.getY());

		jText1.setText("");

		jText2.setText("");

		// 画红线

		mp.myGraphics.setPaint(Color.darkGray);

		mp.myGraphics.drawLine(e.getX() - 3, e.getY(), e.getX() + 3, e.getY());

		mp.myGraphics.drawLine(e.getX(), e.getY() - 3, e.getX(), e.getY() + 3);

		mp.myGraphics.drawString(String.valueOf((float) zs[0]), e.getX() + 10,
				e.getY());

		mp.myGraphics.drawString(String.valueOf((float) zs[1]), e.getX() + 10,
				e.getY() + 12);

	}

	// 鼠标拖动事件

	void JPanel_mouseDragged(MouseEvent e)

	{

		double[] zs = mp.ScrtoCon(e.getX(), e.getY());

		jText1.setText(String.valueOf((float) zs[0]));

		jText2.setText(String.valueOf((float) zs[1]));

	}

	// 开始

	void jButton1_actionPerformed(ActionEvent e) {

		wx1 = 1000000;

		wy1 = 1000000;

		wx2 = -1000000;

		wy2 = -1000000;

		// 试验中数据来自一个数组，实际中可来自任何地方

		// 设置初始范围

		for (int i = 0; i < Xs1.length; i++)

		{

			if (Xs1[i] < wx1)

				wx1 = Xs1[i];

			if (Xs1[i] > wx2)

				wx2 = Xs1[i];

			if (Ys1[i] < wy1)

				wy1 = Ys1[i];

			if (Ys1[i] > wy2)

				wy2 = Ys1[i];

		}

		for (int i = 0; i < Xs2.length; i++)

		{

			if (Xs2[i] < wx1)

				wx1 = Xs2[i];

			if (Xs2[i] > wx2)

				wx2 = Xs2[i];

			if (Ys2[i] < wy1)

				wy1 = Ys2[i];

			if (Ys2[i] > wy2)

				wy2 = Ys2[i];

		}

		DwData();

	}

	// 左移

	void jButton2_actionPerformed(ActionEvent e) {

		wx1 -= 1;

		wx2 -= 1;

		DwData();

	}

	// 右移

	void jButton3_actionPerformed(ActionEvent e) {

		wx1 += 1;

		wx2 += 1;

		DwData();

	}

	// 上移

	void jButton4_actionPerformed(ActionEvent e) {

		wy1 -= 1;

		wy2 -= 1;

		DwData();

	}

	// 下移

	void jButton5_actionPerformed(ActionEvent e) {

		wy1 += 1;

		wy2 += 1;

		DwData();

	}

	// X扩

	void jButton6_actionPerformed(ActionEvent e) {

		wx1 -= 1;

		wx2 += 1;

		DwData();

	}

	// X缩

	void jButton7_actionPerformed(ActionEvent e) {

		wx1 += 1;

		wx2 -= 1;

		DwData();

	}

	// Y扩

	void jButton8_actionPerformed(ActionEvent e) {

		wy1 -= 1;

		wy2 += 1;

		DwData();

	}

	// Y缩

	void jButton9_actionPerformed(ActionEvent e) {

		wy1 += 1;

		wy2 -= 1;

		DwData();

	}

	// 样条控制

	void jButton10_actionPerformed(ActionEvent e) {

		if (kcu)

		{

			jButton10.setText("直线");

			kcu = false;

		}

		else

		{

			jButton10.setText("样条");

			kcu = true;

		}

		DwData();

	}

}

// 图形处理类

class myGraphicsData

{

	// 屏幕坐标

	private int X11, Y11, X12, Y12; // x1,y1,x2,y2

	// 用户坐标

	private double W1, W2, W3, W4; // x1,x2,y1,y2

	// 绘图对象

	public Graphics2D myGraphics;

	double Ax8, Ay8;

	// 用户窗口与屏幕窗口的转换

	// x1,x2,,y1,y2为屏幕坐标

	// wx1,wx2,wy1,wy2为用户坐标

	public void truemode(int x1, int x2, int y1, int y2, double wx1,
			double wx2, double wy1, double wy2)

	{

		X11 = x1;
		X12 = x2;

		Y11 = y1;
		Y12 = y2;

		W1 = wx1;
		W2 = wx2;

		W3 = wy1;
		W4 = wy2;

		Ax8 = (X12 - X11) / (wx2 - wx1);

		Ay8 = (Y12 - Y11) / (wy2 - wy1);

	}

	// 把用户坐标转为屏幕坐标

	public int[] moxy(double Xa, double Ya)

	{

		int[] myout = new int[2];

		myout[0] = (int) (Ax8 * (Xa - W1) + X11);

		myout[1] = (int) (Y12 - Ay8 * (Ya - W3));

		return myout;

	}

	// 把屏幕坐标转为用户坐标

	public double[] ScrtoCon(int X6, int Y6)

	{

		double[] myout = new double[2];

		myout[0] = (X6 - X11) / Ax8 + W1;

		myout[1] = (Y12 - Y6) / Ay8 + W3;

		return myout;

	}

	// 画线

	public void Dline(double xa, double ya, double xb, double yb)

	{

		try

		{

			int x6, y6, x7, y7;

			x6 = (int) (Ax8 * (xa - W1) + X11);

			y6 = (int) (Y12 - Ay8 * (ya - W3));

			x7 = (int) (Ax8 * (xb - W1) + X11);

			y7 = (int) (Y12 - Ay8 * (yb - W3));

			myGraphics.drawLine(x6, y6, x7, y7);

		}

		catch (Exception e) {
		}

	}

	// 画坐标U,V为 X,Y轴单位,ns,nt为 x,y轴写字间隔

	public void axis(double u, double v, int ns, int nt)

	{

		double p9, q9, s;

		int n2, swx, swy;

		int xk = 0;

		int yk = 0;

		double ge;

		int[] showxy = new int[2];

		swx = 0;

		swy = 4;

		ge = (double) 0.008 * (W2 - W1);

		p9 = W1;

		q9 = (double) (W3 + (W4 - W3) * 0.05);

		if ((W1 < 0) && (W2 > 0))
			p9 = 0;

		if ((W3 < 0) && (W4 > 0))
			q9 = 0;

		Dline(p9, W3, p9, W4);

		n2 = 0;

		s = 0;

		while (s < W4)

		{

			Dline(p9, s, p9 + ge, s);

			if (n2 >= nt)

			{

				Dline(p9, s, p9 + ge + ge, s);

				n2 = 1;

				showxy = moxy(p9 + ge + ge, s);

				myGraphics.drawString(String.valueOf(s), showxy[0] - swx + 4,
						showxy[1] - swy + 4);

			}

			else

			{

				n2++;

			}

			s += v;

		}

		// End While

		s = 0;

		n2 = 0;

		while (s > W3)

		{

			Dline(p9, s, p9 + ge, s);

			if (n2 >= nt)

			{

				Dline(p9, s, p9 + ge + ge, s);

				n2 = 1;

				showxy = moxy(p9 + ge + ge, s);

				myGraphics.drawString(String.valueOf(s), showxy[0] - swx + 4,
						showxy[1] - swy + 4);

			}

			else

			{

				n2++;

			}

			s -= v;

		}

		// End While

		Dline(W1, q9, W2, q9);

		ge = (float) (0.008 * (W4 - W3));

		n2 = 0;

		s = 0;

		while (s < W2)

		{

			Dline(s, q9, s, q9 + ge);

			if (n2 >= ns)

			{

				Dline(s, q9, s, q9 + ge + ge);

				n2 = 1;

				showxy = moxy(s, q9);

				myGraphics.drawString(String.valueOf(s), showxy[0] - swx,
						showxy[1] - swy - 4);

			}

			else

			{

				n2++;

			}

			s += u;

		}

		// End While

		s = 0;

		n2 = 0;

		while (s > W1)

		{

			Dline(s, q9, s, q9 + ge);

			if (n2 >= ns)

			{

				Dline(s, q9, s, q9 + ge + ge);

				n2 = 1;

				showxy = moxy(s, q9);

				myGraphics.drawString(String.valueOf(s), showxy[0] - swx,
						showxy[1] - swy - 4);

			}

			else

			{

				n2++;

			}

			s -= u;

		}

		// End While

	}

}

class GraphicsCurve

{

	// 绘图对象

	public Graphics2D myGraphics;

	public GraphicsCurve()

	{

	}

	public GraphicsCurve(Graphics2D graphics)

	{

		this.myGraphics = graphics;

	}

	// 参数表

	// x数组，y数组，笔刷

	public void DrawCurves(int[] xa, int[] ya)

	{

		int[] x, y;

		double[] a, b, c;

		double[] px, py, qx, qy, tt;

		double[] dx, dy;

		int px1, py1, px2, py2;

		x = xa;

		y = ya;

		px1 = x[0];

		py1 = y[0];

		int n = x.length;

		a = new double[n];

		b = new double[n];

		c = new double[n];

		px = new double[n];

		py = new double[n];

		qx = new double[n];

		qy = new double[n];

		tt = new double[n];

		dx = new double[n];

		dy = new double[n];

		int i, t, es;

		double bx3, bx4, by3, by4, cx, cy;

		bx4 = 0;

		by3 = 0;

		es = 3;

		px[0] = 1;

		py[0] = 1;

		px[n - 1] = 1;

		py[n - 1] = 1;

		if (n > 1)

		{

			for (i = 1; i < n; i++)

				tt[i] = Math.sqrt((x[i] - x[i - 1]) * (x[i] - x[i - 1])
						+ (y[i] - y[i - 1]) * (y[i] - y[i - 1]));

			switch (n)

			{

			case 2:

				break;

			case 3:

				for (i = 1; i < n - 1; i++)

				{

					a[i] = 2 * (tt[i] + tt[i + 1]);

					b[i] = tt[i + 1];

					c[i] = tt[i];

					dx[i] = 3 * (tt[i] * (x[i + 1] - x[i]) / tt[i + 1] + tt[i + 1]
							* (x[i] - x[i - 1]) / tt[i]);

					dy[i] = 3 * (tt[i] * (y[i + 1] - y[i]) / tt[i + 1] + tt[i + 1]
							* (y[i] - y[i - 1]) / tt[i]);

				}

				dx[1] = dx[1] - tt[2] * px[0];

				dx[n - 2] = dx[n - 2] - tt[n - 2] * px[n - 1];

				dy[1] = dy[1] - tt[2] * py[0];

				dy[n - 2] = dy[n - 2] - tt[n - 2] * py[n - 1];

				// 注意，这是n=3的情况专有计算

				px[1] = dx[1] / a[1];

				py[1] = dy[1] / a[1];

				break;

			default:

				for (i = 1; i < n - 1; i++)

				{

					a[i] = 2 * (tt[i] + tt[i + 1]);

					b[i] = tt[i + 1];

					c[i] = tt[i];

					dx[i] = 3 * (tt[i] * (x[i + 1] - x[i]) / tt[i + 1] + tt[i + 1]
							* (x[i] - x[i - 1]) / tt[i]);

					dy[i] = 3 * (tt[i] * (y[i + 1] - y[i]) / tt[i + 1] + tt[i + 1]
							* (y[i] - y[i - 1]) / tt[i]);

				}

				dx[1] = dx[1] - tt[2] * px[0];

				dx[n - 2] = dx[n - 2] - tt[n - 2] * px[n - 1];

				dy[1] = dy[1] - tt[2] * py[0];

				dy[n - 2] = dy[n - 2] - tt[n - 2] * py[n - 1];

				c[1] = c[1] / a[1];

				for (i = 2; i < n - 1; i++)

				{

					a[i] = a[i] - b[i] * c[i - 1];

					c[i] = c[i] / a[i];

				}

				qx[1] = dx[1] / a[1];

				qy[1] = dy[1] / a[1];

				for (i = 2; i < n - 1; i++)

				{

					qx[i] = (dx[i] - b[i] * qx[i - 1]) / a[i];

					qy[i] = (dy[i] - b[i] * qy[i - 1]) / a[i];

				}

				px[n - 2] = qx[n - 2];

				py[n - 2] = qy[n - 2];

				for (i = n - 3; i >= 1; i--)

				{

					px[i] = qx[i] - c[i] * px[i + 1];

					py[i] = qy[i] - c[i] * py[i + 1];

				}

				break;

			}

			for (i = 0; i < n - 1; i++)

			{

				bx3 = (3 * (x[i + 1] - x[i]) / tt[i + 1] - 2 * px[i] - px[i + 1])
						/ tt[i + 1];

				bx4 = ((2 * (x[i] - x[i + 1]) / tt[i + 1] + px[i] + px[i + 1]) / tt[i + 1])
						/ tt[i + 1];

				by3 = (3 * (y[i + 1] - y[i]) / tt[i + 1] - 2 * py[i] - py[i + 1])
						/ tt[i + 1];

				by4 = ((2 * (y[i] - y[i + 1]) / tt[i + 1] + py[i] + py[i + 1]) / tt[i + 1])
						/ tt[i + 1];

				t = 0;

				while (t < tt[i + 1])

				{

					t = t + es;

					cx = x[i] + (px[i] + (bx3 + bx4 * t) * t) * t;

					cy = y[i] + (py[i] + (by3 + by4 * t) * t) * t;

					px2 = (int) cx;

					py2 = (int) cy;

					myGraphics.drawLine(px1, py1, px2, py2);

					px1 = px2;

					py1 = py2;

				}

			}

		}

	}

}

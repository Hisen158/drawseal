package com.yil.seal.third;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.awt.geom.*;

public class DrawDemo1 extends JFrame

{

	public JPanel contentPane; // ��ͼ����

	public Graphics2D comp2D; // ��ͼ����

	JPanel jPanel1 = new JPanel();// �ؼ�����

	JButton jButton1 = new JButton();

	JButton jButton2 = new JButton();

	// ���캯��

	public DrawDemo1() {

		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		try {

			jbInit();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	// �ؼ���ʼ��

	private void jbInit() throws Exception {

		contentPane = (JPanel) this.getContentPane();

		contentPane.setLayout(new BorderLayout());

		this.setSize(new Dimension(400, 300));

		this.setTitle("Frame Title");

		// contentPane.setSize(400,240);

		jPanel1.setLayout(null);

		jButton1.setBounds(new Rectangle(30, 235, 150, 31));

		jButton1.setText("���ױ�����");

		jButton1.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton1_actionPerformed(e);

			}

		});

		jButton2.setBounds(new Rectangle(200, 235, 150, 30));

		jButton2.setText("���ױ�����");

		jButton2.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jButton2_actionPerformed(e);

			}

		});

		contentPane.add(jPanel1, BorderLayout.CENTER);

		jPanel1.add(jButton1, null);

		jPanel1.add(jButton2, null);

	}

	public static void main(String[] args) {

		DrawDemo1 frame = new DrawDemo1();

		frame.show();

		frame.comp2D = (Graphics2D) frame.contentPane.getGraphics();

		frame.comp2D.setBackground(Color.white);

		frame.comp2D.clearRect(0, 0, 401, 221);

	}

	// Overridden so we can exit when window is closed

	protected void processWindowEvent(WindowEvent e) {

		super.processWindowEvent(e);

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {

			System.exit(0);

		}

	}

	void jButton1_actionPerformed(ActionEvent e) {

		double[] x1 = { 50, 180, 300 };

		double[] y1 = { 100, 190, 100 };

		comp2D.clearRect(0, 0, 401, 221);

		// �ʿ��

		float thick = 1f;

		comp2D.setPaint(Color.red);

		QuadCurve2D.Double qc = new QuadCurve2D.Double();

		qc.setCurve(x1[0], y1[0], x1[1], y1[1], x1[2], y1[2]);

		comp2D.draw(qc);

		comp2D.drawLine((int) x1[1] - 5, (int) y1[1], (int) x1[1] + 5,
				(int) y1[1]);

		comp2D.drawLine((int) x1[1], (int) y1[1] - 5, (int) x1[1],
				(int) y1[1] + 5);

		comp2D.setPaint(Color.blue);

		x1[1] = 180;

		y1[1] = 30;

		qc.setCurve(x1[0], y1[0], x1[1], y1[1], x1[2], y1[2]);

		comp2D.draw(qc);

		comp2D.drawLine((int) x1[1] - 5, (int) y1[1], (int) x1[1] + 5,
				(int) y1[1]);

		comp2D.drawLine((int) x1[1], (int) y1[1] - 5, (int) x1[1],
				(int) y1[1] + 5);

	}

	void jButton2_actionPerformed(ActionEvent e) {

		double[] x1 = { 50, 80, 200, 300 };

		double[] y1 = { 100, 70, 190, 100 };

		comp2D.clearRect(0, 0, 401, 221);

		// �ʿ��

		float thick = 1f;

		comp2D.setPaint(Color.red);

		CubicCurve2D.Double qc = new CubicCurve2D.Double();

		qc.setCurve(x1[0], y1[0], x1[1], y1[1], x1[2], y1[2], x1[3], y1[3]);

		comp2D.draw(qc);

		comp2D.drawLine((int) x1[1] - 5, (int) y1[1], (int) x1[1] + 5,
				(int) y1[1]);

		comp2D.drawLine((int) x1[1], (int) y1[1] - 5, (int) x1[1],
				(int) y1[1] + 5);

		comp2D.drawLine((int) x1[2] - 5, (int) y1[2], (int) x1[2] + 5,
				(int) y1[2]);

		comp2D.drawLine((int) x1[2], (int) y1[2] - 5, (int) x1[2],
				(int) y1[2] + 5);

		float dash1[] = { 10.0f };

		// ������

		BasicStroke dashed = new BasicStroke(1.0f,

		BasicStroke.CAP_BUTT,

		BasicStroke.JOIN_MITER,

		10.0f, dash1, 0.0f);

		comp2D.setStroke(dashed);

		comp2D.setPaint(Color.darkGray);

		comp2D.drawLine((int) x1[1], (int) y1[1], (int) x1[2], (int) y1[2]);

		// ��ʵ��

		BasicStroke stroke = new BasicStroke(1.0f);

		comp2D.setStroke(stroke);

		comp2D.setPaint(Color.blue);

		x1[1] = 180;

		y1[1] = 70;

		x1[2] = 80;

		y1[2] = 190;

		qc.setCurve(x1[0], y1[0], x1[1], y1[1], x1[2], y1[2], x1[3], y1[3]);

		comp2D.draw(qc);

		comp2D.drawLine((int) x1[1] - 5, (int) y1[1], (int) x1[1] + 5,
				(int) y1[1]);

		comp2D.drawLine((int) x1[1], (int) y1[1] - 5, (int) x1[1],
				(int) y1[1] + 5);

		comp2D.drawLine((int) x1[2] - 5, (int) y1[2], (int) x1[2] + 5,
				(int) y1[2]);

		comp2D.drawLine((int) x1[2], (int) y1[2] - 5, (int) x1[2],
				(int) y1[2] + 5);

		comp2D.setStroke(dashed);

		comp2D.setPaint(Color.darkGray);

		comp2D.drawLine((int) x1[1], (int) y1[1], (int) x1[2], (int) y1[2]);

		comp2D.setStroke(stroke);

	}

}

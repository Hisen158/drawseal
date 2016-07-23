package com.yil.seal;

/**
 *(300,100)(400,100)
 *
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Lipse {
	public static void main(String[] args) {
		new MainFrame();
	}
}

class MainFrame extends JFrame implements ActionListener {
	JPanel pane = new JPanel();
	JTextField T_a, T_b;
	JButton Draw, Show;
	JLabel L_a, L_b;
	int a, b;

	MainFrame() {
		super("DrawLipse Window");
		Container con = this.getContentPane();
		con.setLayout(null);

		pane.setBounds(20, 20, 850, 550);
		pane.setBackground(new Color(100, 156, 200));
		con.add(pane);

		L_a = new JLabel("«Î ‰»Î≥§∞Îæ∂:a");
		L_a.setBounds(180, 580, 100, 20);
		con.add(L_a);

		L_b = new JLabel("«Î ‰»Î∂Ã∞Îæ∂:b");
		L_b.setBounds(180, 630, 100, 20);
		con.add(L_b);

		T_a = new JTextField();
		T_a.setBounds(300, 580, 50, 20);
		con.add(T_a);

		T_b = new JTextField();
		T_b.setBounds(300, 630, 50, 20);
		con.add(T_b);

		Draw = new JButton("ª≠Õ÷‘≤");
		Draw.setBounds(550, 580, 90, 30);
		Draw.addActionListener(this);
		con.add(Draw);

		Show = new JButton("œ‘ æ◊¯±Í");
		Show.setBounds(550, 620, 90, 30);
		Show.addActionListener(this);
		con.add(Show);

		this.addWindowListener(new CloseWindow());
		this.setBounds(20, 20, 900, 700);
		this.setVisible(true);
		this.setResizable(false);

	}/* MainFrame() */

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Draw) {
			a = Integer.parseInt(T_a.getText().trim());
			b = Integer.parseInt(T_b.getText().trim());
			Line line = new Line(this);
			line.drawLipse(a, b);
		}
		if (e.getSource() == Show) {
			Graphics g1 = this.pane.getGraphics();
			g1.setColor(Color.PINK);
			g1.drawLine(0, 300, 920, 300);// ----x---
			g1.drawLine(410, 0, 410, 720);// ----y---
			g1.dispose();
		}

	}/* method actionPerformed */
}

class Line {
	MainFrame jb;

	Line(MainFrame jb) {
		this.jb = jb;
	}

	public void drawLipse(int a, int b) {
		int x, y;
		double d1, d2;
		x = 0;
		y = b;
		d1 = b * b + a * a * (-b + 0.25);
		Graphics g = jb.pane.getGraphics();
		g.setColor(Color.red);
		g.drawLine(x + 410, y + 300, x + 410, y + 300);
		g.drawLine(-x + 410, -y + 300, -x + 410, -y + 300);
		g.drawLine(-x + 410, y + 300, x + 410, -y + 300);
		g.drawLine(x + 410, -y + 300, x + 410, -y + 300);
		try {
			while (b * b * (x + 1) < a * a * (y - 0.5)) {
				if (d1 <= 0) {
					d1 += b * b * (2 * x + 3);
					x++;
				} else {
					d1 += (b * b * (2 * x + 3) + a * a * (-2 * y + 2));
					x++;
					y--;
				}
				g.drawLine(x + 410, y + 300, x + 410, y + 300);
				g.drawLine(-x + 410, -y + 300, -x + 410, -y + 300);
				g.drawLine(-x + 410, y + 300, x + 410, -y + 300);
				g.drawLine(x + 410, -y + 300, x + 410, -y + 300);
				Thread.sleep(30);
			}// top of while
		} catch (Exception e) {
		}

		d2 = b * b * (x + 0.5) * (x + 0.5) + a * a * (y - 1) * (y - 1) - a * a
				* b * b;
		try {
			while (y > 0) {
				if (d2 <= 0) {
					d2 += b * b * (2 * x + 2) + a * a * (-2 * y + 3);
					x++;
					y--;
				} else {
					d2 += a * a * (-2 * y + 3);
					y--;
				}
				g.drawLine(x + 410, y + 300, x + 410, y + 300);
				g.drawLine(-x + 410, -y + 300, -x + 410, -y + 300);
				g.drawLine(-x + 410, y + 300, x + 410, -y + 300);
				g.drawLine(x + 410, -y + 300, x + 410, -y + 300);
				Thread.sleep(30);
			}/* bottom of while */

		} catch (Exception e) {
		}

	} /* DrawLipse */

}

class CloseWindow extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}

package termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class First_Frame extends JFrame {
	public Container c;
	public Second_Frame second_frame;
	
	public First_Frame() {
		setTitle("MYONGJI MARKET");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setBackground(Color.PINK);
		c.setLayout(new BorderLayout(0,10));
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("MYONGJI MARKET");
		panel.setBackground(Color.PINK);
		panel.add(label, BorderLayout.CENTER);
		
		c.add(panel, BorderLayout.NORTH);
		label.setForeground(Color.YELLOW);
		label.setFont(new Font("Arial", Font.ITALIC, 70));
		
		this.second_frame = new Second_Frame();
		c.add(this.second_frame);
		
		setSize(900, 580);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new First_Frame();
	}
}
package termproject;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Category extends JPanel {
	public Category(ActionListener btnAction, String[][] categoryList) {
		
		GridLayout grid = new GridLayout(3, 3);
		this.setLayout(grid);
		this.setBackground(Color.PINK);
		
		ArrayList<JButton> btn = new ArrayList<JButton>();
		for(int i=0; i<categoryList.length; i++) {
			JButton jButton = new JButton(categoryList[i][0]);
			jButton.setBackground(new Color(170, 200, 230));
			btn.add(jButton);
		}
		
		for(int i=0; i<btn.size(); i++) {
			btn.get(i).addActionListener(btnAction);
			btn.get(i).setActionCommand(categoryList[i][1]);
			this.add(btn.get(i));
		}
	}
}
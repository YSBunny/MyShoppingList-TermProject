package termproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;

public class MoveDialog extends JDialog {
	private String selectionText;
	private String[][] categoryList;
	private JList<String> itemList;
	
	public MoveDialog(String[][] categoryList, String selectionText, JList<String> itemList) {
		this.selectionText = selectionText;
		this.categoryList = categoryList;
		
		setTitle("�ٸ� ī�װ��� �̵�");
		
		Container c = getContentPane();
		c.setBackground(Color.PINK);
		c.setLayout(new GridLayout(2, 4));
		
		for(int i=0; i<this.categoryList.length-1; i++) {
			JButton jButton = new JButton(this.categoryList[i][0]);
			jButton.setBackground(new Color(170, 200, 230));
			jButton.addActionListener(new BtnAction());
			c.add(jButton);
		}
		
		setSize(300, 200);
		setVisible(true);
		setResizable(false);
	}
	
	class BtnAction implements ActionListener {
		JButton btnCategory;
		
		@Override
		public void actionPerformed(ActionEvent event) {
			this.btnCategory = (JButton)event.getSource();
			
			// ���õ� ����Ʈ�� ������ ī�װ��� �̵�
			for(int i=0; i<categoryList.length-1; i++) {
				if(btnCategory.getText().equals(categoryList[i][0])) {
					File file1 = new File("data/" + categoryList[i][1] + ".txt");
					
					try {
						// ���õ� ������ ������ ī�װ��� �����ϱ�
						FileWriter fout = new FileWriter(file1, true);
						fout.write("\r\n");
						fout.write(selectionText);
						fout.close();
						
						// ���õ� �������� ���� ī�װ����� �����ϱ�
						File file2 = new File("data/undetermined.txt");
						Scanner sc = new Scanner(file2);
						FileWriter fout2 = new FileWriter(file2);
						
						while(sc.hasNext()) {
							String str = sc.next();
							
							if(str.equals(selectionText)) {
								continue;
							}
							else {
								fout2.write("\r\n");
								fout2.write(str);
							}
							itemList.updateUI();
						}
						sc.close();
						fout2.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
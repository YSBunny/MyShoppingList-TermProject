package termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Second_Frame extends JPanel {
   public SearchPanel searchPanel;
   public Third_Frame third_frame;
   
   private JTextField tf = new JTextField(30);
   private JButton button = new JButton("�߰�");
   private Vector<String> v = new Vector<String>();
   
   private String[][] categoryList = 
      {{"�Ƿ�", "clothes"}, {"�Ź�", "shoes"}, {"��Ƽ", "beauty"}, {"��ǰ", "food"}, {"����ǰ", "dailyNecessity"}, 
            {"����", "stationery"}, {"������", "sports"}, {"����", "homeAppliances"}, {"����", "undetermined"}};
   
   public Second_Frame() {
      this.setBackground(Color.PINK);
      this.setLayout(new BorderLayout(0,35));
      
      // ī�װ��� �ִ� �����۵� �о ���Ϳ� ����
      try {
         for (int i=0; i<categoryList.length; i++) {
            File file = new File("data/" + categoryList[i][1] + ".txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()) {
               v.add(scanner.next());
            }
            scanner.close();
         }
      } 
      catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      
      button.setBackground(new Color(170, 200, 230));
      
      MyMouseListener mouse = new MyMouseListener();

      this.searchPanel = new SearchPanel(mouse, tf, button);
      this.third_frame = new Third_Frame(categoryList);
      
      this.add(searchPanel, BorderLayout.NORTH);
      this.add(third_frame, BorderLayout.CENTER);
   }
   
   class MyMouseListener extends MouseAdapter {
	   public void mousePressed(MouseEvent e) {
		   try {
			   // ī�װ��� �ִ� ������ �ٽ� �о ���Ϳ� ����
			   for (int i=0; i<categoryList.length; i++) {
				   File file = new File("data/" + categoryList[i][1] + ".txt");
				   Scanner scanner = new Scanner(file);
				   
				   while(scanner.hasNext()) {
					   v.add(scanner.next());
				   }
				   scanner.close();
			   }
		   } 
		   catch (FileNotFoundException exception) {
			   exception.printStackTrace();
		   }
		   
		   // ���Ϳ� ��� ��� �����۵� �а� ���翩�� Ȯ��
		   for (int i=0; i<v.size(); i++) {
			   if (tf.getText().equals(v.get(i))) {
				   JOptionPane.showMessageDialog(null, "�̹� �ִ� ��ǰ�Դϴ�.");
				   return;
			   }
		   }
		   
		   // �������� ������ ���� ī�װ��� ����
		   File file = new File("data/undetermined.txt");
		   FileWriter writer = null;
		   try {
			   writer = new FileWriter(file, true);
			   tf.write(writer);
			   writer.write("\r\n");
			   writer.close();
		   }
		   catch (IOException a) {
			   a.printStackTrace();
		   }
		   JOptionPane.showMessageDialog(null, "���� ī�װ��� �����ϴ�.");
	   }
   }
}
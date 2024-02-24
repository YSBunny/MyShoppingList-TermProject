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
   private JButton button = new JButton("추가");
   private Vector<String> v = new Vector<String>();
   
   private String[][] categoryList = 
      {{"의류", "clothes"}, {"신발", "shoes"}, {"뷰티", "beauty"}, {"식품", "food"}, {"생필품", "dailyNecessity"}, 
            {"문구", "stationery"}, {"스포츠", "sports"}, {"가전", "homeAppliances"}, {"미정", "undetermined"}};
   
   public Second_Frame() {
      this.setBackground(Color.PINK);
      this.setLayout(new BorderLayout(0,35));
      
      // 카테고리에 있는 아이템들 읽어서 벡터에 저장
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
			   // 카테고리에 있는 아이템 다시 읽어서 벡터에 저장
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
		   
		   // 벡터에 담긴 모든 아이템들 읽고 존재여부 확인
		   for (int i=0; i<v.size(); i++) {
			   if (tf.getText().equals(v.get(i))) {
				   JOptionPane.showMessageDialog(null, "이미 있는 상품입니다.");
				   return;
			   }
		   }
		   
		   // 존재하지 않으면 미정 카테고리에 저장
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
		   JOptionPane.showMessageDialog(null, "미정 카테고리에 담겼습니다.");
	   }
   }
}
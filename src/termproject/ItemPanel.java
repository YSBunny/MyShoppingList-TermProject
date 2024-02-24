package termproject;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;

public class ItemPanel extends JPanel {
   public ItemList itemList;
   private Vector<String> v = new Vector<String>();
   private String[][] categoryList;
   
   public ItemPanel(String[][] categoryList, ListSelectionListener listSelection, ActionListener btn) {
      this.categoryList = categoryList;
      this.itemList = new ItemList();
      
      JScrollPane scroll = new JScrollPane(itemList);
      scroll.setPreferredSize(new Dimension(250, 300));
      
      this.add(scroll);
      
      // 카테고리에 있는 아이템들 읽어서 벡터에 저장
      try {
            for (int i=0; i<this.categoryList.length; i++) {
               File file = new File("data/" + this.categoryList[i][1] + ".txt");
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
      
      
      JButton btnput = new JButton("담기");
      btnput.setBackground(new Color(170, 200, 230));
      btnput.addActionListener(btn);
      
      JButton btnMove = new JButton("이동");
      btnMove.setBackground(new Color(170, 200, 230));
      btnMove.addActionListener(btn);
      
      JButton btndelete = new JButton("제거");
      btndelete.setBackground(new Color(170, 200, 230));
      btndelete.addActionListener(btn);
      
      JButton btnprint = new JButton("출력");
      btnprint.setBackground(new Color(170, 200, 230));
      btnprint.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent event) {
            // 리스트의 데이터를 하나의 문자열로 보존
            String line = "";
            line += "Item List \r\n";
            
            for(int i=0; i<v.size(); i++) {
               line += (String)v.elementAt(i);
               line += "\r\n";
            }
            
            // JEditorPane에 문자열 설정
            JEditorPane pane = new JEditorPane("text/plain", line);
            
            // JEditorPane의 print 메소드를 호출해서 인쇄
            try {
               pane.print();
            } catch (PrinterException e) {
               e.printStackTrace();
            }
         }
      });
      
      this.add(btnput);
      this.add(btnMove);
      this.add(btndelete);
      this.add(btnprint);
      this.setBackground(Color.PINK);
   }
   
   public String getItem() {
	   return this.itemList.getItems();
   }
   
   public void item_refresh(Vector<String> vector) {
      this.itemList.item_refresh(vector);
   }
   
   public class ItemList extends JList<String> {
      private Vector<String> listData;
      
      public ItemList() {
         this.listData = new Vector<String>();
         this.setListData(listData);
         this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         this.setSelectedIndex(0);
      }
      
      public String getItems() {
     	 String name = listData.get(this.getSelectedIndex());
     	 return name;
      }
      
      public void item_refresh(Vector<String> vector) {
         this.listData.clear();
         for(int i=0; i<vector.size(); i++) {
            this.listData.add(vector.get(i));
         }
         this.updateUI();
      }
   }
}
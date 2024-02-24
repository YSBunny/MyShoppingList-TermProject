package termproject;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Third_Frame extends JPanel {
	private Category category;
	private ItemPanel itemPanel;
	private ShoppingPanel shoppingPanel;
	private String selectionText;
	private JList<String> itemList;
	private ListSelection listSelection;
	private ButtonAction btnAction;
	private ButtonHandler btn;
	private String[][] categoryList;
	private Vector<String> itemVector;
	private JButton btn8;

	// ī�װ� ���� �̸�
	String readCategory;
	// ī�װ� �ѱ� �̸� 
	String readCategory_korea;

	public Third_Frame(String[][] categoryList) {
		this.categoryList = categoryList;
		
		this.setBackground(Color.PINK);
		this.setLayout(new GridLayout(1, 3, 50, 0));
		
		this.btnAction = new ButtonAction();
		this.btn = new ButtonHandler();
		this.listSelection = new ListSelection();
		
		this.category = new Category(btnAction, categoryList);
		this.itemPanel = new ItemPanel(categoryList, listSelection, btn);
		this.shoppingPanel = new ShoppingPanel();
		
		this.add(category);
		this.add(itemPanel);
		this.add(shoppingPanel);
	}
	
	public void readFile(String btnAction) {
		readCategory = btnAction;
		
		// Ŭ���� ī�װ��� �����۵��� ���Ͽ��� �о ���Ϳ� ����
		File file = new File("data/" + readCategory + ".txt");
		try {
			Scanner sc = new Scanner(file);
			itemVector = new Vector<String>();
			while(sc.hasNext()) {
				itemVector.add(sc.next());
			}
			this.itemPanel.item_refresh(itemVector);
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteList() {
		String item = this.itemPanel.getItem(); // ������ ����Ʈ���� ���õ� ������
		String fileName = "";
		
		// ī�װ��� �ִ� �����۵��� �����鼭 ������ �����۰� ������ ī�װ� ���Ͽ��� ����
		for(int i=0; i<categoryList.length; i++) {
			try {
				File file = new File("data/" + categoryList[i][1] + ".txt");
				Scanner sc = new Scanner(file);
				String write = "";
				Vector<String> v = new Vector<String>();
				while(sc.hasNext()) {
					String str = sc.next();
					if(str.equals(item)) {
						fileName = categoryList[i][1];
					}
					else {
						write = write + str + "\n";
						v.add(str);
					}
				}
				sc.close();
				FileWriter fw = new FileWriter(file, false);
				fw.write(write);
				fw.close();
			}
			catch(IOException exception) {
				exception.printStackTrace();
			}
		}
		readFile(fileName);
	}

	class GetItemList {
		public GetItemList(JList<String> items) {
			itemList = items;
		}
	}

	class ButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			btn8 = (JButton)e.getSource();
			String btnAction = e.getActionCommand();
			readFile(btnAction);
		}
	}

	class ListSelection implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				selectionText = itemList.getSelectedValue().toString();
			}
		}
	}

	class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String bt = e.getActionCommand();
			if(bt.equals("���")) {
				// if ������ null ó���ص� �� or Ʈ����ĳġ���ص���
				try {
					// ī�װ� �̸� ã��
					for( String[] a : categoryList) {
						if( readCategory.equals( a[1].toString() ) ){
							readCategory_korea = a[0].toString();
						}
					}
					// ī�װ� �ѱ��̸��� �� add �ϱ�
					shoppingPanel.addItem( readCategory_korea, itemPanel.itemList.getSelectedValue() );
				}
				catch( NullPointerException ne ) {
				}
			}
			else if(bt.equals("�̵�") && btn8.getText().equals("����")) {
				listSelection = new ListSelection();
				String select = itemPanel.getItem();
				deleteList();
				new MoveDialog(categoryList, select, itemList);
			}
			else if(bt.equals("����")) {
				deleteList();
			}
		}
	}
}
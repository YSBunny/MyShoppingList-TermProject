package termproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

public class ShoppingPanel extends JPanel implements ActionListener{
	
	private ShoppingList shoppingList;
	
	JButton btnRemove = new JButton("����");
	JButton btnPrint = new JButton("���");
	
	public ShoppingPanel() {
		this.shoppingList = new ShoppingList(null);
		
		JScrollPane scroll = new JScrollPane(shoppingList);
		scroll.setPreferredSize(new Dimension(250, 300));
		
		btnRemove.setBackground(new Color(170, 200, 230));
		btnPrint.setBackground(new Color(170, 200, 230));
		
		this.add(scroll);
		
		this.add(btnRemove);
		this.add(btnPrint);
		this.setBackground(Color.PINK);
		
		btnRemove.addActionListener(this);
		btnPrint.addActionListener(this);
	}
	
	// ������ �ֱ� 
	public void addItem( String category, String name ) {
		shoppingList.listData = new Vector<String>();
		shoppingList.listData.add( category );
		shoppingList.listData.add( name );
		shoppingList.dtm.addRow( shoppingList.listData );
	}
	
	public void removeItem() {
		if( shoppingList.getSelectedRow() >= 0 ) {
			shoppingList.dtm.removeRow( shoppingList.getSelectedRow() );
		}
	}
	
	public void printItem() {
		// ����Ʈ�� �����͸� �ϳ��� ���ڿ��� ����
		String line = "";
		line += "Shopping List \r\n";
		
		for(int i=0; i<shoppingList.getModel().getRowCount(); i++) {
			for(int j=0; j<shoppingList.getColumnCount(); j++) {
				line += (String)shoppingList.getModel().getValueAt(i, j);
				line += '\t';
			}
			line += "\r\n";
		}
		
		// JEditorPane�� ���ڿ� ����
		JEditorPane pane = new JEditorPane("text/plain", line);
		
		// JEditorPane�� print �޼ҵ带 ȣ���ؼ� �μ�
		try {
			pane.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// ��ư Ŭ��
		if(arg0.getSource().equals(btnRemove)) {
			removeItem();
		}
		else if(arg0.getSource().equals(btnPrint)) {
			printItem();
		}
	}
	
	public class ShoppingList extends JTable {
		private Vector<String> colName;
		public DefaultTableModel dtm;
		public Vector<String> listData;
		
		public ShoppingList(Vector<String> listData) {
			this.listData = listData;
			
			colName = new Vector<String>();
			colName.add("ī�װ�");
			colName.add("��ǰ");
			
			// �͸� ��ø Ŭ������ ���̺� ���� ���� ����
			dtm = new DefaultTableModel(colName, 0) { // DefaultTableModel(Vector ColumnNames, int rowCount)
				@Override
				public boolean isCellEditable(int row, int column) { // ���̺��� ���� ���� ���θ� �˷��ִ� �޼ҵ�
					return false; // ������ �� �ǵ��� ��
				}
			};
			
			this.setModel(dtm);
			this.getTableHeader().setPreferredSize(new Dimension(100, 20));
			this.getTableHeader().setBackground(new Color(170, 200, 230));
			this.getTableHeader().setReorderingAllowed(false); // JTable�� ����� ���� (true�� ��������)
			this.getTableHeader().setResizingAllowed(false); // JTable�� ��� ũ�� ���� �Ұ�
		}
	}
}
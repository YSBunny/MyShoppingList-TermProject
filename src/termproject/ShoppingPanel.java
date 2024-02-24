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
	
	JButton btnRemove = new JButton("제거");
	JButton btnPrint = new JButton("출력");
	
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
	
	// 아이템 넣기 
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
		// 리스트의 데이터를 하나의 문자열로 보존
		String line = "";
		line += "Shopping List \r\n";
		
		for(int i=0; i<shoppingList.getModel().getRowCount(); i++) {
			for(int j=0; j<shoppingList.getColumnCount(); j++) {
				line += (String)shoppingList.getModel().getValueAt(i, j);
				line += '\t';
			}
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// 버튼 클릭
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
			colName.add("카테고리");
			colName.add("상품");
			
			// 익명 중첩 클래스로 테이블 편집 여부 설정
			dtm = new DefaultTableModel(colName, 0) { // DefaultTableModel(Vector ColumnNames, int rowCount)
				@Override
				public boolean isCellEditable(int row, int column) { // 테이블의 편집 가능 여부를 알려주는 메소드
					return false; // 편집이 안 되도록 함
				}
			};
			
			this.setModel(dtm);
			this.getTableHeader().setPreferredSize(new Dimension(100, 20));
			this.getTableHeader().setBackground(new Color(170, 200, 230));
			this.getTableHeader().setReorderingAllowed(false); // JTable의 헤더를 고정 (true는 고정해제)
			this.getTableHeader().setResizingAllowed(false); // JTable의 헤더 크기 조절 불가
		}
	}
}
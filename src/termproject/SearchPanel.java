package termproject;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {
   public SearchPanel(MouseListener mouse, JTextField tf, JButton button) {
      
      this.add(tf);
      this.add(button);
      this.setBackground(Color.PINK);      
      
      button.addMouseListener(mouse);
   }
}
import java.awt.Color;

import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
	  JFrame.setDefaultLookAndFeelDecorated(true);
      draw frame = new draw();

//frame.setForeground(Color.WHITE);
      frame.pack();
      frame.setVisible(true);
	}
}

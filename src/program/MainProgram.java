package program;

import javax.swing.UIManager;
import components.Window;
import gui.StartPanel;

public class MainProgram {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			Window.getInstance().setContainer(new StartPanel());
			Window.getInstance().setVisible(true);	
		} catch (Exception e) {
			System.err.println("% [Main Program] Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
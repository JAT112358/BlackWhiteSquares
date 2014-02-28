package components;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar
{
	private static final long serialVersionUID = -8432386582582038108L;
	
	public ToolBar()
	{
		super();
		
		JMenuItem mitemPlay = new JMenuItem(new ImageIcon("play-icon.png"));
	}
}

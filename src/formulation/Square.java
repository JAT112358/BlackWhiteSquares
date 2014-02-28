package formulation;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Square extends JPanel {

	private static final long 	serialVersionUID = -4162750023324593339L;
	private boolean				isWhite;
	private int 				pos;
	
	public Square(boolean isWhite, int pos)
	{
		this.isWhite = isWhite;
		this.pos = pos;
		
		if( ! isWhite)
			setBackground(Color.BLACK);
		setBorder(new LineBorder(Color.DARK_GRAY, 2));
	}
	
	public boolean isWhite()
	{
		return isWhite;
	}
}

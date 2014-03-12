package formulation;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Square extends JPanel {
	private static final long 	serialVersionUID = -4162750023324593339L;
	private boolean				isWhite;
	
	public Square(boolean isWhite) {
		super();
		this.isWhite = isWhite;
		
		if( ! isWhite)
			setBackground(Color.BLACK);
		setBorder(new LineBorder(Color.DARK_GRAY, 2));
	}
	
	public boolean isWhite() {
		return isWhite;
	}
	
	public String toString() {
		if(isWhite) 
			return "[W]"; 
		else 
			return "[B]"; 
	}
	
	// HOMEWORK 2/4 [Punto 3]
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Square && ((Square) obj).isWhite == isWhite;
	}
}
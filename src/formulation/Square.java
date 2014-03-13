package formulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Square extends JPanel {
	private static final long 	serialVersionUID = -4162750023324593339L;
	private boolean				isWhite;
	private int					step;
	
	public Square(boolean isWhite) {
		super();
		this.isWhite = isWhite;
		this.step = -1;
		
		if( ! isWhite)
			setBackground(Color.BLACK);
	}
	
	public boolean isWhite() {
		return isWhite;
	}
	
	public void setStep(int step) {
		this.step = step;
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
	
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(isWhite) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillRect(3, 3, this.getWidth()-6, this.getHeight()-6);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		if(step >= 0) {
			g.setColor(Color.RED); 
			g.drawString(Integer.toString(step),  this.getWidth()/2 - 4, this.getHeight()/2 + 8);
		}
	}
}
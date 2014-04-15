package formulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Square extends JPanel {
	private static final long 	serialVersionUID = -4162750023324593339L;
	private char				color;
	private int					step;
	
	public Square(char color) {
		super();
		this.color = color;
		this.step = -1;
		
		if(color == 'b') {
			setBackground(Color.BLACK);			
		} else if(color == 'w') {
			setBackground(Color.WHITE);
		}
	}
	
	public char getColor() {
		return color;
	}
	
	public void setColor(char color) {
		this.color = color;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public String toString() {
		return "[" + color + "]";
	}
	
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Square && ((Square) obj).color == color;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(color == 'b') {
			g.setColor(Color.BLACK);
		} else if(color == 'w') {
			g.setColor(Color.WHITE);
		}
		g.fillRect(3, 3, this.getWidth()-6, this.getHeight()-6);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.BLUE);
		if(step >= 0 && step < 10) { 
			g.drawString(Integer.toString(step),  this.getWidth()/2 - 4, this.getHeight()/2 + 8);
		} else if(step >=10 && step <=100){
			g.drawString(Integer.toString(step),  this.getWidth()/2 - 8, this.getHeight()/2 + 8);
		}
	}
}
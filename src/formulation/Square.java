package formulation;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Square extends JPanel implements Cloneable{

	private static final long 	serialVersionUID = -4162750023324593339L;
	private boolean				isWhite;
	private boolean 			selected;
	
	public Square(boolean isWhite, boolean selected)
	{
		super();
		this.isWhite = isWhite;
		this.selected = selected;
		
		if( ! isWhite)
			setBackground(Color.BLACK);
		setBorder(new LineBorder(Color.DARK_GRAY, 2));
	}
	
	public boolean isWhite()
	{
		return isWhite;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected(boolean selected) 
	{
		this.selected = selected;
		this.repaint();
	}

	public String toString()
	{
		String text = "[";
		if(isWhite) 
			text += "W"; 
		else 
			text += "B"; 
		if(selected) 
			text += "*";
		text += "] ";
		return text;
	}
	
	// HOMEWORK 2/4 [Punto 3]
	public boolean equals(Object obj) 
	{
		return obj != null && obj instanceof Square
			   && ((Square) obj).isWhite == isWhite
			   && ((Square) obj).selected == selected;
	}
	
	public void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        if(this.selected)
        {
        	g.setColor(Color.RED);
        	g.fillOval((this.getWidth() / 2) - 10, (this.getHeight() / 2) - 10, 20, 20);
        }
    }
}
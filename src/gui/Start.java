package gui;

import javax.swing.JPanel;

import components.Window;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;

public class Start extends JPanel {

	private static final long serialVersionUID = -5851058755580336316L;
	
	private SquaresPanel squares_panel;
	private boolean[] testSquares = new boolean[]{true, true, true, false, true, false, true, false, false, true, false, true, false, true, false, false, true, true, false, true};
	
	
	public Start() 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{50, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_left = new JPanel();
		GridBagConstraints gbc_panel_squares = new GridBagConstraints();
		gbc_panel_squares.anchor = GridBagConstraints.NORTH;
		gbc_panel_squares.insets = new Insets(25, 0, 5, 0);
		gbc_panel_squares.gridx = 0;
		gbc_panel_squares.gridy = 0;
		add(panel_left, gbc_panel_squares);
		panel_left.setLayout(new BorderLayout(0, 0));
		
		squares_panel = new SquaresPanel(testSquares);
		panel_left.add(squares_panel);
	}
	
	public static void main (String [] args)
	{
		Window.getInstance().setContainer(new Start());
		Window.getInstance().setVisible(true);
	}
}
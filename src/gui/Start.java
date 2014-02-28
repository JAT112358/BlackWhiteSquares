package gui;

import javax.swing.JPanel;

import components.Window;
import formulation.Square;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import java.awt.Dimension;

public class Start extends JPanel 
{
	private static final long 	serialVersionUID = -5851058755580336316L;
	
	private SquaresPanel 		squares_panel;
	private Square[] 			squares = new Square[16];
	
	private JTextArea			console;
	
	public Start() 
	{
		setBackground(Color.LIGHT_GRAY);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 100, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 2.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// TEMPORAL TO GENERATE RANDOM SQUARES
		for(int i=0; i<16; i++)
		{
			squares[i] = new Square(Math.random() < 0.5, i);
		}
		
		JPanel panel_top = new JPanel();
		GridBagConstraints gbc_panel_top = new GridBagConstraints();
		gbc_panel_top.gridwidth = 2;
		gbc_panel_top.insets = new Insets(25, 0, 5, 0);
		gbc_panel_top.gridx = 0;
		gbc_panel_top.gridy = 1;
		add(panel_top, gbc_panel_top);
		panel_top.setLayout(new BorderLayout(0, 0));
		
		squares_panel = new SquaresPanel(squares);
		panel_top.add(squares_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Algoritmos", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		scrollPane.setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(25, 25, 25, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JScrollPane scrollPane_console = new JScrollPane();
		scrollPane_console.setOpaque(false);
		scrollPane_console.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Consola", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_scrollPane_console = new GridBagConstraints();
		gbc_scrollPane_console.insets = new Insets(25, 25, 25, 25);
		gbc_scrollPane_console.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_console.gridx = 1;
		gbc_scrollPane_console.gridy = 2;
		add(scrollPane_console, gbc_scrollPane_console);
		
		console = new JTextArea();
		console.setMargin(new Insets(5, 5, 5, 5));
		console.setForeground(Color.BLACK);
		console.setFont(new Font("Calibri", Font.PLAIN, 15));
		scrollPane_console.setViewportView(console);
		
		console.setText("Generated B&W Squares: ");
		for(int i=0; i<16; i++)
		{
			if(squares[i].isWhite())
			{
				console.append("[W] ");
			} else {
				console.append("[B] ");
			}
		}
		console.append("\n");
	}
	
	public static void main (String [] args)
	{
		Window.getInstance().setContainer(new Start());
		Window.getInstance().setVisible(true);
	}
}
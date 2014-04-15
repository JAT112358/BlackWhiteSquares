package gui;

import heuristic.HeuristicEvaluationFunction;

import javax.swing.JPanel;
import formulation.Environment;
import es.deusto.ingenieria.is.search.algorithms.blind.BreadthFS;
import es.deusto.ingenieria.is.search.algorithms.blind.DepthFS;
import es.deusto.ingenieria.is.search.algorithms.heuristic.BestFS;
import formulation.BWSProblem;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import utils.JFile;
import algorithms.AStar;
import algorithms.HillClimbing;
import components.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JCheckBox;

public class MainPanel extends JPanel {
	private static final long 	serialVersionUID = -5851058755580336316L;
	
	private SquaresPanel 		squares_panel;
	private JTextArea			console;
	private JList<String>		list_algorithms;
	private JCheckBox 			checkBoxLog;
	private BWSProblem			problem;
		
	public MainPanel(final BWSProblem problem) {			
		this.problem = problem;
		
		setBackground(Color.LIGHT_GRAY);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{180, 0, 0};
		gridBagLayout.rowHeights = new int[]{100, 50, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 2.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_top = new JPanel();
		panel_top.setOpaque(false);
		GridBagConstraints gbc_panel_top = new GridBagConstraints();
		gbc_panel_top.gridwidth = 2;
		gbc_panel_top.insets = new Insets(20, 25, 20, 25);
		gbc_panel_top.gridx = 0;
		gbc_panel_top.gridy = 0;
		add(panel_top, gbc_panel_top);
		panel_top.setLayout(new BorderLayout(0, 0));
		
		squares_panel = new SquaresPanel(((Environment) problem.gatherInitialPercepts()).getSquares());
		panel_top.add(squares_panel);
		
		JButton btnOpen = new JButton("Open...");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = JFile.getFile("XML file", "xml");
				if(file != null)
				{
					if(JFile.isValidXML(file, "label", "BWSquares"))
					{
						BWSProblem problem = new BWSProblem(file.getAbsolutePath());			
						problem.addInitialState(problem.gatherInitialPercepts());
						Window.getInstance().setContainer(new MainPanel(problem));
						Window.getInstance().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(Window.getInstance(), "Error select a velid XML file.", "Error", JOptionPane.ERROR_MESSAGE );
					}
				}
			}
		});
		btnOpen.setForeground(Color.BLACK);
		btnOpen.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.fill = GridBagConstraints.BOTH;
		gbc_btnOpen.insets = new Insets(0, 25, 10, 5);
		gbc_btnOpen.gridx = 0;
		gbc_btnOpen.gridy = 1;
		add(btnOpen, gbc_btnOpen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Algorithms", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		scrollPane.setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 25, 10, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		String [] algorithms = new String[]{" BreadthFS", " DepthFS", " BestFS", " A*", "Hill Climbing"};
		list_algorithms = new JList<String>(algorithms);
		list_algorithms.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = list_algorithms.locationToIndex(e.getPoint());
					switch(index) {
						case 0:
							MainPanel.this.problem.restart();
							MainPanel.this.squares_panel.restart();
							MainPanel.this.console.setText("");
							MainPanel.this.problem.solve(BreadthFS.getInstance(), console, squares_panel);
							break;
						case 1:
							MainPanel.this.problem.restart();
							MainPanel.this.squares_panel.restart();
							MainPanel.this.console.setText("");
							MainPanel.this.problem.solve(DepthFS.getInstance(), console, squares_panel);
							break;
						case 2:
							MainPanel.this.problem.restart();
							MainPanel.this.squares_panel.restart();
							MainPanel.this.console.setText("");
							MainPanel.this.problem.solve(new BestFS(new HeuristicEvaluationFunction()), console, squares_panel);
							break;
						case 3:
							MainPanel.this.problem.restart();
							MainPanel.this.squares_panel.restart();
							MainPanel.this.console.setText("");
							MainPanel.this.problem.solve(new AStar(new HeuristicEvaluationFunction()), console, squares_panel);
							break;
						case 4:
							MainPanel.this.problem.restart();
							MainPanel.this.squares_panel.restart();
							MainPanel.this.console.setText("");
							MainPanel.this.problem.solve(new HillClimbing(new HeuristicEvaluationFunction()), console, squares_panel);
							break;
					}
				}
			}
		});
		list_algorithms.setFont(new Font("Calibri", Font.PLAIN, 16));
		list_algorithms.setOpaque(false);
		scrollPane.setViewportView(list_algorithms);
		
		JScrollPane scrollPane_console = new JScrollPane();
		scrollPane_console.setOpaque(false);
		scrollPane_console.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		GridBagConstraints gbc_scrollPane_console = new GridBagConstraints();
		gbc_scrollPane_console.gridheight = 2;
		gbc_scrollPane_console.insets = new Insets(0, 20, 10, 25);
		gbc_scrollPane_console.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_console.gridx = 1;
		gbc_scrollPane_console.gridy = 1;
		add(scrollPane_console, gbc_scrollPane_console);
		
		console = new JTextArea();
		console.setEditable(false);
		console.setMargin(new Insets(5, 2, 5, 2));
		console.setOpaque(false);
		console.setForeground(Color.BLACK);
		console.setFont(new Font("Calibri", Font.PLAIN, 15));
		scrollPane_console.setViewportView(console);
		
		checkBoxLog = new JCheckBox("Create solution Log");
		checkBoxLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				problem.setLogEnable(checkBoxLog.isSelected());
			}
		});
		checkBoxLog.setFont(new Font("Calibri", Font.PLAIN, 16));
		GridBagConstraints gbc_checkBoxLog = new GridBagConstraints();
		gbc_checkBoxLog.anchor = GridBagConstraints.WEST;
		gbc_checkBoxLog.insets = new Insets(0, 20, 5, 0);
		gbc_checkBoxLog.gridx = 1;
		gbc_checkBoxLog.gridy = 3;
		add(checkBoxLog, gbc_checkBoxLog);
		
		JLabel lblSignature = new JLabel("Created by Jordan Aranda & Nerea Barqu\u00EDn");
		lblSignature.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblSignature = new GridBagConstraints();
		gbc_lblSignature.anchor = GridBagConstraints.EAST;
		gbc_lblSignature.insets = new Insets(0, 0, 15, 25);
		gbc_lblSignature.gridx = 1;
		gbc_lblSignature.gridy = 4;
		add(lblSignature, gbc_lblSignature);
		
		console.setText("Initial State: ");
		console.append(problem.gatherInitialPercepts().toString());
	}
}
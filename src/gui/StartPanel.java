package gui;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import components.Window;
import formulation.BWSProblem;
import utils.JFile;
import java.awt.Font;
import java.io.File;

public class StartPanel extends JPanel implements MouseListener {

	private static final long 	serialVersionUID = 8935308584205150223L;
	private JButton 			btnSearchFile;
	private JButton 			btnDefaultFile;
	private JLabel				lblDescription;
	
	public StartPanel() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTittle = new JLabel("Select an option");
		lblTittle.setForeground(Color.LIGHT_GRAY);
		lblTittle.setFont(new Font("Arial", Font.PLAIN, 50));
		GridBagConstraints gbc_lblTittle = new GridBagConstraints();
		gbc_lblTittle.gridwidth = 2;
		gbc_lblTittle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTittle.gridx = 0;
		gbc_lblTittle.gridy = 0;
		add(lblTittle, gbc_lblTittle);
		
		btnSearchFile = new JButton(new ImageIcon("img/open-file-icon.png"));
		btnSearchFile.addMouseListener(this);
		btnSearchFile.setFocusPainted(false);
		btnSearchFile.setContentAreaFilled(false);
		btnSearchFile.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		GridBagConstraints gbc_btnSearchFile = new GridBagConstraints();
		gbc_btnSearchFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearchFile.gridx = 0;
		gbc_btnSearchFile.gridy = 1;
		add(btnSearchFile, gbc_btnSearchFile);
		
		btnDefaultFile = new JButton(new ImageIcon("img/default-file-icon.png"));
		btnDefaultFile.addMouseListener(this);
		btnDefaultFile.setFocusPainted(false);
		btnDefaultFile.setContentAreaFilled(false);
		btnDefaultFile.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		GridBagConstraints gbc_btnDefaultFile = new GridBagConstraints();
		gbc_btnDefaultFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnDefaultFile.gridx = 1;
		gbc_btnDefaultFile.gridy = 1;
		add(btnDefaultFile, gbc_btnDefaultFile);
		
		lblDescription = new JLabel("Example");
		lblDescription.setForeground(Color.DARK_GRAY);
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.gridwidth = 2;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 2;
		add(lblDescription, gbc_lblDescription);
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == btnSearchFile) {
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
		} else if(e.getSource() == btnDefaultFile) {
			BWSProblem problem = new BWSProblem("data/blackwhitesquaresPartialpercepts1.xml");			
			problem.addInitialState(problem.gatherInitialPercepts());
			Window.getInstance().setContainer(new MainPanel(problem));
			Window.getInstance().setVisible(true);	
		}
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == btnSearchFile) {
			btnSearchFile.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
			lblDescription.setText("Open a file from other directory");
			lblDescription.setForeground(Color.LIGHT_GRAY);
		} else if(e.getSource() == btnDefaultFile) {
			btnDefaultFile.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
			lblDescription.setText("Open a default file");
			lblDescription.setForeground(Color.LIGHT_GRAY);
		}
	}

	public void mouseExited(MouseEvent e) {
		if(e.getSource() == btnSearchFile) {
			btnSearchFile.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
			lblDescription.setForeground(Color.DARK_GRAY);
		} else if(e.getSource() == btnDefaultFile) {
			btnDefaultFile.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
			lblDescription.setForeground(Color.DARK_GRAY);
		}
	}
}
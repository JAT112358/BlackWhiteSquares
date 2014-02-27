package components;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private static final long	serialVersionUID	= -8641413596663241575L;
	private static Window		instance;

	private Window()
	{
		super();
		setTitle("BlackWhiteSquares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 400));
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void setContainer(Container container)
	{
		setContentPane(container);
		((JPanel) getContentPane()).updateUI();
	}

	public static Window getInstance()
	{
		if (instance == null)
		{
			instance = new Window();
		}
		return instance;
	}
}
package utils;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import components.Window;

public class JFile {
	public static File getFile(final String description, final String ... extensions) {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File file = null;
		try {
			if (fileChooser.showOpenDialog(Window.getInstance()) == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				System.out.println(file.getAbsolutePath());
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return file;
	}
}
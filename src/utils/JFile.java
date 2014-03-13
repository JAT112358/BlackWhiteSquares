package utils;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	public static boolean isValidXML(File file, String label, String value) {
		Document dom;
		DocumentBuilder db;
		DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		try {
		  db = dbf.newDocumentBuilder();
		  dom = db.parse(file.getAbsolutePath());
		  Element rootElement = dom.getDocumentElement();
		  return rootElement.getAttribute(label).equals(value);
		} catch(Exception ex) {
			return false;
		}
	}
}
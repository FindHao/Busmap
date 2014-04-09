package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.Image;

/*The home of the project*/
public class Home extends JFrame{
	private static final long serialVersionUID = -4731380524981415485L;
	private JPanel mapPanel=new JPanel();
	private JLabel mapLabel;
	private ImageIcon map=new ImageIcon("/rs/");
	public Home() {
		setVisible(true);
	}
}

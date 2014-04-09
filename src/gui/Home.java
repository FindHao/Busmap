package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*The home of the project*/
public class Home extends JFrame{
	private static final long serialVersionUID = -4731380524981415485L;
	JPanel mapPanel = new JPanel();
	ImageIcon map=new ImageIcon("res/Map.jpg");
	JLabel mapLabel = new JLabel(map);

	
	public Home() {
		setSize(1200,600);
		getContentPane().setLayout(null);
		mapLabel.setBounds(0, 0, 1011, 518);
		mapPanel.setBounds(0, 0, 1011, 518);
		mapPanel.add(mapLabel);
		
		
		getContentPane().add(mapPanel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Home();
	}
}

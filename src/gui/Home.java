package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JButton;

import core.Dijkstra;

/*The home of the project*/
public class Home extends JFrame{
	private static final long serialVersionUID = -4731380524981415485L;
	JPanel mapPanel = new JPanel();
	ImageIcon map=new ImageIcon("res/Map.jpg");
	JLabel mapLabel = new JLabel(map);
	
	final int maxnode=200;
	JComboBox<Object>startSite;
	JComboBox<Object>endSite;
	JLabel lblFrom = new JLabel("From");
	JLabel lblTo = new JLabel("to");
	JPanel BottomPanel = new JPanel();
	JButton btnFind = new JButton("Find");
	Dijkstra dks=new Dijkstra();;
	public Home() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		setSize(1200,600);
		getContentPane().setLayout(null);
		mapLabel.setBounds(0, 0, 1011, 518);
		mapPanel.setBounds(0, 0, 1011, 518);
		mapPanel.add(mapLabel);
		
		getContentPane().add(mapPanel);
		
		JPanel ansPanel = new JPanel();
		ansPanel.setBounds(1016, 0, 158, 518);
		getContentPane().add(ansPanel);
		bottomPaneladd();
		getContentPane().add(BottomPanel);
		
		
		/**windows Destroy*/
		addWindowListener(new WindowAdapter() {
	        public void windowClosing (WindowEvent we) {
	        	int result = JOptionPane.showConfirmDialog(rootPane, "Are you sure to exit?",
						"Warning！！", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.NO_OPTION)
					return;
				System.exit(0);
	        }
	});
		setVisible(true);
	}
	void bottomPaneladd(){
		int lena=dks.nodelen;
		String startname[]=new String[lena];
		String endname[]=new String[lena];
		String a;
		for(int i=0;i<lena;i++){
			a=dks.node[i].getName();
			startname[i]=a;
			endname[i]=a;
		}
		startSite=new JComboBox<Object>(startname);
		startSite.setBounds(67, 6, 122, 21);
		endSite=new JComboBox<Object>(endname);
		endSite.setBounds(211, 6, 146, 21);
		BottomPanel.setBounds(70, 523, 434, 38);
		BottomPanel.setLayout(null);
		lblFrom.setBounds(10, 9, 47, 15);
		BottomPanel.add(lblFrom);
		BottomPanel.add(startSite);
		lblTo.setBounds(194, 9, 12, 15);
		BottomPanel.add(lblTo);
		BottomPanel.add(endSite);
		btnFind.setBounds(367, 5, 57, 23);
		btnFind.addActionListener(new ActionListener() {
			/**To find the way*/
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int []ans1=dks.work1(startSite.getSelectedIndex(), endSite.getSelectedIndex());
				for(int i=0;i<ans1.length;i++){
					System.out.println(ans1[i]);
				}
			}
		});
		BottomPanel.add(btnFind);
	}
	
	public static void main(String[] args) {
		new Home();
	}
}

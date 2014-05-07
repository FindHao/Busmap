package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import core.Dijkstra;
import core.MapPanel;
import core.Node;

/*The home of the project*/
public class Home extends JFrame{
	private static final long serialVersionUID = -4731380524981415485L;
	MapPanel mapPanel = new MapPanel();
	
	final int maxnode=200;
	JComboBox<Object>startSite;
	JComboBox<Object>endSite;
	JLabel lblFrom = new JLabel("From");
	JLabel lblTo = new JLabel("to");
	JPanel BottomPanel = new JPanel();
	JButton btnFind = new JButton("Find");
	JButton Find2 = new JButton("Find2");
	JTextArea ansText;
	Dijkstra dks=new Dijkstra();
	Node []node=dks.getNode();
	private final JLabel lblYourAnswer = new JLabel("Your answer:");
	public Home() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}  
		setLocation(50, 50);
		setResizable(false);
		setSize(1200,600);
		getContentPane().setLayout(null);
		mapPanel.setBounds(0, 0, 1011, 518);
		
		getContentPane().add(mapPanel);
		
		JPanel ansPanel = new JPanel();
		ansPanel.setBounds(1016, 0, 158, 518);
		getContentPane().add(ansPanel);
		ansPanel.setLayout(new BoxLayout(ansPanel, BoxLayout.Y_AXIS));
		lblYourAnswer.setFont(new Font("Consolas", Font.PLAIN, 14));
		ansPanel.add(lblYourAnswer);
		
		ansText = new JTextArea();
		ansText.setEditable(false);
		ansText.setLineWrap(true);
		ansPanel.add(ansText);
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
		BottomPanel.setBounds(70, 523, 574, 38);
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
//				show(dks.work1(startSite.getSelectedIndex(), endSite.getSelectedIndex()));
				//shortest time
				int []ans1=dks.work1(startSite.getSelectedIndex(), endSite.getSelectedIndex());
				show(ans1);
//				for(int i=0;i<=ans1[0]*2-1;i++){
//					System.out.println(ans1[i]);
//				}
			}
		});
		BottomPanel.add(btnFind);
		Find2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dks.work2(startSite.getSelectedIndex(), endSite.getSelectedIndex());
				
			}
		});
		Find2.setBounds(434, 5, 71, 23);
		BottomPanel.add(Find2);
	}
	public void show(int ans[]){
		int i=ans[0];
		int k=1;
		int j=i+1;
		ansText.setText("");
		while(j<i*2){
			ansText.append(("No."+ans[j]+" :"+node[ans[k++]].getName()+"->"));
			j++;
			while(ans[j]==ans[j-1]&&j<i*2){ansText.append(node[ans[k]].getName()+"->");j++;k++;}
			if(j<i*2||k<=i)ansText.append(""+node[ans[k]].getName());
			ansText.append("\n");
			
//			ansText.append(("No."+ans[j]+" :"+ans[k++]+"->"));
//			j++;
//			while(ans[j]==ans[j-1]&&j<i*2){ansText.append(ans[k]+"->");j++;k++;}
//			if(j<i*2||k<=i)ansText.append(""+ans[k]);
//			ansText.append("\n");
		}
		ansText.append("Full time "+new DecimalFormat("#.00").format(dks.getAnsTime())+"\n");
		int x1[]=new int[ans[0]+2];int y1[]=new int[ans[0]+2];int x2[]=new int[ans[0]+2];int y2[]=new int[ans[0]+2];
		for(int ii=1;ii<=ans[0];ii++){
			x1[ii]=node[ans[ii-1]].getX();y1[ii]=node[ans[ii-1]].getY();x2[ii]=node[ans[ii]].getX();y2[ii]=node[ans[ii]].getY();
		};
		mapPanel.update(mapPanel.getGraphics());
		mapPanel.drawline(mapPanel.getGraphics(), x1, y1, x2, y2, ans[0]);
	}
	public static void main(String[] args) {
		new Home();
	}
}

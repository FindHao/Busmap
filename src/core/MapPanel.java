package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1510078527819840606L;

//	public void paintComponent(Graphics g){ 
//		  super.paintComponent(g);
//		  try{
//			  Image image=ImageIO.read(new File("res/Map2.jpg"));
//		   g.drawImage(image, 0, 0, null);
//		  }catch(Exception e){
//		   e.printStackTrace();  
//		  }
//	}
	public void paint(Graphics g){
		try{
			super.paint(g);
			  Image image=ImageIO.read(new File("res/Map2.jpg"));
		   g.drawImage(image, 0, 0, null);
		  }catch(Exception e){
		   e.printStackTrace();  
		  }
	}
	
	public void drawline(Graphics g,int x1[],int y1[],int x2[],int y2[],int len){
		float lineWidth = 5.0f;
		g.setColor(Color.RED); 
	      ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
		for(int ii=2;ii<=len;ii++){
//			((Graphics2D)g).drawLine(x1[ii]+12,y1[ii]+30,x2[ii]+12,y2[ii]+30);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
//				e.printStackTrace();
				System.out.println("thread error");
			}
			((Graphics2D)g).drawLine(x1[ii],y1[ii],x2[ii],y2[ii]);
		}
	}
}

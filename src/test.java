import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import gui.PreShow;

import javax.swing.JFrame;  
import javax.swing.UIManager;  
  
/** 
 * 
 * @author 杨胜寒 
 */  
public class test {  
  
    public static void main(String args[]) {  
        try {  
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) {  
        }  
        //初始化闪屏Dialog时指定闪屏图片  
        final PreShow splashWindow = new PreShow();  
        //启动一个线程来加载数据  
        new Thread() {  
            @Override  
            public void run() {  
                try {  
                    for (int i = 0; i < 10; i++) {  
                        splashWindow.updateProcess("正在进行第" + i + "次缓存数据加载. . .", i * 9);  
                        Thread.sleep(300);  
                    }  
                } catch (InterruptedException ex) {  
                    //异常不做处理  
                }  
                JFrame window = new JFrame();  
                splashWindow.updateProcess("正在启动主窗体. . .", 100);  
                moveToScreenCenter(window);  
                splashWindow.setVisible(false);  
                //数据加载完成，显示主窗体  
                window.setVisible(true);  
                //释放资源  
                splashWindow.dispose();  
            }  
        }.start();  
        //显示闪屏Dialog  
        splashWindow.setVisible(true);  
    }  
    public static final void moveToScreenCenter(Component comp) {   
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width/2;
        int centerY = screenSize.height/2;
        Point point = new Point(centerX, centerY);
        comp.setLocation(point);
      }
}  
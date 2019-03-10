package tankwar;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

public class TankClient extends Frame {

     public void lunchFrame(){
         this.setLocation(200,100);
         this.setSize(800,600);
         this.setVisible(true);
         this.addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
                 System.exit(0);
             }
         });
         this.setResizable(false);
         this.setTitle("坦克大战");
         this.setBackground(Color.GREEN);
         new Thread(new PaintThread()).start();
     }
    private int x = 50;
    private int y =  50;
    @Override
    public void paint(Graphics g) {
         Color c = g.getColor();
         g.setColor(Color.RED);
         g.fillOval(x,y,30,30);
         g.setColor(c);
        super.paint(g);
    }

    public static void main(String []args){
         TankClient tc = new TankClient();
         tc.lunchFrame();
     }
     private class PaintThread implements Runnable {
         /**
          * 内部类可以访问外部类的方法
          */
         @Override
         public void run() {
            while (true){
                y += 5;
                x += 5;
                repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
         }
     }
}

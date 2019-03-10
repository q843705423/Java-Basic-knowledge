package tankwar;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

public class TankClient extends Frame {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    Tank myTank = new Tank(40,50);
     public void lunchFrame(){
         int tankX = 200;
         this.setLocation(tankX,100);
         this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
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
         this.addKeyListener(new KeyMonitor());
     }
    Image offScreenImage = null;
    @Override
    public void paint(Graphics g) {
        myTank.draw(g);
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(SCREEN_WIDTH,SCREEN_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.GREEN);
        gOffScreen.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0, null);
    }

    public static void main(String []args){
         TankClient tc = new TankClient();
         tc.lunchFrame();
     }
     private class PaintThread implements Runnable {
         @Override
         public void run() {
            while (true){
                repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
         }
     }
     private class KeyMonitor extends KeyAdapter{
         @Override
         public void keyPressed(KeyEvent e) {
             myTank.keyPressed(e);
         }
     }

}

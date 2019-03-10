package tankwar;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

public class TankClient extends Frame {
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;

     public void lunchFrame(){
         this.setLocation(200,100);
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
     }
    private int x = 50;
    private int y =  50;
    Image offScreenImage = null;
    @Override
    public void paint(Graphics g) {
         Color c = g.getColor();
         g.setColor(Color.RED);
         g.fillOval(x,y,30,30);
         g.setColor(c);
        y += 5;
        x += 5;
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
}

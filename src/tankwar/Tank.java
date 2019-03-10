package tankwar;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x,y;
    private boolean isLeftPress =false, isRightPress = false, isUpPress  = false, isDownPress = false;
    enum Direction{L,R,U,D,LU,LD,RU,RD,STOP}
    private Direction direction = Direction.STOP;
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,30,30);
        g.setColor(c);
    }
    public static final int xSpeed = 5;
    public static final int ySpeed = 5;
    void move(){
        switch (direction){
            case L:
                x -= xSpeed;
               break;
            case LU:
                x -= xSpeed;
                y -= ySpeed;
                break;
            case U:
                y -= ySpeed;
                break;
            case RU:
                y -= ySpeed;
                x += xSpeed;
                break;
            case R:
                x += xSpeed;
                break;
            case RD:
                x += xSpeed;
                y += ySpeed;
                break;
            case D:
                y += ySpeed;
                break;
            case LD:
                x -= xSpeed;
                y += ySpeed;
                break;
            case STOP:
                break;
        }
    }
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                isLeftPress = true;
                break;
            case KeyEvent.VK_UP:
                isUpPress =  true;
                break;
            case KeyEvent.VK_RIGHT:
                isRightPress = true;
                break;
            case KeyEvent.VK_DOWN:
                isDownPress = true;
                break;
             }
    }
    void lcateDirection()
}

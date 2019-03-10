package tankwar;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;
    private int x,y;
    private boolean isLeftPress =false, isRightPress = false, isUpPress  = false, isDownPress = false;
    TankClient tc;

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                isLeftPress = false;
                break;
            case KeyEvent.VK_DOWN:
                isDownPress = false;
                break;
            case KeyEvent.VK_UP:
                isUpPress = false;
                break;
            case KeyEvent.VK_RIGHT:
                isRightPress = false;
                break;
        }
    }

    public Tank(int x, int y, TankClient tc) {
        this.x = x;
        this.y = y;
        this.tc = tc;
    }

    enum Direction{L,R,U,D,LU,LD,RU,RD,STOP}
    private Direction direction = Direction.STOP;
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y, WIDTH, HEIGHT);
        g.setColor(c);
        drawGunBarrel(g);
    }
    private Tank.Direction gunBarrelDir = Tank.Direction.L;
    public static final int xSpeed = 5;
    public static final int ySpeed = 5;
    private void drawGunBarrel(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        switch (gunBarrelDir){
            case L:
                g.drawLine(x + Tank.WIDTH / 2 ,y + Tank.HEIGHT / 2, x , y + Tank.HEIGHT / 2);
               break;
            case LU:
                g.drawLine(x + Tank.WIDTH  / 2 ,y + Tank.HEIGHT  / 2, x , y );
                break;
            case U:
                g.drawLine(x + Tank.WIDTH  / 2 ,y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2 , y );
                break;
            case RU:
                g.drawLine(x + Tank.WIDTH  / 2 ,y + Tank.HEIGHT / 2, x + Tank.WIDTH  , y + Tank.HEIGHT );
                break;
            case R:
                g.drawLine(x + Tank.WIDTH  / 2 ,y + Tank.HEIGHT / 2, x + Tank.WIDTH  , y);
                break;
            case RD:
                g.drawLine(x + Tank.WIDTH  / 2 ,y + Tank.HEIGHT / 2, x + Tank.WIDTH  , y + Tank.HEIGHT);
                break;
            case D:
                g.drawLine(x + Tank.WIDTH  / 2 ,y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2 , y + Tank.HEIGHT);
                break;
            case LD:
                g.drawLine(x + Tank.WIDTH  / 2 ,y + Tank.HEIGHT / 2, x , y + Tank.HEIGHT);
                break;
        }
        g.setColor(c);
    }
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
        if(this.direction!= Tank.Direction.STOP){
            this.gunBarrelDir = this.direction;
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
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        locateCalculate();
        move();
    }

    private Missile fire() {
        Missile missile = new Missile(x,y,gunBarrelDir,tc);
        tc.missileList.add(missile);
        return missile;
    }

    void locateCalculate(){
        if( isLeftPress && isUpPress){
            direction = Direction.LU;
        }else if( isLeftPress && isDownPress ){
            direction = Direction.LD;
        }else if( isRightPress && isUpPress ){
            direction = Direction.RU;
        }else if( isRightPress && isDownPress ){
            direction = Direction.RD;
        }else if(isLeftPress){
            direction = Direction.L;
        }else if(isRightPress){
            direction = Direction.R;
        }else if(isDownPress){
            direction = Direction.D;
        }else if(isUpPress){
            direction = Direction.U;
        }else {
            direction = Direction.STOP;
        }

    }
}

package tankwar;

import java.awt.*;

public class Missile {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private TankClient tc;
    private int x,y;
    private Tank.Direction dir;

    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Missile(int x, int y, Tank.Direction dir,TankClient tc) {
        this.x = x + Tank.WIDTH/2 - Missile.WIDTH/2;
        this.y = y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
        this.dir = dir;
        this.tc = tc;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.black);
        g.fillOval(x,y, WIDTH, HEIGHT);
        g.setColor(c);
        move();
    }



    private int xSpeed = 10;
    private int ySpeed = 10;
    void move(){
        switch (dir){
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
        if(x < 0 || y < 0 || x > TankClient.SCREEN_WIDTH || y > TankClient.SCREEN_HEIGHT){
            live = false;
            tc.missileList.remove(this);
        }
    }

}

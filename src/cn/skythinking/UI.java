package cn.skythinking;

import javax.swing.*;
import java.awt.*;

import java.util.Arrays;

public class UI {
    public static void main(String []args){
        JFrame f=new JFrame();
        f.setSize(1400,900);
        Mypanl p=new Mypanl();
        f.add(p);
        f.setVisible(true);
    }

}
class Mypanl extends JPanel {
    private final int BOX_WIDTH = 60;
    private final int BOX_HEIGHT = 60;
    private final int CHECKER_BOARD_WIDTH = BOX_WIDTH * 8;
    private final int CHECKER_BOARD_HEIGHT = BOX_HEIGHT * 9;
    private final int MARGIN_LEFT = 40;
    private final int MARGIN_TOP = 40;
    private final int signDistance = 6;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.WHITE);
        g.setColor(Color.BLACK);
        g.drawRect(MARGIN_LEFT,MARGIN_TOP,CHECKER_BOARD_WIDTH,CHECKER_BOARD_HEIGHT);
        for(int i = 0 ; i < 9 ; i ++){
            g.drawLine(MARGIN_LEFT,MARGIN_TOP + i * BOX_HEIGHT,MARGIN_LEFT + CHECKER_BOARD_WIDTH, MARGIN_TOP + i * BOX_HEIGHT);
        }
        for(int i = 1 ; i < 9 ; i++){
            g.drawLine(MARGIN_LEFT + i * BOX_WIDTH,MARGIN_TOP,MARGIN_LEFT + i * BOX_WIDTH ,MARGIN_TOP + 4 * BOX_HEIGHT);
            g.drawLine(MARGIN_LEFT + (i) * BOX_WIDTH,MARGIN_TOP + 5 * BOX_HEIGHT,MARGIN_LEFT + (i  ) * BOX_WIDTH ,MARGIN_TOP + 9 * BOX_HEIGHT);
        }
       // (3,0) (5,2)
        //(3,2) (5,0)
        //(3,9) (5,7)
        //(3,7) (5,9)
        drawLineInBox(g,3,0,5,2);
        drawLineInBox(g,3,2,5,0);
        drawLineInBox(g,3,9,5,7);
        drawLineInBox(g,3,7,5,9);

        drawSign(g,0,3,false,true);
        drawSign(g,2,3,true,true);
        drawSign(g,4,3,true,true);
        drawSign(g,6,3,true,true);
        drawSign(g,8,3,true,false);

        drawSign(g,0,6,false,true);
        drawSign(g,2,6,true,true);
        drawSign(g,4,6,true,true);
        drawSign(g,6,6,true,true);
        drawSign(g,8,6,true,false);

        drawSign(g,1,2,true,true);
        drawSign(g,7,2,true,true);
        drawSign(g,1,7,true,true);
        drawSign(g,7,7,true,true);
        drawPiece(g,0,0,"车",Color.RED);
        drawPiece(g,0,1,"马",Color.BLACK);
    }
    public void drawLineInBox(Graphics g,int x_box_begin, int y_box_begin, int x_box_end, int y_box_end){
        g.drawLine(MARGIN_LEFT+ BOX_WIDTH * x_box_begin,MARGIN_TOP + y_box_begin * BOX_HEIGHT,MARGIN_LEFT + x_box_end * BOX_WIDTH,MARGIN_TOP + y_box_end * BOX_HEIGHT);
    }
    public void drawPiece(Graphics g, int x, int y, String name,Color color){
        g.setColor(Color.orange);
        g.fillOval(MARGIN_LEFT + x * BOX_WIDTH - BOX_WIDTH / 2,MARGIN_TOP + y * BOX_HEIGHT - BOX_HEIGHT / 2,BOX_WIDTH,BOX_HEIGHT);
        Font font = new Font("", Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(color);
        g.drawString(name,MARGIN_LEFT+ x * BOX_WIDTH,MARGIN_TOP + y * BOX_HEIGHT);
    }
    public void drawSign(Graphics g,int x,int y,boolean showLeft,boolean showRight){
        java.util.List<SignReset>list = Arrays.asList(
                new SignReset(-1,-1,-1,-2,showLeft),
                new SignReset(-1,-1,-2,-1,showLeft),
                new SignReset(-1,1,-1,2,showLeft),
                new SignReset(-1,1,-2,1,showLeft),
                new SignReset(1,-1,1,-2,showRight),
                new SignReset(1,-1,2,-1,showRight),
                new SignReset(1,1,1,2,showRight),
                new SignReset(1,1,2,1,showRight)

        );
        list.forEach((signReset -> {
            if(signReset.draw)
            g.drawLine(MARGIN_LEFT + x * BOX_WIDTH + signReset.x1 * signDistance, MARGIN_TOP + y * BOX_HEIGHT + signReset.y1 * signDistance ,
                    MARGIN_LEFT + x * BOX_WIDTH + signDistance * signReset.x2 , MARGIN_TOP + y * BOX_HEIGHT + signDistance * signReset.y2);
        }));
        //
    }
    class SignReset{

        public SignReset(int x1, int y1, int x2, int y2,boolean draw) {
            this.draw = draw;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public boolean draw;
        public int x1;
        public int y1;
        public int x2;
        public int y2;
    }
}


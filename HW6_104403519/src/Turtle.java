/*** 
 * @Author 侯凱翔
 * 學號: 104403519
 * 系級: 資管3A
 * HW6: 水族箱
***/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Turtle extends JLabel implements Runnable, MouseListener {

    private int x, y, r1 = 0, r, index,s; ////index為判斷自己在List裡的index,r為方向(左右),r1為不同大小,s為速度
    private ImageIcon[][] img
            = {{new ImageIcon(this.getClass().getResource("Img\\w2.png")), new ImageIcon(this.getClass().getResource("Img\\w2a.png"))},
            {new ImageIcon(this.getClass().getResource("Img\\w.png")), new ImageIcon(this.getClass().getResource("Img\\wa.png"))}};
    boolean dirFlag = true, stopFlag = false;
    private Timer t1;
    private Random rand = new Random();

    public Turtle(int x, int y) {
        this.x = x - 50;
        this.y = y - 50;
        r = rand.nextInt(2);
        if (r == 1) { //先隨機給個方向
            this.dirFlag = false;
        }
        this.setIcon(img[r][r1 = rand.nextInt(2)]); //隨機給大小
        this.setBounds(x, y, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
        addMouseListener(this);
    }

    @Override
    public void run() {
        t1 = new Timer(rand.nextInt(100) + 100, new ActionListener() { //set timer
            @Override
            public void actionPerformed(ActionEvent e) {
                if (y == 590) { //已經到地板
                    if (rand.nextInt(10) == 1) { //隨機轉向(左右)
                        if (r == 1) {
                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
                            r = 0;
                            Turtle.this.setIcon(img[r][r1]);
                        } else {
                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
                            r = 1;
                            Turtle.this.setIcon(img[r][r1]);
                        }
                    }
                    if (Turtle.this.dirFlag) {
                    	s = rand.nextInt(40)+10; //隨機給個速度
                        if ((x - s) > 0) { //是否撞壁
                            x -= s;
                        } else {
                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
                            r = 1;
                            Turtle.this.setIcon(img[r][r1]);
                            x += s;
                        }
                        Turtle.this.setLocation(x, y);
                    } else {
                    	s = rand.nextInt(40)+10; //隨機給個速度
                        if ((x + Turtle.this.getIcon().getIconWidth() + 20) < 800) { //是否撞壁
                            x += s;
                        } else {
                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
                            r = 0;
                            Turtle.this.setIcon(img[r][r1]);
                            x -= s;
                        }
                        Turtle.this.setLocation(x, y);
                    }
                } else { //尚未觸底，慢慢下降到地
                    y += 10;
                    if (y > 590) { //到底就停
                        y = 590;
                    }
                    Turtle.this.setLocation(x, y);
                }
            }

        });
        t1.start();
    }

    public Timer getTimer() {
        return t1;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

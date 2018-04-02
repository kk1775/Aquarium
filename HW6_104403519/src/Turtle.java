/*** 
 * @Author �J�͵�
 * �Ǹ�: 104403519
 * �t��: ���3A
 * HW6: ���ڽc
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

    private int x, y, r1 = 0, r, index,s; ////index���P�_�ۤv�bList�̪�index,r����V(���k),r1�����P�j�p,s���t��
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
        if (r == 1) { //���H�����Ӥ�V
            this.dirFlag = false;
        }
        this.setIcon(img[r][r1 = rand.nextInt(2)]); //�H�����j�p
        this.setBounds(x, y, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
        addMouseListener(this);
    }

    @Override
    public void run() {
        t1 = new Timer(rand.nextInt(100) + 100, new ActionListener() { //set timer
            @Override
            public void actionPerformed(ActionEvent e) {
                if (y == 590) { //�w�g��a�O
                    if (rand.nextInt(10) == 1) { //�H����V(���k)
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
                    	s = rand.nextInt(40)+10; //�H�����ӳt��
                        if ((x - s) > 0) { //�O�_����
                            x -= s;
                        } else {
                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
                            r = 1;
                            Turtle.this.setIcon(img[r][r1]);
                            x += s;
                        }
                        Turtle.this.setLocation(x, y);
                    } else {
                    	s = rand.nextInt(40)+10; //�H�����ӳt��
                        if ((x + Turtle.this.getIcon().getIconWidth() + 20) < 800) { //�O�_����
                            x += s;
                        } else {
                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
                            r = 0;
                            Turtle.this.setIcon(img[r][r1]);
                            x -= s;
                        }
                        Turtle.this.setLocation(x, y);
                    }
                } else { //�|��Ĳ���A�C�C�U����a
                    y += 10;
                    if (y > 590) { //�쩳�N��
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

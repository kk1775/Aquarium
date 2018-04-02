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

public class Fish extends JLabel implements Runnable,MouseListener{
    private int x,y,r1,r,h,s,index; //index���P�_�ۤv�bFishList�̪�index,r����V(���k),r1�����P���ءB�j�p,h���W�U,s���t��
    private ImageIcon[][] img = 
        {{new ImageIcon(this.getClass().getResource("Img\\2.png")), new ImageIcon(this.getClass().getResource("Img\\4.png")), new ImageIcon(this.getClass().getResource("Img\\6.png")), new ImageIcon(this.getClass().getResource("Img\\8.png")), new ImageIcon(this.getClass().getResource("Img\\10.png")), new ImageIcon(this.getClass().getResource("Img\\12.png")), new ImageIcon(this.getClass().getResource("Img\\14.png")), new ImageIcon(this.getClass().getResource("Img\\16.png")), new ImageIcon(this.getClass().getResource("Img\\18.png"))},
         {new ImageIcon(this.getClass().getResource("Img\\1.png")), new ImageIcon(this.getClass().getResource("Img\\3.png")), new ImageIcon(this.getClass().getResource("Img\\5.png")), new ImageIcon(this.getClass().getResource("Img\\7.png")), new ImageIcon(this.getClass().getResource("Img\\9.png")),  new ImageIcon(this.getClass().getResource("Img\\11.png")), new ImageIcon(this.getClass().getResource("Img\\13.png")), new ImageIcon(this.getClass().getResource("Img\\15.png")), new ImageIcon(this.getClass().getResource("Img\\17.png"))}};
    boolean dirFlag = true; //�P�_���k
    boolean updownFlag = true; //�P�_�W�U
    private Timer t1;
    private Random rand = new Random();
    public Fish(int x,int y){
       this.x = x-50;
       this.y = y-50;
       r = rand.nextInt(2);
       h = rand.nextInt(2);
       if(r == 1){
           this.dirFlag = false; //�H������V(���k)
       }
       if(h == 1){
    	   this.updownFlag = false; //�H������V(�W�U)
       }
       this.setIcon(img[r][r1 = rand.nextInt(9)]); //�H���X�{���ءB�j�p
       this.setBounds(x, y, this.getIcon().getIconWidth(), this.getIcon().getIconHeight()); //���o���i�Ϥ����j�p
       addMouseListener(this);
    }

    @Override
    public void run() {
        t1 = new Timer(rand.nextInt(100)+100, new ActionListener(){ //timer�@�q�ɶ����Ӱʧ@
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rand.nextInt(10)==1){ //�H�����k��V
                    if(r==1){
                        Fish.this.dirFlag = !Fish.this.dirFlag;
                        r=0;
                        Fish.this.setIcon(img[r][r1]);
                    }else{
                        Fish.this.dirFlag = !Fish.this.dirFlag;
                        r = 1;
                        Fish.this.setIcon(img[r][r1]);
                    }
                }
                if(rand.nextInt(10)==1){ //�H���W�U��V
                	if(h==1){
                		Fish.this.updownFlag = !Fish.this.updownFlag;
                		h=0;
                	}else{
                		Fish.this.updownFlag = !Fish.this.updownFlag;
                		h=1;
                	}
                }
                
                if(Fish.this.dirFlag){ //��directFlag�M�w��V(���k) �쥻�V��
                	s = rand.nextInt(40)+10; //�H�����ӳt��
                	if((x-s) > 0){ //�ݬO�_����
                		x-=s;
                	}else{ //�����n�ϼu
                		Fish.this.dirFlag = !Fish.this.dirFlag;
	                    r = 1; //�Ϥ����V
	                    Fish.this.setIcon(img[r][r1]);
	                    x+=s;
	                }
	                Fish.this.setLocation(x,y);
                }
                else{ //�쥻�V�k
                	s = rand.nextInt(40)+10; //�H�����ӳt��
	                if((x+Fish.this.getIcon().getIconWidth()) < 790){
	                    x+=s;
	                }else{
	                    Fish.this.dirFlag = !Fish.this.dirFlag;
	                    r=0;
	                    Fish.this.setIcon(img[r][r1]);
	                    x-=s;
	                }
	                Fish.this.setLocation(x,y);
                }
                if(Fish.this.updownFlag){ //��updownFlag�M�w��V(�W�U)
                	s = rand.nextInt(20)+10; //�H�����ӳt��
                        if((y-s) > 0){ //�O�_���쳻
                            y-=s;
                        }else{
                            Fish.this.updownFlag = !Fish.this.updownFlag;
                            y+=s;
                        }
                }else{
                	s = rand.nextInt(20)+10; //�H�����ӳt��
                        if((y+s+Fish.this.getIcon().getIconHeight()) < 650){ //�O�_Ĳ��
                            y+=s;
                        }else{
                            Fish.this.updownFlag = !Fish.this.updownFlag;
                            y-=s;
                        }
                }
                 Fish.this.setLocation(x, y);
            }
        });
        t1.start(); //�Ұ�Timer
    }
    public Timer getTimer(){
        return t1;
    }
    public void setIndex(int i){
        this.index = i ;
    }
    public int getIndex(){
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

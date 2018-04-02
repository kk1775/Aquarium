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

public class Fish extends JLabel implements Runnable,MouseListener{
    private int x,y,r1,r,h,s,index; //index為判斷自己在FishList裡的index,r為方向(左右),r1為不同魚種、大小,h為上下,s為速度
    private ImageIcon[][] img = 
        {{new ImageIcon(this.getClass().getResource("Img\\2.png")), new ImageIcon(this.getClass().getResource("Img\\4.png")), new ImageIcon(this.getClass().getResource("Img\\6.png")), new ImageIcon(this.getClass().getResource("Img\\8.png")), new ImageIcon(this.getClass().getResource("Img\\10.png")), new ImageIcon(this.getClass().getResource("Img\\12.png")), new ImageIcon(this.getClass().getResource("Img\\14.png")), new ImageIcon(this.getClass().getResource("Img\\16.png")), new ImageIcon(this.getClass().getResource("Img\\18.png"))},
         {new ImageIcon(this.getClass().getResource("Img\\1.png")), new ImageIcon(this.getClass().getResource("Img\\3.png")), new ImageIcon(this.getClass().getResource("Img\\5.png")), new ImageIcon(this.getClass().getResource("Img\\7.png")), new ImageIcon(this.getClass().getResource("Img\\9.png")),  new ImageIcon(this.getClass().getResource("Img\\11.png")), new ImageIcon(this.getClass().getResource("Img\\13.png")), new ImageIcon(this.getClass().getResource("Img\\15.png")), new ImageIcon(this.getClass().getResource("Img\\17.png"))}};
    boolean dirFlag = true; //判斷左右
    boolean updownFlag = true; //判斷上下
    private Timer t1;
    private Random rand = new Random();
    public Fish(int x,int y){
       this.x = x-50;
       this.y = y-50;
       r = rand.nextInt(2);
       h = rand.nextInt(2);
       if(r == 1){
           this.dirFlag = false; //隨機給方向(左右)
       }
       if(h == 1){
    	   this.updownFlag = false; //隨機給方向(上下)
       }
       this.setIcon(img[r][r1 = rand.nextInt(9)]); //隨機出現魚種、大小
       this.setBounds(x, y, this.getIcon().getIconWidth(), this.getIcon().getIconHeight()); //取得那張圖片的大小
       addMouseListener(this);
    }

    @Override
    public void run() {
        t1 = new Timer(rand.nextInt(100)+100, new ActionListener(){ //timer一段時間做個動作
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rand.nextInt(10)==1){ //隨機左右轉向
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
                if(rand.nextInt(10)==1){ //隨機上下轉向
                	if(h==1){
                		Fish.this.updownFlag = !Fish.this.updownFlag;
                		h=0;
                	}else{
                		Fish.this.updownFlag = !Fish.this.updownFlag;
                		h=1;
                	}
                }
                
                if(Fish.this.dirFlag){ //用directFlag決定方向(左右) 原本向左
                	s = rand.nextInt(40)+10; //隨機給個速度
                	if((x-s) > 0){ //看是否撞壁
                		x-=s;
                	}else{ //撞壁要反彈
                		Fish.this.dirFlag = !Fish.this.dirFlag;
	                    r = 1; //圖片改方向
	                    Fish.this.setIcon(img[r][r1]);
	                    x+=s;
	                }
	                Fish.this.setLocation(x,y);
                }
                else{ //原本向右
                	s = rand.nextInt(40)+10; //隨機給個速度
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
                if(Fish.this.updownFlag){ //用updownFlag決定方向(上下)
                	s = rand.nextInt(20)+10; //隨機給個速度
                        if((y-s) > 0){ //是否撞到頂
                            y-=s;
                        }else{
                            Fish.this.updownFlag = !Fish.this.updownFlag;
                            y+=s;
                        }
                }else{
                	s = rand.nextInt(20)+10; //隨機給個速度
                        if((y+s+Fish.this.getIcon().getIconHeight()) < 650){ //是否觸底
                            y+=s;
                        }else{
                            Fish.this.updownFlag = !Fish.this.updownFlag;
                            y-=s;
                        }
                }
                 Fish.this.setLocation(x, y);
            }
        });
        t1.start(); //啟動Timer
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

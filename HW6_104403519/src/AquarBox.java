/*** 
 * @Author 侯凱翔
 * 學號: 104403519
 * 系級: 資管3A
 * HW6: 水族箱
***/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AquarBox extends JFrame {

    private JPanel FishPanel = new JPanel();
    private int f = 0, t = 0; //魚數量&烏龜數量
    private BtuPanel ButtonList = new BtuPanel() {
        @Override
        public void actionPerformed(ActionEvent e) { //Override按鈕功能
            int i;
            if (e.getSource() == AddFish) {
                ButtonList.NowStatus.setText("目前功能：新增魚");
                nowTool = 1;
            }
            if (e.getSource() == RemoveSel) { //移除選取的物件
                ButtonList.NowStatus.setText("目前功能：移除選取");
                if (nowTool == 3) {
                    for (i = 0; i < SelFishList.size(); i++) {
                        FThreadList.get(SelFishList.get(i)).interrupt(); //中斷魚
                        FThreadList.remove(SelFishList.get(i)); //從threadList中刪掉
                        FishPanel.remove(FishList.get(SelFishList.get(i))); //從水族箱中刪掉
                        FishList.remove(SelFishList.get(i)); //從魚的List刪掉
                    }
                    f -= SelFishList.size(); //一隻一隻刪
                    SelFishList.clear();
                    ButtonList.FishNum.setText("魚數量：" + f);
                    for (i = 0; i < SelTurtleList.size(); i++) {
                        TThreadList.get(SelTurtleList.get(i)).interrupt(); //中斷烏龜
                        TThreadList.remove(SelTurtleList.get(i)); //從threadList中刪除
                        FishPanel.remove(TurtleList.get(SelTurtleList.get(i))); //從水族箱刪除
                        FishList.remove(SelTurtleList.get(i)); //從烏龜的List刪掉
                    }
                    t -= SelTurtleList.size(); //一隻一隻刪
                    SelTurtleList.clear();
                    ButtonList.TurtleNum.setText("烏龜數量：" + t);

                    FishPanel.repaint(); //重畫水族箱的panel

                }
            }
            if (e.getSource() == AddTur) {
                ButtonList.NowStatus.setText("目前功能：新增烏龜");
                nowTool = 4;
            }
            if (e.getSource() == RemoveAll) { //移除全部 並清空ArrayList
                ButtonList.NowStatus.setText("目前功能：移除全部");
                nowTool = 0;
                FishPanel.removeAll();
                FishPanel.repaint();
                for (i = FThreadList.size() - 1; i >= 0; i--) {
                    FThreadList.get(i).interrupt();
                }
                for (i = TThreadList.size() - 1; i >= 0; i--) {
                    TThreadList.get(i).interrupt();
                }
                //以下全部清空
                FishList.clear();
                TurtleList.clear();
                FThreadList.clear();
                TThreadList.clear();
                SelFishList.clear();
                SelTurtleList.clear();
                f = 0;
                t = 0;
                ButtonList.FishNum.setText("魚數量：0");
                ButtonList.TurtleNum.setText("烏龜數量：0");
            }
        }
    };
    int nowTool = 0;
    private ArrayList<Fish> FishList = new ArrayList<Fish>(); //存魚
    private ArrayList<Turtle> TurtleList = new ArrayList<Turtle>(); //存烏龜
    private ArrayList<Thread> FThreadList = new ArrayList<Thread>(); //存魚的thread
    private ArrayList<Thread> TThreadList = new ArrayList<Thread>(); //存烏龜的Thread
    private ArrayList<Integer> SelFishList = new ArrayList<Integer>(); //存選取的魚
    private ArrayList<Integer> SelTurtleList = new ArrayList<Integer>(); //存取選取的烏龜

    public AquarBox() {
        super("JAVA水族箱");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        FishPanel.setBackground(new Color(137, 192, 255));
        FishPanel.setLayout(null);
        add(ButtonList, BorderLayout.NORTH);
        add(FishPanel, BorderLayout.CENTER);

        MouseHandler moshandler = new MouseHandler();
        FishPanel.addMouseListener(moshandler);
        FishPanel.addMouseMotionListener(moshandler);
    }

    public class MouseHandler implements MouseListener,
            MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (nowTool == 1) { //新增魚的方法
                FishList.add(new Fish(e.getX(), e.getY()) {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        boolean isSel = false; //用來判斷物件是否已被選取
                        int SelIndex = 0;
                        nowTool = 3;
                        for (int i = 0; i < SelFishList.size(); i++) {
                            if (SelFishList.get(i) == this.getIndex()) {
                                isSel = true;
                                SelIndex = i;
                            }
                        }
                        if (isSel) { //被選取
                            SelFishList.remove(SelIndex);
                        } else { //未選取
                            SelFishList.add(this.getIndex());
                        }

                        ButtonList.NowStatus.setText("目前功能：選取中...");

                    }

                });
                FishPanel.add(FishList.get(FishList.size() - 1)); //把魚加到水族箱panel
                FishList.get(FishList.size() - 1).setIndex(FishList.size() - 1); //把這條魚加進魚的ArrayList
                FThreadList.add(new Thread(FishList.get(FishList.size() - 1))); //把這條魚的Thread丟進魚的ThreadList
                FThreadList.get(FThreadList.size() - 1).start(); //啟動

                f++;
                ButtonList.FishNum.setText("魚數量：" + f); //status印出
            }
            if (nowTool == 4) { //新增烏龜
                TurtleList.add(new Turtle(e.getX(), e.getY()) {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        boolean isSel = false;
                        int SelIndex = 0;
                        nowTool = 3;
                        for (int i = 0; i < SelTurtleList.size(); i++) {
                            if (SelTurtleList.get(i) == this.getIndex()) {
                                isSel = true;
                                SelIndex = i;
                            }
                        }
                        if (isSel) {
                            SelTurtleList.remove(SelIndex);
                        } else {
                            SelTurtleList.add(this.getIndex());
                        }

                        ButtonList.NowStatus.setText("目前功能：選取中...");

                    }

                });
                FishPanel.add(TurtleList.get(TurtleList.size() - 1)); //把烏龜加到水族箱panel
                TurtleList.get(TurtleList.size() - 1).setIndex(TurtleList.size() - 1); //把這隻龜丟進烏龜的ArrayList
                TThreadList.add(new Thread(TurtleList.get(TurtleList.size() - 1))); //把這隻烏龜的thread加進烏龜的ThreadList
                TThreadList.get(TThreadList.size() - 1).start(); //啟動

                t++;
                ButtonList.TurtleNum.setText("烏龜數量：" + t); //Status印出
            }

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

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

    }
}

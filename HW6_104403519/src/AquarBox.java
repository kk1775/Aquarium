/*** 
 * @Author �J�͵�
 * �Ǹ�: 104403519
 * �t��: ���3A
 * HW6: ���ڽc
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
    private int f = 0, t = 0; //���ƶq&�Q�t�ƶq
    private BtuPanel ButtonList = new BtuPanel() {
        @Override
        public void actionPerformed(ActionEvent e) { //Override���s�\��
            int i;
            if (e.getSource() == AddFish) {
                ButtonList.NowStatus.setText("�ثe�\��G�s�W��");
                nowTool = 1;
            }
            if (e.getSource() == RemoveSel) { //�������������
                ButtonList.NowStatus.setText("�ثe�\��G�������");
                if (nowTool == 3) {
                    for (i = 0; i < SelFishList.size(); i++) {
                        FThreadList.get(SelFishList.get(i)).interrupt(); //���_��
                        FThreadList.remove(SelFishList.get(i)); //�qthreadList���R��
                        FishPanel.remove(FishList.get(SelFishList.get(i))); //�q���ڽc���R��
                        FishList.remove(SelFishList.get(i)); //�q����List�R��
                    }
                    f -= SelFishList.size(); //�@���@���R
                    SelFishList.clear();
                    ButtonList.FishNum.setText("���ƶq�G" + f);
                    for (i = 0; i < SelTurtleList.size(); i++) {
                        TThreadList.get(SelTurtleList.get(i)).interrupt(); //���_�Q�t
                        TThreadList.remove(SelTurtleList.get(i)); //�qthreadList���R��
                        FishPanel.remove(TurtleList.get(SelTurtleList.get(i))); //�q���ڽc�R��
                        FishList.remove(SelTurtleList.get(i)); //�q�Q�t��List�R��
                    }
                    t -= SelTurtleList.size(); //�@���@���R
                    SelTurtleList.clear();
                    ButtonList.TurtleNum.setText("�Q�t�ƶq�G" + t);

                    FishPanel.repaint(); //���e���ڽc��panel

                }
            }
            if (e.getSource() == AddTur) {
                ButtonList.NowStatus.setText("�ثe�\��G�s�W�Q�t");
                nowTool = 4;
            }
            if (e.getSource() == RemoveAll) { //�������� �òM��ArrayList
                ButtonList.NowStatus.setText("�ثe�\��G��������");
                nowTool = 0;
                FishPanel.removeAll();
                FishPanel.repaint();
                for (i = FThreadList.size() - 1; i >= 0; i--) {
                    FThreadList.get(i).interrupt();
                }
                for (i = TThreadList.size() - 1; i >= 0; i--) {
                    TThreadList.get(i).interrupt();
                }
                //�H�U�����M��
                FishList.clear();
                TurtleList.clear();
                FThreadList.clear();
                TThreadList.clear();
                SelFishList.clear();
                SelTurtleList.clear();
                f = 0;
                t = 0;
                ButtonList.FishNum.setText("���ƶq�G0");
                ButtonList.TurtleNum.setText("�Q�t�ƶq�G0");
            }
        }
    };
    int nowTool = 0;
    private ArrayList<Fish> FishList = new ArrayList<Fish>(); //�s��
    private ArrayList<Turtle> TurtleList = new ArrayList<Turtle>(); //�s�Q�t
    private ArrayList<Thread> FThreadList = new ArrayList<Thread>(); //�s����thread
    private ArrayList<Thread> TThreadList = new ArrayList<Thread>(); //�s�Q�t��Thread
    private ArrayList<Integer> SelFishList = new ArrayList<Integer>(); //�s�������
    private ArrayList<Integer> SelTurtleList = new ArrayList<Integer>(); //�s��������Q�t

    public AquarBox() {
        super("JAVA���ڽc");
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

            if (nowTool == 1) { //�s�W������k
                FishList.add(new Fish(e.getX(), e.getY()) {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        boolean isSel = false; //�ΨӧP�_����O�_�w�Q���
                        int SelIndex = 0;
                        nowTool = 3;
                        for (int i = 0; i < SelFishList.size(); i++) {
                            if (SelFishList.get(i) == this.getIndex()) {
                                isSel = true;
                                SelIndex = i;
                            }
                        }
                        if (isSel) { //�Q���
                            SelFishList.remove(SelIndex);
                        } else { //�����
                            SelFishList.add(this.getIndex());
                        }

                        ButtonList.NowStatus.setText("�ثe�\��G�����...");

                    }

                });
                FishPanel.add(FishList.get(FishList.size() - 1)); //�⳽�[����ڽcpanel
                FishList.get(FishList.size() - 1).setIndex(FishList.size() - 1); //��o�����[�i����ArrayList
                FThreadList.add(new Thread(FishList.get(FishList.size() - 1))); //��o������Thread��i����ThreadList
                FThreadList.get(FThreadList.size() - 1).start(); //�Ұ�

                f++;
                ButtonList.FishNum.setText("���ƶq�G" + f); //status�L�X
            }
            if (nowTool == 4) { //�s�W�Q�t
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

                        ButtonList.NowStatus.setText("�ثe�\��G�����...");

                    }

                });
                FishPanel.add(TurtleList.get(TurtleList.size() - 1)); //��Q�t�[����ڽcpanel
                TurtleList.get(TurtleList.size() - 1).setIndex(TurtleList.size() - 1); //��o���t��i�Q�t��ArrayList
                TThreadList.add(new Thread(TurtleList.get(TurtleList.size() - 1))); //��o���Q�t��thread�[�i�Q�t��ThreadList
                TThreadList.get(TThreadList.size() - 1).start(); //�Ұ�

                t++;
                ButtonList.TurtleNum.setText("�Q�t�ƶq�G" + t); //Status�L�X
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

/*** 
 * @Author �J�͵�
 * �Ǹ�: 104403519
 * �t��: ���3A
 * HW6: ���ڽc
***/
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BtuPanel extends JPanel implements ActionListener{
    JButton AddFish = new JButton("�s�W��");
    JButton AddTur = new JButton("�s�W�Q�t");
    JButton RemoveSel = new JButton("�������");
    JButton RemoveAll = new JButton("��������");
    JPanel Row1 = new JPanel(); //�Ĥ@�檺���s(�s�W���B�������)
    JPanel Row2 = new JPanel(); //�ĤG�檺���s(�s�W�Q�t�B��������)
    JPanel Row3 = new JPanel(); //��Status�B�X�����B�Q�t
    JLabel NowStatus = new JLabel();
    JLabel FishNum = new JLabel();
    JLabel TurtleNum = new JLabel();
    
    public BtuPanel(){
        super(new GridLayout(4,2));
        setBackground(new Color(77, 90, 109));
        Row1.setLayout(new GridLayout(0,2));
        Row2.setLayout(new GridLayout(0,2));
        Row3.setLayout(new GridLayout(0,3));
        Row1.add(AddFish);
        AddFish.addActionListener(this);
        Row1.add(RemoveSel);
        RemoveSel.addActionListener(this);
        Row2.add(AddTur);
        AddTur.addActionListener(this);
        Row2.add(RemoveAll);
        RemoveAll.addActionListener(this);

        NowStatus.setText("�ثe�\��G");
        FishNum.setText("���ƶq�G0");
        TurtleNum.setText("�Q�t�ƶq�G0");
        NowStatus.setForeground(new Color(80, 235, 252));
        FishNum.setForeground(new Color(80, 235, 252));
        TurtleNum.setForeground(new Color(80, 235, 252));
        Row3.add(NowStatus);
        Row3.add(FishNum);
        Row3.add(TurtleNum);
        Row3.setBackground(new Color(77, 90, 109));
        add(Row1);
        add(Row2);
        add(Row3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}

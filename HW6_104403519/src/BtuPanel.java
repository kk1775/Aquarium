/*** 
 * @Author 侯凱翔
 * 學號: 104403519
 * 系級: 資管3A
 * HW6: 水族箱
***/
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BtuPanel extends JPanel implements ActionListener{
    JButton AddFish = new JButton("新增魚");
    JButton AddTur = new JButton("新增烏龜");
    JButton RemoveSel = new JButton("移除選取");
    JButton RemoveAll = new JButton("移除全部");
    JPanel Row1 = new JPanel(); //第一行的按鈕(新增魚、移除選取)
    JPanel Row2 = new JPanel(); //第二行的按鈕(新增烏龜、移除全部)
    JPanel Row3 = new JPanel(); //放Status、幾條魚、烏龜
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

        NowStatus.setText("目前功能：");
        FishNum.setText("魚數量：0");
        TurtleNum.setText("烏龜數量：0");
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

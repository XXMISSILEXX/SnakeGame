import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameFrame extends JFrame implements ActionListener{
    JButton btnStart;
    JLabel L1;
    JButton SubmitNameButton;
    JMenuItem About=new JMenuItem("About");
    JLabel ten = new JLabel("Name");
    GameFrame () {
        this.setContentPane(new JLabel(new ImageIcon("sng.jpg")));
        this.L1 = new JLabel();
        this.add(L1);
        this.setSize(500,500);
        this.btnStart = new JButton();
        this.btnStart.setBounds(200,300,100,50);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(btnStart);
        this.setLocationRelativeTo(null);
        this.btnStart.setText("Start");
        this.btnStart.addActionListener(this);
        this.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==About){
            JFrame jf=new JFrame();
            jf.setSize(1000,600);
            jf.setVisible(true);
            jf.setTitle("About");
            jf.setLayout(null);
            jf.setLocationRelativeTo(null);

            JLabel jLabel1 = new JLabel();
            jLabel1.setText("Thành viên trong nhóm");
            jLabel1.setBounds(120,50,500,30);
            jLabel1.setFont(new Font("Serif",Font.PLAIN,20));
            jf.add(jLabel1);
            JLabel jLabel2 = new JLabel();
            jLabel2.setText("Trần Trung Hiếu - 201210128");
            jLabel2.setBounds(120,75,500,30);
            jLabel2.setFont(new Font("Serif",Font.PLAIN,20));
            jf.add(jLabel2);
            JLabel jLabel3 = new JLabel();
            jLabel3.setText("Lê Hồng Phong - 201200271");
            jLabel3.setBounds(120,100,500,30);
            jLabel3.setFont(new Font("Serif",Font.PLAIN,20));
            jf.add(jLabel3);
            JLabel jLabel = new JLabel("<html><h3 style='color:black'>Thông tin về game</h1><p>Qua nghiên cứu và tìm hiểu ta nhận thấy game Rắn săn mồi là game cổ điện xuất hiện vào năm 1997 trên Nokia 6610 là những ô vuông xếp liên tiếp nhau di chuyển trên một màn hình màu xanh đơn giản, nhưng Rắn săn mồi đã xây dựng rất thành công tên tuổi của mình. Với 400 triệu bản được xuất xưởng và đến hiện tại đã là phiên bản thứ 8. Game có sự hấp đẫn đối với người chơi. Do đó nhóm em quyết định xây dựng game dựa trên những ý tưởng từ xưa cũ nhưng chưa bao giờ là mất đi chất của tựa game dã tạo nên tuổi thơ của một thế hệ. ");
            jLabel.setBounds(120,115,500,400);
            jLabel.setFont(new Font("Serif",Font.PLAIN,20));
            jf.add(jLabel);
        }
        if (e.getSource()==btnStart) {
            this.setVisible(false);
            JFrame jFrame = new JFrame();
            jFrame.add(new GamePanel());
            jFrame.setTitle("Snake");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setResizable(false);
            jFrame.pack();
            jFrame.setVisible(true);
            jFrame.setLocationRelativeTo(null);

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenu HelpMenu = new JMenu("Help");
            JMenu LeaderBoardMenu = new JMenu("LeaderBoard");

            About.addActionListener(this);
            HelpMenu.add(About);
            menuBar.add(fileMenu);
            menuBar.add(HelpMenu);
            menuBar.add(LeaderBoardMenu);
            jFrame.setJMenuBar(menuBar);
        }
    }
}

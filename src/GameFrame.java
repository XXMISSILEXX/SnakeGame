import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener{
    JButton btnStart;
    JLabel L1;
    JButton resetButton;
    JMenuItem About=new JMenuItem("About");
    GameFrame () {
        this.setContentPane(new JLabel(new ImageIcon("sng.jpg")));
//        this.setLayout(new FlowLayout());
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
            jf.setSize(500,500);
            jf.setVisible(true);
            jf.setTitle("About");
            jf.setLayout(null);
            jf.setLocationRelativeTo(null);
//            jf.setContentPane(new JLabel("Tran Trung Hieu-2012101208Le Hong Phong-201200271"));
//            JLabel j1=new JLabel("Tran Trung Hieu-2012101208");
//            jf.add(j1);
            JLabel label=new JLabel();
            label.setText("Tran Trung Hieu");
            label.setAlignmentX(0);
            label.setAlignmentY(0);
            label.setForeground(Color.red);
            jf.add(label);
            
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

            JMenuBar menuBar=new JMenuBar();
            JMenu fileMenu =new JMenu("File");
            JMenu HelpMenu =new JMenu("Help");
            JMenu LeaderBoardMenu =new JMenu("LeaderBoard");


            About.addActionListener(this);

            HelpMenu.add(About);
            menuBar.add(fileMenu);
            menuBar.add(HelpMenu);
            menuBar.add(LeaderBoardMenu);
            jFrame.setJMenuBar(menuBar);

        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener{
    JButton btnStart;
    JLabel L1;
    JButton resetButton;
    GameFrame () {
        this.setContentPane(new JLabel(new ImageIcon("sng.jpg")));
        this.setLayout(new FlowLayout());
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
//        this.add(new GamePanel());
//        this.setTitle("Snake");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
//        this.pack();
//        this.setVisible(true);
//        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
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
        }
    }
}

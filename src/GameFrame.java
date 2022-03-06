import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener{
    JButton btnStart;
    JLabel L1;
    GameFrame () {
        setContentPane(new JLabel(new ImageIcon("sng.jpg")));
        setLayout(new FlowLayout());
        L1 = new JLabel();
        add(L1);
        setSize(500,500);
        btnStart = new JButton();
        btnStart.setBounds(200,300,100,50);
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500,500);
        setVisible(true);
        add(btnStart);
        setLocationRelativeTo(null);
        btnStart.setText("Start");
        btnStart.addActionListener(this);
        setResizable(false);
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

import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class GameFrame extends JFrame implements ActionListener{
    JButton btnStart;
    JLabel L1;
    JButton SubmitNameButton;
    JMenuItem About=new JMenuItem("About");
    JMenuItem ShowLeaderBoard = new JMenuItem("Show Leader Board");
    JLabel ten = new JLabel("Name");
    String url = "jdbc:mysql://localhost:3306/playersnake";
    String user = "root";
    String password = "";
    GameFrame () {
        this.setContentPane(new JLabel(new ImageIcon("sng.jpg")));
        this.L1 = new JLabel();
        this.add(L1);
        this.setSize(500,500);
        this.btnStart = new JButton();
        this.btnStart.setBounds(200,300,100,50);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu HelpMenu = new JMenu("Help");
        JMenu LeaderBoardMenu = new JMenu("LeaderBoard");
        HelpMenu.add(About);

        About.addActionListener(this);
        LeaderBoardMenu.add(ShowLeaderBoard);
        ShowLeaderBoard.addActionListener(this);
        menuBar.add(fileMenu);
        menuBar.add(HelpMenu);
        menuBar.add(LeaderBoardMenu);
        this.setJMenuBar(menuBar);

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

//            JMenuBar menuBar = new JMenuBar();
//            JMenu fileMenu = new JMenu("File");
//            JMenu HelpMenu = new JMenu("Help");
//            JMenu LeaderBoardMenu = new JMenu("LeaderBoard");
//            HelpMenu.add(About);
//            About.addActionListener(this);
//            LeaderBoardMenu.add(ShowLeaderBoard);
//            ShowLeaderBoard.addActionListener(this);
//            menuBar.add(fileMenu);
//            menuBar.add(HelpMenu);
//            menuBar.add(LeaderBoardMenu);
//            jFrame.setJMenuBar(menuBar);
        }
        if (e.getSource()==ShowLeaderBoard) {
            String []data = new String[100];
            var sql = "select * from playersnakegame ORDER BY Diem DESC";
            try (Connection conn = DriverManager.getConnection(url,user,password)) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                JTable jTable = new JTable();
                jTable.removeAll();
                String [] arr = {"Ten","Diem"};
                DefaultTableModel model = new DefaultTableModel(arr,0);
                String []Colum = {"Name Player","Score"};
                model.addRow(Colum);
                while (resultSet.next()) {
                    Vector vec = new Vector();
                    vec.add(resultSet.getString("Ten"));
                    vec.add(resultSet.getString("Diem"));
                    model.addRow(vec);
                }
                jTable.setModel(model);
                JFrame leaderBoard = new JFrame("Leader Board");
                leaderBoard.add(jTable);
                leaderBoard.setVisible(true);
                leaderBoard.setSize(600,600);
                leaderBoard.setLocationRelativeTo(null);
                leaderBoard.setResizable(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(e.getSource()==About){
            JFrame jf=new JFrame();
            jf.setContentPane(new JLabel(new ImageIcon("SnakeGameAboutbackground.jpg")));
            jf.setSize(1000,600);
            jf.setVisible(true);
            jf.setTitle("About");
            jf.setLayout(null);
            jf.setLocationRelativeTo(null);
            jf.setResizable(false);

            JLabel jLabel1 = new JLabel();
            jLabel1.setText("Th??nh vi??n trong nh??m");
            jLabel1.setBounds(120,50,500,30);
            jLabel1.setFont(new Font("Serif",Font.PLAIN,20));
            jLabel1.setForeground(Color.yellow);
            jf.add(jLabel1);
            JLabel jLabel2 = new JLabel();
            jLabel2.setText("Tr???n Trung Hi???u - 201210128");
            jLabel2.setBounds(120,75,500,30);
            jLabel2.setFont(new Font("Serif",Font.PLAIN,20));
            jLabel2.setForeground(Color.yellow);
            jf.add(jLabel2);
            JLabel jLabel3 = new JLabel();
            jLabel3.setText("L?? H???ng Phong - 201200271");
            jLabel3.setBounds(120,100,500,30);
            jLabel3.setFont(new Font("Serif",Font.PLAIN,20));
            jLabel3.setForeground(Color.yellow);
            jf.add(jLabel3);
            JLabel jLabel = new JLabel("<html><h3 style='color:yellow'>Th??ng tin v??? game</h1><p>Qua nghi??n c???u v?? t??m hi???u ta nh???n th???y game R???n s??n m???i l?? game c??? ??i???n xu???t hi???n v??o n??m 1997 tr??n Nokia 6610 l?? nh???ng ?? vu??ng x???p li??n ti???p nhau di chuy???n tr??n m???t m??n h??nh m??u xanh ????n gi???n, nh??ng R???n s??n m???i ???? x??y d???ng r???t th??nh c??ng t??n tu???i c???a m??nh. V???i 400 tri???u b???n ???????c xu???t x?????ng v?? ?????n hi???n t???i ???? l?? phi??n b???n th??? 8. Game c?? s??? h???p ?????n ?????i v???i ng?????i ch??i. Do ???? nh??m em quy???t ?????nh x??y d???ng game d???a tr??n nh???ng ?? t?????ng t??? x??a c?? nh??ng ch??a bao gi??? l?? m???t ??i ch???t c???a t???a game d?? t???o n??n tu???i th?? c???a m???t th??? h???. ");
            jLabel.setBounds(120,115,500,400);
            jLabel.setFont(new Font("Serif",Font.PLAIN,20));
            jLabel.setForeground(Color.red);
            jf.add(jLabel);
        }
    }
}

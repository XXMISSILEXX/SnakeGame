import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 1280;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 30;
    static final int GAME_UNIT = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;//tong so o cua ma tran
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNIT];
    final int y[] = new int[GAME_UNIT];
    int bodyParts = 6; //chieu dai ran ban dau
    int applesEaten;//so tao da an
    int appleX;//toa do cua tao theo truc x
    int appleY;//toa do cua tao theo truc y
    char direction = 'R';//huong di cua ran
    boolean running = false;//check chuong trinh dang chay hay dung
    Timer timer;
    Random random;
    JButton SubmitNameButton;
    String url = "jdbc:mysql://localhost:3306/PlayerSnake";
    String user = "root";
    String password = "";
    String sql = "INSERT INTO playersnakegame (Ten,Diem) VALUES (?,?);";
    JTextField InsertName;
    JFrame jFrame;
    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        StartGame();
    }
    public void StartGame () {
        playMusic("GTASAN.wav");
        newApple();
        running=true;
        timer=new Timer(DELAY,this); // để chạy chương trình với delay 75
        timer.start();
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g); // dùng để reset khung hình khi rắn di chuyển
        draw(g);
    }
    public void draw (Graphics g) {
        if (running) {
//            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
//                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
//                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++)
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                g.setColor(Color.red);
                g.setFont(new Font("Ink Free",Font.BOLD,40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score : "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score : "+applesEaten))/2,g.getFont().getSize());
        }
        else {
            InsertNameframe();
            gameover(g);
        }
    }
    public void InsertNameframe () {
        jFrame = new JFrame();
        jFrame.setSize(300,250);
        jFrame.setLayout(null);
        JLabel NamePlayer = new JLabel("Player's Name");
        NamePlayer.setBounds(15,30,150,30);
        jFrame.add(NamePlayer);
        InsertName = new JTextField();
        InsertName.setBounds(110,30,150,30);
        jFrame.add(InsertName);
        SubmitNameButton = new JButton("Submit");
        SubmitNameButton.setBounds(100,100,100,50);
        SubmitNameButton.addActionListener(this);
        jFrame.add(SubmitNameButton);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    public void newApple() {
        appleX=  random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY=  random.nextInt((int)SCREEN_HEIGHT/ UNIT_SIZE)*UNIT_SIZE;
    }
    public void move (){
        for (int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction) {
            case 'U' :
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D' :
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L' :
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R' :
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    public void checkApple(){
        if ((x[0] == appleX)&& (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisons(){
        // rắn đâm vào thân
        for (int i=bodyParts;i>0;i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // đầu rắn đâm vào tường bên trái
        if (x[0] < 0) {
            running = false;
        }
        // đầu rắn đâm vào tường bên phải
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        // đầu rắn đâm vào tường phía trên
        if (y[0] < 0) {
            running = false;
        }
        // đầu rắn đâm vào tường phía dưới
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }
    public void InsertMySql () {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,InsertName.getText());
            preparedStatement.setInt(2, applesEaten);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void gameover(Graphics g){
        //Score
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics1= getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten,(SCREEN_WIDTH -metrics1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());

        //Game Over
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2= getFontMetrics(g.getFont());
        g.drawString("Game Over",(SCREEN_WIDTH -metrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);

        //Press Enter to restart game
        g.setColor(Color.green);
        g.drawString("Press Enter to restart game",(SCREEN_WIDTH -metrics2.stringWidth("Press Enter to restart game")-120),SCREEN_HEIGHT-200);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(running){
            move();
            checkApple();
            checkCollisons();
            repaint();// dùng để reset khung hình khi chương trình chạy
        }
        if (e.getSource()==SubmitNameButton) {
            if (InsertName.getText().isEmpty()) {
                JOptionPane checkEmpty = new JOptionPane();
                checkEmpty.showMessageDialog(jFrame,"Player's Name did not empty");
            }
            else {
            InsertMySql();
            jFrame.setVisible(false);
            }
        }
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction='L';
                    }
                    break;
                    case KeyEvent.VK_RIGHT:
                        if(direction != 'L') {
                            direction='R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(direction != 'D') {
                            direction='U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(direction != 'U') {
                            direction='D';
                        }
                        break;
                case KeyEvent.VK_ENTER:
                    if(!running){
                        running= true;
                        applesEaten=0;
                        bodyParts=6;
                        for (int i = 0; i < bodyParts; i++)
                        {
                            x[i]=0;
                            y[i]=0;
                        }
                        direction ='R';
                        timer.start();
                        move();
                        checkApple();
                        checkCollisons();
                    }
                    break;
                }
        }
    }
    public void playMusic(String musiclocation){
        try{
            File musicPath = new File(musiclocation);
            if(musicPath.exists()){
                AudioInputStream audioInput= AudioSystem.getAudioInputStream(musicPath);
                Clip clip=AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else{
                System.out.println("Cannot find the Audio File");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

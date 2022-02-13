import javax.swing.JFrame;

public class FrameScreen extends JFrame {
    GameScreen gameScreen;
    public FrameScreen () {
        setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        gameScreen = new GameScreen();
        add(gameScreen);
    }

    public static void main(String[] args) {
        FrameScreen f = new FrameScreen();
        f.setVisible(true);
        f.setSize(500,500);
    }
}

import java.awt.*;
import javax.swing.JPanel;
public class GameScreen extends JPanel implements Runnable {
    private int [][] bgr = new int[20][20];
    Thread thread;
    PaintSnake PSnake;
    public GameScreen ()
    {
        PSnake = new PaintSnake();
        thread = new Thread(this);
        thread.start();
    }
    public void run () {
        while (true) {
            PSnake.update();
            repaint();
            try {
                thread.sleep(20);
            }catch (InterruptedException e) {}
        }
    }
    public void paintBgr (Graphics g) {
        for (int i=0;i<20;i++) {
            for (int j=0;j<20;j++) {
                g.fillRect(i*20+1,j*20+1,18,18);
            }
        }
    }
    public void paint (Graphics g) {
        paintBgr(g);
        PSnake.paintSnake(g)   ;
    }
}

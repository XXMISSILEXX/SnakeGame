import java.awt.*;

public class PaintSnake {
    private int doDai = 1;
    private int []x;
    private int []y;
    public PaintSnake () {
        x = new int[20];
        y = new int[20];
        x[0] = 5;
        y[0] = 4;
        double t1 = 0;
    }
    public void update () {
        double t1 = 0;
        if (System.currentTimeMillis()-t1 > 1000) {
            x[0]++;
            t1 = System.currentTimeMillis();
        }
    }
    public void paintSnake (Graphics g) {
        g.setColor(Color.RED);
        for (int i=0;i<doDai;i++) {
            g.fillRect(x[i]*20+1,y[i]*20+1,18,18);
        }
    }
}

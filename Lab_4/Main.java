import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Main extends JPanel {

    double theta = 0;
    double deltaTheta = Math.PI / 90;
    int x = 0;
    int deltaX = 1;

    public Main() {
        Timer timer = new Timer(10, e -> {
            theta += deltaTheta;
            x += deltaX;
            if (x > getWidth() - 100 || x < 0) {
                deltaX *= -1;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(x + 50, getHeight() / 2);
        g2d.rotate(theta);
        g2d.drawLine(-120, 3, 120, 3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Main(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
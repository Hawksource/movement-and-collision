import javax.swing.*;
import java.awt.*;

public class mainWindow implements Runnable{
    public static void main(String args[]) {
        graphics graphics = new graphics();
        JFrame frame = new JFrame("Game Window");
        frame.setSize(1200,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setFocusTraversalKeysEnabled(false);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.add(graphics);
        frame.addKeyListener(graphics);
        Thread g = new Thread(graphics);
        g.start();

    }
    public void run() {
        System.out.println("mainWindow run");
    }
}

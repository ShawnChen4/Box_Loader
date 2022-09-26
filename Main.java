import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Main {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int DELAY = 10;
    
    static JFrame frame;
    static GraphicsPanel canvas;
    static Box selectedBox;

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.run();
        Main driver = new Main();
        boolean running = true;
        while (running) {
            
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            frame.repaint();
        }
    }

    Main() {
        frame = new JFrame("Recieving System");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new GraphicsPanel();
        frame.add(canvas);
        frame.setVisible(true);
        
        selectedBox = new Box(200,200,200,200,200,200,200, new Color(0, 0, 0));
    }

    class GraphicsPanel extends JPanel {
        GraphicsPanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setDoubleBuffered(true);
            
            if (selectedBox != null) {
                selectedBox.draw(g);
            }
        }
    }
}

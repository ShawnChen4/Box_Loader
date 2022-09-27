import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;

class InputGraphics {
    final int WIDTH = 800;
    final int HEIGHT = 600;
    final int DELAY = 10;
    
    JFrame frame;
    GraphicsPanel canvas;
    InputBox selectedBox;
    ArrayList<Box> boxes = new ArrayList<Box>();
    
    public ArrayList<Box> run() {
        InputGraphics gui = new InputGraphics();
        boolean running = true;
        while (running) {
            
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            frame.repaint();
        }
        return boxes;
    }
    
    InputGraphics() {
        frame = new JFrame("Recieving System");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new GraphicsPanel();
        frame.add(canvas);
        frame.setVisible(true);
        
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
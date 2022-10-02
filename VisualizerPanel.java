import java.awt.Graphics;
import javax.swing.JPanel;

class VisualizerPanel extends JPanel {
    
    private InputBox box;
    
    VisualizerPanel() {
        setFocusable(true);
        requestFocusInWindow();
    }
    
    public void selectBox(InputBox box) {
        this.box = box;
    }

    public void paintComponent(Graphics g) {
        if (box != null) {
            box.draw(g);
        }
    }
}

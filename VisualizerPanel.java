import java.awt.Graphics;
import javax.swing.JPanel;

class VisualizerPanel extends JPanel {
    VisualizerPanel() {
        setFocusable(true);
        requestFocusInWindow();
    }

    public void paintComponent(Graphics g) {
        g.drawString("visualization panel", 20, 20);
    }
}

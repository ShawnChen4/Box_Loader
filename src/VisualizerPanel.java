package src;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Visualization panel used to visualize boxes for the
 * input graphical user interface.
 */
public class VisualizerPanel extends JPanel {
    
    private InputBox box;
    
    VisualizerPanel() {
        setFocusable(true);
        requestFocusInWindow();
    }
    
    /**
     * Change the focus of the visualizer
     * @param box The new focus of the visualizer
     */
    public void selectBox(InputBox box) {
        this.box = box;
    }

    /**
     * Paints the box which the visualizer is focusing on
     * @param g A Graphics object.
     */
    public void paintComponent(Graphics g) {
        if (box != null) {
            box.draw(g);
        }
    }
}

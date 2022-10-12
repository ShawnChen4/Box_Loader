import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Visualization panel used to visualize boxes for the
 * input graphical user interface.
 */
class VisualizerPanel extends JPanel {
    
    private Drawable obj;
    
    VisualizerPanel() {
        setFocusable(true);
        requestFocusInWindow();
    }
    
    /**
     * Change the focus of the visualizer
     * @param box The new focus of the visualizer
     */
    public void select(Drawable obj) {
        this.obj = obj;
    }

    /**
     * Paints the box which the visualizer is focusing on
     * @param g A Graphics object.
     */
    public void paintComponent(Graphics g) {
        if (obj != null) {
            obj.draw(g);
        }
    }
}

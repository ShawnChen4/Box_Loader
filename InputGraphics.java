import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

class InputGraphics {
    final int WIDTH = 800;
    final int HEIGHT = 600;
    final int DELAY = 10;
    
    private final int FORM_PANEL_HEIGHT = 1;
    private final int VISUALIZATION_PANEL_HEIGHT = 1;
    
    private JTextField lengthInput = new JTextField(10);
    private JTextField widthInput = new JTextField(10);
    private JTextField heightInput = new JTextField(10);
    private JTextField weightInput = new JTextField(10);
    
    JFrame frame;
    InputBox selectedBox;
    ArrayList<Box> boxes = new ArrayList<Box>();
    
    public ArrayList<Box> run() {
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
        frame.setLayout(new GridLayout(0, 2, 10, 10));
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 0, 10));
        
        formPanel.add(new JLabel("Length"));
        formPanel.add(lengthInput);
        formPanel.add(new JLabel("Width"));
        formPanel.add(widthInput);
        formPanel.add(new JLabel("Height"));
        formPanel.add(heightInput);
        formPanel.add(new JLabel("Weight"));
        formPanel.add(weightInput);
        
        frame.getContentPane().add(formPanel, BorderLayout.WEST);
        
        // Visualization panel
        JPanel visualizationPanel = new JPanel();
        visualizationPanel.setLayout(new GridLayout(VISUALIZATION_PANEL_HEIGHT, 2));
        visualizationPanel.add(new VisualizerPanel());
        frame.getContentPane().add(visualizationPanel, BorderLayout.WEST);
        
        frame.pack();
        frame.setVisible(true);
    }
}

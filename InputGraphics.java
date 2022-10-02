import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JDialog;
import javax.swing.JComponent;

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
    private JTextField idInput = new JTextField(10);
    private JButton colorButton = new JButton("Color");
    private JButton submitButton = new JButton("Submit");
    
    JPanel formPanel, visualizationPanel;
    
    private Color color;
    private JDialog dialog;
    private VisualizerPanel visualizer = new VisualizerPanel();
    
    JColorChooser colorChooser;
    
    JFrame frame;
    InputBox selectedBox;
    ArrayList<Box> boxes = new ArrayList<Box>();
    
    public ArrayList<Box> run() {
        boolean running = true;
        visualizer.selectBox(new InputBox(10,10,10,10,10,new Color(20,20,20)));
        while (running) {
            
            // submit();
            
            System.out.println(color);
            
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            frame.repaint();
        }
        return boxes;
    }
    
    public void submit() {
        float length = Float.parseFloat(lengthInput.getText());
        float width = Float.parseFloat(lengthInput.getText());
        float height = Float.parseFloat(heightInput.getText());
        System.out.printf("%d %d %d\n", length, width, height);
    }
    
    public void addItem(JPanel panel, JComponent component,
                        int x, int y, int pady, int gridwidth) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x;
        c.gridy = y;
        c.ipady = pady;
        c.gridwidth = gridwidth;
        panel.add(component, c);
    }
    
    InputGraphics() {
        // boiler plate
        frame = new JFrame("Recieving System");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2, 10, 10));
        
        // Form panel
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        
        // Form text fields

        addItem(formPanel, new JLabel("Length"), 0, 0, 0, 1);
        addItem(formPanel, lengthInput, 1, 0, 0, 1);
        addItem(formPanel, new JLabel("Width"), 0, 1, 0, 1);
        addItem(formPanel, widthInput, 1, 1, 0, 1);
        addItem(formPanel, new JLabel("Height"), 0, 2, 0, 1);
        addItem(formPanel, heightInput, 1, 2, 0, 1);
        addItem(formPanel, new JLabel("Weight"), 0, 3, 0, 1);
        addItem(formPanel, weightInput, 1, 3, 0, 1);
        
        // color picker
        colorButton.addActionListener(new ColorListener());
        colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel());
        
        // c.gridwidth = 3;
        addItem(formPanel, colorButton, 0, 4, 20, 3);
        addItem(formPanel, submitButton, 0, 5, 20, 3);
        
        frame.getContentPane().add(formPanel, BorderLayout.WEST);
        
        // Visualization panel
        visualizationPanel = new JPanel();
        visualizationPanel.setLayout(new GridLayout(VISUALIZATION_PANEL_HEIGHT, 2));
        visualizationPanel.add(visualizer);
        frame.getContentPane().add(visualizationPanel, BorderLayout.WEST);
        
        // pack and visualize
        frame.pack();
        frame.setVisible(true);
    }
    
    class ColorListener implements ActionListener, ChangeListener {
        public void actionPerformed(ActionEvent e) {
            color = JColorChooser.showDialog(
                formPanel,
                "Choose Color",
                color
            );
        }
        
        public void stateChanged(ChangeEvent e) {
            color = colorChooser.getColor();
        }
    }
}

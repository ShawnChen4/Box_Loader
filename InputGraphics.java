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
        int length = Integer.parseInt(lengthInput.getText());
        int width = Integer.parseInt(lengthInput.getText());
        int height = Integer.parseInt(heightInput.getText());
        System.out.printf("%d %d %d\n", length, width, height);
        
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
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        // Form text fields
        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(new JLabel("Length"), c);
        c.gridx = 1;
        c.gridy = 0;
        formPanel.add(lengthInput, c);
        
        c.gridx = 0;
        c.gridy = 1;
        formPanel.add(new JLabel("Width"), c);
        c.gridx = 1;
        c.gridy = 1;
        formPanel.add(widthInput, c);
        
        c.gridx = 0;
        c.gridy = 2;
        formPanel.add(new JLabel("Height"), c);
        c.gridx = 1;
        c.gridy = 2;
        formPanel.add(heightInput, c);
        
        c.gridx = 0;
        c.gridy = 3;
        formPanel.add(new JLabel("Weight"), c);
        c.gridx = 1;
        c.gridy = 3;
        formPanel.add(weightInput, c);
        
        // color picker
        c.ipady = 20;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        colorButton.addActionListener(new ColorListener());
        colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel());
        formPanel.add(colorButton, c);
        
        c.ipady = 20;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
        formPanel.add(submitButton, c);
        
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

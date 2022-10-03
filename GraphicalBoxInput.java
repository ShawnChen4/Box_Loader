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
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;

class GraphicalBoxInput {
    final int WIDTH = 800;
    final int HEIGHT = 600;
    final int DELAY = 10;
    
    private final int VISUALIZATION_PANEL_HEIGHT = 1;
    
    private JTextField lengthInput;
    private JTextField widthInput;
    private JTextField heightInput;
    private JTextField weightInput;
    private JButton colorButton;
    private JButton submitButton;
    private JButton deleteButton;
    private JButton doneButton;
    
    private JPanel formPanel, visualizationPanel;
    
    private Color color;
    private VisualizerPanel visualizer;
    
    private JColorChooser colorChooser;
    private JList<String> boxList;
    private DefaultListModel<String> boxListModel;
    
    private JFrame frame;
    private InputBox selectedBox;
    private ArrayList<InputBox> boxes = new ArrayList<InputBox>();
    private boolean running = true;
    
    public ArrayList<Box> run() {
        ArrayList<Box> output = new ArrayList<Box>();
        visualizer.selectBox(new InputBox(10, 10, 10, 10, new Color(20,20,20)));
        while (running) {
            
            int index = boxList.getSelectedIndex();
            if (index != -1) {
                visualizer.selectBox(boxes.get(index));
            }
            
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            frame.repaint();
        }
        
        for (InputBox b: boxes) {
            output.add(b.toBox());
        }
        
        frame.dispose();
        return output;
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
    
    GraphicalBoxInput() {
        lengthInput = new JTextField(10);
        widthInput = new JTextField(10);
        heightInput = new JTextField(10);
        weightInput = new JTextField(10);
        colorButton = new JButton("Color");
        submitButton = new JButton("Submit");
        deleteButton = new JButton("Delete");
        
        // boiler plate
        frame = new JFrame("Box Input");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2, 10, 10));
        
        // Form panel
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        
        // Form text fields
        addItem(formPanel, new JLabel("Length"), 0, 0, 0, 1);
        addItem(formPanel, lengthInput, 1, 0, 0, 2);
        addItem(formPanel, new JLabel("Width"), 0, 1, 0, 1);
        addItem(formPanel, widthInput, 1, 1, 0, 2);
        addItem(formPanel, new JLabel("Height"), 0, 2, 0, 1);
        addItem(formPanel, heightInput, 1, 2, 0, 2);
        addItem(formPanel, new JLabel("Weight"), 0, 3, 0, 1);
        addItem(formPanel, weightInput, 1, 3, 0, 2);
        
        // color picker
        colorButton.addActionListener(new ColorListener());
        colorChooser = new JColorChooser();
        
        // submit button
        submitButton.addActionListener(new SubmitListener());
        
        addItem(formPanel, colorButton, 0, 4, 20, 3);
        addItem(formPanel, submitButton, 0, 5, 20, 3);
        
        frame.getContentPane().add(formPanel, BorderLayout.WEST);
        
        // List of inputed boxes
        boxListModel = new DefaultListModel<String>();
        boxList = new JList<String>(boxListModel);
        boxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        boxList.setSelectedIndex(0);
        boxList.addListSelectionListener(new BoxListSelectionListener());
        boxList.setVisibleRowCount(5);
        JScrollPane boxListScrollPane = new JScrollPane(boxList);
        addItem(formPanel, boxListScrollPane, 0, 6, 20, 3);
        
        // Button for deleting boxes
        deleteButton.addActionListener(new DeleteListener());
        addItem(formPanel, deleteButton, 0, 7, 20, 3);
        
        // Visualization panel
        visualizer = new VisualizerPanel();
        visualizationPanel = new JPanel();
        visualizationPanel.setLayout(new GridLayout(VISUALIZATION_PANEL_HEIGHT, 2));
        visualizationPanel.add(visualizer);
        frame.getContentPane().add(visualizationPanel, BorderLayout.WEST);
        
        // Done button
        doneButton = new JButton("Done");
        doneButton.addActionListener(new DoneListener());
        addItem(formPanel, doneButton, 0, 8, 20, 3);
        
        // pack and visualize
        frame.pack();
        frame.setVisible(true);
    }
    
    class ColorListener implements ActionListener, ChangeListener {
        public void actionPerformed(ActionEvent e) {
            colorChooser.setPreviewPanel(new JPanel());
            color = JColorChooser.showDialog(formPanel, "Choose Color", color);
        }
        
        public void stateChanged(ChangeEvent e) {
            color = colorChooser.getColor();
        }
    }
    
    class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int length = Integer.parseInt(lengthInput.getText());
            int width  = Integer.parseInt(widthInput.getText());
            int height = Integer.parseInt(heightInput.getText());
            int weight = Integer.parseInt(weightInput.getText());
            
            InputBox box = new InputBox(width, length, height, weight, color);
            boxes.add(box);
            boxListModel.addElement(box.toString());
        }
    }
    
    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = boxList.getSelectedIndex();
            boxListModel.remove(index);
            boxes.remove(index);
            
            int size = boxListModel.getSize();
            if (size == 0) {
                deleteButton.setEnabled(false);
            } else {
                if (index == boxListModel.getSize()) {
                    index--;
                }
                boxList.setSelectedIndex(index);
                boxList.ensureIndexIsVisible(index);
            }
        }
    }
    
    class BoxListSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                if (boxList.getSelectedIndex() == -1) {
                    deleteButton.setEnabled(false);
                } else {
                    deleteButton.setEnabled(true);
                }
            }
        }
    }
    
    class DoneListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            running = false;
        }
    }
}

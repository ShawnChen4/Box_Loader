package src;
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

/**
 * GraphicalBoxInput
 * Manages a Graphical User Interface for inputting boxes
 * @see GraphicalTruckInput
 */
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
    
    private Color color; // user's currently chosen color.
    private VisualizerPanel visualizer; // The program's box visualizer
    
    private JColorChooser colorChooser;
    private JList<String> boxList; // list of given boxes
    private DefaultListModel<String> boxListModel;
    
    private JFrame frame;
    private InputBox selectedBox; // the box the current program is focusing on
    private ArrayList<InputBox> boxes = new ArrayList<InputBox>();
    private boolean running = true;
    
    /**
     * Runs the main loop of the box input GUI.
     * 
     * @return the boxes the user fed to it.
     */
    public ArrayList<Box> run() {
        ArrayList<Box> output = new ArrayList<Box>();
       
        while (running) {
            
            // if a box is selected, the change the focus of
            // program to that box.
            int index = boxList.getSelectedIndex();
            if (index != -1) {
                visualizer.selectBox(boxes.get(index));
            }
            
            try { Thread.sleep(DELAY); }
            catch (InterruptedException e) {}
            
            frame.repaint();
        }
        
        // Convert the user's choosen boxes to Box and write
        // it to the output array.
        for (InputBox b: boxes) {
            output.add(b.toBox());
        }
        
        frame.dispose(); // close the frame
        return output;   // return the output array
    }
    
    /**
     * Adds an item to the given panel at the given point of the
     * GridBagLayout of the panel.
     * 
     * @param panel     The panel which items will be added to.
     * @param component The component that is being added.
     * @param x         The x coordinate of where the component
     *                  is going to be placed at in the layout
     *                  of the panel
     * @param y         The y cooridnate of where the component
     *                  is going to be placed at.
     * @param pady      The y-padding of the component in the
     *                  GridBagLayout.
     * @param gridwidth The gridwidth of the component's GridBag
     *                  Constraints.
     */
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
    
    /**
     * Initilizes the Graphical User Interface by adding and arranging
     * the input form and the visualization panel.
     */
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
    
    /**
     * Listens for the color button. Opens up a color chooser where
     * the user may choose a color for the box
     */
    class ColorListener implements ActionListener, ChangeListener {
        /**
         * When an ActionEvent is perfored, opens up a color chooser
         * and updates the color variable to the user's desired color
         * @param e An ActionEvent.
         */
        public void actionPerformed(ActionEvent e) {
            colorChooser.setPreviewPanel(new JPanel());
            color = JColorChooser.showDialog(formPanel, "Choose Color", color);
        }
        
        /**
         * When the state of the color chooser changes, it updates the color
         * @param e A ChangeEvent.
         */
        public void stateChanged(ChangeEvent e) {
            color = colorChooser.getColor();
        }
    }
    
    /**
     * Listener for the submit button. Adds a box to the box ArrayList
     * when the submit button is pressed
     */
    class SubmitListener implements ActionListener {
        /**
         * When an the submit button is pressed, it adds a box to
         * the box class.
         * @param e An ActionEvent.
         */
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
    
    /**
     * Listener for the delete button.
     */
    class DeleteListener implements ActionListener {
        /**
         * When the delete button is pressed, remove the
         * selected box from the box array.
         * @param e An ActionEvent
         */
        public void actionPerformed(ActionEvent e) {
            int index = boxList.getSelectedIndex();
            boxListModel.remove(index);
            boxes.remove(index);
            
            int size = boxListModel.getSize();
            // if there are no boxes left, then disable 
            // the delete button.
            if (size == 0) {
                deleteButton.setEnabled(false);
            } else {
                // After deleting the box, move the selection
                // to the box above it on the list.
                if (index == boxListModel.getSize()) {
                    index--;
                }
                boxList.setSelectedIndex(index);
                boxList.ensureIndexIsVisible(index);
            }
        }
    }
    
    /**
     * Listener for the list of boxes
     */
    class BoxListSelectionListener implements ListSelectionListener {
        /**
         * When the user clicks on another item in the list, change the
         * focus of the program and the visualizer to that.
         * @param e A ListSelectionEvent.
         */
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                // if there are no boxes in the list, disable the delete
                // button
                if (boxList.getSelectedIndex() == -1) {
                    deleteButton.setEnabled(false);
                } else {
                    deleteButton.setEnabled(true);
                }
            }
        }
    }
    
    /**
     * Listener for the done button
     */
    class DoneListener implements ActionListener {
        /**
         * When the done button is pressed, stop running the program
         * @param e An ActionEvent
         */
        public void actionPerformed(ActionEvent e) {
            running = false;
        }
    }
}

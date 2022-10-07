package src;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * GraphicalBoxInput
 * Manages a Graphical User Interface for inputting trucks
 * @see GraphicalBoxInput
 */
class GraphicalTruckInput {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int DELAY = 10;
    
    private final int VISUALIZATION_PANEL_HEIGHT = 1;
    
    private JTextField lengthInput;
    private JTextField widthInput;
    private JTextField heightInput;
    private JTextField maxWeightInput;
    private JButton submitButton;
    private JButton deleteButton;
    private JButton doneButton;

    private JLabel lengthLabel;
    private JLabel widthLabel;
    private JLabel heightLabel;
    private JLabel maxWeightLabel;

    private JToggleButton backgroundButton;
    
    private JPanel formPanel;
    private JPanel visualizationPanel;
    private VisualizerPanel visualizer;
    
    private JList<String> truckList;
    private DefaultListModel<String> truckListModel;
    
    private JFrame frame;
    private ArrayList<InputTruck> trucks = new ArrayList<InputTruck>();
    private boolean running = true;
    
    /**
     * Initilizes the Graphical User Interface by adding and arranging
     * the input form and the visualization panel.
     */
    GraphicalTruckInput() {
        lengthInput = new JTextField(10);
        widthInput = new JTextField(10);
        heightInput = new JTextField(10);
        maxWeightInput = new JTextField(10);
        submitButton = new JButton("Submit");
        deleteButton = new JButton("Delete");
        backgroundButton = new JToggleButton("Switch");
        
        // boiler plate
        frame = new JFrame("Truck Input");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2, 10, 10));
        
        // Form panel
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        // Jlabels

        lengthLabel = new JLabel("Length");
        widthLabel = new JLabel("Width");
        heightLabel = new JLabel("Height");
        maxWeightLabel = new JLabel("Max Weight");

        // Form text fields
        addItem(formPanel, lengthLabel, 0, 0, 0, 1);
        addItem(formPanel, lengthInput, 1, 0, 0, 2);
        addItem(formPanel, widthLabel, 0, 1, 0, 1);
        addItem(formPanel, widthInput, 1, 1, 0, 2);
        addItem(formPanel, heightLabel, 0, 2, 0, 1);
        addItem(formPanel, heightInput, 1, 2, 0, 2);
        addItem(formPanel, maxWeightLabel, 0, 3, 0, 1);
        addItem(formPanel, maxWeightInput, 1, 3, 0, 2);
        
        // submit button
        submitButton.addActionListener(new SubmitListener());
        addItem(formPanel, submitButton, 0, 5, 20, 3);
        
        frame.getContentPane().add(formPanel, BorderLayout.WEST);
        
        // List of inputed trucks
        truckListModel = new DefaultListModel<String>();
        truckList = new JList<String>(truckListModel);
        truckList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        truckList.setSelectedIndex(0);
        truckList.addListSelectionListener(new TruckListSelectionListener());
        truckList.setVisibleRowCount(5);
        JScrollPane truckListScrollPane = new JScrollPane(truckList);
        addItem(formPanel, truckListScrollPane, 0, 6, 20, 3);
        
        // Button for deleting trucks
        deleteButton.addActionListener(new DeleteListener());
        addItem(formPanel, deleteButton, 0, 7, 20, 3);
        
        // Done button
        doneButton = new JButton("Done");
        doneButton.addActionListener(new DoneListener());
        addItem(formPanel, doneButton, 0, 8, 20, 3);

        // Background button

        backgroundButton = new JToggleButton("Switch");
        backgroundButton.addItemListener(new BackgroundListener());
        addItem(formPanel, backgroundButton, 0, 9, 20, 3);
        
        // Visualization panel
        visualizer = new VisualizerPanel();
        visualizationPanel = new JPanel();
        visualizationPanel.setLayout(new GridLayout(VISUALIZATION_PANEL_HEIGHT, 2));
        visualizationPanel.add(visualizer);
        frame.getContentPane().add(visualizationPanel, BorderLayout.WEST);

        // Setting fonts for JLabels
        lengthLabel.setFont(Const.CUSTOM_FONT);
        widthLabel.setFont(Const.CUSTOM_FONT);
        heightLabel.setFont(Const.CUSTOM_FONT);
        maxWeightLabel.setFont(Const.CUSTOM_FONT);
        backgroundButton.setFont(Const.CUSTOM_FONT);  

        // Setting button fonts
        submitButton.setFont(Const.CUSTOM_FONT);
        doneButton.setFont(Const.CUSTOM_FONT);
        deleteButton.setFont(Const.CUSTOM_FONT);
        
        // pack and visualize
        frame.pack();
        frame.setVisible(true);
    }
    
    
    /**
     * Runs the main loop of the truck input GUI.
     * 
     * @return the trucks the user fed to it.
     */
    public ArrayList<Truck> run() {
        ArrayList<Truck> output = new ArrayList<Truck>();
        while (running) {
            // if a box is selected, the change the focus of
            // program to that box.
            int index = truckList.getSelectedIndex();
            if (index != -1) {
                visualizer.select(trucks.get(index));
            }
            
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {}
            
            frame.repaint();
        }
        
        for (InputTruck t: trucks) {
            output.add(t.toTruck());
        }
        
        frame.dispose();
        return output;
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
     * Listener for the submit button. Adds a truck to the truck ArrayList
     * when the submit button is pressed
     */
    class SubmitListener implements ActionListener {
        /**
         * When an the submit button is pressed, it adds a truck to
         * the truck ArrayList.
         * @param e An ActionEvent.
         */
        public void actionPerformed(ActionEvent e) {
            int length = Integer.parseInt(lengthInput.getText());
            int width  = Integer.parseInt(widthInput.getText());
            int height = Integer.parseInt(heightInput.getText());
            int maxWeight = Integer.parseInt(maxWeightInput.getText());
            
            InputTruck truck = new InputTruck(length, width, height, maxWeight);
            trucks.add(truck);
            truckListModel.addElement(truck.toString());
        }
    }
    
    /**
     * Listener for the delete button.
     */
    class DeleteListener implements ActionListener {
        /**
         * When the delete button is pressed, remove the
         * selected truck from the truck array.
         * @param e An ActionEvent
         */
        public void actionPerformed(ActionEvent e) {
            int index = truckList.getSelectedIndex();
            truckListModel.remove(index);
            trucks.remove(index);
            
            int size = truckListModel.getSize();
            // if there are no trucks left, then disable 
            // the delete button.
            if (size == 0) {
                deleteButton.setEnabled(false);
            } else {
                // After deleting the truck, move the selection
                // to the truck above it on the list.
                if (index == truckListModel.getSize()) {
                    index--;
                }
                truckList.setSelectedIndex(index);
                truckList.ensureIndexIsVisible(index);
            }
        }
    }
    
    /**
     * Listener for the list of trucks
     */
    class TruckListSelectionListener implements ListSelectionListener {
        /**
         * When the user clicks on another item in the list, change the
         * focus of the program and the visualizer to that.
         * @param e A ListSelectionEvent.
         */
        public void valueChanged(ListSelectionEvent e) {
            // if there are no trucks in the list, disable the delete
            // button
            if (e.getValueIsAdjusting() == false) {
                if (truckList.getSelectedIndex() == -1) {
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

    /**
     * Listener for the switch background button
     */
    class BackgroundListener implements ItemListener {
        /**
         * When the switch background button is pressed, switch background theme
         * @param itemEvent An ActionEvent
         */
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            int state = itemEvent.getStateChange();

            // if selected print selected in console
            if (state == ItemEvent.SELECTED) {
                // Setting panel backgrounds
                frame.getContentPane().setBackground(Const.BACKGROUND_DARK_COLOR);
                formPanel.setBackground(Const.BACKGROUND_DARK_COLOR);
                // Setting submit button
                submitButton.setBackground(Const.WHITE_TEXT_COLOR);
                // Setting delete button
                deleteButton.setBackground(Const.WHITE_TEXT_COLOR);
                // Setting done button
                doneButton.setBackground(Const.WHITE_TEXT_COLOR);
                // Setting texts
                lengthLabel.setForeground(Const.WHITE_TEXT_COLOR);
                widthLabel.setForeground(Const.WHITE_TEXT_COLOR);
                heightLabel.setForeground(Const.WHITE_TEXT_COLOR);
                maxWeightLabel.setForeground(Const.WHITE_TEXT_COLOR);
                
            }
            else {
                // Setting panel backgrounds
                frame.getContentPane().setBackground(Const.BACKGROUND_LIGHT_COLOR);
                formPanel.setBackground(Const.BACKGROUND_LIGHT_COLOR);
                // Setting submit button
                submitButton.setBackground(Const.BACKGROUND_DARK_COLOR);
                // Setting delete button
                deleteButton.setBackground(Const.BACKGROUND_DARK_COLOR);
                // Setting done button
                doneButton.setBackground(Const.BACKGROUND_DARK_COLOR);
                // Setting texts
                lengthLabel.setForeground(Const.BACKGROUND_DARK_COLOR);
                widthLabel.setForeground(Const.BACKGROUND_DARK_COLOR);
                heightLabel.setForeground(Const.BACKGROUND_DARK_COLOR);
                maxWeightLabel.setForeground(Const.BACKGROUND_DARK_COLOR);
            }
        }
    }
}

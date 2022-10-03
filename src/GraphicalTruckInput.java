package src;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;

class GraphicalTruckInput {
    final int WIDTH = 800;
    final int HEIGHT = 600;
    final int DELAY = 10;
        
    private JTextField lengthInput;
    private JTextField widthInput;
    private JTextField heightInput;
    private JTextField maxWeightInput;
    private JButton submitButton;
    private JButton deleteButton;
    private JButton doneButton;
    
    private JPanel formPanel;
    
    private JList<String> truckList;
    private DefaultListModel<String> truckListModel;
    
    private JFrame frame;
    private ArrayList<InputTruck> trucks = new ArrayList<InputTruck>();
    private boolean running = true;
    
    public ArrayList<Truck> run() {
        ArrayList<Truck> output = new ArrayList<Truck>();
        while (running) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            frame.repaint();
        }
        
        for (Truck t: trucks) {
            output.add(t);
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
    
    GraphicalTruckInput() {
        lengthInput = new JTextField(10);
        widthInput = new JTextField(10);
        heightInput = new JTextField(10);
        maxWeightInput = new JTextField(10);
        submitButton = new JButton("Submit");
        deleteButton = new JButton("Delete");
        
        // boiler plate
        frame = new JFrame("Truck Input");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new GridLayout(0, 2, 10, 10));
        
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
        addItem(formPanel, new JLabel("Max Weight"), 0, 3, 0, 1);
        addItem(formPanel, maxWeightInput, 1, 3, 0, 2);
        
        // submit button
        submitButton.addActionListener(new SubmitListener());
        addItem(formPanel, submitButton, 0, 5, 20, 3);
        
        frame.getContentPane().add(formPanel, BorderLayout.WEST);
        
        // List of inputed boxes
        truckListModel = new DefaultListModel<String>();
        truckList = new JList<String>(truckListModel);
        truckList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        truckList.setSelectedIndex(0);
        truckList.addListSelectionListener(new TruckListSelectionListener());
        truckList.setVisibleRowCount(5);
        JScrollPane truckListScrollPane = new JScrollPane(truckList);
        addItem(formPanel, truckListScrollPane, 0, 6, 20, 3);
        
        // Button for deleting boxes
        deleteButton.addActionListener(new DeleteListener());
        addItem(formPanel, deleteButton, 0, 7, 20, 3);
        
        // Done button
        doneButton = new JButton("Done");
        doneButton.addActionListener(new DoneListener());
        addItem(formPanel, doneButton, 0, 8, 20, 3);
        
        // pack and visualize
        frame.pack();
        frame.setVisible(true);
    }
    
    class SubmitListener implements ActionListener {
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
    
    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = truckList.getSelectedIndex();
            truckListModel.remove(index);
            trucks.remove(index);
            
            int size = truckListModel.getSize();
            if (size == 0) {
                deleteButton.setEnabled(false);
            } else {
                if (index == truckListModel.getSize()) {
                    index--;
                }
                truckList.setSelectedIndex(index);
                truckList.ensureIndexIsVisible(index);
            }
        }
    }
    
    class TruckListSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                if (truckList.getSelectedIndex() == -1) {
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

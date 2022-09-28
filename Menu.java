import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu {
	
	private GraphicsPanel canvas;
	private JFrame frame;
	private JTextField lengthInput;
	private JTextField widthInput;
	private JTextField heightInput;
	private JTextField weightInput;
	private JLabel lengthLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel weightLabel;
	private JButton submitCustomOrder;
	private JPanel customOrderPanel;
	private JLabel customOutputPanel;
	
	public Menu() {
		frame = new JFrame("Box Loader");
		frame.setSize(1280, 720);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new GraphicsPanel();
		frame.add(canvas);
		customOrderPanel = new JPanel();
		customOutputPanel = new JLabel();
		frame.add(customOrderPanel);
		frame.add(customOutputPanel);
		
		lengthLabel = new JLabel("Enter Box Length: ");
		lengthLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		canvas.add(lengthLabel);
		lengthInput = new JTextField(10);
		canvas.add(lengthInput);
		
		widthLabel = new JLabel("Enter Box Width: ");
		widthLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		canvas.add(widthLabel);
		widthInput = new JTextField(10);
		canvas.add(widthInput);
		
		heightLabel = new JLabel("Enter Box Height: ");
		heightLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		canvas.add(heightLabel);
		heightInput = new JTextField(10);
		canvas.add(heightInput);
		
		weightLabel = new JLabel("Enter Box Weight: ");
		weightLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		canvas.add(weightLabel);
		weightInput = new JTextField(10);
		canvas.add(weightInput);
		
		
		submitCustomOrder = new JButton("Submit Custom Order");
		customOrderPanel.add(submitCustomOrder);
		
		submitCustomOrder.addActionListener(new SubmitButtonListener());
		frame.setVisible(true);
		
	}

	public void run() {
		while (true) {
			frame.repaint();
			try  {Thread.sleep(10);} catch(Exception e){}
		}
	}
	
	
	
	public void storeOrder() {
		String length = lengthInput.getText();
		lengthInput.setText("");
		String width = widthInput.getText();
		widthInput.setText("");
		String height = heightInput.getText();
		heightInput.setText("");
		String weight = weightInput.getText();
		weightInput.setText("");		
		customOutputPanel.setText("Length: " + length + " Width: " + width + "Height: " + height + " Weight: " + weight);

	}
	
	
	public class GraphicsPanel extends JPanel {
		public GraphicsPanel() {
			setFocusable(true);
			requestFocusInWindow();
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}

	public class SubmitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			storeOrder();
		}
	}

    

}

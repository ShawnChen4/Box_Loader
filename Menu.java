import java.awt.Font;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu {
	
	private GraphicsPanel canvas;
	private JFrame frame;
	private JTextField QuantityInput;
	private JTextField PriceInput;
	private JLabel BuyerID;
	private JLabel SellerID;
	private JLabel Quantity;
	private JLabel Price;
	private JLabel printLabel;
	private JButton enterButton;
	private JPanel buttonPanel;
	private JPanel printoutPanel;
	private MyKeyListener keyListener; 
	private File database;
	private String[] buyerIDArr;
	private String[] sellerIDArr;
	private JComboBox buyerDropDown;
	private JComboBox sellerDropDown;
	private String print = "Empty";
	private String size;
	private String price;
	
	
	Menu() {
		frame = new JFrame("Shipping Receiving System");
		frame.setSize(1280, 720);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new GraphicsPanel();
		frame.add(canvas);
		canvas.setLayout(new GridLayout(2, 4));
		frame.pack();
		buttonPanel = new JPanel();
		frame.add(buttonPanel);

		printLabel = new JLabel(print);
		printLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		frame.add(printLabel);

		keyListener = new MyKeyListener();

	    BuyerID = new JLabel("Enter Buyer ID");
	    BuyerID.setFont(new Font("Arial", Font.PLAIN, 15));
	    canvas.add(BuyerID);
	    
	    SellerID = new JLabel("Enter Seller ID");
	    SellerID.setFont(new Font("Arial", Font.PLAIN, 15));
	    canvas.add(SellerID);
	    
	    Quantity = new JLabel("Enter Quantity");
	    Quantity.setFont(new Font("Arial", Font.PLAIN, 15));
	    canvas.add(Quantity);
	    
	    Price = new JLabel("Enter Price");
	    Price.setFont(new Font("Arial", Font.PLAIN, 15));
	    canvas.add(Price);
	    

	    
	    enterButton = new JButton("Enter");
	    buttonPanel.add(enterButton);
	    enterButton.addActionListener(new EnterButtonListener());
		canvas.addKeyListener(keyListener); 
		database = new File("Database.txt");
		frame.setVisible(true);
		
	}
	
	public void collectInfo() throws IOException {
		//Instantiate array

	}
	
	public void run() {
		while (true) {
			frame.repaint();
			try  {Thread.sleep(10);} catch(Exception e){}
		}
	}
	
	public class GraphicsPanel extends JPanel {
		GraphicsPanel() {
			setFocusable(true);
			requestFocusInWindow();
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}
	
	public class EnterButtonListener implements ActionListener {
	
	    public void actionPerformed (ActionEvent event) {
	      //Write something here
	    }
	}
	
	public class MyKeyListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER) {
				//Write something here
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
	
}

package src;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUITesting extends JFrame {
  public static void main(String[] args) {
    new GUITesting();
  }

  JTextField width = new JTextField(5), length = new JTextField(5), height = new JTextField(5);

  JRadioButton small = new JRadioButton("Small"), medium = new JRadioButton("Medium"),
      large = new JRadioButton("Large");

  JCheckBox pepperoni = new JCheckBox("Pepperoni"), mushrooms = new JCheckBox("Mushrooms"),
      anchovies = new JCheckBox("Anchovies");

  JButton okButton = new JButton("OK");

  public GUITesting() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridBagLayout());
    addItem(panel1, new JLabel("Width:"), 0, 0, 1, 1, GridBagConstraints.WEST);
    addItem(panel1, new JLabel("Length:"), 0, 1, 1, 1, GridBagConstraints.WEST);
    addItem(panel1, new JLabel("Height:"), 0, 2, 1, 1, GridBagConstraints.WEST);

    addItem(panel1, width,  1, 0, 1, 1, GridBagConstraints.WEST);
    addItem(panel1, length,  1, 1, 1, 1, GridBagConstraints.WEST);
    addItem(panel1, height, 1, 2, 1, 1, GridBagConstraints.WEST);

    Box sizeBox = Box.createVerticalBox();
    ButtonGroup sizeGroup = new ButtonGroup();
    sizeGroup.add(small);
    sizeGroup.add(medium);
    sizeGroup.add(large);
    sizeBox.add(small);
    sizeBox.add(medium);
    sizeBox.add(large);
    sizeBox.setBorder(BorderFactory.createTitledBorder("Size"));
    addItem(panel1, sizeBox, 0, 3, 1, 1, GridBagConstraints.NORTH);

    Box buttonBox = Box.createHorizontalBox();
    buttonBox.add(okButton);
    buttonBox.add(Box.createHorizontalStrut(20));
    addItem(panel1, buttonBox, 2, 4, 1, 1, GridBagConstraints.NORTH);

    this.add(panel1);
    this.pack();
    this.setVisible(true);
	this.setSize(600, 400);
  }

// Add item method
  private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx = x;
    gc.gridy = y;
    gc.gridwidth = width;
    gc.gridheight = height;
    gc.insets = new Insets(5, 5, 5, 5);
    gc.anchor = align;
    gc.fill = GridBagConstraints.NONE;
    p.add(c, gc);
  }
}
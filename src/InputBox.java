package src;
import java.awt.Color;
import java.awt.Graphics;

/**
 * InputBox is used by the Graphical Inputing system to draw boxes
 * and to convert them to strings.
 */
class InputBox extends Box {
    
    InputBox(int width, int length, int height, int weight, Color color) {
        super(width, length, height, weight, color);
    }
    
    /**
     * Converts the InputBox to a string.
     * @return String A string representation of the input box.
     */
    public String toString() {
        return String.format("Box[ width: %d length: %d height: %d weight: %d ]", 
                             this.getWidth(), this.getLength(), 
                             this.getHeight(), this.getWeight());
    }
    
    /**
     * Converts the input box to a normal box.
     * @return Box The converted box.
     */
    public Box toBox() {
        return new Box(this.getWidth(), this.getLength(), this.getHeight(),
                       this.getWeight(), this.getColor());
    }
    
    /**
     * Draws the InputBox to the given graphics object.
     * @param g The Graphics object.
     */
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getX() + 20, this.getY() + 20, this.getWidth(), this.getHeight());
    }
}

import java.awt.Color;
import java.awt.Graphics;

class InputBox extends Box {
    
    public InputBox(int width, int length, int height, int weight, Color color) {
        super(width, length, height, weight, color);
    }
    
    public String toString() {
        return String.format("Box[ width: %d length: %d height: %d weight: %d ]", 
                             this.getWidth(), this.getLength(), 
                             this.getHeight(), this.getWeight());
    }
    
    public Box toBox() {
        return new Box(this.getWidth(), this.getLength(), this.getHeight(),
                       this.getWeight(), this.getColor());
    }
    
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getX() + 20, this.getY() + 20, this.getWidth(), this.getHeight());
    }
}

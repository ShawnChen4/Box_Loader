import java.awt.Color;
import java.awt.Graphics;

class InputBox extends Box {
    
    public InputBox(int width, int length, int height, int weight, int shippingNumber, Color color) {
        super(width, length, height, weight, shippingNumber, color);
    }
    
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
import java.awt.Graphics;

public class Box {
    private int width;
    private int length;
    private int height;
    private int weight;
    private int x;
    private int y;
    private int shippingNumber;

    public Box(int width, int length, int height, int weight, int x, int y, int shippingNumber) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.x = x;
        this.y = y;
        this.shippingNumber = shippingNumber;
    }

    public void draw(Graphics g) {}
}

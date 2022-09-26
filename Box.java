import java.awt.Graphics;

public class Box {
    private int width;
    private int length;
    private int height;
    private int weight;
    private int x;
    private int y;
    private int shippingNumber;
//---------------------------------------------------------------------------
    public Box(int width, int length, int height, int weight, int x, int y, int shippingNumber) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.x = x;
        this.y = y;
        this.shippingNumber = shippingNumber;
    }
//---------------------------------------------------------------------------
    //getters and setters
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getShippingNumber() {
        return shippingNumber;
    }
    public void setShippingNumber(int shippingNumber) {
        this.shippingNumber = shippingNumber;
    }
//---------------------------------------------------------------------------
    public void draw(Graphics g) {} 
}
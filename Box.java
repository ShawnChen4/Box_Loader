import java.awt.Graphics;
import java.awt.Color;

public class Box {
    private int width;
    private int length;
    private int height;
    private int weight;
    private int x;
    private int y;
    private Color color;
//---------------------------------------------------------------------------
    public Box(int width, int length, int height, int weight, Color color) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.x = x;
        this.y = y;
        this.color = color;
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
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

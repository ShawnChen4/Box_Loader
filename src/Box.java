package src;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Box
 * An object for a box
 */
public class Box {
    private int width;
    private int length;
    private int height;
    private int weight;
    private int x;
    private int y;
    private Color color;
    
    public Box(int width, int length, int height, int weight, Color color) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.x = 0;
        this.y = 0;
        this.color = color;
    }
    
    public Box() {
        this.width = 0;
        this.length = 0;
        this.height = 0;
        this.weight = 0;
        this.x = 0;
        this.y = 0;
        this.color = new Color(0, 0, 0);
    }
    
    /**
     * Checks whether this box is colliding with other
     * boxes. It does this in 2 dimensions assuming that--
     * when viewed from the top-- the width is AD and the height
     * is AB. 
     * <pre>
     *     B----------C
     *     |          |
     *     |          |
     *     |          |
     *     |          |
     *  A(x,y)--------D
     * </pre>
     * @param box The box that we might be colliding with.
     * @return    Whether the boxes have collided.
     */
    public boolean collidesWith(Box box) {
        int x1 = box.getX();
        int y1 = box.getY();
        int width1 = box.getWidth();
        int length1 = box.getLength();
        int height1 = box.getHeight();
        return
            (x  <= (x1 +   width1)) &&
            (x1 <= (x  +    width)) &&
            (y  <= (y1 +  length1)) &&
            (y1 <= (y  +   length));
    }
    
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

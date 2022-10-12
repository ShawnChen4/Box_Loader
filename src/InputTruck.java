import java.awt.Color;
import java.awt.Graphics;

/**
 * InputTruck is used by the graphical inputting system to represent
 * trucks and to get a string representation of them
 */
class InputTruck extends Truck implements Drawable {

    InputTruck(int length, int width, int height, int maxWeight) {
        super(length, width, height, maxWeight);
    }
    
    /**
     * Provides a string representation of this truck.
     * @return A string representation this truck.
     */
    public String toString() {
        return String.format("Truck[ length: %d width: %d height: %d maxWeight: %d ]",
                             this.getLength(), this.getWidth(), this.getHeight(), this.getMaxWeight());
    }
    
    /**
     * Converts this InputTruck to a normal truck.
     * @return The converted truck.
     */
    public Truck toTruck() {
        return new Truck(this.getLength(), this.getWidth(), this.getHeight(), this.getMaxWeight());
    }
    
    /**
     * Draws the InputTruck to the given graphics object.
     * @param g The Graphics object.
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(20, 20, this.getWidth(), this.getHeight());
    }
}
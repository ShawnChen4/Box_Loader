import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Truck
 * An object for a truck
 */
public class Truck {
    private int length;
    private int width;
    private int height;
    private int maxWeight;
    private ArrayList<Box> loadedBoxes;
    private int availableWeight;
    
    public Truck(int length, int width, int height, int maxWeight) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.maxWeight = maxWeight;
        this.loadedBoxes = new ArrayList<Box>();
    }
    
    /** 
     * Method to check if a box is loaded
     * 
     * @param box Check whether this is loaded
     * @return boolean
     */
    public boolean checkLoaded(Box box) {
        for (int loadedBoxesLen = 0; loadedBoxesLen < this.loadedBoxes.size(); loadedBoxesLen++) {
            if (this.loadedBoxes.get(loadedBoxesLen) == box) {
                return true;
            }
        }
        return false;
    }
    
    /*
     * Add the given box to the truck
     * @param box the box to be added
     */
    public void addBox(Box box) {
        loadedBoxes.add(box);
    }
    
    /**
     * Remove the given box from the truck
     * @param box the box to be removed.
     */
    public void removeBox(Box box) {
        loadedBoxes.remove(box);
    }
    
    public ArrayList<Box> getLoadedBoxes() {
        return this.loadedBoxes;
    }
    
    public void setLoadedBoxes(ArrayList<Box> newBoxes) {
        this.loadedBoxes = newBoxes;
    }
    
    public int getLength() {
        return length;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getMaxWeight() {
        return maxWeight;
    }
    
    public int getWidth() {
        return width;
    }
}

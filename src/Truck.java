package src;
import java.awt.Graphics;
import java.util.ArrayList;

public class Truck {
    private int length;
    private int width;
    private int height;
    private int maxWeight;
    private ArrayList<Box> loadedBoxes;
//---------------------------------------------------------------------------
    public Truck(int  length, int width, int height, int maxWeight) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.maxWeight = maxWeight;
        this.loadedBoxes = new ArrayList<Box>();
    }
//---------------------------------------------------------------------------
    // getters and setters
    public ArrayList<Box> getLoadedBoxes() {
        return this.loadedBoxes;
    }
    public void setLoadedBoxes(ArrayList<Box> newBoxes) {
        this.loadedBoxes = newBoxes;
    }
//---------------------------------------------------------------------------
    /** 
    * Method to check if a box is loaded
    * 
    * @param box
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
//---------------------------------------------------------------------------
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

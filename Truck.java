import java.awt.Graphics;
import java.util.ArrayList;

public class Truck {
    private int length;
    private int width;
    private int height;
    private int maxWeight;
    private ArrayList<Box> loadedBoxes;
//---------------------------------------------------------------------------
    public Truck(ArrayList<Box> boxes, int  length, int width, int height, int maxWeight, int loadedBoxes) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.maxWeight = maxWeight;
        this.loadedBoxes = boxes;
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
    /** 
    * Method to draw/visualize a truck
    * 
    * @param g
    */
    public void draw(Graphics g) {} 
}

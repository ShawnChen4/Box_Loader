import java.awt.Graphics;
import java.util.ArrayList;

public class Warehouse {
    ArrayList<Box> boxes;
    ArrayList<Box> boxSorted;
    private ArrayList<Truck> trucks;
//---------------------------------------------------------------------------
    public Warehouse(ArrayList<Box> boxes, ArrayList<Box> boxSorted, ArrayList<Truck> trucks) {
        this.boxes = boxes;
        this.boxSorted = boxSorted;
        this.trucks = trucks;
    }
    
    public Warehouse() {
        boxes = new ArrayList<Box>();
        boxSorted = new ArrayList<Box>();
        trucks = new ArrayList<Truck>();
    }
//---------------------------------------------------------------------------
    // getters and setters
    public ArrayList<Truck> getTrucks() {
        return this.trucks;
    }
    
    public void setTrucks(ArrayList<Truck> newTrucks) {
        this.trucks = newTrucks;
    }
//---------------------------------------------------------------------------
    public void sortWeight() {
        for (int truckLen = 0; truckLen < this.trucks.size(); truckLen++) {
            for (int boxLen = 0; boxLen < this.boxes.size(); boxLen++) {
                
            }
        }
    }
    
    public ArrayList<Box> getBoxes() {
        return boxes;
    }
    
    /** 
     * Method to add trucks
     * 
     * @param newTruck
     */
    public void addTruck(Truck newTruck) {
        this.trucks.add(newTruck);
    }
    /** 
     * Method to add boxes
     * 
     * @param newBox
     */
    public void addBox(Box newBox) {
        this.boxes.add(newBox);
    }
//---------------------------------------------------------------------------
    /** 
    * Method to draw/visualize a warehouse
    * 
    * @param g
    */
    public void draw(Graphics g) {} 
}

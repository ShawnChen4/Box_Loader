import java.util.ArrayList;

/**
 * Warehouse
 * A class that manages the boxes into the trucks
 */
public class Warehouse {
    ArrayList<Box> boxes;
    private ArrayList<Truck> trucks;

    public Warehouse(ArrayList<Box> boxes, ArrayList<Truck> trucks) {
        this.boxes = boxes;
        this.trucks = trucks;
    }
    
    public Warehouse() {
        boxes = new ArrayList<Box>();
        trucks = new ArrayList<Truck>();
    }

    /**
     * Method to add trucks
     * 
     * @param newTruck The truck we're adding
     */
    public void addTruck(Truck newTruck) {
        this.trucks.add(newTruck);
    }
    
    /** 
     * Method to add boxes
     * 
     * @param newBox the box we're adding
     */
    public void addBox(Box newBox) {
        this.boxes.add(newBox);
    }

    public ArrayList<Truck> getTrucks() {
        return this.trucks;
    }
    
    public void setTrucks(ArrayList<Truck> newTrucks) {
        this.trucks = newTrucks;
    }
    
    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(ArrayList<Box> newBoxes) {
        this.boxes = newBoxes;
    }
}

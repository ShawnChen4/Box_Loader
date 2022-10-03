import java.util.Scanner;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the recieving system. It manages a warehouse by adding boxes
 * to it and giving it to other systems such as the placement system.
 */
class RecievingSystem {
    
    private Warehouse warehouse;
    
    RecievingSystem() {
        warehouse = new Warehouse();
    }
    
    /**
     * Reads the specifications of boxes from a file. The file should be formatted as
     * follows:
     *     width length height weight x y shippingNumber red green blue
     * where each of these fields is an integer number and the first line of the file
     * includes the number of boxes specified. For example, the following
     * text indicates 2 boxes:
     *     2
     *     10 20 30 40 50 60 70 80 90 100
     *     1000 900 800 700 600 500 400 300 200 100
     * Where for the first box, the width is 10, the height is 20, and so forth. For
     * the second box, the width is 1000, the height is 900, and so forth. These two
     * boxes are then added to the RecievingSystem warehouse.
     * 
     * @param file this is the file where the box specifications are located.
     */
    public void loadBoxes(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        int numberOfBoxes = scanner.nextInt();
        for (int i = 0; i < numberOfBoxes; i++) {
            int width            = scanner.nextInt();
            int length           = scanner.nextInt();
            int height           = scanner.nextInt();
            int weight           = scanner.nextInt();
            int red              = scanner.nextInt();
            int green            = scanner.nextInt();
            int blue             = scanner.nextInt();
            
            Color colour = new Color(red, green, blue);
            Box box = new Box(width, length, height, weight, colour);
            
            warehouse.addBox(box);
        }
        scanner.close();
    }
    
    public void loadBoxes() {
        ArrayList<Box> boxes = new GraphicalBoxInput().run();
        for (Box box: boxes) {
            warehouse.addBox(box);
        }
    }
    
    public void loadTrucks() {
        ArrayList<Truck> trucks = new GraphicalTruckInput().run();
        for (Truck truck: trucks) {
            warehouse.addTruck(truck);
        }
    }
    
    public void run() {
        this.loadBoxes();
        this.loadTrucks();
    }
    
    public Warehouse getWarehouse() {
        return warehouse;
    }
}

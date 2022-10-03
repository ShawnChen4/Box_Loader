/**
 * InputTruck is used by the graphical inputting system to represent
 * trucks and to get a string representation of them
 */
class InputTruck extends Truck {
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
}
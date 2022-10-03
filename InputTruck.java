class InputTruck extends Truck {
    InputTruck(int length, int width, int height, int maxWeight) {
        super(length, width, height, maxWeight);
    }
    
    public String toString() {
        return String.format("Truck[ length: %d width: %d height: %d maxWeight: %d ]",
                             this.getLength(), this.getWidth(), this.getHeight(), this.getMaxWeight());
    }
    
    public Truck toTruck() {
        return new Truck(this.getLength(), this.getWidth(), this.getHeight(), this.getMaxWeight());
    }
}
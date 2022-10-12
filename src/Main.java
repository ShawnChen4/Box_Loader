class Main {
    public static void main(String[] args) {
        RecievingSystem rs = new RecievingSystem();
        rs.run();
        System.out.println(rs.getWarehouse().getTrucks());
        System.out.println(rs.getWarehouse().getBoxes());
    }
}
import java.io.File;
import java.io.IOException;

class Test {
    public static void main(String[] args) throws IOException {
        RecievingSystem rs = new RecievingSystem();
        rs.loadBoxes(new File("BoxInput.boxEnjoyers"));
        rs.loadBoxes();
        rs.loadTrucks();
    }
}
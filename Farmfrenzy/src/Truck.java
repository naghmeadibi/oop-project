import java.util.LinkedList;

public class Truck {
    public LinkedList<Products> stuffToSell = new LinkedList<Products>();
    private int capacity = 15;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

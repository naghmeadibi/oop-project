import java.util.LinkedList;

public class Truck {
    public LinkedList<String> stuffToSell = new LinkedList<String>();
    private int capacity = 15;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

import java.util.LinkedList;

public class Store {
   private int capacity = 10;
   public LinkedList<String> stuff = new LinkedList<String>();


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

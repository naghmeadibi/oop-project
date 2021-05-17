import java.util.LinkedList;

public class Store {
   private int capacity = 10;
   private LinkedList<String> stuff = new LinkedList<String>();

    public LinkedList<String> getStuff() {
        return stuff;
    }

    public void setStuff(LinkedList<String> stuff) {
        this.stuff = stuff;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

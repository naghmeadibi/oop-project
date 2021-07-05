import java.util.LinkedList;

public class Store {
   private int capacity = 30;
   public LinkedList<Products> stuff = new LinkedList<>();

   public Store() {

   }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

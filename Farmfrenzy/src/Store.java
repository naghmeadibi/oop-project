import java.util.LinkedList;

public class Store {
   private int capacity = 10;
   public LinkedList<Products> stuff = new LinkedList<Products>();

   public Store() {

   }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

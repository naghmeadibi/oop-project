import java.util.LinkedList;

public class Products {
    protected int live;
    protected int price;
    protected int capacity;
    private LinkedList<String> allUnPickedupedProducts = new LinkedList<String>();
     protected int coordinate;
    public LinkedList<String> getAllUnPickedupedProducts() {
        return allUnPickedupedProducts;
    }

    public void setAllUnPickedupedProducts(LinkedList<String> allUnPickedupedProducts) {
        this.allUnPickedupedProducts = allUnPickedupedProducts;
    }
}

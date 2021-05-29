import java.util.HashMap;
import java.util.LinkedList;

public class Products {
    protected int live;
    protected int price;
    protected int capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Products> allUnPickedupedProducts = new LinkedList<Products>();
    protected int coordinate;
    protected String name;

    public HashMap<String,Integer> capacityOfProduct = new HashMap<>();
    public HashMap<String,Integer> priceOfProduct = new HashMap<>();

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Products() {
        capacityOfProduct.put("egg",1);
        capacityOfProduct.put("feather",1);
        capacityOfProduct.put("milk",1);
        capacityOfProduct.put("flour",2);
        capacityOfProduct.put("cloth",2);
        capacityOfProduct.put("packagedMilk",2);
        capacityOfProduct.put("bread",4);
        capacityOfProduct.put("icecream",4);
        capacityOfProduct.put("shirt",4);
        priceOfProduct.put("egg",15);
        priceOfProduct.put("feather",20);
        priceOfProduct.put("milk",25);
        priceOfProduct.put("flour",40);
        priceOfProduct.put("cloth",50);
        priceOfProduct.put("packagedMilk",60);
        priceOfProduct.put("bread",80);
        priceOfProduct.put("icecream",100);
        priceOfProduct.put("shirt",120);
    }
}


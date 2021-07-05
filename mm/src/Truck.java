import java.util.LinkedList;

public class Truck {
    public LinkedList<Products> stuffToSell = new LinkedList<>();
    public LinkedList<WildAnimals> wildAnimalsToSell = new LinkedList<>();
    public LinkedList<Farmanimals> farmAnimalsToSell = new LinkedList<>();
    public LinkedList<Dog> dogsToSell = new LinkedList<>();
    public LinkedList<Cat> catToSell = new LinkedList<>();
    private int capacity = 15;
    private int price = 0;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

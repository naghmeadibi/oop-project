import java.util.LinkedList;

public class Truck {
    public LinkedList<Products> stuffToSell = new LinkedList<Products>();
    public LinkedList<WildAnimals> wildAnimalsToSell = new LinkedList<WildAnimals>();
    public LinkedList<Farmanimals> farmAnimalsToSell = new LinkedList<Farmanimals>();
    public LinkedList<Dog> dogsToSell = new LinkedList<Dog>();
    public LinkedList<Cat> catToSell = new LinkedList<Cat>();
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

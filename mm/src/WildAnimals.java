import java.util.LinkedList;

public class WildAnimals {
    LinkedList<WildAnimals> livingWildAnimals = new LinkedList<>();
    LinkedList<WildAnimals> cagedWildAnimals = new LinkedList<>();
    LinkedList<WildAnimals> storedWildAnimals = new LinkedList<>();

    int life;
    int coordinate;
    protected int price;
    protected int caged;
    protected int maxLife;
    String name;
    int capacity = 15;

    public int getMaxLife() {
        return maxLife;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getLife() {

        return life;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCaged() {
        return caged;
    }

    public void setCaged(int caged) {
        this.caged = caged;
    }

    //اگه -1 ینی خارج قفس از پنج به پایین تعداد مرحله هایی که تو قفس بوده
}

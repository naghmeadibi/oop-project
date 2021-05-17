import java.util.LinkedList;

public class Feather extends Products {
   public LinkedList<Feather> feathers = new LinkedList<Feather>();
    public Feather(int coordinate) {
        this.price = 20;
        this.live = 4;
        this.capacity = 1;
        this.coordinate = coordinate;
    }
    public Feather() {

    }
}

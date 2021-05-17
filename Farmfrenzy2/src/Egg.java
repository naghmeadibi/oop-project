import java.util.LinkedList;

public class Egg extends Products {
    public LinkedList<Egg> eggs = new LinkedList<Egg>();

    public Egg(int coordinate) {
        this.price = 15;
        this.live = 4;
        this.capacity = 1;
        this.coordinate = coordinate;
    }
    public Egg() {

    }
}


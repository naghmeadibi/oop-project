import java.util.LinkedList;

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Shirt extends Products {
    public LinkedList<Shirt> shirts = new LinkedList<Shirt>();
    public Shirt(int coordinate) {
        this.price = 100;
        this.live = 6;
        this.capacity = 4;
        this.coordinate = coordinate;
    }
    public Shirt() {

    }
}

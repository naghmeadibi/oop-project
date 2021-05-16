import java.util.LinkedList;

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Powder extends Products {
    public LinkedList<Powder> powders = new LinkedList<Powder>();
    public Powder(int coordinate) {
        this.price = 40;
        this.live = 5;
        this.capacity = 2;
        this.coordinate = coordinate;
    }
    public Powder() {

    }
}

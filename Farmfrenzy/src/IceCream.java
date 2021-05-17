import java.util.LinkedList;

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class IceCream extends Products {
    public LinkedList<IceCream> iceCreams = new LinkedList<IceCream>();
    public IceCream(int coordinate) {
        this.price = 120;
        this.live = 6;
        this.capacity = 4;
        this.coordinate = coordinate;
    }
    public IceCream() {

    }
}

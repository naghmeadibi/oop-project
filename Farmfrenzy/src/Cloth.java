import java.util.LinkedList;

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Cloth extends Products{
    public LinkedList<Cloth> cloths = new LinkedList<Cloth>();
    public Cloth(int coordinate) {
        this.price = 50;
        this.live = 5;
        this.capacity = 2;
        this.coordinate = coordinate;
    }
    public Cloth() {

    }
}


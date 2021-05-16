import java.util.LinkedList;

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Bread extends Products {

    public LinkedList<Bread> breads = new LinkedList<Bread>();

    public Bread(int coordinate) {
        this.price = 80;
        this.live = 6;
        this.capacity = 4;
        this.coordinate = coordinate;
    }
    public Bread (){

    }
}

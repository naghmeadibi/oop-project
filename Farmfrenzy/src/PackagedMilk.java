import java.util.LinkedList;

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class PackagedMilk extends Products{
    public LinkedList<PackagedMilk> packagedMilks = new LinkedList<PackagedMilk>();
    public PackagedMilk(int coordinate) {
        this.price = 60;
        this.live = 5;
        this.capacity = 2;
        this.coordinate = coordinate;
    }
    public PackagedMilk() {

    }
}

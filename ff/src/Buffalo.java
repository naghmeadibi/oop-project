/**
 * Created by Atmospher-PC on 30/05/2021.
 */
public class Buffalo extends Farmanimals {
    public Buffalo() {
        this.price = 400;
        this.timeNeededToProduce = 3;
        this.life = 10;
        this.name = "buffalo";
    }

    public Buffalo(int coordinate) {
        this.price = 400;
        this.timeNeededToProduce = 5;
        this.life = 10;
        this.coordinate = coordinate;
        this.name = "buffalo";
        this.capacity = 4;
    }
}

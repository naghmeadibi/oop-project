import java.util.LinkedList;

public class Hen extends Farmanimals {
    public Hen(int coordinate) {
        this.price = 100;
        this.timeNeededToProduce = 2;
        this.life = 10;
        this.coordinate = coordinate;
    }

    public Hen() {
        this.price = 100;
        this.timeNeededToProduce = 2;
        this.life = 10;
    }
}

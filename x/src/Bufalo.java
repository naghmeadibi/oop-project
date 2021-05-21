import java.util.LinkedList;

public class Bufalo extends Farmanimals {
    public Bufalo() {
        this.price = 400;
        this.timeNeededToProduce = 5;
        this.life = 10;
    }

    public Bufalo(int coordinate) {
        this.price = 400;
        this.timeNeededToProduce = 5;
        this.life = 10;
        this.coordinate = coordinate;
    }
}

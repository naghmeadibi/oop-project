

public class Hen extends Farmanimals {
    public Hen(int coordinate) {
        this.price = 100;
        this.timeNeededToProduce = 2;
        this.life = 10;
        this.coordinate = coordinate;
        this.name = "hen";
        this.image = "duck.png";
        this.capacity = 1;
    }

    public Hen() {
        this.price = 100;
        this.timeNeededToProduce = 2;
        this.life = 10;
        this.name = "hen";
        this.capacity = 1;
    }
}



public class Turkey extends Farmanimals{
    public Turkey() {
        this.price = 200;
        this.timeNeededToProduce = 3;
        this.life = 10;
        this.name = "turkey";
        this.image = "turkey.png";
    }

    public Turkey(int coordinate) {
        this.price = 200;
        this.timeNeededToProduce = 3;
        this.life = 10;
        this.coordinate = coordinate;
        this.name = "turkey";
        this.capacity = 2;
        this.image = "turkey.png";
    }


}

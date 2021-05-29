import java.util.LinkedList;

public class Bear extends WildAnimals{
    public Bear(int coordinate) {
        this.coordinate = coordinate;
        this.life = 4;
        this.price = 400;
        this.caged = -1;
        this.maxLife = 4;
        this.name = "bear";
    }
}

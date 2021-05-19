import java.util.LinkedList;

public class Grass {
    public LinkedList<Grass> grassList = new LinkedList<Grass>();
    private int coordinate;

    public Grass(int coordinate) {
        this.coordinate = coordinate;
    }
    public Grass (){

    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }
}

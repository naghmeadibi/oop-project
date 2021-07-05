public class Tiger extends WildAnimals {
    int interCoordinate;
    public Tiger(int coordinate) {
        this.coordinate = coordinate;
        this.life = 4;
        this.interCoordinate = 0;
        this.price = 500;
        this.caged = -1;
        this.maxLife = 4;
        this.name = "tiger";
    }



    public void setInterCoordinate(int interCoordinate) {
        this.interCoordinate = interCoordinate;
    }
}

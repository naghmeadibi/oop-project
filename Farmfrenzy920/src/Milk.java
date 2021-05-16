import java.util.LinkedList;

public class Milk extends Products{
    private LinkedList<String> featherCoordinates = new LinkedList<String>();

    public void setFeatherCoordinates(LinkedList<String> featherCoordinates) {
        this.featherCoordinates = featherCoordinates;
    }

    public LinkedList<String> getFeatherCoordinates() {
        return featherCoordinates;
    }

    public void addMilk(Milk milk) {

        //یه عدد رندمو برا مختصات درمیره به لیست اضافه میکنه
        //میزاره تو لیست به همراه عدد 4 واحد زمانی
    }

    public Milk() {
        this.price = 20;
        this.live = 4;
        this.capacity = 1;
    }
}

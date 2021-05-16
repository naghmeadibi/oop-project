import java.util.LinkedList;

public class Egg extends Products {
    private LinkedList<String> EggCoordinates = new LinkedList<String>();

    public void setEggCoordinates(LinkedList<String> eggCoordinates) {
        EggCoordinates = eggCoordinates;
    }

    public LinkedList<String> getEggCoordinates() {
        return EggCoordinates;
    }

    public void addEgg(Egg egg) {

        //یه عدد رندمو برا مختصات درمیره به لیست اضافه میکنه
        //میزاره تو لیست به همراه عدد 4 واحد زمانی
    }

    public Egg() {
        this.price = 15;
        this.live = 4;
        this.capacity = 1;
    }
}

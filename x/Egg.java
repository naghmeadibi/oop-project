import java.util.LinkedList;

public class Egg extends Products {
    private LinkedList<String> EggCoordinates = new LinkedList<String>();

    public void setEggCoordinates(LinkedList<String> eggCoordinates) {
        EggCoordinates = eggCoordinates;
    }

    public LinkedList<String> getEggCoordinates() {
        return EggCoordinates;
    }

    public int addEgg(Egg egg, int counter) {
        if (counter > 2) {
            counter = 0;
            //یه عدد رندمو برا مختصات درمیره به لیست اضافه میکنه
        } else
            counter++;
        return counter;
    }

    public Egg() {
        this.price = 15;
        this.live = 4;
        this.capacity = 1;
    }
}

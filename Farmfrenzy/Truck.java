import java.util.LinkedList;

public class Truck {
    private LinkedList<String> stuffToSell = new LinkedList<String>();

    public void setStuffToSell(LinkedList<String> stuffToSell) {
        this.stuffToSell = stuffToSell;
    }

    public LinkedList<String> getStuffToSell() {
        return stuffToSell;
    }
}

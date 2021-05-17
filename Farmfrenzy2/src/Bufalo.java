import java.util.LinkedList;

public class Bufalo extends Farmanimals {
    private LinkedList<String> buffaloList = new LinkedList<String>();

    public void setBuffaloList(LinkedList<String> buffalo) {
        this.buffaloList = buffalo;
    }

    public LinkedList<String> getBuffaloList() {
        return buffaloList;
    }

    public Bufalo() {
        this.price = 400;
        this.timeNeededToProduce = 5;
    }
}

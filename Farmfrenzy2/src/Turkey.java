import java.util.LinkedList;

public class Turkey extends Farmanimals{
    private LinkedList<String> turkeyList = new LinkedList<String>();

    public void setTurkeyList(LinkedList<String> turkeyList) {
        this.turkeyList = turkeyList;
    }

    public LinkedList<String> getTurkeyList() {
        return turkeyList;
    }

    public Turkey() {
        this.price = 200;
        this.timeNeededToProduce = 3;
    }
}

import java.util.LinkedList;

public class Hen extends Farmanimals {
    private LinkedList<String> henList = new LinkedList<String>();

    public void setHenList(LinkedList<String> henList) {
        this.henList = henList;
    }

    public LinkedList<String> getHenList() {
        return henList;
    }

    public Hen() {
        this.price = 100;
        this.timeNeededToProduce = 2;
    }
}

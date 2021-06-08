import java.util.LinkedList;

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Factory {
    public LinkedList<Factory> factories = new LinkedList<>();
    protected int price;
    protected int upgraidPrice;
    protected int time1;
    protected int time2;
    protected int level;
    protected String name;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUpgraidPrice() {
        return upgraidPrice;
    }

    public void setUpgraidPrice(int upgraidPrice) {
        this.upgraidPrice = upgraidPrice;
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Mill extends Factory {
    Egg egg = new Egg();
    Powder powder = new Powder();
    public Mill() {
        price = 150;
        upgraidPrice = 250;
        time1 = 4;
        time2 = 2;
        level = 1;
        name = "mill";
    }
}

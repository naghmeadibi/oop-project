/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Mill extends Factory {
    Egg egg = new Egg();
    Powder powder = new Powder();
    public Mill() {
        price = 150;
        time = 4;
        inputProduct = egg;
        outputProduct = powder;
    }
}

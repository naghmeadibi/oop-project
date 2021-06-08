/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Sewing extends Factory {
    Cloth cloth = new Cloth();
    Shirt shirt = new Shirt();
    public Sewing() {
        price = 400;
        upgraidPrice = 750;
        time1 = 6;
        time2 = 3;
        level = 1;
        name = "sewing";
    }
}

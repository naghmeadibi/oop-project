/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Sewing extends Factory {
    Cloth cloth = new Cloth();
    Shirt shirt = new Shirt();
    public Sewing() {
        price = 400;
        time = 6;
        inputProduct = cloth;
        outputProduct = shirt;
    }
}

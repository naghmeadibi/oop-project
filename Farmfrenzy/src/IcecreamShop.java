/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class IcecreamShop extends Factory {
    PackagedMilk packagedMilk = new PackagedMilk();
    IceCream iceCream = new IceCream();
    public IcecreamShop() {
        price = 550;
        time = 7;
        inputProduct = packagedMilk;
        outputProduct = iceCream;
    }
}

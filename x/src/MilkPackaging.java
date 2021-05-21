/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class MilkPackaging extends Factory {
    Milk milk = new Milk();
    PackagedMilk packagedMilk = new PackagedMilk();
    public MilkPackaging () {
        price = 400;
        time = 6;
        inputProduct = milk;
        outputProduct = packagedMilk;
    }
}

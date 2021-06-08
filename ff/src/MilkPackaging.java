/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class MilkPackaging extends Factory {
    Milk milk = new Milk();
    PackagedMilk packagedMilk = new PackagedMilk();
    public MilkPackaging () {
        price = 400;
        upgraidPrice = 750;
        time1 = 6;
        time2 = 3;
        level = 1;
        name = "packagingMilk";
    }
}

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class ClothWeaving extends Factory {
    Feather feather = new Feather();
    Cloth cloth = new Cloth();
    public ClothWeaving(){
        price = 250;
        time = 5;
        inputProduct = feather;
        outputProduct = cloth;
    }
}

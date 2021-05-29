import java.util.HashMap;

/**
 * Created by Atmospher-PC on 25/05/2021.
 */
public class User {
    int money;
    int level;
    String name;
    String password;
    HashMap<String,Boolean> workShops = new HashMap<>();

    public User( String name, String password, int level,int money) {
        this.money = money;
        this.level = level;
        this.name = name;
        this.password = password;
        HashMap<String,Boolean> workShopsKomaki = new HashMap<>();
        workShopsKomaki.put("mill",false);
        workShopsKomaki.put("iceCreamShop",false);
        workShopsKomaki.put("Sewing",false);
        workShopsKomaki.put("clothWeaving",false);
        workShopsKomaki.put("milkPackaging",false);
        workShopsKomaki.put("bakery",false);
        this.workShops = workShopsKomaki;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

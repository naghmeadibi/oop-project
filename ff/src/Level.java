import java.util.HashMap;

/**
 * Created by Atmospher-PC on 24/05/2021.
 */
public class Level {
    public int level;
    public int coin;
    public HashMap<String,Integer> tasks = new HashMap<>();
    public HashMap<Integer,String> wildAnimalsHashMap = new HashMap<>();
    public HashMap<Integer,Integer> time = new HashMap<>();

    public Level(int level, int coin, HashMap<String, Integer> tasks, HashMap<Integer, String> wildAnimalsHashMap, HashMap<Integer, Integer> time) {
        this.level = level;
        this.coin = coin;
        this.tasks = tasks;
        this.wildAnimalsHashMap = wildAnimalsHashMap;
        this.time = time;
    }
}

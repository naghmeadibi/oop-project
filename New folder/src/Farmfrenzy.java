import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Farmfrenzy {

    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
       int n = scanner.nextInt();
       Level[] levels = new Level[n];
        for (int i = 0; i < n; i++) {
            int Level = scanner.nextInt();
            int coin = scanner.nextInt();
            int counter = scanner.nextInt();
            HashMap<String, Integer> tasks = new HashMap<>();
            for (int j = 0; j < counter; j++) {
                tasks.put(scanner.next(), scanner.nextInt());
            }
            HashMap<Integer , String> wildAnimalsIntegerHashMap = new HashMap<>();
            int numberOfWildAnimals = scanner.nextInt();
            for (int j = 0; j < numberOfWildAnimals; j++) {
                String nameAnimal = scanner.next();
                wildAnimalsIntegerHashMap.put(scanner.nextInt() , nameAnimal);
            }
            HashMap<Integer,Integer> time = new HashMap<>();
            for (int j = 0; j < 3; j++) {
                if (j==2)
                    time.put(Integer.MAX_VALUE,scanner.nextInt());
                else
                    time.put(scanner.nextInt(),scanner.nextInt());
            }
        levels[i] = new Level(Level, coin, tasks, wildAnimalsIntegerHashMap, time);
        }
            GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
            Gson gson = builder.create();
            String level = gson.toJson(levels);
            write(level);
    }

    public static void write (String string) {
        try {
            FileWriter levels = new FileWriter("levels.json");
            levels.append(string);
            levels.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

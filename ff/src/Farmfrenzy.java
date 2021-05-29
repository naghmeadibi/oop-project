import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import jdk.nashorn.internal.parser.JSONParser;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Farmfrenzy {

    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
      /* int n = scanner.nextInt();
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
            write(level);*/
     // String jsonString = read();
       // System.out.println(jsonString);
     //   GsonBuilder builder = new GsonBuilder();
      //  Gson gson = builder.create();
      //  Level level = gson.fromJson(jsonString,Level.class);
      //  System.out.println(level);

       /* GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();*/
    //   JSONParser jsonParser = new JSONParser();
       try {
           Gson gson = new Gson();
           Level[] levels = gson.fromJson(new FileReader("levels.json"),Level[].class);
           System.out.println(levels.length);
           System.out.println(levels[1].tasks.toString());
       }catch (Exception e){

       }


    }

    public static void write (String string) {
        try {
            FileWriter levels = new FileWriter("levels.json",true);
            levels.append(string);
            levels.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String read () {
        String string = new String();
        File file = new File("levels.json");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                string += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return string;
    }
}

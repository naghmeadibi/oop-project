import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

/**
 * Created by Atmospher-PC on 15/05/2021.
 */
public class Main {
    public static void main(String[] args) {
         Manager manager = new Manager();
         InputProcossor inputProcossor = new InputProcossor(manager);
         inputProcossor.run();
    /*   File l = new File("levels.json");
        try {
            l.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}

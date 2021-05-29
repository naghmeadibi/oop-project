import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Atmospher-PC on 25/05/2021.
 */

public class Users {
    public static void main(String[] arg) {

       ArrayList<User> users = new ArrayList<>();
        users.add(new User("mamad", "6543", 3, 400));

        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        String user = gson.toJson(users);
        write(user);
       // readUser();
    }

    public static void write(String string) {
        try {
            FileWriter users = new FileWriter("users.json");
            users.append(string);
            users.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void readUser() {
        try {
            Gson gson = new Gson();
            User[] userss = gson.fromJson(new FileReader("users.json"), User[].class);
            ArrayList<User> users = new ArrayList<>();
            for (int i = 0; i < userss.length; i++) {
                users.add(userss[i]);
            }
            System.out.println(users.get(0).name);
        } catch (Exception e) {

        }
    }

    public void menu (){
        Scanner scanner = new Scanner(System.in);
        String userName = new String();
        String password = new String();
        int choice;
        System.out.println("Enter number of your choice!");
        System.out.println("1) login");
        System.out.println("2) sign up");
        choice = scanner.nextInt();
        if(choice == 1){
            System.out.println("enter user and pass");
            userName = scanner.next();
            password = scanner.next();
        }
    }

}

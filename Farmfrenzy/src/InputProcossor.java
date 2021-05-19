import jdk.nashorn.internal.runtime.ECMAException;

import java.lang.reflect.Executable;
import java.util.LinkedList;
import java.util.Scanner;

public class InputProcossor {
    Manager manager = new Manager();
    private LinkedList<String> orderOrgenizer = new LinkedList<String>();

    public InputProcossor(Manager manager) {
        this.manager = manager;
    }

    public void processWorkFactory(String[] strings){
        //try {
            if (strings[1].equalsIgnoreCase("mill")){
                manager.addPowder();
            } else if (strings[1].equalsIgnoreCase("sewing")){
                manager.addCloth();
            } else if ((strings[1].equalsIgnoreCase("Packaging")) && (strings[2].equalsIgnoreCase("milk"))){
                manager.addPackagedMilk();
            } else if (strings[1].equalsIgnoreCase("bakery")){
                manager.addBread();
            } else if (strings[1].equalsIgnoreCase("weaving")){
                manager.addShirt();
            } else if (strings[1].equalsIgnoreCase("weaving")){
                manager.addShirt();
            } else if ((strings[1].equalsIgnoreCase("Ice")) && (strings[2].equalsIgnoreCase("cream")) && (strings[3].equalsIgnoreCase("shop"))){
                manager.addIceCream();
            } else {
                System.out.println("Error, Wrong input!!");
            }
       // }
       // catch (Exception e) {
         //   System.out.println("Error,Wrong input!!");
      //  }
    }

    public void processAdd(String[] strings){
        try{
            if (strings[1].equalsIgnoreCase("egg")){
                manager.addEgg();
            } else if (strings[1].equalsIgnoreCase("feather")){
                manager.addFeather();
            } else if (strings[1].equalsIgnoreCase("milk")){
                manager.addMilk();
            }
        }
        catch (Exception e){
            System.out.println("Error, Wrong input!!");
        }
    }

    public void processWell(String[] strings){
        manager.well();
    }

    public void processPlant (String[] strings) {
        try {
            int x = Integer.valueOf(strings[1]);
            int y = Integer.valueOf(strings[2]);
            if ((x>0) && (x<7) && (y>0) && (y<7)) {
                int coordinate = x*10 + y;
                manager.plant(coordinate);
            } else {
                System.out.println("Error, Wrong coordinate!!");
            }
        }
        catch (Exception e) {
            System.out.println("Error, Wrong Input!!");
        }
    }

    public void processPickUp (String[] strings) {
        try {
            int x = Integer.valueOf(strings[1]);
            int y = Integer.valueOf(strings[2]);
            if ((x>0) && (x<7) && (y>0) && (y<7)) {
                int coordinate = x*10 + y;
                manager.pickUp(coordinate);
            } else {
                System.out.println("Error, Wrong coordinate!!");
            }
        }
        catch (Exception e) {
            System.out.println("Error, Wrong Input!!");
        }
    }

    public void processTruckLoad (String[] strings) {
        try {
            manager.truckLoad(strings[2]);
        }
        catch (Exception e){
            System.out.println("Error, Wrong input!!");
        }
    }

    public void processTruckUnload (String[] strings){
        try {
            manager.truckUnload(strings[2]);
        }
        catch (Exception e){
            System.out.println("Error, Wrong input");
        }
    }

    public void run() {
        while (true) {
            manager.menu();
            String input;
            Scanner scanner = new Scanner(System.in);
            while (!(input = scanner.nextLine()).equalsIgnoreCase("logout")) {
                String[] inputs = input.split("\\s");
               // try{
                    if (inputs[0].equalsIgnoreCase("Work")){
                        processWorkFactory(inputs);
                    } else if (inputs[0].equalsIgnoreCase("add")){
                        processAdd(inputs);
                    } else if (inputs[0].equalsIgnoreCase("well")){
                        processWell(inputs);
                    } else if (inputs[0].equalsIgnoreCase("plant")){
                        processPlant(inputs);
                    } else if (inputs[0].equalsIgnoreCase("pickup")){
                        processPickUp(inputs);
                    } else if ((inputs[0].equalsIgnoreCase("truck")) && (inputs[1].equalsIgnoreCase("load"))){
                        processTruckLoad(inputs);
                    } else if ((inputs[0].equalsIgnoreCase("truck")) && (inputs[1].equalsIgnoreCase("unload"))){
                        processTruckUnload(inputs);
                    } else {
                        System.out.println("Wrong input!!");
                    }
                    System.out.println(manager.products.allUnPickedupedProducts.toString());
                    System.out.println(manager.store.stuff.toString());
                    System.out.println(manager.truck.stuffToSell.toString());
              //  }
              //  catch (Exception e) {
              //      System.out.println("Error, Wrong input!!");
              //  }
            }
        }
    }
}

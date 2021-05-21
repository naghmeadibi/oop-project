

import java.lang.reflect.Executable;
import java.util.LinkedList;
import java.util.Scanner;

public class InputProcossor {
    Manager manager = new Manager();

    public LinkedList<String> orders = new LinkedList<>();

    public InputProcossor(Manager manager) {
        this.manager = manager;
    }

    public void processWorkFactory(String[] strings) {
        try {
            if (strings[1].equalsIgnoreCase("mill")) {
                if ((!orders.contains("addPowder")) && (manager.checkEgg())) {
                    orders.add("addPowder");
                    orders.add("5");
                }
                else if (orders.contains("addPowder")){
                    System.out.println("Factory is working!!");
                }
            } else if (strings[1].equalsIgnoreCase("sewing")) {
                if ((!orders.contains("addCloth")) && (manager.checkFeather())) {
                    orders.add("addCloth");
                    orders.add("6");
                }
                else if (orders.contains("addCloth")){
                    System.out.println("Factory is working!!");
                }
            } else if ((strings[1].equalsIgnoreCase("Packaging")) && (strings[2].equalsIgnoreCase("milk"))) {
                if ((!orders.contains("addPackagedMilk")) && (manager.checkMilk())) {
                    orders.add("addPackagedMilk");
                    orders.add("7");
                }
                else if (orders.contains("addPackagedMilk")){
                    System.out.println("Factory is working!!");
                }
            } else if (strings[1].equalsIgnoreCase("bakery")) {
                if ((!orders.contains("addBread")) && (manager.checkPowder())) {
                    orders.add("addBread");
                    orders.add("6");
                }
                else if (orders.contains("addBread")){
                    System.out.println("Factory is working!!");
                }
            } else if (strings[1].equalsIgnoreCase("weaving")) {
                if ((!orders.contains("addShirt")) && (manager.checkCloth())) {
                    orders.add("addShirt");
                    orders.add("7");
                }
                else if (orders.contains("addShirt")){
                    System.out.println("Factory is working!!");
                }
            }  else if ((strings[1].equalsIgnoreCase("Ice")) && (strings[2].equalsIgnoreCase("cream")) && (strings[3].equalsIgnoreCase("shop"))) {
                if ((!orders.contains("addIceCream")) && (manager.checkPackagedMilk())) {
                    orders.add("addIceCream");
                    orders.add("8");
                }
                else if (orders.contains("addIceCream")){
                    System.out.println("Factory is working!!");
                }
            } else {
                System.out.println("Error, Wrong input!!");
            }
        } catch (Exception e) {
            System.out.println("Error,Wrong input!!");
        }
    }

    public void processAdd(String[] strings) {
        try {
            if (strings[1].equalsIgnoreCase("egg")) {
                manager.addEgg();
            } else if (strings[1].equalsIgnoreCase("feather")) {
                manager.addFeather();
            } else if (strings[1].equalsIgnoreCase("milk")) {
                manager.addMilk();
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong input!!");
        }
    }

    public void processWell(String[] strings) {
        manager.well();
    }

    public void processCage(String[] strings){
        int x = Integer.valueOf(strings[1]);
        int y = Integer.valueOf(strings[2]);
        if ((x > 0) && (x < 7) && (y > 0) && (y < 7)) {
            int coordinate = x * 10 + y;
            manager.cage(coordinate);
    }
        else {
            System.out.println("Error, Wrong coordinate!!");
        }
    }

    public void processPlant(String[] strings) {
        try {
            int x = Integer.valueOf(strings[1]);
            int y = Integer.valueOf(strings[2]);
            if ((x > 0) && (x < 7) && (y > 0) && (y < 7)) {
                int coordinate = x * 10 + y;
                manager.plant(coordinate);
            } else {
                System.out.println("Error, Wrong coordinate!!");
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong Input!!");
        }
    }

    public void processPickUp(String[] strings) {
        try {
            int x = Integer.valueOf(strings[1]);
            int y = Integer.valueOf(strings[2]);
            if ((x > 0) && (x < 7) && (y > 0) && (y < 7)) {
                int coordinate = x * 10 + y;
                manager.pickUp(coordinate);
            } else {
                System.out.println("Error, Wrong coordinate!!");
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong Input!!");
        }
    }

    public void processTruckLoad(String[] strings) {
        try {
            manager.truckLoad(strings[2]);
        } catch (Exception e) {
            System.out.println("Error, Wrong input!!");
        }
    }

    public void processTruckUnload(String[] strings) {
        try {
            manager.truckUnload(strings[2]);
        } catch (Exception e) {
            System.out.println("Error, Wrong input");
        }
    }

    public void processAddAnimals(String[] strings) {
        if (strings[2].equalsIgnoreCase("hen")) {
            manager.addHen();
        } else if (strings[2].equalsIgnoreCase("turkey")) {
            manager.addTurkey();
        } else if (strings[2].equalsIgnoreCase("bufalo")) {
            manager.addBufalo();
        } else if (strings[2].equalsIgnoreCase("lion")) {
            manager.addLion();
        } else if (strings[2].equalsIgnoreCase("tiger")) {
            manager.addTiger();
        } else if (strings[2].equalsIgnoreCase("bear")) {
            manager.addBear();
        }
        //اینا بعدن از تو فایل خونده میشن
        else if (strings[2].equalsIgnoreCase("dog")) {
            manager.addDog();
        }
        else if (strings[2].equalsIgnoreCase("cat")) {
            manager.addCat();
        }
    }

    public void timeHandle() {
        for (int cnt = 1; cnt < orders.size(); cnt += 2) {
            System.out.println(orders.get(cnt));
            orders.set(cnt,String.valueOf(Integer.valueOf(orders.get(cnt)) - 1));
        }
        for (int cnt = 1; cnt < orders.size(); cnt += 2) {
            int y = Integer.valueOf(orders.get(cnt));
            if (y == 0) {
                if (orders.get(cnt - 1).equalsIgnoreCase("addPowder")) {
                    manager.addPowder();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addCloth")) {
                    manager.addCloth();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addPackagedMilk")) {
                    manager.addPackagedMilk();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addBread")) {
                    manager.addBread();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addShirt")) {
                    manager.addShirt();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addIceCream")) {
                    manager.addIceCream();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                }
            }
        }
    }

    public void run() {
        while (true) {
            manager.menu();
            String input;
            Scanner scanner = new Scanner(System.in);
            while (!(input = scanner.nextLine()).equalsIgnoreCase("logout")) {
                manager.catCollect();
                manager.bearAndLionToEat();
                manager.tigerToEat();
                manager.dogToCatch();
                String[] inputs = input.split("\\s");
               try {
                    if (inputs[0].equalsIgnoreCase("Work")) {
                        processWorkFactory(inputs);
                    } else if (inputs[0].equalsIgnoreCase("add")) {
                        processAdd(inputs);
                    } else if (inputs[0].equalsIgnoreCase("well")) {
                        processWell(inputs);
                    } else if (inputs[0].equalsIgnoreCase("plant")) {
                        processPlant(inputs);
                    } else if (inputs[0].equalsIgnoreCase("pickup")) {
                        processPickUp(inputs);
                    } else if ((inputs[0].equalsIgnoreCase("truck")) && (inputs[1].equalsIgnoreCase("load"))) {
                        processTruckLoad(inputs);
                    } else if ((inputs[0].equalsIgnoreCase("truck")) && (inputs[1].equalsIgnoreCase("unload"))) {
                        processTruckUnload(inputs);
                    } else if ((inputs[0].equalsIgnoreCase("buy")) && (inputs[1].equalsIgnoreCase("animal"))) {
                        processAddAnimals(inputs);
                    } else if (inputs[0].equalsIgnoreCase("cage")) {
                        processCage(inputs);
                    }
                    else {
                        System.out.println("Wrong input!!");
                    }
                    manager.movingTiger();
                    manager.moving();
                    timeHandle();
                    System.out.println(orders.toString());
                       System.out.println(manager.products.allUnPickedupedProducts.toString());
                       System.out.println(manager.store.stuff.toString());
                       System.out.println(manager.truck.stuffToSell.toString());
                //   System.out.println(manager.lionsInCage.toString());
                 //      manager.coordinatePrint();
                  //     manager.lifeOfAnimals();
                  //     manager.grassToBeEaten();
                  //     manager.unCage();
                    //   manager.checkCagedAnimals();
            } catch (Exception e) {
                    System.out.println("Error, Wrong input!!");
                }
            }
        }
    }
}



import java.lang.reflect.Executable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputProcossor {
    Logger logger = Logger.getLogger("name");
    Manager manager = new Manager();
    int n = 1, counter = 0;
    boolean finishLev = true;
    private int money = 0;
    private int playerLevel = 0;
    private boolean checkMenu = true;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public LinkedList<String> orders = new LinkedList<>();


    public void processWorkFactory(String[] strings) {
        try {
            if (strings[1].equalsIgnoreCase("mill")) {
                if (manager.factory.factories.contains("mill")) {
                    if ((!orders.contains("addPowder")) && (manager.checkEgg())) {
                        orders.add("addPowder");
                        orders.add("5");
                    } else if (orders.contains("addPowder")) {
                        System.out.println("Factory is working!!");
                    }
                } else {
                    System.out.println("this factory doesnt exist!!");
                }
            } else if (strings[1].equalsIgnoreCase("sewing")) {
                if (manager.factory.factories.contains("sewing")) {
                    if ((!orders.contains("addCloth")) && (manager.checkFeather())) {
                        orders.add("addCloth");
                        orders.add("6");
                    } else if (orders.contains("addCloth")) {
                        System.out.println("Factory is working!!");
                    }
                } else {
                    System.out.println("this factory doesnt exist!!");
                }
            } else if ((strings[1].equalsIgnoreCase("Packaging")) && (strings[2].equalsIgnoreCase("milk"))) {
                if (manager.factory.factories.contains("packagingMilk")) {
                    if ((!orders.contains("addPackagedMilk")) && (manager.checkMilk())) {
                        orders.add("addPackagedMilk");
                        orders.add("7");
                    } else if (orders.contains("addPackagedMilk")) {
                        System.out.println("Factory is working!!");
                    }
                } else {
                    System.out.println("this factory doesnt exist!!");
                }
            } else if (strings[1].equalsIgnoreCase("bakery")) {
                if (manager.factory.factories.contains("bakery")) {
                    if ((!orders.contains("addBread")) && (manager.checkPowder())) {
                        orders.add("addBread");
                        orders.add("6");
                    } else if (orders.contains("addBread")) {
                        System.out.println("Factory is working!!");
                    }
                } else {
                    System.out.println("this factory doesnt exist!!");
                }
            } else if (strings[1].equalsIgnoreCase("weaving")) {
                if (manager.factory.factories.contains("weaving")) {
                if ((!orders.contains("addShirt")) && (manager.checkCloth())) {
                    orders.add("addShirt");
                    orders.add("7");
                } else if (orders.contains("addShirt")) {
                    System.out.println("Factory is working!!");
                }
                } else {
                    System.out.println("this factory doesnt exist!!");
                }
            } else if ((strings[1].equalsIgnoreCase("Ice")) && (strings[2].equalsIgnoreCase("cream")) && (strings[3].equalsIgnoreCase("shop"))) {
                if (manager.factory.factories.contains("iceCreamShop")) {
                if ((!orders.contains("addIceCream")) && (manager.checkPackagedMilk())) {
                    orders.add("addIceCream");
                    orders.add("8");
                } else if (orders.contains("addIceCream")) {
                    System.out.println("Factory is working!!");
                }
                } else {
                    System.out.println("this factory doesnt exist!!");
                }
            } else {
                System.out.println("Error, Wrong input!!");
            }
        } catch (Exception e) {
            System.out.println("Error,Wrong input!!");
        }
    }

    public void processWell(String[] strings) {
        if (!orders.contains("well")) {
            if (manager.checkWell()) {
                orders.add("well");
                orders.add("4");
            } else {
                System.out.println("well is not empty!!");
            }
        } else if (orders.contains("well")) {
            System.out.println("well is working!!");
        }
    }

    public void processCage(String[] strings) {
        int x = Integer.valueOf(strings[1]);
        int y = Integer.valueOf(strings[2]);
        if ((x > 0) && (x < 7) && (y > 0) && (y < 7)) {
            int coordinate = x * 10 + y;
            manager.cage(coordinate);
        } else {
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
            if (!orders.contains("truckGo")) {
                manager.truckLoad(strings[2]);
            } else {
                System.out.println("The truck is moving");
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong input!!");
        }
    }

    public void processTruckUnload(String[] strings) {
        try {
            if (!orders.contains("truckGo")) {
                manager.truckUnload(strings[2]);
            } else {
                System.out.println("The truck is moving");
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong input");
        }
    }

    public void processTruckGo() {
        if (!orders.contains("truckGo")) {
            manager.checkTruckGo();
            orders.add("truckGo");
            orders.add("11");
        } else if (orders.contains("truckGo")) {
            System.out.println("The truck is moving!!");
        }
    }

    public void processAddAnimals(String[] strings) {
        if (strings[1].equalsIgnoreCase("hen")) {
            manager.addHen();
        } else if (strings[1].equalsIgnoreCase("turkey")) {
            manager.addTurkey();
        } else if (strings[1].equalsIgnoreCase("bufalo")) {
            manager.addBufalo();
        }/* else if (strings[1].equalsIgnoreCase("lion")) {
            manager.addLion();
        } else if (strings[1].equalsIgnoreCase("tiger")) {
            manager.addTiger();
        } else if (strings[1].equalsIgnoreCase("bear")) {
            manager.addBear();
        }*/
        //اینا بعدن از تو فایل خونده میشن
        else if (strings[2].equalsIgnoreCase("dog")) {
            manager.addDog();
        } else if (strings[2].equalsIgnoreCase("cat")) {
            manager.addCat();
        } else {
            System.out.println("Wrong input!");
        }
    }

    public void processTurn(String[] strings) {
        n = Integer.parseInt(strings[1]);
        for (int c = 0; c < n - 1; c++) {
            manager.unCage();
            manager.checkCagedAnimals();
            manager.movingTiger();
            manager.moving();
            manager.catCollect();
            manager.bearAndLionToEat();
            manager.tigerToEat();
            manager.dogToCatch();
            manager.lifeOfAnimals();
            manager.productTimeHandle();
            manager.grassToBeEaten();
            manager.animalProduct();
            timeHandle();
            counter++;
            manager.addWildAnimals(counter);
            if (manager.checkTasks()) {
                System.out.println("BABA KHAFAN");
                if (manager.getSelectedLevel() > manager.users.get(manager.getIndexOfUser()).getLevel())
                    manager.users.get(manager.getIndexOfUser()).setLevel(manager.getSelectedLevel());
                manager.moneySet(counter);
                finishLev = false;
                break;
            }
        }
        //  manager.Turn(counter);
    }

    public void timeHandle() {
        for (int cnt = 1; cnt < orders.size(); cnt += 2) {
            orders.set(cnt, String.valueOf(Integer.valueOf(orders.get(cnt)) - 1));
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
                } else if (orders.get(cnt - 1).equalsIgnoreCase("well")) {
                    manager.well();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                } else if (orders.get(cnt - 1).equalsIgnoreCase("truckGo")) {
                    manager.truckGo();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                }
            }
        }
    }

    public void setUser() {
        money = manager.users.get(manager.getIndexOfUser()).getMoney();
        playerLevel = manager.users.get(manager.getIndexOfUser()).getLevel() + 1;
    }

    public void check(String input) {
        if (!input.equalsIgnoreCase("turn 0")) {
            manager.unCage();
            manager.checkCagedAnimals();
            manager.moving();
            manager.movingTiger();
            manager.catCollect();
            manager.bearAndLionToEat();
            manager.tigerToEat();
            manager.dogToCatch();
            manager.lifeOfAnimals();
            manager.productTimeHandle();
            manager.grassToBeEaten();
            manager.animalProduct();
            timeHandle();
            counter++;
            manager.addWildAnimals(counter);
        }
    }

    public void checkFinishLevel() {
        System.out.println("BABA KHAFAN");
        if (manager.getSelectedLevel() >= manager.users.get(manager.getIndexOfUser()).getLevel())
            manager.users.get(manager.getIndexOfUser()).setLevel(manager.getSelectedLevel() + 1);
        manager.moneySet(counter);
        finishLev = false;
    }

    public void processBuild(String[] strings) {
        try {
            if (strings[1].equalsIgnoreCase("mill")) {
                manager.buildFactory("mill");
            } else if (strings[1].equalsIgnoreCase("sewing")) {
                manager.buildFactory("sewing");
            } else if ((strings[1].equalsIgnoreCase("Packaging")) && (strings[2].equalsIgnoreCase("milk"))) {
                manager.buildFactory("packagingMilk");
            } else if (strings[1].equalsIgnoreCase("bakery")) {
                manager.buildFactory("bakery");
            } else if (strings[1].equalsIgnoreCase("weaving")) {
                manager.buildFactory("weaving");
            } else if ((strings[1].equalsIgnoreCase("Ice")) && (strings[2].equalsIgnoreCase("cream")) && (strings[3].equalsIgnoreCase("shop"))) {
                manager.buildFactory("iceCreamShop");
            } else {
                System.out.println("Wrong input!!");
            }
        } catch (Exception e) {
            System.out.println("Wrong input!!");
        }
    }

    public void run() {
        while (true) {
            manager.readingLevels();
            counter = 0;
            manager.setBack();
            manager.menu(checkMenu);
            manager.coinSet();
            setUser();
            manager.setNumOfUsers(manager.numOfUsers);
            checkMenu = false;
            finishLev = true;
            while (true && finishLev) {
                Scanner scanner = new Scanner(System.in);
                String input;
                while (!(input = scanner.nextLine()).equalsIgnoreCase("logout") && finishLev) {
                    if (input.equalsIgnoreCase("inquiry")) {
                        input = "turn 0";
                    }
                    n = 1;
                    String[] inputs = input.split("\\s");
                    if (inputs[0].equalsIgnoreCase("turn")) {
                        n = Integer.valueOf(inputs[1]);
                    }
                    if (finishLev) {
                        try {
                            if (inputs[0].equalsIgnoreCase("Work")) {
                                processWorkFactory(inputs);
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
                            } else if (inputs[0].equalsIgnoreCase("buy")) {
                                processAddAnimals(inputs);
                            } else if (inputs[0].equalsIgnoreCase("cage")) {
                                processCage(inputs);
                            } else if (inputs[0].equalsIgnoreCase("turn")) {
                                processTurn(inputs);
                            } else if ((inputs[0].equalsIgnoreCase("truck")) && (inputs[1].equalsIgnoreCase("go"))) {
                                processTruckGo();
                            } else if (inputs[0].equalsIgnoreCase("build")) {
                                processBuild(inputs);
                            } else if (inputs[0].equalsIgnoreCase("exit")) {
                                System.exit(0);
                            } else {
                                System.out.println("Wrong input!!");
                            }
                        } catch (Exception e) {
                            System.out.println("Error, Wrong input!!");
                        }
                        if (manager.checkTasks()) {
                            checkFinishLevel();
                            break;
                        }
                        manager.grassCheck();
                    }
                    check(input);
                    if (manager.checkTasks()) {
                        checkFinishLevel();
                    }

                    if (inputs[0].equalsIgnoreCase("turn")) {
                        manager.Turn(counter);
                    }
                }
                // } else {
                //   System.out.println("ghofle!!");
                //}
                manager.readingLevels();
                setUser();
                manager.writeGsonUsers();
                manager.setNumOfUsers(manager.numOfUsers);
            }
        }
    }

    public InputProcossor(Manager manager) {
        this.manager = manager;
    }
}

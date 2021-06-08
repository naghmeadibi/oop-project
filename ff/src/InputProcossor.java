

import java.lang.reflect.Executable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputProcossor {
    Scanner scanner = new Scanner(System.in);
    String input="";
    Logger logger = Logger.getLogger("name");
    Manager manager = new Manager();
    int n = 1;
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

    public String processNameFactory(String[] strings) {
        String output = new String();
        try {
            if (strings[1].equalsIgnoreCase("mill")) {
                output = "mill";
            } else if (strings[1].equalsIgnoreCase("sewing")) {
                output = "sewing";
            } else if ((strings[1].equalsIgnoreCase("Packaging")) && (strings[2].equalsIgnoreCase("milk"))) {
                output = "packagingMilk";
            } else if (strings[1].equalsIgnoreCase("bakery")) {
                output = "bakery";
            } else if (strings[1].equalsIgnoreCase("weaving")) {
                output = "weaving";
            } else if ((strings[1].equalsIgnoreCase("Ice")) && (strings[2].equalsIgnoreCase("cream")) && (strings[3].equalsIgnoreCase("shop"))) {
                output = "iceCreamShop";
            } else {
                System.out.println("Wrong input!!");
            }
        } catch (Exception e) {
            System.out.println("Wrong input!!");
        }
        return output;
    }

    public void processWorkFactory(String[] strings) {
        try {
            int pointer = manager.foundFactory(processNameFactory(strings));
            if (strings[1].equalsIgnoreCase("mill")) {
                    if (pointer < manager.factory.factories.size()) {
                        if ((!orders.contains("addPowder")) ) {
                            if (manager.factory.factories.get(pointer).getLevel() == 1 && (manager.checkEgg())) {
                                orders.add("addPowder");
                                orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime1() + 1));
                            } else if (manager.factory.factories.get(pointer).getLevel() == 2&& (manager.checkEgg())) {
                                if(manager.checkEgg()) {
                                    orders.add("addPowder");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                    orders.add("addPowder");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                } else {
                                    orders.add("addPowder");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                }
                            }
                        } else if (orders.contains("addPowder")) {
                            System.out.println("Factory is working!!");
                            manager.logger.warning("Mill is working");
                        }
                    } else {
                        System.out.println("this factory doesn't exist!!");
                        manager.logger.warning("Mill is not built");
                    }

            } else if (strings[1].equalsIgnoreCase("sewing")) {
                    if (pointer < manager.factory.factories.size()) {
                        if ((!orders.contains("addCloth")) && (manager.checkFeather())) {
                            if (manager.factory.factories.get(pointer).getLevel() == 1) {
                                orders.add("addCloth");
                                orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime1() + 1));
                            } else if (manager.factory.factories.get(pointer).getLevel() == 2) {
                                if (manager.checkFeather()) {
                                    orders.add("addCloth");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                    orders.add("addCloth");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                } else {
                                    orders.add("addCloth");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                }
                            }
                        } else if (orders.contains("addCloth")) {
                            System.out.println("Factory is working!!");
                            manager.logger.warning("Sewing is working");
                        }
                    } else {
                        System.out.println("this factory doesn't exist!!");
                        manager.logger.warning("Sewing is not built");
                    }

            } else if ((strings[1].equalsIgnoreCase("packaging")) && (strings[2].equalsIgnoreCase("milk"))) {
                    if (pointer < manager.factory.factories.size()) {
                        if ((!orders.contains("addPackagedMilk")) && (manager.checkMilk())) {
                            if (manager.factory.factories.get(pointer).getLevel() == 1) {
                                orders.add("addPackagedMilk");
                                orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime1() + 1));
                            } else if (manager.factory.factories.get(pointer).getLevel() == 2) {
                                if(manager.checkMilk()) {
                                    orders.add("addPackagedMilk");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                    orders.add("addPackagedMilk");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                } else {
                                    orders.add("addPackagedMilk");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                }
                            }
                        } else if (orders.contains("addPackagedMilk")) {
                            System.out.println("Factory is working!!");
                            manager.logger.warning("Packaging milk is working");
                        }
                    } else {
                        System.out.println("this factory doesn't exist!!");
                        manager.logger.warning("Packaging Milk is not built");
                    }

            } else if (strings[1].equalsIgnoreCase("bakery")) {
                    if (pointer < manager.factory.factories.size()) {
                        if ((!orders.contains("addBread")) && (manager.checkPowder())) {
                            if (manager.factory.factories.get(pointer).getLevel() == 1) {
                                orders.add("addBread");
                                orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime1() + 1));
                            } else if (manager.factory.factories.get(pointer).getLevel() == 2) {
                                if (manager.checkPowder()) {
                                    orders.add("addBread");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                    orders.add("addBread");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                } else {
                                    orders.add("addBread");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                }
                            }
                        } else if (orders.contains("addBread")) {
                            System.out.println("Factory is working!!");
                            manager.logger.warning("Bakery is working");
                        }
                    } else {
                        System.out.println("this factory doesn't exist!!");
                        manager.logger.warning("Bakery is not built");
                    }

            } else if (strings[1].equalsIgnoreCase("weaving")) {
                    if (pointer < manager.factory.factories.size()) {
                        if ((!orders.contains("addShirt")) && (manager.checkCloth())) {
                            if (manager.factory.factories.get(pointer).getLevel() == 1) {
                                orders.add("addShirt");
                                orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime1() + 1));
                            } else if (manager.factory.factories.get(pointer).getLevel() == 2) {
                                if (manager.checkCloth()) {
                                    orders.add("addShirt");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                    orders.add("addShirt");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                } else {
                                    orders.add("addShirt");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                }
                            }
                        } else if (orders.contains("addShirt")) {
                            System.out.println("Factory is working!!");
                            manager.logger.warning("Weaving is working");
                        }
                    } else {
                        System.out.println("this factory doesn't exist!!");
                        manager.logger.warning("weaving is not built");
                    }

            } else if (processNameFactory(strings).equalsIgnoreCase("iceCreamShop")) {
                    if (pointer < manager.factory.factories.size()) {
                        if ((!orders.contains("addIceCream")) && (manager.checkPackagedMilk())) {
                            if (manager.factory.factories.get(pointer).getLevel() == 1) {
                                orders.add("addIceCream");
                                orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime1() + 1));
                            } else if (manager.factory.factories.get(pointer).getLevel() == 2) {
                                if (manager.checkPackagedMilk()) {
                                    orders.add("addIceCream");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                    orders.add("addIceCream");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                } else {
                                    orders.add("addIceCream");
                                    orders.add(String.valueOf(manager.factory.factories.get(pointer).getTime2() + 1));
                                }
                            }
                        } else if (orders.contains("addIceCream")) {
                            System.out.println("Factory is working!!");
                            manager.logger.warning("Ice cream shop is working");
                        }
                    } else {
                        System.out.println("this factory doesn't exist!!");
                        manager.logger.warning("Ice cream shop is not built");
                    }

            } else {
                System.out.println("Wrong Input!!");
                manager.logger.warning("Wrong input");
            }
        } catch(Exception e){
                System.out.println("Error,Wrong input!!");
                manager.logger.warning("Wrong format input");
            }
        }

    public void processWell(String[] strings) {
        if (!orders.contains("well")) {
            if (manager.checkWell()) {
                orders.add("well");
                orders.add("4");
            } else {
                System.out.println("well is not empty!!");
                manager.logger.warning("Well is not empty");
            }
        } else if (orders.contains("well")) {
            System.out.println("well is working!!");
            manager.logger.warning("Well is working");
        }
    }

    public void processCage(String[] strings) {
        try {
            int x = Integer.valueOf(strings[1]);
            int y = Integer.valueOf(strings[2]);
            if ((x > 0) && (x < 7) && (y > 0) && (y < 7)) {
                int coordinate = x * 10 + y;
                manager.cage(coordinate);
            } else {
                System.out.println("Error, Wrong coordinate!!");
                manager.logger.warning("Wrong coordinate");
            }
        } catch (Exception e) {
            System.out.println("Wrong input!!");
            manager.logger.warning("Wrong input format");
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
                manager.logger.warning("Wrong coordinate");
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong Input!!");
            manager.logger.warning("Wrong input format");
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
                manager.logger.warning("Wrong Coordinate");
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong Input!!");
            manager.logger.warning("Wrong format input");
        }
    }

    public void processTruckLoad(String[] strings) {
        try {
            if (!orders.contains("truckGo")) {
                manager.truckLoad(strings[2]);
            } else {
                System.out.println("The truck is moving");
                manager.logger.warning("Truck is moving");
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong input!!");
            manager.logger.warning("Wrong format input");
        }
    }

    public void processTruckUnload(String[] strings) {
        try {
            if (!orders.contains("truckGo")) {
                manager.truckUnload(strings[2]);
            } else {
                System.out.println("The truck is moving");
                manager.logger.warning("Truck is moving");
            }
        } catch (Exception e) {
            System.out.println("Error, Wrong input");
            manager.logger.warning("Wrong format input");
        }
    }

    public void processTruckGo() {
        if (!orders.contains("truckGo")) {
            manager.checkTruckGo();
            orders.add("truckGo");
            orders.add("11");
        } else if (orders.contains("truckGo")) {
            System.out.println("The truck is moving!!");
            manager.logger.warning("Truck is moving");
        }
    }

    public void processAddAnimals(String[] strings) {
        try {
            if (strings[1].equalsIgnoreCase("hen")) {
                manager.addHen();
            } else if (strings[1].equalsIgnoreCase("turkey")) {
                manager.addTurkey();
            } else if (strings[1].equalsIgnoreCase("buffalo")) {
                manager.addBuffalo();
            } else if (strings[1].equalsIgnoreCase("dog")) {
                manager.addDog();
            } else if (strings[1].equalsIgnoreCase("cat")) {
                manager.addCat();
            } else {
                System.out.println("Wrong input!");
                manager.logger.warning("Wrong input");
            }
        } catch (Exception e) {
            System.out.println("Wrong input!!");
            manager.logger.warning("Wrong format input");
        }
    }

    public void processTurn(String[] strings) {
        n = Integer.parseInt(strings[1]);
        for (int c = 0; c < n - 1 && finishLev; c++) {
            manager.unCage();
            manager.checkCagedAnimals();
            manager.catMove();
            manager.farmAnimalMove();
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
            manager.setCounter(manager.getCounter() + 1);
            manager.addWildAnimals(manager.getCounter());
            if (manager.checkTasks()) {
                checkFinishLevel();
            }
        }
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
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addCloth")) {
                    manager.addCloth();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addPackagedMilk")) {
                    manager.addPackagedMilk();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addBread")) {
                    manager.addBread();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addShirt")) {
                    manager.addShirt();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addIceCream")) {
                    manager.addIceCream();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("well")) {
                    manager.well();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("truckGo")) {
                    manager.truckGo();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                }
            }
        }
    }

    public void setUser() {
        //money = manager.users.get(manager.getIndexOfUser()).getMoney();
        playerLevel = manager.users.get(manager.getIndexOfUser()).getLevel() + 1;
    }

    public void check(String input) {
        if (!input.equalsIgnoreCase("turn 0")) {
            manager.unCage();
            manager.checkCagedAnimals();
            manager.catMove();
            manager.farmAnimalMove();
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
            manager.setCounter(manager.getCounter() + 1);
            manager.addWildAnimals(manager.getCounter());
        }
    }

    public void checkFinishLevel() {
        System.out.println("BABA KHAFAN");
        manager.logger.info("finished level " + String.valueOf(manager.getSelectedLevel()));
        if (manager.getSelectedLevel() >= manager.users.get(manager.getIndexOfUser()).getLevel()){
            if(manager.users.get(manager.getIndexOfUser()).getLevel() < manager.levels.size()){
                manager.users.get(manager.getIndexOfUser()).setLevel(manager.getSelectedLevel() + 1);
                System.out.println("now You can play level " + manager.users.get(manager.getIndexOfUser()).getLevel());
                manager.logger.info("Level " + String.valueOf(manager.users.get(manager.getIndexOfUser()).getLevel()) + " is open now");
            }
            if(manager.users.get(manager.getIndexOfUser()).getLevel()>manager.levels.size()){
                System.out.println("YOU FINISHED ALL LEVELS |^.^|");
            }
        }
        manager.moneySet(manager.getCounter());
        finishLev = false;
        manager.writeGsonUsers();
        setUser();
        manager.users.clear();
        manager.readUser();

    }

    public void processBuild(String[] strings) {
        try {
            if (strings[1].equalsIgnoreCase("mill")) {
                if (manager.checkMill()) {
                manager.buildFactory("mill");}
            } else if (strings[1].equalsIgnoreCase("sewing")) {
                if (manager.checkSewing()) {
                manager.buildFactory("sewing");}
            } else if ((strings[1].equalsIgnoreCase("Packaging")) && (strings[2].equalsIgnoreCase("milk"))) {
                if (manager.checkMilkPackaging()) {
                manager.buildFactory("packagingMilk");}
            } else if (strings[1].equalsIgnoreCase("bakery")) {
                if (manager.checkBakery()) {
                manager.buildFactory("bakery");}
            } else if (strings[1].equalsIgnoreCase("weaving")) {
                if (manager.checkWeaving()) {
                manager.buildFactory("weaving");}
            } else if ((strings[1].equalsIgnoreCase("Ice")) && (strings[2].equalsIgnoreCase("cream")) && (strings[3].equalsIgnoreCase("shop"))) {
                if (manager.checkIceCreamShop()) {
                manager.buildFactory("iceCreamShop");}
            } else {
                System.out.println("Wrong input!!");
                manager.logger.warning("Wrong input");
            }
        } catch (Exception e) {
            System.out.println("Wrong input!!");
            manager.logger.warning("Wrong format input");
        }
    }

    public void processUpgrade(String[] strings) {
        try {
            if (strings[1].equalsIgnoreCase("mill")) {
                manager.upgradeFactory("mill");
            } else if (strings[1].equalsIgnoreCase("sewing")) {
                manager.upgradeFactory("sewing");
            } else if ((strings[1].equalsIgnoreCase("Packaging")) && (strings[2].equalsIgnoreCase("milk"))) {
                manager.upgradeFactory("packagingMilk");
            } else if (strings[1].equalsIgnoreCase("bakery")) {
                manager.upgradeFactory("bakery");
            } else if (strings[1].equalsIgnoreCase("weaving")) {
                manager.upgradeFactory("weaving");
            } else if ((strings[1].equalsIgnoreCase("Ice")) && (strings[2].equalsIgnoreCase("cream")) && (strings[3].equalsIgnoreCase("shop"))) {
                manager.upgradeFactory("iceCreamShop");
            } else {
                System.out.println("Wrong input!!");
                manager.logger.warning("Wrong input");
            }
        } catch (Exception e) {
            System.out.println("Wrong input!!");
            manager.logger.warning("Wrong format input");

        }
    }

    public void run() {
        manager.setLogger();
        while (true) {
            if(input.equalsIgnoreCase("logout"))
            {checkMenu = true;}
            manager.readingLevels();
            manager.setCounter(0);
            manager.setBack();
            manager.menu(checkMenu);
            manager.coinSet();
            setUser();
            manager.setNumOfUsers(manager.numOfUsers);
            checkMenu = false;
            finishLev = true;
            input = "";
            while (finishLev &&!(input .equalsIgnoreCase("logout"))) {
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
                                if (!finishLev) {
                                    break;
                                }
                            } else if ((inputs[0].equalsIgnoreCase("truck")) && (inputs[1].equalsIgnoreCase("go"))) {
                                processTruckGo();
                            } else if (inputs[0].equalsIgnoreCase("build")) {
                                processBuild(inputs);
                            } else if (inputs[0].equalsIgnoreCase("upgrade")) {
                                processUpgrade(inputs);
                            } else if (inputs[0].equalsIgnoreCase("exit")) {
                                manager.logger.info("exit");
                                manager.writeGsonUsers();
                                System.exit(0);
                            } else {
                                System.out.println("Wrong input!!");
                            }
                         //   System.out.println(manager.store.stuff.toString());
                         //   System.out.println(orders.toString());
                        } catch (Exception e) {
                            System.out.println("Error, Wrong input!!");
                        }
                        manager.grassCheck();
                        if ((manager.checkTasks() && finishLev)) {
                            checkFinishLevel();
                            break;
                        }
                    }
                    check(input);
                    if ((manager.checkTasks()) && finishLev) {
                        checkFinishLevel();
                    }
                    if ((inputs[0].equalsIgnoreCase("turn")) && (finishLev)) {
                        manager.Turn(manager.getCounter());
                    }
                }
                manager.readingLevels();
                manager.setNumOfUsers(manager.numOfUsers);
            }
        }
    }

    public InputProcossor(Manager manager) {
        this.manager = manager;
    }
}

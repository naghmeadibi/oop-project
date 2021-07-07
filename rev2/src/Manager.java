import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Manager {
    Logger logger = Logger.getLogger("MyLog");
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    public int selectedLevel;
    Store store = new Store();
    Truck truck = new Truck();
    Hen hen = new Hen();
    Turkey turkey = new Turkey();
    Buffalo buffalo = new Buffalo();
    Products products = new Products();
    Factory factory = new Factory();
    WildAnimals wildAnimals = new WildAnimals();
    Farmanimals farmanimals = new Farmanimals();
    int wellWater = 50;
    public int coin = 10000;
    public LinkedList<Grass> grasses = new LinkedList<>();
    public LinkedList<Dog> dogs = new LinkedList<>();
    public LinkedList<Cat> cats = new LinkedList<>();
    public ArrayList<Level> levels = new ArrayList<>();
    public Level[] levells = new Level[20];
    int numOfUsers = getNumOfUsers();
    private int counter = 0;
    User[] userss = new User[numOfUsers + 1];
    ArrayList<User> users = new ArrayList<>();
    public LinkedList<String> orders = new LinkedList<>();
    public int indexOfUser = 0;
    int timeCounter = -1;


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setLogger() {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;
        try {
            fh = new FileHandler("LogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public int getIndexOfUser() {
        return indexOfUser;
    }

    public void well() {
        wellWater = 5;
        logger.info("The well was full");
    }

    public void plant(int x) {
        if (wellWater > 0) {
            wellWater--;
            grasses.add(new Grass(x));
            logger.info("plant " + (x));
        } else {
            logger.warning("Well is empty");
        }
    }

    public void check() {
        unCage();
        checkCagedAnimals();
        catCollect();
        bearAndLionToEat();
        tigerToEat();
        dogToCatch();
        lifeOfAnimals();
        productTimeHandle();
        grassToBeEaten();
        catMove();
        farmAnimalMove();
        moving();
        movingTiger();
        dogMove();
        animalProduct();
        timeHandle();
        setCounter(getCounter() + 1);
        addWildAnimals(getCounter());
        timeCounter++;

    }


    public int randomCoordinate() {
        int width = (random.nextInt(6) + 1) * 10;
        int height = random.nextInt(6) + 1;
        int coordinate = width + height;
        logger.info("Random coordinates were generated :" + (coordinate));
        return coordinate;
    }

    public void addEgg(int x) {
        Egg egg1 = new Egg(x);
        products.allUnPickedupedProducts.add(egg1);
        logger.info("Egg was produced " + (x) + " " + counter);
    }

    public boolean checkMill() {
        if (!users.get(getIndexOfUser()).workShops.get("mill")) {
            System.err.println("buy mill from setting");
            logger.warning("The mill has not been purchased");
            return false;
        } else
            return true;

    }

    public boolean checkBakery() {
        if (!users.get(getIndexOfUser()).workShops.get("bakery")) {
            System.err.println("buy bakery from setting");
            logger.warning("The bakery has not been purchased");
            return false;
        } else
            return true;

    }

    public boolean checkEgg() {

        if (counterProductInStore("egg") <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addPowder() {
        int x = 11;
        Powder powder1 = new Powder(x);
        products.allUnPickedupedProducts.add(powder1);
        logger.info("Powder was produced " + (x));
    }

    public boolean checkPowder() {
        if (counterProductInStore("powder") <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addBread() {
        int x = 16;
        Bread bread1 = new Bread(x);
        products.allUnPickedupedProducts.add(bread1);
        logger.info("Bread was produced " + (x) + " " + counter);
    }

    public void addFeather(int x) {
        Feather feather1 = new Feather(x);
        products.allUnPickedupedProducts.add(feather1);
        logger.info("Feather was produced " + (x) + counter);
    }

    public boolean checkWeaving() {
        if (!users.get(getIndexOfUser()).workShops.get("clothWeaving")) {
            System.err.println("buy weaving from setting");
            logger.warning("The weaving has not been purchased");
            return false;
        } else
            return true;
    }

    public boolean checkFeather() {
        if (counterProductInStore("feather") <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addCloth() {
        int x = 61;
        Cloth cloth1 = new Cloth(x);
        products.allUnPickedupedProducts.add(cloth1);
        logger.info("Cloth was produced " + (x) + counter);
    }

    public boolean checkSewing() {
        if (!users.get(getIndexOfUser()).workShops.get("Sewing")) {
            System.err.println("buy sewing from setting");
            logger.warning("The sewing has not been purchased");
            return false;
        } else
            return true;

    }

    public boolean checkCloth() {
        if (counterProductInStore("cloth") <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addShirt() {
        int x = 66;
        Shirt shirt1 = new Shirt(x);
        products.allUnPickedupedProducts.add(shirt1);
        logger.info("Shirt was produced " + (x) + " " + counter);
    }

    public void addMilk(int x) {
        Milk milk1 = new Milk(x);
        products.allUnPickedupedProducts.add(milk1);
        logger.info("Milk was produced " + (x) + " " + counter);
    }

    public boolean checkMilkPackaging() {
        if (!users.get(getIndexOfUser()).workShops.get("milkPackaging")) {
            System.err.println("buy milkPackaging from setting");
            logger.warning("The milk packaging has not been purchased");
            return false;
        } else
            return true;

    }

    public boolean checkMilk() {
        if (counterProductInStore("milk") <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addPackagedMilk() {
        int x = 31;
        PackagedMilk packagedMilk1 = new PackagedMilk(x);
        products.allUnPickedupedProducts.add(packagedMilk1);
        logger.info("packaged milk was produced " + (x) + " " + counter);
    }

    public boolean checkIceCreamShop() {
        if (!users.get(getIndexOfUser()).workShops.get("iceCreamShop")) {
            System.err.println("buy iceCreamShop from setting");
            logger.warning("The ice cream shop has not been purchased");
            return false;
        } else
            return true;

    }

    public boolean checkPackagedMilk() {

        if (counterProductInStore("packagedMilk") <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addIceCream() {
        int x = 36;
        IceCream iceCream1 = new IceCream(x);
        products.allUnPickedupedProducts.add(iceCream1);
        logger.info("ice cream was produced " + (x) + " " + counter);
    }

    public boolean checkWell() {
        return wellWater == 0;
    }


    public int pickUp(int x) {
        int check = 0;
        for (int i = 0; i < products.allUnPickedupedProducts.size(); i++) {
            if ((products.allUnPickedupedProducts.get(i).coordinate == x)) {
                check = 1;
                if (store.getCapacity() >= products.allUnPickedupedProducts.get(i).capacity) {
                    store.setCapacity(store.getCapacity() - products.allUnPickedupedProducts.get(i).capacity);
                    store.stuff.add(products.allUnPickedupedProducts.get(i));
                    logger.info("pickup and add to warehouse " + products.allUnPickedupedProducts.get(i).getName());
                    products.allUnPickedupedProducts.remove(i);
                }
                return 1;
            }
        }
        if (check == 0) {
            for (int i = 0; i < wildAnimals.cagedWildAnimals.size(); i++) {
                if ((wildAnimals.cagedWildAnimals.get(i).coordinate == x)) {
                    check = 1;
                    if (store.getCapacity() >= wildAnimals.cagedWildAnimals.get(i).capacity) {
                        store.setCapacity(store.getCapacity() - wildAnimals.cagedWildAnimals.get(i).capacity);
                        wildAnimals.storedWildAnimals.add(wildAnimals.cagedWildAnimals.get(i));
                        logger.info("pickup and add to warehouse " + wildAnimals.cagedWildAnimals.get(i).getName());
                        wildAnimals.cagedWildAnimals.remove(i);
                    }
                    return 1;
                }
            }
        }
        if (check == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean truckLoad(String name) {
        int check = 0;
        for (int i = 0; i < store.stuff.size(); i++) {
            if (store.stuff.get(i).name.equalsIgnoreCase(name)) {
                check = 1;
                if (truck.getCapacity() >= store.stuff.get(i).capacity) {
                    truck.setCapacity(truck.getCapacity() - store.stuff.get(i).capacity);
                    store.setCapacity(store.getCapacity() + store.stuff.get(i).capacity);
                    truck.setPrice(truck.getPrice() + store.stuff.get(i).getPrice());
                    truck.stuffToSell.add(store.stuff.get(i));
                    logger.info("add to truck " + store.stuff.get(i).getName());
                    store.stuff.remove(i);
                    return true;
                } else {
                    logger.warning("The truck does not have enough space");
                    return false;
                }
            }
        }
        if (check == 0) {
            for (int i = 0; i < wildAnimals.storedWildAnimals.size(); i++) {
                if (wildAnimals.storedWildAnimals.get(i).name.equalsIgnoreCase(name)) {
                    check = 1;
                    if (truck.getCapacity() >= wildAnimals.storedWildAnimals.get(i).capacity) {
                        truck.setCapacity(truck.getCapacity() - wildAnimals.storedWildAnimals.get(i).getCapacity());
                        store.setCapacity(store.getCapacity() + wildAnimals.storedWildAnimals.get(i).capacity);
                        truck.setPrice(truck.getPrice() + wildAnimals.storedWildAnimals.get(i).price);
                        truck.wildAnimalsToSell.add(wildAnimals.storedWildAnimals.get(i));
                        logger.info("add to truck " + wildAnimals.storedWildAnimals.get(i).getName());
                        wildAnimals.storedWildAnimals.remove(i);
                        return true;
                    } else {
                        logger.warning("The truck does not have enough space");
                        return false;
                    }
                }
            }
        }
        if (check == 0) {
            for (int i = 0; i < farmanimals.farmanimalss.size(); i++) {
                if (farmanimals.farmanimalss.get(i).name.equalsIgnoreCase(name)) {
                    check = 1;
                    if (truck.getCapacity() >= farmanimals.farmanimalss.get(i).capacity) {
                        truck.setCapacity(truck.getCapacity() - farmanimals.farmanimalss.get(i).capacity);
                        store.setCapacity(store.getCapacity() + farmanimals.farmanimalss.get(i).capacity);
                        truck.farmAnimalsToSell.add(farmanimals.farmanimalss.get(i));
                        truck.setPrice(truck.getPrice() + farmanimals.farmanimalss.get(i).getPrice());
                        logger.info("add to truck " + farmanimals.farmanimalss.get(i).getName());
                        farmanimals.farmanimalss.remove(i);
                        return true;
                    } else {
                        logger.warning("The truck does not have enough space");
                        return false;
                    }
                }
            }
        }
        if (check == 0) {
            logger.warning("This product doesn't exist in warehouse");
            return false;
        }
        return false;
    }

    public boolean truckUnload(String name) {
        int check = 0;
        for (int i = 0; i < truck.stuffToSell.size(); i++) {
            if (truck.stuffToSell.get(i).name.equalsIgnoreCase(name)) {
                check = 1;
                if (store.getCapacity() >= truck.stuffToSell.get(i).capacity) {
                    truck.setCapacity(truck.getCapacity() + truck.stuffToSell.get(i).capacity);
                    truck.setPrice(truck.getPrice() - truck.stuffToSell.get(i).getPrice());
                    store.setCapacity(store.getCapacity() - truck.stuffToSell.get(i).capacity);
                    store.stuff.add(truck.stuffToSell.get(i));
                    logger.info("back to warehouse " + truck.stuffToSell.get(i).getName());
                    truck.stuffToSell.remove(i);
                    return true;
                } else
                    return false;
            }
        }
        if (check == 0) {
            for (int i = 0; i < truck.wildAnimalsToSell.size(); i++) {
                if (truck.wildAnimalsToSell.get(i).name.equalsIgnoreCase(name)) {
                    check = 1;
                    if (store.getCapacity() >= truck.wildAnimalsToSell.get(i).capacity) {
                        truck.setCapacity(truck.getCapacity() + truck.wildAnimalsToSell.get(i).capacity);
                        store.setCapacity(store.getCapacity() - truck.wildAnimalsToSell.get(i).capacity);
                        wildAnimals.storedWildAnimals.add(truck.wildAnimalsToSell.get(i));
                        truck.setPrice(truck.getPrice() - truck.wildAnimalsToSell.get(i).getPrice());
                        logger.info("back to warehouse " + truck.wildAnimalsToSell.get(i).getName());
                        truck.wildAnimalsToSell.remove(i);
                        return true;
                    } else
                        return false;
                }
            }
        }
        if (check == 0) {
            for (int i = 0; i < truck.farmAnimalsToSell.size(); i++) {
                if (truck.farmAnimalsToSell.get(i).name.equalsIgnoreCase(name)) {
                    if (store.getCapacity() >= truck.farmAnimalsToSell.get(i).capacity) {
                        truck.setCapacity(truck.getCapacity() + truck.farmAnimalsToSell.get(i).capacity);
                        store.setCapacity(store.getCapacity() - truck.farmAnimalsToSell.get(i).capacity);
                        truck.setPrice(truck.getPrice() - truck.farmAnimalsToSell.get(i).getPrice());
                        farmanimals.farmanimalss.add(truck.farmAnimalsToSell.get(i));
                        logger.info("back to warehouse " + truck.farmAnimalsToSell.get(i).getName());
                        truck.farmAnimalsToSell.remove(i);
                        return true;
                    } else
                        return false;
                }
            }
        }
        if (check == 0) {
            logger.warning("This product is not in the truck");
            return false;
        }
        return false;
    }


    public void removeFromStoredList(String string) {
        for (int i = 0; i < store.stuff.size(); i++) {
            if (store.stuff.get(i).name.equalsIgnoreCase(string)) {
                store.setCapacity(store.getCapacity() + store.stuff.get(i).capacity);
                store.stuff.remove(i);
                break;
            }
        }
    }

    public int counterProductInStore(String string) {
        int counter = 0;
        for (int i = 0; i < store.stuff.size(); i++) {
            if (store.stuff.get(i).name.equalsIgnoreCase(string)) {
                counter++;
            }
        }
        return counter;
    }

    public void addHen() {
        if (coin >= hen.price) {
            Hen hen = new Hen(randomCoordinate());
            farmanimals.farmanimalss.add(hen);
            coin -= hen.price;
            logger.info("new hen " + (hen.getCoordinate()) + " " + counter);
        } else {
            System.err.println("you don't have enough coins!");
            logger.warning("There is not enough coin");
        }
    }

    public void addTurkey() {
        if (coin >= turkey.price) {
            Turkey turkey = new Turkey(randomCoordinate());
            farmanimals.farmanimalss.add(turkey);
            coin -= turkey.price;
            logger.info("new turkey " + (turkey.getCoordinate()) + " " + counter);
        } else {
            System.err.println("you don't have enough coins!");
            logger.warning("There is not enough coin");
        }
    }

    public void addBuffalo() {
        if (coin >= buffalo.price) {
            Buffalo buffalo = new Buffalo(randomCoordinate());
            farmanimals.farmanimalss.add(buffalo);
            coin -= buffalo.price;
            logger.info("new buffalo " + (buffalo.getCoordinate()) + " " + counter);
        } else {
            System.err.println("you don't have enough coins!");
            logger.warning("There is not enough coin");
        }
    }

    public int cage(int coordinates) {
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            if (wildAnimals.livingWildAnimals.get(cnt).coordinate == coordinates) {
                wildAnimals.livingWildAnimals.get(cnt).setLife(wildAnimals.livingWildAnimals.get(cnt).life - 2);
                logger.info("try cage " + wildAnimals.livingWildAnimals.get(cnt).getName() + " " + wildAnimals.livingWildAnimals.get(cnt).getLife());
                if (wildAnimals.livingWildAnimals.get(cnt).life == -1) {
                    wildAnimals.livingWildAnimals.get(cnt).setCaged(5);
                    wildAnimals.cagedWildAnimals.add(wildAnimals.livingWildAnimals.get(cnt));
                    logger.info("add to cage " + wildAnimals.livingWildAnimals.get(cnt).getName());
                    wildAnimals.livingWildAnimals.remove(cnt);
                }
                return 1;
            }
        }

        return 0;
    }

    public void unCage() {
        //اگه دستور بعد کیج خودش نباشه
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            if (wildAnimals.livingWildAnimals.get(cnt).life < wildAnimals.livingWildAnimals.get(cnt).getMaxLife()) {
                wildAnimals.livingWildAnimals.get(cnt).setLife(wildAnimals.livingWildAnimals.get(cnt).getLife() + 1);
            }
        }
    }

    public void checkCagedAnimals() {
        for (int cnt = 0; cnt < wildAnimals.cagedWildAnimals.size(); cnt++) {
            if (wildAnimals.cagedWildAnimals.get(cnt).getCaged() != 1) {
                wildAnimals.cagedWildAnimals.get(cnt).setCaged(wildAnimals.cagedWildAnimals.get(cnt).getCaged() - 1);
            } else {
                logger.info(wildAnimals.cagedWildAnimals.get(cnt).getName() + " escaped from the cage");
                wildAnimals.cagedWildAnimals.remove(cnt);
                cnt--;
            }
        }
    }

    public void checkTruckGo() {
        truck.stuffToSell.clear();
        truck.wildAnimalsToSell.clear();
        truck.farmAnimalsToSell.clear();
        truck.dogsToSell.clear();
        truck.catToSell.clear();
    }

    public void truckGo() {
        coin += truck.getPrice();
        truck.setPrice(0);
        truck.setCapacity(15);
        logger.info("The truck arrived");
    }

    public void moving() {
        for (int cnt = 0; cnt < farmanimals.farmanimalss.size(); cnt++) {
            if (farmanimals.farmanimalss.get(cnt).life > 5 || grasses.isEmpty()) {
                farmanimals.farmanimalss.get(cnt).setCoordinate(movingCoordinate(farmanimals.farmanimalss.get(cnt).coordinate));
            }
        }
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            if ((wildAnimals.livingWildAnimals.get(cnt) instanceof Bear) || (wildAnimals.livingWildAnimals.get(cnt) instanceof Lion)) {
                wildAnimals.livingWildAnimals.get(cnt).setCoordinate(movingCoordinate(wildAnimals.livingWildAnimals.get(cnt).coordinate));
            }
        }
        if (wildAnimals.livingWildAnimals.isEmpty()) {
            for (int cnt = 0; cnt < dogs.size(); cnt++) {
                Dog dog = dogs.get(cnt);
                dog.coordinate = movingCoordinate(dog.coordinate);
                dogs.set(cnt, dog);
            }
        }
        if (products.allUnPickedupedProducts.isEmpty()) {
            for (int cnt = 0; cnt < cats.size(); cnt++) {
                Cat cat = cats.get(cnt);
                cat.coordinate = movingCoordinate(cat.coordinate);
                cats.set(cnt, cat);
            }
        }
    }

    public int movingCoordinate(int coordinate) {
        int newCoordinate = coordinate;
        int randomMovement = random.nextInt(4);
        if (randomMovement == 0) {
            if (coordinate % 10 != 6)
                newCoordinate = coordinate + 1;
            else
                newCoordinate = coordinate - 1;
        }
        if (randomMovement == 1) {
            if (coordinate % 10 != 1)
                newCoordinate = coordinate - 1;
            else
                newCoordinate = coordinate + 1;
        }
        if (randomMovement == 2) {
            if (coordinate / 10 < 6)
                newCoordinate = coordinate + 10;
            else
                newCoordinate = coordinate - 10;
        }
        if (randomMovement == 3) {
            if (coordinate / 10 > 1)
                newCoordinate = coordinate - 10;
            else
                newCoordinate = coordinate + 10;
        }
        return newCoordinate;
    }

    public void grassToBeEaten() {
        int[][] henCheck = new int[grasses.size()][farmanimals.farmanimalss.size()];
        try {
            for (int cnt = 0; cnt < grasses.size(); cnt++) {

                for (int cnthen = 0; cnthen < farmanimals.farmanimalss.size(); cnthen += 1) {
                    if (grasses.get(cnt).coordinate == (farmanimals.farmanimalss.get(cnthen)).coordinate) {
                        henCheck[cnt][cnthen] = farmanimals.farmanimalss.get(cnthen).life;
                    } else
                        henCheck[cnt][cnthen] = Integer.MAX_VALUE;
                }
            }
            int c = 0;
            int size = grasses.size();
            for (int cnt = 0; cnt < size; cnt++) {
                int a = indexOfSmallest(henCheck[cnt]);
                int aa;
                if (farmanimals.farmanimalss.size() > 0) {
                    aa = Arrays.stream(henCheck[cnt]).min().getAsInt();
                } else {
                    aa = Integer.MAX_VALUE;
                }
                if (aa <= 5) {
                    farmanimals.farmanimalss.get(a).setLife(10);
                    logger.info(farmanimals.farmanimalss.get(a).getName() + " eat " + grasses.get(c).coordinate);
                    for (int i = 0; i < size; i++) {
                        henCheck[i][a] = Integer.MAX_VALUE;
                    }
                    grasses.remove(c);
                    c--;
                }
                c++;
            }
        } catch (Exception e) {

        }
    }

    public static int indexOfSmallest(int[] array) {

        // add this
        if (array.length == 0)
            return -1;

        int index = 0;
        int min = array[index];

        for (int i = 1; i < array.length; i++) {
            if (array[i] <= min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }

    public void bearAndLionToEat() {
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            if (!(wildAnimals.livingWildAnimals.get(cnt) instanceof Tiger)) {
                for (int cnthen = 0; cnthen < farmanimals.farmanimalss.size(); cnthen += 1) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == farmanimals.farmanimalss.get(cnthen).coordinate) {
                        logger.info(wildAnimals.livingWildAnimals.get(cnt).getName() + " eat " + farmanimals.farmanimalss.get(cnthen).getName());
                        farmanimals.farmanimalss.remove(cnthen);
                        cnthen--;
                    }
                }
                for (int cntbread = 0; cntbread < products.allUnPickedupedProducts.size(); cntbread += 1) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == products.allUnPickedupedProducts.get(cntbread).coordinate) {
                        logger.info(wildAnimals.livingWildAnimals.get(cnt).getName() + " destroyed " + products.allUnPickedupedProducts.get(cntbread).getName());
                        products.allUnPickedupedProducts.remove(cntbread);
                        cntbread--;
                    }
                }
                for (int cntt = 0; cntt < cats.size(); cntt++) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == cats.get(cntt).coordinate) {
                        logger.info(wildAnimals.livingWildAnimals.get(cnt).getName() + " eat cat ");
                        cats.remove(cntt);
                        cntt--;
                    }
                }
            }
        }
    }

    public int movingCoordinateTiger(int coordinate) {
        int newCoordinate = coordinate;
        int randomMovement = random.nextInt(4);
        if (randomMovement == 0) {
            if (coordinate % 10 < 5)
                newCoordinate = coordinate + 2;
            else
                newCoordinate = coordinate - 2;
        }
        if (randomMovement == 1) {
            if (coordinate % 10 > 2)
                newCoordinate = coordinate - 2;
            else
                newCoordinate = coordinate + 2;
        }
        if (randomMovement == 2) {
            if (coordinate / 10 < 5)
                newCoordinate = coordinate + 20;
            else
                newCoordinate = coordinate - 20;
        }
        if (randomMovement == 3) {
            if (coordinate / 10 > 2)
                newCoordinate = coordinate - 20;
            else
                newCoordinate = coordinate + 20;
        }
        return newCoordinate;
    }

    public void movingTiger() {
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            if (wildAnimals.livingWildAnimals.get(cnt) instanceof Tiger) {
                int inter = wildAnimals.livingWildAnimals.get(cnt).coordinate;
                wildAnimals.livingWildAnimals.get(cnt).setCoordinate(movingCoordinateTiger(wildAnimals.livingWildAnimals.get(cnt).coordinate));
                ((Tiger) wildAnimals.livingWildAnimals.get(cnt)).setInterCoordinate((inter + wildAnimals.livingWildAnimals.get(cnt).coordinate) / 2);
            }
        }
    }

    public void tigerToEat() {
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            if (wildAnimals.livingWildAnimals.get(cnt) instanceof Tiger) {
                for (int cnthen = 0; cnthen < farmanimals.farmanimalss.size(); cnthen++) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == farmanimals.farmanimalss.get(cnthen).coordinate || ((Tiger) wildAnimals.livingWildAnimals.get(cnt)).interCoordinate == farmanimals.farmanimalss.get(cnthen).coordinate) {
                        logger.info(wildAnimals.livingWildAnimals.get(cnt).getName() + " eat " + farmanimals.farmanimalss.get(cnthen).getName());
                        farmanimals.farmanimalss.remove(cnthen);
                        cnthen--;
                    }
                }
                for (int cntt = 0; cntt < products.allUnPickedupedProducts.size(); cntt++) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == products.allUnPickedupedProducts.get(cntt).coordinate || ((Tiger) wildAnimals.livingWildAnimals.get(cnt)).interCoordinate == products.allUnPickedupedProducts.get(cntt).coordinate) {
                        logger.info(wildAnimals.livingWildAnimals.get(cnt).getName() + " destroyed " + products.allUnPickedupedProducts.get(cntt).getName());
                        products.allUnPickedupedProducts.remove(cntt);
                        cntt--;
                    }

                }
                for (int cntt = 0; cntt < cats.size(); cntt++) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == cats.get(cntt).coordinate || ((Tiger) wildAnimals.livingWildAnimals.get(cnt)).interCoordinate == cats.get(cntt).coordinate) {
                        cats.remove(cntt);
                        cntt--;
                        logger.info(wildAnimals.livingWildAnimals.get(cnt).getName() + " eat cat ");
                    }

                }
            }
        }

    }

    public void dogToCatch() {
        for (int cnt = 0; cnt < dogs.size(); cnt++) {
            for (int cnttiger = 0; cnttiger < wildAnimals.livingWildAnimals.size(); cnttiger++) {
                if (dogs.get(cnt).coordinate == wildAnimals.livingWildAnimals.get(cnttiger).coordinate) {
                    logger.info(wildAnimals.livingWildAnimals.get(cnttiger).getName() + " escaped");
                    dogs.remove(cnt);
                    cnt--;
                    wildAnimals.livingWildAnimals.remove(cnttiger);
                    break;
                }
            }
        }
    }

    public void catCollect() {
        for (Cat cat : cats) {
            for (int cntt = 0; cntt < products.allUnPickedupedProducts.size(); cntt++) {
                if (cat.coordinate == products.allUnPickedupedProducts.get(cntt).coordinate) {
                    if (store.getCapacity() >= products.allUnPickedupedProducts.get(cntt).capacity) {
                        store.stuff.add(products.allUnPickedupedProducts.get(cntt));
                        store.setCapacity(store.getCapacity() - products.allUnPickedupedProducts.get(cntt).capacity);
                        logger.info("cat collect " + products.allUnPickedupedProducts.get(cntt).getName());
                        products.allUnPickedupedProducts.remove(cntt);
                        cntt--;
                    }
                }
            }
        }
    }

    public void animalCoordinatePrint() {
        for (int cnt = 0; cnt < farmanimals.farmanimalss.size(); cnt++) {
            System.out.println("name : " + farmanimals.farmanimalss.get(cnt).name + " coordinate : " + farmanimals.farmanimalss.get(cnt).coordinate + " life : " + farmanimals.farmanimalss.get(cnt).life);
        }
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            System.out.println("name : " + wildAnimals.livingWildAnimals.get(cnt).name + " coordinate : " + wildAnimals.livingWildAnimals.get(cnt).coordinate + " life : " + wildAnimals.livingWildAnimals.get(cnt).life);
        }
        if (!dogs.isEmpty()) {
            System.out.println("dogs");
            for (Dog dog : dogs) {
                System.out.println("coordinate : " + dog.coordinate);
            }
        }
        if (!cats.isEmpty()) {
            System.out.println("cats");
            for (Cat cat : cats) {
                System.out.println("coordinate : " + cat.coordinate);
            }
        }
    }

    public void lifeOfAnimals() {
        for (int cnt = 0; cnt < farmanimals.farmanimalss.size(); cnt++) {
            if (farmanimals.farmanimalss.get(cnt).getLife() == 1) {
                logger.info(farmanimals.farmanimalss.get(cnt).getName() + " died!");
                farmanimals.farmanimalss.remove(cnt);
                cnt--;
            } else
                farmanimals.farmanimalss.get(cnt).setLife(farmanimals.farmanimalss.get(cnt).getLife() - 1);
        }
    }

    public void addBear() {
        wildAnimals.livingWildAnimals.add(new Bear(randomCoordinate()));
        logger.info("new Bear");
    }

    public void addLion() {
        wildAnimals.livingWildAnimals.add(new Lion(randomCoordinate()));
        logger.info("new Lion");
    }

    public void addTiger() {
        wildAnimals.livingWildAnimals.add(new Tiger(randomCoordinate()));
        logger.info("new Tiger");
    }

    public void addDog() {
        if (coin >= 150) {
            dogs.add(new Dog(randomCoordinate()));
            coin -= 100;
            logger.info("new Dog");
        } else {
            System.err.println("you don't have enough coins!");
            logger.warning("There is not enough coin");
        }
    }

    public void addCat() {
        if (coin >= 150) {
            cats.add(new Cat(randomCoordinate()));
            coin -= 150;
            logger.info("new Cat");
        } else {
            System.err.println("you don't have enough coins!");
            logger.warning("There is not enough coin");
        }
    }

    public void animalProduct() {
        for (int cnt = 0; cnt < farmanimals.farmanimalss.size(); cnt++) {
            if (farmanimals.farmanimalss.get(cnt).getTimeNeededToProduce() <= 0) {
                if (farmanimals.farmanimalss.get(cnt) instanceof Hen) {
                    addEgg(farmanimals.farmanimalss.get(cnt).coordinate);
                    farmanimals.farmanimalss.get(cnt).setTimeNeededToProduce(2);
                } else if (farmanimals.farmanimalss.get(cnt) instanceof Turkey) {
                    addFeather(farmanimals.farmanimalss.get(cnt).coordinate);
                    farmanimals.farmanimalss.get(cnt).setTimeNeededToProduce(3);
                } else if (farmanimals.farmanimalss.get(cnt) instanceof Buffalo) {
                    addMilk(farmanimals.farmanimalss.get(cnt).coordinate);
                    farmanimals.farmanimalss.get(cnt).setTimeNeededToProduce(5);
                }
            } else {
                farmanimals.farmanimalss.get(cnt).setTimeNeededToProduce(farmanimals.farmanimalss.get(cnt).getTimeNeededToProduce() - 1);
            }
        }
    }

    public void grassCheck() {
        if (grasses.isEmpty()) {
            System.out.println("warning!! plant grass!!");
        }
    }

    public void productCoordinatePrint() {
        for (int cnt = 0; cnt < products.allUnPickedupedProducts.size(); cnt++) {
            System.out.println(products.allUnPickedupedProducts.get(cnt).name);
            System.out.println("coordinate : " + products.allUnPickedupedProducts.get(cnt).coordinate);
        }
    }

    public void Turn(int timeCounter) {

        System.out.println(timeCounter + " units of time passed :)");
        int[][] grassField = new int[6][6];
        for (int cn = 0; cn < 6; cn++)
            Arrays.fill(grassField[cn], 0);
        for (int cnt = 1; cnt < 7; cnt++) {
            for (int cn = 1; cn < 7; cn++) {
                for (Grass grass : grasses) {
                    if (grass.coordinate == (cnt * 10 + cn)) {
                        grassField[cnt - 1][cn - 1] += 1;
                    }
                }
            }
        }
        System.out.println("Grass Field");
        for (int cnt = 0; cnt < 6; cnt++) {
            for (int cn = 0; cn < 6; cn++) {
                System.out.print(grassField[cnt][cn] + " ");
            }
            System.out.println();
        }
        animalCoordinatePrint();
        productCoordinatePrint();
        if (!factory.factories.isEmpty()) {
            System.out.println("Factories : ");
        }
        for (int i = 0; i < factory.factories.size(); i++) {
            System.out.print(factory.factories.get(i).getName());
            System.out.println("  level = " + factory.factories.get(i).getLevel());
        }
        if (!wildAnimals.cagedWildAnimals.isEmpty()) {
            System.out.println("Caged Animals : ");
        }
        for (int i = 0; i < wildAnimals.cagedWildAnimals.size(); i++) {
            System.out.print(wildAnimals.cagedWildAnimals.get(i).getName());
            System.out.println("  Coordinate : " + wildAnimals.cagedWildAnimals.get(i).getCoordinate());
        }
        System.out.println("Coin$ = " + coin);
        System.out.println("Remaining tasks : ");
        printTask();
    }



    public void productTimeHandle() {
        for (int cnt = 0; cnt < products.allUnPickedupedProducts.size(); cnt++) {
            if (products.allUnPickedupedProducts.get(cnt).live <= 1) {
                logger.info(products.allUnPickedupedProducts.get(cnt).getName() + " removed");
                products.allUnPickedupedProducts.remove(cnt);
                cnt--;
            } else {
                products.allUnPickedupedProducts.get(cnt).setLive(products.allUnPickedupedProducts.get(cnt).getLive() - 1);
            }
        }
    }

    public void readingLevels() {
        try {
            levels.clear();
            Gson gson = new Gson();
            levells = gson.fromJson(new FileReader("levels.json"), Level[].class);
            Collections.addAll(levels, levells);
        } catch (Exception e) {

        }
    }

    public void coinSet() {
        coin = levels.get(selectedLevel - 1).coin;
    }

    public boolean checkLevel(int playerLvel) {
        coin = levels.get(selectedLevel - 1).coin;
        return selectedLevel <= playerLvel;
    }

    public void addWildAnimals(int counter) {
        if (levels.get(selectedLevel - 1).wildAnimalsHashMap.containsKey(counter)) {
            if (levels.get(selectedLevel - 1).wildAnimalsHashMap.get(counter).equalsIgnoreCase("lion")) {
                addLion();
            } else if (levels.get(selectedLevel - 1).wildAnimalsHashMap.get(counter).equalsIgnoreCase("bear")) {
                addBear();
            } else if (levels.get(selectedLevel - 1).wildAnimalsHashMap.get(counter).equalsIgnoreCase("tiger")) {
                addTiger();
            }
        }
    }

    public boolean checkTasks() {
        if (levels.get(selectedLevel - 1).tasks.containsKey("hen")) {
            if (levels.get(selectedLevel - 1).tasks.get("hen") <= countFarmAnimal("hen")) {
                logger.info("task was done : hen");
                levels.get(selectedLevel - 1).tasks.remove("hen");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("turkey")) {
            if (levels.get(selectedLevel - 1).tasks.get("turkey") <= countFarmAnimal("turkey")) {
                logger.info("task was done : turkey");
                levels.get(selectedLevel - 1).tasks.remove("turkey");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("buffalo")) {
            if (levels.get(selectedLevel - 1).tasks.get("buffalo") <= countFarmAnimal("buffalo")) {
                logger.info("task was done : buffalo");
                levels.get(selectedLevel - 1).tasks.remove("buffalo");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("egg")) {
            if (levels.get(selectedLevel - 1).tasks.get("egg") <= counterProductInStore("egg")) {
                logger.info("task was done : egg");
                levels.get(selectedLevel - 1).tasks.remove("egg");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("feather")) {
            if (levels.get(selectedLevel - 1).tasks.get("feather") <= counterProductInStore("feather")) {
                logger.info("task was done : feather");
                levels.get(selectedLevel - 1).tasks.remove("feather");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("milk")) {
            if (levels.get(selectedLevel - 1).tasks.get("milk") <= counterProductInStore("milk")) {
                logger.info("task was done : milk");
                levels.get(selectedLevel - 1).tasks.remove("milk");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("powder")) {
            if (levels.get(selectedLevel - 1).tasks.get("powder") <= counterProductInStore("powder")) {
                logger.info("task was done : powder");
                levels.get(selectedLevel - 1).tasks.remove("powder");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("cloth")) {
            if (levels.get(selectedLevel - 1).tasks.get("cloth") <= counterProductInStore("cloth")) {
                logger.info("task was done : cloth");
                levels.get(selectedLevel - 1).tasks.remove("cloth");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("packagedMilk")) {
            if (levels.get(selectedLevel - 1).tasks.get("packagedMilk") <= counterProductInStore("packagedMilk")) {
                logger.info("task was done : packaged milk");
                levels.get(selectedLevel - 1).tasks.remove("packagedMilk");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("bread")) {
            if (levels.get(selectedLevel - 1).tasks.get("bread") <= counterProductInStore("bread")) {
                logger.info("task was done : bread");
                levels.get(selectedLevel - 1).tasks.remove("bread");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("shirt")) {
            if (levels.get(selectedLevel - 1).tasks.get("shirt") <= counterProductInStore("shirt")) {
                logger.info("task was done : shirt");
                levels.get(selectedLevel - 1).tasks.remove("shirt");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("iceCream")) {
            if (levels.get(selectedLevel - 1).tasks.get("iceCream") <= counterProductInStore("iceCream")) {
                logger.info("task was done : ice cream");
                levels.get(selectedLevel - 1).tasks.remove("iceCream");
            }
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("coin")) {
            if (levels.get(selectedLevel - 1).tasks.get("coin") <= coin) {
                logger.info("task was done : coin");
                levels.get(selectedLevel - 1).tasks.remove("coin");
            }
        }
        return levels.get(selectedLevel - 1).tasks.isEmpty();
    }

    public int countFarmAnimal(String nameAnimal) {
        int counter = 0;
        for (int i = 0; i < farmanimals.farmanimalss.size(); i++) {
            if (farmanimals.farmanimalss.get(i).getName().equalsIgnoreCase(nameAnimal)) {
                counter++;
            }
        }
        return counter;
    }

    public void writeGsonUsers() {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        String user = gson.toJson(users);
        writeUsers(user);
    }

    public void writeUsers(String string) {
        try {
            FileWriter users = new FileWriter("users.json");
            users.append(string);
            users.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readUser() {
        try {
            users.clear();
            Gson gson = new Gson();
            userss = gson.fromJson(new FileReader("users.json"), User[].class);
            Collections.addAll(users, userss);
        } catch (Exception e) {

        }
    }





    public void menu1(boolean c) {
        String password;
        String username;
        readUser();
        boolean mainWhile = true;
        while (mainWhile && c) {
            System.out.println("Enter number of your choice : ");
            System.out.println("1) login");
            System.out.println("2) sign up");
            System.out.println("3) EXIT");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("enter your username :");
                    username = scanner.nextLine();
                    if (checkUsername(username) == -1) {
                        System.out.println("Invalid Username!!");
                        logger.warning("Invalid Username");
                    } else {
                        while (true) {
                            System.out.println("enter your password : ");
                            password = scanner.nextLine();
                            if (users.get(checkUsername(username)).getPassword().equalsIgnoreCase(password)) {
                                mainWhile = false;
                                System.out.println("done!");
                                logger.info("logged in successfully");
                                indexOfUser = checkUsername(username);
                                break;
                            } else {
                                logger.warning("wrong password");
                                System.out.println("Wrong Password, try again!!");
                            }
                        }
                    }
                    break;
                case "2":
                    while (true) {
                        System.out.println("enter your username :");
                        username = scanner.nextLine();
                        if (checkUsername(username) == -1) {
                            System.out.println("enter your password : ");
                            password = scanner.nextLine();
                            mainWhile = false;
                            indexOfUser = numOfUsers;
                            numOfUsers++;
                            setNumOfUsers(numOfUsers);
                            users.add(new User(username, password, 1, 0));
                            logger.info("signed up successfully");
                            break;
                        } else {
                            logger.warning("invalid username");
                            System.out.println("your username is invalid choose another ");
                        }
                    }
                    break;
                case "3":
                    writeGsonUsers();
                    logger.info("exit");
                    System.exit(0);
            }
        }
        while (true) {
            System.out.println("what shall we do next?");
            System.out.println("1) start [level] " + " 2) log out" + "  3) settings " + " 4) EXIT");
            String choice = scanner.nextLine();
            String[] strings = choice.split("\\s+");
            if (strings[0].equalsIgnoreCase("1")) {
                selectedLevel = Integer.parseInt(strings[1]);
                if (selectedLevel > users.get(getIndexOfUser()).getLevel()) {
                    logger.warning("wrong selected level");
                    System.out.println("You cant play this level");
                } else {
                    System.out.println("Go On");
                    logger.info("Start Game");
                    return;
                }
            } else if (strings[0].equalsIgnoreCase("2")) {
                c = true;
                logger.info("log out");
                menu(c);
                return;
            } else if (strings[0].equalsIgnoreCase("3")) {
                logger.info("enter setting");
                System.out.println("you wanna buy?");
                System.out.println("1) mill 2) weaving 3) milkPackaging 4) bakery 5) sewing 6) iceCreamShop ");
                String workShop = scanner.nextLine();
                if (workShop.equals("1")) {
                    if (!users.get(indexOfUser).workShops.get("mill")) {
                        if (users.get(indexOfUser).getMoney() >= 150) {
                            users.get(indexOfUser).workShops.replace("mill", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 150);
                            logger.info("Mill was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("mill is already taken");
                        logger.warning("mill is already taken");
                    }
                }
                if (workShop.equals("2")) {
                    if (!users.get(indexOfUser).workShops.get("clothWeaving")) {
                        if (users.get(indexOfUser).getMoney() >= 250) {
                            users.get(indexOfUser).workShops.replace("clothWeaving", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 250);
                            logger.info("ClothWeaving was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("clothWeaving is already taken");
                        logger.warning("clothWeaving is already taken");
                    }
                }
                if (workShop.equals("3")) {
                    if (!users.get(indexOfUser).workShops.get("milkPackaging")) {
                        if (users.get(indexOfUser).getMoney() >= 400) {
                            users.get(indexOfUser).workShops.replace("milkPackaging", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 400);
                            logger.info("MilkPackaging was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("milkPackaging is already taken");
                        logger.warning("milkPackaging is already taken");
                    }
                }
                if (workShop.equals("4")) {
                    if (!users.get(indexOfUser).workShops.get("bakery")) {
                        if (users.get(indexOfUser).getMoney() >= 250) {
                            users.get(indexOfUser).workShops.replace("bakery", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 250);
                            logger.info("Bakery was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("bakery is already taken");
                        logger.warning("bakery is already taken");
                    }
                }
                if (workShop.equals("5")) {
                    if (!users.get(indexOfUser).workShops.get("Sewing")) {
                        if (users.get(indexOfUser).getMoney() >= 400) {
                            users.get(indexOfUser).workShops.replace("Sewing", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 400);
                            logger.info("Sewing was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("sewing is already taken");
                        logger.warning("sewing is already taken");
                    }
                }
                if (workShop.equals("6")) {
                    if (!users.get(indexOfUser).workShops.get("iceCreamShop")) {
                        if (users.get(indexOfUser).getMoney() >= 550) {
                            users.get(indexOfUser).workShops.replace("iceCreamShop", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 550);
                            logger.info("IceCreamShop was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("iceCreamShop is already taken");
                        logger.warning("iceCreamShop is already taken");
                    }
                }
                writeGsonUsers();
            } else if (choice.equals("4")) {
                writeGsonUsers();
                logger.info("exit");
                System.exit(0);
            }

        }
    }




    String password;
    String username;
    String choice ;
    public int menu(boolean c) {
        readUser();
        boolean mainWhile = true;
        while (mainWhile && c) {
            switch (choice) {
                case "1":
                    if (checkUsername(username) == -1) {
                        logger.warning("Invalid Username");
                        return -1;
                    } else {
                        if (users.get(checkUsername(username)).getPassword().equalsIgnoreCase(password)) {
                            logger.info("logged in successfully");
                            indexOfUser = checkUsername(username);
                            return 1;
                        } else {
                            logger.warning("wrong password");
                            return 0;
                        }

                    }
                case "2":
                    if (checkUsername(username) == -1) {
                        indexOfUser = numOfUsers;
                        numOfUsers++;
                        setNumOfUsers(numOfUsers);
                        users.add(new User(username, password, 1, 0));
                        logger.info("signed up successfully");
                        return 2;
                    } else {
                        logger.warning("invalid username");
                        return 3;
                    }
            }
        }
        return 0;
    }
    public void secondMenu(boolean c){
        while (true) {
            System.out.println("what shall we do next?");
            System.out.println("1) start [level] " + " 2) log out" + "  3) settings " + " 4) EXIT");
            String choice = scanner.nextLine();
            String[] strings = choice.split("\\s+");
            if (strings[0].equalsIgnoreCase("1")) {
                selectedLevel = Integer.parseInt(strings[1]);
                if (selectedLevel > users.get(getIndexOfUser()).getLevel()) {
                    logger.warning("wrong selected level");
                    System.out.println("You cant play this level");
                } else {
                    System.out.println("Go On");
                    logger.info("Start Game");
                    return;
                }
            } else if (strings[0].equalsIgnoreCase("2")) {
                c = true;
                logger.info("log out");
                menu(c);
                return;
            } else if (strings[0].equalsIgnoreCase("3")) {
                logger.info("enter setting");
                System.out.println("you wanna buy?");
                System.out.println("1) mill 2) weaving 3) milkPackaging 4) bakery 5) sewing 6) iceCreamShop ");
                String workShop = scanner.nextLine();
                if (workShop.equals("1")) {
                    if (!users.get(indexOfUser).workShops.get("mill")) {
                        if (users.get(indexOfUser).getMoney() >= 150) {
                            users.get(indexOfUser).workShops.replace("mill", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 150);
                            logger.info("Mill was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("mill is already taken");
                        logger.warning("mill is already taken");
                    }
                }
                if (workShop.equals("2")) {
                    if (!users.get(indexOfUser).workShops.get("clothWeaving")) {
                        if (users.get(indexOfUser).getMoney() >= 250) {
                            users.get(indexOfUser).workShops.replace("clothWeaving", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 250);
                            logger.info("ClothWeaving was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("clothWeaving is already taken");
                        logger.warning("clothWeaving is already taken");
                    }
                }
                if (workShop.equals("3")) {
                    if (!users.get(indexOfUser).workShops.get("milkPackaging")) {
                        if (users.get(indexOfUser).getMoney() >= 400) {
                            users.get(indexOfUser).workShops.replace("milkPackaging", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 400);
                            logger.info("MilkPackaging was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("milkPackaging is already taken");
                        logger.warning("milkPackaging is already taken");
                    }
                }
                if (workShop.equals("4")) {
                    if (!users.get(indexOfUser).workShops.get("bakery")) {
                        if (users.get(indexOfUser).getMoney() >= 250) {
                            users.get(indexOfUser).workShops.replace("bakery", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 250);
                            logger.info("Bakery was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("bakery is already taken");
                        logger.warning("bakery is already taken");
                    }
                }
                if (workShop.equals("5")) {
                    if (!users.get(indexOfUser).workShops.get("Sewing")) {
                        if (users.get(indexOfUser).getMoney() >= 400) {
                            users.get(indexOfUser).workShops.replace("Sewing", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 400);
                            logger.info("Sewing was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("sewing is already taken");
                        logger.warning("sewing is already taken");
                    }
                }
                if (workShop.equals("6")) {
                    if (!users.get(indexOfUser).workShops.get("iceCreamShop")) {
                        if (users.get(indexOfUser).getMoney() >= 550) {
                            users.get(indexOfUser).workShops.replace("iceCreamShop", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 550);
                            logger.info("IceCreamShop was bought");
                        } else {
                            System.out.println("you don't have enough money");
                            logger.warning("There is not enough money");
                        }
                    } else {
                        System.out.println("iceCreamShop is already taken");
                        logger.warning("iceCreamShop is already taken");
                    }
                }
                writeGsonUsers();
            } else if (choice.equals("4")) {
                writeGsonUsers();
                logger.info("exit");
                System.exit(0);
            }

        }
    }



    String[] tasks = new String[13];
    public String[] printTask() {
        if (levels.get(selectedLevel - 1).tasks.containsKey("hen")) {
            tasks[0] = ("HEN " + countFarmAnimal("hen") + "/" + levels.get(selectedLevel - 1).tasks.get("hen"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("turkey")) {
            tasks[1] = (countFarmAnimal("turkey") + "/" + levels.get(selectedLevel - 1).tasks.get("turkey"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("buffalo")) {
            System.out.print("Buffalo : ");
            System.out.println(countFarmAnimal("buffalo") + "/" + levels.get(selectedLevel - 1).tasks.get("buffalo"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("egg")) {
            tasks [3] = ("EGG " + counterProductInStore("egg") + "/" + levels.get(selectedLevel - 1).tasks.get("egg"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("feather")) {
            System.out.print("Feather : ");
            System.out.println(counterProductInStore("feather") + "/" + levels.get(selectedLevel - 1).tasks.get("feather"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("milk")) {
            System.out.print("Milk : ");
            System.out.println(counterProductInStore("milk") + "/" + levels.get(selectedLevel - 1).tasks.get("milk"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("powder")) {
            System.out.print("Powder : ");
            System.out.println(counterProductInStore("powder") + "/" + levels.get(selectedLevel - 1).tasks.get("powder"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("cloth")) {
            System.out.print("Cloth : ");
            System.out.println(counterProductInStore("cloth") + "/" + levels.get(selectedLevel - 1).tasks.get("cloth"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("packagedMilk")) {
            System.out.print("Packaged Milk : ");
            System.out.println(counterProductInStore("packagedMilk") + "/" + levels.get(selectedLevel - 1).tasks.get("packagedMilk"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("bread")) {
            System.out.print("Bread : ");
            System.out.println(counterProductInStore("bread") + "/" + levels.get(selectedLevel - 1).tasks.get("bread"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("shirt")) {
            System.out.print("Shirt : ");
            System.out.println(counterProductInStore("shirt") + "/" + levels.get(selectedLevel - 1).tasks.get("shirt"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("iceCream")) {
            System.out.print("Ice Cream : ");
            System.out.println(counterProductInStore("iceCream") + "/" + levels.get(selectedLevel - 1).tasks.get("iceCream"));
        }
        if (levels.get(selectedLevel - 1).tasks.containsKey("coin")) {
            System.out.print("Coin : ");
            System.out.println(coin + "/" + levels.get(selectedLevel - 1).tasks.get("coin"));
        }
        return tasks;
    }






    public int checkUsername(String userName) {
        int check = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(userName)) {
                check = i;
                break;
            }
        }
        return check;
    }

    public int getNumOfUsers() {
        int x = 0;
        File file = new File("numOfUsers.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            x = Integer.parseInt(scanner.next());
        }
        return x;
    }

    public void setNumOfUsers(int number) {
        try {
            FileWriter fileWriter = new FileWriter("numOfUsers.txt");
            fileWriter.append(String.valueOf(number));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moneySet(int timeCounter) {

        boolean check = true;

        for (Map.Entry<Integer, Integer> entry : levels.get(getSelectedLevel() - 1).time.entrySet()) {
            if (entry.getValue() == 400) {
                if (timeCounter <= entry.getKey()) {
                    timeCounter = entry.getKey();
                    check = false;
                    break;
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : levels.get(getSelectedLevel() - 1).time.entrySet()) {
            if (entry.getValue() == 200 && check) {
                if (timeCounter <= entry.getKey()) {
                    timeCounter = entry.getKey();
                    check = false;
                    break;
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : levels.get(getSelectedLevel() - 1).time.entrySet()) {
            if (entry.getValue() == 100 && check) {
                if (timeCounter <= entry.getKey()) {
                    timeCounter = entry.getKey();
                    break;
                }
            }
        }


        users.get(getIndexOfUser()).setMoney(users.get(getIndexOfUser()).getMoney() + levels.get(getSelectedLevel() - 1).time.get(timeCounter));
        if (levels.get(getSelectedLevel() - 1).time.get(timeCounter) == 400) {
            System.out.println("GOLDEN");
            logger.info("Golden");
        } else if (levels.get(getSelectedLevel() - 1).time.get(timeCounter) == 200) {
            System.out.println("SILVER");
            logger.info("Silver");
        } else if (levels.get(getSelectedLevel() - 1).time.get(timeCounter) == 100) {
            System.out.println("BRONZE");
            logger.info("Bronze");
        }
    }

    public void setBack() {
        store.setCapacity(30);
        truck.setCapacity(15);
        truck.setPrice(0);
        wellWater = 5;
        farmanimals.farmanimalss.clear();
        products.allUnPickedupedProducts.clear();
        store.stuff.clear();
        wildAnimals.cagedWildAnimals.clear();
        wildAnimals.storedWildAnimals.clear();
        wildAnimals.livingWildAnimals.clear();
        truck.wildAnimalsToSell.clear();
        truck.stuffToSell.clear();
        truck.dogsToSell.clear();
        truck.catToSell.clear();
        truck.farmAnimalsToSell.clear();
        cats.clear();
        dogs.clear();
        factory.factories.clear();
        grasses.clear();
    }

    public void buildFactory(String factoryName) {
        if (checkFactory(factoryName)) {
            System.out.println("this factory already exist!!");
            logger.warning(factoryName + " already exist");
        } else {
            if (factoryName.equalsIgnoreCase("mill")) {
                if (coin >= 150) {
                    factory.factories.add(new Mill());
                    coin -= 150;
                    logger.info("Mill was built");
                } else {
                    System.out.println("You don't have enough money!");
                    logger.warning("There is not enough money");
                }
            } else if (factoryName.equalsIgnoreCase("sewing")) {
                if (coin >= 250) {
                    factory.factories.add(new Sewing());
                    coin -= 250;
                    logger.info("Sewing was built");
                } else {
                    System.out.println("You don't have enough money!");
                    logger.warning("There is not enough money");
                }
            } else if (factoryName.equalsIgnoreCase("milkPackaging")) {
                if (coin >= 400) {
                    factory.factories.add(new MilkPackaging());
                    coin -= 400;
                    logger.info("Milk packaging was built");
                } else {
                    System.out.println("You don't have enough money!");
                    logger.warning("There is not enough money");
                }
            } else if (factoryName.equalsIgnoreCase("bakery")) {
                if (coin >= 250) {
                    factory.factories.add(new Bakery());
                    coin -= 250;
                    logger.info("Bakery was built");
                } else {
                    System.out.println("You don't have enough money!");
                    logger.warning("There is not enough money");
                }
            } else if (factoryName.equalsIgnoreCase("weaving")) {
                if (coin >= 400) {
                    factory.factories.add(new Weaving());
                    coin -= 400;
                    logger.info("Weaving was built");
                } else {
                    System.out.println("You don't have enough money!");
                    logger.warning("There is not enough money");
                }
            } else if (factoryName.equalsIgnoreCase("iceCreamShop")) {
                if (coin >= 550) {
                    factory.factories.add(new IcecreamShop());
                    coin -= 550;
                    logger.info("Ice Cream Shop was built");
                } else {
                    System.out.println("You don't have enough money!");
                    logger.warning("There is not enough money");
                }
            } else {
                System.out.println("Wrong input!!");
                logger.warning("Wrong input");
            }
        }
    }

    public boolean checkFactory(String string) {
        int check = 0;
        for (int i = 0; i < factory.factories.size(); i++) {
            if (factory.factories.get(i).getName().equalsIgnoreCase(string)) {
                check = 1;
                break;
            }
        }
        return check == 1;
    }

    public void upgradeFactory(String factoryName) {
        int pointer = foundFactory(factoryName);
        if (factoryName.equalsIgnoreCase("mill")) {
            if (pointer < factory.factories.size()) {
                if (factory.factories.get(pointer).getLevel() == 1) {
                    if (coin >= 250) {
                        factory.factories.get(pointer).setLevel(factory.factories.get(foundFactory("mill")).getLevel() + 1);
                        coin -= 250;
                    } else {
                        System.out.println("You don't have enough coin!");
                        logger.warning("There is not enough coin");
                    }
                } else {
                    System.out.println("Max Level");
                    logger.warning("Max Level");
                }
            } else {
                System.out.println("This factory was not built");
                logger.warning("Mill was not built");
            }
        } else if (factoryName.equalsIgnoreCase("sewing")) {
            if (pointer < factory.factories.size()) {
                if (factory.factories.get(pointer).getLevel() == 1) {
                    if (coin >= 750) {
                        factory.factories.get(foundFactory("sewing")).setLevel(factory.factories.get(foundFactory("sewing")).getLevel() + 1);
                        coin -= 750;
                    } else {
                        System.out.println("You don't have enough coin!");
                        logger.warning("There is not enough coin");
                    }
                } else {
                    System.out.println("Max Level");
                    logger.warning("Max Level");
                }
            } else {
                System.out.println("This factory was not built");
                logger.warning("Sewing was not built");
            }
        } else if (factoryName.equalsIgnoreCase("milkPackaging")) {
            if (pointer < factory.factories.size()) {
                if (factory.factories.get(pointer).getLevel() == 1) {
                    if (coin >= 750) {
                        factory.factories.get(foundFactory("milkPackaging")).setLevel(factory.factories.get(foundFactory("milkPackaging")).getLevel() + 1);
                        coin -= 750;
                    } else {
                        System.out.println("You don't have enough coin!");
                        logger.warning("There is not enough coin");
                    }
                } else {
                    System.out.println("Max Level");
                    logger.warning("Max Level");
                }
            } else {
                System.out.println("This factory was not built");
                logger.warning("Milk packaging was not built");
            }
        } else if (factoryName.equalsIgnoreCase("bakery")) {
            if (pointer < factory.factories.size()) {
                if (factory.factories.get(pointer).getLevel() == 1) {
                    if (coin >= 400) {
                        factory.factories.get(foundFactory("bakery")).setLevel(factory.factories.get(foundFactory("bakery")).getLevel() + 1);
                        coin -= 400;
                    } else {
                        System.out.println("You don't have enough coin!");
                        logger.warning("There is not enough coin");
                    }
                } else {
                    System.out.println("Max Level");
                    logger.warning("Max Level");
                }
            } else {
                System.out.println("This factory was not built");
                logger.warning("Bakery was not built");
            }
        } else if (factoryName.equalsIgnoreCase("weaving")) {
            if (pointer < factory.factories.size()) {
                if (factory.factories.get(pointer).getLevel() == 1) {
                    if (coin >= 750) {
                        factory.factories.get(foundFactory("weaving")).setLevel(factory.factories.get(foundFactory("weaving")).getLevel() + 1);
                        coin -= 750;
                    } else {
                        System.out.println("You don't have enough coin!");
                        logger.warning("There is not enough coin");
                    }
                } else {
                    System.out.println("Max Level");
                    logger.warning("Max Level");
                }
            } else {
                System.out.println("This factory was not built");
                logger.warning("Weaving was not built");
            }
        } else if (factoryName.equalsIgnoreCase("iceCreamShop")) {
            if (pointer < factory.factories.size()) {
                if (factory.factories.get(pointer).getLevel() == 1) {
                    if (coin >= 1000) {
                        factory.factories.get(foundFactory("iceCreamShop")).setLevel(factory.factories.get(foundFactory("iceCreamShop")).getLevel() + 1);
                        coin -= 1000;
                    } else {
                        System.out.println("You don't have enough coin!");
                        logger.warning("There is not enough coin");
                    }
                } else {
                    System.out.println("Max Level");
                    logger.warning("Max Level");
                }
            } else {
                System.out.println("This factory was not built");
                logger.warning("Ice cream shop was not built");
            }
        } else {
            System.out.println("Wrong input!!");
        }
    }

    public int foundFactory(String name) {
        int i;
        for (i = 0; i < factory.factories.size(); i++) {
            if (factory.factories.get(i).getName().equalsIgnoreCase(name)) {
                break;
            }
        }
        return i;
    }

    public boolean checkFactoryLevel(String factoryName) {
        int check = 0;
        for (int i = 0; i < factory.factories.size(); i++) {
            if ((factory.factories.get(i).getName().equalsIgnoreCase(factoryName)) && (factory.factories.get(i).getLevel() == 1)) {
                check = 1;
                break;
            }
        }
        return check == 1;
    }

    public void catMove() {
        double distance;
        int index = 0;
        if (!products.allUnPickedupedProducts.isEmpty()) {
            for (int i = 0; i < cats.size(); i++) {
                int x = cats.get(i).coordinate / 10;
                int y = cats.get(i).coordinate % 10;
                distance = 100.0;
                for (int j = 0; j < products.allUnPickedupedProducts.size(); j++) {
                    int z = products.allUnPickedupedProducts.get(j).coordinate / 10;
                    int w = products.allUnPickedupedProducts.get(j).coordinate % 10;
                    double distance1 = Math.pow((x - z), 2) + Math.pow((y - w), 2);
                    if (distance1 < distance) {
                        distance = distance1;
                        index = j;
                    }
                }
                movingTargetCat(index, i);
            }
        }
    }

    public void movingTargetCat(int indexOfProduct, int indexOfAnimal) {
        if (products.allUnPickedupedProducts.get(indexOfProduct).coordinate / 10 == cats.get(indexOfAnimal).coordinate / 10) {
            if (products.allUnPickedupedProducts.get(indexOfProduct).coordinate % 10 > cats.get(indexOfAnimal).coordinate % 10)
                cats.get(indexOfAnimal).coordinate = cats.get(indexOfAnimal).coordinate + 1;
            else
                cats.get(indexOfAnimal).coordinate = cats.get(indexOfAnimal).coordinate - 1;
        } else {
            if (products.allUnPickedupedProducts.get(indexOfProduct).coordinate / 10 > cats.get(indexOfAnimal).coordinate / 10)
                cats.get(indexOfAnimal).coordinate = cats.get(indexOfAnimal).coordinate + 10;
            else
                cats.get(indexOfAnimal).coordinate = cats.get(indexOfAnimal).coordinate - 10;
        }
    }

    public void movingTargetAnimal(int indexOfProduct, int indexOfAnimal) {
        if (grasses.get(indexOfProduct).coordinate / 10 == farmanimals.farmanimalss.get(indexOfAnimal).coordinate / 10) {
            if (grasses.get(indexOfProduct).coordinate % 10 > farmanimals.farmanimalss.get(indexOfAnimal).coordinate % 10)
                farmanimals.farmanimalss.get(indexOfAnimal).coordinate = farmanimals.farmanimalss.get(indexOfAnimal).coordinate + 1;
            else
                farmanimals.farmanimalss.get(indexOfAnimal).coordinate = farmanimals.farmanimalss.get(indexOfAnimal).coordinate - 1;
        } else {
            if (grasses.get(indexOfProduct).coordinate / 10 > farmanimals.farmanimalss.get(indexOfAnimal).coordinate / 10)
                farmanimals.farmanimalss.get(indexOfAnimal).coordinate = farmanimals.farmanimalss.get(indexOfAnimal).coordinate + 10;
            else
                farmanimals.farmanimalss.get(indexOfAnimal).coordinate = farmanimals.farmanimalss.get(indexOfAnimal).coordinate - 10;
        }
    }

    public void farmAnimalMove() {
        double distance;
        int index = 0;
        if (!grasses.isEmpty()) {
            for (int i = 0; i < farmanimals.farmanimalss.size(); i++) {
                if (farmanimals.farmanimalss.get(i).life <= 5) {
                    int x = farmanimals.farmanimalss.get(i).coordinate / 10;
                    int y = farmanimals.farmanimalss.get(i).coordinate % 10;
                    distance = 100.0;
                    for (int j = 0; j < grasses.size(); j++) {
                        int z = grasses.get(j).coordinate / 10;
                        int w = grasses.get(j).coordinate % 10;
                        double v = Math.pow((x - z), 2) + Math.pow((y - w), 2);
                        if (v < distance) {
                            distance = v;
                            index = j;
                        }
                    }
                    movingTargetAnimal(index, i);
                }
            }
        }
    }

    public void dogMove() {
        double distance;
        int index = 0;
        if (!wildAnimals.livingWildAnimals.isEmpty()) {
            for (int i = 0; i < dogs.size(); i++) {
                int x = dogs.get(i).coordinate / 10;
                int y = dogs.get(i).coordinate % 10;
                distance = 100.0;
                for (int j = 0; j < wildAnimals.livingWildAnimals.size(); j++) {
                    int z = wildAnimals.livingWildAnimals.get(j).coordinate / 10;
                    int w = wildAnimals.livingWildAnimals.get(j).coordinate % 10;
                    double v = Math.pow((x - z), 2) + Math.pow((y - w), 2);
                    if (v < distance) {
                        distance = v;
                        index = j;
                    }
                }
                movingTargetDog(index, i);
            }
        }
    }

    public void movingTargetDog(int indexOfWildAnimal, int indexOfAnimal) {
        if (wildAnimals.livingWildAnimals.get(indexOfWildAnimal).coordinate / 10 == dogs.get(indexOfAnimal).coordinate / 10) {
            if (wildAnimals.livingWildAnimals.get(indexOfWildAnimal).coordinate % 10 > dogs.get(indexOfAnimal).coordinate % 10)
                dogs.get(indexOfAnimal).coordinate = dogs.get(indexOfAnimal).coordinate + 1;
            else
                dogs.get(indexOfAnimal).coordinate = dogs.get(indexOfAnimal).coordinate - 1;
        } else {
            if (wildAnimals.livingWildAnimals.get(indexOfWildAnimal).coordinate / 10 > dogs.get(indexOfAnimal).coordinate / 10)
                dogs.get(indexOfAnimal).coordinate = dogs.get(indexOfAnimal).coordinate + 10;
            else
                dogs.get(indexOfAnimal).coordinate = dogs.get(indexOfAnimal).coordinate - 10;
        }
    }


    public void timeHandle() {
        for (int cnt = 1; cnt < orders.size(); cnt += 2) {
            orders.set(cnt, String.valueOf(Integer.parseInt(orders.get(cnt)) - 1));
        }
        for (int cnt = 1; cnt < orders.size(); cnt += 2) {
            int y = Integer.parseInt(orders.get(cnt));
            if (y == 0) {
                if (orders.get(cnt - 1).equalsIgnoreCase("addPowder")) {
                    addPowder();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addCloth")) {
                    addCloth();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addPackagedMilk")) {
                    addPackagedMilk();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addBread")) {
                    addBread();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addShirt")) {
                    addShirt();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("addIceCream")) {
                    addIceCream();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("well")) {
                    well();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                } else if (orders.get(cnt - 1).equalsIgnoreCase("truckGo")) {
                    truckGo();
                    orders.remove(cnt);
                    orders.remove(cnt - 1);
                    cnt -= 2;
                }
            }
        }
    }

    public void workFactory(String string) {
        int pointer = foundFactory(string);
        try {
            if (string.equalsIgnoreCase("mill")) {
                if (pointer < factory.factories.size()) {
                    if ((!orders.contains("addPowder"))) {
                        if (checkEgg() == false) {
                            logger.warning("There are not enough eggs");
                        } else if (factory.factories.get(pointer).getLevel() == 1 && (checkEgg())) {
                            orders.add("addPowder");
                            orders.add(String.valueOf(factory.factories.get(pointer).getTime1() + 1));
                            removeFromStoredList("egg");
                            logger.info("An egg was removed from the warehouse");
                        } else if (factory.factories.get(pointer).getLevel() == 2 && (checkEgg())) {
                            removeFromStoredList("egg");
                            logger.info("An egg was removed from the warehouse");
                            if (checkEgg()) {
                                orders.add("addPowder");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                orders.add("addPowder");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                removeFromStoredList("egg");
                                logger.info("An egg was removed from the warehouse");
                            } else {
                                orders.add("addPowder");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                            }
                        }
                    } else if (orders.contains("addPowder")) {
                        logger.warning("Mill is working");
                    }
                } else {
                    logger.warning("Mill is not built");
                }

            } else if (string.equalsIgnoreCase("sewing")) {
                if (pointer < factory.factories.size()) {
                    if (!orders.contains("addCloth")) {
                        if (checkFeather() == false) {
                            logger.warning("There are not enough feathers");
                        } else if ((factory.factories.get(pointer).getLevel() == 1) && (checkFeather())) {
                            orders.add("addCloth");
                            orders.add(String.valueOf(factory.factories.get(pointer).getTime1() + 1));
                            removeFromStoredList("feather");
                            logger.info("A feather was removed from the warehouse");
                        } else if ((factory.factories.get(pointer).getLevel() == 2) && (checkFeather())) {
                            removeFromStoredList("feather");
                            logger.info("A feather was removed from the warehouse");
                            if (checkFeather()) {
                                orders.add("addCloth");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                orders.add("addCloth");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                removeFromStoredList("feather");
                                logger.info("A feather was removed from the warehouse");
                            } else {
                                orders.add("addCloth");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                            }
                        }
                    } else if (orders.contains("addCloth")) {
                        logger.warning("Sewing is working");
                    }
                } else {
                    logger.warning("Sewing is not built");
                }

            } else if (string.equalsIgnoreCase("milkPackaging")) {
                if (pointer < factory.factories.size()) {
                    if (!orders.contains("addPackagedMilk")) {
                        if (checkMilk() == false) {
                            logger.warning("There are not enough milks");
                        }
                        if ((factory.factories.get(pointer).getLevel() == 1) && (checkMilk())) {
                            orders.add("addPackagedMilk");
                            orders.add(String.valueOf(factory.factories.get(pointer).getTime1() + 1));
                            removeFromStoredList("milk");
                            logger.info("A milk was removed from the warehouse");
                        } else if ((factory.factories.get(pointer).getLevel() == 2) && (checkMilk())) {
                            removeFromStoredList("milk");
                            logger.info("A milk was removed from the warehouse");
                            if (checkMilk()) {
                                orders.add("addPackagedMilk");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                orders.add("addPackagedMilk");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                removeFromStoredList("milk");
                                logger.info("A milk was removed from the warehouse");
                            } else {
                                orders.add("addPackagedMilk");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                            }
                        }
                    } else if (orders.contains("addPackagedMilk")) {
                        logger.warning("Packaging milk is working");
                    }
                } else {
                    logger.warning("Packaging Milk is not built");
                }

            } else if (string.equalsIgnoreCase("bakery")) {
                if (pointer < factory.factories.size()) {
                    if (!orders.contains("addBread")) {
                        if (checkPowder() == false) {
                            logger.warning("There are not enough powders");
                        } else if ((factory.factories.get(pointer).getLevel() == 1) && (checkPowder())) {
                            orders.add("addBread");
                            orders.add(String.valueOf(factory.factories.get(pointer).getTime1() + 1));
                            removeFromStoredList("powder");
                            logger.info("A powder was removed from the warehouse");
                        } else if ((factory.factories.get(pointer).getLevel() == 2) && (checkPowder())) {
                            removeFromStoredList("powder");
                            logger.info("A powder was removed from the warehouse");
                            if (checkPowder()) {
                                orders.add("addBread");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                orders.add("addBread");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                removeFromStoredList("powder");
                                logger.info("A powder was removed from the warehouse");
                            } else {
                                orders.add("addBread");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                            }
                        }
                    } else if (orders.contains("addBread")) {
                        logger.warning("Bakery is working");
                    }
                } else {
                    logger.warning("Bakery is not built");
                }

            } else if (string.equalsIgnoreCase("weaving")) {
                if (pointer < factory.factories.size()) {
                    if (!orders.contains("addShirt")) {
                        if (checkCloth() == false) {
                            logger.warning("There are not enough cloths");
                        }
                        if ((factory.factories.get(pointer).getLevel() == 1) && (checkCloth())) {
                            orders.add("addShirt");
                            orders.add(String.valueOf(factory.factories.get(pointer).getTime1() + 1));
                            removeFromStoredList("cloth");
                            logger.info("A cloth was removed from the warehouse");
                        } else if ((factory.factories.get(pointer).getLevel() == 2) && (checkCloth())) {
                            removeFromStoredList("cloth");
                            logger.info("A cloth was removed from the warehouse");
                            if (checkCloth()) {
                                orders.add("addShirt");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                orders.add("addShirt");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                removeFromStoredList("cloth");
                                logger.info("A cloth was removed from the warehouse");
                            } else {
                                orders.add("addShirt");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                            }
                        }
                    } else if (orders.contains("addShirt")) {
                        logger.warning("Weaving is working");
                    }
                } else {
                    logger.warning("weaving is not built");
                }

            } else if (string.equalsIgnoreCase("iceCreamShop")) {
                if (pointer < factory.factories.size()) {
                    if (!orders.contains("addIceCream")) {
                        if (checkPackagedMilk() == false) {
                            logger.warning("There are not enough packaged milks");
                        } else if ((factory.factories.get(pointer).getLevel() == 1) && (checkPackagedMilk())) {
                            orders.add("addIceCream");
                            orders.add(String.valueOf(factory.factories.get(pointer).getTime1() + 1));
                            removeFromStoredList("packagedMilk");
                            logger.info("A packaging milk was removed from the warehouse");
                        } else if ((factory.factories.get(pointer).getLevel() == 2) && (checkPackagedMilk())) {
                            removeFromStoredList("packagedMilk");
                            logger.info("A packaging milk was removed from the warehouse");
                            if (checkPackagedMilk()) {
                                orders.add("addIceCream");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                orders.add("addIceCream");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                                removeFromStoredList("packagedMilk");
                                logger.info("A packaging milk was removed from the warehouse");
                            } else {
                                orders.add("addIceCream");
                                orders.add(String.valueOf(factory.factories.get(pointer).getTime2() + 1));
                            }
                        }
                    } else if (orders.contains("addIceCream")) {
                        logger.warning("Ice cream shop is working");
                    }
                } else {
                    logger.warning("Ice cream shop is not built");
                }

            } else {
                System.out.println("Wrong Input!!");
                logger.warning("Wrong input");
            }
        } catch (Exception e) {
            System.out.println("Error,Wrong input!!");
            logger.warning("Wrong format input");
        }
    }

    public void processBuild(String strings) {
        try {
            if (strings.equalsIgnoreCase("mill")) {
                if (checkMill()) {
                    buildFactory("mill");
                }
            } else if (strings.equalsIgnoreCase("sewing")) {
                if (checkSewing()) {
                    buildFactory("sewing");
                }
            } else if (strings.equalsIgnoreCase("milkPackaging")) {
                if (checkMilkPackaging()) {
                    buildFactory("milkPackaging");
                }
            } else if (strings.equalsIgnoreCase("bakery")) {
                if (checkBakery()) {
                    buildFactory("bakery");
                }
            } else if (strings.equalsIgnoreCase("weaving")) {
                if (checkWeaving()) {
                    buildFactory("weaving");
                }
            } else if (strings.equalsIgnoreCase("iceCreamShop")) {
                if (checkIceCreamShop()) {
                    buildFactory("iceCreamShop");
                }
            } else {
                logger.warning("Wrong input");
            }
        } catch (Exception e) {
            System.out.println("Wrong input!!");
            logger.warning("Wrong format input");
        }
    }

    public void processUpgrade(String string) {
        try {
            if (string.equalsIgnoreCase("mill")) {
                upgradeFactory("mill");
            } else if (string.equalsIgnoreCase("sewing")) {
                upgradeFactory("sewing");
            } else if ((string.equalsIgnoreCase("milkPackaging"))) {
                upgradeFactory("milkPackaging");
            } else if (string.equalsIgnoreCase("bakery")) {
                upgradeFactory("bakery");
            } else if (string.equalsIgnoreCase("weaving")) {
                upgradeFactory("weaving");
            } else if (string.equalsIgnoreCase("iceCreamShop")) {
                upgradeFactory("iceCreamShop");
            } else {
                System.out.println("Wrong input!!");
                logger.warning("Wrong input");
            }
        } catch (Exception e) {
            System.out.println("Wrong input!!");
            logger.warning("Wrong format input");
        }
    }

    public void processWell() {
        if (!orders.contains("well")) {
            if (checkWell()) {
                orders.add("well");
                orders.add("4");
            } else {
                logger.warning("Well is not empty");
            }
        } else if (orders.contains("well")) {
            logger.warning("Well is working");
        }
    }


}
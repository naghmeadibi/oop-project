import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Manager {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private String password;
    private String username;
    private int selectedLevel;
    Shirt shirt = new Shirt();
    Store store = new Store();
    Truck truck = new Truck();
    Hen hen = new Hen();
    Turkey turkey = new Turkey();
    Bufalo bufalo = new Bufalo();
    Products products = new Products();
    Factory factory = new Factory();
    WildAnimals wildAnimals = new WildAnimals();
    Farmanimals farmanimals = new Farmanimals();
    int wellWater = 5;
    public int coin = 0;
    public LinkedList<Grass> grasses = new LinkedList<Grass>();
    public LinkedList<Dog> dogs = new LinkedList<Dog>();
    public LinkedList<Cat> cats = new LinkedList<>();
    public ArrayList<Level> levels = new ArrayList<Level>();
    public Level[] levells = new Level[20];
    int numOfUsers = getNumOfUsers();
    User[] userss = new User[numOfUsers + 1];
    ArrayList<User> users = new ArrayList<>();
    private int indexOfUser = 0;

    public void coinSet() {
        coin = levels.get(selectedLevel - 1).coin;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public int getIndexOfUser() {
        return indexOfUser;
    }

    public void setIndexOfUser(int indexOfUser) {
        this.indexOfUser = indexOfUser;
    }

    public void mennnu() {
        int check = 0;
        int start;
        Scanner scanner = new Scanner(System.in);
        while (check == 0) {
            System.out.println("You have an account");
            System.out.println("1) login");
            System.out.println("You do not account");
            System.out.println("2) sign up");
            System.out.println("Please enter the number of your choose");
            start = scanner.nextInt();
            if (start == 1) {
                System.out.println("Enter your username:");
                username = scanner.next();
                System.out.println("Enter your password:");
                password = scanner.next();
                File players = new File("Players.txt");
                try {
                    Scanner sc = new Scanner(players);
                    while (sc.hasNextLine()) {
                        String user = sc.nextLine();
                        if (user.equals(username + " " + password)) {
                            check = 1;
                        }
                    }
                    if (check == 1) {
                        System.out.println("You have successfully logged in");
                    } else {
                        System.out.println("password or username is incorrect");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (start == 2) {
                int check1 = 0;
                System.out.println("Enter your username:");
                username = scanner.next();
                File players = new File("Players.txt");
                try {
                    Scanner scanner2 = new Scanner(players);
                    while (scanner2.hasNextLine()) {
                        String data = scanner2.nextLine();
                        String[] inputs = data.split("\\s");
                        if (inputs[0].equals(username)) {
                            check1 = 1;
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (check1 == 0) {
                    System.out.println("Enter your password:");
                    password = scanner.next();
                    check = 1;
                    System.out.println("You signed up successfully");
                    try {
                        FileWriter player = new FileWriter("Players.txt", true);
                        player.append(username);
                        player.append(" ");
                        player.append(password);
                        player.append("\n");
                        player.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (check1 == 1) {
                    System.out.println("This username already exist!!");
                }
            } else {
                System.out.println("Error, Wrong input!!");
            }
        }
        check = 0;
    }

    public int randomCoordinate() {
        int width = (random.nextInt(6) + 1) * 10;
        int height = random.nextInt(6) + 1;
        int coordinate = width + height;
        return coordinate;
    }


    public void addEgg(int x) {
        Egg egg1 = new Egg(x);
        products.allUnPickedupedProducts.add(egg1);
    }

    public boolean checkEgg() {
        if (!users.get(getIndexOfUser()).workShops.get("mill")) {
            System.err.println("buy mill");
            return false;
        }
        if (counterProductInStore("egg") <= 0) {
            System.out.println("nadary");
            return false;
        } else {
            removeFromStoredList("egg");
            return true;
        }
    }

    public void addPowder() {
        int x = randomCoordinate();
        Powder powder1 = new Powder(x);
        products.allUnPickedupedProducts.add(powder1);
    }

    public boolean checkPowder() {
        if (!users.get(getIndexOfUser()).workShops.get("bakery")) {
            System.err.println("buy bakery");
            return false;
        }
        if (counterProductInStore("powder") <= 0) {
            System.out.println("nadary");
            return false;
        } else {
            removeFromStoredList("powder");
            return true;
        }
    }

    public void addBread() {
        int x = randomCoordinate();
        Bread bread1 = new Bread(x);
        products.allUnPickedupedProducts.add(bread1);
    }


    public void addFeather(int x) {
        Feather feather1 = new Feather(x);
        products.allUnPickedupedProducts.add(feather1);
    }

    public boolean checkFeather() {
        if (!users.get(getIndexOfUser()).workShops.get("clothWeaving")) {
            System.err.println("buy clothWeaving");
            return false;
        }
        if (counterProductInStore("feather") <= 0) {
            System.out.println("nadary");
            return false;
        } else {
            removeFromStoredList("feather");
            return true;
        }
    }

    public void addCloth() {
        int x = randomCoordinate();
        Cloth cloth1 = new Cloth(x);
        products.allUnPickedupedProducts.add(cloth1);
    }

    public boolean checkCloth() {
        if (!users.get(getIndexOfUser()).workShops.get("sewing")) {
            System.err.println("buy sewing");
            return false;
        }
        if (counterProductInStore("cloth") <= 0) {
            System.out.println("nadary");
            return false;
        } else {
            removeFromStoredList("cloth");
            return true;
        }
    }

    public void addShirt() {
        int x = randomCoordinate();
        Shirt shirt1 = new Shirt(x);
        products.allUnPickedupedProducts.add(shirt1);
    }


    public void addMilk(int x) {
        Milk milk1 = new Milk(x);
        products.allUnPickedupedProducts.add(milk1);
    }

    public boolean checkMilk() {
        if (!users.get(getIndexOfUser()).workShops.get("milkPackaging")) {
            System.err.println("buy milkPackaging");
            return false;
        }
        if (counterProductInStore("milk") <= 0) {
            System.out.println("nadary");
            return false;
        } else {
            removeFromStoredList("milk");
            return true;
        }
    }

    public void addPackagedMilk() {
        int x = randomCoordinate();
        PackagedMilk packagedMilk1 = new PackagedMilk(x);
        products.allUnPickedupedProducts.add(packagedMilk1);
    }

    public boolean checkPackagedMilk() {
        if (!users.get(getIndexOfUser()).workShops.get("iceCreamShop")) {
            System.err.println("buy iceCreamShop");
            return false;
        }
        if (counterProductInStore("packagedMilk") <= 0) {
            System.out.println("nadary");
            return false;
        } else {
            removeFromStoredList("packagedMilk");
            return true;
        }
    }

    public void addIceCream() {
        int x = randomCoordinate();
        IceCream iceCream1 = new IceCream(x);
        products.allUnPickedupedProducts.add(iceCream1);
    }


    public boolean checkWell() {
        return wellWater == 0;
    }

    public void well() {
        wellWater = 5;
        System.out.println("Your well water is : " + wellWater);
    }

    public void plant(int x) {
        if (wellWater > 0) {
            wellWater--;
            grasses.add(new Grass(x));
            System.out.println("grass " + x);
            System.out.println("Well water : " + wellWater);
        } else {
            System.out.println("Your well is empty!!");
        }
    }


    public void pickUp(int x) {
        int check = 0;
        for (int i = 0; i < products.allUnPickedupedProducts.size(); i++) {
            if ((products.allUnPickedupedProducts.get(i).coordinate == x)) {
                check = 1;
                if (store.getCapacity() >= products.allUnPickedupedProducts.get(i).capacity) {
                    store.setCapacity(store.getCapacity() - products.allUnPickedupedProducts.get(i).capacity);
                    store.stuff.add(products.allUnPickedupedProducts.get(i));
                    products.allUnPickedupedProducts.remove(i);
                } else {
                    System.out.println("Error, Your warehouse does not have enough space!!");
                }
                break;
            }
        }
        if (check == 0) {
            for (int i = 0; i < wildAnimals.cagedWildAnimals.size(); i++) {
                if ((wildAnimals.cagedWildAnimals.get(i).coordinate == x)) {
                    check = 1;
                    if (store.getCapacity() >= wildAnimals.cagedWildAnimals.get(i).capacity) {
                        store.setCapacity(store.getCapacity() - wildAnimals.cagedWildAnimals.get(i).capacity);
                        wildAnimals.storedWildAnimals.add(wildAnimals.cagedWildAnimals.get(i));
                        wildAnimals.cagedWildAnimals.remove(i);
                    } else {
                        System.out.println("Error, Your warehouse does not have enough space!!");
                    }
                    break;
                }
            }
        }
        if (check == 0) {
            System.out.println("this Coordinates is empty!!");
        }
    }

    public void truckLoad(String name) {
        int check = 0;
        for (int i = 0; i < store.stuff.size(); i++) {
            if (store.stuff.get(i).name.equalsIgnoreCase(name)) {
                check = 1;
                if (truck.getCapacity() >= store.stuff.get(i).capacity) {
                    truck.setCapacity(truck.getCapacity() - store.stuff.get(i).capacity);
                    truck.stuffToSell.add(store.stuff.get(i));
                    store.stuff.remove(i);
                } else {
                    System.out.println("The truck does not have enough space!!");
                }
                break;
            }
        }
        if (check == 0) {
            for (int i = 0; i < wildAnimals.storedWildAnimals.size(); i++) {
                if (wildAnimals.storedWildAnimals.get(i).name.equalsIgnoreCase(name)) {
                    check = 1;
                    if (truck.getCapacity() >= wildAnimals.storedWildAnimals.get(i).capacity) {
                        truck.setCapacity(truck.getCapacity() - wildAnimals.storedWildAnimals.get(i).getCapacity());
                        truck.setPrice(truck.getPrice() + wildAnimals.storedWildAnimals.get(i).price);
                        truck.wildAnimalsToSell.add(wildAnimals.storedWildAnimals.get(i));
                        wildAnimals.storedWildAnimals.remove(i);
                    } else {
                        System.out.println("The truck does not have enough space!!");
                    }
                    break;
                }
            }
        }
     /*   if (check == 0){
            for (int i = 0; i < farmanimals.farmanimalss.size(); i++) {
                if (farmanimals.farmanimalss.get(i).name.equalsIgnoreCase(name)) {
                    check = 1;
                    if (truck.getCapacity() >= farmanimals.farmanimalss.get(i).capacity) {
                        truck.setCapacity(truck.getCapacity() - store.stuff.get(i).capacity);
                        truck.wildAnimalsToSell.add(wildAnimals.storedWildAnimals.get(i));
                        wildAnimals.storedWildAnimals.remove(i);
                    } else {
                        System.out.println("The truck does not have enough space!!");
                    }
                    break;
                }
            }
        }*/
        if (check == 0) {
            System.out.println("This product dosnt exist in warehouse");
        }
    }

    public void truckUnload(String name) {
        int check = 0;
        for (int i = 0; i < truck.stuffToSell.size(); i++) {
            if (truck.stuffToSell.get(i).name.equalsIgnoreCase(name)) {
                check = 1;
                truck.setCapacity(truck.getCapacity() + truck.stuffToSell.get(i).capacity);
                store.stuff.add(truck.stuffToSell.get(i));
                truck.stuffToSell.remove(i);
                break;
            }
        }
        if (check == 0) {
            for (int i = 0; i < truck.wildAnimalsToSell.size(); i++) {
                if (truck.wildAnimalsToSell.get(i).name.equalsIgnoreCase(name)) {
                    check = 1;
                    truck.setCapacity(truck.getCapacity() + truck.wildAnimalsToSell.get(i).capacity);
                    wildAnimals.storedWildAnimals.add(truck.wildAnimalsToSell.get(i));
                    truck.wildAnimalsToSell.remove(i);
                    break;
                }
            }
        }
        if (check == 0) {
            System.out.println("Error, This product is not in the truck");
        }
    }

    public void removeFromStoredList(String string) {
        for (int i = 0; i < store.stuff.size(); i++) {
            if (store.stuff.get(i).name.equalsIgnoreCase(string)) {
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
        } else {
            System.err.println("you dont have enough coins!");
        }
    }

    public void addTurkey() {
        if (coin >= turkey.price) {
            Turkey turkey = new Turkey(randomCoordinate());
            farmanimals.farmanimalss.add(turkey);
            coin -= turkey.price;
        } else {
            System.err.println("you dont have enough coins!");
        }
    }

    public void addBufalo() {
        if (coin >= bufalo.price) {
            Bufalo bufalo = new Bufalo(randomCoordinate());
            farmanimals.farmanimalss.add(bufalo);
            coin -= bufalo.price;
        } else {
            System.err.println("you dont have enough coins!");
        }
    }


    public void cage(int coordinates) {
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            if (wildAnimals.livingWildAnimals.get(cnt).coordinate == coordinates) {
                wildAnimals.livingWildAnimals.get(cnt).setLife(wildAnimals.livingWildAnimals.get(cnt).life - 2);
                if (wildAnimals.livingWildAnimals.get(cnt).life == -1) {
                    wildAnimals.livingWildAnimals.get(cnt).setCaged(5);
                    wildAnimals.cagedWildAnimals.add(wildAnimals.livingWildAnimals.get(cnt));
                    wildAnimals.livingWildAnimals.remove(cnt);
                }
                return;
            }
        }

        System.out.println("there's no wild animal here :(");
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
                wildAnimals.cagedWildAnimals.remove(cnt);
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
        System.out.println(coin);
        coin += truck.getPrice();
        System.out.println(coin);
    }

    public void moving() {
        for (int cnt = 0; cnt < farmanimals.farmanimalss.size(); cnt++) {
            farmanimals.farmanimalss.get(cnt).setCoordinate(movingCoordinate(farmanimals.farmanimalss.get(cnt).coordinate));
        }
        for (int cnt = 0; cnt < wildAnimals.livingWildAnimals.size(); cnt++) {
            if ((wildAnimals.livingWildAnimals.get(cnt) instanceof Bear) || (wildAnimals.livingWildAnimals.get(cnt) instanceof Lion)) {
                wildAnimals.livingWildAnimals.get(cnt).setCoordinate(movingCoordinate(wildAnimals.livingWildAnimals.get(cnt).coordinate));
            }
        }
        for (int cnt = 0; cnt < dogs.size(); cnt++) {
            Dog dog = dogs.get(cnt);
            dog.coordinate = movingCoordinate(dog.coordinate);
            dogs.set(cnt, dog);
        }
        for (int cnt = 0; cnt < cats.size(); cnt++) {
            Cat cat = cats.get(cnt);
            cat.coordinate = movingCoordinate(cat.coordinate);
            cats.set(cnt, cat);
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
            for (int cnt = 0; cnt < grasses.size(); cnt++) {
                int a = indexOfSmallest(henCheck[cnt]);
                int aa;
                if (farmanimals.farmanimalss.size() > 0) {
                    aa = Arrays.stream(henCheck[cnt]).min().getAsInt();
                } else {
                    aa = Integer.MIN_VALUE;
                }
                if (aa != Integer.MAX_VALUE && aa <= 5) {
                    farmanimals.farmanimalss.get(a).setLife(10);
                    grasses.remove(cnt);
                }
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
                        farmanimals.farmanimalss.remove(cnthen);
                    }
                }
                for (int cntbread = 0; cntbread < products.allUnPickedupedProducts.size(); cntbread += 1) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == products.allUnPickedupedProducts.get(cntbread).coordinate) {
                        products.allUnPickedupedProducts.remove(cntbread);
                    }
                }
                for (int cntt = 0; cntt < cats.size(); cntt++) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == cats.get(cntt).coordinate) {
                        cats.remove(cntt);
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
                        farmanimals.farmanimalss.remove(cnthen);
                    }
                }
                for (int cntt = 0; cntt < products.allUnPickedupedProducts.size(); cntt++) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == products.allUnPickedupedProducts.get(cntt).coordinate || ((Tiger) wildAnimals.livingWildAnimals.get(cnt)).interCoordinate == products.allUnPickedupedProducts.get(cntt).coordinate) {
                        products.allUnPickedupedProducts.remove(cntt);
                    }

                }
                for (int cntt = 0; cntt < cats.size(); cntt++) {
                    if (wildAnimals.livingWildAnimals.get(cnt).coordinate == cats.get(cntt).coordinate || ((Tiger) wildAnimals.livingWildAnimals.get(cnt)).interCoordinate == cats.get(cntt).coordinate) {
                        cats.remove(cntt);
                    }

                }
            }
        }

    }

    public void dogToCatch() {
        for (int cnt = 0; cnt < dogs.size(); cnt++) {
            for (int cnttiger = 0; cnttiger < wildAnimals.livingWildAnimals.size(); cnttiger++) {
                if (dogs.get(cnt).coordinate == wildAnimals.livingWildAnimals.get(cnttiger).coordinate) {
                    dogs.remove(cnt);
                    wildAnimals.livingWildAnimals.remove(cnttiger);
                }
            }
        }
    }

    public void catCollect() {
        for (int cnt = 0; cnt < cats.size(); cnt++) {
            for (int cntt = 0; cntt < products.allUnPickedupedProducts.size(); cntt++) {
                if (cats.get(cnt).coordinate == products.allUnPickedupedProducts.get(cntt).coordinate) {
                    if (store.getCapacity() >= shirt.capacity) {
                        store.stuff.add(products.allUnPickedupedProducts.get(cntt));
                        products.allUnPickedupedProducts.remove(cntt);
                        store.setCapacity(store.getCapacity() - shirt.capacity);
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
            for (int cnt = 0; cnt < dogs.size(); cnt++) {
                System.out.println("coordinate : " + dogs.get(cnt).coordinate);
            }
        }
        if (!cats.isEmpty()) {
            System.out.println("cats");
            for (int cnt = 0; cnt < cats.size(); cnt++) {
                System.out.println("coordinate : " + cats.get(cnt).coordinate);
            }
        }
    }

    public void lifeOfAnimals() {
        for (int cnt = 0; cnt < farmanimals.farmanimalss.size(); cnt++) {
            if (farmanimals.farmanimalss.get(cnt).getLife() == 0)
                farmanimals.farmanimalss.remove(cnt);
            else
                farmanimals.farmanimalss.get(cnt).setLife(farmanimals.farmanimalss.get(cnt).getLife() - 1);
        }
    }

    public void addBear() {
        wildAnimals.livingWildAnimals.add(new Bear(randomCoordinate()));
    }

    public void addLion() {
        wildAnimals.livingWildAnimals.add(new Lion(randomCoordinate()));
    }

    public void addTiger() {
        wildAnimals.livingWildAnimals.add(new Tiger(randomCoordinate()));
    }

    public void addDog() {
        dogs.add(new Dog(randomCoordinate()));
        coin -= 100;
    }

    public void addCat() {
        cats.add(new Cat(randomCoordinate()));
        coin -= 150;
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
                } else if (farmanimals.farmanimalss.get(cnt) instanceof Bufalo) {
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
                for (int c = 0; c < grasses.size(); c++) {
                    if (grasses.get(c).coordinate == (cnt * 10 + cn)) {
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
        System.out.println("Coin$ = " + coin);
        System.out.println("Remaining tasks : ");
        printTask();
        //System.out.println(levels.get(selectedLevel - 1).tasks.toString());
    }

    public void printTask(){
        if (levels.get(selectedLevel - 1).tasks.containsKey("hen")) {
            System.out.print("Hen : ");
            System.out.println(countFarmAnimal("hen") + "/" + levels.get(selectedLevel - 1).tasks.get("hen"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("turkey")) {
            System.out.print("Turkey : ");
            System.out.println(countFarmAnimal("turkey") + "/" + levels.get(selectedLevel - 1).tasks.get("turkey"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("buffalo")) {
            System.out.print("Buffalo : ");
            System.out.println(countFarmAnimal("buffalo") + "/" + levels.get(selectedLevel - 1).tasks.get("buffalo"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("egg")) {
            System.out.print("Egg : ");
            System.out.println(countFarmAnimal("egg") + "/" + levels.get(selectedLevel - 1).tasks.get("egg"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("feather")) {
            System.out.print("Feather : ");
            System.out.println(countFarmAnimal("feather") + "/" + levels.get(selectedLevel - 1).tasks.get("feather"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("milk")) {
            System.out.print("Milk : ");
            System.out.println(countFarmAnimal("milk") + "/" + levels.get(selectedLevel - 1).tasks.get("milk"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("powder")) {
            System.out.print("Powder : ");
            System.out.println(countFarmAnimal("powder") + "/" + levels.get(selectedLevel - 1).tasks.get("powder"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("cloth")) {
            System.out.print("Cloth : ");
            System.out.println(countFarmAnimal("cloth") + "/" + levels.get(selectedLevel - 1).tasks.get("cloth"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("packagedMilk")) {
            System.out.print("Packaged Milk : ");
            System.out.println(countFarmAnimal("packagedMilk") + "/" + levels.get(selectedLevel - 1).tasks.get("packagedMilk"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("bread")) {
            System.out.print("Bread : ");
            System.out.println(countFarmAnimal("bread") + "/" + levels.get(selectedLevel - 1).tasks.get("bread"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("shirt")) {
            System.out.print("Shirt : ");
            System.out.println(countFarmAnimal("shirt") + "/" + levels.get(selectedLevel - 1).tasks.get("shirt"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("iceCream")) {
            System.out.print("Ice Cream : ");
            System.out.println(countFarmAnimal("iceCream") + "/" + levels.get(selectedLevel - 1).tasks.get("iceCream"));
        } if (levels.get(selectedLevel - 1).tasks.containsKey("coin")) {
            System.out.print("Coin : ");
            System.out.println(coin + "/" + levels.get(selectedLevel - 1).tasks.get("coin"));
        }
    }

    public void productTimeHandle() {
        for (int cnt = 0; cnt < products.allUnPickedupedProducts.size(); cnt++) {
            if (products.allUnPickedupedProducts.get(cnt).live <= 0) {
                products.allUnPickedupedProducts.remove(cnt);
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
            for (int i = 0; i < levells.length; i++) {
                levels.add(levells[i]);
            }
        } catch (Exception e) {

        }
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
                levels.get(selectedLevel - 1).tasks.remove("hen");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("turkey")) {
            if (levels.get(selectedLevel - 1).tasks.get("turkey") <= countFarmAnimal("turkey")) {
                levels.get(selectedLevel - 1).tasks.remove("turkey");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("buffalo")) {
            if (levels.get(selectedLevel - 1).tasks.get("buffalo") <= countFarmAnimal("buffalo")) {
                levels.get(selectedLevel - 1).tasks.remove("buffalo");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("egg")) {
            if (levels.get(selectedLevel - 1).tasks.get("egg") <= counterProductInStore("egg")) {
                levels.get(selectedLevel - 1).tasks.remove("egg");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("feather")) {
            if (levels.get(selectedLevel - 1).tasks.get("feather") <= counterProductInStore("feather")) {
                levels.get(selectedLevel - 1).tasks.remove("feather");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("milk")) {
            if (levels.get(selectedLevel - 1).tasks.get("milk") <= counterProductInStore("milk")) {
                levels.get(selectedLevel - 1).tasks.remove("milk");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("powder")) {
            if (levels.get(selectedLevel - 1).tasks.get("powder") <= counterProductInStore("powder")) {
                levels.get(selectedLevel - 1).tasks.remove("powder");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("cloth")) {
            if (levels.get(selectedLevel - 1).tasks.get("cloth") <= counterProductInStore("cloth")) {
                levels.get(selectedLevel - 1).tasks.remove("cloth");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("packagedMilk")) {
            if (levels.get(selectedLevel - 1).tasks.get("packagedMilk") <= counterProductInStore("packagedMilk")) {
                levels.get(selectedLevel - 1).tasks.remove("packagedMilk");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("bread")) {
            if (levels.get(selectedLevel - 1).tasks.get("bread") <= counterProductInStore("bread")) {
                levels.get(selectedLevel - 1).tasks.remove("bread");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("shirt")) {
            if (levels.get(selectedLevel - 1).tasks.get("shirt") <= counterProductInStore("shirt")) {
                levels.get(selectedLevel - 1).tasks.remove("shirt");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("iceCream")) {
            if (levels.get(selectedLevel - 1).tasks.get("iceCream") <= counterProductInStore("iceCream")) {
                levels.get(selectedLevel - 1).tasks.remove("iceCream");
            }
        } if (levels.get(selectedLevel - 1).tasks.containsKey("coin")) {
            if (levels.get(selectedLevel - 1).tasks.get("coin") <= coin) {
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
            Gson gson = new Gson();
            userss = gson.fromJson(new FileReader("users.json"), User[].class);
            for (int i = 0; i < userss.length; i++) {
                users.add(userss[i]);
            }
        } catch (Exception e) {

        }
    }

    public void menu(boolean c) {
        readUser();
        boolean mainWhile = true;
        while (mainWhile == true && c) {
            System.out.println("Enter number of your choice : ");
            System.out.println("1) login");
            System.out.println("2) sign up");
            System.out.println("3) EXIT");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("enter your username :");
                username = scanner.nextLine();
                if (checkUsername(username) == -1) {
                    System.out.println("Invalid Username!!");
                } else {
                    while (true) {
                        System.out.println("enter your password : ");
                        password = scanner.nextLine();
                        if (users.get(checkUsername(username)).getPassword().equalsIgnoreCase(password)) {
                            mainWhile = false;
                            System.out.println("done!");
                            indexOfUser = checkUsername(username);
                            break;
                        } else {
                            System.out.println("Wrong Password, try again!!");
                        }
                    }
                }
            } else if (choice.equals("2")) {
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
                        break;
                    } else {
                        System.out.println("your username is invalid choose another ");
                    }
                }
            } else if (choice.equals("3")) {
                System.exit(0);
            }
        }
        while (true) {
            System.out.println("what shall we do next?");
            System.out.println("1) start [level] " + " 2) log out" + "  3) settings ");
            String choice = scanner.nextLine();
            String[] strings = choice.split("\\s+");
            if (strings[0].equalsIgnoreCase("1")) {
                selectedLevel = Integer.valueOf(strings[1]);
                if (selectedLevel > users.get(getIndexOfUser()).getLevel()) {
                    System.out.println("ghofle!!!");
                } else {
                    System.out.println("Go On");
                    return;
                }
            } else if (strings[0].equalsIgnoreCase("2")) {
                mainWhile = true;
                c = true;
                menu(c);
            } else if (strings[0].equalsIgnoreCase("3")) {
                System.out.println("you wanna buy?");
                System.out.println("1) mill 2) clothWeaving 3) milkPackaging 4) bakery 5) sewing 6) iceCreamShop ");
                String workShop = scanner.nextLine();
                if (workShop.equals("1")) {
                    if (!users.get(indexOfUser).workShops.get("mill")) {
                        if (users.get(indexOfUser).getMoney() >= 150) {
                            users.get(indexOfUser).workShops.replace("mill", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 150);
                        } else {
                            System.out.println("you don't have enough money");
                        }
                    } else {
                        System.out.println("mill is already taken");
                    }
                }
                if (workShop.equals("2")) {
                    if (!users.get(indexOfUser).workShops.get("mill")) {
                        if (users.get(indexOfUser).getMoney() >= 250) {
                            users.get(indexOfUser).workShops.replace("clothWeaving", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 250);
                        } else {
                            System.out.println("you don't have enough money");
                        }
                    } else {
                        System.out.println("clothWeaving is already taken");
                    }
                }
                if (workShop.equals("3")) {
                    if (!users.get(indexOfUser).workShops.get("milkPackaging")) {
                        if (users.get(indexOfUser).getMoney() >= 400) {
                            users.get(indexOfUser).workShops.replace("milkPackaging", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 400);
                        } else {
                            System.out.println("you don't have enough money");
                        }
                    } else {
                        System.out.println("milkPackaging is already taken");
                    }
                }
                if (workShop.equals("4")) {
                    if (!users.get(indexOfUser).workShops.get("bakery")) {
                        if (users.get(indexOfUser).getMoney() >= 250) {
                            users.get(indexOfUser).workShops.replace("bakery", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 250);
                        } else {
                            System.out.println("you don't have enough money");
                        }
                    } else {
                        System.out.println("bakery is already taken");
                    }
                }
                if (workShop.equals("5")) {
                    if (!users.get(indexOfUser).workShops.get("sewing")) {
                        if (users.get(indexOfUser).getMoney() >= 400) {
                            users.get(indexOfUser).workShops.replace("sewing", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 400);
                        } else {
                            System.out.println("you don't have enough money");
                        }
                    } else {
                        System.out.println("sewing is already taken");
                    }
                }
                if (workShop.equals("6")) {
                    if (!users.get(indexOfUser).workShops.get("iceCreamShop")) {
                        if (users.get(indexOfUser).getMoney() >= 550) {
                            users.get(indexOfUser).workShops.replace("iceCreamShop", true);
                            users.get(indexOfUser).setMoney(users.get(indexOfUser).getMoney() - 550);
                        } else {
                            System.out.println("you don't have enough money");
                        }
                    } else {
                        System.out.println("iceCreamShop is already taken");
                    }
                }
                writeGsonUsers();
            }

        }
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
            x = Integer.valueOf(scanner.next());
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

        while (!levels.get(getSelectedLevel() - 1).time.containsKey(timeCounter)) {
            timeCounter++;
        }

        users.get(getIndexOfUser()).setMoney(users.get(getIndexOfUser()).getMoney() + levels.get(getSelectedLevel() - 1).time.get(timeCounter));

    }

    public void setBack() {
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
    }

    public void buildFactory(String factoryName) {
        if (factory.factories.contains(factoryName)){
            System.out.println("this factory already exist!!");
        }
        else {
            if ((factoryName.equalsIgnoreCase("mill")) && (coin >= 150)) {
                factory.factories.add("mill");
                coin -= 150;
            } else if ((factoryName.equalsIgnoreCase("sewing")) && (coin >= 250)) {
                factory.factories.add("sewing");
                coin -= 250;
            } else if ((factoryName.equalsIgnoreCase("packagingMilk")) && (coin >= 400)) {
                factory.factories.add("packagingMilk");
                coin -= 400;
            } else if ((factoryName.equalsIgnoreCase("bakery")) && (coin >= 250)) {
                factory.factories.add("bakery");
                coin -= 250;
            } else if ((factoryName.equalsIgnoreCase("weaving")) && (coin >= 400)) {
                factory.factories.add("weaving");
                coin -= 400;
            } else if ((factoryName.equalsIgnoreCase("iceCreamShop")) && (coin >= 550)) {
                factory.factories.add("iceCreamShop");
                coin -= 550;
            } else {
                System.out.println("You dont have enough money!");
            }
        }
    }
}
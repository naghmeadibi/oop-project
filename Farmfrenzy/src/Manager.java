import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Manager {
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private String password;
    private String username;
    LinkedList<Bear> bears = new LinkedList<Bear>();
    LinkedList<Lion> lions = new LinkedList<Lion>();
    Bread bread = new Bread();
    Cloth cloth = new Cloth();
    Egg egg = new Egg();
    Feather feather = new Feather();
    IceCream iceCream = new IceCream();
    Milk milk = new Milk();
    PackagedMilk packagedMilk = new PackagedMilk();
    Powder powder = new Powder();
    Shirt shirt = new Shirt();
    Store store = new Store();
    Truck truck = new Truck();
    Hen hen = new Hen();
    Turkey turkey = new Turkey();
    Bufalo bufalo = new Bufalo();
    Products products = new Products();
    int wellWater = 5;
    public LinkedList<Grass> grasses = new LinkedList<Grass>();
    public LinkedList<Bread> unPickedBreads = new LinkedList<Bread>();
    public LinkedList<Bread> storedBreads = new LinkedList<Bread>();
    public LinkedList<Bread> truckBreads = new LinkedList<Bread>();
    public LinkedList<Milk> unPickedMilks = new LinkedList<Milk>();
    public LinkedList<Milk> storedMilks = new LinkedList<Milk>();
    public LinkedList<Milk> truckMilks = new LinkedList<Milk>();
    public LinkedList<PackagedMilk> unPickedPackagedMilks = new LinkedList<PackagedMilk>();
    public LinkedList<PackagedMilk> storedPackagedMilks = new LinkedList<PackagedMilk>();
    public LinkedList<PackagedMilk> truckPackagedMilks = new LinkedList<PackagedMilk>();
    public LinkedList<Powder> unPickedPowders = new LinkedList<Powder>();
    public LinkedList<Powder> storedPowders = new LinkedList<Powder>();
    public LinkedList<Powder> truckPowders = new LinkedList<Powder>();
    public LinkedList<Shirt> unPickedShirts = new LinkedList<Shirt>();
    public LinkedList<Shirt> storedShirts = new LinkedList<Shirt>();
    public LinkedList<Shirt> truckShirts = new LinkedList<Shirt>();
    public LinkedList<Cloth> unPickedCloths = new LinkedList<Cloth>();
    public LinkedList<Cloth> storedCloths = new LinkedList<Cloth>();
    public LinkedList<Cloth> truckCloths = new LinkedList<Cloth>();
    public LinkedList<Egg> unPickedEggs = new LinkedList<Egg>();
    public LinkedList<Egg> storedEggs = new LinkedList<Egg>();
    public LinkedList<Egg> truckEggs = new LinkedList<Egg>();
    public LinkedList<Feather> unPickedFeathers = new LinkedList<Feather>();
    public LinkedList<Feather> storedFeathers = new LinkedList<Feather>();
    public LinkedList<Feather> truckFeathers = new LinkedList<Feather>();
    public LinkedList<IceCream> unPickedIceCreams = new LinkedList<IceCream>();
    public LinkedList<IceCream> storedIceCreams = new LinkedList<IceCream>();
    public LinkedList<IceCream> truckIceCreams = new LinkedList<IceCream>();
    public LinkedList<Hen> hens = new LinkedList<Hen>();
    public LinkedList<Turkey> turkeys = new LinkedList<Turkey>();
    public LinkedList<Bufalo> bufalos = new LinkedList<Bufalo>();
    public LinkedList<Tiger> tigers = new LinkedList<Tiger>();
    public LinkedList<Dog> dogs = new LinkedList<Dog>();
    public LinkedList<Lion> lionsInStore = new LinkedList<>();
    public LinkedList<Bear> bearsInStore = new LinkedList<>();
    public LinkedList<Tiger> tigersInStore = new LinkedList<>();
    public LinkedList<Lion> lionsInCage = new LinkedList<>();
    public LinkedList<Bear> bearsInCage = new LinkedList<>();
    public LinkedList<Tiger> tigersInCage = new LinkedList<>();
    public LinkedList<Cat> cats = new LinkedList<>();


    public void menu() {
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

    public int random1() {
        return random.nextInt(6);
    }

    public void addBread() {
        if ((storedPowders.size() > 0)) {
            int x = randomCoordinate();
            Bread bread1 = new Bread(x);
            unPickedBreads.add(bread1);
            storedPowders.removeFirst();
            products.allUnPickedupedProducts.add(bread1);
            removeFromStoredList("powder");
            System.out.println("Bread " + x);
        } else {
            System.out.println("Sorry, you dont have enough powder!!");
        }
    }

    public void addCloth() {
        if (storedFeathers.size() > 0) {
            int x = randomCoordinate();
            Cloth cloth1 = new Cloth(x);
            unPickedCloths.add(cloth1);
            storedFeathers.removeFirst();
            products.allUnPickedupedProducts.add(cloth1);
            removeFromStoredList("feather");
            System.out.println("Cloth " + x);
        } else {
            System.out.println("Sorry, you dont have enough feather!!");
        }
    }

    public void addEgg() {
        int x = randomCoordinate();
        Egg egg1 = new Egg(x);
        unPickedEggs.add(egg1);
        products.allUnPickedupedProducts.add(egg1);
        System.out.println(unPickedEggs);
        System.out.println(storedEggs);
        System.out.println(truckEggs);
        System.out.println("Egg " + x);
    }

    public void addFeather() {
        int x = randomCoordinate();
        Feather feather1 = new Feather(x);
        unPickedFeathers.add(feather1);
        products.allUnPickedupedProducts.add(feather1);
        System.out.println("Feather " + x);
    }

    public void addIceCream() {
        if (storedPackagedMilks.size() > 0) {
            int x = randomCoordinate();
            IceCream iceCream1 = new IceCream(x);
            unPickedIceCreams.add(iceCream1);
            storedPackagedMilks.removeFirst();
            products.allUnPickedupedProducts.add(iceCream1);
            removeFromStoredList("packagedMilk");
            System.out.println("Ice Cream " + x);
        } else {
            System.out.println("Sorry, you dont have enough packaged milk!!");
        }
    }

    public void addMilk() {
        int x = randomCoordinate();
        Milk milk1 = new Milk(x);
        unPickedMilks.add(milk1);
        products.allUnPickedupedProducts.add(milk1);
        System.out.println("Milk " + x);
    }

    public void addPackagedMilk() {
        if (storedMilks.size() > 0) {
            int x = randomCoordinate();
            PackagedMilk packagedMilk1 = new PackagedMilk(x);
            unPickedPackagedMilks.add(packagedMilk1);
            products.allUnPickedupedProducts.add(packagedMilk1);
            removeFromStoredList("milk");
            storedMilks.removeFirst();
            System.out.println("Packaged Milk " + x);
        } else {
            System.out.println("Sorry, you dont have enough milk!!");
        }
    }

    public void addPowder() {
        if (storedEggs.size() > 0) {
            int x = randomCoordinate();
            Powder powder1 = new Powder(x);
            unPickedPowders.add(powder1);
            storedEggs.removeFirst();
            products.allUnPickedupedProducts.add(powder1);
            removeFromStoredList("egg");
            System.out.println("Powder " + x);
        } else {
            System.out.println("Sorry, you dont have enough egg!!");
        }
    }

    public void addShirt() {
        if (storedCloths.size() > 0) {
            int x = randomCoordinate();
            Shirt shirt1 = new Shirt(x);
            unPickedShirts.add(shirt1);
            products.allUnPickedupedProducts.add(shirt1);
            storedCloths.removeFirst();
            removeFromStoredList("cloth");
            System.out.println("Shirt " + x);
        } else {
            System.out.println("Sorry, you dont have enough cloth!!");
        }
    }

    public void well() {
        if (wellWater == 0) {
            wellWater = 5;
            System.out.println("Your well water is : " + wellWater);
        } else {
            System.out.println("Your well is not empty!!");
        }
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
                    addToPicked(products.allUnPickedupedProducts.get(i));
                    products.allUnPickedupedProducts.remove(i);
                } else {
                    System.out.println("Error, Your warehouse does not have enough space!!");
                }
                break;
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
                    addToTruck(store.stuff.get(i));
                    store.stuff.remove(i);
                } else {
                    System.out.println("The truck does not have enough space!!");
                }
                break;
            }
        }
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
                backToStoredList(truck.stuffToSell.get(i));
                store.stuff.add(truck.stuffToSell.get(i));
                truck.stuffToSell.remove(i);
                break;
            }
        }
        if (check == 0) {
            System.out.println("Error, This product is not in the truck");
        }
    }

    // in 4 ta braye listaye jadidan
    public void addToPicked (Products products) {
        if (products.name.equalsIgnoreCase("egg")){
            storedEggs.add((Egg) products);
            unPickedEggs.removeFirst();
        } else if (products.name.equalsIgnoreCase("milk")){
            storedMilks.add((Milk) products);
            unPickedMilks.removeFirst();
        } else if (products.name.equalsIgnoreCase("feather")){
            storedFeathers.add((Feather) products);
            unPickedFeathers.removeFirst();
        } else if (products.name.equalsIgnoreCase("Powder")){
            storedPowders.add((Powder) products);
            unPickedPowders.removeFirst();
        } else if (products.name.equalsIgnoreCase("Cloth")){
            storedCloths.add((Cloth) products);
            unPickedCloths.removeFirst();
        } else if (products.name.equalsIgnoreCase("PackagedMilk")){
            storedPackagedMilks.add((PackagedMilk) products);
            unPickedPackagedMilks.removeFirst();
        } else if (products.name.equalsIgnoreCase("bread")){
            storedBreads.add((Bread) products);
            unPickedBreads.removeFirst();
        } else if (products.name.equalsIgnoreCase("shirt")){
            storedShirts.add((Shirt) products);
            unPickedShirts.removeFirst();
        } else if (products.name.equalsIgnoreCase("IceCream")){
            storedIceCreams.add((IceCream) products);
            unPickedIceCreams.removeFirst();
        }
    }

    public void addToTruck (Products products) {
        if (products.name.equalsIgnoreCase("egg")){
            truckEggs.add((Egg) products);
            storedEggs.removeFirst();
        } else if (products.name.equalsIgnoreCase("milk")){
            truckMilks.add((Milk) products);
            storedMilks.removeFirst();
        } else if (products.name.equalsIgnoreCase("feather")){
            truckFeathers.add((Feather) products);
            storedFeathers.removeFirst();
        } else if (products.name.equalsIgnoreCase("Powder")){
            truckPowders.add((Powder) products);
            storedPowders.removeFirst();
        } else if (products.name.equalsIgnoreCase("Cloth")){
            truckCloths.add((Cloth) products);
            storedCloths.removeFirst();
        } else if (products.name.equalsIgnoreCase("PackagedMilk")){
            truckPackagedMilks.add((PackagedMilk) products);
            storedPackagedMilks.removeFirst();
        } else if (products.name.equalsIgnoreCase("bread")){
            truckBreads.add((Bread) products);
            storedBreads.removeFirst();
        } else if (products.name.equalsIgnoreCase("shirt")){
            truckShirts.add((Shirt) products);
            storedShirts.removeFirst();
        } else if (products.name.equalsIgnoreCase("IceCream")){
            truckIceCreams.add((IceCream) products);
            storedIceCreams.removeFirst();
        }
    }

    public void backToStoredList(Products products) {
        if (products.name.equalsIgnoreCase("egg")){
            storedEggs.add((Egg) products);
            truckEggs.removeFirst();
        } else if (products.name.equalsIgnoreCase("milk")){
            storedMilks.add((Milk) products);
            truckMilks.removeFirst();
        } else if (products.name.equalsIgnoreCase("feather")){
            storedFeathers.add((Feather) products);
            truckFeathers.removeFirst();
        } else if (products.name.equalsIgnoreCase("Powder")){
            storedPowders.add((Powder) products);
            truckPowders.removeFirst();
        } else if (products.name.equalsIgnoreCase("Cloth")){
            storedCloths.add((Cloth) products);
            truckCloths.removeFirst();
        } else if (products.name.equalsIgnoreCase("PackagedMilk")){
            storedPackagedMilks.add((PackagedMilk) products);
            truckPackagedMilks.removeFirst();
        } else if (products.name.equalsIgnoreCase("bread")){
            storedBreads.add((Bread) products);
            truckBreads.removeFirst();
        } else if (products.name.equalsIgnoreCase("shirt")){
            storedShirts.add((Shirt) products);
            truckShirts.removeFirst();
        } else if (products.name.equalsIgnoreCase("IceCream")){
            storedIceCreams.add((IceCream) products);
            truckIceCreams.removeFirst();
        }
    }

    public void removeFromStoredList(String string) {
        for (int i = 0; i < store.stuff.size() ; i++) {
            if(store.stuff.get(i).name.equals(string)){
                store.stuff.remove(i);
                break;
            }
        }
    }
    //ta inja


    public int counterProductInStore(String string) {
        int counter = 0;
        for (int i = 0; i < store.stuff.size(); i++) {
            if (store.stuff.get(i).name.equalsIgnoreCase(string)) {
                counter++;
            }
        }
        return counter;
    }

    public int buyAnimal(int coin, String string) {
        int newCoin = coin;
        String[] animalName = string.split("\\s+");
        if (animalName[1].equals("hen")) {
            if (coin >= hen.price) {
                hens.add(new Hen(randomCoordinate()));
                newCoin = coin - hen.price;
            } else
                System.err.println("you dont have enough coins!");
        } else if (animalName[1].equals("turkey")) {
            Turkey turkey = new Turkey();
            if (coin >= turkey.price) {
                turkeys.add(new Turkey(randomCoordinate()));
                newCoin = coin - turkey.price;
            } else
                System.err.println("you dont have enough coins!");
        } else if (animalName[1].equals("buffalo")) {
            Bufalo bufalo = new Bufalo();
            if (coin >= bufalo.price) {
                bufalos.add(new Bufalo(randomCoordinate()));
                newCoin = coin - bufalo.price;
            } else
                System.err.println("you dont have enough coins!");
        } else {
            System.out.println("You only can have hen , turkey , buffalo :)");
        }
        return newCoin;
    }

    public void cage(int coordinates) {
        for (int cnt = 0; cnt < bears.size(); cnt++) {
            if (bears.get(cnt).coordinate == coordinates) {
                bears.get(cnt).life -= 1;
                if (bears.get(cnt).life == 0) {
                    if (store.getCapacity() >= 15) {
                        store.setCapacity(store.getCapacity() - 15);
                        bearsInStore.add(bears.get(cnt));
                        bears.remove(cnt);
                    } else {
                        Bear bear = bears.get(cnt);
                        bear.caged = 5;
                        bearsInCage.add(bear);
                        bears.remove(cnt);
                    }
                }
                return;
            }
        }
        for (int cnt = 0; cnt < lions.size(); cnt++) {
            if (lions.get(cnt).coordinate == coordinates) {
                lions.get(cnt).life -= 1;
                if (lions.get(cnt).life == 0) {
                    if (store.getCapacity() >= 15) {
                        store.setCapacity(store.getCapacity() - 15);
                        lionsInStore.add(lions.get(cnt));
                        lions.remove(cnt);
                    } else {
                        Lion lion = lions.get(cnt);
                        lion.caged = 5;
                        lionsInCage.add(lion);
                        lions.remove(cnt);
                    }
                }
                return;
            }
        }
        for (int cnt = 0; cnt < tigers.size(); cnt++) {
            if (tigers.get(cnt).coordinate == coordinates) {
                tigers.get(cnt).life -= 1;
                if (tigers.get(cnt).life == 0) {
                    if (store.getCapacity() >= 15) {
                        store.setCapacity(store.getCapacity() - 15);
                        tigersInStore.add(tigers.get(cnt));
                        tigers.remove(cnt);
                    } else {
                        Tiger tiger = tigers.get(cnt);
                        tiger.caged = 5;
                        tigersInCage.add(tiger);
                        tigers.remove(cnt);
                    }
                }
                return;
            }
        }
    }

    public void unCage() {
        //اگه دستور بعد کیج خودش نباشه
      for (int cnt=0; cnt<bears.size();cnt++){
          if(bears.get(cnt).life<4){
              Bear bear = bears.get(cnt);
              bear.life+=1;
              bears.set(cnt,bear);
          }
      }
        for (int cnt=0; cnt<lions.size();cnt++){
            if(lions.get(cnt).life<3){
                Lion lion = lions.get(cnt);
                lion.life+=1;
                lions.set(cnt,lion);
            }
        }
        for (int cnt=0; cnt<tigers.size();cnt++){
            if(tigers.get(cnt).life<4){
                Tiger tiger = tigers.get(cnt);
                tiger.life+=1;
                tigers.set(cnt,tiger);
            }
        }
    }

    public void checkCagedAnimals() {
        for (int cnt = 0; cnt < lionsInCage.size(); cnt++) {
            if (store.getCapacity() > 15) {
                lionsInStore.add(lionsInCage.get(cnt));
                lionsInStore.remove(cnt);
                store.setCapacity(store.getCapacity()-15);
            } else {
                Lion lion = lionsInCage.get(cnt);
                lion.caged -= 1;
                if (lion.caged != 0) {
                    lionsInCage.set(cnt, lion);
                } else {
                    lionsInCage.remove(cnt);
                }
            }
        }
        for (int cnt = 0; cnt < bearsInCage.size(); cnt++) {
            if (store.getCapacity() > 15) {
                bearsInStore.add(bearsInCage.get(cnt));
                bearsInStore.remove(cnt);
                store.setCapacity(store.getCapacity()-15);
            } else {
                Bear bear = bearsInCage.get(cnt);
                bear.caged -= 1;
                if (bear.caged != 0) {
                    bearsInCage.set(cnt, bear);
                } else {
                    bearsInCage.remove(cnt);
                }
            }
        }
        for (int cnt = 0; cnt < tigersInCage.size(); cnt++) {
            if (store.getCapacity() > 15) {
                tigersInStore.add(tigersInCage.get(cnt));
                tigersInStore.remove(cnt);
                store.setCapacity(store.getCapacity()-15);
            } else {
                Tiger tiger = tigersInCage.get(cnt);
                tiger.caged -= 1;
                if (tiger.caged != 0) {
                    tigersInCage.set(cnt, tiger);
                } else {
                    tigersInCage.remove(cnt);
                }
            }
        }
    }
  /*  public void truckUnLoad(String string) {
        Truck truck = new Truck();
        Products products = new Products();
        Store store = new Store();
        //کاربر جلو اسم ایتم تعداد فروش رو هم میده
        String[] itemToSell = string.split("\\s+");
        int number = Integer.parseInt(itemToSell[3]);
        int space = number * products.capacityOfProduct.get(itemToSell[2]);
        if (store.getCapacity() >= space) {
            store.setCapacity(store.getCapacity() - space);
            for (int cnt = 0; cnt < number; cnt++) {
                truck.stuffToSell.add(itemToSell[2]);
                store.stuff.remove(itemToSell[2]);
            }
        }
    }*/


    public int truckGo(int coin) {
        Truck truck = new Truck();
        while (truck.stuffToSell.iterator().hasNext()) {
            coin += products.priceOfProduct.get(truck.stuffToSell.iterator().next());
            truck.stuffToSell.removeFirst();
        }
        return coin;
    }

    public void moving() {
        for (int cnt = 0; cnt < hens.size(); cnt++) {
            Hen hen1 = hens.get(cnt);
            hen1.coordinate = movingCoordinate(hen1.coordinate);
            hens.set(cnt, hen1);
        }
        for (int cnt = 0; cnt < turkeys.size(); cnt++) {
            Turkey turkey1 = turkeys.get(cnt);
            turkey1.coordinate = movingCoordinate(turkey1.coordinate);
            turkeys.set(cnt, turkey1);
        }
        for (int cnt = 0; cnt < bufalos.size(); cnt++) {
            Bufalo bufalo1 = bufalos.get(cnt);
            bufalo1.coordinate = movingCoordinate(bufalo1.coordinate);
            bufalos.set(cnt, bufalo1);
        }
        for (int cnt = 0; cnt < bears.size(); cnt++) {
            Bear bear1 = bears.get(cnt);
            bear1.coordinate = movingCoordinate(bear1.coordinate);
            bears.set(cnt, bear1);
        }
        for (int cnt = 0; cnt < lions.size(); cnt++) {
            Lion lion1 = lions.get(cnt);
            lion1.coordinate = movingCoordinate(lion1.coordinate);
            lions.set(cnt, lion1);
        }
    }

    public int movingCoordinate(int coordinate) {
        int newCoordinate;
        int randomMovement = random.nextInt(3);
        if (randomMovement == 0 && coordinate % 10 != 6) {
            newCoordinate = coordinate + 1;
        } else {
            newCoordinate = coordinate - 1;
        }
        if (randomMovement == 1 && coordinate % 10 != 1) {
            newCoordinate = coordinate - 1;
        } else {
            newCoordinate = coordinate + 1;
        }
        if (randomMovement == 0 && coordinate / 10 != 6) {
            newCoordinate = coordinate + 10;
        } else {
            newCoordinate = coordinate - 10;
        }
        if (randomMovement == 0 && coordinate / 10 != 1) {
            newCoordinate = coordinate - 10;
        } else {
            newCoordinate = coordinate + 10;
        }
        return newCoordinate;
    }

    public void grassToBeEaten() {
        int[][] henCheck = new int[grasses.size()][hens.size()];
        int[][] bufaloCheck = new int[grasses.size()][bufalos.size()];
        int[][] turkeyCheck = new int[grasses.size()][turkeys.size()];

        for (int cnt = 0; cnt < grasses.size(); cnt++) {
            for (int cnthen = 0; cnthen < hens.size(); cnthen += 1) {
                if (grasses.get(cnt).coordinate == (hens.get(cnthen)).coordinate) {
                    henCheck[cnt][cnthen] = hens.get(cnthen).life;
                } else
                    henCheck[cnt][cnthen] = Integer.MAX_VALUE;
            }
            for (int cntturkey = 0; cntturkey < turkeys.size(); cntturkey += 1) {
                if (grasses.get(cnt).coordinate == turkeys.get(cntturkey).coordinate) {
                    turkeyCheck[cnt][cntturkey] = turkeys.get(cntturkey).life;
                } else
                    turkeyCheck[cnt][cntturkey] = Integer.MAX_VALUE;
            }
            for (int cntbufalo = 0; cntbufalo < bufalos.size(); cntbufalo += 1) {
                if (grasses.get(cnt).equals(bufalos.get(cntbufalo))) {
                    bufaloCheck[cnt][cntbufalo] = bufalos.get(cntbufalo).life;
                } else
                    bufaloCheck[cnt][cntbufalo] = Integer.MAX_VALUE;
            }
        }
        for (int cnt = 0; cnt < grasses.size(); cnt++) {
            int a = indexOfSmallest(henCheck[cnt]);
            int aa = Arrays.stream(henCheck[cnt]).min().getAsInt();
            int b = indexOfSmallest(turkeyCheck[cnt]);
            int bb = Arrays.stream(turkeyCheck[cnt]).min().getAsInt();
            int c = indexOfSmallest(bufaloCheck[cnt]);
            int cc = Arrays.stream(bufaloCheck[cnt]).min().getAsInt();
            if (aa <= bb && aa <= cc && aa != Integer.MAX_VALUE && aa<=5) {
                Hen hen1 = new Hen(hens.get(a).coordinate);
                hen1.life = 10;
                hens.set(a, hen1);
                grasses.remove(cnt);
            } else if (bb < aa && bb <= cc && bb != Integer.MAX_VALUE && bb<=5) {
                Bufalo bufalo1 = bufalos.get(b);
                bufalo1.life = 10;
                bufalos.set(b, bufalo1);
                grasses.remove(cnt);
            } else if (cc < bb && cc < aa && cc != Integer.MAX_VALUE && cc<=5) {
                Turkey turkey1 = turkeys.get(c);
                turkey1.life = 10;
                turkeys.set(c, turkey1);
                grasses.remove(cnt);
            }
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

        for (int cnt = 0; cnt < bears.size(); cnt++) {
            for (int cnthen = 0; cnt < hens.size(); cnt += 2) {
                if (bears.get(cnt).coordinate == hens.get(cnthen).coordinate) {
                    hens.remove(cnthen);
                }
            }
            for (int cntturkey = 0; cnt < turkeys.size(); cnt += 2) {
                if (bears.get(cnt).coordinate == hens.get(cntturkey).coordinate) {
                    turkeys.remove(cntturkey);
                }
            }
            for (int cntbufalo = 0; cnt < hens.size(); cnt += 2) {
                if (bears.get(cnt).coordinate == hens.get(cntbufalo).coordinate) {
                    bufalos.remove(cntbufalo);
                }
            }
        }
        for (int cnt = 0; cnt < lions.size(); cnt++) {
            for (int cnthen = 0; cnt < hens.size(); cnt += 2) {
                if (lions.get(cnt).coordinate == hens.get(cnthen).coordinate) {
                    hens.remove(cnthen);
                }
            }
            for (int cntturkey = 0; cnt < turkeys.size(); cnt += 2) {
                if (lions.get(cnt).coordinate == hens.get(cntturkey).coordinate) {
                    turkeys.remove(cntturkey);
                }
            }
            for (int cntbufalo = 0; cnt < hens.size(); cnt += 2) {
                if (lions.get(cnt).coordinate == hens.get(cntbufalo).coordinate) {
                    bufalos.remove(cntbufalo);
                }
            }
        }
    }

    public int movingCoordinateTiger(int coordinate) {
        int newCoordinate;
        int randomMovement = random.nextInt(3);
        if (randomMovement == 0 && coordinate % 10 < 5) {
            newCoordinate = coordinate + 2;
        } else {
            newCoordinate = coordinate - 2;
        }
        if (randomMovement == 1 && coordinate % 10 > 2) {
            newCoordinate = coordinate - 2;
        } else {
            newCoordinate = coordinate + 2;
        }
        if (randomMovement == 0 && coordinate / 10 < 5) {
            newCoordinate = coordinate + 20;
        } else {
            newCoordinate = coordinate - 20;
        }
        if (randomMovement == 0 && coordinate / 10 > 2) {
            newCoordinate = coordinate - 20;
        } else {
            newCoordinate = coordinate + 20;
        }
        return newCoordinate;
    }

    public void movingTiger() {
        for (int cnt = 0; cnt < tigers.size(); cnt++) {
            Tiger tiger = tigers.get(cnt);
            int inter = tiger.coordinate;
            tiger.coordinate = movingCoordinateTiger(tiger.coordinate);
            inter += tiger.coordinate;
            inter /= 2;
            tiger.interCoordinate = inter;
            tigers.set(cnt, tiger);
        }
    }

    public void tigerToEat() {
        for (int cnt = 0; cnt < tigers.size(); cnt++) {
            for (int cnthen = 0; cnthen < hens.size(); cnthen++) {
                if (tigers.get(cnt).coordinate == hens.get(cnthen).coordinate || tigers.get(cnt).interCoordinate == hens.get(cnthen).coordinate) {
                    hens.remove(cnthen);
                }
            }
            for (int cntturkey = 0; cntturkey < turkeys.size(); cntturkey++) {
                if (tigers.get(cnt).coordinate == turkeys.get(cntturkey).coordinate || tigers.get(cnt).interCoordinate == turkeys.get(cntturkey).coordinate) {
                    turkeys.remove(cntturkey);
                }

            }
            for (int cntbufallo = 0; cntbufallo < bufalos.size(); cntbufallo++) {
                if (tigers.get(cnt).coordinate == bufalos.get(cntbufallo).coordinate || tigers.get(cnt).interCoordinate == bufalos.get(cntbufallo).coordinate) {
                    bufalos.remove(cntbufallo);
                }

            }
        }
        //دقیقا همین تابعا بالا باید تکرار شن دوباره با محصولات و همجنین تو خرس و شیر
        //گفتم اگه مخوایم عوضش کنیم خرکاریشو انجام بدم
    }

    public void dogToCatch() {
        for (int cnt = 0; cnt < dogs.size(); cnt++) {
            for (int cnttiger = 0; cnttiger < tigers.size(); cnttiger++) {
                if (dogs.get(cnt).coordinate == tigers.get(cnttiger).coordinate) {
                    dogs.remove(cnt);
                    tigers.remove(cnttiger);
                }
            }
            for (int cntlion = 0; cntlion < lions.size(); cntlion++) {
                if (dogs.get(cnt).coordinate == lions.get(cntlion).coordinate) {
                    dogs.remove(cnt);
                    lions.remove(cntlion);
                }
            }
            for (int cntbear = 0; cntbear < tigers.size(); cntbear++) {
                if (dogs.get(cnt).coordinate == bears.get(cntbear).coordinate) {
                    dogs.remove(cnt);
                    bears.remove(cntbear);
                }
            }
        }
    }

    public void catCollect(){
        for (int cnt=0;cnt<cats.size();cnt++){
            //میاد برخورد با همه کالا ها رو چک میکنه
            //نزدم چون بهتره لیست بندی کالاها عوض شن
        }
    }
}

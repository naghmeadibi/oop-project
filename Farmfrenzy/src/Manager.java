import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Manager {
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private String password;
    private String username;
    Bread bread = new Bread();
    Cloth cloth = new Cloth();
    Egg egg = new Egg();
    Feather feather = new Feather();
    IceCream iceCream = new IceCream();
    Milk milk = new Milk();
    PackagedMilk packagedMilk = new PackagedMilk();
    Powder powder = new Powder();
    Shirt shirt = new Shirt();


    public int randomCoordinate(){
        int width = (random.nextInt(5) + 1) * 10;
        int height = random.nextInt(5) + 1;
        int coordinate = width + height;
        return coordinate;
    }

    public void addBread(){
        bread.breads.add(new Bread(randomCoordinate()));
        System.out.println("Bread " + randomCoordinate());
    }

    public void addCloth() {
        if (feather.feathers.size()> 0) {
            cloth.cloths.add(new Cloth(randomCoordinate()));
            System.out.println("Done");
            feather.feathers.removeFirst();
        }
        else {
            System.out.println("Sorry, you dont have enough feather!!");
        }
    }

    public void addEgg() {
        egg.eggs.add(new Egg(randomCoordinate()));
    }

    public void addFeather() {
        feather.feathers.add(new Feather(randomCoordinate()));
    }

    public void addIceCream() {
        if (packagedMilk.packagedMilks.size()>0) {
            iceCream.iceCreams.add(new IceCream(randomCoordinate()));
            packagedMilk.packagedMilks.removeFirst();
        }
        else {
            System.out.println("Sorry, you dont have enough packaged milk!!");
        }
    }

    public void addMilk() {
        milk.milks.add(new Milk(randomCoordinate()));
    }

    public void addPackagedMilk() {
        if (milk.milks.size()>0) {
            packagedMilk.packagedMilks.add(new PackagedMilk(randomCoordinate()));
            milk.milks.removeFirst();
        }
        else {
            System.out.println("Sorry, you dont have enough milk!!");
        }
    }

    public void addPowder() {
        if (egg.eggs.size()>0) {
            powder.powders.add(new Powder(randomCoordinate()));
            egg.eggs.removeFirst();
            System.out.println("done");
        }
        else {
            System.out.println("Sorry, you dont have enough egg!!");
        }
    }

    public void addShirt() {
        if (cloth.cloths.size()>0) {
            shirt.shirts.add(new Shirt(randomCoordinate()));
            cloth.cloths.removeFirst();
        }
        else {
            System.out.println("Sorry, you dont have enough cloth!!");
        }
    }

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

    public int buyAnimal(int coin, String string) {
        Manager manager = new Manager();
        int newCoin = coin;
        String[] animalName = string.split("\\s+");
        if (animalName[1].equals("hen")) {
            Hen hen = new Hen();
            if (coin >= hen.price) {
                Farmanimals farmanimals = new Farmanimals();
                int coordinate = manager.randomCoordinate();
                hen.henList.add(String.valueOf(coordinate));
                farmanimals.allFarmAnimals.add("hen");
                farmanimals.allFarmAnimals.add(String.valueOf(coordinate));
                newCoin = coin - hen.price;
            } else
                System.err.println("you dont have enough coins!");
        } else if (animalName[1].equals("turkey")) {
            Turkey turkey = new Turkey();
            if (coin >= turkey.price) {
                Farmanimals farmanimals = new Farmanimals();
                int coordinate = manager.randomCoordinate();
                turkey.turkeyList.add(String.valueOf(coordinate));
                farmanimals.allFarmAnimals.add("turkey");
                farmanimals.allFarmAnimals.add(String.valueOf(coordinate));
                newCoin = coin - turkey.price;
            } else
                System.err.println("you dont have enough coins!");
        } else if (animalName[1].equals("bufallo")) {
            Bufalo bufalo = new Bufalo();
            if (coin >= bufalo.price) {
                Farmanimals farmanimals = new Farmanimals();
                int coordinate = manager.randomCoordinate();
                bufalo.buffaloList.add(String.valueOf(coordinate));
                farmanimals.allFarmAnimals.add("bufallo");
                farmanimals.allFarmAnimals.add(String.valueOf(coordinate));
                newCoin = coin - bufalo.price;
            } else
                System.err.println("you dont have enough coins!");
        } else {
            System.out.println("You only can have hen , turkey , bufallo :)");
        }
        return newCoin;
    }

    public void pickup(int coordinates) {
        Products products = new Products();
        Store store = new Store();
        int index = products.allUnPickedupedProducts.indexOf(String.valueOf(coordinates));
        String name = products.allUnPickedupedProducts.get(index - 1);
        products.allUnPickedupedProducts.remove(index - 1);
        products.allUnPickedupedProducts.remove(index);
        store.stuff.add(name);
        store.setCapacity(store.getCapacity() - products.capacityOfProduct.get(name));
    }

    public int well() {
        return 5;
    }

    public int plant(int wellWater) {
        Grass grass = new Grass();
        int newWellWater = wellWater - 1;
        int coordinate = randomCoordinate();
        grass.grassList.add(String.valueOf(coordinate));
        return newWellWater;
    }

    public void cage(int coordinates) {
        WildAnimals wildAnimals = new WildAnimals();
        int index = wildAnimals.wildanimals.indexOf(String.valueOf(coordinates));
        int life = Integer.parseInt(wildAnimals.wildanimals.get(index + 1)) - 1;
        if (life == 0) {
            wildAnimals.wildanimals.remove(index - 1);
            wildAnimals.wildanimals.remove(index);
            wildAnimals.wildanimals.remove(index + 1);
        } else {
            wildAnimals.wildanimals.set(index + 1, String.valueOf(life));
        }
        //اول اسم حیوون بعد مختصاتش بعد جونش داخل لیستن
        //با استرینگ کار میکنم که موقع فایل راحت تر باشیم
    }

    public void truckLoad(String string) {
        Truck truck = new Truck();
        Products products = new Products();
        Store store = new Store();
        //کاربر جلو اسم ایتم تعداد فروش رو هم میده
        String[] itemToSell = string.split("\\s+");
        int number = Integer.parseInt(itemToSell[3]);
        int space = number * products.capacityOfProduct.get(itemToSell[2]);
        if (truck.getCapacity() >= space) {
            if (counter(store.stuff, itemToSell[2]) > number) {
                truck.setCapacity(truck.getCapacity() - space);
                store.setCapacity(store.getCapacity() + space);
                for (int cnt = 0; cnt < number; cnt++) {
                    truck.stuffToSell.add(itemToSell[2]);
                    store.stuff.remove(itemToSell[2]);
                }
            } else {
                System.out.println("we dont have enough" + itemToSell[2] + "in store :(");
            }
        } else
            System.out.println("there's not enough space in truck!");
    }

    public int counter(LinkedList<String> linkedList, String string) {
        int counter = 0;
        while (linkedList.iterator().hasNext()) {
            if (linkedList.iterator().next().equals(string)) {
                counter++;
            }
        }
        return counter;
    }

    public void truckUnLoad(String string) {
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
    }

    public int truckGo(int coin) {
        Products products = new Products();
        Truck truck = new Truck();
        while (truck.stuffToSell.iterator().hasNext()) {
            coin += products.priceOfProduct.get (truck.stuffToSell.iterator().next());
            truck.stuffToSell.removeFirst();
        }
        return coin;
    }

}

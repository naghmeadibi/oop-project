import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Manager {
    //private Manager manager = new Manager();
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final Bread bread = new Bread();

    public int randomCoordinate() {
        int width = (random.nextInt(5) + 1) * 10;
        int height = random.nextInt(5) + 1;
        int coordinate = width + height;
        return coordinate;
    }

    public void workBakery() {
        bread.breads.add(new Bread(randomCoordinate()));
        System.out.println("Bread " + randomCoordinate());
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

    public void turn() {

    }

    public void grassToBeEatenAndWildAnimalToEat(LinkedList grass, LinkedList farmAnimal, LinkedList wildAnimal) {

    }

    public void moving(LinkedList animal, LinkedList tiger) {

    }

    public int upgradeStore(int coin) {
        int newCoin = coin;
        return coin;
    }
}

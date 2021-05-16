import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Manager {
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public int buyAnimal(int coin) {
        int newCoin = coin;
        System.out.println("Enter animal name *_* ");
        String animalName = scanner.next();
        if (animalName.equals("hen")) {
            Hen hen = new Hen();
            Farmanimals farmanimals = new Farmanimals();
            int width = (random.nextInt() % 6 + 1) * 10;
            int height = random.nextInt() % 6 + 1;
            int coordinate = width + height;
            LinkedList<String> newList = hen.getHenList();
            newList.add(String.valueOf(coordinate));
            hen.setHenList(newList);
            newList.clear();
            newList = farmanimals.getAllFarmAnimals();
            newList.add(String.valueOf(coordinate));
            farmanimals.setAllFarmAnimals(newList);
            newCoin = coin - hen.price;
        }
        if (animalName.equals("turkey")) {
            Turkey turkey = new Turkey();
            Farmanimals farmanimals = new Farmanimals();
            int width = (random.nextInt() % 6 + 1) * 10;
            int height = random.nextInt() % 6 + 1;
            int coordinate = width + height;
            LinkedList<String> newList = turkey.getTurkeyList();
            newList.add(String.valueOf(coordinate));
            turkey.setTurkeyList(newList);
            newList.clear();
            newList = farmanimals.getAllFarmAnimals();
            newList.add(String.valueOf(coordinate));
            farmanimals.setAllFarmAnimals(newList);
            newCoin = coin - turkey.price;
        }
        if (animalName.equals("bufallo")) {
            Bufalo bufalo = new Bufalo();
            Farmanimals farmanimals = new Farmanimals();
            int width = (random.nextInt() % 6 + 1) * 10;
            int height = random.nextInt() % 6 + 1;
            int coordinate = width + height;
            LinkedList<String> newList = bufalo.getBuffaloList();
            newList.add(String.valueOf(coordinate));
            bufalo.setBuffaloList(newList);
            newList.clear();
            newList = farmanimals.getAllFarmAnimals();
            newList.add(String.valueOf(coordinate));
            farmanimals.setAllFarmAnimals(newList);
            newCoin = coin - bufalo.price;
        }
        return newCoin;
    }

    public void pickup(int coordinates) {
        Products products = new Products();
        Store store = new Store();

        int index = products.getAllUnPickedupedProducts().indexOf(String.valueOf(coordinates));
        String name = products.getAllUnPickedupedProducts().get(index - 1);
        int capacity = Integer.parseInt(products.getAllUnPickedupedProducts().get(index + 1));

        LinkedList<String> newList = new LinkedList<String>();

        newList = products.getAllUnPickedupedProducts();

        newList.remove(index - 1);
        newList.remove(index);
        newList.remove(index + 1);

        products.setAllUnPickedupedProducts(newList);

        newList.clear();
        //تو لیست اسم پروداکت و بعدش مختصاتشو و بعدش جایی که میگیره تو انبارو  داریم
        newList = store.getStuff();
        newList.add(name);
        store.setStuff(newList);
        store.setCapacity(store.getCapacity() - capacity);
    }

    public int well() {
        return 5;
    }

    public int plant(int wellWater) {
        Grass grass = new Grass();
        int newWellWater = wellWater - 1;
        int width = (random.nextInt() % 6 + 1) * 10;
        int height = random.nextInt() % 6 + 1;
        int coordinate = width + height;
        LinkedList<String> newList = grass.getGrassList();
        newList.add(String.valueOf(coordinate));
        grass.setGrassList(newList);
        return newWellWater;
    }

    public void work() {

    }

    public void cage(int coordinates) {

    }

    public void truckLoad() {

    }

    public void truckUnLoad() {

    }

    public void truckGo() {

    }

    public void turn(int timeCounter) {

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

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
    Grass grass = new Grass();
    Hen hen = new Hen();
    Turkey turkey = new Turkey();
    Bufalo bufalo = new Bufalo();
    Products products = new Products();
    int wellWater = 5;
    public LinkedList<Bread> breads = new LinkedList<Bread>();
    public LinkedList<Milk> milks = new LinkedList<Milk>();
    public LinkedList<PackagedMilk> packagedMilks = new LinkedList<PackagedMilk>();
    public LinkedList<Powder> powders = new LinkedList<Powder>();
    public LinkedList<Shirt> shirts = new LinkedList<Shirt>();
    public LinkedList<Cloth> cloths = new LinkedList<Cloth>();
    public LinkedList<Egg> eggs = new LinkedList<Egg>();
    public LinkedList<Feather> feathers = new LinkedList<Feather>();
    public LinkedList<IceCream> iceCreams = new LinkedList<IceCream>();


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

    public int randomCoordinate(){
        int width = (random.nextInt(6) + 1) * 10;
        int height = random.nextInt(6) + 1;
        int coordinate = width + height;
        return coordinate;
    }

    public int random1 () {
        return random.nextInt(6);
    }

    public void addBread(){
        if ((powders.size() > 0)) {
            int x = randomCoordinate();
            Bread bread1 = new Bread(x);
            breads.add(bread1);
            powders.removeFirst();
            products.allUnPickedupedProducts.add(bread1);
           // removeFromUnpickedList("powder");
            System.out.println("Bread " + x);
        }
        else {
            System.out.println("Sorry, you dont have enough powder!!");
        }
    }

    public void addCloth() {
        if (feathers.size()> 0) {
            int x = randomCoordinate();
            Cloth cloth1 = new Cloth(x);
            cloths.add(cloth1);
            System.out.println("Cloth " + x);
            feathers.removeFirst();
         //   removeFromUnpickedList("fearher");
            products.allUnPickedupedProducts.add(cloth1);
        }
        else {
            System.out.println("Sorry, you dont have enough feather!!");
        }
    }

    public void addEgg() {
        int x = randomCoordinate();
        Egg egg1 = new Egg(x);
        eggs.add(egg1);
        products.allUnPickedupedProducts.add(egg1);
        System.out.println("Egg " + x );
    }

    public void addFeather() {
        int x = randomCoordinate();
        Feather feather1 = new Feather(x);
        feathers.add(feather1);
        System.out.println("Feather " + x);
        products.allUnPickedupedProducts.add(feather1);
    }

    public void addIceCream() {
        if (packagedMilks.size()>0) {
            int x = randomCoordinate();
            IceCream iceCream1 = new IceCream(x);
            iceCreams.add(iceCream1);
            packagedMilks.removeFirst();
          //  removeFromUnpickedList("packagedMilk");
            products.allUnPickedupedProducts.add(iceCream1);
            System.out.println("Ice Cream " + x);
        }
        else {
            System.out.println("Sorry, you dont have enough packaged milk!!");
        }
    }

    public void addMilk() {
        int x = randomCoordinate();
        Milk milk1 = new Milk(x);
        milks.add(milk1);
        products.allUnPickedupedProducts.add(milk1);
        System.out.println("Milk " + x);
    }

    public void addPackagedMilk() {
        if (milks.size()>0) {
            int x = randomCoordinate();
            PackagedMilk packagedMilk1 = new PackagedMilk(x);
            packagedMilks.add(packagedMilk1);
            products.allUnPickedupedProducts.add(packagedMilk1);
          //  removeFromUnpickedList("milk");
            milks.removeFirst();
            System.out.println("Packaged Milk " + x);
        }
        else {
            System.out.println("Sorry, you dont have enough milk!!");
        }
    }

    public void addPowder() {
        if (eggs.size()>0) {
            int x = randomCoordinate();
            Powder powder1 = new Powder(x);
            powders.add(powder1);
            eggs.removeFirst();
            products.allUnPickedupedProducts.add(powder1);
         //   removeFromUnpickedList("egg");
            System.out.println("Powder " + x);
        }
        else {
            System.out.println("Sorry, you dont have enough egg!!");
        }
    }

    public void addShirt() {
        if (cloths.size()>0) {
            int x = randomCoordinate();
            Shirt shirt1 = new Shirt(x);
            shirts.add(shirt1);
          //  removeFromUnpickedList("cloth");
            cloths.removeFirst();
            System.out.println("Shirt " + x);
        }
        else {
            System.out.println("Sorry, you dont have enough cloth!!");
        }
    }

    public void well() {
        if (wellWater == 0) {
            wellWater = 5;
            System.out.println("Your well water is : " + wellWater);
        }
        else {
            System.out.println("Your well is not empty!!");
        }
    }

    public void plant(int x) {
        if (wellWater > 0) {
            Grass grass = new Grass();
            wellWater--;
            grass.grassList.add(new Grass(x));
            System.out.println("grass " + x);
            System.out.println("Well water : " + wellWater);
        }
        else {
            System.out.println("Your well is empty!!");
        }
    }

    public void pickUp(int x) {
        int check = 0;
        for (int i = 0; i < products.allUnPickedupedProducts.size() ; i++) {
            if ((products.allUnPickedupedProducts.get(i).coordinate == x)) {
                check = 1;
                if (store.getCapacity() >= products.allUnPickedupedProducts.get(i).capacity) {
                    store.setCapacity(store.getCapacity() - products.allUnPickedupedProducts.get(i).capacity);
                    store.stuff.add(products.allUnPickedupedProducts.get(i));
                    products.allUnPickedupedProducts.remove(i);
                }
                else {
                    System.out.println("Error, Your warehouse does not have enough space!!");
                }
                break;
            }
        }
        if (check == 0) {
            System.out.println("this Coordinates is empty!!");
        }
    }

    public void truckLoad (String name) {
        int check = 0;
        for (int i = 0; i < store.stuff.size() ; i++) {
            if(store.stuff.get(i).name.equalsIgnoreCase(name)) {
                check = 1;
                if (truck.getCapacity() >= store.stuff.get(i).capacity){
                    truck.setCapacity(truck.getCapacity() - store.stuff.get(i).capacity);
                    truck.stuffToSell.add(store.stuff.get(i));
                    System.out.println(truck.getCapacity());
                    store.stuff.remove(i);
                }
                else {
                    System.out.println("The truck does not have enough space!!");
                }
                break;
            }
        }
        if (check == 0){
            System.out.println("This product dosnt exist in warehouse");
        }
    }

    public void truckUnload (String name) {
        int check = 0;
        for (int i = 0; i < truck.stuffToSell.size() ; i++) {
            if (truck.stuffToSell.get(i).name.equalsIgnoreCase(name)){
                check = 1;
                truck.setCapacity(truck.getCapacity() + truck.stuffToSell.get(i).capacity);
                store.stuff.add(truck.stuffToSell.get(i));
                truck.stuffToSell.remove(i);
                break;
            }
        }
        if (check == 0){
            System.out.println("Error, This product is not in the truck");
        }
    }

    public int counterProductInStore(String string) {
        int counter = 0;
        for (int i = 0; i < store.stuff.size() ; i++) {
            if (store.stuff.get(i).name.equalsIgnoreCase(string)) {
                counter ++;
            }
        }
        return counter;
    }

    public void removeFromUnpickedList (String string){
        int a = 0;
        for (int i = 0; i < products.allUnPickedupedProducts.size() ; i++) {
            if (products.allUnPickedupedProducts.get(i).name.equalsIgnoreCase(string)){
                products.allUnPickedupedProducts.remove(i);
                System.out.println("done!");
                a = 1;
                break;
            }
        }
        if (a == 0) {
            System.out.println("ridi");
        }
    }


  /*  public int buyAnimal(int coin, String string) {
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
    }*

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
        Products products = new Products();
        Truck truck = new Truck();
        while (truck.stuffToSell.iterator().hasNext()) {
            coin += products.priceOfProduct.get (truck.stuffToSell.iterator().next());
            truck.stuffToSell.removeFirst();
        }
        return coin;
    }

    public void moving() {
        Hen hen = new Hen();
        Turkey turkey = new Turkey();
        Bufalo bufalo = new Bufalo();
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
        int[][] henCheck = new int[grass.grassList.size()][hen.henList.size()/2];
        int[][] bufaloCheck = new int[grass.grassList.size()][bufalo.buffaloList.size()/2];
        int[][] turkeyCheck = new int[grass.grassList.size()][turkey.turkeyList.size()/2];

        for (int cnt = 0; cnt < grass.grassList.size(); cnt++) {
            for (int cnthen = 0; cnthen < hen.henList.size(); cnthen += 2) {
                if(grass.grassList.get(cnt).equals(hen.henList.get(cnthen))){
                    henCheck[cnt][cnthen/2] = Integer.parseInt(hen.henList.get(cnthen+1));
                } else
                    henCheck[cnt][cnthen/2] = Integer.MAX_VALUE;
            }
            for (int cntturkey = 0; cntturkey < turkey.turkeyList.size(); cntturkey += 2) {
                if(grass.grassList.get(cnt).equals(turkey.turkeyList.get(cntturkey))){
                    turkeyCheck[cnt][cntturkey/2] = Integer.parseInt(turkey.turkeyList.get(cntturkey+1));
                } else
                    turkeyCheck[cnt][cntturkey/2] = Integer.MAX_VALUE;
            }
            for (int cntbufalo = 0; cntbufalo < bufalo.buffaloList.size(); cntbufalo += 2) {
                if(grass.grassList.get(cnt).equals(bufalo.buffaloList.get(cntbufalo))){
                    bufaloCheck[cnt][cntbufalo/2] = Integer.parseInt(bufalo.buffaloList.get(cntbufalo+1));
                } else
                    bufaloCheck[cnt][cntbufalo/2] = Integer.MAX_VALUE;
            }
        }
        for(int cnt = 0;cnt<hen.henList.size();cnt++){
            int a = indexOfSmallest(henCheck[cnt]);
            int aa = Arrays.stream(henCheck[cnt]).min().getAsInt();
            int b = indexOfSmallest(turkeyCheck[cnt]);
            int bb = Arrays.stream(turkeyCheck[cnt]).min().getAsInt();
            int c = indexOfSmallest(bufaloCheck[cnt]);
            int cc = Arrays.stream(bufaloCheck[cnt]).min().getAsInt();
            if(aa<=bb && aa<=cc && aa!=Integer.MAX_VALUE){
                hen.henList.set(a*2+1,"10");
                grass.grassList.remove(cnt);
            } else
            if(bb<aa && bb<=cc && bb!=Integer.MAX_VALUE){
                bufalo.buffaloList.set(b*2+1,"10");
                grass.grassList.remove(cnt);
            } else
            if(cc<bb && cc<aa && cc!=Integer.MAX_VALUE){
                hen.henList.set(c*2+1,"10");
                grass.grassList.remove(cnt);
            }
        }

    }

    public static int indexOfSmallest(int[] array){

        // add this
        if (array.length == 0)
            return -1;

        int index = 0;
        int min = array[index];

        for (int i = 1; i < array.length; i++){
            if (array[i] <= min){
                min = array[i];
                index = i;
            }
        }
        return index;
    }

    public void bearAndLionToEat(){
        Bear bear = new Bear();
        Lion lion = new Lion();
        Hen hen = new Hen();
        Turkey turkey = new Turkey();
        Bufalo bufalo = new Bufalo();
        for(int cnt =0 ; cnt<bear.bearList.size() ; cnt++){
            for(int cnthen =0 ; cnt<hen.henList.size();cnt+=2){
                if(bear.bearList.get(cnt).equals(hen.henList.get(cnthen))){
                    hen.henList.remove(cnthen);
                    hen.henList.remove(cnthen+1);
                }
            }
            for(int cntturkey =0 ; cnt<turkey.turkeyList.size();cnt+=2){
                if(bear.bearList.get(cnt).equals(hen.henList.get(cntturkey))){
                    turkey.turkeyList.remove(cntturkey);
                    turkey.turkeyList.remove(cntturkey+1);
                }
            }
            for(int cntbufalo =0 ; cnt<hen.henList.size();cnt+=2){
                if(bear.bearList.get(cnt).equals(hen.henList.get(cntbufalo))){
                    bufalo.buffaloList.remove(cntbufalo);
                    bufalo.buffaloList.remove(cntbufalo+1);
                }
            }
        }
        for(int cnt =0 ; cnt<lion.lionList.size() ; cnt++){
            for(int cnthen =0 ; cnt<hen.henList.size();cnt+=2){
                if(lion.lionList.get(cnt).equals(hen.henList.get(cnthen))){
                    hen.henList.remove(cnthen);
                    hen.henList.remove(cnthen+1);
                }
            }
            for(int cntturkey =0 ; cnt<turkey.turkeyList.size();cnt+=2){
                if(lion.lionList.get(cnt).equals(hen.henList.get(cntturkey))){
                    turkey.turkeyList.remove(cntturkey);
                    turkey.turkeyList.remove(cntturkey+1);
                }
            }
            for(int cntbufalo =0 ; cnt<hen.henList.size();cnt+=2){
                if(lion.lionList.get(cnt).equals(hen.henList.get(cntbufalo))){
                    bufalo.buffaloList.remove(cntbufalo);
                    bufalo.buffaloList.remove(cntbufalo+1);
                }
            }
        }
    }
}

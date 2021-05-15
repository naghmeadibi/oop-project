import java.util.LinkedList;

public class Farmanimals {
   protected int price;
   protected int timeNeededToProduce;
   protected int life = 10;
   private LinkedList<String> allFarmAnimals = new LinkedList<String>();

   public void setAllFarmAnimals(LinkedList<String> allFarmAnimals) {
      this.allFarmAnimals = allFarmAnimals;
   }

   public LinkedList<String> getAllFarmAnimals() {
      return allFarmAnimals;
   }
}

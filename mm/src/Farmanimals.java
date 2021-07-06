import java.util.LinkedList;

public class Farmanimals {
   LinkedList<Farmanimals> farmanimalss = new LinkedList<>();
   protected int price;
   protected int timeNeededToProduce;
   protected int life = 10;
   protected int coordinate;
   protected String name;
   protected int capacity;

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public int getTimeNeededToProduce() {
      return timeNeededToProduce;
   }

   public void setTimeNeededToProduce(int timeNeededToProduce) {
      this.timeNeededToProduce = timeNeededToProduce;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getLife() {
      return life;
   }

   public void setLife(int life) {
      this.life = life;
   }

   public int getCoordinate() {
      return coordinate;
   }

   public void setCoordinate(int coordinate) {
      this.coordinate = coordinate;
   }
}

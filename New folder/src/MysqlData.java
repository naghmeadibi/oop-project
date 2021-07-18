import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


class MysqlData {

    private static LinkedList<Level> levels = new LinkedList<>();
    public static void main(String[] args) {
        final String username = "root";
        final String password = "naghme1380";
        final String con = "jdbc:mysql://localhost:3306/stagegame";
        String sql = "SELECT * " +
                "FROM level";

        try (Connection conn = DriverManager.getConnection(con, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int levelIndex = rs.getInt("idlevel");
                int coin = rs.getInt("coin");
                HashMap<String,Integer> tasks = new HashMap<>();
                String taskName = rs.getString("taskname");
                String[] taskNames = taskName.split("\\s+");
                String taskAmount = rs.getString("taskamount");
                String[] taskAmounts = taskAmount.split("\\s+");
                for (int i = 0; i < taskAmounts.length; i++) {
                    tasks.put(taskNames[i],Integer.parseInt(taskAmounts[i]));
                }
                HashMap<Integer,String> wildAnimal = new HashMap<>();
                String wildAnimalName = rs.getString("wildname");
                String[] wildAnimalNames = wildAnimalName.split("\\s+");
                String wildAnimalTime = rs.getString("wildtime");
                String[] wildAnimalTimes = wildAnimalTime.split("\\s+");
                for (int i = 0; i < wildAnimalTimes.length; i++) {
                    wildAnimal.put(Integer.parseInt(wildAnimalTimes[i]),wildAnimalNames[i]);
                }
                HashMap<Integer,Integer> time = new HashMap<>();
                String timeFinish = rs.getString("timefinish");
                String[] timeFinishes = timeFinish.split("\\s+");
                String timePrize = rs.getString("timeprize");
                String[] timePrizes = timePrize.split("\\s+");
                for (int i = 0; i < timeFinishes.length; i++) {
                    time.put(Integer.parseInt(timeFinishes[i]),Integer.parseInt(timePrizes[i]));
                }

               Level level = new Level(levelIndex,coin,tasks,wildAnimal,time);
                levels.add(level);



            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    }

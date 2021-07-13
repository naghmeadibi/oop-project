import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;


class MysqlData {
    private static final String username = "root";
    private static final String password = "naghme1380";
    private static final String con = "jdbc:mysql://localhost:3306/levels";
    private static ArrayList<User> users = new ArrayList<>();
   /* public static void main(String[] args)
    {
        try
        {
            // create a java mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/test";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // create the java mysql update preparedstatement
            String query = "update users set num_points = ? where first_name = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt   (1, 6000);
            preparedStmt.setString(2, "Fred");

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    } */


    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.readUser();
        users =(ArrayList<User>) manager.users.clone();
        users.get(1).workShops.put("bakery",false);
        try (Connection conn = DriverManager.getConnection(con, username, password)) {

            for (int i = 0; i < users.size(); i++) {
                String query = "update user set money = ? , level_user = ?,mill = ?,bakery=?,sewing=?,weaving=?,milkPackaging=?,iceCreamShop=? where username = ?";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, users.get(i).money);
                preparedStmt.setInt(2, users.get(i).level);
                preparedStmt.setBoolean(3, users.get(i).workShops.get("mill"));
                preparedStmt.setBoolean(4, users.get(i).workShops.get("bakery"));
                preparedStmt.setBoolean(5, users.get(i).workShops.get("Sewing"));
                preparedStmt.setBoolean(6, users.get(i).workShops.get("clothWeaving"));
                preparedStmt.setBoolean(7, users.get(i).workShops.get("milkPackaging"));
                preparedStmt.setBoolean(8, users.get(i).workShops.get("iceCreamShop"));

                preparedStmt.setString(9, users.get(i).name);


                preparedStmt.execute();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
} 
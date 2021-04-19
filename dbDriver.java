import java.io.*;
import java.sql.*;
import java.util.Properties;



public class dbDriver {

    public static void main(String[] args) throws SQLException {
        Connection dbConnection = null;
        //base connection to database
        try {
            String url = "jdbc:mysql://ip goes here:3306/spamFilterData";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "password goes here");
            dbConnection = DriverManager.getConnection(url, info);

            if (dbConnection != null) {
                System.out.println("Successfully connected to MySQL database test");
            } } catch (SQLException ex) {
            System.out.println("An error occurred while connecting MySQL databse");
            ex.printStackTrace(); }

        //first query set
            Statement state = dbConnection.createStatement();
            ResultSet rs;
        //query command
            rs = state.executeQuery("SELECT * FROM learnData;");
            while ( rs.next() ) {
                String id = rs.getString("id");
                String data = rs.getString("message");
                String flag = rs.getString("flag");

                System.out.print(id + ": " + flag + ": " + data + "\n");
            }
            dbConnection.close();



        }
    }





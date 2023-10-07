package better.life;

import java.sql.*;

public class DBHelper {
    
    Connection connection;
    Statement statement; 
    public DBHelper(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql:///better_life", "root", "Nagpur$123");
            statement = connection.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}

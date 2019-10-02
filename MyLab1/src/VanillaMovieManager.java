
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *Created by T00204198 on 11/09/2019
 */


public class VanillaMovieManager {

    private String driverClass = "oracle.jdbc.driver.OracleDriver";
    private Connection  connection = null;
    private String url =
            "jdbc:oracle:thin:@studentoracle.students.ittralee.ie:1521:orcl";
    private String username ="T00204198";
    private String password = "usfpxxsj";

    public VanillaMovieManager(){

    }

    private void setConnection(){
        try{
            Class.forName(driverClass).newInstance();
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (Exception ex) {
            System.err.println("Exception:"+ ex.getMessage());
            ex.printStackTrace();
        }

    }

    private Connection getConnection() {
        if (connection == null) {
            setConnection();
        }
        return connection;
    }

    public static void main(String[] args){
        VanillaMovieManager manager = new VanillaMovieManager();
        manager.setConnection();



    }


}

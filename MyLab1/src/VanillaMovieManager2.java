
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *Created by T00204198 on 11/09/2019
 */


public class VanillaMovieManager2 {



    private String driverClass = "oracle.jdbc.driver.OracleDriver";
    private Connection  connection = null;
    private String url =
            "jdbc:oracle:thin:@studentoracle.students.ittralee.ie:1521:orcl";
    private String username ="T00204198";
    private String password = "usfpxxsj";


    // add the next two lines directly after the class definition
    private String insertSql = "INSERT INTO MOVIES VALUES (3, 'Michael Collins','Neil Jordan', 'Irish civil war')";
    private String selectSql = "SELECT * FROM MOVIES";

    public VanillaMovieManager2(){

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

    private void persistMovie() {

            try {
                Statement st = getConnection().createStatement();
// Execute the statement
                st.executeUpdate(insertSql);
                System.out.println("Movie persisted successfully!");
            } catch (java.sql.SQLException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }


    }

    public static void main(String[] args){
        VanillaMovieManager2 manager = new VanillaMovieManager2();
        manager.setConnection();

        manager.persistMovie();

    }



}

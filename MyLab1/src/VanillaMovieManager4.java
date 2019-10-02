
import java.sql.*;
import java.sql.PreparedStatement;

/**
 *Created by T00204198 on 11/09/2019
 */


public class VanillaMovieManager4 {



    private String driverClass = "oracle.jdbc.driver.OracleDriver";
    private Connection  connection = null;
    private String url =
            "jdbc:oracle:thin:@studentoracle.students.ittralee.ie:1521:orcl";
    private String username ="T00204198";
    private String password = "usfpxxsj";
   // private String insertSql = "INSERT INTO MOVIES VALUES (3, 'Michael Collins','Neil Jordan', 'Irish civil war')";
    private String selectSql = "SELECT * FROM MOVIES";


    // add the next two lines directly after the class definition
    private String insertSql2 = "insert into movies " + "(id, title, director, synopsis) " + "values " + "(?, ?, ?, ?)";

    public VanillaMovieManager4(){

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
            PreparedStatement pst = getConnection().prepareStatement(insertSql2);
            pst.setInt(1, 4);
            pst.setString(2, "Lawrence of Arabia");
            pst.setString(3, "David Lean");
            pst.setString(4, "First World War");
// Execute the statement
            pst.execute();
            System.out.println("Movie persisted successfully!");
        } catch (java.sql.SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();


        }
    }

    public static void main(String[] args){
        VanillaMovieManager4 manager = new VanillaMovieManager4();
        manager.setConnection();

        manager.persistMovie();
        manager.queryMovies();

    }

    private void queryMovies() {
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(selectSql);
            while (rs.next()) {
                System.out.println("Movie Found: " + rs.getInt("ID") +
                        ", DIRECTOR: " + rs.getString("DIRECTOR"));
            }
        } catch (java.sql.SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }




}

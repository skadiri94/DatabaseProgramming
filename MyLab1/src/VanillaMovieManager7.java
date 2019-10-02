import java.sql.*;
import java.sql.Savepoint;

/**
 *Created by T00204198 on 11/09/2019
 */


public class VanillaMovieManager7 {



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

    public VanillaMovieManager7(){

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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();


        }
    }

    public static void main(String[] args){
        VanillaMovieManager7 manager = new VanillaMovieManager7();
        manager.setConnection();

        //manager.persistMovie();
        manager.queryMovies();
        int total = manager.noMovies();
        System.out.println("Number of Movies :" + total);






    }

    private void queryMovies() {
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(selectSql);
            while (rs.next()) {
                System.out.println("Movie Found: " + rs.getInt("ID") +
                        ", DIRECTOR: " + rs.getString("DIRECTOR"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private int noMovies() {
        int count = 0 ;
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(selectSql);

            while (rs.next()) {
               count ++;
            }
            return count;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return count;
    }


    public  void printMetaData() {
        try {
            Statement stmt = getConnection().createStatement();

            ResultSet rs = stmt.executeQuery(selectSql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println("Column Count:" + columnCount);

            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Index:" + i +
                        ", Name:" + rsmd.getColumnName(i) +
                        ", Label:" + rsmd.getColumnLabel(i) +
                        ", Type Name:" + rsmd.getColumnTypeName(i) +
                        ", Class Name:" + rsmd.getColumnClassName(i));
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("error");
        }
    }

    public  void useSavepoint(){
        String SQL = "update movies " +"set synopsis = ? "+"where id=?";
        PreparedStatement pstmt = null;
        try{
            getConnection().setAutoCommit(false);
            pstmt = getConnection().prepareStatement(SQL);
            pstmt.setString(1, "a bad movie"); // value for synopsis
            pstmt.setInt(2, 1); // value for movie id 1
            pstmt.execute();

            // Set a save point
            Savepoint sp1 = getConnection().setSavepoint();
            //Change synopsis to ‘a terrible movie’and execute SQL again
            pstmt.setString(1, "a terrible movie");
            pstmt.execute();

            // Set a save point
            Savepoint sp2 = getConnection().setSavepoint();

            // Roll back the transaction to the save point sp1,
            // so that the synopsis for movie id 1 will remain set
            // to ‘a bad movie’ and not ‘a terrible movie’

            getConnection().rollback(sp1);

            // Commit the transaction
            getConnection().commit();
            pstmt.close();
            getConnection().close();
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());

        }
    }






}

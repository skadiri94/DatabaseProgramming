/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import org.hibernate.cfg.Configuration;

/**
 *
 * @author mkonda
 */
public class MappingTest {

    public void testCusomConfigurationWithMapping() {

        Configuration configuration = new Configuration().configure();
        
        configuration.addFile("src/Movie.hbm.xml");
        configuration.addClass(Movie.class);
        configuration.addFile("src/Trade.hbm.xml");
        configuration.addClass(Trade.class);

        

//        configuration.addClass(Movie.class);
    }

    public static void main(String[] args) {
        MappingTest test = new MappingTest();
        test.testCusomConfigurationWithMapping();
    }
}



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

/**
 * Class that demonstratest the Hibernate's configuration capabilities
 *
 * @author mkonda
 *
 */
public class ConfigurationTest {

    /**
     * Tests for simple configuration which looks for either
     * hibernate.properties or hibernate.cfg.xml in classpath.
     */
    public void testVanillaConfiguration() {

        Configuration configuration = new Configuration().configure();

        System.out.println("Configuration: "
                + configuration.configure().toString());
    }


    public static void main(String[] args) {
        ConfigurationTest test = new ConfigurationTest();
          test.testVanillaConfiguration();
    }
}

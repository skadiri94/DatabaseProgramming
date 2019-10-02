/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 *
 * @author mkonda
 */
public class ArrayTest {

    private SessionFactory factory = null;

    private void init() {
        Configuration config = new Configuration().configure();
        factory = config.buildSessionFactory();
    }

    private void persistArrays() {
        Session session = factory.getCurrentSession();
        
        session.beginTransaction();
        Showroom showroom = new Showroom();
        showroom.setLocation("Tralee Co.Kerry, Ireland "); 
        showroom.setManager("Suleman Kadiri");
        String[] cars = new String[]{"Benz","Range Rover","Bentle","Accord"};
        
        showroom.setCars(cars);
        
        session.save(showroom);
        
        session.getTransaction().commit();
        System.out.println("Done");
    }

 
    public static void main(String[] args) {
        ArrayTest test = new ArrayTest();
        test.init();
        test.persistArrays();
       
    }
}

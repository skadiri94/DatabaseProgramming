/**
 * Created by t00036478 on 08/02/2018.
 */
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BasicCarsManager {
    private SessionFactory sessionFactory = null;
    // Creating SessionFactory using 4.2 version of Hibernate
    public void initSessionFactory(){
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory();
        }
    }
    public void persistCars(Cars cars) {
        Transaction tx = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.save(cars);
            tx.commit();
        }
        catch(HibernateException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
        finally{
            session.close();
        }
    }
    public static void main(String[] args){
        BasicCarsManager manager = new BasicCarsManager();
        manager.initSessionFactory();

        Cars cars = new Cars();
        cars.setId(3);
        cars.setDiscription("honda civic");
        cars.setName("honda");
        cars.setType("japanese");
        System.out.println(manager);

        manager.persistCars(cars);



    }

}




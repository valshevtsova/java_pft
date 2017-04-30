package ru.stqa.pft.m2.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.m2.model.CustomerData;

import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void testHbConnection(){
         /*SessionFactory sessionFactory = null;
           try {
                 sessionFactory = new Configuration().configure().buildSessionFactory();
               } catch (Throwable ex) {
                 System.err.println("Initial SessionFactory creation failed. " + ex);
                 ex.printStackTrace();
               }

           Session session = sessionFactory.getCurrentSession(); */
        Configuration config = new Configuration();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
       // Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<CustomerData> result = session.createQuery( "from CustomerData" ).list();
        for ( CustomerData customer : result ) {
            System.out.println(customer);
        }
        session.getTransaction().commit();
        session.close();

    }
}

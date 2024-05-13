import models.Gender;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user1 = new User("vasya", "vasya@asd.com", Arrays.asList("java", "js"), Gender.MALE);
        session.save(user1);
        User user2 = new User("petya", "petya@asd.com", Arrays.asList("html", "css"), Gender.MALE);
        session.save(user2);
        User user3 = new User("anna", "anna@asd.com", Arrays.asList("php", "C++"), Gender.FEMALE);
        session.save(user3);

        session.getTransaction().commit();

//        List<User> users = session.createNativeQuery("select  * from user_table", User.class).getResultList();

//        List<User> users = session.createQuery("select u from User u", User.class).getResultList();
//        System.out.println(users);

//        List<Integer> idList = session.createQuery("select x.id from User x", Integer.class).getResultList();
//        System.out.println(idList);
        session.close();
        sessionFactory.close();


    }
}
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static org.hibernate.id.PersistentIdentifierGenerator.PK;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

//            Course course = session.get(Course.class, 1);
//            System.out.println(course.getTeacher().getName());
//            System.out.println(course.getStudents().size());
//            course.getStudents().forEach(student -> System.out.println(student.getName()));

            Subscription subscription = session.get(Subscription.class, new PK(1, 2));
            System.out.println("Студент " + subscription.getStudent().getName() + " подписан на курс " + subscription.getCourse().getName());

            transaction.commit();


        }
    }
}

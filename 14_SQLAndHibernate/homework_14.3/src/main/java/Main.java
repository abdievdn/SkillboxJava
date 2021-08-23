import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Subscription subscription = session.get(Subscription.class, new Subscription.Key(1, 2));
            System.out.println("Студент " + subscription.getStudent().getName() + " " +
                    "подписан на " +
                    "курс " + subscription.getCourse().getName());


            Student student = session.get(Student.class, 51);
            System.out.println("\nПодписки студента " + student.getName());
            student.getSubscriptions()
                    .forEach(s -> System.out.format("%-34s | цена %s руб. | дата подписки %s%n",
                            s.getCourse().getName(),
                            s.getCourse().getPrice(),
                            s.getSubscriptionDate()));

            Course course = session.get(Course.class, 10);
            System.out.println("\nСтуденты на курсе " + course.getName());
            course.getSubscriptions().forEach(s -> System.out.println(s.getStudent().getName()));
            transaction.commit();



        }
    }
}

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<PurchaseList> purchaseList = session.createQuery("From PurchaseList").getResultList();
            List<Student> studentList = session.createQuery("From Student").getResultList();
            List<Course> courseList = session.createQuery("From Course").getResultList();
//            session.createQuery("From LinkedPurchaseList");

            for (PurchaseList p : purchaseList) {
                Student studentId = studentList
                        .stream()
                        .filter(student -> student.getName()
                                .equals(p.getStudentName()))
                        .findAny()
                        .get();
                Course courseId = courseList
                        .stream()
                        .filter(course -> course.getName()
                                .equals(p.getCourseName()))
                        .findAny()
                        .get();

                LinkedPurchaseList linkedPurchaseList =
                        new LinkedPurchaseList(new LinkedPurchaseList.Key(studentId.getId(), courseId.getId()),
                                studentId, courseId);
                session.save(linkedPurchaseList);
            }
            transaction.commit();
        }
    }
}

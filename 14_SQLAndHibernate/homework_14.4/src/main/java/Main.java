import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            session.createQuery("From LinkedPurchaseList");


            for (PurchaseList p : purchaseList) {
                int studentId = studentList
                        .stream()
                        .filter(student -> student.getName()
                                .equals(p.getStudentName()))
                        .findAny()
                        .get()
                        .getId();
                int courseId = courseList
                        .stream()
                        .filter(course -> course.getName()
                                .equals(p.getCourseName()))
                        .findAny()
                        .get()
                        .getId();
                System.out.println(studentId + " " + courseId);
            }

//            studentList.forEach(student -> System.out.println(student.getId()));
//            purchaseLists.forEach(p -> System.out.println(p.getStudentName()));



            transaction.commit();
        }
    }
}

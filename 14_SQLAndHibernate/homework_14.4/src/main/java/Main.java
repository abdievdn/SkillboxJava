import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "select new " + StudentCourseIds.class.getName() + "(s.id, c.id)"
                    + " from " + PurchaseList.class.getSimpleName() + " p "
                    + "inner join " + Student.class.getSimpleName() + " s on s.name = p.id.studentName "
                    + "inner join " + Course.class.getSimpleName() + " c on c.name = p.id.courseName "
                    + "order by s.name ";

            List<StudentCourseIds> studentCourseIds = session.createQuery(hql).getResultList();

            studentCourseIds.forEach(id -> {
                LinkedPurchaseList linkedPurchaseList =
                        new LinkedPurchaseList(new LinkedPurchaseList.Key(id.getStudentId(), id.getCourseId()));
                session.save(linkedPurchaseList);
            });
            transaction.commit();
        }
    }
}

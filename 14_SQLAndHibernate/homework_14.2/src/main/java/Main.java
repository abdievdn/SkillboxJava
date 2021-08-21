import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите id курса: ");
        try {
            int id = scanner.nextInt();
            Course course = session.get(Course.class, id);
            System.out.println("На курсе " + course.getName() + " учиться " + course.getStudentsCount() + " студентов.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("Введите id студента: ");
        try {
            int studentId = scanner.nextInt();
            Student student = session.get(Student.class, studentId);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
            System.out.println("Студент " + student.getName() + " учиться с "
                    + simpleDateFormat.format(student.getRegistrationDate()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sessionFactory.close();
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);
        System.out.println();
        sortBySalaryAndAlphabet(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        ArrayList<Employee> sortedStaff = new ArrayList<>(staff);
        Collections.sort(staff, ((o1, o2) -> o1.getName().compareTo(o2.getName())));
        Collections.sort(staff, ((o1, o2) -> o1.getSalary().compareTo(o2.getSalary())));
        for (Employee employee : staff) {
            System.out.println(employee);
        }
    }
}
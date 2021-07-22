package ru.skillbox.main;
import java.util.*;

public class Company {

    private String name;
    private List<Employee> employeeList = new ArrayList<>();
    private long income = 0;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void hire(Employee employee) {
        employeeList.add(employee);
    }

    public void hireAll(Class employee, int employeeCount) {
        for (int i = 0; i < employeeCount; i++) {
            if (employee ==  Manager.class) employeeList.add(new Manager(this));
            if (employee == TopManager.class) employeeList.add(new TopManager(this));
            if (employee == Operator.class) employeeList.add(new Operator(this));
        }
    }

    public void fire(int index) {
        employeeList.remove(index);
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income += income;
    }

    private List<Employee> getSortEmployees() {
        ArrayList<Employee> sortSalaryStaff = new ArrayList<>(employeeList);
        Collections.sort(sortSalaryStaff, new EmployeeComparator());
        return sortSalaryStaff;
    }

    private boolean isErrorStaffCount(int count) {
        if (count < 0 || count > employeeList.size()) {
            System.out.println("Количество сотрудников превышает фактическое или меньше нуля!");;
            return true;
        }
        return false;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        if (isErrorStaffCount(count)) count = 0;
        List<Employee> topSalaryStaff = getSortEmployees();
        Collections.reverse(topSalaryStaff);
        return topSalaryStaff.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        if (isErrorStaffCount(count)) count = 0;
        List<Employee> lowestSalaryStaff = getSortEmployees();
        return lowestSalaryStaff.subList(0, count);
    }

}

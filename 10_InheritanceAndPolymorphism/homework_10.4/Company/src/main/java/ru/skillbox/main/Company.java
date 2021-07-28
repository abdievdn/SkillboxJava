package ru.skillbox.main;
import java.util.*;

public class Company {

    private String name;
    private List<Employee> employeeList = new ArrayList<>();
    private long income;

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

    public void hire(EmployeeType employeeType) {
        employeeList.add(EmployeeFactory.createEmployee(this, employeeType));
    }

    public void hireAll(EmployeeType employeeType, int employeeCount) {
        for (int i = 0; i < employeeCount; i++) {
            employeeList.add(EmployeeFactory.createEmployee(this, employeeType));
        }
    }

    public void fire(int index) {
        employeeList.remove(index);
    }

    public long getIncome() {
        return income;
    }

    public void setIncome() {
        income = 0;
        income = employeeList
            .stream()
            .filter(e -> e instanceof Manager)
            .map(e -> ((Manager) e).getEarningsForCompany())
            .reduce((x, y) -> x + y).get();
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
        List<Employee> topSalaryStaff = new ArrayList<>(employeeList);
        topSalaryStaff.sort(Comparator.comparing(Employee::getMonthSalary).reversed());
        return topSalaryStaff.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        if (isErrorStaffCount(count)) count = 0;
        List<Employee> lowestSalaryStaff = new ArrayList<>(employeeList);
        lowestSalaryStaff.sort(Comparator.comparing(Employee::getMonthSalary));
        return lowestSalaryStaff.subList(0, count);
    }
}

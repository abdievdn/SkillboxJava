package ru.skillbox.test;
import ru.skillbox.main.*;


public class Main {

    public static void main(String[] args) {

        Company company = new Company("Skyline");
        company.hireAll(Operator.class, 60);
        company.hireAll(Manager.class, 80);
        company.hireAll(TopManager.class, 10);
        System.out.println("Доход компании: " + company.getIncome());
        System.out.println();

        printTest(company);

        int fireCount = company.getEmployeeList().size() / 2;
        for (int i = 0; i < fireCount; i++) {
            company.fire(i);
        }

        printTest(company);
    }

    private static void printTest(Company company) {
        System.out.println("Список зарплат по убыванию");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }
        System.out.println();
        System.out.println("Список зарплат по возрастанию");
        for (Employee employee : company.getLowestSalaryStaff(40)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }
        System.out.println();
    }
}

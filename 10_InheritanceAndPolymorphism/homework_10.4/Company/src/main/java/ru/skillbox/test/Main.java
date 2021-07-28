package ru.skillbox.test;
import ru.skillbox.main.*;


public class Main {

    public static void main(String[] args) {

        Company company = new Company("Skyline");
        company.hireAll(EmployeeType.OPERATOR, 100);
        company.hireAll(EmployeeType.MANAGER, 80);
        company.setIncome();
        company.hireAll(EmployeeType.TOP_MANAGER, 10);
        System.out.println("Доход компании: " + company.getIncome());
        System.out.println();

        printTest(company);

        int fireCount = company.getEmployeeList().size() / 2;
        for (int i = 0; i < fireCount; i++) {
            company.fire(0);
        }

        printTest(company);
    }

    private static void printTest(Company company) {
        System.out.println("Список зарплат по убыванию");
        company.getTopSalaryStaff(15).forEach(e -> System.out.println(e.getMonthSalary() + " руб."));
        System.out.println();
        System.out.println("Список зарплат по возрастанию");
        company.getLowestSalaryStaff(40).forEach(e -> System.out.println(e.getMonthSalary() + " руб."));
        System.out.println();
    }
}

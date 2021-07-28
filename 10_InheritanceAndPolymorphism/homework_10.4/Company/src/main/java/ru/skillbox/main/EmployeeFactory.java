package ru.skillbox.main;

public class EmployeeFactory  {
    public static EmployeeBase createEmployee(Company company, EmployeeType type) {

        switch (type) {
            case OPERATOR:
                return new Operator(company);
            case MANAGER:
                return new Manager(company);
            case TOP_MANAGER:
                return new TopManager(company);
            default:
                throw new RuntimeException("Unknown EmployeeType");
        }
    }
}

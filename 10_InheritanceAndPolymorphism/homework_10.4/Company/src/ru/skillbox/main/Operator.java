package ru.skillbox.main;

public class Operator extends EmployeeType {

    public Operator(Company company) {
        super(company);
        setMonthSalary(getBaseSalary());
    }
}

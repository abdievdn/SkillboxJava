package ru.skillbox.main;

public class TopManager extends EmployeeType {

    public static final double TOP_MANAGER_PERCENT = 1.5;
    public static final int MIN_INCOME_FOR_BONUS = 10_000_000;

    public TopManager(Company company) {
        super(company);
        if (company.getIncome() > MIN_INCOME_FOR_BONUS) setMonthSalary(getBaseSalary() + getBaseSalary() * TOP_MANAGER_PERCENT);
        else setMonthSalary(getBaseSalary());
    }

}

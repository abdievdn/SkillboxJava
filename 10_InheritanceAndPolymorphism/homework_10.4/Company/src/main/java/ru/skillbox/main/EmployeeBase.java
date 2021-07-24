package ru.skillbox.main;

public abstract class EmployeeBase implements Employee {

    public static final int BASE_SALARY = 86_000;
    private Company company;
    private int baseSalary;
    private double monthSalary;

    public EmployeeBase(Company company) {
        this.company = company;
        setBaseSalary(BASE_SALARY);
    }

    @Override
    public Company getCompany() {
        return company;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(double monthSalary) {
        this.monthSalary = monthSalary;
    }
}

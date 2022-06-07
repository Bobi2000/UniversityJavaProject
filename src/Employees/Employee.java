package Employees;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import Config.PriceConfig;
import Pechatnica.Pechatnica;

public class Employee {
    private String name;
    private BigDecimal salary;
    private Pechatnica workPlace;

    public Employee(Pechatnica workPlace, String name) {
        this.salary = PriceConfig.baseSalary;
        this.workPlace = workPlace;
        this.name = name;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal newSalary) {
        this.salary = newSalary;
    }

    public Pechatnica getWorkplace() {
        return this.workPlace;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");

        return "Name: " + this.getName() + " Salary: " + df.format(this.getSalary());
    }
}

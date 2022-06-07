package Employees;

import java.math.BigDecimal;

import Config.PriceConfig;
import Pechatnica.Pechatnica;

public class Manager extends Employee {

    public Manager(Pechatnica pechatnica, String name) {
        super(pechatnica, name);

        if(this.getWorkplace().getRevenue().compareTo(PriceConfig.MinimalRevenueForManagerBonus) == 1) {
            this.setSalary(this.getSalary().add(this.getSalary().multiply(BigDecimal.valueOf(0.2))));
        }
    }
}

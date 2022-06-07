package Pechatnica;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Config.PriceConfig;
import Edition.Edition;
import Edition.Paper;
import Employees.Employee;
import Employees.Manager;
import Employees.Operator;
import Enums.CopyTypes;
import Enums.Formats;
import Enums.PaperTypes;

public class Pechatnica {
    private BigDecimal revenue;
    private BigDecimal expense;

    private List<Employee> employees = new ArrayList<Employee>();
    private List<Edition> editions = new ArrayList<Edition>();

    public Pechatnica() {

        this.revenue = PriceConfig.MonthlyRevenueForPechatnica;
        this.expense = BigDecimal.ZERO;

        this.employees.add(new Manager(this, "Bobi"));
        this.employees.add(new Operator(this, "Juan"));
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void AddNewEmployee(Employee curEmployee) {
        this.employees.add(curEmployee);
    }

    public void ShowAllEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public BigDecimal PaySalaries() {
        BigDecimal totalSalaries = BigDecimal.ZERO;

        for (Employee employee : employees) {
            totalSalaries = totalSalaries.add(employee.getSalary());
        }

        this.expense = this.expense.add(totalSalaries);
        return totalSalaries;
    }

    public Edition CreateNewEdition(CopyTypes type, String title, int count, int pagesPerEntry, PaperTypes paperType,
            Formats format) {
        Edition curEdition = new Edition(type, title, count, pagesPerEntry);

        BigDecimal priceForOnePage = PayForPaper(paperType, format, curEdition.getQuantity() * count);
        BigDecimal totalPriceForManufacturingOneEntry = priceForOnePage.multiply(BigDecimal.valueOf(pagesPerEntry));

        this.expense = this.expense.add(totalPriceForManufacturingOneEntry.multiply(BigDecimal.valueOf(count)));

        curEdition.setPriceToManufacture(totalPriceForManufacturingOneEntry);

        this.editions.add(curEdition);
        return curEdition;
    }

    public BigDecimal PayForPaper(PaperTypes type, Formats format, int quantity) {
        BigDecimal costForPaper = BigDecimal.ZERO;

        Paper paper = new Paper(type, format, quantity);

        costForPaper = paper.getPricePerOne().multiply(BigDecimal.valueOf(paper.getQuantity()));

        System.out.println(costForPaper);
        return paper.getPricePerOne();
    }

    public void SellCopiesEdition(String title, int quantity) {

        Edition curEdition = null;

        for (Edition edition : editions) {
            if (edition.getTitle().equals(title)) {
                curEdition = edition;
            }
        }

        if (curEdition == null) {
            System.out.println("There is no such edition with the title: " + title);
            return;
        }

        try {
            BigDecimal price = curEdition.SellCoppies(quantity);
            this.revenue = this.revenue.add(price);
            System.out.println(price);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void PrintAllEditions() {
        if (this.editions.size() == 0) {
            System.out.println("There are no editions in this pechatnica.");
            return;
        }

        System.out.println("Listing all editions: ");

        for (Edition edition : editions) {
            System.out.println(edition.getTitle() + " - " + edition.getQuantity());
        }
    }

    public void ShowInforForEdition(String editionTitle) {
        for (Edition edition : editions) {
            if (edition.getTitle().equals(editionTitle)) {
                System.out.println(edition.toString());
                return;
            }
        }

        System.out.println("No such entry found: Please try again with any of the bellow ones!");

        for (Edition edition : editions) {
            System.out.print(" " + edition.getTitle() + " ");
        }
    }
}

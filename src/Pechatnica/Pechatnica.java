package Pechatnica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Edition.Edition;
import Edition.Paper;
import Employees.Employee;
import Employees.Manager;
import Employees.Operator;
import Enums.CopyTypes;
import Enums.Formats;
import Enums.PaperTypes;
import IO.Input;
import IO.Output;

public class Pechatnica {
    private BigDecimal revenue;
    private BigDecimal expense;

    private List<Employee> employees = new ArrayList<Employee>();
    private List<Edition> editions = new ArrayList<Edition>();
    private List<Printer> printers = new ArrayList<Printer>();

    public Pechatnica() throws FileNotFoundException, IOException {
        this.revenue = Input.GetRevenue();
        this.expense = Input.GetExpenses();
        
        this.employees.add(new Manager(this, "Bobi"));
        this.employees.add(new Operator(this, "Juan"));

        this.printers.add(new Printer("1"));
        this.printers.add(new Printer("2"));
    }

    public BigDecimal getRevenue() {
        return this.revenue;
    }

    public BigDecimal getExpense() {
        return this.expense;
    }

    public void AddNewEmployee(Employee curEmployee) {
        this.employees.add(curEmployee);
    }

    public void ShowAllEmployees() {
        for (Employee employee : this.employees) {
            System.out.println(employee.toString());
        }
    }

    public void ShowInfoAboutPrinters() {
        for (Printer printer : this.printers) {
           System.out.println(printer.toString());
        }
    }

    public BigDecimal PaySalaries() throws IOException {
        BigDecimal totalSalaries = BigDecimal.ZERO;

        for (Employee employee : employees) {
            totalSalaries = totalSalaries.add(employee.getSalary());
        }

        this.expense = this.expense.add(totalSalaries);
        Output.SetExpenses(this.expense);
        return totalSalaries;
    }

    public Edition CreateNewEdition(CopyTypes type, String title, int count, int pagesPerEntry, PaperTypes paperType,
            Formats format) throws IOException {
        Edition curEdition = new Edition(type, title, count, pagesPerEntry);

        BigDecimal priceForOnePage = PayForPaper(paperType, format, curEdition.getQuantity() * count);
        BigDecimal totalPriceForManufacturingOneEntry = priceForOnePage.multiply(BigDecimal.valueOf(pagesPerEntry));

        this.expense = this.expense.add(totalPriceForManufacturingOneEntry.multiply(BigDecimal.valueOf(count)));
        Output.SetExpenses(this.expense);
        
        curEdition.setPriceToManufacture(totalPriceForManufacturingOneEntry);

        this.editions.add(curEdition);
        this.PrintEdition(curEdition);

        return curEdition;
    }

    public void SellCopiesEdition(String title, int quantity, Output out) {

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

        if(curEdition.getIsPrinted() == false) {
            System.out.println("Edition is still not printed! Please wait!");
            return;
        }

        try {
            BigDecimal price = curEdition.SellCoppies(quantity);
            this.revenue = this.revenue.add(price);
            Output.SetRevenue(this.revenue);


            System.out.println(price);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            StringBuilder sb = new StringBuilder();

            sb.append("Edition: " + title + System.lineSeparator());
            sb.append("Quantity: " + quantity + System.lineSeparator());
            sb.append("Single Price: " + curEdition.getPricePerEntry() + System.lineSeparator());
            sb.append("Total Price: " + price + System.lineSeparator());
            sb.append("Date: " + dtf.format(now) + System.lineSeparator());

            out.WriteReciept(sb.toString());

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
            System.out.println("Title: " + edition.getTitle() + " - Quantity " + edition.getQuantity()
                    + " - Is printed " + edition.getIsPrinted());
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

    private BigDecimal PayForPaper(PaperTypes type, Formats format, int quantity) {
        BigDecimal costForPaper = BigDecimal.ZERO;

        Paper paper = new Paper(type, format, quantity);

        costForPaper = paper.getPricePerOne().multiply(BigDecimal.valueOf(paper.getQuantity()));

        System.out.println(costForPaper);
        return paper.getPricePerOne();
    }

    private void PrintEdition(Edition edition) {
        long pagesPerPrinter = edition.pagesNeededToCreateEdition();
        long averagePagesPerPrinter = pagesPerPrinter / this.printers.size();

        for(int i = 0; i < this.printers.size(); i++) {
            Printer curPrinter = this.printers.get(i);

            if(this.printers.size() - 1 == i) {
                curPrinter.setCurrentlyPrintingPages(pagesPerPrinter);
            } else {
                curPrinter.setCurrentlyPrintingPages(averagePagesPerPrinter);
                pagesPerPrinter -= averagePagesPerPrinter;
            }

            curPrinter.setEdition(edition);
            
            Thread curThread = new Thread(curPrinter, curPrinter.getId());
            curThread.start();
        }
    }
}

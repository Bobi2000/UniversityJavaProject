import java.util.Scanner;

import Config.PriceConfig;
import Edition.Edition;
import Employees.Manager;
import Employees.Operator;
import Enums.CopyTypes;
import Enums.Formats;
import Enums.PaperTypes;
import Pechatnica.Pechatnica;

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            Pechatnica curPechatnica = new Pechatnica();

            ShowCommands();

            while (true) {
                System.out.print("Input: ");
                String input[] = scanner.nextLine().split(" ");

                if (FormatInput(input[0]).equals("end")) {
                    break;
                } else if (FormatInput(input[0]).equals("?")) {
                    ShowCommands();
                } else if (FormatInput(input[0]).equals("list")) {
                    curPechatnica.PrintAllEditions();
                } else if (FormatInput(input[0]).equals("clear")) {
                    ClearConsole();
                } else if (FormatInput(input[0]).equals("add")) {
                    AddEdition(scanner, curPechatnica);
                } else if (FormatInput(input[0]).equals("show")) {
                    ShowEdition(input, curPechatnica);
                } else if (FormatInput(input[0]).equals("pay")) {
                    PayEmployees(curPechatnica);
                } else if (FormatInput(input[0]).equals("status")) {
                    ListExpenses(curPechatnica);
                } else if (FormatInput(input[0]).equals("sell")) {
                    SellEditions(input, curPechatnica);
                } else if (FormatInput(input[0]).equals("employ-operator")) {
                    EmployOperator(input, curPechatnica);
                } else if (FormatInput(input[0]).equals("employ-manager")) {
                    EmployManager(input, curPechatnica);
                } else if (FormatInput(input[0]).equals("list-employees")) {
                    ListEmployees(curPechatnica);
                } else {
                    System.out.println("There is no such command. Please try again!");
                }
            }

            scanner.close();
        }
    }

    public static String FormatInput(String input) {
        return input.toLowerCase();
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static void ShowCommands() {
        int whiteSpaces = PriceConfig.ConsoleWhitespaces;

        System.out.println("Usage: ");
        System.out.println(padRight("?", whiteSpaces) + ": Show all commands.");
        System.out.println(padRight("clear", whiteSpaces) + ": Clear the console.");
        System.out.println(padRight("end", whiteSpaces) + ": End the program.");
        System.out.println("");
        System.out.println(padRight("employ-operator [employee name]", whiteSpaces) + ": Employ a new operator.");
        System.out.println(padRight("employ-manager [employee name]", whiteSpaces) + ": Employ a new manager.");
        System.out.println(padRight("list-employees", whiteSpaces) + ": List all employees.");
        System.out.println(padRight("add", whiteSpaces) + ": Add a new edition.");
        System.out.println(padRight("pay", whiteSpaces) + ": Pay salaries to employees.");
        System.out.println(padRight("list", whiteSpaces) + ": List all edition.");
        System.out.println(padRight("status", whiteSpaces) + ": List revenue and expenses.");
        System.out.println(
                padRight("show [edition name]", whiteSpaces) + ": Shows all the information about an edition.");
        System.out.println(padRight("sell [edition name] [count]", whiteSpaces) + ": Sells n count of editions.");
    }

    public static void ClearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void ShowEdition(String[] args, Pechatnica curPechatnica) {

        if (args.length == 2) {
            String editionName = args[1];
            curPechatnica.ShowInforForEdition(editionName);
            return;
        }

        System.out.println("Invalid input!");
    }

    public static void PayEmployees(Pechatnica curPechatnica) {
        System.out.println("Total salaries payed: " + curPechatnica.PaySalaries());
    }

    public static void ListExpenses(Pechatnica curPechatnica) {
        System.out.println("Revenue : " + curPechatnica.getRevenue());
        System.out.println("Expense : " + curPechatnica.getExpense());
    }

    public static void SellEditions(String[] args, Pechatnica curPechatnica) {
        if (args.length == 3) {
            String editionName = args[1];
            int quantity = Integer.parseInt(args[2]);

            curPechatnica.SellCopiesEdition(editionName, quantity);
            return;
        }

        System.out.println("Invalid input!");
    }

    public static void EmployOperator(String[] args, Pechatnica curPechatnica) {
        if (args.length == 2) {
            String employeeName = args[1];

            curPechatnica.AddNewEmployee(new Operator(curPechatnica, employeeName));
        }
    }

    public static void EmployManager(String[] args, Pechatnica curPechatnica) {
        if (args.length == 2) {
            String employeeName = args[1];

            curPechatnica.AddNewEmployee(new Manager(curPechatnica, employeeName));
        }
    }

    public static void ListEmployees(Pechatnica curPechatnica) {
        curPechatnica.ShowAllEmployees();
    }

    public static void AddEdition(Scanner scanner, Pechatnica curPechatnica) throws Exception {
        System.out.println("Creating new edition: ");
        System.out.println("Choose what kind of edition: ");
        System.out.println("1: Book");
        System.out.println("2: Poster");
        System.out.println("3: Newspaper");
        System.out.print("Input: ");
        String typeNumber = scanner.nextLine();

        CopyTypes type;

        if (typeNumber.equals("1")) {
            type = CopyTypes.Book;
        } else if (typeNumber.equals("2")) {
            type = CopyTypes.Poster;
        } else if (typeNumber.equals("3")) {
            type = CopyTypes.Newspaper;
        } else {
            throw new Exception("...");
        }

        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Quantity: ");
        int count = Integer.parseInt(scanner.nextLine());
        System.out.print("Pages per entry: ");
        int pagesPerEntry = Integer.parseInt(scanner.nextLine());

        System.out.println("Choose paper type: ");
        System.out.println("1: Normal");
        System.out.println("2: Glossy");
        System.out.println("3: Newspaper");
        System.out.print("Input: ");
        String paperTypeNuber = scanner.nextLine();

        PaperTypes paperType;

        if (paperTypeNuber.equals("1")) {
            paperType = PaperTypes.Normal;
        } else if (paperTypeNuber.equals("2")) {
            paperType = PaperTypes.Glossy;
        } else if (paperTypeNuber.equals("3")) {
            paperType = PaperTypes.Newspaper;
        } else {
            throw new Exception("...");
        }

        System.out.println("Choose paper format: ");
        System.out.println("1: A1");
        System.out.println("2: A2");
        System.out.println("3: A3");
        System.out.println("4: A4");
        System.out.println("5: A5");
        System.out.print("Input: ");
        String paperFormatNumber = scanner.nextLine();

        Formats format;

        if (paperFormatNumber.equals("1")) {
            format = Formats.A1;
        } else if (paperFormatNumber.equals("2")) {
            format = Formats.A2;
        } else if (paperFormatNumber.equals("3")) {
            format = Formats.A3;
        } else if (paperFormatNumber.equals("4")) {
            format = Formats.A4;
        } else if (paperFormatNumber.equals("5")) {
            format = Formats.A5;
        } else {
            throw new Exception("...");
        }

        Edition edition = curPechatnica.CreateNewEdition(type, title, count, pagesPerEntry, paperType, format);
        System.out.println("Edition " + edition.getTitle() + " has been created successfully.");
    }
}

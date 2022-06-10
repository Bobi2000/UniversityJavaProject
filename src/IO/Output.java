package IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.UUID;

public class Output {

    public void WriteReciept(String message) throws IOException {
        
        String recieptId = UUID.randomUUID().toString().substring(0, 8);
        String route = "recipets/" + recieptId + ".txt";

        File curReciept = new File(route);
        curReciept.createNewFile();

        FileWriter recieptWriter = new FileWriter(route);
        recieptWriter.write(message);
        recieptWriter.close();
    }

    public static void SetExpenses(BigDecimal money) throws IOException {
        String route = "money/expenses.txt";
        File curFile = new File(route);

        PrintWriter writer = new PrintWriter(curFile);
        writer.print("");
        writer.close();

        FileWriter recieptWriter = new FileWriter(route);
        recieptWriter.write(money.toString());
        recieptWriter.close();
    }

    public static void SetRevenue(BigDecimal money) throws IOException {
        String route = "money/revenue.txt";
        File curFile = new File(route);

        PrintWriter writer = new PrintWriter(curFile);
        writer.print("");
        writer.close();

        FileWriter recieptWriter = new FileWriter(route);
        recieptWriter.write(money.toString());
        recieptWriter.close();
    }
}

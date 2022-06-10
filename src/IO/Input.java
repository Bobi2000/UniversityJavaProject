package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Input {
    public void ReadAllReciepts() throws IOException {

        try (Stream<Path> paths = Files.walk(Paths.get("recipets"))) {
            paths
                .filter(Files::isRegularFile)
                .forEach(System.out::println);
        }
    }

    public void ReadFile(String id) throws IOException {

        String route = "recipets/" + id + ".txt";
        File curReciept = new File(route);
        if(curReciept.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(curReciept))) {
                String st;
                while ((st = br.readLine()) != null) {
                    System.out.println(st);
                }
                br.close();
            }
        } else {
            System.out.println("There isn't such receipt with id " + id);
        }
    }

    public static BigDecimal GetExpenses() throws FileNotFoundException, IOException {
        String route = "money/expenses.txt";
        File curFile = new File(route);

        BigDecimal price = BigDecimal.ZERO;

        if(curFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(curFile))) {
                String st;
                while ((st = br.readLine()) != null) {
                    if(st != null) {
                      price = BigDecimal.valueOf(Double.parseDouble(st));
                    }
                }
                br.close();
            }
        }

        return price;
    }

    public static BigDecimal GetRevenue() throws FileNotFoundException, IOException {
        String route = "money/revenue.txt";
        File curFile = new File(route);

        BigDecimal price = BigDecimal.ZERO;

        if(curFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(curFile))) {
                String st;
                while ((st = br.readLine()) != null) {
                    if(st != null) {
                      price = BigDecimal.valueOf(Double.parseDouble(st));
                    }
                }
                br.close();
            }
        }

        return price;
    }
}

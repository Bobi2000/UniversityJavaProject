package Config;

import java.math.BigDecimal;

public class PriceConfig {

    public static BigDecimal baseSalary = BigDecimal.valueOf(2000);
    public static int BonusPercentForManagers = 20; //20%
    public static BigDecimal MonthlyRevenueForPechatnica = BigDecimal.valueOf(15000);
    public static BigDecimal MinimalRevenueForManagerBonus = BigDecimal.valueOf(10000);

    public static int IfBoughtMoreThen = 99;
    public static int QuantityPercentDiscount = 10; //10%
    public static int OverchargePercentForEntries = 50; //50%

    public static double StartingPrice = 1;

    public static double A1Price = 2.0;
    public static double A2Price = 1.8;
    public static double A3Price = 1.6;
    public static double A4Price = 1.4;
    public static double A5Price = 1.2;

    public static double NewspaperPaper = 0.8;
    public static double NormalPaper = 1.0;
    public static double GlossyPaper = 1.5;

    public static int ConsoleWhitespaces = 40;
}

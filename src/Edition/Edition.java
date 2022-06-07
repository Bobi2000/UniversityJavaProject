package Edition;

import java.math.BigDecimal;

import Config.PriceConfig;
import Enums.CopyTypes;

public class Edition {
    private CopyTypes type;
    private String title;
    private int quantity;
    private int pagesPerEntry;
    private BigDecimal pricePerEntry;

    private boolean isPrinted = false;

    public Edition(CopyTypes type, String title, int quantity, int pagesPerEntry) {
        this.type = type;
        this.title = title;
        this.quantity = quantity;
        this.pagesPerEntry = pagesPerEntry;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public CopyTypes getType() {
        return type;
    }

    public int pagesNeededToCreateEdition() {
        return this.quantity * this.pagesPerEntry;
    }

    public void setPriceToManufacture(BigDecimal priceToManufacture) {
        this.pricePerEntry = priceToManufacture
                .add(priceToManufacture.multiply(BigDecimal.valueOf(PriceConfig.OverchargePercentForEntries))
                        .divide(BigDecimal.valueOf(100)));
    }

    public BigDecimal getPricePerEntry() {
        return pricePerEntry;
    }

    public BigDecimal SellCoppies(int quantity) throws Exception {

        if (this.quantity < quantity) {
            throw new Exception("There are not enough quantity of this edition! The current quantity is "
                    + this.quantity + ", but you requestet " + quantity);
        }

        this.quantity -= quantity;
        BigDecimal price = pricePerEntry.multiply(BigDecimal.valueOf(quantity));

        if (quantity > PriceConfig.IfBoughtMoreThen) {
            price = price
                    .subtract(price
                            .multiply(BigDecimal.valueOf(PriceConfig.QuantityPercentDiscount))
                            .divide(BigDecimal.valueOf(100)));
        }

        return price;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + " Type: " + this.type.toString() + " Quantity: " + this.quantity + " Pages: "
                + this.pagesPerEntry + " Price: " + this.pricePerEntry + " isPrinted " + this.isPrinted;
    }
}

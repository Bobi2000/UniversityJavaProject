package Edition;

import java.math.BigDecimal;

import Config.PriceConfig;
import Enums.Formats;
import Enums.PaperTypes;

public class Paper {
    private PaperTypes type;
    private Formats format;
    private int quantity;
    private BigDecimal pricePerOne = BigDecimal.ZERO;

    public Paper(PaperTypes type, Formats foramt, int quantity) {
        this.type = type;
        this.quantity = quantity;

        BigDecimal price = BigDecimal.valueOf(PriceConfig.StartingPrice);

        if (this.type == PaperTypes.Glossy) {
            price = price.multiply(BigDecimal.valueOf(PriceConfig.GlossyPaper));
        } else if (this.type == PaperTypes.Newspaper) {
            price = price.multiply(BigDecimal.valueOf(PriceConfig.NewspaperPaper));
        } else if (this.type == PaperTypes.Normal) {
            price = price.multiply(BigDecimal.valueOf(PriceConfig.NormalPaper));
        }

        this.pricePerOne = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public PaperTypes getType() {
        return type;
    }

    public BigDecimal getPricePerOne() {
        return pricePerOne;
    }

    public Formats getFormat() {
        return format;
    }
}

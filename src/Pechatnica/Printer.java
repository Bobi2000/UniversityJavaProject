package Pechatnica;

import Config.PriceConfig;
import Edition.Edition;

public class Printer implements Runnable {

    private String id;
    private Edition edition;
    private long currentlyPrintingPages;
    private long printedPages;
    private boolean isPrinting = false;
    private boolean isColor = true;

    public Printer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean getColor() {
        return this.isColor;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public void setCurrentlyPrintingPages(long currentlyPrintingPages) {
        this.currentlyPrintingPages = currentlyPrintingPages;
    }

    @Override
    public void run() {
        try {
            Print();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Print() throws Exception {
        this.isPrinting = true;
        while (true) {
            if (this.currentlyPrintingPages == 0) {
                break;
            }

            for (int i = 0; i < PriceConfig.PagesPerMinute; i++) {
                this.printedPages++;
                this.currentlyPrintingPages--;

                if (this.currentlyPrintingPages == 0) {
                    break;
                }
            }

            Thread.sleep(10000);
        }
        this.isPrinting = false;
        this.edition.setPrintedPages(this.printedPages, this.id);
    }

    @Override
    public String toString() {
        if(this.isPrinting) {
            return "Id: " + this.id + " - is printing " + this.edition.getTitle();
        }

        return "Id: " + this.id + " - is not printing";
    }
}

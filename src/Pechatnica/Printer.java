package Pechatnica;

public class Printer implements Runnable {

    private String id;

    public Printer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void run() {
        Print();
    }

    public void Print() {

    }
}

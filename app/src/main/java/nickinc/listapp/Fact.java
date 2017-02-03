package nickinc.listapp;


public class Fact {

    String fact;
    boolean torf;

    public Fact(String fact, boolean torf) {
        this.fact = fact;
        this.torf = torf;
    }

    public String getFact() {
        return fact;
    }

    public boolean getTorf() {
        return torf;
    }
}

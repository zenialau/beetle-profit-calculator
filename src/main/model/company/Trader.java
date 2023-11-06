package src.main.model.company;

public abstract class Trader {
    protected String name;

    public Trader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

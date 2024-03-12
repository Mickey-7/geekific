package functional_interface;

public class Customer {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

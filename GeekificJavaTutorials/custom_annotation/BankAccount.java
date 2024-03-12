package custom_annotation;
@XMLSerializable(tag = "bankAccount")
public class BankAccount {

    @XMLElement
    private int id;

    @XMLElement
    private String name;

    @XMLElement(tag = "balance")
    private double bal;

    public BankAccount(int id, String name, double bal) {
        this.id = id;
        this.name = name;
        this.bal = bal;
    }
}

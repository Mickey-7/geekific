public class BankAccount {
    private int balance = 0;

    public BankAccount() {};
    public BankAccount(int i) {
        balance = i;
    };

    public void deposit(int i) {
        if (i<0){
            throw new NumberFormatException("Negative values not allowed");
        }
        balance += i;
    }

    public int withdraw(int i) {
        return balance -= Math.min(i, 100);
    }

    public int getBalance() {
        return balance;
    }
}

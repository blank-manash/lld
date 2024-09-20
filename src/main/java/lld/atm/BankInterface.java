package lld.atm;

public interface BankInterface {
    double getBalance(User user);
    boolean deposit(User user, double amount);
    boolean withdraw(User user, double amount);
    boolean acceptPin(User user, Integer pin);
}

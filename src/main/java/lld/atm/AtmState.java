package lld.atm;

public interface AtmState {
    boolean authenticate(User user);
    boolean withDraw(User user, double amount);
    boolean deposit(User user, double amount);
}

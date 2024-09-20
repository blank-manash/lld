package lld.atm;

import java.util.EnumMap;

public class AuthenticatedAtmState implements AtmState {
    private final BankService bankService = BankService.getInstance();
    private final CashDispenser dispenser = CashDispenser.getInstance();
    @Override
    public boolean authenticate(User user) {
        return true;
    }

    @Override
    public synchronized boolean withDraw(User user, double amount) {
        try {
            Bank bank = user.bank();
            BankInterface userBank = bankService.getBankInterface(bank);
            if (userBank.getBalance(user) >= amount && userBank.withdraw(user, amount)) {
                dispenser.dispense(amount);
                return true;
            }
            throw new Exception("Insufficient Balance");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public synchronized boolean deposit(User user, double amount) {
        try {
            BankInterface userBank = this.bankService.getBankInterface(user.bank());
            userBank.deposit(user, amount);
            dispenser.addCash(amount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();;
            return false;
        }
    }
}

package lld.atm;

import java.util.EnumMap;

public class BankService {
    private final EnumMap<Bank, BankInterface> bankInterface;
    private static BankService instance = null;
    private BankService() {
        bankInterface = new EnumMap<Bank, BankInterface>(Bank.class);
    }

    public static BankService getInstance() {
        if (instance == null) {
            instance = new BankService();
        }
        return instance;
    }

    public BankInterface getBankInterface(Bank bank) {
        return this.bankInterface.get(bank);
    }

}

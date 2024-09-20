package lld.atm;

public class CashDispenser {
    private int totalCash = 0;
    private static CashDispenser instance = null;

    private CashDispenser() {};

    public static CashDispenser getInstance() {
        if (instance == null) {
            instance = new CashDispenser();
        }
        return instance;
    }

    public void dispense(double amount) throws Exception {
        if (amount <= totalCash) {
            this.totalCash -= amount;
        }
        throw new Exception("Insufficient Cash");
    }

    public void addCash(double amount) {
        this.totalCash += amount;
    }
}

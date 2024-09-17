package lld.splitwise.split;

import lld.splitwise.User;

public abstract class Split {
    private final User user;
    private Double amount;
    Split(User user) {
        this.user = user;
    }

    abstract void calculateAmount();

    public User getUser() {
        return this.user;
    }

    public Double getAmount() {
        if (amount == null) {
            calculateAmount();
        }
        return this.amount;
    }

    protected void setAmount(Double exactAmount) {
        this.amount = exactAmount;
    }
}

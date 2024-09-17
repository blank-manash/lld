package lld.splitwise.split;

import lld.splitwise.User;

public class EqualSplit extends Split {
    private final Double totalAmount;
    private final Double totalSplits;

    EqualSplit(User user, Double totalAmount, Double totalSplits) {
        super(user);
        this.totalAmount = totalAmount;
        this.totalSplits = totalSplits;
    }

    @Override
    void calculateAmount() {
        Double amount = totalAmount / totalSplits;
        this.setAmount(amount);
    }
}

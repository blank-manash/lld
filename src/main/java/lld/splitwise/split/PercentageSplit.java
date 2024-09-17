package lld.splitwise.split;

import lld.splitwise.User;

public class PercentageSplit extends Split {
    private final Double percentage;
    private final Double totalAmount;

    public PercentageSplit(User user, Double percentage, Double total) {
        super(user);
        this.totalAmount = total;
        this.percentage = percentage;
    }


    @Override
    void calculateAmount() {
        this.setAmount(
                this.totalAmount * this.percentage
        );
    }
}

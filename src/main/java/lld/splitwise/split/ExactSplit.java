package lld.splitwise.split;

import lld.splitwise.User;

public class ExactSplit extends Split {
    ExactSplit(User user, Double exactAmount) {
        super(user);
        this.setAmount(exactAmount);
    }

    void calculateAmount() {};
}

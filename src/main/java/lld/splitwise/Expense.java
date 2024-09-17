package lld.splitwise;

import lld.splitwise.split.Split;

import java.util.List;

public class Expense {
    User paidBy;
    List<Split> splits;

    public User getPayingUser() { return paidBy; }
    public List<Split> getSplits() { return splits; }
}

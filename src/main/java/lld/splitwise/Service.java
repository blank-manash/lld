package lld.splitwise;

import lld.splitwise.split.Split;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Service {
    Map<String, Group> groups;
    Map<String, User> users;
    public Service() {
        this.groups = new ConcurrentHashMap<>();
        this.users = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public Group createGroup(List<User> users) {
        Group group = new Group();
        users.forEach(group ::addMember);
        groups.put(group.getId(), group);
        return group;
    }

    public void addExpense(Group group, User paidBy, Expense expense) {
        group.addExpense(expense);
        for(Split split : expense.getSplits()) {
            Double amount = split.getAmount();
            User user = split.getUser();
            if (user.equals(paidBy)) continue;
            user.borrow(paidBy, amount);
            paidBy.refund(user, amount);
        }
    }

    public void settleBalance(User a, User b) {
       double amount = a.owe(b);
       if (amount < 0) {
           performTransaction(b, a, amount);
           a.refund(b, amount);
           b.borrow(a, amount);
       } else {
           performTransaction(a, b, amount);
           b.refund(a, amount);
           a.borrow(b, amount);
       }
    }

    void performTransaction(User from, User to, Double amount) {
        Transaction transaction = new Transaction(from, to, amount);
        transaction.process();
    }


}

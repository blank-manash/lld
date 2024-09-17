package lld.splitwise;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Group {
    String id;
    List<User> users;
    List<Expense> expenseList;
    Group() {
        this.id = String.valueOf(UUID.randomUUID());
        this.users = new CopyOnWriteArrayList<>();
        this.expenseList = new CopyOnWriteArrayList<>();
    }

    void addMember(User user) {
        this.users.add(user);
    }

    void addExpense(Expense expense) {
        this.expenseList.add(expense);
    }

    public String getId() {
        return this.id;
    }
}

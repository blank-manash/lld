package lld.splitwise;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    String id;
    String name;
    Map<String, Double> balance;

    User() {
        this.id = String.valueOf(UUID.randomUUID());
        this.balance = new ConcurrentHashMap<>();
    }


    public String getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User userObj) {
            return this.getId().equals(userObj.getId());
        }
        return false;
    }

    public void borrow(User user, double v) {
        this.balance.put(user.getId(), v);
    }

    public void refund(User user, double v) {
        this.balance.put(user.getId(), -v);
    }

    public double owe(User user) {
        return this.balance.get(user.getId());
    }
}

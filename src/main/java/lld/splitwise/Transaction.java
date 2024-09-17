package lld.splitwise;

import java.util.UUID;

public class Transaction {
    String id;
    User sender;
    User receiver;
    Double amount;
    Transaction(User sender, User receiver, Double amount) {
        this.id = String.valueOf(UUID.randomUUID());
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    public void process() {
        System.out.println("Processing Transaction");
    }
}

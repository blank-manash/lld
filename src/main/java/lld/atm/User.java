package lld.atm;

public record User(String name, String cardId, Bank bank) {
    public Integer getPin() {
        return (int) (Math.random() * 10000);
    }
}

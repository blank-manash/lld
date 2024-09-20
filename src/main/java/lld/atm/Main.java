package lld.atm;

public class Main {
    public static void main(String args[]) {
        final AtmService atmService = new AtmService();
        final User user = new User("Hi", "01010", Bank.SBI);
        atmService.authenticate(user);

    };
}

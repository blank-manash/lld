package lld.atm;

public class PINAuthentication implements AuthenticationInterface {
    private BankService service;
    @Override
    public boolean authenticate(User user) {
        Integer pin = user.getPin();
        BankInterface bank = service.getBankInterface(user.bank());
        if (bank.acceptPin(user, pin)) {
            return true;
        } return false;
    }
}

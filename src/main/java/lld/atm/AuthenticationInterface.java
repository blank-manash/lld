package lld.atm;

public interface AuthenticationInterface {
    boolean authenticate(User user);
}
// extended by multiple classes like OTPAuthentication interface, PINAuthentication
package lld.atm;

public class UnAuthenticatedAtmState implements AtmState {
    private AuthenticationInterface authInterface;
    private static UnAuthenticatedAtmState instance = null;

    public UnAuthenticatedAtmState(AuthenticationInterface authenticationInterface) {
        this.authInterface = authenticationInterface;
    }

    public static AtmState getInstance() {
        if (instance == null) {
            instance = new UnAuthenticatedAtmState(null);
        }
        return instance;
    }

    public void setAuthInterface(AuthenticationInterface authInterface) {
        this.authInterface = authInterface;
    }

    @Override
    public boolean authenticate(User user) {
        return this.authInterface.authenticate(user);
    }

    @Override
    public boolean withDraw(User user, double amount) {
        System.out.println("Please authenticate");
        this.authenticate(user);
        return false;
    }

    @Override
    public boolean deposit(User user, double amount) {
        this.authenticate(user);
        return false;
    }
}

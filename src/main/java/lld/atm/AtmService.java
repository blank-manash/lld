package lld.atm;

public class AtmService {
    private AtmState atmState;

    private AtmState getCurrentState() {
        return this.atmState;
    }

    private void setState(AtmState state) {
        this.atmState = state;
    }

    public boolean authenticate(User user) {
        boolean authenticated = this.getCurrentState().authenticate(user);
        if (authenticated) {
            this.setState(UnAuthenticatedAtmState.getInstance());
            return true;
        } else {
            return false;
        }
    }

    public boolean withDraw(User user, double amount) {
        return this.getCurrentState().withDraw(user, amount);
    }

    public boolean deposit(User user, double amount) {
        return this.getCurrentState().deposit(user , amount);
   }

   public void logOut() {
        this.setState(AuthenticatedAtmState.getInstance());
   }
}

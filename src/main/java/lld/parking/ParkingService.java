package lld.parking;

public class ParkingService {
    private final EntryTerminal entryTerminal = new EntryTerminal(new MinDistanceAllocation());
    private final ExitTerminal exitTerminal = new ExitTerminal();

    public EntryTerminal getEntryTerminal() {
        return this.entryTerminal;
    }

    public ExitTerminal getExitTerminal() {
        return this.exitTerminal;
    }
}

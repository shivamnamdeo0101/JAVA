package JAVA_15_BEHAVIORAL_PATTERNS;

/**
 * STATE PATTERN – VENDING MACHINE EXAMPLE
 */
public class Java_6_State {
    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine();

        machine.insertCoin();
        machine.selectItem();
        machine.insertCoin();
        machine.ejectCoin();
        machine.selectItem();
    }
}

// ==================== STATE INTERFACE ====================
interface MachineState {
    void insertCoin();
    void ejectCoin();
    void selectItem();
}

// ==================== CONCRETE STATES ====================
class NoCoinState implements MachineState {
    private final VendingMachine machine;
    public NoCoinState(VendingMachine machine) { this.machine = machine; }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted.");
        machine.setState(machine.getHasCoinState());
    }

    @Override
    public void ejectCoin() { System.out.println("No coin to eject."); }

    @Override
    public void selectItem() { System.out.println("Insert coin first."); }
}

class HasCoinState implements MachineState {
    private final VendingMachine machine;
    public HasCoinState(VendingMachine machine) { this.machine = machine; }

    @Override
    public void insertCoin() { System.out.println("Coin already inserted."); }

    @Override
    public void ejectCoin() {
        System.out.println("Coin returned.");
        machine.setState(machine.getNoCoinState());
    }

    @Override
    public void selectItem() {
        System.out.println("Item dispensed.");
        machine.setState(machine.getNoCoinState());
    }
}

// ==================== CONTEXT ====================
class VendingMachine {
    private final MachineState noCoinState;
    private final MachineState hasCoinState;
    private MachineState currentState;

    public VendingMachine() {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        currentState = noCoinState;
    }

    public void setState(MachineState state) { this.currentState = state; }
    public MachineState getNoCoinState() { return noCoinState; }
    public MachineState getHasCoinState() { return hasCoinState; }

    public void insertCoin() { currentState.insertCoin(); }
    public void ejectCoin() { currentState.ejectCoin(); }
    public void selectItem() { currentState.selectItem(); }
}

/*
================ STATE PATTERN – FEATURES & DESIGN =================
WHAT:
- Allows object to alter behavior when its internal state changes.
- Example: Vending machine transitions between "NoCoin" and "HasCoin".
WHY IT EXISTS:
- Avoids large conditional logic.
REAL SYSTEM USAGE:
- Workflow engines, game characters, network connections, GUI state machines
*/

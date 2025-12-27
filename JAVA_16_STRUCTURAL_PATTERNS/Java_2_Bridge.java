package JAVA_16_STRUCTURAL_PATTERNS;

/**
 * BRIDGE PATTERN – REMOTE CONTROL & DEVICE EXAMPLE
 */
public class Java_2_Bridge {
    public static void main(String[] args) {

        RemoteControl tvRemote = new RemoteControl(new TV());
        tvRemote.turnOn();
        tvRemote.turnOff();

        RemoteControl radioRemote = new RemoteControl(new Radio());
        radioRemote.turnOn();
        radioRemote.turnOff();
    }
}

// ==================== IMPLEMENTOR ====================
interface Device {
    void turnOn();
    void turnOff();
}

// ==================== CONCRETE IMPLEMENTORS ====================
class TV implements Device {
    @Override public void turnOn() { System.out.println("TV is ON"); }
    @Override public void turnOff() { System.out.println("TV is OFF"); }
}

class Radio implements Device {
    @Override public void turnOn() { System.out.println("Radio is ON"); }
    @Override public void turnOff() { System.out.println("Radio is OFF"); }
}

// ==================== ABSTRACTION ====================
class RemoteControl {
    private final Device device;
    public RemoteControl(Device device) { this.device = device; }
    public void turnOn() { device.turnOn(); }
    public void turnOff() { device.turnOff(); }
}

/*
================ BRIDGE PATTERN – FEATURES & DESIGN =================
WHAT:
- Decouples abstraction from implementation.
- Example: Remote control can work with any device.
REAL SYSTEM USAGE:
- GUI frameworks, device drivers, cross-platform applications
*/

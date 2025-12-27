package JAVA_15_BEHAVIORAL_PATTERNS;

/**
 * COMMAND PATTERN – REMOTE CONTROL EXAMPLE
 */
public class Java_3_Command {
    public static void main(String[] args) {

        RemoteControl remote = new RemoteControl();
        Light light = new Light();

        remote.setCommand(new LightOnCommand(light));
        remote.pressButton();

        remote.setCommand(new LightOffCommand(light));
        remote.pressButton();
    }
}

// ==================== COMMAND INTERFACE ====================
interface Command {
    void execute();
}

// ==================== CONCRETE COMMANDS ====================
class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light light) { this.light = light; }
    @Override public void execute() { light.on(); }
}

class LightOffCommand implements Command {
    private final Light light;
    public LightOffCommand(Light light) { this.light = light; }
    @Override public void execute() { light.off(); }
}

// ==================== RECEIVER ====================
class Light {
    public void on() { System.out.println("Light is ON"); }
    public void off() { System.out.println("Light is OFF"); }
}

// ==================== INVOKER ====================
class RemoteControl {
    private Command command;
    public void setCommand(Command command) { this.command = command; }
    public void pressButton() { command.execute(); }
}

/*
================ COMMAND PATTERN – FEATURES & DESIGN =================
WHAT:
- Encapsulates a request as an object.
- Example: Remote control commands.
WHY IT EXISTS:
- Decouples sender and receiver.
- Supports undo, queueing, logging of operations.
REAL SYSTEM USAGE:
- GUI buttons, task scheduling, job queues, transactional systems
*/

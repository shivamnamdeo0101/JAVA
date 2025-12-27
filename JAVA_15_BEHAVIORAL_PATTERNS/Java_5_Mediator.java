package JAVA_15_BEHAVIORAL_PATTERNS;

/**
 * MEDIATOR PATTERN – CHAT ROOM EXAMPLE
 */
public class Java_5_Mediator {
    public static void main(String[] args) {

        ChatRoom chatRoom = new ChatRoom();

        User u1 = new User("Alice", chatRoom);
        User u2 = new User("Bob", chatRoom);

        u1.sendMessage("Hi Bob!");
        u2.sendMessage("Hey Alice!");
    }
}

// ==================== MEDIATOR ====================
interface ChatMediator {
    void showMessage(User user, String message);
}

// ==================== CONCRETE MEDIATOR ====================
class ChatRoom implements ChatMediator {
    @Override
    public void showMessage(User user, String message) {
        System.out.println(user.getName() + ": " + message);
    }
}

// ==================== COLLEAGUE ====================
class User {
    private final String name;
    private final ChatMediator mediator;

    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() { return name; }

    public void sendMessage(String message) { mediator.showMessage(this, message); }
}

/*
================ MEDIATOR PATTERN – FEATURES & DESIGN =================
WHAT:
- Defines an object that encapsulates how a set of objects interact.
- Example: Chat room managing messages.
WHY IT EXISTS:
- Reduces direct dependencies between objects.
REAL SYSTEM USAGE:
- Chat applications, airline reservation systems, workflow engines
*/

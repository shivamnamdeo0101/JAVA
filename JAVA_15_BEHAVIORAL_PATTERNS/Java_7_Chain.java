package JAVA_15_BEHAVIORAL_PATTERNS;

/**
 * CHAIN OF RESPONSIBILITY PATTERN – TECH SUPPORT EXAMPLE
 */
public class Java_7_Chain {
    public static void main(String[] args) {

        SupportLevel level1 = new Level1Support();
        SupportLevel level2 = new Level2Support();
        SupportLevel level3 = new Level3Support();

        level1.setNext(level2);
        level2.setNext(level3);

        level1.handleTicket("Password reset");
        level1.handleTicket("Network outage");
        level1.handleTicket("Server crash");
    }
}

// ==================== HANDLER ====================
abstract class SupportLevel {
    protected SupportLevel next;
    public void setNext(SupportLevel next) { this.next = next; }
    public abstract void handleTicket(String issue);
}

// ==================== CONCRETE HANDLERS ====================
class Level1Support extends SupportLevel {
    @Override
    public void handleTicket(String issue) {
        if (issue.equalsIgnoreCase("Password reset")) {
            System.out.println("Level 1 resolved: " + issue);
        } else if (next != null) { next.handleTicket(issue); }
    }
}

class Level2Support extends SupportLevel {
    @Override
    public void handleTicket(String issue) {
        if (issue.equalsIgnoreCase("Network outage")) {
            System.out.println("Level 2 resolved: " + issue);
        } else if (next != null) { next.handleTicket(issue); }
    }
}

class Level3Support extends SupportLevel {
    @Override
    public void handleTicket(String issue) {
        System.out.println("Level 3 resolved: " + issue);
    }
}

/*
================ CHAIN OF RESPONSIBILITY PATTERN – FEATURES & DESIGN =================
WHAT:
- Passes request along a chain of handlers.
- Example: Tech support escalations.
WHY IT EXISTS:
- Avoids tight coupling between sender and receiver.
REAL SYSTEM USAGE:
- Logging frameworks, event handling, customer support, workflow engines
*/

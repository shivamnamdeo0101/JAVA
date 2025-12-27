package JAVA_16_STRUCTURAL_PATTERNS;

/**
 * FACADE PATTERN – PAYMENT GATEWAY EXAMPLE
 */
public class Java_5_Facade {
    public static void main(String[] args) {
        PaymentFacade payment = new PaymentFacade();
        payment.payByCard(100);
        payment.payByUPI(50);
    }
}

// ==================== SUBSYSTEMS ====================
class CardPayment {
    void payCard(double amount) { System.out.println("Paid " + amount + " via Card"); }
}

class UpiPayment {
    void payUPI(double amount) { System.out.println("Paid " + amount + " via UPI"); }
}

// ==================== FACADE ====================
class PaymentFacade {
    private final CardPayment card = new CardPayment();
    private final UpiPayment upi = new UpiPayment();

    void payByCard(double amount) { card.payCard(amount); }
    void payByUPI(double amount) { upi.payUPI(amount); }
}

/*
================ FACADE PATTERN – FEATURES & DESIGN =================
WHAT:
- Provides a unified interface to a set of interfaces in subsystem.
- Example: Payment gateway simplifies multiple payment types.
REAL SYSTEM USAGE:
- Complex subsystems (file systems, network APIs, DB connections)
- Hiding complexity from client code
*/

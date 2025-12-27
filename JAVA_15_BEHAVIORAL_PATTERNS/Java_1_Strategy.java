package JAVA_15_BEHAVIORAL_PATTERNS;

/**
 * STRATEGY PATTERN – PAYMENT GATEWAY EXAMPLE
 */
public class Java_1_Strategy {
    public static void main(String[] args) {

        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment());
        context.pay(500);

        context.setPaymentStrategy(new PayPalPayment());
        context.pay(1200);
    }
}

// ==================== STRATEGY INTERFACE ====================
interface PaymentStrategy {
    void pay(int amount);
}

// ==================== CONCRETE STRATEGIES ====================
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

// ==================== CONTEXT ====================
class PaymentContext {
    private PaymentStrategy strategy;
    public void setPaymentStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
    public void pay(int amount) { strategy.pay(amount); }
}

/*
================ STRATEGY PATTERN – FEATURES & DESIGN =================
WHAT:
- Defines a family of algorithms and makes them interchangeable.
- Example: Payment methods (CreditCard, PayPal, Wallet).
WHY IT EXISTS:
- Avoids multiple if-else in business logic.
- Makes it easy to add new payment methods without modifying existing code.
INTERNAL WORKING:
- Context holds reference to Strategy interface.
- Concrete strategies implement the interface.
- Context delegates behavior to strategy object.
CORE FEATURES:
- Behavior encapsulation
- Runtime algorithm selection
REAL SYSTEM USAGE:
- Payment gateways, sorting algorithms, dynamic routing, tax calculation modules
*/

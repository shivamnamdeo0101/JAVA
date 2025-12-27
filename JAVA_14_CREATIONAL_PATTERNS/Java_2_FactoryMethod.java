package JAVA_14_CREATIONAL_PATTERNS;

/**
 * FACTORY METHOD PATTERN – NOTIFICATION EXAMPLE
 */
public class Java_2_FactoryMethod {
    public static void main(String[] args) {

        // Create email notification
        Notification emailNotification = NotificationFactory.createNotification("EMAIL");
        emailNotification.notifyUser("Welcome to our platform!");

        // Create SMS notification
        Notification smsNotification = NotificationFactory.createNotification("SMS");
        smsNotification.notifyUser("Your OTP is 123456");
    }
}

// ==================== NOTIFICATION INTERFACE ====================
interface Notification {
    void notifyUser(String message);
}

// Concrete implementations
class EmailNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SMSNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// ==================== FACTORY ====================
class NotificationFactory {
    public static Notification createNotification(String type) {
        switch (type.toUpperCase()) {
            case "EMAIL":
                return new EmailNotification();
            case "SMS":
                return new SMSNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type");
        }
    }
}

/*
================ FACTORY METHOD – FEATURES & DESIGN =================

WHAT:
-----
Factory Method defines an interface for creating objects, but allows subclasses
to decide which class to instantiate. It decouples object creation from usage.

WHY IT EXISTS:
--------------
Before Factory Method:
• Client directly instantiates classes
• Changes in implementation break client code
Factory Method solves:
✔ Decoupled object creation
✔ Easy extension without modifying client

INTERNAL WORKING:
-----------------
- Client calls factory method
- Factory method returns instance of concrete class
- No new keyword in client code

DEFAULT VALUES:
---------------
- No thread-safety needed (depends on objects)
- Can be extended to dynamic type mapping

TIME COMPLEXITY:
----------------
- Object creation: O(1)
- Client code remains stable

CORE FEATURES:
--------------
- Decouples object creation
- Open for extension, closed for modification
- Supports polymorphism

ENTERPRISE PITFALLS:
-------------------
❌ Overuse: creating factories for trivial objects
❌ Ignoring constructor parameters in factory
❌ Hard-coded strings instead of enums

REAL SYSTEM USAGE:
-----------------
✔ Notification services
✔ Payment gateway integration
✔ Report generators
✔ Plugin-based architectures

INTERVIEW QUESTIONS:
-------------------
Q1: Difference between Factory Method and Abstract Factory?
A: Factory Method creates one product, Abstract Factory creates families of products.

Q2: Why use Factory Method over new?
A: Reduces coupling and makes code easier to extend.

Q3: Can Factory Method return null?
A: Yes, but it’s better to throw an exception to avoid NPE.

INTERVIEW ONE-LINER:
-------------------
"Factory Method decouples instantiation, making systems extensible without changing client code."
*/

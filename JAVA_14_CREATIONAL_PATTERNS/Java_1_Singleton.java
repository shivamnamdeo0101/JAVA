package JAVA_14_CREATIONAL_PATTERNS;

import java.time.LocalDateTime;

/**
 * SINGLETON PATTERN – LOGGER EXAMPLE
 */
public class Java_1_Singleton {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Starting application...");
        logger2.log("Processing data...");

        System.out.println("Are logger instances same? " + (logger1 == logger2));
    }
}

// ================= SINGLETON LOGGER =================
class Logger {

    // Eager initialization
    private static final Logger instance = new Logger();

    // Private constructor
    private Logger() {
        System.out.println("Logger initialized at " + LocalDateTime.now());
    }

    // Global access point
    public static Logger getInstance() {
        return instance;
    }

    // Logging utility
    public void log(String message) {
        System.out.println(LocalDateTime.now() + " [LOG] " + message);
    }
}

/*
================ SINGLETON – FEATURES & DESIGN =================

WHAT:
-----
Ensures a class has only one instance and provides a global point of access.

WHY IT EXISTS:
--------------
Before singleton:
• Multiple instances of Logger or Config could exist
• Risk of inconsistent state
Singleton solves:
✔ Centralized control
✔ Shared resources like Logging, Config, ThreadPool

INTERNAL WORKING:
-----------------
- Private static final instance ensures single object.
- Private constructor blocks external instantiation.
- Global access via getInstance().

DEFAULT VALUES:
---------------
- Thread-safe due to eager initialization
- Lazy init can be implemented with synchronized or static holder

TIME COMPLEXITY:
----------------
- Access: O(1)
- Memory: O(1) (single instance)

CORE FEATURES:
--------------
- Only one instance
- Global access
- Thread-safe (depends on implementation)
- Used for logging, configuration, cache

ENTERPRISE PITFALLS:
-------------------
❌ Using singleton for mutable objects without thread safety
❌ Creating multiple instances via reflection or serialization

REAL SYSTEM USAGE:
-----------------
✔ Logger, Audit trails
✔ Configuration Manager
✔ Connection Pool Manager

INTERVIEW QUESTIONS:
-------------------
Q1: How to make Singleton thread-safe?
A: Use eager init, synchronized block, or static holder pattern.

Q2: Difference between Singleton and Static Class?
A: Singleton can implement interfaces and be passed around; static class cannot.

Q3: Can Singleton be broken via serialization?
A: Yes, override readResolve() method to return same instance.

Q4: How to prevent Reflection-based instantiation?
A: Throw exception in constructor if instance already exists.

INTERVIEW ONE-LINER:
-------------------
"Singleton provides controlled single instance access across the application."
*/

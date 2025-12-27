package JAVA_10_FUNCTIONAL_PROGRAMMING;

/**
 * TOPIC: Functional Interfaces (SAM - Single Abstract Method)
 * * CONCEPT:
 * - A Functional Interface is a "Blueprint" for a Lambda.
 * - It must have EXACTLY ONE abstract method.
 * - It can have multiple 'default' and 'static' methods.
 */
@FunctionalInterface // Optional but recommended: Prevents adding a 2nd abstract method
interface SmartCalculator {
    
    // 1. THE SAM (Single Abstract Method)
    // This is what the Lambda will implement.
    int calculate(int x, int y);

    // 2. DEFAULT METHODS
    // Why: To add new functionality without breaking existing implementations.
    default void printDescription() {
        System.out.println("I am a Functional Interface performing math.");
    }

    // 3. STATIC METHODS
    // Why: Helper methods related to the interface.
    static boolean isPositive(int n) {
        return n > 0;
    }
}

public class Java_2_FunctionalInterfaces {
    public static void main(String[] args) {

        // Use Case: Strategy Pattern (Behavioral design)
        // We provide the "Logic" at runtime using Lambdas.
        
        SmartCalculator addition = (a, b) -> a + b;
        SmartCalculator power = (a, b) -> (int) Math.pow(a, b);

        System.out.println("Sum: " + addition.calculate(10, 20));
        System.out.println("Power: " + power.calculate(2, 3));
        
        // Calling default method
        addition.printDescription();

        // Calling static method
        System.out.println("Is 5 positive? " + SmartCalculator.isPositive(5));
    }
}

/*
================================================================================
FUNCTIONAL INTERFACES – MASTER CHEATSHEET
================================================================================

1️⃣ THE RULES (The "SAM" Rule)
-----------------------------
• MUST HAVE: Exactly one abstract method.
• CAN HAVE: 
  - Multiple 'default' methods.
  - Multiple 'static' methods.
  - Overridden methods from Object class (e.g., toString(), equals()).

2️⃣ INTERNAL WORKING (Bytecode Level)
------------------------------------
• When you write: `SmartCalculator addition = (a, b) -> a + b;`
• JVM does NOT create a class file. It uses 'invokedynamic' instruction to create
  a call site that points to the lambda logic.
• Memory: Much lighter than Anonymous Inner Classes.

3️⃣ TYPES OF FUNCTIONAL INTERFACES
----------------------------------
A. Custom: Defined by you (like SmartCalculator).
B. Built-in: (Java 8 Package java.util.function)
   - Predicate (Boolean check)
   - Consumer (Executes action)
   - Function (Transformation)
   - Supplier (Data generation)

4️⃣ REAL-WORLD SYSTEM USAGE (Enterprise)
---------------------------------------
✔ Strategy Pattern: Passing different tax calculation logic based on country.
✔ Event Listeners: Handling button clicks or message arrivals.
✔ Threading: Runnable is a Functional Interface used in new Thread(() -> ...).

5️⃣ COMMON INTERVIEW QUESTIONS
-----------------------------
Q: What happens if I add a second abstract method to @FunctionalInterface?
A: Compiler Error: "Invalid '@FunctionalInterface' annotation; ... is not a functional interface".

Q: Can a Functional Interface extend another interface?
A: Yes, as long as the total number of abstract methods remains exactly ONE.

Q: Why use default methods?
A: To add new features to interfaces (like Stream support in Collections) without 
   breaking billions of lines of legacy code.

6️⃣ SUMMARY ANALOGY
-------------------
• Functional Interface = A "Job Description" that has only one main task.
• Lambda = The "Worker" who performs that one specific task.
• @FunctionalInterface = The "HR Policy" that ensures the job description stays simple.

================================================================================
*/
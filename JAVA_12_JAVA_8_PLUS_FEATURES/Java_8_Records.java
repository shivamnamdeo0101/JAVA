package JAVA_12_JAVA_8_PLUS_FEATURES;

/**
 * Topic: Records
 *
 * Java Records are **compact, immutable data carriers** introduced in Java 14 (preview) and standardized in Java 16.
 * They reduce boilerplate for classes whose main purpose is to hold data.
 */
public record Java_8_Records(String name, int age) {
    // compact, immutable data carrier
}

class TestRecords {
    public static void main(String[] args) {
        Java_8_Records r = new Java_8_Records("Alice", 25);

        // Accessors auto-generated
        System.out.println(r.name() + " - " + r.age());

        // Auto-generated toString
        System.out.println(r); // Java_8_Records[name=Alice, age=25]

        // Auto-generated equals and hashCode
        Java_8_Records r2 = new Java_8_Records("Alice", 25);
        System.out.println(r.equals(r2)); // true
    }
}

/*
================================================================================
RECORDS – DEEP DIVE (Updated Cheat Sheet)
================================================================================

Lombok POJO → flexible, can be mutable, works anywhere, needs dependency.
Record → immutable, no boilerplate, modern Java, simpler, safer.
Rule of thumb:
DTOs / API responses / value objects → Record
Complex entities / mutable objects / extendable → Lombok POJO

WHAT:
-----
• Immutable, transparent data carriers
• Reduce boilerplate code (equals, hashCode, toString, constructor)
• Designed primarily for **data storage** and transfer

WHY IT EXISTS:
--------------
• Avoid verbose POJO creation
• Supports immutability & concise, readable code
• Works well with **functional programming patterns**

INTERNAL WORKING:
-----------------
• Fields are implicitly final
• Compact canonical constructor auto-generated
• Auto-generates equals(), hashCode(), toString()
• No setters → immutable by design
• Can implement interfaces
• Cannot extend another class (all records extend java.lang.Record)

CORE FEATURES:
--------------
✔ Immutable fields (final)
✔ Concise declaration
✔ Auto-generated canonical constructor
✔ Auto-generated equals(), hashCode(), toString()
✔ Implements interfaces but cannot extend other classes
✔ Supports pattern matching in newer Java versions

ENTERPRISE PITFALLS:
-------------------
❌ Not suitable for mutable data
❌ Avoid extending or adding complex state
❌ Keep records **focused on data**, avoid heavy business logic

REAL SYSTEM USAGE:
-----------------
✔ DTOs (Data Transfer Objects)
✔ API request/response payloads
✔ Configuration objects
✔ Value objects in functional-style programming

EXAMPLES:
---------
Java_8_Records person = new Java_8_Records("Alice", 25);
System.out.println(person.name());       // "Alice"
System.out.println(person.age());        // 25
System.out.println(person);              // "Java_8_Records[name=Alice, age=25]"

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Can a record extend a class?
A: ❌ No, records implicitly extend java.lang.Record

Q2: Can a record implement interfaces?
A: ✅ Yes, e.g., record Person(...) implements Comparable<Person>

Q3: Can record fields be mutable?
A: ❌ Fields are implicitly final, immutable

Q4: Difference between POJO and Record?
A: POJO → needs boilerplate (getters, setters, equals, hashCode, toString)
   Record → auto-generates all boilerplate, immutable by default

INTERVIEW ONE-LINER:
-------------------
"Records are compact, immutable data carriers that reduce boilerplate and enable functional-style data handling."
================================================================================
*/

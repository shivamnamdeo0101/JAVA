package JAVA_12_JAVA_8_PLUS_FEATURES;

/**
 * Topic: Records
 */
public record Java_8_Records(String name, int age) {
    // compact, immutable data carrier
}

class TestRecords {
    public static void main(String[] args) {
        Java_8_Records r = new Java_8_Records("Alice", 25);
        System.out.println(r.name() + " - " + r.age());
    }
}

/*
================================================================================
RECORDS – DEEP DIVE
================================================================================

WHAT:
-----
• Immutable data carriers
• Reduces boilerplate (equals, hashCode, toString)

WHY IT EXISTS:
--------------
• Avoid verbose POJO creation
• Supports immutability & conciseness

INTERNAL WORKING:
-----------------
• final fields, compact constructor
• Compiler generates equals, hashCode, toString
• No setters; fields are final

CORE FEATURES:
--------------
✔ Immutable
✔ Concise
✔ Auto-generated methods
✔ Compact canonical constructor

ENTERPRISE PITFALLS:
-------------------
❌ Using for mutable data
❌ Avoid extending records

REAL SYSTEM USAGE:
-----------------
✔ DTOs
✔ API request/response
✔ Config objects

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Can a record extend a class?
A: No, records implicitly extend java.lang.Record

INTERVIEW ONE-LINER:
-------------------
"Records provide compact, immutable data carriers reducing boilerplate and supporting functional-style data handling."
================================================================================
*/

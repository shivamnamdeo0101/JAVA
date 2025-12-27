package JAVA_11_ADVANDED_JAVA;

/**
 * Topic: Enum
 */
public class Java_7_Enum {

    enum Status {
        NEW, IN_PROGRESS, DONE;
    }

    public static void main(String[] args) {
        Status s = Status.NEW;
        System.out.println("Current Status: " + s);

        for(Status st : Status.values()) {
            System.out.println(st);
        }
    }
}

/*
================================================================================
ENUM – DEEP DIVE
================================================================================

WHAT:
-----
• Special class representing fixed constants
• Type-safe, singleton instances per enum value
• Can have fields, methods, constructors

WHY IT EXISTS:
--------------
• Replace int / String constants
• Ensure type safety
• Simplify switch/case logic

INTERNAL WORKING:
-----------------
• Compiles to final class extending java.lang.Enum
• Each value is public static final
• Maintains values() array for iteration
• Implements Serializable & Comparable

CORE FEATURES:
--------------
✔ Fixed constants
✔ Type-safe
✔ Can have fields/methods
✔ Comparable by ordinal

ENTERPRISE PITFALLS:
-------------------
❌ Adding mutable fields without care
❌ Relying on ordinal for persistence

REAL SYSTEM USAGE:
-----------------
✔ Status codes
✔ Config constants
✔ Workflow states

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between enum and int constants?
A: Enum is type-safe, object-like, comparable, iterable

INTERVIEW ONE-LINER:
-------------------
"Enums provide type-safe constants that can encapsulate behavior, fields, and methods."
================================================================================
*/

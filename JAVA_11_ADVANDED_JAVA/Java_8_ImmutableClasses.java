package JAVA_11_ADVANDED_JAVA;

/**
 * Topic: Immutable Classes
 */
public class Java_8_ImmutableClasses {

    final static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }

    public static void main(String[] args) {
        Person p = new Person("Shivam", 30);
        System.out.println("Name: " + p.getName() + ", Age: " + p.getAge());
    }
}

/*
================================================================================
IMMUTABLE CLASSES – DEEP DIVE
================================================================================

WHAT:
-----
• Object whose state cannot change after creation
• Fields are final and private

WHY IT EXISTS:
--------------
• Thread-safety by design
• Predictable behavior
• Avoid defensive copying
• Core in caching, keys, constants

INTERNAL WORKING:
-----------------
• final class → prevents inheritance
• private final fields → cannot be reassigned
• no setters
• references to mutable objects must be cloned defensively

CORE FEATURES:
--------------
✔ Thread-safe
✔ Predictable
✔ Can be safely shared
✔ Used as Map keys

ENTERPRISE PITFALLS:
-------------------
❌ Mutable fields
❌ Exposing internal mutable references
❌ Forgetting final class

REAL SYSTEM USAGE:
-----------------
✔ String class
✔ LocalDate, Instant
✔ Cache keys, constants
✔ DTOs

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between immutable and mutable?
A: Immutable → state never changes, thread-safe; Mutable → state can change

INTERVIEW ONE-LINER:
-------------------
"Immutable classes ensure thread-safety and predictability by preventing state changes after creation."
================================================================================
*/

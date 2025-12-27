package JAVA_13_BEST_PRACTICES_DESIGN;

public class Java_3_Immutability {
    public static void main(String[] args) {
        ImmutablePerson person = new ImmutablePerson("Alice", 25);
        System.out.println(person.getName() + ", " + person.getAge());
        // No setters → cannot modify state
    }
}

final class ImmutablePerson {
    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) { this.name = name; this.age = age; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

/*
================================================================================
IMMUTABILITY – DEEP DIVE
================================================================================
WHAT:
- Objects whose state cannot change after creation.
- Provides thread-safety and predictable behavior.

WHY IT EXISTS:
- Prevents accidental modification.
- Critical in multi-threaded apps or shared data.

INTERNAL WORKING:
- final class → cannot extend
- final fields → cannot be reassigned
- Defensive copies for mutable fields

ENTERPRISE PITFALLS:
- Mutable objects in shared caches → data corruption.
- Forgetting defensive copy → external mutation possible.

REAL USE CASE:
- DTOs, configuration objects, keys in Map

INTERVIEW Q&A:
Q1: How to make a class immutable? → final class, final fields, no setters, defensive copy.
Q2: Are Strings immutable? → Yes.
================================================================================
*/

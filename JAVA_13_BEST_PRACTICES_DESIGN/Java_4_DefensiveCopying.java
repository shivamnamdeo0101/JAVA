package JAVA_13_BEST_PRACTICES_DESIGN;

import java.util.Date;

public class Java_4_DefensiveCopying {
    public static void main(String[] args) {
        Date dob = new Date();
        SafePerson safePerson = new SafePerson("Charlie", dob);

        dob.setTime(0); // Original mutated
        System.out.println("DOB (SafePerson): " + safePerson.getDob()); // Remains unchanged
    }
}

class SafePerson {
    private String name;
    private Date dob;

    public SafePerson(String name, Date dob) {
        this.name = name;
        this.dob = new Date(dob.getTime()); // defensive copy
    }

    public String getName() { return name; }
    public Date getDob() { return new Date(dob.getTime()); } // defensive copy
}

/*
================================================================================
DEFENSIVE COPYING – DEEP DIVE
================================================================================
WHAT:
- Returns a copy of internal mutable state to prevent external modification.

WHY IT EXISTS:
- Mutable objects shared externally can be altered → data corruption.

INTERNAL WORKING:
- Constructor copies mutable fields.
- Getter returns new object → caller cannot modify internal state.

ENTERPRISE PITFALLS:
- Directly returning internal mutable objects → accidental modification.
- Forgetting defensive copy in setter or constructor.

REAL USE CASE:
- Date, List, Map in DTOs or configuration objects

INTERVIEW Q&A:
Q1: Why defensive copying? → Prevent external mutation.
Q2: Is defensive copy required for immutable objects? → No.
================================================================================
*/

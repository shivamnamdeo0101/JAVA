package JAVA_10_FUNCTIONAL_PROGRAMMING;

import java.util.*;

/**
 * Topic: Optional
 */
public class Java_7_Optional {

    public static void main(String[] args) {

        // Optional.ofNullable
        Optional<String> opt = Optional.ofNullable(null);

        // Check presence
        System.out.println("Is present? " + opt.isPresent());

        // orElse
        System.out.println("Value or default: " + opt.orElse("Default"));

        // orElseGet (Supplier)
        System.out.println("Value via Supplier: " + opt.orElseGet(() -> "Generated"));

        // ifPresent
        opt.ifPresent(System.out::println);

        // map
        Optional<Integer> lengthOpt = opt.map(String::length);
        System.out.println("Length: " + lengthOpt.orElse(0));


        Optional<String> opt1 = Optional.of("Hello");
        Optional<String> opt2 = Optional.empty();

        opt1.ifPresent(System.out::println); // prints "Hello"
        opt2.ifPresent(System.out::println); // prints nothing




    }
}

/*
================================================================================
OPTIONAL – DEEP DIVE
================================================================================

WHAT:
-----
• Container to represent optional value (may be null)
• Avoids NullPointerException
• Provides functional APIs for safe access

WHY IT EXISTS:
--------------
• Replace null checks
• Chain transformations safely
• Encourages immutability

INTERNAL WORKING:
-----------------
• Holds reference internally
• isPresent() checks if value != null
• map(), flatMap() apply functions if value exists
• orElse / orElseGet provide defaults lazily/eagerly

CORE FEATURES:
--------------
• Nullable-safe operations
• Functional-style API: map, flatMap, filter
• Lazy default computation via Supplier

ENTERPRISE PITFALLS:
-------------------
❌ Using Optional in fields (should be return types)
❌ Excessive nesting
❌ Using get() without isPresent

REAL SYSTEM USAGE:
-----------------
✔ REST API response wrapping
✔ DB query results
✔ Null-safe aggregation
✔ Stream pipelines

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference orElse vs orElseGet?
A: orElse always evaluates, orElseGet lazily via Supplier.

Q2: Can Optional hold null?
A: Optional itself cannot hold null; use Optional.ofNullable.

INTERVIEW ONE-LINER:
-------------------
"Optional encapsulates a potentially null value, allowing functional, null-safe operations."
================================================================================
*/

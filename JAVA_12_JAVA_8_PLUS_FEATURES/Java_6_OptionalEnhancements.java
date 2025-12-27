package JAVA_12_JAVA_8_PLUS_FEATURES;

import java.util.*;

/**
 * Topic: Optional Enhancements
 */
public class Java_6_OptionalEnhancements {
    public static void main(String[] args) {

        Optional<String> optional = Optional.of("Hello");

        // =========================
        // Basic Operations
        // =========================
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("Default"));
        optional.ifPresent(System.out::println);

        // =========================
        // Map / Filter
        // =========================
        Optional<String> filtered = optional.filter(s -> s.startsWith("H"));
        System.out.println(filtered.orElse("Not Found"));

        Optional<Integer> length = optional.map(String::length);
        System.out.println(length.orElse(0));
    }
}

/*
================================================================================
OPTIONAL – DEEP DIVE
================================================================================

WHAT:
-----
• Wrapper for optional values
• Prevents null checks / NullPointerException

WHY IT EXISTS:
--------------
• Null checks prone to errors
• Encourages functional-style handling of absent values

INTERNAL WORKING:
-----------------
• Holds reference to value
• empty() = singleton
• Map, Filter, ifPresent delegate to internal value
• No mutation, thread-safe

CORE FEATURES:
--------------
✔ Prevents nulls
✔ Functional methods
✔ Chainable
✔ Thread-safe

ENTERPRISE PITFALLS:
-------------------
❌ Overusing Optional in fields
❌ Misusing get() without isPresent
❌ Nested optionals

REAL SYSTEM USAGE:
-----------------
✔ API return types
✔ Optional config values
✔ Functional pipelines

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Can Optional be null itself?
A: No, use Optional.empty() for no value

Q2: Difference between orElse and orElseGet?
A: orElse always evaluates; orElseGet lazy

INTERVIEW ONE-LINER:
-------------------
"Optional wraps potentially absent values, enforcing safer, functional-style handling."
================================================================================
*/

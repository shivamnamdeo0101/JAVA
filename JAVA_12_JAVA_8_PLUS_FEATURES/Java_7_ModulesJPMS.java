package JAVA_12_JAVA_8_PLUS_FEATURES;

/**
 * Topic: Modules (JPMS)
 */
public class Java_7_ModulesJPMS {
    public static void main(String[] args) {
        System.out.println("Java Platform Module System (JPMS) example");
        // Actual module declaration is in module-info.java
    }
}

/*
================================================================================
JPMS – DEEP DIVE
================================================================================

WHAT:
-----
• Java 9+ module system
• Groups packages, controls visibility, dependency

WHY IT EXISTS:
--------------
• Avoid classpath hell
• Encapsulation across packages
• Explicit dependencies

INTERNAL WORKING:
-----------------
• module-info.java describes requires/exports
• JVM loads modules at startup
• Enforces access rules at compile & runtime

CORE FEATURES:
--------------
✔ Encapsulation at package level
✔ Explicit dependencies
✔ Strong module boundaries

ENTERPRISE PITFALLS:
-------------------
❌ Mixing module path and classpath
❌ Exposing internal APIs
❌ Circular dependencies

REAL SYSTEM USAGE:
-----------------
✔ Large enterprise systems
✔ JDK internal modules
✔ Plugin-based architectures

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between module and package?
A: Module = group of packages + dependency rules; Package = namespace

INTERVIEW ONE-LINER:
-------------------
"JPMS brings strong modular boundaries, better encapsulation, and safer dependency management."
================================================================================
*/

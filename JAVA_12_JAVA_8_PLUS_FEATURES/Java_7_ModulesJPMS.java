package JAVA_12_JAVA_8_PLUS_FEATURES;

/**
 * Topic: Modules (JPMS)
 *
 * This is a demonstration of Java Platform Module System (JPMS).
 * Actual module definitions and dependencies are declared in module-info.java.
 */
public class Java_7_ModulesJPMS {

    public static void main(String[] args) {
        System.out.println("Java Platform Module System (JPMS) example");

        // Example usage of a module class (would require module dependency in module-info.java)
        // com.example.greet.Greeter greeter = new com.example.greet.Greeter();
        // greeter.sayHello();
    }
}

/*
================================================================================
JPMS – DEEP DIVE
================================================================================


JPMS is mainly used by:

JDK itself

Large enterprise systems

Framework/library developers

Plugin-based or embedded applications

JPMS is NOT commonly used by:

Spring Boot, most microservices, legacy projects

WHAT:
-----
• Java 9+ modular system
• Groups related packages into modules
• Controls visibility and enforces explicit dependencies

WHY IT EXISTS:
--------------
• Avoid "classpath hell" in large projects
• Strong encapsulation across packages
• Explicit declaration of dependencies for safer code

INTERNAL WORKING:
-----------------
• module-info.java declares:
      - module name
      - required modules (requires)
      - exported packages (exports)
• JVM loads modules at startup
• Compile-time and runtime enforce access rules

CORE FEATURES:
--------------
✔ Encapsulation at package level
✔ Explicit module dependencies
✔ Strong boundaries between modules
✔ Supports modular runtime (jlink)

ENTERPRISE PITFALLS:
-------------------
❌ Mixing module path and classpath can break access rules
❌ Exposing internal APIs reduces encapsulation
❌ Circular module dependencies are not allowed

REAL SYSTEM USAGE:
-----------------
✔ Large enterprise systems
✔ JDK internal modules
✔ Plugin-based architectures (e.g., IDEs, servers)

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between module and package?
A: Module = group of packages with dependency rules;
   Package = namespace for classes without enforced boundaries.

Q2: Can a module hide packages completely?
A: Yes, only exported packages are visible to other modules; rest remain internal.

INTERVIEW ONE-LINER:
-------------------
"JPMS enforces strong modular boundaries, better encapsulation, and reliable dependency management."
================================================================================
*/
